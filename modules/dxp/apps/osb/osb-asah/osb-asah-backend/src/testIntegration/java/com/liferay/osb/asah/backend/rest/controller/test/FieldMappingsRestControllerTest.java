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

package com.liferay.osb.asah.backend.rest.controller.test;

import com.liferay.osb.asah.backend.rest.controller.FieldMappingsRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.hamcrest.CoreMatchers;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Shinn Lok
 */
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class FieldMappingsRestControllerTest {

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data-sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test(expected = Exception.class)
	public void testDuplicateFieldMappingFieldName() throws Exception {
		JSONObject fieldMappingJSONObject =
			FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
				JSONUtil.put("351238757269547424", "givenName"), "givenName",
				"Text");

		String fieldMappingJSON = fieldMappingJSONObject.toString();

		_fieldMappingsRestController.postFieldMapping(fieldMappingJSON);

		try {
			_fieldMappingsRestController.postFieldMapping(fieldMappingJSON);
		}
		catch (Exception e) {
			Assert.assertThat(
				e.getMessage(),
				CoreMatchers.containsString("Duplicate field name"));

			throw e;
		}
	}

	@Autowired
	private FieldMappingsRestController _fieldMappingsRestController;

}