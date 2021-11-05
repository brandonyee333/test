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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.dog.ActivityGroupDog;
import com.liferay.osb.asah.common.dog.AssetDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.ActivityGroup;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.AssetKeyword;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.lock.KeyReentrantLock;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.MapUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;
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
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import reactor.util.function.Tuple2;
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
			_run();
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		try {
			_semaphore.acquire(_activitiesNaniteConcurrentTasksLimit);
		}
		catch (InterruptedException interruptedException) {
			_log.error(interruptedException, interruptedException);
		}
		finally {
			_semaphore.release(_activitiesNaniteConcurrentTasksLimit);
		}
	}

	private ActivityGroup _addActivityGroup(AnalyticsEvent analyticsEvent) {
		String userId = analyticsEvent.getUserId();

		ReentrantLock reentrantLock = KeyReentrantLock.getReentrantLock(
			getClass(), ProjectIdThreadLocal.getProjectId());

		try {
			reentrantLock.lock();

			String channelId = analyticsEvent.getChannelId();
			String dataSourceId = analyticsEvent.getDataSourceId();

			LocalDateTime eventLocalDateTime = DateUtil.toLocalDateTime(
				analyticsEvent.getEventDate(), _timeZoneDog.getZoneId());

			Date dayDate = DateUtil.toUTCDate(
				eventLocalDateTime.with(LocalTime.MIDNIGHT));

			ActivityGroup activityGroup = _activityGroupDog.fetchActivityGroup(
				"BROWSE", Long.valueOf(channelId), Long.valueOf(dataSourceId),
				dayDate, userId);

			if (activityGroup != null) {
				return _activityGroupDog.updatedActivityGroup(
					activityGroup.getId(), analyticsEvent.getEventDate(),
					eventLocalDateTime);
			}

			return _activityGroupDog.addActivityGroup(
				"BROWSE", Long.valueOf(channelId), Long.valueOf(dataSourceId),
				dayDate, analyticsEvent.getEventDate(), eventLocalDateTime,
				_getOwnerId(analyticsEvent), analyticsEvent.getEventDate(),
				eventLocalDateTime, userId);
		}
		finally {
			reentrantLock.unlock();
		}
	}

	private void _addActivityJSONArray(
		JSONArray activityJSONArray, String projectId) {

		for (int i = 0; i < activityJSONArray.length(); i++) {
			JSONObject activityJSONObject = activityJSONArray.getJSONObject(i);

			_sendActivityMessage(activityJSONObject, projectId);
		}

		_faroInfoActivityDog.addActivity(activityJSONArray);
	}

	private void _addActivityJSONObject(
		JSONObject activityJSONObject, String projectId) {

		_sendActivityMessage(activityJSONObject, projectId);

		_faroInfoActivityDog.addActivity(activityJSONObject);
	}

	@PreDestroy
	private void _destroy() {
		_reentrantLock.lock();

		_executorService.shutdown();

		try {
			if (!_executorService.awaitTermination(1, TimeUnit.MINUTES)) {
				_executorService.shutdownNow();
			}
		}
		catch (InterruptedException interruptedException) {
			_log.error(
				"Interrupted while waiting for termination of executor",
				interruptedException);
		}
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

		ReentrantLock reentrantLock = KeyReentrantLock.getReentrantLock(
			getClass(), ProjectIdThreadLocal.getProjectId());

		try {
			reentrantLock.lock();

			Asset asset = _assetDog.fetchAsset(
				dataSourceAssetPK,
				Long.valueOf(analyticsEvent.getDataSourceId()));

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
						_assetDog.updateAsset(asset);
					}

					return asset;
				}

				String title = _getEventPropertiesTitle(
					eventPropertiesJSONObject);

				if (StringUtils.isNotEmpty(title) &&
					!Objects.equals(asset.getTitle(), title)) {

					asset.setTitle(title);

					updated = true;
				}

				if (updated) {
					_assetDog.updateAsset(asset);
				}

				return asset;
			}

			if (Objects.equals(analyticsEvent.getApplicationId(), "Comment")) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to get asset for comment analytics event " +
							"with data source PK " + dataSourceAssetPK +
								" and data source ID " +
									analyticsEvent.getDataSourceId());
				}

				return null;
			}

			Map<String, String> context = analyticsEvent.getContext();

			return _assetDog.addAsset(
				_getAssetKeywords(analyticsEvent.getApplicationId(), context),
				analyticsEvent.getApplicationId(),
				MapUtil.getString(context, "canonicalUrl"),
				SetUtil.of(Long.valueOf(analyticsEvent.getChannelId())),
				dataSourceAssetPK,
				Long.valueOf(analyticsEvent.getDataSourceId()),
				MapUtil.getString(context, "description"),
				_getTitle(analyticsEvent, eventPropertiesJSONObject),
				MapUtil.getString(context, "url"));
		}
		finally {
			reentrantLock.unlock();
		}
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

	@Cacheable
	private Set<AssetKeyword> _getAssetKeywords(String text, String type) {
		if (StringUtils.isEmpty(text)) {
			return Collections.emptySet();
		}

		Stream<String> stream = Stream.of(text.split(","));

		if (type.equals("keyword")) {
			return stream.map(
				String::toLowerCase
			).map(
				keyword -> new AssetKeyword(keyword, type)
			).collect(
				Collectors.toSet()
			);
		}

		return stream.filter(
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
			JSONObject formViewedActivityJSONObject =
				_faroInfoActivityDog.fetchLatestFormViewedActivity(
					analyticsEvent.getEventDate(), analyticsEvent.getUserId());

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

		if ((ownerId != null) &&
			_individualDog.existsById(Long.valueOf(ownerId))) {

			return Long.valueOf(ownerId);
		}

		Individual individual = _individualDog.fetchIndividual(
			Long.valueOf(analyticsEvent.getDataSourceId()),
			analyticsEvent.getUserId());

		if (individual != null) {
			return individual.getId();
		}

		throw new IllegalStateException(
			"Unable to find individual for owner ID " + ownerId +
				" or user ID " + analyticsEvent.getUserId());
	}

	private String _getPageViewActivityId(AnalyticsEvent analyticsEvent) {
		if (Objects.equals(analyticsEvent.getApplicationId(), "Page") &&
			Objects.equals(analyticsEvent.getEventId(), "pageViewed")) {

			return String.valueOf(UUID.randomUUID());
		}

		return _faroInfoActivityDog.fetchLatestPageViewActivityId(
			analyticsEvent.getUserId());
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

	@PostConstruct
	private void _init() {
		_semaphore = new Semaphore(_activitiesNaniteConcurrentTasksLimit, true);
	}

	private void _run() throws Exception {
		while (true) {
			try {
				_reentrantLock.lock();

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
					Collectors.groupingBy(
						analyticsEvent -> Tuples.of(
							analyticsEvent.getProjectId(),
							analyticsEvent.getUserId()))
				).forEach(
					this::_run
				);

				if (_log.isInfoEnabled()) {
					Class<?> clazz = getClass();

					_log.info(
						String.format(
							"%s dispatched %d events in %d ms",
							clazz.getSimpleName(), analyticsEvents.size(),
							System.currentTimeMillis() - start));
				}
			}
			finally {
				_reentrantLock.unlock();
			}
		}
	}

	private void _run(
		Tuple2<String, String> tuple2, List<AnalyticsEvent> analyticsEvents) {

		try {
			_semaphore.acquire();

			CompletableFuture.runAsync(
				() -> {
					long start = System.currentTimeMillis();

					try {
						ProjectIdThreadLocal.setProjectId(tuple2.getT1());

						JSONArray activityJSONArray = new JSONArray();

						for (AnalyticsEvent analyticsEvent : analyticsEvents) {
							if ((Objects.equals(
									analyticsEvent.getApplicationId(),
									"Form") &&
								 Objects.equals(
									 analyticsEvent.getEventId(),
									 "formViewed")) ||
								(Objects.equals(
									analyticsEvent.getApplicationId(),
									"Page") &&
								 Objects.equals(
									 analyticsEvent.getEventId(),
									 "pageViewed"))) {

								JSONObject activityJSONObject =
									_getActivityJSONObject(analyticsEvent);

								if (activityJSONObject != null) {
									_addActivityJSONObject(
										activityJSONObject, tuple2.getT1());
								}

								continue;
							}

							JSONObject activityJSONObject =
								_getActivityJSONObject(analyticsEvent);

							if (activityJSONObject != null) {
								activityJSONArray.put(activityJSONObject);
							}
						}

						_addActivityJSONArray(
							activityJSONArray, tuple2.getT1());
					}
					catch (Exception exception) {
						List<String> analyticsEventsString = ListUtil.map(
							analyticsEvents, AnalyticsEvent::toJSON);

						_log.error(
							"Unable to process analytics events messages " +
								analyticsEventsString,
							exception);
					}
					finally {
						_semaphore.release();
					}

					if (_log.isInfoEnabled()) {
						Class<?> clazz = getClass();

						_log.info(
							String.format(
								"%s processed %d events in %d ms",
								clazz.getSimpleName(), analyticsEvents.size(),
								System.currentTimeMillis() - start));
					}
				},
				_executorService);
		}
		catch (InterruptedException interruptedException) {
			_log.error(interruptedException, interruptedException);
		}
	}

	private void _sendActivityMessage(
		JSONObject activityJSONObject, String projectId) {

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

	@Value("${osb.asah.activities.nanite.concurrent.tasks.limit:15}")
	private int _activitiesNaniteConcurrentTasksLimit;

	@Value("${osb.asah.activities.nanite.pull.messages.size:50}")
	private int _activitiesNanitePullMessagesSize;

	@Autowired
	private ActivityGroupDog _activityGroupDog;

	@Autowired
	private AssetDog _assetDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	private final ExecutorService _executorService =
		Executors.newFixedThreadPool(10);

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private MessageBus _messageBus;

	@MessageSubscriber.Autowired(channel = Channel.ANALYTICS_EVENTS_ACTIVITY)
	private MessageSubscriber _messageSubscriber;

	private final ReentrantLock _reentrantLock = new ReentrantLock();
	private Semaphore _semaphore;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}