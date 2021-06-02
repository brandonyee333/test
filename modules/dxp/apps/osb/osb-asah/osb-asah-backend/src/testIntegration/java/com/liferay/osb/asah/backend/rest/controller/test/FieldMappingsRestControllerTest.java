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

import com.liferay.osb.asah.backend.dto.FieldMappingDTO;
import com.liferay.osb.asah.backend.rest.controller.FieldMappingsRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Collections;

import org.hamcrest.CoreMatchers;

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
		name = "data-sources", resourcePath = "data_sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test(expected = Exception.class)
	public void testDuplicateFieldMappingFieldName() {
		FieldMapping fieldMapping =
			FaroInfoTestUtil.buildIndividualFieldMapping(
				Collections.singletonMap("351238757269547424", "givenName"),
				"givenName", "Text");

		FieldMappingDTO fieldMappingDTO = _objectMapper.convertValue(
			fieldMapping, FieldMappingDTO.class);

		_fieldMappingsRestController.postFieldMapping(fieldMappingDTO);

		try {
			_fieldMappingsRestController.postFieldMapping(fieldMappingDTO);
		}
		catch (Exception exception) {
			Assert.assertThat(
				exception.getMessage(),
				CoreMatchers.containsString("Duplicate field name"));

			throw exception;
		}
	}

	@Autowired
	private FieldMappingsRestController _fieldMappingsRestController;

	@Autowired
	private ObjectMapper _objectMapper;

}