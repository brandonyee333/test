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

package com.liferay.osb.asah.batch.curator.bot.nanite.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.batch.curator.bot.nanite.ClearChannelsNanite;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.dog.AccountDog;
import com.liferay.osb.asah.common.dog.ActivityGroupDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.entity.ActivityGroup;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.http.ChannelHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.AccountRepository;
import com.liferay.osb.asah.common.repository.ActivityGroupRepository;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.IndividualRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Optional;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @author André Miranda
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBatchCuratorSpringBootApplication.class)
public class ClearChannelsNaniteTest extends BaseNaniteTestCase {

	@Test
	public void test() throws Exception {
		DataSource dataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSource());

		Assert.assertNotNull(dataSource);

		Account account = _accountDog.addAccount(
			FaroInfoTestUtil.buildAccount(dataSource));

		Long channelId = _dataSourceDog.getDefaultChannelId(dataSource.getId());

		_segmentDog.addSegment(
			FaroInfoTestUtil.buildAccountSegment(account, channelId));

		Individual individual = _individualDog.addIndividual(
			FaroInfoTestUtil.buildIndividual(channelId, dataSource), false);

		ActivityGroup activityGroup = _activityGroupDog.addActivityGroup(
			FaroInfoTestUtil.buildActivityGroup(
				channelId, dataSource.getId(), new Date(), individual));

		Asset asset = _assetRepository.save(
			_objectMapper.convertValue(
				FaroInfoTestUtil.buildAssetJSONObject(
					"Page", channelId, dataSource.getId()),
				Asset.class));

		JSONObject activityJSONObject = faroInfoElasticsearchInvoker.add(
			"activities",
			FaroInfoTestUtil.buildActivityJSONObject(
				_objectMapper.convertValue(activityGroup, JSONObject.class),
				_objectMapper.convertValue(asset, JSONObject.class), channelId,
				"pageViewed", new String[0]));

		_clearChannelsNanite.run(
			JSONUtil.put("channelIds", JSONUtil.put(channelId)));

		Assert.assertTrue(_accountRepository.existsById(account.getId()));
		Assert.assertFalse(
			faroInfoElasticsearchInvoker.exists(
				"activities", activityJSONObject.getString("id")));

		Long activityGroupId = activityGroup.getId();

		if (activityGroupId == null) {
			activityGroupId = 0L;
		}

		Assert.assertFalse(
			_activityGroupRepository.existsById(activityGroupId));

		Optional<Asset> assetOptional = _assetRepository.findById(
			Optional.ofNullable(
				asset.getId()
			).orElse(
				0L
			));

		Assert.assertFalse(assetOptional.isPresent());

		Assert.assertTrue(_channelRepository.existsById(channelId));
		Assert.assertTrue(_dataSourceRepository.existsById(dataSource.getId()));
		Assert.assertFalse(
			_segmentRepository.existsByName("Account: " + account.getId()));
		Assert.assertFalse(
			faroInfoElasticsearchInvoker.exists(
				"individuals",
				QueryBuilders.termQuery("channelIds", channelId)));
		Assert.assertEquals(1, _individualRepository.count());
	}

	@Autowired
	private AccountDog _accountDog;

	@Autowired
	private AccountRepository _accountRepository;

	@Autowired
	private ActivityGroupDog _activityGroupDog;

	@Autowired
	private ActivityGroupRepository _activityGroupRepository;

	@Autowired
	private AssetRepository _assetRepository;

	@MockBean
	private ChannelHttp _channelHttp;

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private ClearChannelsNanite _clearChannelsNanite;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private IndividualRepository _individualRepository;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentDog _segmentDog;

	@Autowired
	private SegmentRepository _segmentRepository;

}