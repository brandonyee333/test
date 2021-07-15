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

package com.liferay.osb.asah.stream.curator.bot.nanite.activity;

import com.github.benmanes.caffeine.cache.Cache;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.dog.ActivityGroupDog;
import com.liferay.osb.asah.common.dog.AssetDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.ActivityGroup;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.AssetKeyword;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.util.MapUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;
import com.liferay.osb.asah.stream.curator.bot.nanite.util.NaniteUtil;
import com.liferay.osb.asah.stream.curator.nlp.NLPUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import reactor.util.function.Tuple2;
import reactor.util.function.Tuple4;
import reactor.util.function.Tuples;

/**
 * @author Michael Bowerman
 */
@Component
public class ActivitiesNanite implements Nanite {

	@Override
	public String getCollectionName() {
		return "activities";
	}

	@Override
	public long getInterval() {
		return DateUtil.MINUTE;
	}

	@Override
	public void run() {
		try {
			while (true) {
				long start = System.currentTimeMillis();

				List<AnalyticsEvent> analyticsEvents =
					_messageSubscriber.pullMessages(
						_activitiesNanitePullMessagesSize,
						AnalyticsEvent::toAnalyticsEvent);

				if (analyticsEvents.isEmpty()) {
					break;
				}

				Stream<AnalyticsEvent> stream = analyticsEvents.stream();

				stream.collect(
					Collectors.groupingBy(AnalyticsEvent::getProjectId)
				).forEach(
					this::_run
				);

				if (_log.isInfoEnabled()) {
					Class<?> clazz = getClass();

					_log.info(
						String.format(
							"%s processed %d events in %d ms",
							clazz.getSimpleName(), analyticsEvents.size(),
							System.currentTimeMillis() - start));
				}
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
		finally {
			_invalidateCaches();
		}
	}

	private ActivityGroup _addActivityGroup(AnalyticsEvent analyticsEvent) {
		String channelId = analyticsEvent.getChannelId();
		String dataSourceId = analyticsEvent.getDataSourceId();

		LocalDateTime eventLocalDateTime = DateUtil.toLocalDateTime(
			analyticsEvent.getEventDate(), _timeZoneDog.getZoneId());

		Date dayDate = DateUtil.toUTCDate(
			eventLocalDateTime.with(LocalTime.MIDNIGHT));

		String userId = analyticsEvent.getUserId();

		Tuple4<Long, Long, Date, String> activityGroupTuple4 = Tuples.of(
			Long.valueOf(channelId), Long.valueOf(dataSourceId), dayDate,
			userId);

		ActivityGroup activityGroup = _activityGroups.getIfPresent(
			activityGroupTuple4);

		if (activityGroup == null) {
			activityGroup = _activityGroupDog.fetchActivityGroup(
				"BROWSE", Long.valueOf(channelId), Long.valueOf(dataSourceId),
				dayDate, userId);
		}

		if (activityGroup != null) {
			activityGroup.setEndDate(analyticsEvent.getEventDate());
			activityGroup.setEndLocalDateTime(eventLocalDateTime);

			_activityGroupDog.updatedActivityGroup(
				activityGroup.getId(), analyticsEvent.getEventDate(),
				eventLocalDateTime);

			_activityGroups.put(activityGroupTuple4, activityGroup);

			return activityGroup;
		}

		activityGroup = _activityGroupDog.addActivityGroup(
			"BROWSE", Long.valueOf(channelId), Long.valueOf(dataSourceId),
			dayDate, analyticsEvent.getEventDate(), eventLocalDateTime,
			_getOwnerId(analyticsEvent), analyticsEvent.getEventDate(),
			eventLocalDateTime, userId);

		_activityGroups.put(activityGroupTuple4, activityGroup);

		return activityGroup;
	}

	private void _addActivityJSONArray(JSONArray activityJSONArray) {
		String projectId = ProjectIdThreadLocal.getProjectId();

		for (int i = 0; i < activityJSONArray.length(); i++) {
			JSONObject activityJSONObject = activityJSONArray.getJSONObject(i);

			if (_faroInfoActivityDog.isActivity(
					activityJSONObject.getString("applicationId"),
					activityJSONObject.getString("eventId"))) {

				_messageBus.sendMessage(
					Channel.ACTIVE_INDIVIDUAL_IDS,
					JSONUtil.put(
						"channelId", activityJSONObject.getString("channelId")
					).put(
						"ownerId", activityJSONObject.getString("ownerId")
					).put(
						"projectId", projectId
					).toString());
			}
		}

		_faroInfoActivityDog.addActivity(activityJSONArray);
	}

	private JSONObject _getActivityJSONObject(AnalyticsEvent analyticsEvent) {
		try {
			Map<String, String> context = analyticsEvent.getContext();

			String dataSourceAssetPK = _getDataSourceAssetPK(
				analyticsEvent, context);

			JSONObject eventPropertiesJSONObject =
				_getEventPropertiesJSONObject(analyticsEvent);

			Asset asset = _getAsset(
				analyticsEvent, dataSourceAssetPK, eventPropertiesJSONObject);

			if (asset == null) {
				return null;
			}

			String eventId = analyticsEvent.getEventId();

			JSONObject objectJSONObject = new JSONObject();

			objectJSONObject.put("dataSourceAssetPK", dataSourceAssetPK);

			if (context.containsKey("canonicalUrl")) {
				objectJSONObject.put(
					"canonicalUrl", context.get("canonicalUrl"));
			}

			String assetId = String.valueOf(asset.getId());

			objectJSONObject.put("id", assetId);

			objectJSONObject.put(
				"name", _getTitle(analyticsEvent, eventPropertiesJSONObject));
			objectJSONObject.put("objectType", asset.getAssetType());
			objectJSONObject.put("url", MapUtil.getString(context, "url"));

			String applicationId = analyticsEvent.getApplicationId();

			if (applicationId.equals("Comment")) {
				applicationId = objectJSONObject.getString("objectType");

				eventId = "comment" + StringUtils.capitalize(eventId);
			}

			String eventDateString = DateUtil.toUTCString(
				analyticsEvent.getEventDate());

			LocalDateTime eventLocalDateTime = DateUtil.toLocalDateTime(
				analyticsEvent.getEventDate(), _timeZoneDog.getZoneId());

			String dayUTCString = DateUtil.toUTCString(
				eventLocalDateTime.with(LocalTime.MIDNIGHT));

			ActivityGroup activityGroup = _addActivityGroup(analyticsEvent);

			JSONObject activityJSONObject = JSONUtil.put(
				"activityKey", applicationId + "#" + eventId + "#" + assetId
			).put(
				"activityType", "BROWSE"
			).put(
				"applicationId", applicationId
			).put(
				"channelId", analyticsEvent.getChannelId()
			).put(
				"dataSourceId", analyticsEvent.getDataSourceId()
			).put(
				"day", dayUTCString
			).put(
				"endTime", eventDateString
			).put(
				"endTimeLocal", eventLocalDateTime
			).put(
				"eventContext", new JSONObject(analyticsEvent.getContext())
			).put(
				"eventId", eventId
			).put(
				"eventProperties", eventPropertiesJSONObject
			).put(
				"groupId", String.valueOf(activityGroup.getId())
			).put(
				"object", objectJSONObject
			).put(
				"ownerId", String.valueOf(activityGroup.getOwnerId())
			).put(
				"startTime", eventDateString
			).put(
				"startTimeLocal", eventLocalDateTime
			).put(
				"userId", analyticsEvent.getUserId()
			);

			String sessionId = _getSessionId(
				analyticsEvent, activityGroup.getOwnerId());

			if (sessionId != null) {
				activityJSONObject.put("sessionId", sessionId);
			}

			if (Objects.equals(analyticsEvent.getEventId(), "formViewed")) {
				TreeMap<Date, JSONObject> formViewedActivity =
					_formViewedActivies.getIfPresent(
						analyticsEvent.getUserId());

				if (formViewedActivity == null) {
					formViewedActivity = new TreeMap<>();
				}

				formViewedActivity.put(
					analyticsEvent.getEventDate(), activityJSONObject);

				_formViewedActivies.put(
					analyticsEvent.getUserId(), formViewedActivity);
			}

			return activityJSONObject;
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		return null;
	}

	private Asset _getAsset(
		AnalyticsEvent analyticsEvent, String dataSourceAssetPK,
		JSONObject eventPropertiesJSONObject) {

		Tuple2<String, String> assetTuple2 = Tuples.of(
			dataSourceAssetPK, analyticsEvent.getDataSourceId());

		Asset asset = _assets.getIfPresent(assetTuple2);

		if (asset == null) {
			asset = _assetDog.fetchAsset(
				dataSourceAssetPK,
				Long.valueOf(analyticsEvent.getDataSourceId()));

			if (asset != null) {
				_assets.put(assetTuple2, asset);
			}
		}

		if (asset != null) {
			Set<Long> channelIds = asset.getChannelIds();

			Long analyticsEventChannelId = Long.valueOf(
				analyticsEvent.getChannelId());

			boolean updated = false;

			if (channelIds.add(analyticsEventChannelId)) {
				asset.setChannelIds(channelIds);

				updated = true;
			}

			if (Objects.equals(analyticsEvent.getApplicationId(), "Page")) {
				if (updated) {
					_assets.put(assetTuple2, asset);

					Asset newAsset = asset;

					_assetDog.updateAsset(newAsset);
				}

				return asset;
			}

			String title = _getEventPropertiesTitle(eventPropertiesJSONObject);

			if (StringUtils.isNotEmpty(title) &&
				!Objects.equals(asset.getTitle(), title)) {

				asset.setTitle(title);

				updated = true;
			}

			if (updated) {
				_assets.put(assetTuple2, asset);

				Asset newAsset = asset;

				_assetDog.updateAsset(newAsset);
			}

			return asset;
		}

		if (Objects.equals(analyticsEvent.getApplicationId(), "Comment")) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to get asset for comment analytics event with " +
						"data source PK " + dataSourceAssetPK + " and data " +
							"source ID " + analyticsEvent.getDataSourceId());
			}

			return null;
		}

		Map<String, String> context = analyticsEvent.getContext();

		asset = _assetDog.addAsset(
			_getAssetKeywords(analyticsEvent.getApplicationId(), context),
			analyticsEvent.getApplicationId(),
			MapUtil.getString(context, "canonicalUrl"),
			SetUtil.of(Long.valueOf(analyticsEvent.getChannelId())),
			dataSourceAssetPK, Long.valueOf(analyticsEvent.getDataSourceId()),
			MapUtil.getString(context, "description"),
			_getTitle(analyticsEvent, eventPropertiesJSONObject),
			MapUtil.getString(context, "url"));

		_assets.put(assetTuple2, asset);

		return asset;
	}

