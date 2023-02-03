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

package com.liferay.osb.asah.upgrade.v4_0_0.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.spring.TestExecutionListenerUtil;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.upgrade.v4_0_0.SegmentMigrationUpgradeStep;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Robson Pastor
 */
public class SegmentMigrationUpgradeStepTest
	implements OSBAsahUpgradeSpringTestContext {

	@BeforeEach
	public void setUp() throws Exception {
		ProjectIdThreadLocal.setProjectId("test");

		Channel channel = new Channel();

		channel.setId(1L);
		channel.setIsNew(Boolean.TRUE);
		channel.setName("Liferay");

		_channelRepository.save(channel);

		_elasticsearchIndexManager.delete(
			"test_osbasahfaroinfo_individual-segments");

		_elasticsearchIndexManager.create(
			ResourceUtil.readResourceToString(
				"dependencies/individual_segments_index_configuration.json",
				this),
			"test_osbasahfaroinfo_individual-segments");

		_elasticsearchIndexManager.addAlias(
			"test_osbasahfaroinfo_individual-segments_alias",
			"test_osbasahfaroinfo_individual-segments");
	}

	@AfterEach
	public void tearDown() throws Exception {
		_elasticsearchIndexManager.delete(
			"test_osbasahfaroinfo_individual-segments");

		_segmentRepository.deleteAll();

		_channelRepository.deleteAll();
	}

	@Test
	public void testUpgrade() throws Exception {
		JSONArray jsonArray = new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					"dependencies/individual_segments.json", this)));

		Assertions.assertFalse(jsonArray.isEmpty());

		_elasticsearchInvoker.add("individual-segments", jsonArray);

		_segmentMigrationUpgradeStep.upgrade("");

		List<Segment> segments = JSONUtil.toList(
			jsonArray,
			jsonObject -> _objectMapper.convertValue(
				jsonObject, Segment.class));

		Collections.sort(segments, Comparator.comparing(Segment::getId));

		Assertions.assertEquals(segments, _segmentRepository.findAll());

		Assertions.assertTrue(_segmentMigrationUpgradeStep.isSequenceSync());
	}

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentMigrationUpgradeStep _segmentMigrationUpgradeStep;

	@Autowired
	private SegmentRepository _segmentRepository;

}