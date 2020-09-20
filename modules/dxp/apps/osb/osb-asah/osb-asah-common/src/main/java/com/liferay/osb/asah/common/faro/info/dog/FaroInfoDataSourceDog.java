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

package com.liferay.osb.asah.common.faro.info.dog;

import com.liferay.osb.asah.common.cache.DataSourceCache;
import com.liferay.osb.asah.common.dxp.extractor.dog.DXPExtractorConfigurationDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.http.NanitesHttp;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.salesforce.extractor.dog.SalesforceExtractorConfigurationDog;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 * @author Rachael Koestartyo
 */
@Component
public class FaroInfoDataSourceDog extends BaseFaroInfoDog {

	public JSONObject addDataSource(JSONObject dataSourceJSONObject) {
		String name = dataSourceJSONObject.getString("name");
		int nameCount = 0;
		String originalName = name;

		while (elasticsearchInvoker.exists(
					"data-sources", QueryBuilders.termQuery("name", name))) {

			name = String.format("%s (%d)", originalName, ++nameCount);
		}

		dataSourceJSONObject.put("name", name);

		String type = getDataSourceType(dataSourceJSONObject);

		dataSourceJSONObject = elasticsearchInvoker.add(
			"data-sources", dataSourceJSONObject);

		if (Objects.equals(type, "CSV")) {
			dataSourceJSONObject.put("state", "READY");
		}
		else if (Objects.equals(type, "LIFERAY")) {
			_addDefaultChannel(dataSourceJSONObject);
			_dxpExtractorConfigurationDog.addConfiguration(
				dataSourceJSONObject);
		}
		else if (Objects.equals(type, "SALESFORCE")) {
			_salesforceExtractorConfigurationDog.addConfiguration(
				dataSourceJSONObject);
		}

		dataSourceJSONObject = elasticsearchInvoker.update(
			"data-sources", dataSourceJSONObject.getString("id"),
			dataSourceJSONObject);

		_messageBus.sendMessage(
			Channel.DATA_SOURCES_UPDATED, "DATA_SOURCES_UPDATED");

		return dataSourceJSONObject;
	}

	public void deleteDataSource(
			JSONObject dataSourceJSONObject,
			Consumer<Integer> processedCountMonitorConsumer,
			Consumer<Integer> queueMonitorConsumer)
		throws Exception {

		_clearDataSource(
			dataSourceJSONObject, processedCountMonitorConsumer,
			queueMonitorConsumer);

		String dataSourceId = dataSourceJSONObject.getString("id");

		deleteFieldMappings(
			dataSourceId, processedCountMonitorConsumer, queueMonitorConsumer);

		elasticsearchInvoker.delete("data-sources", dataSourceId);

		JSONObject providerJSONObject = dataSourceJSONObject.getJSONObject(
			"provider");

		if (Objects.equals(providerJSONObject.getString("type"), "LIFERAY") ||
			(providerJSONObject.optJSONObject("analyticsConfiguration") !=
				null)) {

			_nanitesHttp.refreshAnalytics();
		}

		_messageBus.sendMessage(
			Channel.DATA_SOURCES_UPDATED, "DATA_SOURCES_UPDATED");
	}

	public void deleteFieldMappings(
			String dataSourceId,
			Consumer<Integer> processedCountMonitorConsumer,
			Consumer<Integer> queueMonitorConsumer)
		throws Exception {

		List<String> disabledFieldMappingIds = new ArrayList<>();

		JSONArrayIterator.of(
			"field-mappings", elasticsearchInvoker,
			fieldMappingJSONObject -> {
				fieldMappingJSONObject =
					_faroInfoFieldMappingDog.removeDataSourceFieldName(
						fieldMappingJSONObject, dataSourceId);

				JSONObject dataSourceFieldNamesJSONObject =
					fieldMappingJSONObject.getJSONObject(
						"dataSourceFieldNames");

				if ((dataSourceFieldNamesJSONObject.length() == 0) &&
					_faroInfoFieldMappingDog.deleteFieldMapping(
						fieldMappingJSONObject)) {

					disabledFieldMappingIds.add(
						fieldMappingJSONObject.getString("id"));
				}

				return null;
			}
		).setMonitoringConsumers(
			processedCountMonitorConsumer, queueMonitorConsumer
		).setQueryBuilder(
			QueryBuilders.existsQuery("dataSourceFieldNames." + dataSourceId)
		).iterate();

		_faroInfoIndividualSegmentDog.disableDynamicIndividualSegments(
			dataSourceId, disabledFieldMappingIds);
	}

