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
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.http.ChannelHttp;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Channel;
import com.liferay.osb.asah.common.model.ChannelDataSource;
import com.liferay.osb.asah.common.rest.response.CollectionGetResponse;
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
import java.util.stream.Collectors;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Geyson Silva
 */
@Component
public class FaroInfoChannelDog extends BaseFaroInfoDog {

	public Channel addChannel(
		Map<Long, Set<Long>> dataSources, String name, boolean updateFaro) {

		List<JSONObject> dataSourceJSONObjects = new ArrayList<>();

		if (dataSources != null) {
			for (Map.Entry<Long, Set<Long>> entry : dataSources.entrySet()) {
				dataSourceJSONObjects.add(
					JSONUtil.put(
						"groupIds", entry.getValue()
					).put(
						"id", entry.getKey()
					));
			}
		}

		JSONObject channelJSONObject = JSONUtil.put(
			"dataSources", dataSourceJSONObjects
		).put(
			"dateCreated", DateUtil.newDateString()
		).put(
			"name", _getChannelName(name)
		);

		channelJSONObject = elasticsearchInvoker.add(
			"channels", channelJSONObject);

		Channel channel = _toChannel(channelJSONObject);

		if (updateFaro) {
			try {
				_channelHttp.addChannel(channel);
			}
			catch (Exception exception) {
				_log.error(exception, exception);

				_handleFaroError(channelJSONObject);
			}
		}

		return channel;
	}

	public Channel addChannel(String name) {
		return addChannel(null, name, false);
	}

	public List<Channel> addChannels(
		Map<Long, String> channelNamesByGroupIds, String channelType,
		Long dataSourceId) {

		if (channelType.equals(_CHANNEL_TYPE_MULTIPLE)) {
			return _saveMultipleChannel(dataSourceId, channelNamesByGroupIds);
		}

		return _saveCombinedChannel(
			dataSourceId, channelNamesByGroupIds.keySet());
	}

	public void clearChannels(
			List<Long> channelIds,
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
			List<Long> channelIds,
			Consumer<Integer> processedCountMonitorConsumer,
			Consumer<Integer> queueMonitorConsumer)
		throws Exception {

		clearChannels(
			channelIds, processedCountMonitorConsumer, queueMonitorConsumer);

		elasticsearchInvoker.deleteByQuery(
			QueryBuilders.termsQuery(
				"id",
				Stream.of(
					channelIds
				).flatMap(
					List::stream
				).map(
					String::valueOf
				).collect(
					Collectors.toList()
				)),
			true, "channels");
	}

	public Channel fetchChannel(Long channelId) {
		JSONObject channelJSONObject = elasticsearchInvoker.fetch(
			"channels", String.valueOf(channelId));

		if (channelJSONObject != null) {
			return _toChannel(channelJSONObject);
		}

		return null;
	}

	public Channel getChannel(Long channelId) {
		JSONObject channelJSONObject = elasticsearchInvoker.get(
			"channels", String.valueOf(channelId));

		return _toChannel(channelJSONObject);
	}

