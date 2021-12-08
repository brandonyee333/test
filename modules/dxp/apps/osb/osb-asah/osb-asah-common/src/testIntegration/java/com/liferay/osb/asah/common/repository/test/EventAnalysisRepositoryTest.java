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

package com.liferay.osb.asah.common.repository.test;

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.entity.EventAnalysis;
import com.liferay.osb.asah.common.repository.EventAnalysisRepository;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;

/**
 * @author Rachael Koestartyo
 */
@Import(JDBCTestConfiguration.class)
public class EventAnalysisRepositoryTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@SQLResource(resourcePath = "test_event_analysis.sql")
	@Test
	public void testCountEventAnalyses() {
		Assertions.assertEquals(
			1, _eventAnalysisRepository.countEventAnalyses(1L, "1"));
		Assertions.assertEquals(
			2, _eventAnalysisRepository.countEventAnalyses(1L, "Event"));
	}

	@SQLResource(resourcePath = "test_event_analysis.sql")
	@Test
	public void testDeleteByIdIn() {
		Assertions.assertEquals(2, _eventAnalysisRepository.count());

		_eventAnalysisRepository.deleteByIdIn(
			new HashSet<>(Arrays.asList(2345L, 2346L)));

		Assertions.assertEquals(0, _eventAnalysisRepository.count());
	}

	@SQLResource(resourcePath = "test_event_analysis.sql")
	@Test
	public void testSearchEventAnalyses() {
		List<EventAnalysis> eventAnalyses =
			_eventAnalysisRepository.searchEventAnalyses(
				1L, "1", PageRequest.of(0, 10));

		Assertions.assertEquals(
			1, eventAnalyses.size(), eventAnalyses.toString());

		eventAnalyses = _eventAnalysisRepository.searchEventAnalyses(
			1L, "Event", PageRequest.of(0, 10));

		Assertions.assertEquals(
			2, eventAnalyses.size(), eventAnalyses.toString());
	}

	@Autowired
	private EventAnalysisRepository _eventAnalysisRepository;

}