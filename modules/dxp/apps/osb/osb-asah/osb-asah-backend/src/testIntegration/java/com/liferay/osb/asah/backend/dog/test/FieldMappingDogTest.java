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

package com.liferay.osb.asah.backend.dog.test;

import com.liferay.osb.asah.backend.dog.FieldMappingDog;
import com.liferay.osb.asah.backend.model.FieldMapping;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Marcellus Tavares
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class FieldMappingDogTest {

	@ElasticsearchIndex(
		name = "field-mappings", resourcePath = "field_mappings_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testFieldMappingResultBag() {
		ResultBag<FieldMapping> fieldMappingResultBag =
			_fieldMappingDog.getFieldMappingResultBag(
				"demographics", "individual", 10, 0);

		Assert.assertEquals(2, fieldMappingResultBag.getTotal());
		Assert.assertEquals(
			Arrays.asList("email", "favoritePokemon"),
			_getFieldMappingsFieldNames(fieldMappingResultBag.getResults()));
	}

	private List<String> _getFieldMappingsFieldNames(
		List<FieldMapping> fieldMappings) {

		Stream<FieldMapping> stream = fieldMappings.stream();

		return stream.map(
			FieldMapping::getFieldName
		).collect(
			Collectors.toList()
		);
	}

	@Autowired
	private FieldMappingDog _fieldMappingDog;

}