	public JSONObject disconnectDataSource(String dataSourceId)
		throws Exception {

		JSONObject dataSourceJSONObject = getDataSourceJSONObject(dataSourceId);

		if (Objects.equals(
				dataSourceJSONObject.getString("state"), "DISCONNECTED") &&
			Objects.equals(
				dataSourceJSONObject.getString("status"), "INACTIVE")) {

			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST, "Data source already disconnected");
		}

		_clearChannels(dataSourceId);

		JSONObject credentialsJSONObject = dataSourceJSONObject.getJSONObject(
			"credentials");

		credentialsJSONObject.put("privateKey", "");
		credentialsJSONObject.put("publicKey", "");

		JSONObject detailsJSONObject = dataSourceJSONObject.optJSONObject(
			"details");

		if (dataSourceJSONObject.has("details")) {
			if (detailsJSONObject == null) {
				detailsJSONObject = new JSONObject();
			}

			detailsJSONObject.put("contactsSelected", false);
			detailsJSONObject.put("sitesSelected", false);
		}

		dataSourceJSONObject.put("faroBackendSecuritySignature", "");
		dataSourceJSONObject.put("state", "DISCONNECTED");
		dataSourceJSONObject.put("status", "INACTIVE");

		return elasticsearchInvoker.update(
			"data-sources", dataSourceId, dataSourceJSONObject);
	}

	public String getChannelId(String dataSourceId) {
		JSONObject dataSourceJSONObject = getDataSourceJSONObject(dataSourceId);

		if (dataSourceJSONObject == null) {
			return null;
		}

		return dataSourceJSONObject.optString("channelId");
	}

	public List<String> getDataSourceFieldMappingIds(
		String dataSourceId, boolean previewDelete) {

		List<String> dataSourceFieldMappingIds = new ArrayList<>();

		JSONArray jsonArray = elasticsearchInvoker.get(
			"field-mappings",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.existsQuery(
					"dataSourceFieldNames." + dataSourceId)));

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			JSONObject dataSourceFieldNamesJSONObject =
				jsonObject.getJSONObject("dataSourceFieldNames");

			if (previewDelete &&
				(dataSourceFieldNamesJSONObject.length() > 1)) {

				continue;
			}

			dataSourceFieldMappingIds.add(jsonObject.getString("id"));
		}

		return dataSourceFieldMappingIds;
	}

	public JSONObject getDataSourceJSONObject(String dataSourceId) {
		return getDataSourceJSONObject(dataSourceId, true);
	}

	public JSONObject getDataSourceJSONObject(
		String dataSourceId, boolean useCache) {

		if (useCache) {
			return _dataSourceCache.getDataSourceJSONObject(dataSourceId);
		}

		return elasticsearchInvoker.get("data-sources", dataSourceId);
	}

	public String getDataSourceName(String dataSourceId) {
		JSONObject jsonObject = getDataSourceJSONObject(dataSourceId);

		return jsonObject.getString("name");
	}

	public String getDataSourceType(JSONObject dataSourceJSONObject) {
		JSONObject providerJSONObject = dataSourceJSONObject.getJSONObject(
			"provider");

		return providerJSONObject.getString("type");
	}

	@Override
	@PostConstruct
	public void init() {
		super.init();

		_dxpRawElasticsearchInvoker = elasticsearchInvokerFactory.forDXPRaw();
		_salesforceElasticsearchInvoker =
			elasticsearchInvokerFactory.forSalesforceRaw();
	}

	public boolean isAnalyticsConfigured() {
		return elasticsearchInvoker.exists(
			"data-sources",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("provider.type", "LIFERAY")
			).filter(
				BoolQueryBuilderUtil.should(
					QueryBuilders.termQuery("details.sitesSelected", true)
				).should(
					QueryBuilders.existsQuery(
						"provider.analyticsConfiguration.sites")
				).should(
					QueryBuilders.termQuery(
						"provider.analyticsConfiguration.enableAllSites", true)
				)
			));
	}

	public JSONObject patchDataSource(
			String dataSourceId, JSONObject dataSourceJSONObject)
		throws Exception {

		JSONObject existingDataSourceJSONObject = getDataSourceJSONObject(
			dataSourceId);

		for (String key : JSONObject.getNames(dataSourceJSONObject)) {
			existingDataSourceJSONObject.put(
				key, dataSourceJSONObject.get(key));
		}

		return updateDataSource(dataSourceId, existingDataSourceJSONObject);
	}

	public JSONObject updateDataSource(
			String dataSourceId, JSONObject dataSourceJSONObject)
		throws Exception {

		String name = dataSourceJSONObject.optString("name", null);

		// Skip JavaParser, will fix

		if ((name != null) &&
			elasticsearchInvoker.exists(
				"data-sources",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.boolQuery(
				).mustNot(
					QueryBuilders.termQuery("id", dataSourceId)
				)
			).filter(
				QueryBuilders.termQuery("name", name)
			))) {

			throw new Exception("Duplicate data source name " + name);
		}

		String type = getDataSourceType(dataSourceJSONObject);

		if (type.equals("LIFERAY")) {
			_dxpExtractorConfigurationDog.updateConfiguration(
				dataSourceJSONObject);
		}
		else if (type.equals("SALESFORCE")) {
			_salesforceExtractorConfigurationDog.updateConfiguration(
				dataSourceJSONObject);
		}

		return elasticsearchInvoker.update(
			"data-sources", dataSourceId, dataSourceJSONObject);
	}

	public JSONObject updateDataSourceDetails(
			String dataSourceId, JSONObject newDetailsJSONObject)
		throws Exception {

		JSONObject dataSourceJSONObject = getDataSourceJSONObject(dataSourceId);

		JSONObject existingDetailsJSONObject =
			dataSourceJSONObject.optJSONObject("details");

		dataSourceJSONObject.put(
			"details",
			JSONUtil.merge(existingDetailsJSONObject, newDetailsJSONObject));

		JSONObject jsonObject = elasticsearchInvoker.update(
			"data-sources", dataSourceId, dataSourceJSONObject);

		if (newDetailsJSONObject.optBoolean("sitesSelected") &&
			((existingDetailsJSONObject == null) ||
			 !existingDetailsJSONObject.optBoolean("sitesSelected"))) {

			_nanitesHttp.refreshAnalytics();

			_faroInfoOSBAsahTaskDog.addOSBAsahTask("ActivitiesNanite", null);
			_faroInfoOSBAsahTaskDog.addOSBAsahTask(
				"IndividualActivityFieldsNanite", null);
			_faroInfoOSBAsahTaskDog.addOSBAsahTask(
				"IndividualSegmentActivityFieldsNanite", null);
		}

		return jsonObject;
	}

	private void _addDefaultChannel(JSONObject dataSourceJSONObject) {
		JSONObject channelJSONObject = _faroInfoChannelDog.addChannel(
			Collections.singletonList(
				JSONUtil.put(
					"groupIds", Collections.emptyList()
				).put(
					"id", dataSourceJSONObject.getString("id")
				)),
			dataSourceJSONObject.getString("name"), true);

		dataSourceJSONObject.put(
			"channelId", channelJSONObject.getString("id"));
	}

	private void _clearChannels(String dataSourceId) {
		JSONArray channelsJSONArray = elasticsearchInvoker.get(
			"channels",
			QueryBuilders.termQuery("dataSources.id", dataSourceId));

		for (int i = 0; i < channelsJSONArray.length(); i++) {
			JSONObject channelJSONObject = channelsJSONArray.getJSONObject(i);

			Stream<Object> stream = JSONUtil.toObjectStream(
				channelJSONObject.getJSONArray("dataSources"));

			channelJSONObject.put(
				"dataSources",
				stream.map(
					object -> (JSONObject)object
				).filter(
					jsonObject -> !Objects.equals(
						jsonObject.get("id"), dataSourceId)
				).collect(
					Collectors.toList()
				));

			elasticsearchInvoker.update("channels", channelJSONObject);
		}
	}

	private void _clearDataSource(
			JSONObject dataSourceJSONObject,
			Consumer<Integer> processedCountMonitorConsumer,
			Consumer<Integer> queueMonitorConsumer)
		throws Exception {

		String dataSourceId = dataSourceJSONObject.getString("id");

		_deleteData(dataSourceJSONObject);

		_deleteRunLogs(dataSourceJSONObject);

		JSONArrayIterator.of(
			"accounts", elasticsearchInvoker,
			accountJSONObject -> {
				try {
					_faroInfoAccountDog.deleteAccount(accountJSONObject);
				}
				catch (Exception e) {
					return e;
				}

				return null;
			}
		).setMonitoringConsumers(
			processedCountMonitorConsumer, queueMonitorConsumer
		).setQueryBuilder(
			QueryBuilders.termQuery("dataSourceId", dataSourceId)
		).iterate();

		String deletionDateString = dataSourceJSONObject.getString(
			"deletionDate");

		JSONArrayIterator.of(
			"individuals", elasticsearchInvoker,
			individualJSONObject -> {
				try {
					JSONArray dataSourceIndividualPKsJSONArray =
						individualJSONObject.getJSONArray(
							"dataSourceIndividualPKs");

					if (dataSourceIndividualPKsJSONArray.length() == 1) {
						_faroInfoIndividualDog.deleteIndividual(
							deletionDateString,
							individualJSONObject.getString("id"));
					}
					else {
						_faroInfoIndividualDog.removeDataSourceIndividualPKs(
							individualJSONObject, dataSourceId);

						_faroInfoIndividualDog.updateIndividual(
							null, _getEmptyDataJSONObject(dataSourceJSONObject),
							dataSourceJSONObject, individualJSONObject);
					}
				}
				catch (Exception e) {
					return e;
				}

				return null;
			}
		).setMonitoringConsumers(
			processedCountMonitorConsumer, queueMonitorConsumer
		).setQueryBuilder(
			QueryBuilders.nestedQuery(
				"dataSourceIndividualPKs",
				QueryBuilders.termQuery(
					"dataSourceIndividualPKs.dataSourceId", dataSourceId),
				ScoreMode.None)
		).iterate();
	}

	private void _deleteAccountReferences(String dataSourceId)
		throws Exception {

		JSONArrayIterator.of(
			"individuals", elasticsearchInvoker,
			individualJSONObject -> {
				JSONObject modifiedJSONObject = new JSONObject();

				JSONArray dataSourceAccountPKsJSONArray =
					individualJSONObject.getJSONArray("dataSourceAccountPKs");

				Iterator<Object> iterator =
					dataSourceAccountPKsJSONArray.iterator();

				while (iterator.hasNext()) {
					JSONObject jsonObject = (JSONObject)iterator.next();

					if (StringUtils.equals(
							jsonObject.getString("dataSourceId"),
							dataSourceId)) {

						iterator.remove();
					}
				}

				modifiedJSONObject.put(
					"dataSourceAccountPKs", dataSourceAccountPKsJSONArray);

				elasticsearchInvoker.update(
					"individuals", individualJSONObject.getString("id"),
					modifiedJSONObject);

				return null;
			}
		).setQueryBuilder(
			QueryBuilders.nestedQuery(
				"dataSourceAccountPKs",
				QueryBuilders.termQuery(
					"dataSourceAccountPKs.dataSourceId", dataSourceId),
				ScoreMode.None)
		).iterate();
	}

	private void _deleteData(JSONObject dataSourceJSONObject) throws Exception {
		String dataSourceId = dataSourceJSONObject.getString("id");

		String type = getDataSourceType(dataSourceJSONObject);

		if (type.equals("CSV")) {
			_deleteData(
				dataSourceId, "dataSourceId", elasticsearchInvoker,
				"csv-individuals");
		}
		else if (type.equals("LIFERAY")) {
			_deleteData(
				dataSourceId, "osbAsahDataSourceId",
				_dxpRawElasticsearchInvoker, "OSBAsahMarkers", "audit-events",
				"groups", "organizations", "roles", "teams", "user-groups",
				"users");
			_deleteData(
				dataSourceId, "dataSourceId", elasticsearchInvoker,
				"organizations");
			_deleteIndividualReferences(dataSourceId);
		}
		else if (type.equals("SALESFORCE")) {
			_deleteAccountReferences(dataSourceId);
			_deleteData(
				dataSourceId, "osbAsahDataSourceId",
				_salesforceElasticsearchInvoker, "Account", "Contact", "Lead",
				"OSBAsahMarkers", "individuals");
			_deleteData(
				dataSourceId, "dataSourceId", elasticsearchInvoker, "fields");
		}
		else if (_log.isWarnEnabled()) {
			_log.warn(
				"Raw data for data source " + dataSourceId +
					" with unknown provider type " + type +
						" will not be deleted");
		}
	}

	private void _deleteData(
		String dataSourceId, String dataSourceIdFieldName,
		ElasticsearchInvoker elasticsearchInvoker, String... collectionNames) {

		for (String collectionName : collectionNames) {
			elasticsearchInvoker.delete(
				collectionName,
				QueryBuilders.termQuery(dataSourceIdFieldName, dataSourceId));
		}
	}

	private void _deleteIndividualReferences(String dataSourceId)
		throws Exception {

		_deleteIndividualReferences(
			"groupIds",
			_getDataIds(
				"groups", dataSourceId, "osbAsahDataSourceId",
				_dxpRawElasticsearchInvoker));
		_deleteIndividualReferences(
			"organizationIds",
			_getDataIds(
				"organizations", dataSourceId, "dataSourceId",
				elasticsearchInvoker));
		_deleteIndividualReferences(
			"roleIds",
			_getDataIds(
				"roles", dataSourceId, "osbAsahDataSourceId",
				_dxpRawElasticsearchInvoker));
		_deleteIndividualReferences(
			"teamIds",
			_getDataIds(
				"teams", dataSourceId, "osbAsahDataSourceId",
				_dxpRawElasticsearchInvoker));
		_deleteIndividualReferences(
			"userGroupIds",
			_getDataIds(
				"user-groups", dataSourceId, "osbAsahDataSourceId",
				_dxpRawElasticsearchInvoker));
	}

	private void _deleteIndividualReferences(String fieldName, List<String> ids)
		throws Exception {

		JSONArrayIterator.of(
			"individuals", elasticsearchInvoker,
			individualJSONObject -> {
				JSONObject modifiedJSONObject = new JSONObject();

				JSONArray idsJSONArray = individualJSONObject.getJSONArray(
					fieldName);

				Iterator<Object> iterator = idsJSONArray.iterator();

				while (iterator.hasNext()) {
					String id = (String)iterator.next();

					if (ids.contains(id)) {
						iterator.remove();
					}
				}

				modifiedJSONObject.put(fieldName, idsJSONArray);

				elasticsearchInvoker.update(
					"individuals", individualJSONObject.getString("id"),
					modifiedJSONObject);

				return null;
			}
		).setQueryBuilder(
			QueryBuilders.termsQuery(fieldName, ids)
		).iterate();
	}

	private void _deleteRunLogs(JSONObject dataSourceJSONObject) {
		QueryBuilder queryBuilder = QueryBuilders.termQuery(
			"dataSourceId", dataSourceJSONObject.getString("id"));

		elasticsearchInvoker.delete("run-logs", queryBuilder);

		String type = getDataSourceType(dataSourceJSONObject);

		if (type.equals("LIFERAY")) {
			_dxpRawElasticsearchInvoker.delete("run-logs", queryBuilder);
		}
		else if (type.equals("SALESFORCE")) {
			_salesforceElasticsearchInvoker.delete("run-logs", queryBuilder);
		}
	}

	private List<String> _getDataIds(
		String collectionName, String dataSourceId,
		String dataSourceIdFieldName,
		ElasticsearchInvoker elasticsearchInvoker) {

		return JSONUtil.toStringList(
			elasticsearchInvoker.get(
				collectionName,
				QueryBuilders.termQuery(dataSourceIdFieldName, dataSourceId)),
			"id");
	}

	private JSONObject _getEmptyDataJSONObject(
		JSONObject dataSourceJSONObject) {

		String type = getDataSourceType(dataSourceJSONObject);

		if (type.equals("CSV")) {
			return JSONUtil.put("fields", new JSONObject());
		}
		else if (type.equals("LIFERAY")) {
			return JSONUtil.put("contact", new JSONObject());
		}
		else if (type.equals("SALESFORCE")) {
		}
		else if (_log.isWarnEnabled()) {
			_log.warn(
				"Invalid provider type " + type + " for data source " +
					dataSourceJSONObject.getString("id"));
		}

		return new JSONObject();
	}

	private static final Log _log = LogFactory.getLog(
		FaroInfoDataSourceDog.class);

	@Autowired
	private DataSourceCache _dataSourceCache;

	@Autowired
	private DXPExtractorConfigurationDog _dxpExtractorConfigurationDog;

	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@Autowired
	private FaroInfoAccountDog _faroInfoAccountDog;

	@Autowired
	private FaroInfoChannelDog _faroInfoChannelDog;

	@Autowired
	private FaroInfoFieldMappingDog _faroInfoFieldMappingDog;

	@Autowired
	private FaroInfoIndividualDog _faroInfoIndividualDog;

	@Autowired
	private FaroInfoIndividualSegmentDog _faroInfoIndividualSegmentDog;

	@Autowired
	private FaroInfoOSBAsahTaskDog _faroInfoOSBAsahTaskDog;

	@Autowired
	private MessageBus _messageBus;

	@Autowired
	private NanitesHttp _nanitesHttp;

	private ElasticsearchInvoker _salesforceElasticsearchInvoker;

	@Autowired
	private SalesforceExtractorConfigurationDog
		_salesforceExtractorConfigurationDog;

}