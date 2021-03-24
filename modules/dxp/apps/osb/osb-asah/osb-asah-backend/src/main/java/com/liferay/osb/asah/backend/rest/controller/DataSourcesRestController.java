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

package com.liferay.osb.asah.backend.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.backend.rest.controller.util.FilterUtil;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dto.DataSourceDTO;
import com.liferay.osb.asah.common.dto.PageDTO;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.http.ConfigurationHttp;
import com.liferay.osb.asah.common.http.DataSourceHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.common.run.logger.RunLogger;
import com.liferay.osb.asah.common.salesforce.extractor.dog.SalesforceExtractorConfigurationDog;

import java.util.Date;
import java.util.Objects;
import java.util.function.Supplier;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author Vishal Reddy
 * @author David Bhasme
 * @author Rachael Koestartyo
 */
@RequestMapping("/data-sources")
@RestController(
	"com.liferay.osb.asah.backend.rest.controller.DataSourcesRestController"
)
public class DataSourcesRestController extends BaseRestController {

	@DeleteMapping("/{id}")
	public void deleteDataSource(@PathVariable Long id) throws Exception {
		_salesforceExtractorConfigurationDog.deleteConfiguration(id);

		DataSource dataSource = _dataSourceDog.getDataSource(id);

		dataSource.setDeletionDate(new Date());
		dataSource.setState("IN_PROGRESS_DELETING");

		_asahTaskDog.scheduleAsahTask(
			"DeleteDataSourcesNanite",
			_objectMapper.convertValue(
				_dataSourceDog.updateDataSource(dataSource), JSONObject.class));
	}

	@PostMapping("/{id}/disconnect")
	public String disconnectDataSource(@PathVariable Long id) throws Exception {
		DataSource dataSource = _dataSourceDog.disconnectDataSource(id);

		_sanitize(dataSource);

		JSONObject dataSourceJSONObject = _objectMapper.convertValue(
			dataSource, JSONObject.class);

		return dataSourceJSONObject.toString();
	}

	@GetMapping("/{id}")
	public String getDataSource(@PathVariable Long id) {
		DataSource dataSource = _dataSourceDog.getDataSource(id);

		_setLastSyncTime(dataSource);

		_sanitize(dataSource);

		JSONObject dataSourceJSONObject = _objectMapper.convertValue(
			dataSource, JSONObject.class);

		return dataSourceJSONObject.toString();
	}

	@GetMapping
	public PageDTO<DataSourceDTO> getDataSourceDTOsPageDTO(
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		Page<DataSource> dataSourcesPage = _dataSourceDog.getDataSourcesPage(
			FilterUtil.getLongValues(filterString, "channelId", false),
			FilterUtil.getString(filterString, "credentials/type"),
			FilterUtil.getStringValues(filterString, "name", false),
			FilterUtil.getString(filterString, "provider/type"),
			FilterUtil.getStringValues(filterString, "name", true),
			FilterUtil.getStringValues(filterString, "state", false),
			FilterUtil.getBoolean(filterString, "url"),
			FilterUtil.getBoolean(filterString, "workspaceURL"), page, size,
			sorts);

		return _toPageDTO(
			dataSourcesPage.map(
				dataSource -> {
					_setLastSyncTime(dataSource);

					_sanitize(dataSource);

					return dataSource;
				}));
	}

	@GetMapping("/{id}/progress")
	public String getProgress(@PathVariable Long id) {
		DataSource dataSource = _dataSourceDog.getDataSource(id);

		String providerType = dataSource.getProviderType();

		if (Objects.equals(providerType, "CSV")) {
			return String.valueOf(_getCSVDataSourceProgressJSONObject(id));
		}
		else if (Objects.equals(providerType, "SALESFORCE")) {
			return String.valueOf(
				_getSalesforceDataSourceProgressJSONObject(id));
		}

		throw new UnsupportedOperationException(
			"Unknown data source type " + providerType);
	}

	@GetMapping("/{id}/salesforce-accounts/fields")
	public String getSalesforceAccountsFields(
			@PathVariable String id, @RequestParam(defaultValue = "20") int end,
			@RequestParam(defaultValue = "0") int start)
		throws Exception {

		return _exchange(
			Long.valueOf(id),
			() -> _dataSourceHttp.getSalesforceAccountsFields(id, end, start));
	}

