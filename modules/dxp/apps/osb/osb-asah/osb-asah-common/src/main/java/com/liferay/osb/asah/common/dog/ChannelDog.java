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

package com.liferay.osb.asah.common.dog;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.ChannelDataSource;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.faro.info.dog.BaseFaroInfoDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;
import com.liferay.osb.asah.common.http.ChannelHttp;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.TimeOrderedUuidGenerator;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.ArrayUtils;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Geyson Silva
 */
@Component
public class ChannelDog extends BaseFaroInfoDog {

	public Channel addChannel(
		Map<Long, Set<Long>> dataSources, boolean defaultChannel, String name,
		boolean updateFaro) {

		Channel channel = new Channel(_getChannelName(name));

		channel.setChannelDataSources(_getChannelDataSources(dataSources));
		channel.setDefaultChannel(defaultChannel);
		channel.setId(_timeOrderedUuidGenerator.generateIdAsLong());
		channel.setIsNew(Boolean.TRUE);
		channel.setState("READY");

		channel = _channelRepository.save(channel);

		if (updateFaro) {
			try {
				_channelHttp.addChannel(channel);
			}
			catch (Exception exception) {
				_log.error(exception, exception);

				_handleFaroError(channel);
			}
		}

		return channel;
	}

	public Channel addChannel(String name) {
		return addChannel(Collections.emptyMap(), false, name, false);
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
		_deleteAssets(channelIds);
		_deleteData(
			channelIds, _cerebroInfoElasticsearchInvoker, "blog-clicks",
			"blog-social-shares", "blog-traffic-sources", "blogs",
			"custom-assets", "custom-asset-dashboards", "document-libraries",
			"forms", "journal-clicks", "journals", "page-referrers", "pages",
			"user-sessions");
		_deleteIndividualReferences(
			channelIds, processedCountMonitorConsumer, queueMonitorConsumer);

		_activityGroupDog.deleteActivityGroups(new HashSet<>(channelIds));
		_faroInfoActivityDog.deleteActivies(new HashSet<>(channelIds));
		_segmentDog.deleteSegments(new HashSet<>(channelIds));
	}

	public void deleteChannels(
			List<Long> channelIds,
			Consumer<Integer> processedCountMonitorConsumer,
			Consumer<Integer> queueMonitorConsumer)
		throws Exception {

		clearChannels(
			channelIds, processedCountMonitorConsumer, queueMonitorConsumer);

		_channelRepository.deleteByIdIn(new HashSet<>(channelIds));
	}

	public Channel fetchChannel(Long channelId) {
		Optional<Channel> channelOptional = _channelRepository.findById(
			channelId);

		return channelOptional.orElse(null);
	}

	public Channel fetchDefaultChannel(Long dataSourceId) {
		List<Channel> defaultChannels =
			_channelRepository.findByDataSourceIdAndDefaultChannel(
				dataSourceId, Boolean.TRUE);

		if (defaultChannels.isEmpty()) {
			return null;
		}

		return defaultChannels.get(0);
	}

