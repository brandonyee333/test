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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.http.ChannelHttp;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Geyson Silva
 */
@Component
public class FaroInfoChannelDog extends BaseFaroInfoDog {

	public JSONObject addChannel(List<JSONObject> dataSources, String name) {
		return addChannel(dataSources, name, false);
	}

	public JSONObject addChannel(
		List<JSONObject> dataSources, String name, boolean updateFaro) {

		JSONObject channelJSONObject = JSONUtil.put(
			"dataSources", dataSources
		).put(
			"dateCreated", DateUtil.newDateString()
		).put(
			"name", _getChannelName(name)
		);

		channelJSONObject = elasticsearchInvoker.add(
			"channels", channelJSONObject);

		if (updateFaro) {
			try {
				_channelHttp.addChannel(channelJSONObject);
			}
			catch (Exception e) {
				_log.error(e, e);

				_handleFaroError(channelJSONObject);
			}
		}

		return channelJSONObject;
	}

	public List<JSONObject> addChannels(JSONObject inputJSONObject) {
		JSONArray groupsJSONArray = inputJSONObject.getJSONArray("groups");
		String channelType = inputJSONObject.getString("channelType");
		String dataSourceId = inputJSONObject.getString("dataSourceId");

		if (channelType.equals(_CHANNEL_TYPE_MULTIPLE)) {
			return _saveMultipleChannel(dataSourceId, groupsJSONArray);
		}

		return _saveCombinedChannel(dataSourceId, groupsJSONArray);
	}

	public void clearChannels(
			List<String> channelIds,
			Consumer<Integer> processedCountMonitorConsumer,
			Consumer<Integer> queueMonitorConsumer)
		throws Exception {

		_deleteAccountReferences(
			channelIds, processedCountMonitorConsumer, queueMonitorConsumer);
		_deleteAssets(
			channelIds, processedCountMonitorConsumer, queueMonitorConsumer);
		_deleteData(
			channelIds, elasticsearchInvoker, "activities", "activity-groups",
			"individual-segments");
		_deleteData(
			channelIds, _cerebroInfoElasticsearchInvoker, "blog-clicks",
			"blog-social-shares", "blog-traffic-sources", "blogs",
			"custom-assets", "custom-asset-dashboards", "document-libraries",
			"forms", "journal-clicks", "journals", "page-referrers", "pages",
			"user-sessions");
		_deleteIndividualReferences(
			channelIds, processedCountMonitorConsumer, queueMonitorConsumer);
	}

	public void deleteChannels(
			List<String> channelIds,
			Consumer<Integer> processedCountMonitorConsumer,
			Consumer<Integer> queueMonitorConsumer)
		throws Exception {

		clearChannels(
			channelIds, processedCountMonitorConsumer, queueMonitorConsumer);

		elasticsearchInvoker.deleteByQuery(
			QueryBuilders.termsQuery("id", channelIds), true, "channels");
	}

	public Map<String, String> getChannelNamesByGroupIds(
		String dataSourceId, Set<String> groupsIds) {

		Map<String, String> channelNamesByGroupIds = new HashMap<>();

		JSONArray channelsJSONArray = elasticsearchInvoker.get(
			"channels",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("dataSources.id", dataSourceId)
			).filter(
				QueryBuilders.termsQuery("dataSources.groupIds", groupsIds)
			));

		for (int i = 0; i < channelsJSONArray.length(); i++) {
			JSONObject channelJSONObject = channelsJSONArray.getJSONObject(i);

			String channelName = channelJSONObject.getString("name");

			Stream<Object> stream = JSONUtil.toObjectStream(
				channelJSONObject.getJSONArray("dataSources"));

			stream.map(
				object -> (JSONObject)object
			).filter(
				dataSourceJSONObject -> Objects.equals(
					dataSourceJSONObject.getString("id"), dataSourceId)
			).flatMap(
				dataSourceJSONObject -> JSONUtil.toObjectStream(
					dataSourceJSONObject.getJSONArray("groupIds"))
			).map(
				String::valueOf
			).filter(
				groupsIds::contains
			).forEach(
				groupId -> channelNamesByGroupIds.put(groupId, channelName)
			);
		}