	@GetMapping("/{id}/salesforce-users/fields")
	public String getSalesforceUsersFields(
			@PathVariable String id, @RequestParam(defaultValue = "20") int end,
			@RequestParam(defaultValue = "0") int start)
		throws Exception {

		return _exchange(
			Long.valueOf(id),
			() -> _dataSourceHttp.getSalesforceUsersFields(id, end, start));
	}

	@PatchMapping("/{id}")
	public String patchDataSource(
			@PathVariable String id, @RequestBody DataSourceDTO dataSourceDTO)
		throws Exception {

		dataSourceDTO.setId(id);

		DataSource dataSource = _dataSourceDog.patchDataSource(
			_objectMapper.convertValue(dataSourceDTO, DataSource.class));

		JSONObject dataSourceJSONObject = _objectMapper.convertValue(
			dataSource, JSONObject.class);

		return dataSourceJSONObject.toString();
	}

	@PostMapping
	public String postDataSource(@RequestBody DataSourceDTO dataSourceDTO)
		throws Exception {

		_beforeAdd(dataSourceDTO);
		_removeLastSyncTime(dataSourceDTO);

		DataSource dataSource = _dataSourceDog.addDataSource(
			_objectMapper.convertValue(dataSourceDTO, DataSource.class));

		JSONObject dataSourceJSONObject = _objectMapper.convertValue(
			dataSource, JSONObject.class);

		return dataSourceJSONObject.toString();
	}

	@PutMapping("/{id}")
	public String putDataSource(
			@PathVariable String id, @RequestBody DataSourceDTO dataSourceDTO)
		throws Exception {

		dataSourceDTO.setId(id);

		_beforeUpdate(dataSourceDTO);

		_removeLastSyncTime(dataSourceDTO);

		DataSource dataSource = _dataSourceDog.updateDataSourceConfiguration(
			_objectMapper.convertValue(dataSourceDTO, DataSource.class));

		JSONObject dataSourceJSONObject = _objectMapper.convertValue(
			dataSource, JSONObject.class);

		return dataSourceJSONObject.toString();
	}

	private void _beforeAdd(DataSourceDTO dataSourceDTO) {
		Date now = new Date();

		dataSourceDTO.setCreateDate(now);

		dataSourceDTO.setId(null);

		dataSourceDTO.setModifiedDate(now);
	}

	private void _beforeUpdate(DataSourceDTO dataSourceDTO) {
		dataSourceDTO.setModifiedDate(new Date());
	}

	private String _exchange(
			Long dataSourceId, Supplier<ResponseEntity<String>> supplier)
		throws Exception {

		DataSource dataSource = _dataSourceDog.getDataSource(dataSourceId);

		try {
			ResponseEntity<String> responseEntity = supplier.get();

			if (responseEntity.getStatusCode() != HttpStatus.OK) {
				_refreshConfiguration(dataSource);
			}

			return responseEntity.getBody();
		}
		catch (Exception e) {
			_refreshConfiguration(dataSource);

			if (e instanceof HttpClientErrorException) {
				HttpClientErrorException hcee = (HttpClientErrorException)e;

				if (hcee.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
					throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
				}

				throw new HttpClientErrorException(hcee.getStatusCode());
			}

			throw e;
		}
	}

	private JSONObject _getCSVDataSourceProgressJSONObject(Long dataSourceId) {
		JSONObject runLogJSONObject = _runLogger.fetchLatestRunLogJSONObject(
			dataSourceId, faroInfoElasticsearchInvoker, "CSVIndividualsNanite");

		if (runLogJSONObject == null) {
			return new JSONObject();
		}

		String status = runLogJSONObject.getString("status");

		if (status.equals("STARTED")) {
			return JSONUtil.put(
				"dateRecorded", DateUtil.newDateString()
			).put(
				"processedOperations",
				runLogJSONObject.getInt("processedOperations")
			).put(
				"status", "IN_PROGRESS"
			).put(
				"totalOperations",
				faroInfoElasticsearchInvoker.count(
					"csv-individuals",
					QueryBuilders.termQuery(
						"dataSourceId", String.valueOf(dataSourceId)))
			);
		}

		return JSONUtil.put(
			"dateRecorded", runLogJSONObject.getString("dateLogged")
		).put(
			"status", status
		);
	}

