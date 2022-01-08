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

package com.liferay.osb.asah.upgrade.v3_1_0;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class ChannelMigrationUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

		searchSourceBuilder.searchAfter(new Object[] {_getChannelId(true)});
		searchSourceBuilder.sort("id");
		searchSourceBuilder.trackTotalHits(false);

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			"channels", searchSourceBuilder);

		SearchHits searchHits = searchResponse.getHits();

		SearchHit[] hits = searchHits.getHits();

		if (hits.length == 0) {
			return;
		}

		Stream<SearchHit> searchHitStream = Arrays.stream(hits);

		JSONArray channelsJSONArray = new JSONArray(
			searchHitStream.parallel(
			).map(
				SearchHit::getSourceAsString
			).map(
				JSONObject::new
			).collect(
				Collectors.toList()
			));

		List<Object> channelObjectList = channelsJSONArray.toList();

		Stream<Object> objectStream = channelObjectList.stream();

		objectStream.map(
			object -> _objectMapper.convertValue(object, Channel.class)
		).forEach(
			channel -> {
				channel.setIsNew(Boolean.TRUE);

				_channelRepository.save(channel);
			}
		);
	}

	private Long _getChannelId(boolean retry) {
		try {
			return Optional.ofNullable(
				_namedParameterJdbcTemplate.queryForObject(
					_SQL_SELECT_LATEST_CHANNEL_ID, Collections.emptyMap(),
					(rs, rowNum) -> rs.getLong("id"))
			).orElse(
				0L
			);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug("Select initial Event ID failed");
			}

			if (retry) {
				_log.error("Retrying...", exception);

				try {
					Thread.sleep(5000);
				}
				catch (InterruptedException interruptedException) {
					_log.error(interruptedException, interruptedException);
				}

				return _getChannelId(false);
			}

			return 0L;
		}
	}

	@PostConstruct
	private void _init() {
		_namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
			_dataSource);
	}

	private static final String _SQL_SELECT_LATEST_CHANNEL_ID =
		"SELECT id FROM channel ORDER BY id DESC LIMIT 1";

	private static final Log _log = LogFactory.getLog(
		ChannelMigrationUpgradeStep.class);

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private DataSource _dataSource;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	private NamedParameterJdbcTemplate _namedParameterJdbcTemplate;

	@Autowired
	private ObjectMapper _objectMapper;

}