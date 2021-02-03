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

package com.liferay.osb.asah.common.elasticsearch.repository.impl;

import com.google.api.client.util.Objects;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Channel;
import com.liferay.osb.asah.common.model.ChannelDataSource;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.rest.response.CollectionGetResponse;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

/**
 * @author Inácio Nery
 */
@ConditionalOnProperty(
	havingValue = "false", matchIfMissing = true,
	value = "osb.asah.postgresql.enabled"
)
@Repository
public class ElasticsearchChannelRepositoryImpl implements ChannelRepository {

	@Override
	public long count() {
		return _faroInfoElasticsearchInvoker.count(
			"channels", QueryBuilders.boolQuery());
	}

	@Override
	public Long countByNameContainingIgnoreCase(String name) {
		return _faroInfoElasticsearchInvoker.count(
			"channels", QueryUtil.buildSearchQueryBuilder("name.search", name));
	}

	@Override
	public void delete(Channel channel) {
		_faroInfoElasticsearchInvoker.delete(
			"channels", String.valueOf(channel.getId()));
	}

	@Override
	public void deleteAll() {
		_faroInfoElasticsearchInvoker.delete(
			"channels", QueryBuilders.boolQuery());
	}

	@Override
	public void deleteAll(Iterable<? extends Channel> channels) {
		Stream<? extends Channel> stream = StreamSupport.stream(
			channels.spliterator(), false);

		_faroInfoElasticsearchInvoker.deleteByQuery(
			QueryBuilders.termsQuery(
				"id",
				stream.map(
					Channel::getId
				).map(
					String::valueOf
				).collect(
					Collectors.toList()
				)),
			true, "channels");
	}

	@Override
	public void deleteById(Long channelId) {
		_faroInfoElasticsearchInvoker.delete(
			"channels", String.valueOf(channelId));
	}

	@Override
	public void deleteByIdIn(Set<Long> ids) {
		_faroInfoElasticsearchInvoker.deleteByQuery(
			QueryBuilders.termsQuery(
				"id",
				Stream.of(
					ids
				).flatMap(
					Set::stream
				).map(
					String::valueOf
				).collect(
					Collectors.toList()
				)),
			true, "channels");
	}

	@Override
	public boolean existsById(Long channelId) {
		return _faroInfoElasticsearchInvoker.exists(
			"channels", String.valueOf(channelId));
	}

