/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.asah.salesforce.extractor.bot.nanite;

import com.liferay.osb.asah.common.array.ArrayUtil;
import com.liferay.osb.asah.common.bot.exception.InterruptBotException;
import com.liferay.osb.asah.common.bot.nanite.Nanite;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoOSBAsahTaskDog;
import com.liferay.osb.asah.common.json.JSONArrayPaginator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.run.logger.RunLogger;
import com.liferay.osb.asah.salesforce.extractor.bot.SalesforceExtractorConfigurableBot;
import com.liferay.osb.asah.salesforce.extractor.client.SalesforceBulkClientInvoker;
import com.liferay.osb.asah.salesforce.extractor.client.SalesforcePartnerClientInvoker;
import com.liferay.osb.asah.salesforce.extractor.configuration.SalesforceExtractorConfiguration;
import com.liferay.osb.asah.salesforce.extractor.util.ElasticsearchPropertyUtil;
import com.liferay.osb.asah.salesforce.extractor.util.SOQLUtil;
import com.liferay.osb.asah.salesforce.extractor.util.SchemaUtil;
import com.liferay.osb.asah.salesforce.extractor.util.TimeUtil;
import com.liferay.osb.asah.salesforce.extractor.xml.SalesforceBulkContentHandler;
import com.liferay.osb.asah.salesforce.extractor.xml.XMLUtil;

import com.sforce.async.BatchInfo;
import com.sforce.async.QueryResultList;
import com.sforce.soap.partner.DeletedRecord;
import com.sforce.soap.partner.DescribeSObjectResult;
import com.sforce.soap.partner.Field;
import com.sforce.soap.partner.GetDeletedResult;
import com.sforce.soap.partner.GetUpdatedResult;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.fault.ExceptionCode;
import com.sforce.soap.partner.fault.UnexpectedErrorFault;

import java.io.File;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

/**
 * @author Brian Wing Shun Chan
 * @author Rachael Koestartyo
 */
public class SalesforceExtractorNanite implements Nanite {

	public SalesforceExtractorNanite(
		SalesforceExtractorConfiguration salesforceExtractorConfiguration,
		String[] tableNames) {

		_salesforceExtractorConfiguration = salesforceExtractorConfiguration;

		if (tableNames == null) {
			_tableNames = salesforceExtractorConfiguration.getTableNames();
		}
		else {
			_tableNames = Arrays.copyOf(tableNames, tableNames.length);
		}

		_osbAsahDataSourceIdTermQueryBuilder = QueryBuilders.termQuery(
			"osbAsahDataSourceId",
			_salesforceExtractorConfiguration.getDataSourceId());
	}

	@PostConstruct
	public void init() {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forSalesforceRaw();
	}

	@Override
	public void run() throws Exception {
		_throwNewInterruptBotException();

		if (!Objects.equals(
				_salesforceExtractorConfiguration.getDataSourceState(),
				"CREDENTIALS_VALID") ||
			!Objects.equals(
				_salesforceExtractorConfiguration.getDataSourceStatus(),
				"ACTIVE")) {

			if (_log.isInfoEnabled()) {
				_log.info(
					"Skipping nanite because data source " +
						_salesforceExtractorConfiguration.getDataSourceId() +
							" is not active");
			}

			return;
		}

		_run();
	}

	private void _addAuditEvent(
		String eventType, JSONObject jsonObject, String recordId,
		String typeName) {

		JSONObject auditEventJSONObject = JSONUtil.put(
			"additionalInfo", jsonObject
		).put(
			"auditEventDate", DateUtil.newDateString()
		).put(
			"eventType", eventType
		).put(
			"osbAsahDataSourceId",
			_salesforceExtractorConfiguration.getDataSourceId()
		).put(
			"recordId", recordId
		).put(
			"typeName", typeName
		);

		try {
			_elasticsearchInvoker.add("audit-events", auditEventJSONObject);
		}
		catch (Exception e) {
			_log.error(
				"Unable to populate audit events with JSON " +
					auditEventJSONObject.toString(),
				e);
		}
	}

