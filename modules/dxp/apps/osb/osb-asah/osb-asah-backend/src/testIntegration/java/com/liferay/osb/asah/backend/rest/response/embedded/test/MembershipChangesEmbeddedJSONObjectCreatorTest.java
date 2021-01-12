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

package com.liferay.osb.asah.backend.rest.response.embedded.test;

import com.liferay.osb.asah.backend.rest.response.embedded.MembershipChangesEmbeddedJSONObjectCreator;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.rest.response.embedded.EmbeddedJSONObjectCreator;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Rachael Koestartyo
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class MembershipChangesEmbeddedJSONObjectCreatorTest {

	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "membership-changes", resourcePath = "membership_changes.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testExpandAccountNames() throws Exception {
		EmbeddedJSONObjectCreator embeddedJSONObjectCreator =
			new MembershipChangesEmbeddedJSONObjectCreator(
				_elasticsearchInvoker, "account-names");

		JSONObject membershipChangesJSONObject =
			embeddedJSONObjectCreator.create("346497473206675793");

		JSONArray accountNamesJSONArray =
			membershipChangesJSONObject.getJSONArray("account-names");

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

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}