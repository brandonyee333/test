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

import com.liferay.osb.asah.backend.rest.response.embedded.IndividualsIndividualSegmentsEmbeddedJSONObjectCreator;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.rest.response.embedded.EmbeddedJSONObjectCreator;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Vishal Reddy
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@ElasticsearchIndex(
	name = "individual-segments", resourcePath = "individual_segments.json",
	weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
)
@ElasticsearchIndex(
	name = "memberships", resourcePath = "memberships.json",
	weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class IndividualsIndividualSegmentsEmbeddedJSONObjectCreatorTest {

	@Before
	public void setUp() {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forFaroInfo();
	}

	@Test
	public void testActiveMembershipReturned() throws Exception {
		EmbeddedJSONObjectCreator embeddedJSONObjectCreator =
			new IndividualsIndividualSegmentsEmbeddedJSONObjectCreator(
				_elasticsearchInvoker, "active-membership", "123");

		JSONObject individualIndividualSegmentJSONObject =
			embeddedJSONObjectCreator.create("910");

		JSONObject activeMembershipJSONObject =
			individualIndividualSegmentJSONObject.getJSONObject(
				"active-membership");

		Assert.assertEquals(
			"123", activeMembershipJSONObject.getString("individualId"));
		Assert.assertEquals(
			"910", activeMembershipJSONObject.getString("individualSegmentId"));
		Assert.assertEquals(
			"ACTIVE", activeMembershipJSONObject.getString("status"));
	}

	@Test
	public void testInactiveMembershipNotReturned() throws Exception {
		EmbeddedJSONObjectCreator embeddedJSONObjectCreator =
			new IndividualsIndividualSegmentsEmbeddedJSONObjectCreator(
				_elasticsearchInvoker, "active-membership", "456");

		Assert.assertNull(embeddedJSONObjectCreator.create("910"));
	}

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}