	private Object[] _buildRunLogAdditionalFields(
			List<DescribeSObjectResult> describeSObjectResults,
			JSONObject osbAsahMarkerJSONObject)
		throws Exception {

		Object[] runLogAdditionalFields =
			new Object[4 * describeSObjectResults.size()];

		JSONObject tablesJSONObject = osbAsahMarkerJSONObject.getJSONObject(
			"tables");

		for (int i = 0; i < describeSObjectResults.size(); i++) {
			DescribeSObjectResult describeSObjectResult =
				describeSObjectResults.get(i);

			String tableName = describeSObjectResult.getName();

			runLogAdditionalFields[4 * i] = "total" + tableName + "Operations";
			runLogAdditionalFields[4 * i + 2] = "initial" + tableName + "Run";

			if (_isNewTable(describeSObjectResult, tablesJSONObject)) {
				QueryResult queryResult = _salesforcePartnerClientInvoker.query(
					_salesforceExtractorConfiguration,
					SOQLUtil.getCountSOQL(tableName));

				runLogAdditionalFields[4 * i + 1] = queryResult.getSize();

				runLogAdditionalFields[4 * i + 3] = true;
			}
			else {
				JSONObject tableJSONObject = tablesJSONObject.optJSONObject(
					tableName);

				long lastSuccessfulUpdatedTime = tableJSONObject.getLong(
					"lastSuccessfulUpdatedTime");

				Date endDate = new Date();

				Date startDate = _getStartDate(
					endDate, lastSuccessfulUpdatedTime);

				GetUpdatedResult getUpdatedResult =
					_salesforcePartnerClientInvoker.getGetUpdatedResult(
						_salesforceExtractorConfiguration, endDate, startDate,
						tableName);

				String[] salesforceKeys = getUpdatedResult.getIds();

				GetDeletedResult getDeletedResult =
					_salesforcePartnerClientInvoker.getGetDeletedResult(
						_salesforceExtractorConfiguration, endDate, startDate,
						tableName);

				DeletedRecord[] deletedRecords =
					getDeletedResult.getDeletedRecords();

				runLogAdditionalFields[4 * i + 1] =
					salesforceKeys.length + deletedRecords.length;

				runLogAdditionalFields[4 * i + 3] = false;
			}
		}

		return runLogAdditionalFields;
	}

	private void _deleteStaleTables(
			JSONObject osbAsahMarkerJSONObject, String[] tableNames)
		throws Exception {

		boolean deletedStaleTable = false;

		Set<String> tableNamesSet = new HashSet<>(Arrays.asList(tableNames));

		JSONObject tablesJSONObject = osbAsahMarkerJSONObject.getJSONObject(
			"tables");

		Set<String> keySet = tablesJSONObject.keySet();

		Iterator<String> iterator = keySet.iterator();

		while (iterator.hasNext()) {
			String tableName = iterator.next();

			if (!tableNamesSet.contains(tableName) &&
				_elasticsearchInvoker.exists(
					tableName, _osbAsahDataSourceIdTermQueryBuilder)) {

				new JSONArrayPaginator() {

					@Override
					protected JSONArray paginate(int start, int end) {
						JSONArray jsonArray = new JSONArray(
							_elasticsearchInvoker.get(
								tableName,
								searchSourceBuilder -> {
									searchSourceBuilder.from(start);
									searchSourceBuilder.query(
										_osbAsahDataSourceIdTermQueryBuilder);
									searchSourceBuilder.size(end - start);
								}));

						for (int i = 0; i < jsonArray.length(); i++) {
							JSONObject jsonObject = jsonArray.getJSONObject(i);

							_elasticsearchInvoker.delete(tableName, jsonObject);

							_addAuditEvent(
								"DELETE", jsonObject,
								jsonObject.getString("id"), tableName);
						}

						return jsonArray;
					}

				};

				if (_log.isInfoEnabled()) {
					String osbAsahDataSourceId =
						osbAsahMarkerJSONObject.getString(
							"osbAsahDataSourceId");

					_log.info(
						"Deleted table " + tableName + " for data source " +
							osbAsahDataSourceId);
				}
			}

			if (!tableNamesSet.contains(tableName)) {
				iterator.remove();

				deletedStaleTable = true;
			}
		}

		if (deletedStaleTable) {
			osbAsahMarkerJSONObject.put("tables", tablesJSONObject);

			_elasticsearchInvoker.replace(
				"OSBAsahMarkers", osbAsahMarkerJSONObject);
		}
	}

