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
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.Event;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.repository.EventRepository;
import com.liferay.osb.asah.common.util.StringUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class CustomEventUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		Optional<Event> lastEventOptional =
			_eventRepository.findFirstByOrderByIdDesc();

		JSONArrayIterator.of(
			"activities", _faroInfoElasticsearchInvoker, null
		).setBatchSize(
			_customEventUpgradeBatchSize
		).setProcessJSONArrayUnsafeFunction(
			this::_upgradeActivities
		).setQueryBuilder(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.rangeQuery(
					"id"
				).gt(
					lastEventOptional.map(
						Event::getAnalyticsEventId
					).orElse(
						"0"
					)
				)
			).filter(
				QueryBuilders.existsQuery("sessionId")
			)
		).iterate();
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

	private Map<String, String> _toSafeMap(Map<String, Object> jsonObjectMap) {
		Map<String, String> map = new HashMap<>();

		for (Map.Entry<String, Object> entry : jsonObjectMap.entrySet()) {
			map.put(entry.getKey(), StringUtil.get(entry.getValue()));
		}

		return map;
	}

	private List<Event> _upgradeActivities(JSONArray activitiesJSONArray) {
		Map<String, List<AnalyticsEvent>> analyticsEventsMap = new HashMap<>();

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

			analyticsEventsMap.compute(
				activityJSONObject.getString("sessionId"),
				(sessionId, analyticsEvents) -> {
					if (analyticsEvents == null) {
						analyticsEvents = new ArrayList<>();
					}

					analyticsEvents.add(analyticsEvent);

					return analyticsEvents;
				});
		}

		try {
			return _eventStorageDog.store(analyticsEventsMap);
		}
		catch (Exception exception) {
			StringBuilder sb = new StringBuilder("Unable to store events ");

			analyticsEventsMap.forEach(
				(sessionId, analyticsEvents) -> analyticsEvents.forEach(
					analyticsEvent -> {
						sb.append(analyticsEvent.toJSON());
						sb.append(" ");
					}));
			_log.error(sb.toString(), exception);

			return null;
		}
	}

	private static final Log _log = LogFactory.getLog(
		CustomEventUpgradeStep.class);

	@Value("${osb.asah.custom.event.upgrade.batch.size:500}")
	private int _customEventUpgradeBatchSize;

	@Autowired
	private EventRepository _eventRepository;

	@Autowired
	private EventStorageDog _eventStorageDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}