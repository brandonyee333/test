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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.IndividualChannel;
import com.liferay.osb.asah.common.repository.IndividualChannelRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.stereotype.Repository;

/**
 * @author Rachael Koestartyo
 */
@Repository
public class ElasticsearchIndividualChannelRepositoryImpl
	implements IndividualChannelRepository {

	@Override
	public long count() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(IndividualChannel individualChannel) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteAll(
		Iterable<? extends IndividualChannel> individualChannels) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteById(Long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean existsById(Long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterable<IndividualChannel> findAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterable<IndividualChannel> findAllById(Iterable<Long> ids) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Optional<IndividualChannel> findById(Long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<IndividualChannel> findByIndividualId(Long individualId) {
		JSONObject individualJSONObject = _faroInfoElasticsearchInvoker.get(
			"individuals", String.valueOf(individualId));

		Map<String, IndividualChannel> individualChannelsMap = new HashMap<>();

		JSONArray activitiesCountsJSONArray = individualJSONObject.optJSONArray(
			"activitiesCounts");

		if (activitiesCountsJSONArray == null) {
			activitiesCountsJSONArray = new JSONArray();
		}

		for (int i = 0; i < activitiesCountsJSONArray.length(); i++) {
			JSONObject jsonObject = activitiesCountsJSONArray.getJSONObject(i);

			String channelId = jsonObject.getString("channelId");

			IndividualChannel individualChannel = new IndividualChannel();

			individualChannel.setActivitiesCount(
				jsonObject.optLong("activitiesCount", 0L));
			individualChannel.setChannelId(Long.valueOf(channelId));
			individualChannel.setIndividualId(individualId);

			individualChannelsMap.put(channelId, individualChannel);
		}

		JSONArray lastActivityDatesJSONArray =
			individualJSONObject.optJSONArray("lastActivityDates");

		if (lastActivityDatesJSONArray == null) {
			lastActivityDatesJSONArray = new JSONArray();
		}

		for (int i = 0; i < lastActivityDatesJSONArray.length(); i++) {
			JSONObject jsonObject = lastActivityDatesJSONArray.getJSONObject(i);

			String channelId = jsonObject.getString("channelId");

			IndividualChannel individualChannel = new IndividualChannel();

			if (individualChannelsMap.containsKey(channelId)) {
				individualChannel = individualChannelsMap.get(channelId);
			}
			else {
				individualChannel.setChannelId(Long.valueOf(channelId));
				individualChannel.setIndividualId(individualId);
			}

			individualChannel.setLastActivityDate(
				DateUtil.toUTCDate(jsonObject.getString("lastActivityDate")));

			individualChannelsMap.put(channelId, individualChannel);
		}

		return new ArrayList<>(individualChannelsMap.values());
	}

	@Override
	public <S extends IndividualChannel> S save(S individualChannel) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <S extends IndividualChannel> Iterable<S> saveAll(
		Iterable<S> individualChannels) {

		throw new UnsupportedOperationException();
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}