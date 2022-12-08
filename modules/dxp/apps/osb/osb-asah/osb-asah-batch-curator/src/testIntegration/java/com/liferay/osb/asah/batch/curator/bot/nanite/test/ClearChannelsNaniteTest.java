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
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.http.ChannelHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @author André Miranda
 */
public class ClearChannelsNaniteTest
	extends BaseNaniteTestCase implements OSBAsahTestExecutionListenersContext {

	@Disabled
	@Test
	public void test() throws Exception {
		DataSource dataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSource());

		Assertions.assertNotNull(dataSource);

		Long dataSourceId = dataSource.getId();

		Long channelId = _dataSourceDog.fetchDefaultChannelId(dataSourceId);

		// TODO Add Individual with dataSourceId and channelId

		Asset asset = _assetRepository.save(
			_objectMapper.convertValue(
				FaroInfoTestUtil.buildAssetJSONObject(
					"Page", channelId, dataSourceId),
				Asset.class));

		_clearChannelsNanite.run(
			JSONUtil.put("channelIds", JSONUtil.put(channelId)));

		Optional<Asset> assetOptional = _assetRepository.findById(
			Optional.ofNullable(
				asset.getId()
			).orElse(
				0L
			));

		Assertions.assertFalse(assetOptional.isPresent());

		Assertions.assertTrue(_channelRepository.existsById(channelId));
		Assertions.assertTrue(_dataSourceRepository.existsById(dataSourceId));

		// TODO Assert individuals do not exist

		// TODO Assert individual count

		Assertions.assertEquals(1, 0);

		// TODO Test BQEvent removal

	}

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
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentDog _segmentDog;

	@Autowired
	private SegmentRepository _segmentRepository;

}