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

import com.liferay.osb.asah.batch.curator.bot.nanite.InterestTopicsNanite;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.dog.InterestTopicDog;
import com.liferay.osb.asah.common.model.InterestTopic;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Victor Oliveira
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBatchCuratorSpringBootApplication.class)
public class InterestTopicsNaniteTest extends BaseNaniteTestCase {

	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void test() throws Exception {
		_interestTopicsNanite.run(null);

		List<InterestTopic> interestTopics =
			_interestTopicDog.getInterestTopics();

		Assert.assertFalse(interestTopics.isEmpty());

		InterestTopic interestTopic = interestTopics.get(0);

		Assert.assertTrue(StringUtils.isNotEmpty(interestTopic.getTerm()));
		Assert.assertTrue(StringUtils.isNotEmpty(interestTopic.getTermType()));
	}

	@ElasticsearchIndex(
		name = "blocked-keywords", resourcePath = "blocked_keywords.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testBlockedKeywords() throws Exception {
		_interestTopicsNanite.run(null);

		List<InterestTopic> interestTopics =
			_interestTopicDog.getInterestTopics();

		Assert.assertFalse(_matchAny(interestTopics, "java"));
		Assert.assertFalse(_matchAny(interestTopics, "jquery"));
		Assert.assertTrue(_matchAny(interestTopics, "php"));
	}

	private boolean _matchAny(List<InterestTopic> interestTopics, String term) {
		Stream<InterestTopic> stream = interestTopics.stream();

		Optional<InterestTopic> interestTopicOptional = stream.filter(
			interestTopic -> Objects.equals(interestTopic.getTerm(), term)
		).findAny();

		return interestTopicOptional.isPresent();
	}

	@Autowired
	private InterestTopicDog _interestTopicDog;

	@Autowired
	private InterestTopicsNanite _interestTopicsNanite;

}