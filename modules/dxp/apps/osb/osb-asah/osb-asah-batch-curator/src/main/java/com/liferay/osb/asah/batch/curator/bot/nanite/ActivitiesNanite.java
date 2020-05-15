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

import com.liferay.osb.asah.batch.curator.nlp.NLPUtil;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;
import com.liferay.osb.asah.common.http.QueueHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.util.MapUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class ActivitiesNanite extends BaseActivitiesNanite {

	public void addActivityJSONObject(
			JSONObject activityJSONObject, String applicationId, String eventId,
			String ownerId)
		throws Exception {

		_faroInfoActivityDog.addActivity(activityJSONObject);

		if (_faroInfoActivityDog.isActivity(applicationId, eventId)) {
			_queueHttp.pushMessage(
				JSONUtil.put(
					"channelId", activityJSONObject.get("channelId")
				).put(
					"ownerId", ownerId
				).toString(),
				QueueHttp.QUEUE_NAME_ACTIVE_INDIVIDUAL_IDS);
		}
	}

	@Override
	@PostConstruct
	public void init() {
		super.init();

		_cerebroRawElasticsearchInvoker =
			elasticsearchInvokerFactory.forCerebroRaw();

		_queueHttp.initializeQueue();
	}

	@Override
	public void run() throws Exception {
		process(
			"analytics-events", "lastSuccessfulAnalyticsEventId",
			_cerebroRawElasticsearchInvoker, QueryBuilders.matchAllQuery());
	}

	@Override
	protected void cleanUp() {
	}

	@Override
	protected boolean isActive() {
		return _active;
	}

	@Override
	protected void process(JSONObject analyticsEventJSONObject) {
		String analyticsEventJSON = analyticsEventJSONObject.toString();

		try {
			AnalyticsEvent analyticsEvent = AnalyticsEvent.toAnalyticsEvent(
				analyticsEventJSON);

			String applicationId = analyticsEvent.getApplicationId();

			String dataSourceAssetPKFieldName =
				_dataSourceAssetPKFieldNames.get(applicationId);

			if (dataSourceAssetPKFieldName == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("Invalid application ID " + applicationId);
				}

				return;
			}

			JSONObject activityGroupJSONObject = _addActivityGroupJSONObject(
				analyticsEvent);

			_addActivityJSONObject(
				activityGroupJSONObject, analyticsEvent, applicationId,
				dataSourceAssetPKFieldName);
		}
		catch (Exception e) {
			_log.error(
				"Unable to process the analytics event " + analyticsEventJSON,
				e);
		}
	}

	@Override
	protected void setActive(boolean active) {
		_active = active;
	}

	private JSONObject _addActivityGroupJSONObject(
			AnalyticsEvent analyticsEvent)
		throws Exception {

		String channelId = analyticsEvent.getChannelId();
		String dataSourceId = analyticsEvent.getDataSourceId();
		String dateString = DateUtil.toUTCString(analyticsEvent.getEventDate());
		String userId = analyticsEvent.getUserId();

		String dayDateString = DateUtil.newDayDateString(dateString);

		JSONObject activityGroupJSONObject = faroInfoElasticsearchInvoker.fetch(
			"activity-groups",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("activityType", "BROWSE")
			).filter(
				QueryBuilders.termQuery("channelId", channelId)
			).filter(
				QueryBuilders.termQuery("dataSourceId", dataSourceId)
			).filter(
				QueryBuilders.termQuery("day", dayDateString)
			).filter(
				QueryBuilders.termQuery("userId", userId)
			));

		if (activityGroupJSONObject != null) {
			faroInfoElasticsearchInvoker.update(
				"activity-groups", activityGroupJSONObject.getString("id"),
				JSONUtil.put("endTime", dateString));

			return activityGroupJSONObject;
		}

		return faroInfoElasticsearchInvoker.add(
			"activity-groups",
			JSONUtil.put(
				"activityType", "BROWSE"
			).put(
				"channelId", channelId
			).put(
				"dataSourceId", dataSourceId
			).put(
				"day", dayDateString
			).put(
				"endTime", dateString
			).put(
				"ownerId", _getOwnerId(dataSourceId, userId)
			).put(
				"startTime", dateString
			).put(
				"userId", userId
			));
	}

	private void _addActivityJSONObject(
			JSONObject activityGroupJSONObject, AnalyticsEvent analyticsEvent,
			String applicationId, String dataSourceAssetPKFieldName)
		throws Exception {

		String eventId = analyticsEvent.getEventId();

		JSONObject objectJSONObject = new JSONObject();

		String dataSourceAssetPK = null;

		Map<String, String> eventProperties =
			analyticsEvent.getEventProperties();

		if (eventProperties != null) {
			dataSourceAssetPK = eventProperties.get(dataSourceAssetPKFieldName);
		}

		Map<String, Object> context = analyticsEvent.getContext();

		if (dataSourceAssetPK == null) {
			dataSourceAssetPK = MapUtil.getString(
				context, dataSourceAssetPKFieldName);

			if (dataSourceAssetPK == null) {
				throw new IllegalArgumentException(
					"No data source asset PK for analytics event " +
						analyticsEvent.toJSON());
			}
		}

		objectJSONObject.put("dataSourceAssetPK", dataSourceAssetPK);

		JSONObject assetJSONObject = _getAssetJSONObject(
			analyticsEvent, dataSourceAssetPK);

		if (assetJSONObject == null) {
			return;
		}

		String assetId = assetJSONObject.getString("id");

		objectJSONObject.put("id", assetId);

		objectJSONObject.put("name", _getName(analyticsEvent));
		objectJSONObject.put(
			"objectType", assetJSONObject.getString("assetType"));
		objectJSONObject.put("url", MapUtil.getString(context, "url"));

		if (applicationId.equals("Comment")) {
			applicationId = objectJSONObject.getString("objectType");

			eventId = "comment" + StringUtils.capitalize(eventId);
		}

		String eventDateString = DateUtil.toUTCString(
			analyticsEvent.getEventDate());
		String ownerId = activityGroupJSONObject.optString("ownerId");

		addActivityJSONObject(
			JSONUtil.put(
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
				"day", DateUtil.newDayDateString(eventDateString)
			).put(
				"endTime", eventDateString
			).put(
				"eventId", eventId
			).put(
				"eventProperties", _getEventPropertiesJSONObject(analyticsEvent)
			).put(
				"groupId", activityGroupJSONObject.get("id")
			).put(
				"object", objectJSONObject
			).put(
				"ownerId", ownerId
			).put(
				"startTime", eventDateString
			).put(
				"userId", analyticsEvent.getUserId()
			),
			applicationId, eventId, ownerId);
	}

	private JSONObject _getAssetJSONObject(
			AnalyticsEvent analyticsEvent, String dataSourceAssetPK)
		throws Exception {

		JSONObject assetJSONObject = faroInfoElasticsearchInvoker.fetch(
			"assets",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("dataSourceAssetPK", dataSourceAssetPK)
			).filter(
				QueryBuilders.termQuery(
					"dataSourceId", analyticsEvent.getDataSourceId())
			));

		if (assetJSONObject != null) {
			JSONArray channelIdsJSONArray = assetJSONObject.getJSONArray(
				"channelIds");

			if (!JSONUtil.hasValue(
					channelIdsJSONArray, analyticsEvent.getChannelId())) {

				channelIdsJSONArray.put(analyticsEvent.getChannelId());

				assetJSONObject = faroInfoElasticsearchInvoker.update(
					"assets", assetJSONObject);
			}

			if (Objects.equals(analyticsEvent.getApplicationId(), "Page")) {
				return assetJSONObject;
			}

			String name = _getEventPropertiesTitle(analyticsEvent);

			if (!Objects.equals(assetJSONObject.getString("name"), name)) {
				assetJSONObject.put("name", name);

				return faroInfoElasticsearchInvoker.update(
					"assets", assetJSONObject);
			}

			return assetJSONObject;
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

		Map<String, Object> context = analyticsEvent.getContext();

		assetJSONObject = JSONUtil.put(
			"assetType", analyticsEvent.getApplicationId()
		).put(
			"canonicalUrl", MapUtil.getString(context, "canonicalUrl")
		).put(
			"channelIds", JSONUtil.put(analyticsEvent.getChannelId())
		).put(
			"dataSourceAssetPK", dataSourceAssetPK
		).put(
			"dataSourceId", analyticsEvent.getDataSourceId()
		).put(
			"description", MapUtil.getString(context, "description")
		).put(
			"name", _getName(analyticsEvent)
		).put(
			"url", MapUtil.getString(context, "url")
		);

		if (Objects.equals(analyticsEvent.getApplicationId(), "Page")) {
			assetJSONObject.put(
				"keywords",
				JSONUtil.toJSONArray(
					new ArrayList<>(_getKeywordPairs(context)),
					keywordPair -> JSONUtil.put(
						"keyword", keywordPair.getLeft()
					).put(
						"type", keywordPair.getRight()
					)));
		}

		return faroInfoElasticsearchInvoker.add("assets", assetJSONObject);
	}

	private JSONObject _getEventPropertiesJSONObject(
			AnalyticsEvent analyticsEvent)
		throws Exception {

		JSONObject eventPropertiesJSONObject = new JSONObject(
			analyticsEvent.getEventProperties());

		String pageViewActivityId = _getPageViewActivityId(analyticsEvent);

		if (pageViewActivityId != null) {
			eventPropertiesJSONObject.put(
				"pageViewActivityId", pageViewActivityId);
		}

		if (Objects.equals(analyticsEvent.getEventId(), "formSubmitted")) {
			JSONArray formViewedActivityJSONArray = new JSONArray(
				_cerebroRawElasticsearchInvoker.get(
					"analytics-events",
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							BoolQueryBuilderUtil.filter(
								QueryBuilders.rangeQuery(
									"eventDate"
								).lt(
									DateUtil.toUTCString(
										analyticsEvent.getEventDate())
								)
							).filter(
								QueryBuilders.termQuery("eventId", "formViewed")
							).filter(
								QueryBuilders.termQuery(
									"userId", analyticsEvent.getUserId())
							));

						searchSourceBuilder.size(1);
						searchSourceBuilder.sort(
							SortBuilderUtil.fieldSort(
								"eventDate", SortOrder.DESC));
					}));

			if (formViewedActivityJSONArray.length() > 0) {
				JSONObject formViewedActivityJSONObject =
					formViewedActivityJSONArray.getJSONObject(0);

				JSONObject formViewedActivityEventPropertiesJSONObject =
					formViewedActivityJSONObject.getJSONObject(
						"eventProperties");

				eventPropertiesJSONObject.put(
					"duration",
					String.valueOf(
						DateUtil.getDeltaMilliseconds(
							formViewedActivityJSONObject.getString("eventDate"),
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

	private String _getEventPropertiesTitle(AnalyticsEvent analyticsEvent)
		throws Exception {

		JSONObject eventPropertiesJSONObject = _getEventPropertiesJSONObject(
			analyticsEvent);

		String title = eventPropertiesJSONObject.optString("title");

		if (!StringUtils.isEmpty(title)) {
			return title;
		}

		return null;
	}

	private Set<Pair<String, String>> _getKeywordPairs(
		Map<String, Object> context) {

		Set<Pair<String, String>> keywords = new HashSet<>();

		keywords.addAll(
			_getKeywordPairs(
				MapUtil.getString(context, "description"), "description"));
		keywords.addAll(
			_getKeywordPairs(
				MapUtil.getString(context, "keywords"), "keyword"));
		keywords.addAll(
			_getKeywordPairs(MapUtil.getString(context, "title"), "title"));
		keywords.addAll(
			_getKeywordPairs(
				MapUtil.getString(context, "og:description"), "description"));
		keywords.addAll(
			_getKeywordPairs(MapUtil.getString(context, "og:title"), "title"));

		return keywords;
	}

	private Set<Pair<String, String>> _getKeywordPairs(
		String text, String type) {

		if (StringUtils.isEmpty(text)) {
			return Collections.emptySet();
		}

		Stream<String> stream = Stream.of(text.split(","));

		if (type.equals("keyword")) {
			return stream.map(
				keyword -> Pair.of(keyword, type)
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
			keyword -> Pair.of(keyword, type)
		).collect(
			Collectors.toSet()
		);
	}

	private String _getName(AnalyticsEvent analyticsEvent) throws Exception {
		if (!Objects.equals(analyticsEvent.getApplicationId(), "Page")) {
			String name = _getEventPropertiesTitle(analyticsEvent);

			if (name != null) {
				return name;
			}
		}

		return MapUtil.getString(analyticsEvent.getContext(), "title");
	}

	private String _getOwnerId(String dataSourceId, String userId) {
		JSONObject individualJSONObject = faroInfoElasticsearchInvoker.fetch(
			"individuals",
			QueryBuilders.nestedQuery(
				"dataSourceIndividualPKs",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"dataSourceIndividualPKs.dataSourceId", dataSourceId)
				).filter(
					QueryBuilders.termQuery(
						"dataSourceIndividualPKs.individualPKs", userId)
				),
				ScoreMode.None));

		if (individualJSONObject == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to find individual with user ID " + userId +
						" and data source ID " + dataSourceId);
			}

			return null;
		}

		return individualJSONObject.getString("id");
	}

	private String _getPageViewActivityId(AnalyticsEvent analyticsEvent) {
		if (Objects.equals(analyticsEvent.getApplicationId(), "Page") &&
			Objects.equals(analyticsEvent.getEventId(), "pageViewed")) {

			return String.valueOf(UUID.randomUUID());
		}

		JSONArray mostRecentPageViewActivitiesJSONArray = new JSONArray(
			faroInfoElasticsearchInvoker.get(
				"activities",
				searchSourceBuilder -> {
					searchSourceBuilder.query(
						BoolQueryBuilderUtil.filter(
							QueryBuilders.termQuery("applicationId", "Page")
						).filter(
							QueryBuilders.termQuery("eventId", "pageViewed")
						).filter(
							QueryBuilders.termQuery(
								"userId", analyticsEvent.getUserId())
						));
					searchSourceBuilder.size(1);
					searchSourceBuilder.sort(
						SortBuilderUtil.fieldSort("startTime", SortOrder.DESC));
				}));

		if (mostRecentPageViewActivitiesJSONArray.length() == 0) {
			return null;
		}

		JSONObject mostRecentPageViewActivityJSONObject =
			mostRecentPageViewActivitiesJSONArray.getJSONObject(0);

		JSONObject eventPropertiesJSONObject =
			mostRecentPageViewActivityJSONObject.getJSONObject(
				"eventProperties");

		return eventPropertiesJSONObject.optString("pageViewActivityId", null);
	}

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

	private boolean _active;
	private ElasticsearchInvoker _cerebroRawElasticsearchInvoker;

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

	@Autowired
	private QueueHttp _queueHttp;

}