	private Set<AssetKeyword> _getAssetKeywords(
		String applicationId, Map<String, String> context) {

		if (!Objects.equals(applicationId, "Page")) {
			return null;
		}

		try {
			CompletableFuture<Set<AssetKeyword>> descriptionCompletableFuture =
				CompletableFuture.supplyAsync(
					() -> _getAssetKeywords(
						MapUtil.getString(context, "description"),
						"description"));

			CompletableFuture<Set<AssetKeyword>> titleCompletableFuture =
				CompletableFuture.supplyAsync(
					() -> _getAssetKeywords(
						MapUtil.getString(context, "title"), "title"));

			Set<AssetKeyword> keywords = new HashSet<>(
				_getAssetKeywords(
					MapUtil.getString(context, "keywords"), "keywords"));

			keywords.addAll(descriptionCompletableFuture.get());
			keywords.addAll(titleCompletableFuture.get());

			return keywords;
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	private Set<AssetKeyword> _getAssetKeywords(String text, String type) {
		if (StringUtils.isEmpty(text)) {
			return Collections.emptySet();
		}

		Tuple2<String, String> keywordTuple2 = Tuples.of(text, type);

		Set<AssetKeyword> assetKeywords = _assetKeywords.getIfPresent(
			keywordTuple2);

		if (assetKeywords != null) {
			return assetKeywords;
		}

		Stream<String> stream = Stream.of(text.split(","));

		if (type.equals("keyword")) {
			assetKeywords = stream.map(
				String::toLowerCase
			).map(
				keyword -> new AssetKeyword(keyword, type)
			).collect(
				Collectors.toSet()
			);
		}
		else {
			assetKeywords = stream.filter(
				NLPUtil::isEnglish
			).map(
				NLPUtil::getKeywords
			).flatMap(
				Collection::stream
			).map(
				String::toLowerCase
			).map(
				keyword -> new AssetKeyword(keyword, type)
			).collect(
				Collectors.toSet()
			);
		}

		_assetKeywords.put(keywordTuple2, assetKeywords);

		return assetKeywords;
	}

	private String _getDataSourceAssetPK(
		AnalyticsEvent analyticsEvent, Map<String, String> context) {

		Map<String, String> eventProperties =
			analyticsEvent.getEventProperties();

		String dataSourceAssetPKFieldName = _dataSourceAssetPKFieldNames.get(
			analyticsEvent.getApplicationId());

		if (eventProperties != null) {
			String dataSourceAssetPK = eventProperties.get(
				dataSourceAssetPKFieldName);

			if (dataSourceAssetPK != null) {
				return dataSourceAssetPK;
			}
		}

		String dataSourceAssetPK = MapUtil.getString(
			context, dataSourceAssetPKFieldName);

		if (dataSourceAssetPK != null) {
			return dataSourceAssetPK;
		}

		throw new IllegalArgumentException(
			"No data source asset PK for analytics event " +
				analyticsEvent.toJSON());
	}

	private JSONObject _getEventPropertiesJSONObject(
		AnalyticsEvent analyticsEvent) {

		JSONObject eventPropertiesJSONObject = new JSONObject();

		Map<String, String> eventProperties =
			analyticsEvent.getEventProperties();

		for (String eventPropertyKey : _EVENT_PROPERTY_KEYS) {
			String value = eventProperties.get(eventPropertyKey);

			if (value != null) {
				eventPropertiesJSONObject.put(eventPropertyKey, value);
			}
		}

		String pageViewActivityId = _getPageViewActivityId(analyticsEvent);

		if (pageViewActivityId != null) {
			eventPropertiesJSONObject.put(
				"pageViewActivityId", pageViewActivityId);
		}

		if (Objects.equals(analyticsEvent.getEventId(), "formSubmitted")) {
			JSONObject formViewedActivityJSONObject = null;

			TreeMap<Date, JSONObject> formViewedEventProperties =
				_formViewedActivies.getIfPresent(analyticsEvent.getUserId());

			if (formViewedEventProperties != null) {
				Map.Entry<Date, JSONObject> formViewedEventPropertiesEntry =
					formViewedEventProperties.floorEntry(
						analyticsEvent.getEventDate());

				if (formViewedEventPropertiesEntry != null) {
					formViewedActivityJSONObject =
						formViewedEventPropertiesEntry.getValue();
				}
			}

			if (formViewedActivityJSONObject == null) {
				formViewedActivityJSONObject =
					_faroInfoActivityDog.fetchLatestFormViewedActivity(
						analyticsEvent.getEventDate(),
						analyticsEvent.getUserId());

				if (formViewedActivityJSONObject != null) {
					if (formViewedEventProperties == null) {
						formViewedEventProperties = new TreeMap<>();
					}

					formViewedEventProperties.put(
						DateUtil.toUTCDate(
							formViewedActivityJSONObject.getString("endTime")),
						formViewedActivityJSONObject);

					_formViewedActivies.put(
						analyticsEvent.getUserId(), formViewedEventProperties);
				}
			}

			if (formViewedActivityJSONObject != null) {
				JSONObject formViewedActivityEventPropertiesJSONObject =
					formViewedActivityJSONObject.getJSONObject(
						"eventProperties");

				eventPropertiesJSONObject.put(
					"duration",
					String.valueOf(
						DateUtil.getDeltaMilliseconds(
							formViewedActivityJSONObject.getString("endTime"),
							DateUtil.toUTCString(
								analyticsEvent.getEventDate())))
				).put(
					"title",
					formViewedActivityEventPropertiesJSONObject.getString(
						"title")
				);
			}
		}

		return eventPropertiesJSONObject;
	}

	private String _getEventPropertiesTitle(
		JSONObject eventPropertiesJSONObject) {

		String title = eventPropertiesJSONObject.optString("title");

		if (!StringUtils.isEmpty(title)) {
			return title;
		}

		return null;
	}

	private Long _getOwnerId(AnalyticsEvent analyticsEvent) {
		String ownerId = analyticsEvent.getIndividualId();

		if ((ownerId != null) && (_ownerIds.getIfPresent(ownerId) != null)) {
			return Long.valueOf(ownerId);
		}

		if ((ownerId != null) &&
			_faroInfoIndividualDog.existsIndividual(ownerId)) {

			_ownerIds.put(ownerId, Long.valueOf(ownerId));

			return Long.valueOf(ownerId);
		}

		JSONObject individualJSONObject =
			_faroInfoIndividualDog.fetchIndividualJSONObject(
				Long.valueOf(analyticsEvent.getDataSourceId()),
				analyticsEvent.getUserId());

		if (individualJSONObject != null) {
			_ownerIds.put(
				individualJSONObject.getString("id"),
				individualJSONObject.getLong("id"));

			return individualJSONObject.getLong("id");
		}

		throw new IllegalStateException(
			"Unable to find individual for owner ID " + ownerId +
				" or user ID " + analyticsEvent.getUserId());
	}

	private String _getPageViewActivityId(AnalyticsEvent analyticsEvent) {
		if (Objects.equals(analyticsEvent.getApplicationId(), "Page") &&
			Objects.equals(analyticsEvent.getEventId(), "pageViewed")) {

			String pageViewActivityId = String.valueOf(UUID.randomUUID());

			_pageViewActivityIds.put(
				analyticsEvent.getUserId(), pageViewActivityId);

			return pageViewActivityId;
		}

		String pageViewActivityId = _pageViewActivityIds.getIfPresent(
			analyticsEvent.getUserId());

		if (pageViewActivityId != null) {
			return pageViewActivityId;
		}

		pageViewActivityId = _faroInfoActivityDog.fetchLatestPageViewActivityId(
			analyticsEvent.getUserId());

		if (pageViewActivityId != null) {
			_pageViewActivityIds.put(
				analyticsEvent.getUserId(), pageViewActivityId);
		}

		return pageViewActivityId;
	}

	private String _getSessionId(AnalyticsEvent analyticsEvent, Long ownerId) {
		JSONObject userSessionJSONObject =
			_cerebroInfoElasticsearchInvoker.fetch(
				"user-sessions",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"channelId", analyticsEvent.getChannelId())
				).filter(
					QueryBuilders.termQuery(
						"dataSourceId", analyticsEvent.getDataSourceId())
				).filter(
					QueryBuilders.rangeQuery(
						"firstEventDate"
					).lte(
						DateUtil.toUTCString(analyticsEvent.getEventDate())
					)
				).filter(
					QueryBuilders.termQuery(
						"individualId", String.valueOf(ownerId))
				).filter(
					QueryBuilders.rangeQuery(
						"lastEventDate"
					).gte(
						DateUtil.toUTCString(analyticsEvent.getEventDate())
					)
				).filter(
					QueryBuilders.termQuery(
						"userId", analyticsEvent.getUserId())
				),
				null, null, "id");

		if (userSessionJSONObject == null) {
			return null;
		}

		return userSessionJSONObject.getString("id");
	}