	public Channel getChannel(Long channelId) {
		Optional<Channel> channelOptional = _channelRepository.findById(
			channelId);

		return channelOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"There is no channel with ID " + channelId));
	}

	public Map<Long, String> getChannelNamesByGroupIds(
		Long dataSourceId, Set<Long> groupsIds) {

		Map<Long, String> channelNamesByGroupIds = new HashMap<>();

		for (Channel channel :
				_channelRepository.findByDataSourceIdAndGroupIds(
					dataSourceId, groupsIds)) {

			for (ChannelDataSource channelDataSource :
					channel.getChannelDataSources()) {

				for (Long channelDataSourceGroupId :
						channelDataSource.getGroupIds()) {

					if (!groupsIds.contains(channelDataSourceGroupId)) {
						continue;
					}

					channelNamesByGroupIds.put(
						channelDataSourceGroupId, channel.getName());
				}
			}
		}

		return channelNamesByGroupIds;
	}

	public Page<Channel> getChannelPage(
		String name, int page, int size, String[] sorts) {

		if (name != null) {
			return PageableExecutionUtils.getPage(
				_channelRepository.findByNameContainingIgnoreCaseAndStateNot(
					name, PageRequest.of(page, size, _getSort(sorts)),
					"IN_PROGRESS_DELETING"),
				PageRequest.of(page, size, _getSort(sorts)),
				() ->
					_channelRepository.
						countByNameContainingIgnoreCaseAndStateNot(
							name, "IN_PROGRESS_DELETING"));
		}

		return _channelRepository.findAll(
			PageRequest.of(page, size, _getSort(sorts)));
	}

	public List<Channel> getChannels(List<Long> channelIds) {
		return IterableUtils.toList(_channelRepository.findAllById(channelIds));
	}

	public List<Channel> getChannels(Long dataSourceId) {
		return _channelRepository.findByDataSourceId(dataSourceId);
	}

	public Set<Long> getRemovedGroupIds(
		Long channelId, Long dataSourceId, Set<Long> groupIds) {

		if ((dataSourceId == null) || (groupIds == null)) {
			return Collections.emptySet();
		}

		Channel channel = getChannel(channelId);

		for (ChannelDataSource channelDataSource :
				channel.getChannelDataSources()) {

			if (Objects.equals(
					channelDataSource.getDataSourceId(), dataSourceId)) {

				Set<Long> oldGroupIds = channelDataSource.getGroupIds();

				if (oldGroupIds.isEmpty()) {
					return Collections.emptySet();
				}

				oldGroupIds.removeAll(groupIds);

				return oldGroupIds;
			}
		}

		return Collections.emptySet();
	}

	public Channel patchChannel(
		Long channelId, Long dataSourceId, Set<Long> groupIds, String name) {

		Channel channel = getChannel(channelId);

		if (StringUtils.isNotBlank(name)) {
			channel.setName(_getChannelName(channelId, name));
		}

		if ((dataSourceId != null) && (groupIds != null)) {
			Set<ChannelDataSource> channelChannelDataSources =
				channel.getChannelDataSources();

			channelChannelDataSources.removeIf(
				channelDataSource -> Objects.equals(
					channelDataSource.getDataSourceId(), dataSourceId));

			channelChannelDataSources.add(
				new ChannelDataSource(dataSourceId, groupIds));

			channel.setChannelDataSources(channelChannelDataSources);
		}

		return _channelRepository.save(channel);
	}

	public Channel update(Channel channel) {
		return _channelRepository.save(channel);
	}

	public void updateState(List<String> ids, String state) {
		Stream<String> stream = ids.stream();

		Iterable<Channel> channels = _channelRepository.findAllById(
			stream.map(
				Long::valueOf
			).collect(
				Collectors.toList()
			));

		for (Channel channel : channels) {
			channel.setState(state);
		}

		_channelRepository.saveAll(channels);
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
						ListUtil.map(channelIds, String::valueOf)),
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

	private void _deleteAssets(List<Long> channelIds) throws Exception {
		while (true) {
			List<Asset> assets = _assetDog.getAssets(channelIds, 0, 10000);

			if (assets.isEmpty()) {
				break;
			}

			assets.forEach(
				asset -> {
					Set<Long> assetChannelIds = asset.getChannelIds();

					assetChannelIds.removeAll(channelIds);

					if (assetChannelIds.isEmpty()) {
						_assetDog.deleteAsset(
							asset,
							DateUtil.newDayLocalDateTimeString(
								_timeZoneDog.getZoneId()));
					}
					else {
						_assetDog.updateAsset(asset);
					}
				});

			if (assets.size() < 10000) {
				break;
			}
		}
	}

	private void _deleteData(
		List<Long> channelIds, ElasticsearchInvoker elasticsearchInvoker,
		String... collectionNames) {

		for (String collectionName : collectionNames) {
			elasticsearchInvoker.delete(
				collectionName,
				QueryBuilders.termsQuery(
					"channelId", ListUtil.map(channelIds, String::valueOf)));
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
			QueryBuilders.termsQuery(
				"channelIds", ListUtil.map(channelIds, String::valueOf)));

		JSONArrayIterator.of(
			"individuals", elasticsearchInvoker,
			individualJSONObject -> {
				Individual individual = _objectMapper.convertValue(
					individualJSONObject, Individual.class);

				_updateActivitiesCounts(channelIds, individual);
				_updateChannelIds(channelIds, individual);
				_updateLastActivityDates(channelIds, individual);

				_individualDog.updateIndividual(individual);

				return null;
			}
		).setMonitoringConsumers(
			processedCountMonitorConsumer, queueMonitorConsumer
		).setQueryBuilder(
			boolQueryBuilder
		).iterate();
	}

	private Set<ChannelDataSource> _getChannelDataSources(
		Map<Long, Set<Long>> dataSources) {

		if (dataSources == null) {
			return Collections.emptySet();
		}

		Set<ChannelDataSource> channelDataSources = new HashSet<>();

		for (Map.Entry<Long, Set<Long>> entry : dataSources.entrySet()) {
			channelDataSources.add(
				new ChannelDataSource(entry.getKey(), entry.getValue()));
		}

		return channelDataSources;
	}

	private String _getChannelName(Long channelId, String name) {
		name = StringUtils.truncate(name, 65);

		int nameCount = 0;
		String originalName = name;

		while (_channelRepository.existsByIdNotAndName(channelId, name)) {
			name = String.format("%s (%d)", originalName, ++nameCount);
		}

		return name;
	}

	private String _getChannelName(String name) {
		name = StringUtils.truncate(name, 65);

		int nameCount = 0;
		String originalName = name;

		while (_channelRepository.existsByName(name)) {
			name = String.format("%s (%d)", originalName, ++nameCount);
		}

		return name;
	}

	private Sort _getSort(String[] sorts) {
		if (ArrayUtils.isEmpty(sorts)) {
			return Sort.by(Sort.Order.asc("name"));
		}

		List<Sort.Order> orders = new ArrayList<>();

		for (String sort : sorts) {
			String[] properties = sort.split(",");

			String property = properties[0];

			if (property.startsWith("name")) {
				property = property.split("\\.")[0];
			}

			if (Objects.equals(properties[1], "asc")) {
				orders.add(Sort.Order.asc(property));
			}
			else {
				orders.add(Sort.Order.desc(property));
			}
		}

		return Sort.by(orders);
	}

	private void _handleFaroError(Channel channel) {
		_channelRepository.delete(channel);

		throw new OSBAsahException(
			HttpStatus.BAD_REQUEST, "Unable to create channel");
	}

	private List<Channel> _saveCombinedChannel(
		Long dataSourceId, Set<Long> groupIds) {

		Optional<DataSource> dataSourceOptional =
			_dataSourceRepository.findById(dataSourceId);

		return Collections.singletonList(
			addChannel(
				Collections.singletonMap(dataSourceId, groupIds), false,
				_getChannelName(
					dataSourceOptional.map(
						DataSource::getName
					).get() + " Combined Property"),
				true));
	}

	private List<Channel> _saveMultipleChannel(
		Long dataSourceId, Map<Long, String> channelNamesByGroupIds) {

		List<Channel> channels = new ArrayList<>();

		for (Map.Entry<Long, String> entry :
				channelNamesByGroupIds.entrySet()) {

			channels.add(
				addChannel(
					Collections.singletonMap(
						dataSourceId, Collections.singleton(entry.getKey())),
					false, _getChannelName(entry.getValue()), true));
		}

		return channels;
	}

	private void _updateActivitiesCounts(
		List<Long> channelIds, Individual individual) {

		Set<Individual.ActivitiesCount> activitiesCounts =
			individual.getActivitiesCounts();

		if (activitiesCounts != null) {
			Iterator<Individual.ActivitiesCount> iterator =
				activitiesCounts.iterator();

			while (iterator.hasNext()) {
				Individual.ActivitiesCount activitiesCount = iterator.next();

				if (channelIds.contains(activitiesCount.getChannelId())) {
					iterator.remove();
				}
			}

			individual.setActivitiesCounts(activitiesCounts);
		}
	}

	private void _updateChannelIds(
		List<Long> channelIds, Individual individual) {

		Set<Long> oldChannelIds = individual.getChannelIds();

		Iterator<Long> iterator = oldChannelIds.iterator();

		while (iterator.hasNext()) {
			Long channelId = iterator.next();

			if (channelIds.contains(channelId)) {
				iterator.remove();
			}
		}

		individual.setChannelIds(oldChannelIds);
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
						propertyJSONObject.getLong("channelId"))) {

					iterator.remove();
				}
			}

			modifiedJSONObject.put(propertyName, jsonArray);
		}
	}

	private void _updateLastActivityDates(
		List<Long> channelIds, Individual individual) {

		Set<Individual.ActivityDate> lastActivityDates =
			individual.getLastActivityDates();

		if (lastActivityDates != null) {
			Iterator<Individual.ActivityDate> iterator =
				lastActivityDates.iterator();

			while (iterator.hasNext()) {
				Individual.ActivityDate activityDate = iterator.next();

				if (channelIds.contains(activityDate.getChannelId())) {
					iterator.remove();
				}
			}

			individual.setLastActivityDates(lastActivityDates);
		}
	}

	private static final String _CHANNEL_TYPE_MULTIPLE = "multiple";

	private static final Log _log = LogFactory.getLog(ChannelDog.class);

	@Autowired
	private ActivityGroupDog _activityGroupDog;

	@Autowired
	private AssetDog _assetDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private ChannelHttp _channelHttp;

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentDog _segmentDog;

	private final TimeOrderedUuidGenerator _timeOrderedUuidGenerator =
		new TimeOrderedUuidGenerator();

	@Autowired
	private TimeZoneDog _timeZoneDog;

}