	@Override
	public boolean existsByIdNotAndNameIgnoreCase(Long id, String name) {
		return _faroInfoElasticsearchInvoker.exists(
			"channels",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("name", name)
			).mustNot(
				QueryBuilders.termQuery("id", id)
			));
	}

	@Override
	public boolean existsByNameIgnoreCase(String name) {
		return _faroInfoElasticsearchInvoker.exists(
			"channels", QueryBuilders.termQuery("name", name));
	}

	@Override
	public List<Channel> findAll() {
		return _toChannels(_faroInfoElasticsearchInvoker.get("channels"));
	}

	@Override
	public List<Channel> findAll(Pageable pageable) {
		return findByNameContainingIgnoreCase(null, pageable);
	}

	@Override
	public List<Channel> findAllById(Iterable<Long> channelIds) {
		Stream<Long> stream = StreamSupport.stream(
			channelIds.spliterator(), false);

		return _toChannels(
			_faroInfoElasticsearchInvoker.get(
				"channels",
				QueryBuilders.termsQuery(
					"id",
					stream.map(
						String::valueOf
					).collect(
						Collectors.toList()
					))));
	}

	@Override
	public List<Channel> findByDataSourceId(Long dataSourceId) {
		return _toChannels(
			_faroInfoElasticsearchInvoker.get(
				"channels",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"dataSources.id", String.valueOf(dataSourceId)))));
	}

	@Override
	public List<Channel> findByDataSourceIdAndGroupIds(
		Long dataSourceId, Set<Long> groupsIds) {

		return _toChannels(
			_faroInfoElasticsearchInvoker.get(
				"channels",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"dataSources.id", String.valueOf(dataSourceId))
				).filter(
					QueryBuilders.termsQuery(
						"dataSources.groupIds",
						Stream.of(
							groupsIds
						).flatMap(
							Set::stream
						).map(
							String::valueOf
						).collect(
							Collectors.toList()
						))
				)));
	}

	@Override
	public Optional<Channel> findById(Long channelId) {
		return Optional.ofNullable(
			_faroInfoElasticsearchInvoker.fetch(
				"channels", String.valueOf(channelId))
		).map(
			channelJSONObject -> _toChannel(channelJSONObject)
		);
	}

	@Override
	public List<Channel> findByNameContainingIgnoreCase(
		String name, Pageable pageable) {

		try {
			CollectionGetResponse collectionGetResponse =
				new CollectionGetResponse();

			collectionGetResponse.setCollectionName("channels");
			collectionGetResponse.setElasticsearchInvoker(
				_faroInfoElasticsearchInvoker);
			collectionGetResponse.setPage(pageable.getPageNumber());
			collectionGetResponse.setQueryBuilder(
				QueryUtil.buildSearchQueryBuilder("name.search", name));
			collectionGetResponse.setSize(pageable.getPageSize());

			List<String> sorts = new ArrayList<>();

			for (Sort.Order order : pageable.getSort()) {
				StringBuilder sb = new StringBuilder();

				sb.append(order.getProperty());

				if (Objects.equal(order.getProperty(), "name")) {
					sb.append(".sort");
				}

				sb.append(",");

				if (order.isAscending()) {
					sb.append("asc");
				}
				else {
					sb.append("desc");
				}

				sorts.add(sb.toString());
			}

			collectionGetResponse.setSorts(sorts.toArray(new String[0]));

			JSONObject jsonObject = new JSONObject(
				collectionGetResponse.respond());

			JSONObject embeddedJSONObject = jsonObject.getJSONObject(
				"_embedded");

			return _toChannels(embeddedJSONObject.getJSONArray("channels"));
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return Collections.emptyList();
		}
	}

	@Override
	public Channel save(Channel channel) {
		List<JSONObject> dataSourceJSONObjects = new ArrayList<>();

		for (ChannelDataSource channelDataSource :
				channel.getChannelDataSources()) {

			dataSourceJSONObjects.add(
				JSONUtil.put(
					"groupIds",
					Stream.of(
						channelDataSource.getGroupIds()
					).flatMap(
						Set::stream
					).map(
						String::valueOf
					).collect(
						Collectors.toList()
					)
				).put(
					"id", String.valueOf(channelDataSource.getDataSourceId())
				));
		}

		JSONObject channelJSONObject = JSONUtil.put(
			"dataSources", dataSourceJSONObjects
		).put(
			"dateCreated", DateUtil.toUTCString(channel.getCreateDate())
		).put(
			"name", channel.getName()
		);

		if (channel.getId() != null) {
			channelJSONObject.put("id", String.valueOf(channel.getId()));
		}

		return _toChannel(
			_faroInfoElasticsearchInvoker.add("channels", channelJSONObject));
	}

	@Override
	public <S extends Channel> Iterable<S> saveAll(Iterable<S> channels) {
		Stream<S> stream = StreamSupport.stream(channels.spliterator(), false);

		return (Iterable<S>)stream.map(this::save);
	}

	private Channel _toChannel(JSONObject channelJSONObject) {
		Channel channel = new Channel();

		JSONArray dataSourcesJSONArray = channelJSONObject.getJSONArray(
			"dataSources");

		for (int j = 0; j < dataSourcesJSONArray.length(); j++) {
			JSONObject dataSourceJSONObject =
				dataSourcesJSONArray.getJSONObject(j);

			ChannelDataSource channelDataSource = new ChannelDataSource();

			channelDataSource.setDataSourceId(
				dataSourceJSONObject.getLong("id"));
			channelDataSource.setGroupIds(
				JSONUtil.toLongSet(
					dataSourceJSONObject.getJSONArray("groupIds")));

			channel.addChannelDataSource(channelDataSource);
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

	private static final Log _log = LogFactory.getLog(
		ElasticsearchChannelRepositoryImpl.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}