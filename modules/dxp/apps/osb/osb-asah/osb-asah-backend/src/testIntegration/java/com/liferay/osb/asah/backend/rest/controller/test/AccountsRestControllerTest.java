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
import com.liferay.osb.asah.backend.dto.AccountDTO;
import com.liferay.osb.asah.backend.rest.controller.AccountsRestController;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.repository.BQMembershipRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;

import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Vishal Reddy
 */
public class AccountsRestControllerTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "fields", resourcePath = "fields_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetAccountDTO() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_account.json", this),
			_objectMapper.convertValue(
				_accountsRestController.getAccountDTO(
					342313459339515838L, null),
				JSONObject.class),
			true);

		AccountDTO accountDTO = _accountsRestController.getAccountDTO(
			342313459339515838L, 888L);

		Assertions.assertEquals(0L, (long)accountDTO.getActivitiesCount());
		Assertions.assertEquals(10L, (long)accountDTO.getIndividualsCount());

		accountDTO = _accountsRestController.getAccountDTO(
			342313459339515838L, 999L);

		Assertions.assertEquals(1L, (long)accountDTO.getActivitiesCount());
		Assertions.assertEquals(0L, (long)accountDTO.getIndividualsCount());
	}

	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "fields", resourcePath = "fields_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individual-segments",
		resourcePath = "individual_segments_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetAccountDTOPageDTO() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_accounts.json", this),
			_objectMapper.convertValue(
				_accountsRestController.getAccountDTOPageDTO(
					null, null, 0, 20, null),
				JSONObject.class),
			false);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_accounts_sorted.json", this),
			_objectMapper.convertValue(
				_accountsRestController.getAccountDTOPageDTO(
					888L, null, 0, 20,
					new String[] {"individualCount", "desc"}),
				JSONObject.class),
			false);
	}

	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts_2.json",
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
	public void testGetAccountsDistribution() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_accounts_distribution_filtered.json",
				this),
			_objectMapper.convertValue(
				_accountsRestController.getDistributionDTOPageDTO(
					1L, 366573389382719637L, null, 366637689379787789L, 10, 100,
					null),
				JSONObject.class),
			false);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_accounts_distribution_filtered.json",
				this),
			_objectMapper.convertValue(
				_accountsRestController.getDistributionDTOPageDTO(
					1L, 366573389382719637L,
					"organization/billingState/value eq 'New York'", null, 10,
					100, null),
				JSONObject.class),
			false);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_accounts_distribution_sorted.json",
				this),
			_objectMapper.convertValue(
				_accountsRestController.getDistributionDTOPageDTO(
					1L, 366573390725218943L, null, null, 10, 100,
					new String[] {"name", "desc"}),
				JSONObject.class),
			false);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_accounts_terms_distribution.json", this),
			_objectMapper.convertValue(
				_accountsRestController.getDistributionDTOPageDTO(
					1L, 366573390725218943L, null, null, 10, 100, null),
				JSONObject.class),
			false);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies" +
					"/expected_accounts_terms_distribution_truncated.json",
				this),
			_objectMapper.convertValue(
				_accountsRestController.getDistributionDTOPageDTO(
					1L, 366573390725218943L, null, null, 10, 1, null),
				JSONObject.class),
			false);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_accounts_numbers_distribution.json",
				this),
			_objectMapper.convertValue(
				_accountsRestController.getDistributionDTOPageDTO(
					1L, 366573389382719637L, null, null, 10, 100, null),
				JSONObject.class),
			false);
	}

	@ElasticsearchIndex(
		name = "field-mappings", resourcePath = "field_mappings.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetAccountsDistributionInvalidFieldMapping1() {
		Exception exception = Assertions.assertThrows(
			Exception.class,
			() -> _accountsRestController.getDistributionDTOPageDTO(
				1L, 366573390725218129L, null, null, 10, 100, null));

		MatcherAssert.assertThat(
			exception.getMessage(),
			CoreMatchers.containsString("Invalid field mapping ID"));
	}

	@ElasticsearchIndex(
		name = "field-mappings", resourcePath = "field_mappings.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetAccountsDistributionInvalidFieldMapping2() {
		Exception exception = Assertions.assertThrows(
			Exception.class,
			() -> _accountsRestController.getDistributionDTOPageDTO(
				1L, 340477857996688156L, null, null, 10, 100, null));

		MatcherAssert.assertThat(
			exception.getMessage(),
			CoreMatchers.containsString("Unable to use non-account field"));
	}

	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetAccountTransformations() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_account_transformations_1.json", this),
			_objectMapper.convertValue(
				_accountsRestController.getTransformationDTOPageDTO(
					"groupby((accountPK))", null, null, 0, 20),
				JSONObject.class),
			false);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_account_transformations_2.json", this),
			_objectMapper.convertValue(
				_accountsRestController.getTransformationDTOPageDTO(
					"groupby((accountPK))", 999L, null, 0, 20),
				JSONObject.class),
			false);
	}

	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts_1.json",
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
	@RepositoryResource(
		repositoryClass = BQMembershipRepository.class,
		resourcePath = "osbasahfaroinfo/bq_memberships.json"
	)
	@Test
	public void testGetIndividualSegments() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_individual_segments.json", this),
			_objectMapper.convertValue(
				_accountsRestController.getSegmentDTOPageDTO(
					342313459339515838L, null, 0, 20, null),
				JSONObject.class),
			false);
	}

	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts_1.json",
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
	@RepositoryResource(
		repositoryClass = BQMembershipRepository.class,
		resourcePath = "osbasahfaroinfo/bq_memberships.json"
	)
	@Test
	public void testGetIndividualSegmentTransformations() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_individual_segment_transformations.json",
				this),
			_objectMapper.convertValue(
				_accountsRestController.getSegmentTransformationDTOPageDTO(
					342313459339515838L, "groupby((filter))", null, 0, 20),
				JSONObject.class),
			false);
	}

	@Autowired
	private AccountsRestController _accountsRestController;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ObjectMapper _objectMapper;

}