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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.ChannelDataSource;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.http.ChannelHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author Geyson Silva
 */
@Component
public class ChannelDog {

	public Channel addChannel(
		Map<Long, Set<Long>> dataSources, boolean defaultChannel, String name,
		boolean updateFaro) {

		Channel channel = new Channel(_getChannelName(name));

		channel.setChannelDataSources(_getChannelDataSources(dataSources));
		channel.setDefaultChannel(defaultChannel);
		channel.setIsNew(Boolean.TRUE);

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

		_deleteAssets(channelIds);

		// TODO delete data

		_deleteIndividualReferences(
			channelIds, processedCountMonitorConsumer, queueMonitorConsumer);

		List<Segment> segments = new ArrayList<>();

		int page = 0;

		while (true) {
			List<Segment> curSegments = _segmentRepository.findByChannelIdIn(
				new HashSet<>(channelIds), PageRequest.of(page++, 500));

			if (curSegments.isEmpty()) {
				break;
			}

			segments.addAll(curSegments);
		}

		if (segments.isEmpty()) {
			return;
		}

		_segmentRepository.deleteAll(segments);

		_asahTaskDog.scheduleAsahTask(
			"DeleteIndividualSegmentTasksNanite",
			JSONUtil.put(
				"individualSegmentIds",
				JSONUtil.toJSONArray(segments, Segment::getId)));
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

	public Page<Channel> getChannelPage(@Nullable String name) {
		return new PageImpl<>(
			_channelRepository.findByNameContainingIgnoreCaseAndStateNot(
				StringUtil.get(name), null, "IN_PROGRESS_DELETING"));
	}

	public Page<Channel> getChannelPage(
		@Nullable String name, int page, int size, String[] sorts) {

		return PageableExecutionUtils.getPage(
			_channelRepository.findByNameContainingIgnoreCaseAndStateNot(
				StringUtil.get(name),
				PageRequest.of(page, size, _getSort(sorts)),
				"IN_PROGRESS_DELETING"),
			PageRequest.of(page, size, _getSort(sorts)),
			() -> _channelRepository.countByNameContainingIgnoreCaseAndStateNot(
				StringUtil.get(name), "IN_PROGRESS_DELETING"));
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
				new ChannelDataSource(null, dataSourceId, groupIds));

			channel.setChannelDataSources(channelChannelDataSources);
		}

		return _channelRepository.save(channel);
	}

	public Channel patchChannel(
		Long channelId, Set<Long> commerceChannelIds, Long dataSourceId,
		Set<Long> groupIds, String name) {

		Channel channel = getChannel(channelId);

		if (StringUtils.isNotBlank(name)) {
			channel.setName(_getChannelName(channelId, name));
		}

		if ((dataSourceId != null) &&
			((groupIds != null) || (commerceChannelIds != null))) {

			Set<ChannelDataSource> channelChannelDataSources =
				channel.getChannelDataSources();

			channelChannelDataSources.removeIf(
				channelDataSource -> Objects.equals(
					channelDataSource.getDataSourceId(), dataSourceId));

			channelChannelDataSources.add(
				new ChannelDataSource(
					commerceChannelIds, dataSourceId, groupIds));

			channel.setChannelDataSources(channelChannelDataSources);
		}

		return _channelRepository.save(channel);
	}

	public Channel update(Channel channel) {
		return _channelRepository.save(channel);
	}

	public void updateState(List<Long> channelIds, String state) {
		Iterable<Channel> channels = _channelRepository.findAllById(channelIds);

		for (Channel channel : channels) {
			channel.setState(state);
		}

		_channelRepository.saveAll(channels);
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

	private void _deleteIndividualReferences(
			List<Long> channelIds,
			Consumer<Integer> processedCountMonitorConsumer,
			Consumer<Integer> queueMonitorConsumer)
		throws Exception {

		// TODO delete individual references

	}

	private Set<ChannelDataSource> _getChannelDataSources(
		Map<Long, Set<Long>> dataSources) {

		if (dataSources == null) {
			return Collections.emptySet();
		}

		Set<ChannelDataSource> channelDataSources = new HashSet<>();

		for (Map.Entry<Long, Set<Long>> entry : dataSources.entrySet()) {
			channelDataSources.add(
				new ChannelDataSource(
					Collections.emptySet(), entry.getKey(), entry.getValue()));
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

		for (int i = 0; i < sorts.length; i++) {
			String sort = sorts[i];

			String order = null;

			String[] properties = sort.split(",");

			if (properties.length == 1) {
				order = sorts[++i];
			}
			else {
				order = properties[1];
			}

			if (Objects.equals(order, "asc")) {
				orders.add(
					Sort.Order.asc(StringUtils.lowerCase(properties[0])));
			}
			else {
				orders.add(
					Sort.Order.desc(StringUtils.lowerCase(properties[0])));
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

	private static final String _CHANNEL_TYPE_MULTIPLE = "multiple";

	private static final Log _log = LogFactory.getLog(ChannelDog.class);

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private AssetDog _assetDog;

	@Autowired
	private ChannelHttp _channelHttp;

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private SegmentRepository _segmentRepository;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}