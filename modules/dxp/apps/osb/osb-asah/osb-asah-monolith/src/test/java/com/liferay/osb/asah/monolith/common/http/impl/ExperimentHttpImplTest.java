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

package com.liferay.osb.asah.monolith.common.http.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.ExperimentsRestController;
import com.liferay.osb.asah.common.http.ExperimentHttp;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author Geyson Silva
 */
@RunWith(MockitoJUnitRunner.class)
public class ExperimentHttpImplTest {

	@Before
	public void setUp() {
		_experimentHttp = new ExperimentHttpImpl();

		ReflectionTestUtils.setField(
			_experimentHttp, "_experimentsRestController",
			_experimentsRestController);
		ReflectionTestUtils.setField(
			_experimentHttp, "_objectMapper", new ObjectMapper());
	}

	@Test
	public void testGetExperimentMetricsJSONObject() {
		String id = RandomTestUtil.randomId();

		_experimentHttp.getExperimentMetricsJSONObject(id);

		Mockito.verify(
			_experimentsRestController
		).getCalculateExperimentMetric(
			Long.valueOf(id)
		);
	}

	private ExperimentHttp _experimentHttp;

	@Mock
	private ExperimentsRestController _experimentsRestController;

}