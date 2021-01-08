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

import com.liferay.osb.asah.backend.rest.controller.IndividualsRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.hamcrest.CoreMatchers;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Leslie Wong
 */
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class IndividualsRestControllerTest {

	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "field-mappings", resourcePath = "field-mappings.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individual-segments", resourcePath = "individual-segments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetIndividualsDistribution() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_individuals_distribution_filtered.json",
				this),
			new JSONObject(
				_individualsRestController.getIndividualsDistribution(
					"331238762059664836", "(accountId eq '342313458385210529')",
					10, 100, null)),
			true);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_individuals_distribution_filtered.json",
				this),
			new JSONObject(
				_individualsRestController.getIndividualsDistribution(
					"331238762059664836",
					"(individualSegmentIds eq '327968823603500655')", 10, 100,
					null)),
			true);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_individuals_distribution_sorted.json",
				this),
			new JSONObject(
				_individualsRestController.getIndividualsDistribution(
					"331238757947565158", null, 10, 100,
					new String[] {"name", "desc"})),
			true);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_individuals_numbers_distribution.json",
				this),
			new JSONObject(
				_individualsRestController.getIndividualsDistribution(
					"331238762059664836", null, 5, 100, null)),
			true);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_individuals_terms_distribution.json",
				this),
			new JSONObject(
				_individualsRestController.getIndividualsDistribution(
					"331238757947565158", null, 10, 100, null)),
			true);
	}

	@ElasticsearchIndex(
		name = "field-mappings", resourcePath = "field-mappings.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individual-segments", resourcePath = "individual-segments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test(expected = Exception.class)
	public void testGetIndividualsDistributionInvalidFieldMappings()
		throws Exception {

		try {
			_individualsRestController.getIndividualsDistribution(
				"331238757947565234", null, 10, 100, null);
		}
		catch (Exception e) {
			Assert.assertThat(
				e.getMessage(),
				CoreMatchers.containsString("Invalid field mapping ID"));

			throw e;
		}
	}

	@Autowired
	private IndividualsRestController _individualsRestController;

}