		return channelNamesByGroupIds;
	}

	public JSONObject patchChannel(String id, JSONObject inputJSONObject) {
		JSONObject channelJSONObject = elasticsearchInvoker.get("channels", id);
		List<String> removedGroupIds = Collections.emptyList();

		if (inputJSONObject.has("name")) {
			channelJSONObject.put(
				"name", _getChannelName(inputJSONObject.optString("name")));
		}

		String dataSourceId = inputJSONObject.optString("dataSourceId", null);
		JSONArray groupsJSONArray = inputJSONObject.optJSONArray("groups");

		if ((dataSourceId != null) && (groupsJSONArray != null)) {
			List<String> groupIds = new ArrayList<>(groupsJSONArray.length());

			for (int i = 0; i < groupsJSONArray.length(); i++) {
				JSONObject groupJSONObject = groupsJSONArray.getJSONObject(i);

				groupIds.add(groupJSONObject.getString("id"));
			}

			JSONArray dataSourcesJSONArray = channelJSONObject.getJSONArray(
				"dataSources");

			JSONObject dataSourceJSONObject = IntStream.range(
				0, dataSourcesJSONArray.length()
			).mapToObj(
				dataSourcesJSONArray::getJSONObject
			).filter(
				jsonObject -> dataSourceId.equals(jsonObject.getString("id"))
			).findFirst(
			).orElseGet(
				() -> {
					JSONObject jsonObject = JSONUtil.put("id", dataSourceId);

					dataSourcesJSONArray.put(jsonObject);

					return jsonObject;
				}
			);

			removedGroupIds = _getRemovedGroupIds(
				dataSourceJSONObject, groupIds);

			dataSourceJSONObject.put("groupIds", groupIds);
		}

		return JSONUtil.put(
			"channel",
			elasticsearchInvoker.update("channels", channelJSONObject)
		).put(
			"removedGroupIds", removedGroupIds
		);
	}

	private BoolQueryBuilder _buildBoolQueryBuilder(
		List<String> channelIds, String... propertyNames) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		for (String propertyName : propertyNames) {
			boolQueryBuilder.should(
				QueryBuilders.nestedQuery(
					propertyName,
					QueryBuilders.termsQuery(
						propertyName + ".channelId", channelIds),
					ScoreMode.None));
		}

		return boolQueryBuilder;
	}

	private void _deleteAccountReferences(
			List<String> channelIds,
			Consumer<Integer> processedCountMonitorConsumer,
			Consumer<Integer> queueMonitorConsumer)
		throws Exception {

		JSONArrayIterator.of(
			"accounts", elasticsearchInvoker,
			accountJSONObject -> {
				JSONObject modifiedJSONObject = new JSONObject();

				_updateChannelIds(
					channelIds, accountJSONObject, modifiedJSONObject,
					"activitiesCounts", "individualCounts");

				elasticsearchInvoker.update(
					"accounts", accountJSONObject.getString("id"),
					modifiedJSONObject);

				return null;
			}
		).setMonitoringConsumers(
			processedCountMonitorConsumer, queueMonitorConsumer
		).setQueryBuilder(
			_buildBoolQueryBuilder(
				channelIds, "activitiesCounts", "individualCounts")
		).iterate();
	}

	private void _deleteAssets(
			List<String> channelIds,
			Consumer<Integer> processedCountMonitorConsumer,
			Consumer<Integer> queueMonitorConsumer)
		throws Exception {

		String deletionDayDateString = DateUtil.newDayDateString();

		JSONArrayIterator.of(
			"assets", elasticsearchInvoker,
			assetJSONObject -> {
				JSONArray channelIdsJSONArray = assetJSONObject.getJSONArray(
					"channelIds");

				Iterator<Object> iterator = channelIdsJSONArray.iterator();

				while (iterator.hasNext()) {
					String channelId = (String)iterator.next();

					if (channelIds.contains(channelId)) {
						iterator.remove();
					}
				}

				if (channelIdsJSONArray.length() == 0) {
					_faroInfoAssetDog.deleteAsset(
						assetJSONObject, deletionDayDateString);
				}
				else {
					elasticsearchInvoker.update(
						"assets", assetJSONObject.getString("id"),
						JSONUtil.put("channelIds", channelIdsJSONArray));
				}

				return null;
			}
		).setMonitoringConsumers(
			processedCountMonitorConsumer, queueMonitorConsumer
		).setQueryBuilder(
			QueryBuilders.termsQuery("channelIds", channelIds)
		).iterate();
	}

	private void _deleteData(
		List<String> channelIds, ElasticsearchInvoker elasticsearchInvoker,
		String... collectionNames) {

		for (String collectionName : collectionNames) {
			elasticsearchInvoker.delete(
				collectionName,
				QueryBuilders.termsQuery("channelId", channelIds));
		}
	}

	private void _deleteIndividualReferences(
			List<String> channelIds,
			Consumer<Integer> processedCountMonitorConsumer,
			Consumer<Integer> queueMonitorConsumer)
		throws Exception {

		BoolQueryBuilder boolQueryBuilder = _buildBoolQueryBuilder(
			channelIds, "activitiesCounts", "lastActivityDates");

		boolQueryBuilder.should(
			QueryBuilders.termsQuery("channelIds", channelIds));

		JSONArrayIterator.of(
			"individuals", elasticsearchInvoker,
			individualJSONObject -> {
				JSONObject modifiedJSONObject = new JSONObject();

				_updateChannelIds(
					channelIds, individualJSONObject, modifiedJSONObject,
					"activitiesCounts", "lastActivityDates");

				JSONArray channelIdsJSONArray =
					individualJSONObject.getJSONArray("channelIds");

				Iterator<Object> iterator = channelIdsJSONArray.iterator();

				while (iterator.hasNext()) {
					String channelId = (String)iterator.next();

					if (channelIds.contains(channelId)) {
						iterator.remove();
					}
				}

				modifiedJSONObject.put("channelIds", channelIdsJSONArray);

				elasticsearchInvoker.update(
					"individuals", individualJSONObject.getString("id"),
					modifiedJSONObject);

				return null;
			}
		).setMonitoringConsumers(
			processedCountMonitorConsumer, queueMonitorConsumer
		).setQueryBuilder(
			boolQueryBuilder
		).iterate();
	}

	private String _getChannelName(String name) {
		name = StringUtils.truncate(name, 65);

		int nameCount = 0;
		String originalName = name;

		while (elasticsearchInvoker.exists(
					"channels", QueryBuilders.termQuery("name", name))) {

			name = String.format("%s (%d)", originalName, ++nameCount);
		}

		return name;
	}

	private List<String> _getRemovedGroupIds(
		JSONObject dataSourceJSONObject, List<String> groupIds) {

		JSONArray oldGroupIdsJSONArray = dataSourceJSONObject.optJSONArray(
			"groupIds");

		if ((oldGroupIdsJSONArray == null) ||
			(oldGroupIdsJSONArray.length() == 0)) {

			return Collections.emptyList();
		}

		List<String> oldGroupIds = JSONUtil.toStringList(oldGroupIdsJSONArray);

		oldGroupIds.removeAll(groupIds);

		return oldGroupIds;
	}

	private void _handleFaroError(JSONObject channelJSONObject) {
		elasticsearchInvoker.delete("channels", channelJSONObject);

		throw new OSBAsahException(
			HttpStatus.BAD_REQUEST, "Unable to create channel");
	}

	private List<JSONObject> _saveCombinedChannel(
		String dataSourceId, JSONArray groupsJSONArray) {

		List<String> groupIds = new ArrayList<>();

		for (int i = 0; i < groupsJSONArray.length(); i++) {
			JSONObject groupJSONObject = groupsJSONArray.getJSONObject(i);

			groupIds.add(groupJSONObject.getString("id"));
		}

		JSONObject dataSourceJSONObject = JSONUtil.put(
			"groupIds", groupIds
		).put(
			"id", dataSourceId
		);

		JSONObject jsonObject = elasticsearchInvoker.get(
			"data-sources", dataSourceId);

		return Collections.singletonList(
			addChannel(
				Collections.singletonList(dataSourceJSONObject),
				_getChannelName(
					jsonObject.getString("name") + " Combined Property"),
				true));
	}

	private List<JSONObject> _saveMultipleChannel(
		String dataSourceId, JSONArray groupsJSONArray) {

		List<JSONObject> channels = new ArrayList<>();

		JSONObject dataSourceJSONObject = JSONUtil.put("id", dataSourceId);

		for (int i = 0; i < groupsJSONArray.length(); i++) {
			JSONObject groupJSONObject = groupsJSONArray.getJSONObject(i);

			dataSourceJSONObject.put(
				"groupIds",
				Collections.singletonList(groupJSONObject.getString("id")));

			channels.add(
				addChannel(
					Collections.singletonList(dataSourceJSONObject),
					_getChannelName(groupJSONObject.getString("name")), true));
		}

		return channels;
	}

	private void _updateChannelIds(
		List<String> channelIds, JSONObject jsonObject,
		JSONObject modifiedJSONObject, String... propertyNames) {

		for (String propertyName : propertyNames) {
			JSONArray jsonArray = jsonObject.optJSONArray(propertyName);

			if (jsonArray == null) {
				continue;
			}

			Iterator<Object> iterator = jsonArray.iterator();

			while (iterator.hasNext()) {
				JSONObject propertyJSONObject = (JSONObject)iterator.next();

				if (channelIds.contains(
						propertyJSONObject.getString("channelId"))) {

					iterator.remove();
				}
			}

			modifiedJSONObject.put(propertyName, jsonArray);
		}
	}

	private static final String _CHANNEL_TYPE_MULTIPLE = "multiple";

	private static final Log _log = LogFactory.getLog(FaroInfoChannelDog.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private ChannelHttp _channelHttp;

	@Autowired
	private FaroInfoAssetDog _faroInfoAssetDog;

}