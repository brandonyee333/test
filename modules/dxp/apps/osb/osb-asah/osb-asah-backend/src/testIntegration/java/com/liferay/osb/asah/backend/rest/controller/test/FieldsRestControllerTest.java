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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.backend.OSBAsahBackendSpringTestContext;
import com.liferay.osb.asah.backend.dto.FieldDTO;
import com.liferay.osb.asah.backend.rest.controller.FieldsRestController;
import com.liferay.osb.asah.common.dog.FieldDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import org.json.JSONObject;

import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Vishal Reddy
 */
public class FieldsRestControllerTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@ElasticsearchIndex(
		name = "fields", resourcePath = "fields_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetField() {
		JSONAssert.assertEquals(
			_objectMapper.convertValue(
				new FieldDTO(_fieldDog.getField(357046433509858076L)),
				JSONObject.class),
			_objectMapper.convertValue(
				_fieldsRestController.getFieldDTO(357046433509858076L),
				JSONObject.class),
			false);
	}

	@ElasticsearchIndex(
		name = "fields", resourcePath = "fields_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetFields() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_fields.json", this),
			_objectMapper.convertValue(
				_fieldsRestController.getFieldDTOPageDTO(null, 0, 20, null),
				JSONObject.class),
			false);
	}

	@ElasticsearchIndex(
		name = "fields", resourcePath = "fields_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetFieldTransformations() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_field_transformations.json", this),
			_objectMapper.convertValue(
				_fieldsRestController.getTransformationDTOPageDTO(
					"groupby((name))", null, 0, 20),
				JSONObject.class),
			false);
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private FieldDog _fieldDog;

	@Autowired
	private FieldsRestController _fieldsRestController;

	@Autowired
	private ObjectMapper _objectMapper;

}