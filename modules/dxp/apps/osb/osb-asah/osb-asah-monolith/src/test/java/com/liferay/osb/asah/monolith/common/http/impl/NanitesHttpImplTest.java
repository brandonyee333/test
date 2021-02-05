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

import com.liferay.osb.asah.batch.curator.rest.controller.NanitesRestController;
import com.liferay.osb.asah.common.http.NanitesHttp;

import org.json.JSONArray;
import org.json.JSONObject;

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
public class NanitesHttpImplTest {

	@Before
	public void setUp() {
		_nanitesHttp = new NanitesHttpImpl();

		ReflectionTestUtils.setField(
			_nanitesHttp, "_nanitesRestController", _nanitesRestController);
	}

	@Test
	public void testExecuteOSBAsahTask() {
		JSONObject jsonObject = new JSONObject();

		_nanitesHttp.executeOSBAsahTask(jsonObject);

		Mockito.verify(
			_nanitesRestController
		).post(
			jsonObject.toString()
		);
	}

	@Test
	public void testRefreshAnalytics() {
		_nanitesHttp.refreshAnalytics();

		Mockito.verify(
			_nanitesRestController
		).refreshAnalytics();
	}

	@Test
	public void testRemoveSchedule() {
		_nanitesHttp.removeSchedule();

		Mockito.verify(
			_nanitesRestController
		).removeSchedule();
	}

	@Test
	public void testRescheduleNanites() {
		_nanitesHttp.rescheduleNanites();

		Mockito.verify(
			_nanitesRestController
		).reschedule();
	}

	@Test
	public void testRun() {
		JSONArray jsonArray = new JSONArray();

		_nanitesHttp.run(jsonArray);

		Mockito.verify(
			_nanitesRestController
		).run(
			jsonArray.toString()
		);
	}

	@Test
	public void testScheduleOSBAsahTask() {
		JSONObject jsonObject = new JSONObject();

		_nanitesHttp.scheduleOSBAsahTask(jsonObject);

		Mockito.verify(
			_nanitesRestController
		).schedule(
			jsonObject.toString()
		);
	}

	@Test
	public void testUnscheduleOSBAsahTask() {
		JSONObject jsonObject = new JSONObject();

		_nanitesHttp.unscheduleOSBAsahTask(jsonObject);

		Mockito.verify(
			_nanitesRestController
		).unschedule(
			jsonObject.toString()
		);
	}

	private NanitesHttp _nanitesHttp;

	@Mock
	private NanitesRestController _nanitesRestController;

}