	private File _getFile(
			String batchInfoId, String batchInfoJobId, String queryResultId)
		throws Exception {

		try (InputStream inputStream =
				_salesforceBulkClientInvoker.getQueryResultStream(
					_salesforceExtractorConfiguration, batchInfoId,
					batchInfoJobId, queryResultId)) {

			File file = new File("query-result.xml");

			FileUtils.copyInputStreamToFile(inputStream, file);

			return file;
		}
	}

	private JSONObject _getOSBAsahMarkerJSONObject() {
		JSONObject osbAsahMarkerJSONObject = _elasticsearchInvoker.fetch(
			"OSBAsahMarkers", _osbAsahDataSourceIdTermQueryBuilder);

		if (osbAsahMarkerJSONObject == null) {
			osbAsahMarkerJSONObject = new JSONObject();

			_setOSBAsahDataSourceId(osbAsahMarkerJSONObject);

			osbAsahMarkerJSONObject.put("tables", new JSONObject());

			return _elasticsearchInvoker.add(
				"OSBAsahMarkers", osbAsahMarkerJSONObject);
		}

		return osbAsahMarkerJSONObject;
	}

	private Date _getStartDate(Date endDate, long startTime) {
		if (startTime <= 0) {
			for (int i = 7; i >= 0; i--) {
				if (i == 0) {
					startTime = endDate.getTime() - DateUtil.HOUR;
				}
				else {
					startTime = endDate.getTime() - (i * DateUtil.DAY);
				}

				Date startDate = new Date(startTime);

				try {
					_salesforcePartnerClientInvoker.getGetUpdatedResult(
						_salesforceExtractorConfiguration, endDate, startDate,
						"Account");

					return startDate;
				}
				catch (Exception e) {
					if (e instanceof UnexpectedErrorFault) {
						UnexpectedErrorFault uef = (UnexpectedErrorFault)e;

						if (uef.getExceptionCode() !=
								ExceptionCode.INVALID_REPLICATION_DATE) {

							if (_log.isDebugEnabled()) {
								_log.debug(e, e);
							}

							return startDate;
						}
					}
				}
			}
		}

		Date startDate = new Date(startTime);

		int days =
			(int)((endDate.getTime() - startDate.getTime()) / DateUtil.DAY);

		if (days > 29) {
			throw new IllegalArgumentException(
				"Start and end dates are too far apart");
		}

		return startDate;
	}

	private boolean _isNewTable(
		DescribeSObjectResult describeSObjectResult,
		JSONObject tablesJSONObject) {

		JSONObject tableJSONObject = tablesJSONObject.optJSONObject(
			describeSObjectResult.getName());

		if ((tableJSONObject == null) ||
			(tableJSONObject.getLong("lastSuccessfulUpdatedTime") <= 0)) {

			return true;
		}

		JSONObject fieldsJSONObject = _toFieldsJSONObject(
			describeSObjectResult);

		if (Objects.equals(
				fieldsJSONObject.toString(),
				tableJSONObject.getString("fields"))) {

			return false;
		}

		if (_log.isInfoEnabled()) {
			_log.info("Fields changes detected");
		}

		return true;
	}

