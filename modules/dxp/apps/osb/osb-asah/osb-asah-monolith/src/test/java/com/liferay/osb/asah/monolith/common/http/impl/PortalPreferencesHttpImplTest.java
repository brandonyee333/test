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

import com.liferay.osb.asah.common.http.PortalPreferencesHttp;
import com.liferay.osb.asah.dxp.extractor.rest.controller.PortalPreferencesRestController;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author Geyson Silva
 */
@RunWith(MockitoJUnitRunner.class)
public class PortalPreferencesHttpImplTest {

	@Before
	public void setUp() {
		_portalPreferencesHttp = new PortalPreferencesHttpImpl();

		ReflectionTestUtils.setField(
			_portalPreferencesHttp, "_portalPreferencesRestController",
			_portalPreferencesRestController);
	}

	@Test
	public void testUpdatePortalPreferences() {
		JSONObject jsonObject = new JSONObject();

		ResponseEntity<String> responseEntity =
			_portalPreferencesHttp.updatePortalPreferences(jsonObject);

		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		Mockito.verify(
			_portalPreferencesRestController
		).post(
			jsonObject.toString()
		);
	}

	private PortalPreferencesHttp _portalPreferencesHttp;

	@Mock
	private PortalPreferencesRestController _portalPreferencesRestController;

}