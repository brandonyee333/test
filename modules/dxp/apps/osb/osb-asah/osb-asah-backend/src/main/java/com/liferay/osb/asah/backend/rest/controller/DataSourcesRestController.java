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

import com.liferay.osb.asah.backend.rest.response.TermsAggregationTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dxp.extractor.dog.DXPExtractorConfigurationDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoDataSourceDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoOSBAsahTaskDog;
import com.liferay.osb.asah.common.http.ConfigurationHttp;
import com.liferay.osb.asah.common.http.DataSourceHttp;
import com.liferay.osb.asah.common.http.PortalPreferencesHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.rest.response.PatchResponse;
import com.liferay.osb.asah.common.rest.response.PostResponse;
import com.liferay.osb.asah.common.rest.response.PutResponse;
import com.liferay.osb.asah.common.run.logger.RunLogger;
import com.liferay.osb.asah.common.salesforce.extractor.dog.SalesforceExtractorConfigurationDog;

import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import java.util.function.Supplier;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void deleteDataSource(@PathVariable String id) throws Exception {
		_dxpExtractorConfigurationDog.deleteConfiguration(id);
		_salesforceExtractorConfigurationDog.deleteConfiguration(id);

		_faroInfoOSBAsahTaskDog.addOSBAsahTask(
			"DeleteDataSourcesNanite",
			faroInfoElasticsearchInvoker.update(
				"data-sources", id,
				JSONUtil.put(
					"deletionDate", DateUtil.newDateString()
				).put(
					"state", "IN_PROGRESS_DELETING"
				)));
	}

	@PostMapping("/{id}/disconnect")
	public String disconnectDataSource(@PathVariable String id)
		throws Exception {

		JSONObject dataSourceJSONObject =
			_faroInfoDataSourceDog.disconnectDataSource(id);

		_sanitize(dataSourceJSONObject);

		return dataSourceJSONObject.toString();
	}

	@GetMapping("/{id}")
	public String getDataSource(@PathVariable String id) {
		JSONObject dataSourceJSONObject =
			_faroInfoDataSourceDog.getDataSourceJSONObject(id);

		_setLastSyncTime(dataSourceJSONObject);

		_sanitize(dataSourceJSONObject);

		return dataSourceJSONObject.toString();
	}

	@GetMapping(params = "!apply")
	public String getDataSources(
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		JSONObject responseJSONObject = new JSONObject(
			toCollectionGetResponse(
				"data-sources", null, page,
				FilterStringToQueryBuilderConverter.convert(filterString), size,
				sorts));

		JSONObject embeddedJSONObject = responseJSONObject.getJSONObject(
			"_embedded");

		JSONArray dataSourcesJSONArray = embeddedJSONObject.getJSONArray(
			"data-sources");

		for (int i = 0; i < dataSourcesJSONArray.length(); i++) {
			JSONObject dataSourceJSONObject =
				dataSourcesJSONArray.getJSONObject(i);

			_setLastSyncTime(dataSourceJSONObject);

			_sanitize(dataSourceJSONObject);
		}

		return responseJSONObject.toString();
	}

	@GetMapping(params = "apply")
	public String getDataSourceTransformations(
			@RequestParam String apply,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size)
		throws Exception {

		return toTransformationGetResponse(
			"data-sources", page,
			FilterStringToQueryBuilderConverter.convert(filterString), size,
			null, null,
			new TermsAggregationTransformationJSONArrayFunction(apply, null),
			"data-source-transformations");
	}

	@GetMapping(params = "parentGroupId", value = "/{id}/dxp-groups")
	public String getDXPGroups(
			@PathVariable String id, @RequestParam(defaultValue = "20") int end,
			@RequestParam(required = false) String name,
			@RequestParam long parentGroupId, @RequestParam boolean site,
			@RequestParam(defaultValue = "0") int start)
		throws Exception {

		return _exchange(
			id,
			() -> _dataSourceHttp.getDXPGroups(
				id, end, name, parentGroupId, site, start));
	}

	@PostMapping(params = "!parentGroupId", value = "/{id}/dxp-groups")
	public String getDXPGroups(
			@PathVariable String id, @RequestBody String json)
		throws Exception {

		return _exchange(id, () -> _dataSourceHttp.getDXPGroups(id, json));
	}

	@GetMapping(
		params = "parentOrganizationId", value = "/{id}/dxp-organizations"
	)
	public String getDXPOrganizations(
			@PathVariable String id, @RequestParam(defaultValue = "20") int end,
			@RequestParam(required = false) String name,
			@RequestParam long parentOrganizationId,
			@RequestParam(defaultValue = "0") int start)
		throws Exception {

		return _exchange(
			id,
			() -> _dataSourceHttp.getDXPOrganizations(
				id, end, name, parentOrganizationId, start));
	}

	@PostMapping(
		params = "!parentOrganizationId", value = "/{id}/dxp-organizations"
	)
	public String getDXPOrganizations(
			@PathVariable String id, @RequestBody String json)
		throws Exception {

		return _exchange(
			id, () -> _dataSourceHttp.getDXPOrganizations(id, json));
	}

	@GetMapping(params = "name", value = "/{id}/dxp-user-groups")
	public String getDXPUserGroups(
			@PathVariable String id, @RequestParam(defaultValue = "20") int end,
			@RequestParam String name,
			@RequestParam(defaultValue = "0") int start)
		throws Exception {

		return _exchange(
			id, () -> _dataSourceHttp.getDXPUserGroups(id, end, name, start));
	}

	@PostMapping(params = "!name", value = "/{id}/dxp-user-groups")
	public String getDXPUserGroups(
			@PathVariable String id, @RequestBody String json)
		throws Exception {

		return _exchange(id, () -> _dataSourceHttp.getDXPUserGroups(id, json));
	}

	@GetMapping("/{id}/dxp-users/fields")
	public String getDXPUsersFields(
			@PathVariable String id, @RequestParam(defaultValue = "20") int end,
			@RequestParam(defaultValue = "0") int start)
		throws Exception {

		return _exchange(
			id, () -> _dataSourceHttp.getDXPUsersFields(id, end, start));
	}

	@GetMapping("/{id}/dxp-users/total")
	public String getDXPUsersTotal(@PathVariable String id) throws Exception {
		return getDXPUsersTotal(id, null);
	}

	@PostMapping("/{id}/dxp-users/total")
	public String getDXPUsersTotal(
			@PathVariable String id, @RequestBody(required = false) String json)
		throws Exception {

		return _exchange(id, () -> _dataSourceHttp.getDXPUsersTotal(id, json));
	}

	@GetMapping("/{id}/progress")
	public String getProgress(@PathVariable String id) {
		String type = _faroInfoDataSourceDog.getDataSourceType(
			_faroInfoDataSourceDog.getDataSourceJSONObject(id));

		if (type.equals("CSV")) {
			return String.valueOf(_getCSVDataSourceProgressJSONObject(id));
		}
		else if (type.equals("LIFERAY")) {
			return String.valueOf(_getLiferayDataSourceProgressJSONObject(id));
		}
		else if (type.equals("SALESFORCE")) {
			return String.valueOf(
				_getSalesforceDataSourceProgressJSONObject(id));
		}

		throw new UnsupportedOperationException(
			"Unknown data source type " + type);
	}

	@GetMapping("/{id}/salesforce-accounts/fields")
	public String getSalesforceAccountsFields(
			@PathVariable String id, @RequestParam(defaultValue = "20") int end,
			@RequestParam(defaultValue = "0") int start)
		throws Exception {

		return _exchange(
			id,
			() -> _dataSourceHttp.getSalesforceAccountsFields(id, end, start));
	}

	@GetMapping("/{id}/salesforce-users/fields")
	public String getSalesforceUsersFields(
			@PathVariable String id, @RequestParam(defaultValue = "20") int end,
			@RequestParam(defaultValue = "0") int start)
		throws Exception {

		return _exchange(
			id, () -> _dataSourceHttp.getSalesforceUsersFields(id, end, start));
	}

	@PatchMapping("/{id}")
	public String patchDataSource(
			@PathVariable String id, @RequestBody String json)
		throws Exception {

		PatchResponse patchResponse = new PatchResponse() {

			@Override
			protected JSONObject invokeElasticsearch(JSONObject jsonObject)
				throws Exception {

				return _faroInfoDataSourceDog.patchDataSource(id, jsonObject);
			}

		};

		patchResponse.setJSON(json);

		return patchResponse.respond();
	}

	@PostMapping
	public String postDataSource(@RequestBody String json) throws Exception {
		PostResponse postResponse = new PostResponse() {

			@Override
			protected JSONObject invokeElasticsearch(JSONObject jsonObject)
				throws Exception {

				return _faroInfoDataSourceDog.addDataSource(jsonObject);
			}

			@Override
			protected void onBeforeAdd(JSONObject jsonObject) {
				super.onBeforeAdd(jsonObject);

				_removeLastSyncTime(jsonObject);
			}

		};

		postResponse.setJSON(json);

		return postResponse.respond();
	}

	@PutMapping("/{id}")
	public String putDataSource(
			@PathVariable String id, @RequestBody String json)
		throws Exception {

		PutResponse putResponse = new PutResponse() {

			@Override
			protected JSONObject invokeElasticsearch(JSONObject jsonObject)
				throws Exception {

				return _faroInfoDataSourceDog.updateDataSource(id, jsonObject);
			}

			@Override
			protected void onBeforeUpdate(JSONObject jsonObject) {
				super.onBeforeUpdate(jsonObject);

				_removeLastSyncTime(jsonObject);
			}

		};

		putResponse.setJSON(json);

		return putResponse.respond();
	}

	@PostMapping("/refresh-liferay")
	public String refreshLiferay() {
		JSONArray responsesJSONArray = new JSONArray();

		JSONArray dataSourcesJSONArray = faroInfoElasticsearchInvoker.get(
			"data-sources",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("provider.type", "LIFERAY")
			).filter(
				QueryBuilders.termQuery("status", "ACTIVE")
			));

		for (int i = 0; i < dataSourcesJSONArray.length(); i++) {
			JSONObject dataSourceJSONObject =
				dataSourcesJSONArray.getJSONObject(i);

			int statusCode = 0;

			try {
				ResponseEntity<String> responseEntity =
					_portalPreferencesHttp.updatePortalPreferences(
						dataSourceJSONObject);

				statusCode = responseEntity.getStatusCodeValue();
			}
			catch (HttpClientErrorException hcee) {
				statusCode = hcee.getRawStatusCode();
			}

			responsesJSONArray.put(
				JSONUtil.put(
					"dataSourceId", dataSourceJSONObject.getString("id")
				).put(
					"statusCode", statusCode
				));
		}

		return responsesJSONArray.toString();
	}

	private String _exchange(
			String dataSourceId, Supplier<ResponseEntity<String>> supplier)
		throws Exception {

		JSONObject dataSourceJSONObject =
			_faroInfoDataSourceDog.getDataSourceJSONObject(dataSourceId);

		try {
			ResponseEntity<String> responseEntity = supplier.get();

			if (responseEntity.getStatusCode() != HttpStatus.OK) {
				_refreshConfiguration(dataSourceJSONObject);
			}

			return responseEntity.getBody();
		}
		catch (Exception e) {
			_refreshConfiguration(dataSourceJSONObject);

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

	private JSONObject _getCSVDataSourceProgressJSONObject(
		String dataSourceId) {

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
					QueryBuilders.termQuery("dataSourceId", dataSourceId))
			);
		}

		return JSONUtil.put(
			"dateRecorded", runLogJSONObject.getString("dateLogged")
		).put(
			"status", status
		);
	}

	private JSONObject _getLiferayDataSourceProgressJSONObject(
		String dataSourceId) {

		JSONObject dxpIndividualsNaniteRunLogJSONObject =
			_runLogger.fetchLatestRunLogJSONObject(
				dataSourceId, faroInfoElasticsearchInvoker,
				"DXPIndividualsNanite");

		if ((dxpIndividualsNaniteRunLogJSONObject != null) &&
			dxpIndividualsNaniteRunLogJSONObject.optBoolean("reprocess")) {

			String status = dxpIndividualsNaniteRunLogJSONObject.getString(
				"status");

			if (status.equals("STARTED")) {
				return JSONUtil.put(
					"dateRecorded", DateUtil.newDateString()
				).put(
					"processedOperations",
					dxpIndividualsNaniteRunLogJSONObject.getInt(
						"processedOperations")
				).put(
					"status", "IN_PROGRESS"
				).put(
					"totalOperations",
					dxpRawElasticsearchInvoker.count(
						"users",
						QueryBuilders.termQuery(
							"osbAsahDataSourceId", dataSourceId))
				);
			}
		}

		JSONObject dxpExtractorNaniteRunLogJSONObject =
			_runLogger.fetchLatestRunLogJSONObject(
				dataSourceId, dxpRawElasticsearchInvoker, "DXPExtractorNanite");

		if (dxpExtractorNaniteRunLogJSONObject == null) {
			return new JSONObject();
		}

		String dxpExtractorNaniteStatus =
			dxpExtractorNaniteRunLogJSONObject.getString("status");

		if (dxpExtractorNaniteStatus.equals("FAILED")) {
			return JSONUtil.put(
				"individuals",
				JSONUtil.put(
					"dateRecorded",
					dxpExtractorNaniteRunLogJSONObject.getString("dateLogged")
				).put(
					"status", "FAILED"
				));
		}
		else if (dxpExtractorNaniteStatus.equals("STARTED")) {
			int totalOperations = dxpExtractorNaniteRunLogJSONObject.getInt(
				"totalOperations");

			long processedOperations = 0;

			if (dxpExtractorNaniteRunLogJSONObject.getBoolean("initialRun")) {
				processedOperations = dxpRawElasticsearchInvoker.count(
					"users",
					QueryBuilders.termQuery(
						"osbAsahDataSourceId", dataSourceId));
			}
			else {
				long count = dxpRawElasticsearchInvoker.count(
					"audit-events",
					QueryBuilders.termQuery(
						"osbAsahDataSourceId", dataSourceId));

				processedOperations = totalOperations - count;
			}

			return JSONUtil.put(
				"individuals",
				JSONUtil.put(
					"dateRecorded", DateUtil.newDateString()
				).put(
					"processedOperations", processedOperations
				).put(
					"status", "IN_PROGRESS"
				).put(
					"totalOperations", totalOperations * 2
				));
		}

		if (dxpIndividualsNaniteRunLogJSONObject == null) {
			return JSONUtil.put(
				"individuals",
				JSONUtil.put(
					"dateRecorded", DateUtil.newDateString()
				).put(
					"processedOperations", 1
				).put(
					"status", "IN_PROGRESS"
				).put(
					"totalOperations", 2
				));
		}

		String dxpIndividualsNaniteStatus =
			dxpIndividualsNaniteRunLogJSONObject.getString("status");

		if (dxpIndividualsNaniteStatus.equals("STARTED")) {
			int totalOperations =
				2 *
					dxpIndividualsNaniteRunLogJSONObject.getInt(
						"totalOperations");

			return JSONUtil.put(
				"individuals",
				JSONUtil.put(
					"dateRecorded", DateUtil.newDateString()
				).put(
					"processedOperations",
					totalOperations -
						dxpRawElasticsearchInvoker.count(
							"faro-audit-events",
							QueryBuilders.termQuery(
								"osbAsahDataSourceId", dataSourceId))
				).put(
					"status", "IN_PROGRESS"
				).put(
					"totalOperations", totalOperations
				));
		}

		String dxpExtractorNaniteCompletedDateString =
			dxpExtractorNaniteRunLogJSONObject.getString("dateLogged");
		String dxpIndividualsNaniteEndDateString =
			dxpIndividualsNaniteRunLogJSONObject.getString("dateLogged");

		if (dxpIndividualsNaniteEndDateString.compareTo(
				dxpExtractorNaniteCompletedDateString) <= 0) {

			return JSONUtil.put(
				"individuals",
				JSONUtil.put(
					"dateRecorded", DateUtil.newDateString()
				).put(
					"processedOperations", 1
				).put(
					"status", "IN_PROGRESS"
				).put(
					"totalOperations", 2
				));
		}

		return JSONUtil.put(
			"individuals",
			JSONUtil.put(
				"dateRecorded",
				dxpIndividualsNaniteRunLogJSONObject.getString("dateLogged")
			).put(
				"status", dxpIndividualsNaniteStatus
			));
	}

	private JSONObject _getSalesforceDataSourceAccountsProgressJSONObject(
		String dataSourceId) {

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
		String dataSourceId) {

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
		String dataSourceId) {

		JSONObject dataSourceJSONObject =
			_faroInfoDataSourceDog.getDataSourceJSONObject(dataSourceId);

		JSONObject providerJSONObject = dataSourceJSONObject.getJSONObject(
			"provider");

		JSONObject accountsConfigurationJSONObject =
			providerJSONObject.optJSONObject("accountsConfiguration");
		JSONObject contactsConfigurationJSONObject =
			providerJSONObject.optJSONObject("contactsConfiguration");

		JSONObject progressJSONObject = new JSONObject();

		if ((accountsConfigurationJSONObject != null) &&
			accountsConfigurationJSONObject.getBoolean("enableAllAccounts")) {

			progressJSONObject.put(
				"accounts",
				_getSalesforceDataSourceAccountsProgressJSONObject(
					dataSourceId));
		}

		if ((contactsConfigurationJSONObject != null) &&
			(contactsConfigurationJSONObject.getBoolean("enableAllContacts") ||
			 contactsConfigurationJSONObject.getBoolean("enableAllLeads"))) {

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

	private void _refreshConfiguration(JSONObject dataSourceJSONObject)
		throws Exception {

		String type = _faroInfoDataSourceDog.getDataSourceType(
			dataSourceJSONObject);

		if (!Objects.equals(type, "LIFERAY") &&
			!Objects.equals(type, "SALESFORCE")) {

			return;
		}

		JSONObject newDataSourceJSONObject = new JSONObject(
			_configurationHttp.refreshConfiguration(
				dataSourceJSONObject, type));

		if (JSONUtil.equals(dataSourceJSONObject, newDataSourceJSONObject)) {
			return;
		}

		if (Objects.equals(type, "LIFERAY")) {
			_dxpExtractorConfigurationDog.updateConfiguration(
				newDataSourceJSONObject);
		}
		else if (Objects.equals(type, "SALESFORCE")) {
			_salesforceExtractorConfigurationDog.updateConfiguration(
				newDataSourceJSONObject);
		}

		_faroInfoDataSourceDog.updateDataSource(
			dataSourceJSONObject.getString("id"), newDataSourceJSONObject);
	}

	private void _removeLastSyncTime(JSONObject dataSourceJSONObject) {
		JSONObject providerJSONObject = dataSourceJSONObject.getJSONObject(
			"provider");

		JSONObject analyticsConfigurationJSONObject =
			providerJSONObject.optJSONObject("analyticsConfiguration");

		if (analyticsConfigurationJSONObject != null) {
			analyticsConfigurationJSONObject.remove("lastSyncTime");
		}

		JSONObject contactsConfigurationJSONObject =
			providerJSONObject.optJSONObject("contactsConfiguration");

		if (contactsConfigurationJSONObject != null) {
			contactsConfigurationJSONObject.remove(
				"lastSuccessfulAuditEventTime");
			contactsConfigurationJSONObject.remove("lastSyncTime");
		}
	}

	private void _sanitize(JSONObject dataSourceJSONObject) {
		dataSourceJSONObject.remove("faroBackendSecuritySignature");

		JSONObject credentialsJSONObject = dataSourceJSONObject.optJSONObject(
			"credentials");

		if (credentialsJSONObject != null) {
			credentialsJSONObject.remove("privateKey");
		}
	}

	private void _setLastSyncTime(JSONObject dataSourceJSONObject) {
		JSONObject providerJSONObject = dataSourceJSONObject.getJSONObject(
			"provider");

		String type = providerJSONObject.getString("type");

		if (!type.equals("LIFERAY")) {
			return;
		}

		String dataSourceId = dataSourceJSONObject.getString("id");

		JSONObject analyticsConfigurationJSONObject =
			providerJSONObject.optJSONObject("analyticsConfiguration");

		if (analyticsConfigurationJSONObject != null) {
			JSONArray activitiesJSONArray = new JSONArray(
				faroInfoElasticsearchInvoker.get(
					"activities",
					searchSourceBuilder -> {
						BoolQueryBuilder boolQueryBuilder =
							BoolQueryBuilderUtil.filter(
								QueryBuilders.termQuery(
									"dataSourceId", dataSourceId));

						Object lastSyncTime =
							analyticsConfigurationJSONObject.opt(
								"lastSyncTime");

						if (!Objects.isNull(lastSyncTime)) {
							String lastSyncTimeString = lastSyncTime.toString();

							if (!lastSyncTimeString.equals("null")) {
								boolQueryBuilder.filter(
									QueryBuilders.rangeQuery(
										"endTime"
									).gt(
										lastSyncTime
									));
							}
						}

						searchSourceBuilder.query(boolQueryBuilder);

						searchSourceBuilder.size(1);
						searchSourceBuilder.sort(
							SortBuilderUtil.fieldSort(
								"endTime", SortOrder.DESC));
					}));

			if (activitiesJSONArray.length() > 0) {
				JSONObject activityJSONObject =
					activitiesJSONArray.getJSONObject(0);

				String lastSyncTime = activityJSONObject.getString("endTime");

				analyticsConfigurationJSONObject.put(
					"lastSyncTime", lastSyncTime);

				Script script = new Script(
					Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
					"ctx._source.provider.analyticsConfiguration." +
						"lastSyncTime = params.lastSyncTime",
					Collections.singletonMap("lastSyncTime", lastSyncTime));

				faroInfoElasticsearchInvoker.update(
					"data-sources", dataSourceId, script);
			}
		}

		JSONObject contactsConfigurationJSONObject =
			providerJSONObject.optJSONObject("contactsConfiguration");

		if (contactsConfigurationJSONObject == null) {
			return;
		}

		JSONObject dxpRawOSBAsahMarkersJSONObject =
			dxpRawElasticsearchInvoker.fetch(
				"OSBAsahMarkers",
				QueryBuilders.termQuery("osbAsahDataSourceId", dataSourceId));

		if (dxpRawOSBAsahMarkersJSONObject == null) {
			return;
		}

		long lastSyncTime = dxpRawOSBAsahMarkersJSONObject.optLong(
			"lastSyncTime");

		if (lastSyncTime > 0) {
			contactsConfigurationJSONObject.put(
				"lastSyncTime", DateUtil.toUTCString(new Date(lastSyncTime)));
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
			contactsConfigurationJSONObject.put(
				"lastSuccessfulAuditEventTime",
				DateUtil.toUTCString(new Date(createDate)));
		}
	}

	@Autowired
	private ConfigurationHttp _configurationHttp;

	@Autowired
	private DataSourceHttp _dataSourceHttp;

	@Autowired
	private DXPExtractorConfigurationDog _dxpExtractorConfigurationDog;

	@Autowired
	private FaroInfoDataSourceDog _faroInfoDataSourceDog;

	@Autowired
	private FaroInfoOSBAsahTaskDog _faroInfoOSBAsahTaskDog;

	@Autowired
	private PortalPreferencesHttp _portalPreferencesHttp;

	@Autowired
	private RunLogger _runLogger;

	@Autowired
	private SalesforceExtractorConfigurationDog
		_salesforceExtractorConfigurationDog;

}