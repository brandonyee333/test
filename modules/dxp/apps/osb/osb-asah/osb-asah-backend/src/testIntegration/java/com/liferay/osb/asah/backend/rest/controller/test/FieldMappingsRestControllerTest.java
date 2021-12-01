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
import com.liferay.osb.asah.backend.dto.FieldMappingDTO;
import com.liferay.osb.asah.backend.rest.controller.FieldMappingsRestController;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Collections;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Shinn Lok
 */
public class FieldMappingsRestControllerTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testDuplicateFieldMappingFieldName() {
		FieldMapping fieldMapping =
			FaroInfoTestUtil.buildIndividualFieldMapping(
				Collections.singletonMap("351238757269547424", "givenName"),
				"givenName", "Text");

		FieldMappingDTO fieldMappingDTO = _objectMapper.convertValue(
			fieldMapping, FieldMappingDTO.class);

		_fieldMappingsRestController.postFieldMapping(fieldMappingDTO);

		Exception exception = Assertions.assertThrows(
			Exception.class,
			() -> _fieldMappingsRestController.postFieldMapping(
				fieldMappingDTO));

		MatcherAssert.assertThat(
			exception.getMessage(),
			CoreMatchers.containsString("Duplicate field name"));
	}

	@Autowired
	private FieldMappingsRestController _fieldMappingsRestController;

	@Autowired
	private ObjectMapper _objectMapper;

}