	private void _populateNewTable(
		DescribeSObjectResult describeSObjectResult,
		JSONObject osbAsahMarkerJSONObject) {

		JSONObject tablesJSONObject = osbAsahMarkerJSONObject.getJSONObject(
			"tables");

		if (!_isNewTable(describeSObjectResult, tablesJSONObject)) {
			if (_log.isInfoEnabled()) {
				_log.info("Skip populating " + describeSObjectResult.getName());
			}

			return;
		}

		long time = System.currentTimeMillis();

		try {
			if (_log.isInfoEnabled()) {
				_log.info("Populate " + describeSObjectResult.getName());
			}

			List<Exception> exceptions = _processTable(
				describeSObjectResult,
				(JSONArray jsonArray) -> {
					try {
						_elasticsearchInvoker.save(
							describeSObjectResult.getName(), jsonArray);
					}
					catch (Exception e) {
						_log.error(
							"Unable to populate " +
								describeSObjectResult.getName() +
									" with JSON " + jsonArray,
							e);

						return e;
					}

					return null;
				},
				null);

			if (!exceptions.isEmpty()) {
				throw exceptions.get(0);
			}

			if (_log.isInfoEnabled()) {
				_log.info(
					"Populated " + describeSObjectResult.getName() + " in " +
						TimeUtil.format(time));
			}

			JSONObject fieldsJSONObject = _toFieldsJSONObject(
				describeSObjectResult);
			Date lastSuccessfulDate = _getStartDate(new Date(time), 0);

			tablesJSONObject.put(
				describeSObjectResult.getName(),
				JSONUtil.put(
					"fields", fieldsJSONObject.toString()
				).put(
					"lastSuccessfulDeletedTime", lastSuccessfulDate.getTime()
				).put(
					"lastSuccessfulUpdatedTime", lastSuccessfulDate.getTime()
				));

			osbAsahMarkerJSONObject.put("tables", tablesJSONObject);

			_elasticsearchInvoker.replace(
				"OSBAsahMarkers", osbAsahMarkerJSONObject);
		}
		catch (Exception e) {
			if (e instanceof InterruptBotException) {
				throw new InterruptBotException();
			}

			_log.error(
				"Unable to populate " + describeSObjectResult.getName(), e);
		}
	}

	private List<Exception> _processTable(
			DescribeSObjectResult describeSObjectResult,
			Function<JSONArray, Exception> saveJSONArrayFunction,
			String[] salesforceKeys)
		throws Exception {

		List<Exception> exceptions = new ArrayList<>();

		BatchInfo batchInfo = null;

		try {
			String soql = SOQLUtil.getSOQL(
				describeSObjectResult, salesforceKeys);

			batchInfo = _salesforceBulkClientInvoker.getBatchInfo(
				_salesforceExtractorConfiguration, soql,
				describeSObjectResult.getName());

			QueryResultList queryResultList =
				_salesforceBulkClientInvoker.getQueryResultList(
					_salesforceExtractorConfiguration, batchInfo.getId(),
					batchInfo.getJobId());

			for (String queryResultId : queryResultList.getResult()) {
				File file = null;

				try {
					XMLReader xmlReader = XMLUtil.newXMLReader();

					xmlReader.setContentHandler(
						new SalesforceBulkContentHandler(
							describeSObjectResult, exceptions,
							_salesforceExtractorConfigurableBot::isStop,
							_salesforceExtractorConfiguration.getDataSourceId(),
							(JSONArray jsonArray) -> {
								try {
									_elasticsearchInvoker.add(
										"audit-events", jsonArray);
								}
								catch (Exception e) {
									_log.error(
										"Unable to populate audit events " +
											"with JSON " + jsonArray,
										e);

									return e;
								}

								return null;
							},
							saveJSONArrayFunction));

					file = _getFile(
						batchInfo.getId(), batchInfo.getJobId(), queryResultId);

					InputStream inputStream = FileUtils.openInputStream(file);

					xmlReader.parse(new InputSource(inputStream));
				}
				finally {
					_throwNewInterruptBotException();

					if (file != null) {
						FileUtils.forceDelete(file);
					}
				}
			}
		}
		finally {
			if (batchInfo != null) {
				_salesforceBulkClientInvoker.closeJob(
					_salesforceExtractorConfiguration, batchInfo.getJobId());
			}
		}

		return exceptions;
	}

