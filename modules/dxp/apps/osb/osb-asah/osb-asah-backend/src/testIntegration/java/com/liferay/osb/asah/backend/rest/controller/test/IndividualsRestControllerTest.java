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
import com.liferay.osb.asah.backend.dto.IndividualDTO;
import com.liferay.osb.asah.backend.dto.PageDTO;
import com.liferay.osb.asah.backend.dto.SegmentDTO;
import com.liferay.osb.asah.backend.rest.controller.IndividualsRestController;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.BQMembershipRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Leslie Wong
 */
public class IndividualsRestControllerTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@ElasticsearchIndex(
		name = "individual-segments",
		resourcePath = "individual_segments_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = BQMembershipRepository.class,
		resourcePath = "osbasahfaroinfo/bq_memberships_2.json"
	)
	@Test
	public void testActiveMembershipReturned() {
		PageDTO<SegmentDTO> segmentDTOPageDTO =
			_individualsRestController.getSegmentDTOPageDTO(
				123L, "active-membership", null, 0, 10, null);

		JSONObject segmentsJSONObject = _objectMapper.convertValue(
			segmentDTOPageDTO, JSONObject.class);

		JSONObject embeddedJSONObject = segmentsJSONObject.getJSONObject(
			"_embedded");

		JSONArray segmentsJSONArray = embeddedJSONObject.getJSONArray(
			"individual-segments");

		Map<String, JSONObject> jsonObjectMap = JSONUtil.toJSONObjectMap(
			segmentsJSONArray, "id");

		JSONObject segmentJSONObject = jsonObjectMap.get("910");

		embeddedJSONObject = segmentJSONObject.getJSONObject("_embedded");

		JSONObject activeMembershipJSONObject =
			embeddedJSONObject.getJSONObject("active-membership");

		Assertions.assertEquals(
			"123", activeMembershipJSONObject.getString("individualId"));
		Assertions.assertEquals(
			"910", activeMembershipJSONObject.getString("individualSegmentId"));
		Assertions.assertEquals(
			"ACTIVE", activeMembershipJSONObject.getString("status"));
	}

	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts_3.json",
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
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources_2.json"
	)
	@Test
	public void testExpandAccountNames() throws Exception {
		IndividualDTO individualDTO =
			_individualsRestController.getIndividualDTO(
				346468649722790279L, null, "account-names");

		Map<String, Object> embedded = individualDTO.getEmbedded();

		JSONArray accountNamesJSONArray = (JSONArray)embedded.get(
			"account-names");

		Assertions.assertEquals(4, accountNamesJSONArray.length());
		Assertions.assertTrue(
			JSONUtil.hasValue(accountNamesJSONArray, "Liferay, Inc."));
		Assertions.assertTrue(
			JSONUtil.hasValue(accountNamesJSONArray, "Nozomi Project"));
		Assertions.assertTrue(
			JSONUtil.hasValue(accountNamesJSONArray, "The Space Program"));
		Assertions.assertTrue(
			JSONUtil.hasValue(
				accountNamesJSONArray, "The World's Foremost Chess Club"));
	}

	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts_3.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources_2.json"
	)
	@Test
	public void testExpandAccounts() throws Exception {
		IndividualDTO individualDTO =
			_individualsRestController.getIndividualDTO(
				346468649722790279L, null, "accounts");

		Map<String, Object> embedded = individualDTO.getEmbedded();

		JSONArray accountsJSONArray = (JSONArray)embedded.get("accounts");

		Assertions.assertEquals(4, accountsJSONArray.length());
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources_2.json"
	)
	@Test
	public void testExpandDataSources() throws Exception {
		IndividualDTO individualDTO =
			_individualsRestController.getIndividualDTO(
				123L, null, "data-sources");

		Map<String, Object> embedded = individualDTO.getEmbedded();

		JSONArray dataSourcesJSONArray = (JSONArray)embedded.get(
			"data-sources");

		Assertions.assertEquals(2, dataSourcesJSONArray.length());
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
	@RepositoryResource(
		repositoryClass = BQMembershipRepository.class,
		resourcePath = "osbasahfaroinfo/bq_memberships_2.json"
	)
	@Test
	public void testExpandIndividualSegments() throws Exception {
		IndividualDTO individualDTO =
			_individualsRestController.getIndividualDTO(
				123L, null, "individual-segments");

		Map<String, Object> embedded = individualDTO.getEmbedded();

		JSONArray individualSegmentsJSONArray = (JSONArray)embedded.get(
			"individual-segments");

		Assertions.assertEquals(2, individualSegmentsJSONArray.length());
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetIndividualDTOPageDTO() throws Exception {
		PageDTO<IndividualDTO> individualDTOPageDTO =
			_individualsRestController.getIndividualDTOPageDTO(
				100L, null, null, false, 0, 1, null);

		Map<String, IndividualDTO> contents = individualDTOPageDTO.getContent();

		IndividualDTO individualDTO = contents.get("_embedded");

		Set<IndividualDTO> individualDTOs = individualDTO.getIndividualDTOs();

		Assertions.assertEquals(
			1, individualDTOs.size(), individualDTOs.toString());

		Stream<IndividualDTO> stream = individualDTOs.stream();

		individualDTO = stream.findFirst(
		).orElse(
			null
		);

		Assertions.assertEquals(1, (long)individualDTO.getActivitiesCount());
		Assertions.assertEquals(
			DateUtil.toUTCDate("2019-03-11T17:10:00.666Z"),
			individualDTO.getLastActivityDate());
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
				_individualsRestController.getDistributionDTOPageDTO(
					366588394714972833L, "(accountId eq '342313458385210529')",
					10, 100, null),
				JSONObject.class),
			false);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_individuals_distribution_filtered.json",
				this),
			_objectMapper.convertValue(
				_individualsRestController.getDistributionDTOPageDTO(
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
				_individualsRestController.getDistributionDTOPageDTO(
					331238757947565158L, null, 10, 100,
					new String[] {"name", "desc"}),
				JSONObject.class),
			false);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_individuals_numbers_distribution.json",
				this),
			_objectMapper.convertValue(
				_individualsRestController.getDistributionDTOPageDTO(
					366588394714972833L, null, 5, 100, null),
				JSONObject.class),
			false);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_individuals_terms_distribution.json",
				this),
			_objectMapper.convertValue(
				_individualsRestController.getDistributionDTOPageDTO(
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
	@Test
	public void testGetIndividualsDistributionInvalidFieldMappings() {
		Exception exception = Assertions.assertThrows(
			Exception.class,
			() -> _individualsRestController.getDistributionDTOPageDTO(
				331238757947565234L, null, 10, 100, null));

		MatcherAssert.assertThat(
			exception.getMessage(),
			CoreMatchers.containsString("Invalid field mapping ID"));
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
	@RepositoryResource(
		repositoryClass = BQMembershipRepository.class,
		resourcePath = "osbasahfaroinfo/bq_memberships_2.json"
	)
	@Test
	public void testInactiveMembershipNotReturned() {
		PageDTO<SegmentDTO> segmentDTOPageDTO =
			_individualsRestController.getSegmentDTOPageDTO(
				456L, "active-membership", null, 0, 10, null);

		Map<String, SegmentDTO> contents = segmentDTOPageDTO.getContent();

		Assertions.assertNull(contents.get("910"));
	}

	@Autowired
	private IndividualsRestController _individualsRestController;

	@Autowired
	private ObjectMapper _objectMapper;

}