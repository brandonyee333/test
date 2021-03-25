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

import com.liferay.osb.asah.backend.rest.controller.ReportRestController;
import com.liferay.osb.asah.common.http.ReportHttp;
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
public class ReportHttpImplTest {

	@Before
	public void setUp() {
		_reportHttp = new ReportHttpImpl();

		ReflectionTestUtils.setField(
			_reportHttp, "_objectMapper", new ObjectMapper());
		ReflectionTestUtils.setField(
			_reportHttp, "_reportRestController", _reportRestController);
	}

	@Test
	public void testGetAccountsJSONObject() {
		String after = RandomTestUtil.randomString();

		_reportHttp.getAccountsJSONObject(after);

		Mockito.verify(
			_reportRestController
		).getAccountResultBag(
			after
		);
	}

	@Test
	public void testGetIndividualsJSONObject() {
		String after = RandomTestUtil.randomString();

		_reportHttp.getIndividualsJSONObject(after);

		Mockito.verify(
			_reportRestController
		).getIndividualResultBag(
			after
		);
	}

	@Test
	public void testGetSegmentsJSONObject() {
		String after = RandomTestUtil.randomString();

		_reportHttp.getSegmentsJSONObject(after);

		Mockito.verify(
			_reportRestController
		).getSegmentDTOPageDTO(
			after
		);
	}

	private ReportHttp _reportHttp;

	@Mock
	private ReportRestController _reportRestController;

}