	private JSONObject _getSalesforceDataSourceAccountsProgressJSONObject(
		Long dataSourceId) {

		JSONObject salesforceExtractorNaniteRunLogJSONObject =
			_runLogger.fetchLatestRunLogJSONObject(
				dataSourceId, salesforceRawElasticsearchInvoker,
				"SalesforceExtractorNanite");

		if (salesforceExtractorNaniteRunLogJSONObject == null) {
			return new JSONObject();
		}

		String salesforceExtractorNaniteStatus =
			salesforceExtractorNaniteRunLogJSONObject.getString("status");

		if (salesforceExtractorNaniteStatus.equals("FAILED")) {
			return JSONUtil.put(
				"dateRecorded",
				salesforceExtractorNaniteRunLogJSONObject.getString(
					"dateLogged")
			).put(
				"status", "FAILED"
			);
		}
		else if (salesforceExtractorNaniteStatus.equals("STARTED")) {
			return _getSalesforceExtractorNaniteProgressJSONObject(
				salesforceExtractorNaniteRunLogJSONObject, 2, "Account");
		}

		JSONObject salesforceAccountsNaniteRunLogJSONObject =
			_runLogger.fetchLatestRunLogJSONObject(
				dataSourceId, faroInfoElasticsearchInvoker,
				"SalesforceAccountsNanite");

		if (salesforceAccountsNaniteRunLogJSONObject == null) {
			return JSONUtil.put(
				"dateRecorded", DateUtil.newDateString()
			).put(
				"processedOperations", 1
			).put(
				"status", "IN_PROGRESS"
			).put(
				"totalOperations", 2
			);
		}

		String salesforceAccountsNaniteStatus =
			salesforceAccountsNaniteRunLogJSONObject.getString("status");

		if (salesforceAccountsNaniteStatus.equals("STARTED")) {
			int totalOperations =
				2 *
					salesforceAccountsNaniteRunLogJSONObject.getInt(
						"totalOperations");

			return JSONUtil.put(
				"dateRecorded", DateUtil.newDateString()
			).put(
				"processedOperations",
				totalOperations -
					salesforceRawElasticsearchInvoker.count(
						"audit-events",
						BoolQueryBuilderUtil.filter(
							QueryBuilders.termQuery(
								"osbAsahDataSourceId", dataSourceId)
						).filter(
							QueryBuilders.termQuery("typeName", "Account")
						))
			).put(
				"status", "IN_PROGRESS"
			).put(
				"totalOperations", totalOperations
			);
		}

		String salesforceExtractorNaniteCompletedDateString =
			salesforceExtractorNaniteRunLogJSONObject.getString("dateLogged");
		String salesforceAccountsNaniteEndDateString =
			salesforceAccountsNaniteRunLogJSONObject.getString("dateLogged");

		if (salesforceAccountsNaniteEndDateString.compareTo(
				salesforceExtractorNaniteCompletedDateString) <= 0) {

			return JSONUtil.put(
				"dateRecorded", DateUtil.newDateString()
			).put(
				"processedOperations", 1
			).put(
				"status", "IN_PROGRESS"
			).put(
				"totalOperations", 2
			);
		}

		return JSONUtil.put(
			"dateRecorded",
			salesforceAccountsNaniteRunLogJSONObject.getString("dateLogged")
		).put(
			"status", salesforceAccountsNaniteStatus
		);
	}

