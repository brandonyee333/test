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

import com.liferay.osb.asah.backend.dto.DataSourceDTO;
import com.liferay.osb.asah.backend.dto.PageDTO;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.dog.CSVIndividualDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.RunLogDog;
import com.liferay.osb.asah.common.dog.SalesforceAuditEventDog;
import com.liferay.osb.asah.common.dog.SalesforceEntityDog;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.RunLog;
import com.liferay.osb.asah.common.entity.SalesforceEntity;
import com.liferay.osb.asah.common.http.ConfigurationHttp;
import com.liferay.osb.asah.common.http.DataSourceHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.salesforce.extractor.dog.SalesforceExtractorConfigurationDog;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Date;
import java.util.Objects;
import java.util.function.Supplier;

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
	public void deleteDataSource(@PathVariable Long id) {
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

		_sanitize(dataSource);

		JSONObject dataSourceJSONObject = _objectMapper.convertValue(
			dataSource, JSONObject.class);

		return dataSourceJSONObject.toString();
	}

	@GetMapping
	public PageDTO<DataSourceDTO> getDataSourceDTOsPageDTO(
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size,
		@RequestParam(name = "sort", required = false) String[] sorts) {

		Page<DataSource> dataSourcesPage = _dataSourceDog.getDataSourcesPage(
			filterString, page, size, sorts);

		return _toPageDTO(
			dataSourcesPage.map(
				dataSource -> {
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

		if (Objects.equals(providerType, "SALESFORCE")) {
			return String.valueOf(
				_getSalesforceDataSourceProgressJSONObject(id));
		}

		throw new UnsupportedOperationException(
			"Unknown data source type " + providerType);
	}

	@GetMapping("/{id}/salesforce-accounts/fields")
	public String getSalesforceAccountsFields(
		@PathVariable Long id, @RequestParam(defaultValue = "20") int end,
		@RequestParam(defaultValue = "0") int start) {

		return _exchange(
			id,
			() -> _dataSourceHttp.getSalesforceAccountsFields(id, end, start));
	}

	@GetMapping("/{id}/salesforce-users/fields")
	public String getSalesforceUsersFields(
		@PathVariable Long id, @RequestParam(defaultValue = "20") int end,
		@RequestParam(defaultValue = "0") int start) {

		return _exchange(
			id, () -> _dataSourceHttp.getSalesforceUsersFields(id, end, start));
	}

	@PatchMapping("/{id}")
	public String patchDataSource(
		@PathVariable String id, @RequestBody DataSourceDTO dataSourceDTO) {

		dataSourceDTO.setId(id);

		DataSource dataSource = _dataSourceDog.patchDataSource(
			_objectMapper.convertValue(dataSourceDTO, DataSource.class));

		JSONObject dataSourceJSONObject = _objectMapper.convertValue(
			dataSource, JSONObject.class);

		return dataSourceJSONObject.toString();
	}

	@PostMapping
	public String postDataSource(@RequestBody DataSourceDTO dataSourceDTO) {
		_beforeAdd(dataSourceDTO);

		DataSource dataSource = _dataSourceDog.addDataSource(
			_objectMapper.convertValue(dataSourceDTO, DataSource.class));

		JSONObject dataSourceJSONObject = _objectMapper.convertValue(
			dataSource, JSONObject.class);

		return dataSourceJSONObject.toString();
	}

	@PutMapping("/{id}")
	public String putDataSource(
		@PathVariable String id, @RequestBody DataSourceDTO dataSourceDTO) {

		dataSourceDTO.setId(id);

		_beforeUpdate(dataSourceDTO);

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
		Long dataSourceId, Supplier<ResponseEntity<String>> supplier) {

		DataSource dataSource = _dataSourceDog.getDataSource(dataSourceId);

		try {
			ResponseEntity<String> responseEntity = supplier.get();

			if (responseEntity.getStatusCode() != HttpStatus.OK) {
				_refreshConfiguration(dataSource);
			}

			return responseEntity.getBody();
		}
		catch (Exception exception) {
			_refreshConfiguration(dataSource);

			if (exception instanceof HttpClientErrorException) {
				HttpClientErrorException httpClientErrorException =
					(HttpClientErrorException)exception;

				if (httpClientErrorException.getStatusCode() ==
						HttpStatus.INTERNAL_SERVER_ERROR) {

					throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
				}

				throw new HttpClientErrorException(
					httpClientErrorException.getStatusCode());
			}

			throw exception;
		}
	}

	private JSONObject _getCSVDataSourceProgressJSONObject(Long dataSourceId) {
		RunLog runLog = _runLogDog.fetchLatestRunLog(
			dataSourceId, "CSVIndividualsNanite", null,
			WeDeployDataService.OSB_ASAH_FARO_INFO);

		if (runLog == null) {
			return new JSONObject();
		}

		String status = runLog.getStatus();

		if (status.equals("STARTED")) {
			JSONObject runLogContextJSONObject = runLog.getContextJSONObject();

			return JSONUtil.put(
				"dateRecorded", DateUtil.newDateString()
			).put(
				"processedOperations",
				runLogContextJSONObject.getInt("processedOperations")
			).put(
				"status", "IN_PROGRESS"
			).put(
				"totalOperations",
				_csvIndividualDog.getCSVIndividualsCount(dataSourceId)
			);
		}

		return JSONUtil.put(
			"dateRecorded", DateUtil.toUTCString(runLog.getDateLogged())
		).put(
			"status", status
		);
	}

	private JSONObject _getSalesforceDataSourceAccountsProgressJSONObject(
		Long dataSourceId) {

		RunLog salesforceExtractorNaniteRunLog = _runLogDog.fetchLatestRunLog(
			dataSourceId, "SalesforceExtractorNanite", null,
			WeDeployDataService.OSB_ASAH_SALESFORCE_RAW);

		if (salesforceExtractorNaniteRunLog == null) {
			return new JSONObject();
		}

		String salesforceExtractorNaniteRunLogStatus =
			salesforceExtractorNaniteRunLog.getStatus();

		if (salesforceExtractorNaniteRunLogStatus.equals("FAILED")) {
			return JSONUtil.put(
				"dateRecorded",
				DateUtil.toUTCString(
					salesforceExtractorNaniteRunLog.getDateLogged())
			).put(
				"status", "FAILED"
			);
		}

		if (salesforceExtractorNaniteRunLogStatus.equals("STARTED")) {
			return _getSalesforceExtractorNaniteProgressJSONObject(
				salesforceExtractorNaniteRunLog, 2, "Account");
		}

		RunLog salesforceAccountsNaniteRunLog = _runLogDog.fetchLatestRunLog(
			dataSourceId, "SalesforceAccountsNanite", null,
			WeDeployDataService.OSB_ASAH_FARO_INFO);

		if (salesforceAccountsNaniteRunLog == null) {
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

		String salesforceAccountsNaniteRunLogStatus =
			salesforceAccountsNaniteRunLog.getStatus();

		if (salesforceAccountsNaniteRunLogStatus.equals("STARTED")) {
			JSONObject salesforceAccountsNaniteContextJSONObject =
				salesforceAccountsNaniteRunLog.getContextJSONObject();

			int totalOperations =
				salesforceAccountsNaniteContextJSONObject.getInt(
					"totalOperations");
			totalOperations = 2 * totalOperations;

			long salesforceAuditEventsCount =
				_salesforceAuditEventDog.getSalesforceAuditEventsCount(
					salesforceExtractorNaniteRunLog.getDataSourceId(),
					"Account");

			return JSONUtil.put(
				"dateRecorded", DateUtil.newDateString()
			).put(
				"processedOperations",
				totalOperations - salesforceAuditEventsCount
			).put(
				"status", "IN_PROGRESS"
			).put(
				"totalOperations", totalOperations
			);
		}

		Date salesforceExtractorNaniteCompletedDate =
			salesforceExtractorNaniteRunLog.getDateLogged();
		Date salesforceAccountsNaniteEndDate =
			salesforceAccountsNaniteRunLog.getDateLogged();

		if (!salesforceAccountsNaniteEndDate.after(
				salesforceExtractorNaniteCompletedDate)) {

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
			DateUtil.toUTCString(salesforceAccountsNaniteRunLog.getDateLogged())
		).put(
			"status", salesforceAccountsNaniteRunLogStatus
		);
	}

	private JSONObject _getSalesforceDataSourceIndividualsProgressJSONObject(
		Long dataSourceId) {

		RunLog salesforceExtractorNaniteRunLog = _runLogDog.fetchLatestRunLog(
			dataSourceId, "SalesforceExtractorNanite", null,
			WeDeployDataService.OSB_ASAH_SALESFORCE_RAW);

		if (salesforceExtractorNaniteRunLog == null) {
			return new JSONObject();
		}

		String salesforceExtractorNaniteRunLogStatus =
			salesforceExtractorNaniteRunLog.getStatus();

		if (salesforceExtractorNaniteRunLogStatus.equals("FAILED")) {
			return JSONUtil.put(
				"dateRecorded",
				DateUtil.toUTCString(
					salesforceExtractorNaniteRunLog.getDateLogged())
			).put(
				"status", "FAILED"
			);
		}

		if (salesforceExtractorNaniteRunLogStatus.equals("STARTED")) {
			return _getSalesforceExtractorNaniteProgressJSONObject(
				salesforceExtractorNaniteRunLog, 3, "Contact", "Lead");
		}

		RunLog salesforceExtractorIndividualsNaniteRunLog =
			_runLogDog.fetchLatestRunLog(
				dataSourceId, "SalesforceExtractorIndividualsNanite", null,
				WeDeployDataService.OSB_ASAH_SALESFORCE_RAW);

		if (salesforceExtractorIndividualsNaniteRunLog == null) {
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

		String salesforceExtractorIndividualsNaniteRunLogStatus =
			salesforceExtractorIndividualsNaniteRunLog.getStatus();

		if (salesforceExtractorIndividualsNaniteRunLogStatus.equals(
				"STARTED")) {

			JSONObject
				salesforceExtractorIndividualsNaniteRunLogContextJSONObject =
					salesforceExtractorIndividualsNaniteRunLog.
						getContextJSONObject();

			int totalOperations =
				salesforceExtractorIndividualsNaniteRunLogContextJSONObject.
					getInt("totalOperations");

			long salesforceAuditEventsCount =
				_salesforceAuditEventDog.getSalesforceAuditEventsCount(
					salesforceExtractorNaniteRunLog.getDataSourceId(),
					"Contact", "Lead");

			return JSONUtil.put(
				"dateRecorded", DateUtil.newDateString()
			).put(
				"processedOperations",
				(totalOperations * 2) - salesforceAuditEventsCount
			).put(
				"status", "IN_PROGRESS"
			).put(
				"totalOperations", 3 * totalOperations
			);
		}

		Date salesforceExtractorNaniteCompletedDate =
			salesforceExtractorNaniteRunLog.getDateLogged();
		Date salesforceExtractorIndividualsNaniteEndDate =
			salesforceExtractorIndividualsNaniteRunLog.getDateLogged();

		if (!salesforceExtractorIndividualsNaniteEndDate.after(
				salesforceExtractorNaniteCompletedDate)) {

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

		if (salesforceExtractorIndividualsNaniteRunLogStatus.equals("FAILED")) {
			return JSONUtil.put(
				"dateRecorded",
				DateUtil.toUTCString(
					salesforceExtractorIndividualsNaniteRunLog.getDateLogged())
			).put(
				"status", "FAILED"
			);
		}

		RunLog salesforceIndividualsNaniteRunLog = _runLogDog.fetchLatestRunLog(
			dataSourceId, "SalesforceIndividualsNanite", null,
			WeDeployDataService.OSB_ASAH_FARO_INFO);

		if (salesforceIndividualsNaniteRunLog == null) {
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

		String salesforceIndividualsNaniteRunLogStatus =
			salesforceIndividualsNaniteRunLog.getStatus();

		if (salesforceIndividualsNaniteRunLogStatus.equals("STARTED")) {
			JSONObject salesforceIndividualsNaniteRunLogContextJSONObject =
				salesforceIndividualsNaniteRunLog.getContextJSONObject();

			int totalOperations =
				salesforceIndividualsNaniteRunLogContextJSONObject.getInt(
					"totalOperations");

			totalOperations = 3 * totalOperations;

			long salesforceAuditEventsCount =
				_salesforceAuditEventDog.getSalesforceAuditEventsCount(
					salesforceExtractorNaniteRunLog.getDataSourceId(),
					"individuals");

			return JSONUtil.put(
				"dateRecorded", DateUtil.newDateString()
			).put(
				"processedOperations",
				totalOperations - salesforceAuditEventsCount
			).put(
				"status", "IN_PROGRESS"
			).put(
				"totalOperations", totalOperations
			);
		}

		Date salesforceExtractorIndividualsNaniteCompletedDate =
			salesforceExtractorIndividualsNaniteRunLog.getDateLogged();
		Date salesforceIndividualsNaniteEndDate =
			salesforceIndividualsNaniteRunLog.getDateLogged();

		if (!salesforceIndividualsNaniteEndDate.after(
				salesforceExtractorIndividualsNaniteCompletedDate)) {

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
			DateUtil.toUTCString(
				salesforceIndividualsNaniteRunLog.getDateLogged())
		).put(
			"status", salesforceIndividualsNaniteRunLogStatus
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
		RunLog salesforceExtractorNaniteRunLog, int totalOperationsMultiplier,
		String... tableNames) {

		int processedOperations = 0;
		int totalOperations = 0;

		JSONObject salesforceExtractorNaniteRunLogContextJSONObject =
			salesforceExtractorNaniteRunLog.getContextJSONObject();

		for (String tableName : tableNames) {
			int totalTableNameOperations =
				salesforceExtractorNaniteRunLogContextJSONObject.optInt(
					"total" + tableName + "Operations");

			if (totalTableNameOperations == 0) {
				continue;
			}

			totalOperations += totalTableNameOperations;

			if (salesforceExtractorNaniteRunLogContextJSONObject.getBoolean(
					"initial" + tableName + "Run")) {

				processedOperations +=
					_salesforceEntityDog.getSalesforceEntitiesCount(
						salesforceExtractorNaniteRunLog.getDataSourceId(),
						SalesforceEntity.Type.of(tableName));
			}
			else {
				processedOperations +=
					_salesforceAuditEventDog.getSalesforceAuditEventsCount(
						salesforceExtractorNaniteRunLog.getDataSourceId(),
						tableName);
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

	private void _refreshConfiguration(DataSource dataSource) {
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

	private void _sanitize(DataSource dataSource) {
		dataSource.setFaroBackendSecuritySignature(null);
		dataSource.setPrivateKey(null);
	}

	private PageDTO<DataSourceDTO> _toPageDTO(
		Page<DataSource> dataSourcesPage) {

		return new PageDTO<>(
			"_embedded", new DataSourceDTO(dataSourcesPage.getContent()),
			dataSourcesPage.getNumber(), dataSourcesPage.getSize(),
			dataSourcesPage.getTotalElements(),
			dataSourcesPage.getTotalPages());
	}

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private ConfigurationHttp _configurationHttp;

	@Autowired
	private CSVIndividualDog _csvIndividualDog;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private DataSourceHttp _dataSourceHttp;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private RunLogDog _runLogDog;

	@Autowired
	private SalesforceAuditEventDog _salesforceAuditEventDog;

	@Autowired
	private SalesforceEntityDog _salesforceEntityDog;

	@Autowired
	private SalesforceExtractorConfigurationDog
		_salesforceExtractorConfigurationDog;

}