	private void _run() throws Exception {
		long time = System.currentTimeMillis();

		JSONObject osbAsahMarkerJSONObject = _getOSBAsahMarkerJSONObject();

		String[] tableNames = _tableNames;

		if (tableNames == null) {
			tableNames = _TABLE_NAMES;
		}

		List<DescribeSObjectResult> describeSObjectResults =
			_salesforcePartnerClientInvoker.getDescribeSObjectResults(
				_salesforceExtractorConfiguration, tableNames);

		_runLogger.log(
			_salesforceExtractorConfiguration.getDataSourceId(), this,
			"STARTED", _elasticsearchInvoker,
			_buildRunLogAdditionalFields(
				describeSObjectResults, osbAsahMarkerJSONObject));

		try {
			for (DescribeSObjectResult describeSObjectResult :
					describeSObjectResults) {

				_throwNewInterruptBotException();

				_populateNewTable(
					describeSObjectResult, osbAsahMarkerJSONObject);
			}

			for (DescribeSObjectResult describeSObjectResult :
					describeSObjectResults) {

				_throwNewInterruptBotException();

				_syncExistingTable(
					describeSObjectResult, osbAsahMarkerJSONObject);
			}

			_throwNewInterruptBotException();

			_deleteStaleTables(osbAsahMarkerJSONObject, tableNames);

			if (_log.isInfoEnabled()) {
				_log.info("Ran in " + TimeUtil.format(time));
			}

			_runLogger.log(
				_salesforceExtractorConfiguration.getDataSourceId(), this,
				"COMPLETED", _elasticsearchInvoker);

			_faroInfoOSBAsahTaskDog.addOSBAsahTask(
				"SalesforceAccountsNanite",
				JSONUtil.put(
					"dataSourceId",
					_salesforceExtractorConfiguration.getDataSourceId()
				).put(
					"type", "audit-events"
				));
		}
		catch (Exception e) {
			_runLogger.log(
				_salesforceExtractorConfiguration.getDataSourceId(), this,
				"FAILED", _elasticsearchInvoker);

			throw e;
		}
	}

	private void _setOSBAsahDataSourceId(JSONObject jsonObject) {
		jsonObject.put(
			"osbAsahDataSourceId",
			_salesforceExtractorConfiguration.getDataSourceId());
	}