	private JSONObject _getSalesforceDataSourceIndividualsProgressJSONObject(
		Long dataSourceId) {

		JSONObject salesforceExtractorNaniteRunLogJSONObject =
			_runLogger.fetchLatestRunLogJSONObject(
				dataSourceId, salesforceRawElasticsearchInvoker,
				"SalesforceExtractorNanite");

		if (salesforceExtractorNaniteRunLogJSONObject == null) {
			return new JSONObject();
		}

		String salesforceExtractorNaniteStatus =
			salesforceExtractorNaniteRunLogJSONObject.getString("status");

		if (salesforceExtractorNaniteStatus.equals("FAILED")) {
			return JSONUtil.put(
				"dateRecorded",
				salesforceExtractorNaniteRunLogJSONObject.getString(
					"dateLogged")
			).put(
				"status", "FAILED"
			);
		}
		else if (salesforceExtractorNaniteStatus.equals("STARTED")) {
			return _getSalesforceExtractorNaniteProgressJSONObject(
				salesforceExtractorNaniteRunLogJSONObject, 3, "Contact",
				"Lead");
		}

		JSONObject salesforceExtractorIndividualsNaniteRunLogJSONObject =
			_runLogger.fetchLatestRunLogJSONObject(
				dataSourceId, salesforceRawElasticsearchInvoker,
				"SalesforceExtractorIndividualsNanite");

		if (salesforceExtractorIndividualsNaniteRunLogJSONObject == null) {
			return JSONUtil.put(
				"dateRecorded", DateUtil.newDateString()
			).put(
				"processedOperations", 1
			).put(
				"status", "IN_PROGRESS"
			).put(
				"totalOperations", 3
			);
		}

		String salesforceExtractorIndividualsNaniteStatus =
			salesforceExtractorIndividualsNaniteRunLogJSONObject.getString(
				"status");

		if (salesforceExtractorIndividualsNaniteStatus.equals("STARTED")) {
			int totalOperations =
				salesforceExtractorIndividualsNaniteRunLogJSONObject.getInt(
					"totalOperations");

			return JSONUtil.put(
				"dateRecorded", DateUtil.newDateString()
			).put(
				"processedOperations",
				(totalOperations * 2) -
					salesforceRawElasticsearchInvoker.count(
						"audit-events",
						BoolQueryBuilderUtil.filter(
							QueryBuilders.termQuery(
								"osbAsahDataSourceId", dataSourceId)
						).filter(
							QueryBuilders.termsQuery(
								"typeName", "Contact", "Lead")
						))
			).put(
				"status", "IN_PROGRESS"
			).put(
				"totalOperations", 3 * totalOperations
			);
		}

		String salesforceExtractorNaniteCompletedDateString =
			salesforceExtractorNaniteRunLogJSONObject.getString("dateLogged");
		String salesforceExtractorIndividualsNaniteEndDateString =
			salesforceExtractorIndividualsNaniteRunLogJSONObject.getString(
				"dateLogged");

		if (salesforceExtractorIndividualsNaniteEndDateString.compareTo(
				salesforceExtractorNaniteCompletedDateString) <= 0) {

			return JSONUtil.put(
				"dateRecorded", DateUtil.newDateString()
			).put(
				"processedOperations", 1
			).put(
				"status", "IN_PROGRESS"
			).put(
				"totalOperations", 3
			);
		}

		if (salesforceExtractorIndividualsNaniteStatus.equals("FAILED")) {
			return JSONUtil.put(
				"dateRecorded",
				salesforceExtractorIndividualsNaniteRunLogJSONObject.getString(
					"dateLogged")
			).put(
				"status", "FAILED"
			);
		}

		JSONObject salesforceIndividualsNaniteRunLogJSONObject =
			_runLogger.fetchLatestRunLogJSONObject(
				dataSourceId, faroInfoElasticsearchInvoker,
				"SalesforceIndividualsNanite");

		if (salesforceIndividualsNaniteRunLogJSONObject == null) {
			return JSONUtil.put(
				"dateRecorded", DateUtil.newDateString()
			).put(
				"processedOperations", 2
			).put(
				"status", "IN_PROGRESS"
			).put(
				"totalOperations", 3
			);
		}

		String salesforceIndividualsNaniteStatus =
			salesforceIndividualsNaniteRunLogJSONObject.getString("status");

		if (salesforceIndividualsNaniteStatus.equals("STARTED")) {
			int totalOperations =
				3 *
					salesforceIndividualsNaniteRunLogJSONObject.getInt(
						"totalOperations");

			return JSONUtil.put(
				"dateRecorded", DateUtil.newDateString()
			).put(
				"processedOperations",
				totalOperations -
					salesforceRawElasticsearchInvoker.count(
						"audit-events",
						BoolQueryBuilderUtil.filter(
							QueryBuilders.termQuery(
								"osbAsahDataSourceId", dataSourceId)
						).filter(
							QueryBuilders.termQuery("typeName", "individuals")
						))
			).put(
				"status", "IN_PROGRESS"
			).put(
				"totalOperations", totalOperations
			);
		}

		String salesforceExtractorIndividualsNaniteCompletedDateString =
			salesforceExtractorIndividualsNaniteRunLogJSONObject.getString(
				"dateLogged");
		String salesforceIndividualsNaniteEndDateString =
			salesforceIndividualsNaniteRunLogJSONObject.getString("dateLogged");

		if (salesforceIndividualsNaniteEndDateString.compareTo(
				salesforceExtractorIndividualsNaniteCompletedDateString) <= 0) {

			return JSONUtil.put(
				"dateRecorded", DateUtil.newDateString()
			).put(
				"processedOperations", 2
			).put(
				"status", "IN_PROGRESS"
			).put(
				"totalOperations", 3
			);
		}

		return JSONUtil.put(
			"dateRecorded",
			salesforceIndividualsNaniteRunLogJSONObject.getString("dateLogged")
		).put(
			"status", salesforceIndividualsNaniteStatus
		);
	}

