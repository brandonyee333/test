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

package com.liferay.osb.asah.batch.curator.bot.nanite;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter.DXPEntityDataExporter;
import com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter.DataExporter;
import com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter.RawDataExporter;
import com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter.SalesforceEntityDataExporter;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.BQUserDog;
import com.liferay.osb.asah.common.dog.CSVIndividualDog;
import com.liferay.osb.asah.common.dog.DataControlTaskDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.dog.SalesforceEntityDog;
import com.liferay.osb.asah.common.dog.SuppressionDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.DataControlTask;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.SalesforceEntity;
import com.liferay.osb.asah.common.http.EmailHttp;
import com.liferay.osb.asah.common.model.DataControlTaskStatus;
import com.liferay.osb.asah.common.model.DataControlTaskType;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.common.zip.ZipFileBuilder;

import java.io.File;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class DataControlNanite extends BaseNanite {

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		List<DataControlTask> pendingDataControlTasks =
			_dataControlTaskDog.getDataControlTasks(
				null, Arrays.asList(DataControlTaskStatus.PENDING.toString()),
				null);

		Stream<DataControlTask> pendingDataControlTasksStream =
			pendingDataControlTasks.stream();

		pendingDataControlTasksStream.forEach(this::_runDataControlTask);

		List<DataControlTask> completedDataControlTasks =
			_dataControlTaskDog.getDataControlTasks(
				DateUtil.addDays(DateUtil.newDate(), -30),
				Arrays.asList(DataControlTaskStatus.COMPLETED.toString()),
				Arrays.asList(DataControlTaskType.ACCESS.toString()));

		Stream<DataControlTask> completedDataControlTasksStream =
			completedDataControlTasks.stream();

		completedDataControlTasksStream.forEach(this::_expireDataControlTask);
	}

	@Override
	protected Log getLog() {
		return LogFactory.getLog(DataControlNanite.class);
	}

	private void _addSuppression(
		DataControlTask dataControlTask, String emailAddress) {

		emailAddress = StringUtils.lowerCase(emailAddress);

		if (_suppressionDog.isSuppressed(emailAddress, null)) {
			return;
		}

		_suppressionDog.addSuppression(
			dataControlTask.getBatchId(), dataControlTask.getCreateDate(),
			DataControlTaskStatus.COMPLETED.toString(), emailAddress);
	}

	private QueryBuilder _buildIndividualQueryBuilder(
		String dataSourceType, Individual individual,
		String individualPKFieldName) {

		BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

		for (Individual.DataSourceIndividualPK dataSourceIndividualPK :
				individual.getDataSourceIndividualPKs()) {

			DataSource dataSource = _dataSourceDog.getDataSource(
				dataSourceIndividualPK.getDataSourceId());

			if (!StringUtils.equals(
					dataSource.getProviderType(), dataSourceType)) {

				continue;
			}

			for (String individualPK :
					dataSourceIndividualPK.getIndividualPKs()) {

				boolQueryBuilder.should(
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termsQuery(
							"dataSourceId", String.valueOf(dataSource.getId()))
					).filter(
						QueryBuilders.termsQuery(
							individualPKFieldName, individualPK)
					));
			}
		}

		if (boolQueryBuilder.hasClauses()) {
			return boolQueryBuilder;
		}

		return null;
	}

	private void _deleteData(String emailAddress) {
		_salesforceEntityDog.deleteSalesforceEntities(
			"email", emailAddress, SalesforceEntity.Type.INDIVIDUAL);

		Individual individual = _individualDog.fetchIndividualByEmailAddress(
			emailAddress);

		if (individual == null) {
			return;
		}

		Map<Long, List<String>> dataSourceIdIndividualsPKs =
			_getDataSourceIdIndividualsPKs("CSV", individual);

		if (!dataSourceIdIndividualsPKs.isEmpty()) {
			for (Map.Entry<Long, List<String>> entry :
					dataSourceIdIndividualsPKs.entrySet()) {

				_csvIndividualDog.deleteCSVIndividuals(
					entry.getKey(), entry.getValue());
			}
		}

		_individualDog.removeDemographics(individual);
	}

	private void _deleteSuppression(String emailAddress) {
		_suppressionDog.deleteByEmailAddress(emailAddress);
	}

	private void _expireDataControlTask(DataControlTask dataControlTask) {
		try {
			Path zipFilePath = Paths.get(
				_exportPathName, dataControlTask.getId() + ".zip");

			File file = zipFilePath.toFile();

			if (file.exists() && !file.delete()) {
				_log.error("Unable to delete file " + file.getAbsolutePath());
			}

			_updateDataControlTaskStatus(
				dataControlTask, DataControlTaskStatus.EXPIRED);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	private void _exportData(
			DataControlTask dataControlTask, String emailAddress)
		throws Exception {

		ZipFileBuilder zipFileBuilder = new ZipFileBuilder(
			_exportPathName + "/" + dataControlTask.getId() + ".zip");

		zipFileBuilder.addToZip(
			"dxp_users.json",
			zipOutputStream -> _writeUsersToZip(
				"emailAddress", emailAddress, zipOutputStream));
		zipFileBuilder.addToZip(
			"individuals.json",
			zipOutputStream -> _writeToZip(
				"individuals", faroInfoElasticsearchInvoker,
				QueryBuilders.termQuery(
					"demographics.email.value", emailAddress),
				zipOutputStream));
		zipFileBuilder.addToZip(
			"salesforce-individuals.json",
			zipOutputStream -> _writeToZip(
				"email", emailAddress, SalesforceEntity.Type.INDIVIDUAL,
				zipOutputStream));

		Individual individual = _individualDog.fetchIndividualByEmailAddress(
			emailAddress);

		if (individual == null) {
			_exportDataControlTask(dataControlTask, zipFileBuilder);

			return;
		}

		QueryBuilder csvIndividualQueryBuilder = _buildIndividualQueryBuilder(
			"CSV", individual, "dataSourceIndividualPK");

		if (csvIndividualQueryBuilder != null) {
			zipFileBuilder.addToZip(
				"csv-individuals.json",
				zipOutputStream -> _writeToZip(
					"csv-individuals", faroInfoElasticsearchInvoker,
					_buildIndividualQueryBuilder(
						"CSV", individual, "dataSourceIndividualPK"),
					zipOutputStream));
		}

		QueryBuilder individualIdQueryBuilder = QueryBuilders.termQuery(
			"individualId", String.valueOf(individual.getId()));

		zipFileBuilder.addToZip(
			"blogs.json",
			zipOutputStream -> _writeToZip(
				"blogs", _cerebroInfoElasticsearchInvoker,
				individualIdQueryBuilder, zipOutputStream));
		zipFileBuilder.addToZip(
			"document-libraries.json",
			zipOutputStream -> _writeToZip(
				"document-libraries", _cerebroInfoElasticsearchInvoker,
				individualIdQueryBuilder, zipOutputStream));
		zipFileBuilder.addToZip(
			"forms.json",
			zipOutputStream -> _writeToZip(
				"forms", _cerebroInfoElasticsearchInvoker,
				individualIdQueryBuilder, zipOutputStream));
		zipFileBuilder.addToZip(
			"journals.json",
			zipOutputStream -> _writeToZip(
				"journals", _cerebroInfoElasticsearchInvoker,
				individualIdQueryBuilder, zipOutputStream));
		zipFileBuilder.addToZip(
			"page-referrers.json",
			zipOutputStream -> _writeToZip(
				"page-referrers", _cerebroInfoElasticsearchInvoker,
				individualIdQueryBuilder, zipOutputStream));
		zipFileBuilder.addToZip(
			"pages.json",
			zipOutputStream -> _writeToZip(
				"pages", _cerebroInfoElasticsearchInvoker,
				individualIdQueryBuilder, zipOutputStream));
		zipFileBuilder.addToZip(
			"user-sessions.json",
			zipOutputStream -> _writeToZip(
				"user-sessions", _cerebroInfoElasticsearchInvoker,
				individualIdQueryBuilder, zipOutputStream));

		_exportDataControlTask(dataControlTask, zipFileBuilder);
	}

	private void _exportDataControlTask(
			DataControlTask dataControlTask, ZipFileBuilder zipFileBuilder)
		throws Exception {

		zipFileBuilder.addToZip(
			"data-control-tasks.json",
			zipOutputStream -> {
				_updateDataControlTaskStatus(
					dataControlTask, DataControlTaskStatus.COMPLETED);

				JSONObject dataControlTaskJSONObject =
					_objectMapper.convertValue(
						dataControlTask, JSONObject.class);

				String dataControlTaskJSON = dataControlTaskJSONObject.toString(
					2);

				zipOutputStream.write(
					dataControlTaskJSON.getBytes(StandardCharsets.UTF_8));
			});

		zipFileBuilder.build();
	}

	private Map<Long, List<String>> _getDataSourceIdIndividualsPKs(
		String dataSourceType, Individual individual) {

		Map<Long, List<String>> dataSourceIdIndividualPKs = new HashMap<>();

		for (Individual.DataSourceIndividualPK dataSourceIndividualPK :
				individual.getDataSourceIndividualPKs()) {

			DataSource dataSource = _dataSourceDog.getDataSource(
				dataSourceIndividualPK.getDataSourceId());

			if (!StringUtils.equals(
					dataSource.getProviderType(), dataSourceType)) {

				continue;
			}

			List<String> individualPKs =
				dataSourceIdIndividualPKs.computeIfAbsent(
					dataSource.getId(), id -> new ArrayList<>());

			individualPKs.addAll(dataSourceIndividualPK.getIndividualPKs());
		}

		return dataSourceIdIndividualPKs;
	}

	private void _runDataControlTask(DataControlTask dataControlTask) {
		try {
			_updateDataControlTaskStatus(
				dataControlTask, DataControlTaskStatus.RUNNING);

			String emailAddress = dataControlTask.getEmailAddress();
			String type = dataControlTask.getType();

			try {
				if (StringUtils.equals(
						type, DataControlTaskType.ACCESS.toString())) {

					_exportData(dataControlTask, emailAddress);
				}
				else if (StringUtils.equals(
							type, DataControlTaskType.DELETE.toString())) {

					_deleteData(emailAddress);
				}
				else if (StringUtils.equals(
							type, DataControlTaskType.SUPPRESS.toString())) {

					_addSuppression(dataControlTask, emailAddress);
				}
				else if (StringUtils.equals(
							type, DataControlTaskType.UNSUPPRESS.toString())) {

					_deleteSuppression(emailAddress);
				}

				if (!StringUtils.equals(
						type, DataControlTaskType.ACCESS.toString())) {

					_exportDataControlTask(
						dataControlTask,
						new ZipFileBuilder(
							_exportPathName + "/" + dataControlTask.getId() +
								".zip"));
				}
			}
			catch (Exception exception) {
				_log.error(exception, exception);

				_updateDataControlTaskStatus(
					dataControlTask, DataControlTaskStatus.ERROR);
			}

			_sendEmail(dataControlTask);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	private void _sendEmail(DataControlTask dataControlTask) {
		if (!_dataControlTaskDog.existsDataControlTask(
				dataControlTask.getBatchId(),
				Arrays.asList(
					DataControlTaskStatus.PENDING.toString(),
					DataControlTaskStatus.RUNNING.toString()))) {

			_emailHttp.sendEmail(
				_objectMapper.convertValue(dataControlTask, JSONObject.class));
		}
	}

	private void _updateDataControlTaskStatus(
		DataControlTask dataControlTask,
		DataControlTaskStatus dataControlTaskStatus) {

		if (dataControlTaskStatus == DataControlTaskStatus.COMPLETED) {
			dataControlTask.setCompleteDate(DateUtil.newDate());
		}
		else if (dataControlTaskStatus == DataControlTaskStatus.RUNNING) {
			dataControlTask.setStartDate(DateUtil.newDate());
		}

		dataControlTask.setStatus(dataControlTaskStatus.toString());

		_dataControlTaskDog.updateDataControlTask(dataControlTask);
	}

	private void _writeToZip(
			String collectionName, ElasticsearchInvoker elasticsearchInvoker,
			QueryBuilder queryBuilder, ZipOutputStream zipOutputStream)
		throws Exception {

		DataExporter dataExporter = new RawDataExporter(
			collectionName, elasticsearchInvoker, _jsonFactory, zipOutputStream,
			queryBuilder);

		dataExporter.export();
	}

	private void _writeToZip(
			String fieldName, String fieldValue, SalesforceEntity.Type type,
			ZipOutputStream zipOutputStream)
		throws Exception {

		DataExporter dataExporter = new SalesforceEntityDataExporter(
			fieldName, fieldValue, _jsonFactory, _objectMapper, zipOutputStream,
			_salesforceEntityDog, type);

		dataExporter.export();
	}

	private void _writeUsersToZip(
			String fieldName, String fieldValue,
			ZipOutputStream zipOutputStream)
		throws Exception {

		DataExporter dataExporter = new DXPEntityDataExporter(
			_bqUserDog, fieldName, fieldValue, _jsonFactory, _objectMapper,
			zipOutputStream);

		dataExporter.export();
	}

	private static final Log _log = LogFactory.getLog(DataControlNanite.class);

	@Autowired
	private BQUserDog _bqUserDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private CSVIndividualDog _csvIndividualDog;

	@Autowired
	private DataControlTaskDog _dataControlTaskDog;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private EmailHttp _emailHttp;

	@Value("${osb.asah.batch.curator.data.export.path:/export}")
	private String _exportPathName;

	@Autowired
	private IndividualDog _individualDog;

	private final JsonFactory _jsonFactory = new JsonFactory() {
		{
			disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
		}
	};

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SalesforceEntityDog _salesforceEntityDog;

	@Autowired
	private SuppressionDog _suppressionDog;

}