	private void _syncExistingTable(
			DescribeSObjectResult describeSObjectResult,
			JSONObject osbAsahMarkerJSONObject)
		throws Exception {

		JSONObject tablesJSONObject = osbAsahMarkerJSONObject.getJSONObject(
			"tables");

		JSONObject tableJSONObject = tablesJSONObject.getJSONObject(
			describeSObjectResult.getName());

		if (tableJSONObject == null) {
			if (_log.isInfoEnabled()) {
				_log.info("Skip syncing " + describeSObjectResult.getName());
			}

			return;
		}

		long lastSuccessfulUpdatedTime = tableJSONObject.getLong(
			"lastSuccessfulUpdatedTime");

		Date endDate = new Date();

		Date startDate = _getStartDate(endDate, lastSuccessfulUpdatedTime);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Sync " + describeSObjectResult.getName() + " since " +
					DateUtil.toUTCString(startDate));
		}

		GetUpdatedResult getUpdatedResult =
			_salesforcePartnerClientInvoker.getGetUpdatedResult(
				_salesforceExtractorConfiguration, endDate, startDate,
				describeSObjectResult.getName());

		String[] salesforceKeys = getUpdatedResult.getIds();

		if (salesforceKeys.length > 0) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"Update " + salesforceKeys.length + " records for " +
						describeSObjectResult.getName());
			}

			int batchSize = SOQLUtil.getBatchSize(
				describeSObjectResult, salesforceKeys);
			int total = salesforceKeys.length;

			int pages = total / batchSize;

			for (int i = 0; i <= pages; i++) {
				int start = i * batchSize;

				int end = start + batchSize;

				if (end > total) {
					end = total;
				}

				String[] subsetSalesforceKeys = ArrayUtil.subset(
					salesforceKeys, start, end);

				List<Exception> exceptions = _processTable(
					describeSObjectResult,
					(JSONArray jsonArray) -> {
						try {
							_elasticsearchInvoker.save(
								describeSObjectResult.getName(), jsonArray);
						}
						catch (Exception e) {
							_log.error(
								"Unable to update " +
									describeSObjectResult.getName() +
										" with JSON " + jsonArray,
								e);

							return e;
						}

						return null;
					},
					subsetSalesforceKeys);

				if (!exceptions.isEmpty()) {
					throw exceptions.get(0);
				}
			}
		}

		Calendar lastSuccessfulUpdatedCalendar =
			getUpdatedResult.getLatestDateCovered();

		tableJSONObject.put(
			"lastSuccessfulUpdatedTime",
			lastSuccessfulUpdatedCalendar.getTimeInMillis());

		tablesJSONObject.put(describeSObjectResult.getName(), tableJSONObject);

		osbAsahMarkerJSONObject.put("tables", tablesJSONObject);

		_elasticsearchInvoker.replace(
			"OSBAsahMarkers", osbAsahMarkerJSONObject);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Updated " + salesforceKeys.length + " records for " +
					describeSObjectResult.getName() + " in " +
						TimeUtil.format(endDate.getTime()));
		}

		long lastSuccessfulDeletedTime = tableJSONObject.optLong(
			"lastSuccessfulDeletedTime");

		startDate = _getStartDate(endDate, lastSuccessfulDeletedTime);

		GetDeletedResult getDeletedResult =
			_salesforcePartnerClientInvoker.getGetDeletedResult(
				_salesforceExtractorConfiguration, endDate, startDate,
				describeSObjectResult.getName());

		DeletedRecord[] deletedRecords = getDeletedResult.getDeletedRecords();

		if (_log.isInfoEnabled()) {
			_log.info(
				"Delete " + deletedRecords.length + " records from " +
					describeSObjectResult.getName());
		}

		int deleteRecordsCount = 0;

		for (DeletedRecord deletedRecord : deletedRecords) {
			JSONObject deletedRecordJSONObject = _elasticsearchInvoker.fetch(
				describeSObjectResult.getName(), deletedRecord.getId());

			if ((deletedRecordJSONObject != null) &&
				_elasticsearchInvoker.delete(
					describeSObjectResult.getName(), deletedRecord.getId())) {

				_addAuditEvent(
					"DELETE", deletedRecordJSONObject, deletedRecord.getId(),
					describeSObjectResult.getName());

				deleteRecordsCount++;
			}
		}

		if (_log.isInfoEnabled()) {
			_log.info(
				"Deleted " + deleteRecordsCount + " records from " +
					describeSObjectResult.getName());
		}

		Calendar lastSuccessfulDeletedCalendar =
			getDeletedResult.getLatestDateCovered();

		tableJSONObject.put(
			"lastSuccessfulDeletedTime",
			lastSuccessfulDeletedCalendar.getTimeInMillis());

		tablesJSONObject.put(describeSObjectResult.getName(), tableJSONObject);

		osbAsahMarkerJSONObject.put("tables", tablesJSONObject);

		_elasticsearchInvoker.replace(
			"OSBAsahMarkers", osbAsahMarkerJSONObject);
	}

	private void _throwNewInterruptBotException() {
		if (_salesforceExtractorConfigurableBot.isStop()) {
			throw new InterruptBotException();
		}
	}

	private JSONObject _toFieldsJSONObject(
		DescribeSObjectResult describeSObjectResult) {

		JSONObject fieldsJSONObject = new JSONObject();

		Map<String, Field> fields = SchemaUtil.getFields(describeSObjectResult);

		for (Map.Entry<String, Field> entry : fields.entrySet()) {
			JSONObject fieldJSONObject = new JSONObject();

			Field field = entry.getValue();

			fieldJSONObject.put("required", !field.isNillable());
			fieldJSONObject.put(
				"type",
				ElasticsearchPropertyUtil.getElasticsearchPropertyType(field));

			fieldsJSONObject.put(field.getName(), fieldJSONObject);
		}

		return fieldsJSONObject;
	}

	private static final String[] _TABLE_NAMES = {"Account", "Contact", "Lead"};

	private static final Log _log = LogFactory.getLog(
		SalesforceExtractorNanite.class);

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	@Autowired
	private FaroInfoOSBAsahTaskDog _faroInfoOSBAsahTaskDog;

	private final TermQueryBuilder _osbAsahDataSourceIdTermQueryBuilder;

	@Autowired
	private RunLogger _runLogger;

	@Autowired
	private SalesforceBulkClientInvoker _salesforceBulkClientInvoker;

	@Autowired
	private SalesforceExtractorConfigurableBot
		_salesforceExtractorConfigurableBot;

	private final SalesforceExtractorConfiguration
		_salesforceExtractorConfiguration;

	@Autowired
	private SalesforcePartnerClientInvoker _salesforcePartnerClientInvoker;

	private final String[] _tableNames;

}