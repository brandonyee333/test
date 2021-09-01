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

package com.liferay.osb.asah.upgrade.v3_0_0;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.EventStorageDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.Event;
import com.liferay.osb.asah.common.entity.EventAttribute;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.util.StringUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marcellus Tavares
 */
@Component
public class CustomEventUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

		searchSourceBuilder.fetchSource(
			new String[] {
				"applicationId", "channelId", "dataSourceId", "endTime",
				"eventContext", "eventId", "eventProperties", "id",
				"object.canonicalUrl", "object.name", "object.url", "ownerId",
				"sessionId", "userId"
			},
			null);
		searchSourceBuilder.query(QueryBuilders.existsQuery("sessionId"));
		searchSourceBuilder.size(_customEventUpgradeBatchSize);
		searchSourceBuilder.sort("id");
		searchSourceBuilder.trackTotalHits(false);

		String projectId = ProjectIdThreadLocal.getProjectId();

		Optional<Event> eventOptional = _getEventOptional();

		String id = eventOptional.map(
			Event::getAnalyticsEventId
		).orElse(
			"0"
		);

		_eventId = new AtomicLong(
			eventOptional.map(
				Event::getId
			).orElse(
				0L
			));

		while (true) {
			searchSourceBuilder.searchAfter(new Object[] {id});

			SearchResponse searchResponse =
				_faroInfoElasticsearchInvoker.search(
					"activities", searchSourceBuilder);

			SearchHits searchHits = searchResponse.getHits();

			SearchHit[] hits = searchHits.getHits();

			if (hits.length == 0) {
				while (!_completableFutures.isEmpty()) {
					_completableFutures.removeIf(CompletableFuture::isDone);
				}

				break;
			}

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

			_completableFutures.add(
				CompletableFuture.runAsync(
					() -> _upgradeActivities(jsonArray, projectId),
					_executorService));

			JSONObject jsonObject = jsonArray.getJSONObject(
				jsonArray.length() - 1);

			id = jsonObject.getString("id");
		}
	}

	private Map<String, String> _getEventContext(
		JSONObject activityJSONObject) {

		JSONObject eventContextJSONObject = activityJSONObject.optJSONObject(
			"eventContext");

		if (eventContextJSONObject != null) {
			return _toSafeMap(eventContextJSONObject.toMap());
		}

		Map<String, String> eventContext = new HashMap<>();

		JSONObject objectJSONObject = activityJSONObject.getJSONObject(
			"object");

		eventContext.put(
			"canonicalUrl", objectJSONObject.getString("canonicalUrl"));
		eventContext.put("url", objectJSONObject.getString("url"));

		if (Objects.equals(
				activityJSONObject.getString("applicationId"), "Page")) {

			eventContext.put("title", objectJSONObject.getString("name"));
		}

		return eventContext;
	}

	private Optional<Event> _getEventOptional() {
		JdbcTemplate jdbcTemplate =
			_namedParameterJdbcTemplate.getJdbcTemplate();

		try {
			return Optional.ofNullable(
				jdbcTemplate.queryForObject(
					_SQL_SELECT_EVENT,
					(rs, rowNum) -> {
						Event event = new Event();

						event.setAnalyticsEventId(
							rs.getString("analyticsEventId"));
						event.setId(rs.getLong("id"));

						return event;
					}));
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception, exception);
			}

			return Optional.empty();
		}
	}

	@PostConstruct
	private void _init() {
		_executorService = Executors.newFixedThreadPool(
			_customEventUpgradeThreadPool);

		_namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
			_dataSource);
	}

	private void _populateMaps(
		AnalyticsEvent analyticsEvent,
		List<Map<String, Object>> eventAttributes,
		List<Map<String, Object>> events, String sessionId) {

		EventDefinition eventDefinition = _eventStorageDog.getEventDefinition(
			analyticsEvent);

		if (!eventDefinition.isBlocked()) {
			Long eventId = _eventId.incrementAndGet();

			Map<String, Object> eventsMap = new HashMap<>();

			eventsMap.put("analyticsEventId", analyticsEvent.getId());
			eventsMap.put("applicationId", analyticsEvent.getApplicationId());
			eventsMap.put(
				"channelId", Long.valueOf(analyticsEvent.getChannelId()));
			eventsMap.put("createDate", analyticsEvent.getCreateDate());
			eventsMap.put(
				"dataSourceId", Long.valueOf(analyticsEvent.getDataSourceId()));
			eventsMap.put("eventDate", analyticsEvent.getEventDate());
			eventsMap.put("eventDefinitionId", eventDefinition.getId());
			eventsMap.put("id", eventId);
			eventsMap.put(
				"individualId",
				Optional.ofNullable(
					analyticsEvent.getIndividualId()
				).map(
					Long::valueOf
				).orElse(
					null
				));
			eventsMap.put("sessionId", sessionId);
			eventsMap.put("userId", analyticsEvent.getUserId());

			events.add(eventsMap);

			for (EventAttribute eventAttribute :
					_eventStorageDog.getEventAttributes(
						analyticsEvent, eventDefinition.getId())) {

				Map<String, Object> eventAttributesMap = new HashMap<>();

				eventAttributesMap.put(
					"eventAttributeDefinitionId",
					eventAttribute.getEventAttributeDefinitionId());
				eventAttributesMap.put(
					"eventDate", eventAttribute.getEventDate());
				eventAttributesMap.put("eventId", eventId);
				eventAttributesMap.put("value", eventAttribute.getValue());

				eventAttributes.add(eventAttributesMap);
			}
		}
	}

	private Map<String, String> _toSafeMap(Map<String, Object> jsonObjectMap) {
		Map<String, String> map = new HashMap<>();

		for (Map.Entry<String, Object> entry : jsonObjectMap.entrySet()) {
			map.put(entry.getKey(), StringUtil.get(entry.getValue()));
		}

		return map;
	}

	@Transactional
	private void _upgradeActivities(
		JSONArray activitiesJSONArray, String projectId) {

		List<Map<String, Object>> eventAttributes = new ArrayList<>();
		List<Map<String, Object>> events = new ArrayList<>();

		ProjectIdThreadLocal.setProjectId(projectId);

		for (int i = 0; i < activitiesJSONArray.length(); i++) {
			JSONObject activityJSONObject = activitiesJSONArray.getJSONObject(
				i);

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
				activityJSONObject.getJSONObject("eventProperties");

			analyticsEvent.setEventProperties(
				_toSafeMap(eventPropertiesJSONObject.toMap()));

			analyticsEvent.setId(activityJSONObject.getString("id"));
			analyticsEvent.setEventId(activityJSONObject.getString("eventId"));
			analyticsEvent.setIndividualId(
				activityJSONObject.getString("ownerId"));
			analyticsEvent.setUserId(activityJSONObject.getString("userId"));

			_populateMaps(
				analyticsEvent, eventAttributes, events,
				activityJSONObject.getString("sessionId"));
		}

		try {
			_namedParameterJdbcTemplate.batchUpdate(
				_SQL_INSERT_EVENT,
				SqlParameterSourceUtils.createBatch(
					events.toArray(new HashMap[0])));

			_namedParameterJdbcTemplate.batchUpdate(
				_SQL_INSERT_EVENT_ATTRIBUTES,
				SqlParameterSourceUtils.createBatch(
					eventAttributes.toArray(new HashMap[0])));
		}
		catch (Exception exception) {
			StringBuilder sb = new StringBuilder("Unable to store events ");

			events.forEach(
				event -> sb.append(event.get("analyticsEventId") + ", "));

			_log.error(sb.toString(), exception);
		}		
	}

	private static final String _SQL_INSERT_EVENT =
		"INSERT INTO event (id, analyticsEventId, applicationId, channelId, " +
			"createDate, dataSourceId, eventDate, eventDefinitionId, " +
				"individualId, sessionId, userId) VALUES (:id, " +
					":analyticsEventId, :applicationId, :channelId, " +
						":createDate, :dataSourceId, :eventDate, " +
							":eventDefinitionId, :individualId, :sessionId, " +
								":userId) ON CONFLICT DO NOTHING";

	private static final String _SQL_INSERT_EVENT_ATTRIBUTES =
		"INSERT INTO eventattribute (eventAttributeDefinitionId, eventDate, " +
			"eventId, value) VALUES (:eventAttributeDefinitionId, " +
				":eventDate, :eventId, :value) ON CONFLICT DO NOTHING";

	private static final String _SQL_SELECT_EVENT =
		"SELECT analyticsEventId, id FROM event ORDER BY id DESC LIMIT 1";

	private static final Log _log = LogFactory.getLog(
		CustomEventUpgradeStep.class);

	private final List<CompletableFuture<Void>> _completableFutures =
		new ArrayList<>();

	@Value("${osb.asah.custom.event.upgrade.batch.size:10000}")
	private int _customEventUpgradeBatchSize;

	@Value("${osb.asah.custom.event.upgrade.thread.pool:10}")
	private int _customEventUpgradeThreadPool;

	@Autowired
	private DataSource _dataSource;

	private AtomicLong _eventId;

	@Autowired
	private EventStorageDog _eventStorageDog;

	private ExecutorService _executorService;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	private NamedParameterJdbcTemplate _namedParameterJdbcTemplate;

}