	private JSONObject _getSalesforceDataSourceProgressJSONObject(
		Long dataSourceId) {

		DataSource dataSource = _dataSourceDog.getDataSource(dataSourceId);

		JSONObject progressJSONObject = new JSONObject();

		if (dataSource.getEnableAllAccounts()) {
			progressJSONObject.put(
				"accounts",
				_getSalesforceDataSourceAccountsProgressJSONObject(
					dataSourceId));
		}

		if (dataSource.getEnableAllContacts() ||
			dataSource.getEnableAllLeads()) {

			progressJSONObject.put(
				"individuals",
				_getSalesforceDataSourceIndividualsProgressJSONObject(
					dataSourceId));
		}

		return progressJSONObject;
	}

	private JSONObject _getSalesforceExtractorNaniteProgressJSONObject(
		JSONObject salesforceExtractorNaniteRunLogJSONObject,
		int totalOperationsMultiplier, String... tableNames) {

		int processedOperations = 0;
		int totalOperations = 0;

		for (String tableName : tableNames) {
			int totalTableNameOperations =
				salesforceExtractorNaniteRunLogJSONObject.optInt(
					"total" + tableName + "Operations");

			if (totalTableNameOperations == 0) {
				continue;
			}

			totalOperations += totalTableNameOperations;

			if (salesforceExtractorNaniteRunLogJSONObject.getBoolean(
					"initial" + tableName + "Run")) {

				processedOperations += salesforceRawElasticsearchInvoker.count(
					tableName,
					QueryBuilders.termQuery(
						"osbAsahDataSourceId",
						salesforceExtractorNaniteRunLogJSONObject.getString(
							"dataSourceId")));
			}
			else {
				processedOperations += salesforceRawElasticsearchInvoker.count(
					"audit-events",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery(
							"osbAsahDataSourceId",
							salesforceExtractorNaniteRunLogJSONObject.getString(
								"dataSourceId"))
					).filter(
						QueryBuilders.termQuery("typeName", tableName)
					));
			}
		}

