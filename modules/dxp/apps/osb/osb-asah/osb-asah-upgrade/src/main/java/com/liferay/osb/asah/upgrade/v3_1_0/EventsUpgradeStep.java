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

package com.liferay.osb.asah.upgrade.v3_1_0;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryError;
import com.google.cloud.bigquery.BigQueryException;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.InsertAllRequest;
import com.google.cloud.bigquery.InsertAllResponse;
import com.google.cloud.bigquery.TableId;

import com.liferay.osb.asah.common.concurrent.BoundedExecutor;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.util.MapUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.util.StringUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.postgresql.util.PGobject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class EventsUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

		searchSourceBuilder.fetchSource(
			new String[] {
				"applicationId", "channelId", "dataSourceId", "endTime",
				"eventContext", "eventId", "eventProperties", "id",
				"object.canonicalUrl", "object.name", "object.url", "ownerId",
				"sessionId", "userId"
			},
			null);
		searchSourceBuilder.query(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.existsQuery("eventContext")
			).filter(
				QueryBuilders.existsQuery("sessionId")
			));
		searchSourceBuilder.size(_customEventUpgradeBatchSize);
		searchSourceBuilder.sort("id");
		searchSourceBuilder.trackTotalHits(false);

		Properties properties = _getProperties();

		String latestActivityId = properties.getProperty(
			ProjectIdThreadLocal.getProjectId(), "0");

		try {
			while (true) {
				searchSourceBuilder.searchAfter(
					new Object[] {latestActivityId});

				long startTime = System.currentTimeMillis();

				SearchResponse searchResponse =
					_faroInfoElasticsearchInvoker.search(
						"activities", searchSourceBuilder);

				long endTime = System.currentTimeMillis();

				SearchHits searchHits = searchResponse.getHits();

				SearchHit[] hits = searchHits.getHits();

				if (hits.length == 0) {
					break;
				}

				if (_log.isDebugEnabled()) {
					_log.debug(
						"It took " + (endTime - startTime) + "ms to retrieve " +
							hits.length + " activities.");
				}

				_setBatchSize(endTime - startTime, searchSourceBuilder);

				Stream<SearchHit> stream = Arrays.stream(hits);

				JSONArray jsonArray = new JSONArray(
					stream.parallel(
					).map(
						SearchHit::getSourceAsString
					).map(
						JSONObject::new
					).collect(
						Collectors.toList()
					));

				String projectId = ProjectIdThreadLocal.getProjectId();

				_boundedExecutor.runAsync(
					() -> upgradeActivities(jsonArray, projectId));

				JSONObject jsonObject = jsonArray.getJSONObject(
					jsonArray.length() - 1);

				latestActivityId = jsonObject.getString("id");

				properties.put(
					ProjectIdThreadLocal.getProjectId(), latestActivityId);

				_saveProperties(properties);
			}

			_boundedExecutor.awaitPendingTasks();
		}
		catch (RuntimeException runtimeException) {
			_log.error(runtimeException, runtimeException);

			throw runtimeException;
		}
	}

	protected void upgradeActivities(
		JSONArray activitiesJSONArray, String projectId) {

		try {
			ProjectIdThreadLocal.setProjectId(projectId);

			List<Map<String, Object>> events = new ArrayList<>();

			long startTime = System.currentTimeMillis();

			for (int i = 0; i < activitiesJSONArray.length(); i++) {
				JSONObject activityJSONObject =
					activitiesJSONArray.getJSONObject(i);

				AnalyticsEvent analyticsEvent = _createAnalyticsEvent(
					activityJSONObject);

				if (analyticsEvent == null) {
					continue;
				}

				_populateEventMap(
					activityJSONObject.getString("id"), analyticsEvent,
					activityJSONObject.getJSONObject("eventContext"),
					activityJSONObject.optJSONObject("eventProperties"), events,
					activityJSONObject.getString("sessionId"));

				if (events.size() == 1000) {
					_batchUpdateEvents(events, startTime);
				}
			}

			if (!events.isEmpty()) {
				_batchUpdateEvents(events, startTime);
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	private void _batchUpdateEvents(
		List<Map<String, Object>> events, long startTime) {

		int writes = events.size();

		try {
			InsertAllRequest.Builder builder = InsertAllRequest.newBuilder(
				_tableId);

			Stream<Map<String, Object>> stream = events.stream();

			stream.map(
				eventMap -> InsertAllRequest.RowToInsert.of(
					MapUtil.getString(eventMap, "id"), eventMap)
			).forEach(
				builder::addRow
			);

			InsertAllResponse insertAllResponse = _bigQuery.insertAll(
				builder.build());

			if (insertAllResponse.hasErrors()) {
				Map<Long, List<BigQueryError>> insertErrors =
					insertAllResponse.getInsertErrors();

				for (Map.Entry<Long, List<BigQueryError>> entry :
						insertErrors.entrySet()) {

					_log.error("Insert all response error: " + entry);
				}

				writes = writes - insertErrors.size();
			}
		}
		catch (BigQueryException bigQueryException) {
			_log.error("Unable to save events batch", bigQueryException);
		}
		finally {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"It took " + (System.currentTimeMillis() - startTime) +
						"ms to insert " + writes + " events");
			}

			events.clear();
		}
	}

	private AnalyticsEvent _createAnalyticsEvent(
		JSONObject activityJSONObject) {

		try {
			AnalyticsEvent analyticsEvent = new AnalyticsEvent();

			analyticsEvent.setApplicationId(
				activityJSONObject.getString("applicationId"));
			analyticsEvent.setChannelId(
				activityJSONObject.getString("channelId"));
			analyticsEvent.setContext(_getEventContext(activityJSONObject));
			analyticsEvent.setCreateDate(
				DateUtil.toUTCDate(activityJSONObject.getString("endTime")));
			analyticsEvent.setDataSourceId(
				activityJSONObject.getString("dataSourceId"));
			analyticsEvent.setEventDate(
				DateUtil.toUTCDate(activityJSONObject.getString("endTime")));

			JSONObject eventPropertiesJSONObject =
				activityJSONObject.optJSONObject("eventProperties");

			if (eventPropertiesJSONObject != null) {
				analyticsEvent.setEventProperties(
					_toSafeMap(eventPropertiesJSONObject.toMap()));
			}

			analyticsEvent.setId(activityJSONObject.getString("id"));
			analyticsEvent.setEventId(activityJSONObject.getString("eventId"));

			Individual individual = _individualDog.fetchIndividual(
				Long.valueOf(activityJSONObject.getString("ownerId")));

			analyticsEvent.setIndividualId(
				activityJSONObject.getString("ownerId"));

			analyticsEvent.setKnownIndividual(_isKnownIndividual(individual));
			analyticsEvent.setSegmentNames(
				_getSegmentNames(
					Long.valueOf(activityJSONObject.getString("channelId")),
					individual));
			analyticsEvent.setUserId(activityJSONObject.getString("userId"));

			return analyticsEvent;
		}
		catch (Exception exception) {
			_log.error("Unable to recreate Analytics Event", exception);

			return null;
		}
	}

	private Map<String, String> _getEventContext(
		JSONObject activityJSONObject) {

		JSONObject eventContextJSONObject = activityJSONObject.getJSONObject(
			"eventContext");

		return _toSafeMap(eventContextJSONObject.toMap());
	}

	private Properties _getProperties() {
		try (FileInputStream fileInputStream = new FileInputStream(
				_propertiesFile)) {

			Properties properties = new Properties();

			properties.load(fileInputStream);

			return properties;
		}
		catch (Exception exception) {
			return new Properties();
		}
	}

	private Set<String> _getSegmentNames(
		Long channelId, Individual individual) {

		if (individual == null) {
			return Collections.emptySet();
		}

		return new HashSet<>(
			_segmentDog.getSegmentNames(channelId, individual.getSegmentIds()));
	}

	@PostConstruct
	private void _init() {
		BigQueryOptions bigQueryOptions = BigQueryOptions.getDefaultInstance();

		_bigQuery = bigQueryOptions.getService();
	}

	private boolean _isKnownIndividual(Individual individual) {
		if (individual == null) {
			return false;
		}

		if (_fieldRepository.existsByNameAndOwnerId(
				"email", individual.getId())) {

			return true;
		}

		return false;
	}

	private void _populateEventMap(
		String activityId, AnalyticsEvent analyticsEvent,
		JSONObject contextJSONObject, JSONObject eventPropertiesJSONObject,
		List<Map<String, Object>> events, String sessionId) {

		Map<String, Object> eventsMap = new HashMap<>();

		eventsMap.put("activityId", Long.parseLong(activityId));
		eventsMap.put("applicationId", analyticsEvent.getApplicationId());

		Map<String, String> context = analyticsEvent.getContext();

		eventsMap.put("browserName", context.get("browserName"));
		eventsMap.put("canonicalUrl", context.get("canonicalUrl"));

		eventsMap.put(
			"channelId", Long.parseLong(analyticsEvent.getChannelId()));
		eventsMap.put("city", context.get("city"));
		eventsMap.put("contentLanguageId", context.get("contentLanguageId"));
		eventsMap.put("context", _toJSONPGobject(contextJSONObject));
		eventsMap.put("country", context.get("country"));
		eventsMap.put("createDate", analyticsEvent.getCreateDate());
		eventsMap.put(
			"dataSourceId", Long.parseLong(analyticsEvent.getDataSourceId()));
		eventsMap.put("description", context.get("description"));
		eventsMap.put("deviceType", context.get("deviceType"));
		eventsMap.put("eventDate", analyticsEvent.getEventDate());
		eventsMap.put("eventId", analyticsEvent.getEventId());
		eventsMap.put(
			"eventProperties", _toJSONPGobject(eventPropertiesJSONObject));

		List<String> eventPropertyNames = new ArrayList<>();
		List<String> eventPropertyValues = new ArrayList<>();

		if (eventPropertiesJSONObject != null) {
			eventPropertyNames = new ArrayList<>(
				eventPropertiesJSONObject.keySet());

			for (String eventPropertyName : eventPropertyNames) {
				eventPropertyValues.add(
					eventPropertiesJSONObject.getString(eventPropertyName));
			}
		}

		eventsMap.put("eventPropertyNames", eventPropertyNames);
		eventsMap.put("eventPropertyValues", eventPropertyValues);
		eventsMap.put("experienceId", context.get("experienceId"));
		eventsMap.put("id", analyticsEvent.getId());
		eventsMap.put(
			"individualId", Long.parseLong(analyticsEvent.getIndividualId()));
		eventsMap.put("keywords", context.get("keywords"));
		eventsMap.put("knownIndividual", analyticsEvent.isKnownIndividual());
		eventsMap.put("languageId", context.get("languageId"));
		eventsMap.put("platformName", context.get("platformName"));
		eventsMap.put("projectId", analyticsEvent.getProjectId());
		eventsMap.put(
			"projectTimeZoneId", analyticsEvent.getProjectTimeZoneId());
		eventsMap.put("referrer", context.get("referrer"));
		eventsMap.put("region", context.get("region"));
		eventsMap.put(
			"segmentNames", new ArrayList<>(analyticsEvent.getSegmentNames()));
		eventsMap.put("sessionId", sessionId);
		eventsMap.put("timezoneOffset", context.get("timezoneOffset"));
		eventsMap.put("title", context.get("title"));
		eventsMap.put("url", context.get("url"));
		eventsMap.put("userId", analyticsEvent.getUserId());
		eventsMap.put("variantId", context.get("variantId"));

		events.add(eventsMap);
	}

	private void _saveProperties(Properties properties) {
		try (FileOutputStream fileOutputStream = new FileOutputStream(
				_propertiesFile)) {

			properties.store(fileOutputStream, null);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	private void _setBatchSize(
		long newTime, SearchSourceBuilder searchSourceBuilder) {

		double newStatistics = newTime / (double)_customEventUpgradeBatchSize;

		if ((_customEventUpgradeBatchSize < 10000) &&
			(_currentStatistics > (newStatistics * 1.05))) {

			_customEventUpgradeBatchSize += 100;

			_updateBatchSize(newStatistics, searchSourceBuilder);
		}
		else if ((_customEventUpgradeBatchSize > 100) &&
				 (_currentStatistics < (newStatistics * 0.95))) {

			_customEventUpgradeBatchSize -= 100;

			_updateBatchSize(newStatistics, searchSourceBuilder);
		}
	}

	private PGobject _toJSONPGobject(JSONObject jsonObject) {
		try {
			PGobject pGobject = new PGobject();

			pGobject.setType("json");
			pGobject.setValue(jsonObject.toString());

			return pGobject;
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	private Map<String, String> _toSafeMap(Map<String, Object> jsonObjectMap) {
		Map<String, String> map = new HashMap<>();

		for (Map.Entry<String, Object> entry : jsonObjectMap.entrySet()) {
			map.put(entry.getKey(), StringUtil.get(entry.getValue()));
		}

		return map;
	}

	private void _updateBatchSize(
		double newStatistics, SearchSourceBuilder searchSourceBuilder) {

		_currentStatistics = newStatistics;

		searchSourceBuilder.size(_customEventUpgradeBatchSize);

		if (_log.isDebugEnabled()) {
			_log.debug("New batch size : " + _customEventUpgradeBatchSize);
		}
	}

	private static final Log _log = LogFactory.getLog(EventsUpgradeStep.class);

	private BigQuery _bigQuery;
	private final BoundedExecutor _boundedExecutor =
		BoundedExecutor.newBoundedExecutor(40, 30);
	private double _currentStatistics = 120;

	@Value("${osb.asah.custom.event.upgrade.batch.size:10000}")
	private int _customEventUpgradeBatchSize;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private FieldRepository _fieldRepository;

	@Autowired
	private IndividualDog _individualDog;

	private final File _propertiesFile = new File(
		"/temp/events-upgrade-step.properties");

	@Autowired
	private SegmentDog _segmentDog;

	private final TableId _tableId = TableId.of("osbasah", "event-import");

}