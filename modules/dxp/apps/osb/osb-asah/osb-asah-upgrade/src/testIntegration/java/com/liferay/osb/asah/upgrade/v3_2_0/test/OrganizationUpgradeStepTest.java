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

package com.liferay.osb.asah.upgrade.v3_2_0.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.Organization;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.spring.TestExecutionListenerUtil;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.v3_2_0.OrganizationUpgradeStep;

import java.util.Date;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Marcos Martins
 */
public class OrganizationUpgradeStepTest
	implements OSBAsahUpgradeSpringTestContext {

	@AfterEach
	public void tearDown() throws Exception {
		_elasticsearchInvoker.delete(
			"organizations", QueryBuilders.matchAllQuery());
	}

	@Test
	public void testUpgrade() throws Exception {
		ProjectIdThreadLocal.setProjectId("test");

		_elasticsearchInvoker.add(
			"organizations",
			new JSONArray(
				TestExecutionListenerUtil.replaceVariables(
					ResourceUtil.readResourceToString(
						"dependencies/organizations.json", this))));

		_organizationUpgradeStep.upgrade("");

		JSONArray jsonArray = _elasticsearchInvoker.get("organizations");

		Assertions.assertEquals(1, jsonArray.length());

		Organization organization = _objectMapper.convertValue(
			jsonArray.getJSONObject(0), Organization.class);

		Assertions.assertEquals(
			new Date(1498132293600L), organization.getModifiedDate());
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private OrganizationUpgradeStep _organizationUpgradeStep;

}