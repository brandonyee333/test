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
import com.liferay.osb.asah.common.dog.InterestTopicDog;
import com.liferay.osb.asah.common.entity.InterestTopic;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.repository.BlockedKeywordRepository;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Victor Oliveira
 */
public class InterestTopicsNaniteTest
	extends BaseNaniteTestCase implements OSBAsahTestExecutionListenersContext {

	@RepositoryResource(
		repositoryClass = AssetRepository.class,
		resourcePath = "osbasahfaroinfo/assets.json"
	)
	@Test
	public void test() throws Exception {
		_interestTopicsNanite.run(null);

		List<InterestTopic> interestTopics =
			_interestTopicDog.getInterestTopics();

		Assertions.assertFalse(interestTopics.isEmpty());

		InterestTopic interestTopic = interestTopics.get(0);

		Assertions.assertTrue(StringUtils.isNotEmpty(interestTopic.getTerm()));
		Assertions.assertTrue(
			StringUtils.isNotEmpty(interestTopic.getTermType()));
	}

	@RepositoryResource(
		repositoryClass = AssetRepository.class,
		resourcePath = "osbasahfaroinfo/assets.json"
	)
	@RepositoryResource(
		repositoryClass = BlockedKeywordRepository.class,
		resourcePath = "osbasahfaroinfo/blocked_keywords.json"
	)
	@Test
	public void testBlockedKeywords() throws Exception {
		_interestTopicsNanite.run(null);

		List<InterestTopic> interestTopics =
			_interestTopicDog.getInterestTopics();

		Assertions.assertFalse(_matchAny(interestTopics, "java"));
		Assertions.assertFalse(_matchAny(interestTopics, "jquery"));
		Assertions.assertTrue(_matchAny(interestTopics, "php"));
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