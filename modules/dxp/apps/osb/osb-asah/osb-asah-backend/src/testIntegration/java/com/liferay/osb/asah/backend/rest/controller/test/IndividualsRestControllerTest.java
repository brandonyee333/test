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

import com.liferay.osb.asah.backend.dto.IndividualDTO;
import com.liferay.osb.asah.backend.dto.PageDTO;
import com.liferay.osb.asah.backend.dto.SegmentDTO;
import com.liferay.osb.asah.backend.rest.controller.IndividualsRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Map;

import org.hamcrest.CoreMatchers;

import org.json.JSONArray;
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
		name = "individual-segments",
		resourcePath = "individual_segments_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "memberships", resourcePath = "memberships_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testActiveMembershipReturned() {
		PageDTO<SegmentDTO> segmentDTOsPageDTOs =
			_individualsRestController.getSegmentDTOsPageDTOs(
				123L, "active-membership", null, 0, 10, null);

		JSONObject segmentsJSONObject = _objectMapper.convertValue(
			segmentDTOsPageDTOs, JSONObject.class);

		JSONObject embeddedJSONObject = segmentsJSONObject.getJSONObject(
			"_embedded");

		JSONArray segmentsJSONArray = embeddedJSONObject.getJSONArray(
			"individual-segments");

		Map<String, JSONObject> jsonObjectMap = JSONUtil.toJSONObjectMap(
			segmentsJSONArray, "id");

		JSONObject segmentJSONObject = jsonObjectMap.get("910");

		embeddedJSONObject = segmentJSONObject.getJSONObject("_embedded");

		JSONArray activeMembershipsJSONArray = embeddedJSONObject.getJSONArray(
			"active-membership");

		JSONObject activeMembershipJSONObject =
			activeMembershipsJSONArray.getJSONObject(0);

		Assert.assertEquals(
			"123", activeMembershipJSONObject.getString("individualId"));
		Assert.assertEquals(
			"910", activeMembershipJSONObject.getString("individualSegmentId"));
		Assert.assertEquals(
			"ACTIVE", activeMembershipJSONObject.getString("status"));
	}

	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts_3.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "fields", resourcePath = "fields_3.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testExpandAccountNames() throws Exception {
		IndividualDTO individualDTO =
			_individualsRestController.getIndividualDTO(
				346468649722790279L, null, "account-names");

		Map<String, Object> embedded = individualDTO.getEmbedded();

		JSONArray accountNamesJSONArray = (JSONArray)embedded.get(
			"account-names");

		Assert.assertEquals(4, accountNamesJSONArray.length());
		Assert.assertTrue(
			JSONUtil.hasValue(accountNamesJSONArray, "Liferay, Inc."));
		Assert.assertTrue(
			JSONUtil.hasValue(accountNamesJSONArray, "Nozomi Project"));
		Assert.assertTrue(
			JSONUtil.hasValue(accountNamesJSONArray, "The Space Program"));
		Assert.assertTrue(
			JSONUtil.hasValue(
				accountNamesJSONArray, "The World's Foremost Chess Club"));
	}

	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts_3.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testExpandAccounts() throws Exception {
		IndividualDTO individualDTO =
			_individualsRestController.getIndividualDTO(
				346468649722790279L, null, "accounts");

		Map<String, Object> embedded = individualDTO.getEmbedded();

		JSONArray accountsJSONArray = (JSONArray)embedded.get("accounts");

		Assert.assertEquals(4, accountsJSONArray.length());
	}

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testExpandDataSources() throws Exception {
		IndividualDTO individualDTO =
			_individualsRestController.getIndividualDTO(
				123L, null, "data-sources");

		Map<String, Object> embedded = individualDTO.getEmbedded();

		JSONArray dataSourcesJSONArray = (JSONArray)embedded.get(
			"data-sources");

		Assert.assertEquals(2, dataSourcesJSONArray.length());
	}

	@ElasticsearchIndex(
		name = "individual-segments",
		resourcePath = "individual_segments_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "memberships", resourcePath = "memberships_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testExpandIndividualSegments() throws Exception {
		IndividualDTO individualDTO =
			_individualsRestController.getIndividualDTO(
				123L, null, "individual-segments");

		Map<String, Object> embedded = individualDTO.getEmbedded();

		JSONArray individualSegmentsJSONArray = (JSONArray)embedded.get(
			"individual-segments");

		Assert.assertEquals(2, individualSegmentsJSONArray.length());
	}

	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "field-mappings", resourcePath = "field_mappings.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individual-segments", resourcePath = "individual_segments.json",
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
			_objectMapper.convertValue(
				_individualsRestController.getDistributionDTOsPageDTO(
					366588394714972833L, "(accountId eq '342313458385210529')",
					10, 100, null),
				JSONObject.class),
			false);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_individuals_distribution_filtered.json",
				this),
			_objectMapper.convertValue(
				_individualsRestController.getDistributionDTOsPageDTO(
					366588394714972833L,
					"(individualSegmentIds eq '327968823603500655')", 10, 100,
					null),
				JSONObject.class),
			false);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_individuals_distribution_sorted.json",
				this),
			_objectMapper.convertValue(
				_individualsRestController.getDistributionDTOsPageDTO(
					331238757947565158L, null, 10, 100,
					new String[] {"name", "desc"}),
				JSONObject.class),
			false);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_individuals_numbers_distribution.json",
				this),
			_objectMapper.convertValue(
				_individualsRestController.getDistributionDTOsPageDTO(
					366588394714972833L, null, 5, 100, null),
				JSONObject.class),
			false);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_individuals_terms_distribution.json",
				this),
			_objectMapper.convertValue(
				_individualsRestController.getDistributionDTOsPageDTO(
					331238757947565158L, null, 10, 100, null),
				JSONObject.class),
			false);
	}

	@ElasticsearchIndex(
		name = "field-mappings", resourcePath = "field_mappings.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individual-segments", resourcePath = "individual_segments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test(expected = Exception.class)
	public void testGetIndividualsDistributionInvalidFieldMappings()
		throws Exception {

		try {
			_individualsRestController.getDistributionDTOsPageDTO(
				331238757947565234L, null, 10, 100, null);
		}
		catch (Exception exception) {
			Assert.assertThat(
				exception.getMessage(),
				CoreMatchers.containsString("Invalid field mapping ID"));

			throw exception;
		}
	}

	@ElasticsearchIndex(
		name = "individual-segments",
		resourcePath = "individual_segments_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "memberships", resourcePath = "memberships_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testInactiveMembershipNotReturned() {
		PageDTO<SegmentDTO> segmentDTOsPageDTOs =
			_individualsRestController.getSegmentDTOsPageDTOs(
				456L, "active-membership", null, 0, 10, null);

		Map<String, SegmentDTO> contents = segmentDTOsPageDTOs.getContent();

		Assert.assertNull(contents.get("910"));
	}

	@Autowired
	private IndividualsRestController _individualsRestController;

	@Autowired
	private ObjectMapper _objectMapper;

}