	public Map<Long, String> getChannelNamesByGroupIds(
		Long dataSourceId, Set<Long> groupsIds) {

		Map<Long, String> channelNamesByGroupIds = new HashMap<>();

		JSONArray channelsJSONArray = elasticsearchInvoker.get(
			"channels",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"dataSources.id", String.valueOf(dataSourceId))
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
					dataSourceJSONObject.getString("id"),
					String.valueOf(dataSourceId))
			).flatMap(
				dataSourceJSONObject -> JSONUtil.toObjectStream(
					dataSourceJSONObject.getJSONArray("groupIds"))
			).map(
				String::valueOf
			).map(
				Long::valueOf
			).filter(
				groupsIds::contains
			).forEach(
				groupId -> channelNamesByGroupIds.put(groupId, channelName)
			);
		}

		return channelNamesByGroupIds;
	}

	public List<Channel> getChannels() {
		JSONArray channelJSONArray = elasticsearchInvoker.get("channels");

		return _toChannels(channelJSONArray);
	}

	public List<Channel> getChannels(Long dataSourceId) {
		JSONArray channelJSONArray = elasticsearchInvoker.get(
			"channels",
			QueryBuilders.termQuery(
				"dataSources.id", String.valueOf(dataSourceId)));

		return _toChannels(channelJSONArray);
	}

	public Page<Channel> getChannels(
		String name, int page, int size, String[] sorts) {

		try {
			CollectionGetResponse collectionGetResponse =
				new CollectionGetResponse();

			collectionGetResponse.setCollectionName("channels");
			collectionGetResponse.setElasticsearchInvoker(elasticsearchInvoker);
			collectionGetResponse.setPage(page);
			collectionGetResponse.setQueryBuilder(
				QueryUtil.buildSearchQueryBuilder("name.search", name));
			collectionGetResponse.setSize(size);
			collectionGetResponse.setSorts(sorts);

			JSONObject jsonObject = new JSONObject(
				collectionGetResponse.respond());

			JSONObject pageJSONObject = jsonObject.getJSONObject("page");

			JSONObject embeddedJSONObject = jsonObject.getJSONObject(
				"_embedded");

			return new PageImpl<>(
				_toChannels(embeddedJSONObject.getJSONArray("channels")),
				PageRequest.of(
					pageJSONObject.getInt("number"),
					pageJSONObject.getInt("size")),
				pageJSONObject.getLong("totalElements"));
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return Page.empty();
		}
	}

	public Set<Long> getRemovedGroupIds(
		Long channelId, Long dataSourceId, Set<Long> groupIds) {

		if ((dataSourceId != null) && (groupIds != null)) {
			return Stream.of(
				getChannel(channelId)
			).map(
				Channel::getChannelDataSources
			).flatMap(
				Set::stream
			).filter(
				channelDataSource -> Objects.equals(
					channelDataSource.getDataSourceId(), dataSourceId)
			).findFirst(
			).map(
				channelDataSource -> _getRemovedGroupIds(
					channelDataSource, groupIds)
			).orElse(
				Collections.emptySet()
			);
		}

		return Collections.emptySet();
	}

	public Channel patchChannel(
		Long channelId, Long dataSourceId, Set<Long> groupIds, String name) {

		Channel channel = getChannel(channelId);

		if (StringUtils.isNotBlank(name)) {
			channel.setName(_getChannelName(name));
		}

		if ((dataSourceId != null) && (groupIds != null)) {
			channel.clearDataSource();
			channel.addDataSource(dataSourceId, groupIds);
		}

		return _toChannel(
			elasticsearchInvoker.update("channels", _toJSONObject(channel)));
	}

	public Channel update(Channel channel) {
		return _toChannel(
			elasticsearchInvoker.update("channels", _toJSONObject(channel)));
	}

	private BoolQueryBuilder _buildBoolQueryBuilder(
		List<Long> channelIds, String... propertyNames) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		for (String propertyName : propertyNames) {
			boolQueryBuilder.should(
				QueryBuilders.nestedQuery(
					propertyName,
					QueryBuilders.termsQuery(
						propertyName + ".channelId",
						Stream.of(
							channelIds
						).flatMap(
							List::stream
						).map(
							String::valueOf
						).collect(
							Collectors.toList()
						)),
					ScoreMode.None));
		}

		return boolQueryBuilder;
	}

	private void _deleteAccountReferences(
			List<Long> channelIds,
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
			List<Long> channelIds,
			Consumer<Integer> processedCountMonitorConsumer,
			Consumer<Integer> queueMonitorConsumer)
		throws Exception {

		JSONArrayIterator.of(
			"assets", elasticsearchInvoker,
			assetJSONObject -> {
				JSONArray channelIdsJSONArray = assetJSONObject.getJSONArray(
					"channelIds");

				Iterator<Object> iterator = channelIdsJSONArray.iterator();

				while (iterator.hasNext()) {
					String channelId = (String)iterator.next();

					if (channelIds.contains(Long.valueOf(channelId))) {
						iterator.remove();
					}
				}

				if (channelIdsJSONArray.length() == 0) {
					_faroInfoAssetDog.deleteAsset(
						assetJSONObject,
						DateUtil.newDayLocalDateTimeString(
							_timeZoneDog.getZoneId()));
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
		List<Long> channelIds, ElasticsearchInvoker elasticsearchInvoker,
		String... collectionNames) {

		for (String collectionName : collectionNames) {
			elasticsearchInvoker.delete(
				collectionName,
				QueryBuilders.termsQuery(
					"channelId",
					Stream.of(
						channelIds
					).flatMap(
						List::stream
					).map(
						String::valueOf
					).collect(
						Collectors.toList()
					)));
		}
	}

	private void _deleteIndividualReferences(
			List<Long> channelIds,
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

					if (channelIds.contains(Long.valueOf(channelId))) {
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

	private Set<Long> _getRemovedGroupIds(
		ChannelDataSource channelDataSource, Set<Long> groupIds) {

		Set<Long> oldGroupIds = channelDataSource.getGroupIds();

		if (oldGroupIds.isEmpty()) {
			return Collections.emptySet();
		}

		oldGroupIds.removeAll(groupIds);

		return oldGroupIds;
	}

	private void _handleFaroError(JSONObject channelJSONObject) {
		elasticsearchInvoker.delete("channels", channelJSONObject);

		throw new OSBAsahException(
			HttpStatus.BAD_REQUEST, "Unable to create channel");
	}

	private List<Channel> _saveCombinedChannel(
		Long dataSourceId, Set<Long> groupIds) {

		JSONObject jsonObject = elasticsearchInvoker.get(
			"data-sources", String.valueOf(dataSourceId));

		return Collections.singletonList(
			addChannel(
				Collections.singletonMap(dataSourceId, groupIds),
				_getChannelName(
					jsonObject.getString("name") + " Combined Property"),
				true));
	}

	private List<Channel> _saveMultipleChannel(
		Long dataSourceId, Map<Long, String> channelNamesByGroupIds) {

		return Stream.of(
			channelNamesByGroupIds
		).map(
			Map::entrySet
		).flatMap(
			Set::stream
		).map(
			entry -> addChannel(
				Collections.singletonMap(
					dataSourceId, Collections.singleton(entry.getKey())),
				_getChannelName(entry.getValue()), true)
		).collect(
			Collectors.toList()
		);
	}

	private Channel _toChannel(JSONObject channelJSONObject) {
		Channel channel = new Channel();

		JSONArray dataSourcesJSONArray = channelJSONObject.getJSONArray(
			"dataSources");

		for (int j = 0; j < dataSourcesJSONArray.length(); j++) {
			JSONObject dataSourceJSONObject =
				dataSourcesJSONArray.getJSONObject(j);

			channel.addDataSource(
				dataSourceJSONObject.getLong("id"),
				Stream.of(
					dataSourceJSONObject.getJSONArray("groupIds")
				).map(
					JSONArray::toList
				).flatMap(
					List::stream
				).map(
					String::valueOf
				).map(
					Long::valueOf
				).collect(
					Collectors.toSet()
				));
		}

		try {
			channel.setCreateDate(
				DateUtil.toUTCDate(channelJSONObject.getString("dateCreated")));
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		channel.setId(channelJSONObject.getLong("id"));
		channel.setName(channelJSONObject.getString("name"));

		return channel;
	}

	private List<Channel> _toChannels(JSONArray channelsJSONArray) {
		List<Channel> channels = new ArrayList<>();

		for (int i = 0; i < channelsJSONArray.length(); i++) {
			try {
				channels.add(_toChannel(channelsJSONArray.getJSONObject(i)));
			}
			catch (Exception exception) {
				_log.error(exception, exception);
			}
		}

		return channels;
	}

	private JSONObject _toJSONObject(Channel channel) {
		JSONObject channelJSONObject = new JSONObject();

		channelJSONObject.put(
			"dateCreated", DateUtil.toUTCString(channel.getCreateDate()));
		channelJSONObject.put("id", String.valueOf(channel.getId()));

		JSONArray dataSourcesJSONArray = new JSONArray();

		for (ChannelDataSource channelDataSource :
				channel.getChannelDataSources()) {

			JSONObject channelDataSourceJSONObject = new JSONObject();

			channelDataSourceJSONObject.put(
				"groupIds",
				Stream.of(
					channelDataSource.getGroupIds()
				).flatMap(
					Set::stream
				).map(
					String::valueOf
				).collect(
					Collectors.toList()
				));

			channelDataSourceJSONObject.put(
				"id", String.valueOf(channelDataSource.getDataSourceId()));

			dataSourcesJSONArray.put(channelDataSourceJSONObject);
		}

		channelJSONObject.put("dataSources", dataSourcesJSONArray);

		channelJSONObject.put("name", channel.getName());

		return channelJSONObject;
	}

	private void _updateChannelIds(
		List<Long> channelIds, JSONObject jsonObject,
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
						Long.valueOf(
							propertyJSONObject.getString("channelId")))) {

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

	@Autowired
	private TimeZoneDog _timeZoneDog;

}