		return JSONUtil.put(
			"dateRecorded", DateUtil.newDateString()
		).put(
			"processedOperations", processedOperations
		).put(
			"status", "IN_PROGRESS"
		).put(
			"totalOperations", totalOperations * totalOperationsMultiplier
		);
	}

	private void _refreshConfiguration(DataSource dataSource) throws Exception {
		String providerType = dataSource.getProviderType();

		if (!Objects.equals(providerType, "SALESFORCE")) {
			return;
		}

		DataSource newDataSource = _configurationHttp.refreshConfiguration(
			dataSource, providerType);

		if (Objects.equals(dataSource, newDataSource)) {
			return;
		}

		_salesforceExtractorConfigurationDog.updateConfiguration(newDataSource);

		_dataSourceDog.updateDataSourceConfiguration(newDataSource);
	}

	private void _removeLastSyncTime(DataSourceDTO dataSourceDTO) {
		DataSourceDTO.ProviderDTO providerDTO = dataSourceDTO.getProviderDTO();

		DataSourceDTO.ProviderDTO.AnalyticsConfigurationDTO
			analyticsConfigurationDTO =
				providerDTO.getAnalyticsConfigurationDTO();

		if (analyticsConfigurationDTO != null) {
			analyticsConfigurationDTO.setLastSyncDate(null);
		}

		DataSourceDTO.ProviderDTO.ContactsConfigurationDTO
			contactsConfigurationDTO =
				providerDTO.getContactsConfigurationDTO();

		if (contactsConfigurationDTO != null) {
			contactsConfigurationDTO.setLastSuccessfulAuditEventDate(null);
			contactsConfigurationDTO.setLastSyncDate(null);
		}
	}

	private void _sanitize(DataSource dataSource) {
		dataSource.setFaroBackendSecuritySignature(null);
		dataSource.setPrivateKey(null);
	}

	private void _setLastSyncTime(DataSource dataSource) {
		if (!Objects.equals(dataSource.getProviderType(), "LIFERAY")) {
			return;
		}

		Long dataSourceId = dataSource.getId();

		Date analyticsLastSyncDate = dataSource.getAnalyticsLastSyncDate();

		if (analyticsLastSyncDate != null) {
			JSONArray activitiesJSONArray = new JSONArray(
				faroInfoElasticsearchInvoker.get(
					"activities",
					searchSourceBuilder -> {
						BoolQueryBuilder boolQueryBuilder =
							BoolQueryBuilderUtil.filter(
								QueryBuilders.termQuery(
									"dataSourceId",
									String.valueOf(dataSourceId))
							).filter(
								QueryBuilders.rangeQuery(
									"endTime"
								).gt(
									DateUtil.toUTCString(analyticsLastSyncDate)
								)
							);

						searchSourceBuilder.query(boolQueryBuilder);

						searchSourceBuilder.size(1);
						searchSourceBuilder.sort(
							SortBuilderUtil.fieldSort(
								"endTime", SortOrder.DESC));
					}));

			if (activitiesJSONArray.length() > 0) {
				JSONObject activityJSONObject =
					activitiesJSONArray.getJSONObject(0);

				try {
					dataSource.setAnalyticsLastSyncDate(
						DateUtil.toUTCDate(
							activityJSONObject.getString("endTime")));

					_dataSourceDog.updateDataSource(dataSource);
				}
				catch (Exception exception) {
					_log.error(exception);
				}
			}
		}

		Date contactsLastSyncDate = dataSource.getContactsLastSyncDate();

		if (contactsLastSyncDate == null) {
			return;
		}

		JSONObject dxpRawOSBAsahMarkersJSONObject =
			dxpRawElasticsearchInvoker.fetch(
				"OSBAsahMarkers",
				QueryBuilders.termQuery(
					"osbAsahDataSourceId", String.valueOf(dataSourceId)));

		if (dxpRawOSBAsahMarkersJSONObject == null) {
			return;
		}

		long lastSyncTime = dxpRawOSBAsahMarkersJSONObject.optLong(
			"lastSyncTime");

		if (lastSyncTime > 0) {
			dataSource.setContactsLastSyncDate(new Date(lastSyncTime));
		}

		JSONObject lastSuccessfulAuditEventJSONObject =
			dxpRawOSBAsahMarkersJSONObject.optJSONObject(
				"lastSuccessfulAuditEvent");

		if (lastSuccessfulAuditEventJSONObject == null) {
			return;
		}

		long createDate = lastSuccessfulAuditEventJSONObject.optLong(
			"createDate");

		if (createDate > 0) {
			dataSource.setContactsLastSuccessfulAuditEventDate(
				new Date(createDate));
		}
	}

	private PageDTO<DataSourceDTO> _toPageDTO(
		Page<DataSource> dataSourcesPage) {

		return new PageDTO<>(
			"_embedded", new DataSourceDTO(dataSourcesPage.getContent()),
			dataSourcesPage.getNumber(), dataSourcesPage.getSize(),
			dataSourcesPage.getTotalElements(),
			dataSourcesPage.getTotalPages());
	}

	private static final Log _log = LogFactory.getLog(
		DataSourcesRestController.class);

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private ConfigurationHttp _configurationHttp;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private DataSourceHttp _dataSourceHttp;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private RunLogger _runLogger;

	@Autowired
	private SalesforceExtractorConfigurationDog
		_salesforceExtractorConfigurationDog;

}