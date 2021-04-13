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

import com.liferay.osb.asah.backend.dto.AccountDTO;
import com.liferay.osb.asah.backend.rest.controller.AccountsRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
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
 * @author Vishal Reddy
 */
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class AccountsRestControllerTest {

	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetAccountDTO() {
		JSONAssert.assertEquals(
			_elasticsearchInvoker.fetch("accounts", "342313459339515838"),
			_objectMapper.convertValue(
				_accountsRestController.getAccountDTO(
					342313459339515838L, null),
				JSONObject.class),
			true);

		AccountDTO accountDTO = _accountsRestController.getAccountDTO(
			342313459339515838L, 888L);

		Assert.assertEquals(0L, (long)accountDTO.getActivitiesCount());
		Assert.assertEquals(10L, (long)accountDTO.getIndividualCount());

		accountDTO = _accountsRestController.getAccountDTO(
			342313459339515838L, 999L);

		Assert.assertEquals(1L, (long)accountDTO.getActivitiesCount());
		Assert.assertEquals(0L, (long)accountDTO.getIndividualCount());
	}

	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetAccountDTOsPageDTO() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_accounts.json", this),
			_objectMapper.convertValue(
				_accountsRestController.getAccountDTOsPageDTO(
					null, null, 0, 20, null),
				JSONObject.class),
			false);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_accounts_sorted.json", this),
			_objectMapper.convertValue(
				_accountsRestController.getAccountDTOsPageDTO(
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
			new JSONObject(
				_accountsRestController.getAccountsDistribution(
					"1", "366573389382719637", null, "366637689379787789", 10,
					100, null)),
			false);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_accounts_distribution_filtered.json",
				this),
			new JSONObject(
				_accountsRestController.getAccountsDistribution(
					"1", "366573389382719637",
					"organization/billingState/value eq 'New York'", null, 10,
					100, null)),
			false);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_accounts_distribution_sorted.json",
				this),
			new JSONObject(
				_accountsRestController.getAccountsDistribution(
					"1", "366573390725218943", null, null, 10, 100,
					new String[] {"name", "desc"})),
			false);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_accounts_terms_distribution.json", this),
			new JSONObject(
				_accountsRestController.getAccountsDistribution(
					"1", "366573390725218943", null, null, 10, 100, null)),
			false);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies" +
					"/expected_accounts_terms_distribution_truncated.json",
				this),
			new JSONObject(
				_accountsRestController.getAccountsDistribution(
					"1", "366573390725218943", null, null, 10, 1, null)),
			false);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_accounts_numbers_distribution.json",
				this),
			new JSONObject(
				_accountsRestController.getAccountsDistribution(
					"1", "366573389382719637", null, null, 10, 100, null)),
			false);
	}

	@ElasticsearchIndex(
		name = "field-mappings", resourcePath = "field_mappings.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test(expected = Exception.class)
	public void testGetAccountsDistributionInvalidFieldMapping1()
		throws Exception {

		try {
			_accountsRestController.getAccountsDistribution(
				"1", "366573390725218129", null, null, 10, 100, null);
		}
		catch (Exception e) {
			Assert.assertThat(
				e.getMessage(),
				CoreMatchers.containsString("Invalid field mapping ID"));

			throw e;
		}
	}

	@ElasticsearchIndex(
		name = "field-mappings", resourcePath = "field_mappings.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test(expected = Exception.class)
	public void testGetAccountsDistributionInvalidFieldMapping2()
		throws Exception {

		try {
			_accountsRestController.getAccountsDistribution(
				"1", "340477857996688156", null, null, 10, 100, null);
		}
		catch (Exception e) {
			Assert.assertThat(
				e.getMessage(),
				CoreMatchers.containsString("Unable to use non-account field"));

			throw e;
		}
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
			new JSONObject(
				_accountsRestController.getAccountTransformations(
					"groupby((accountPK))", null, null, 0, 20)),
			false);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_account_transformations_2.json", this),
			new JSONObject(
				_accountsRestController.getAccountTransformations(
					"groupby((accountPK))", "999", null, 0, 20)),
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
	@ElasticsearchIndex(
		name = "memberships", resourcePath = "memberships.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetIndividualSegments() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_individual_segments.json", this),
			new JSONObject(
				_accountsRestController.getIndividualSegments(
					"342313459339515838", null, 0, 20, null)),
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
	@ElasticsearchIndex(
		name = "memberships", resourcePath = "memberships.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetIndividualSegmentTransformations() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_individual_segment_transformations.json",
				this),
			new JSONObject(
				_accountsRestController.getIndividualSegmentTransformations(
					"342313459339515838", "groupby((filter))", null, 0, 20)),
			false);
	}

	@Autowired
	private AccountsRestController _accountsRestController;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ObjectMapper _objectMapper;

}