	private String _getTitle(
		AnalyticsEvent analyticsEvent, JSONObject eventPropertiesJSONObject) {

		if (!Objects.equals(analyticsEvent.getApplicationId(), "Page")) {
			String title = _getEventPropertiesTitle(eventPropertiesJSONObject);

			if (title != null) {
				return title;
			}
		}

		return MapUtil.getString(analyticsEvent.getContext(), "title");
	}

	private void _invalidateCaches() {
		_activityGroups.invalidateAll();
		_assetKeywords.invalidateAll();
		_assets.invalidateAll();
		_formViewedActivies.invalidateAll();
		_ownerIds.invalidateAll();
		_pageViewActivityIds.invalidateAll();
	}

	private void _run(String projectId, List<AnalyticsEvent> analyticsEvents) {
		try {
			ProjectIdThreadLocal.setProjectId(projectId);

			Stream<AnalyticsEvent> analyticsEventsStream =
				analyticsEvents.stream();

			_addActivityJSONArray(
				analyticsEventsStream.distinct(
				).map(
					this::_getActivityJSONObject
				).filter(
					Objects::nonNull
				).collect(
					Collector.of(JSONArray::new, JSONArray::put, JSONArray::put)
				));
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
		finally {
			ProjectIdThreadLocal.remove();
		}
	}

	private static final String[] _EVENT_PROPERTY_KEYS = {
		"depth", "title", "viewDuration"
	};

	private static final Log _log = LogFactory.getLog(ActivitiesNanite.class);

	private static final Map<String, String> _dataSourceAssetPKFieldNames =
		new HashMap<String, String>() {
			{
				put("Blog", "entryId");
				put("Comment", "classPK");
				put("Custom", "assetId");
				put("Document", "fileEntryId");
				put("Form", "formId");
				put("Page", "canonicalUrl");
				put("WebContent", "articleId");
			}
		};

	@Value("${osb.asah.activities.nanite.pull.messages.size:50}")
	private int _activitiesNanitePullMessagesSize;

	@Autowired
	private ActivityGroupDog _activityGroupDog;

	private final Cache<Tuple4<Long, Long, Date, String>, ActivityGroup>
		_activityGroups = NaniteUtil.createCache();

	@Autowired
	private AssetDog _assetDog;

	private final Cache<Tuple2<String, String>, Set<AssetKeyword>>
		_assetKeywords = NaniteUtil.createCache();
	private final Cache<Tuple2<String, String>, Asset> _assets =
		NaniteUtil.createCache();

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

	@Autowired
	private FaroInfoIndividualDog _faroInfoIndividualDog;

	private final Cache<String, TreeMap<Date, JSONObject>> _formViewedActivies =
		NaniteUtil.createCache();

	@Autowired
	private MessageBus _messageBus;

	@MessageSubscriber.Autowired(channel = Channel.ANALYTICS_EVENTS_ACTIVITY)
	private MessageSubscriber _messageSubscriber;

	private final Cache<String, Long> _ownerIds = NaniteUtil.createCache();
	private final Cache<String, String> _pageViewActivityIds =
		NaniteUtil.createCache();

	@Autowired
	private TimeZoneDog _timeZoneDog;

}