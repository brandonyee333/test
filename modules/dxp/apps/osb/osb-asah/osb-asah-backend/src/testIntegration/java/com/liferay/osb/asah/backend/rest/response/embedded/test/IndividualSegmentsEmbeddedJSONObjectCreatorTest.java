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

import com.liferay.osb.asah.backend.rest.response.embedded.IndividualSegmentsEmbeddedJSONObjectCreator;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.rest.response.embedded.EmbeddedJSONObjectCreator;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.hamcrest.Matchers;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Matthew Kong
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class IndividualSegmentsEmbeddedJSONObjectCreatorTest {

	@Before
	public void setUp() {
		_dxpRawElasticsearchInvoker = _elasticsearchInvokerFactory.forDXPRaw();
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
	}

	@Test
	public void testExpandReferencedObjects() throws Exception {
		JSONObject assetJSONObject = _faroInfoElasticsearchInvoker.add(
			"assets", FaroInfoTestUtil.buildAssetJSONObject("Page", "1"));
		JSONObject accountFieldMappingJSONObject =
			_faroInfoElasticsearchInvoker.add(
				"field-mappings",
				FaroInfoTestUtil.buildAccountFieldMappingJSONObject(
					"1", "shippingPostalCode", "shippingPostalCode", "Text"));
		JSONObject individualFieldMappingJSONObject =
			_faroInfoElasticsearchInvoker.add(
				"field-mappings",
				FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
					"1", "email", "email", "Text"));
		JSONObject groupJSONObject = _dxpRawElasticsearchInvoker.add(
			"groups", JSONUtil.put("name", "groupName"));
		JSONObject organizationJSONObject = _faroInfoElasticsearchInvoker.add(
			"organizations", FaroInfoTestUtil.buildOrganizationJSONObject("1"));
		JSONObject roleJSONObject = _dxpRawElasticsearchInvoker.add(
			"roles", JSONUtil.put("name", "roleName"));
		JSONObject teamJSONObject = _dxpRawElasticsearchInvoker.add(
			"teams", JSONUtil.put("name", "teamName"));
		JSONObject userGroupJSONObject = _dxpRawElasticsearchInvoker.add(
			"user-groups", JSONUtil.put("name", "userGroupName"));
		JSONObject userJSONObject = _dxpRawElasticsearchInvoker.add(
			"users", JSONUtil.put("name", "userName"));

		JSONObject individualSegmentJSONObject =
			_faroInfoElasticsearchInvoker.add(
				"individual-segments",
				JSONUtil.put(
					"referencedAssetIds",
					JSONUtil.put(assetJSONObject.getString("id"))
				).put(
					"referencedFieldMappingIds",
					JSONUtil.putAll(
						accountFieldMappingJSONObject.getString("id"),
						individualFieldMappingJSONObject.getString("id"))
				).put(
					"referencedGroupIds",
					JSONUtil.put(groupJSONObject.getString("id"))
				).put(
					"referencedOrganizationIds",
					JSONUtil.put(organizationJSONObject.getString("id"))
				).put(
					"referencedRoleIds",
					JSONUtil.put(roleJSONObject.getString("id"))
				).put(
					"referencedTeamIds",
					JSONUtil.put(teamJSONObject.getString("id"))
				).put(
					"referencedUserGroupIds",
					JSONUtil.put(userGroupJSONObject.getString("id"))
				).put(
					"referencedUserIds",
					JSONUtil.put(userJSONObject.getString("id"))
				));

		EmbeddedJSONObjectCreator embeddedJSONObjectCreator =
			new IndividualSegmentsEmbeddedJSONObjectCreator(
				_dxpRawElasticsearchInvoker, _faroInfoElasticsearchInvoker,
				"referenced-objects");

		individualSegmentJSONObject = embeddedJSONObjectCreator.create(
			individualSegmentJSONObject.getString("id"));

		JSONObject referencedObjectsJSONObject =
			individualSegmentJSONObject.getJSONObject("referenced-objects");

		Assert.assertThat(
			new String[] {assetJSONObject.getString("id")},
			Matchers.arrayContainingInAnyOrder(
				_getIds("assets", referencedObjectsJSONObject)));
		Assert.assertThat(
			new String[] {
				accountFieldMappingJSONObject.getString("id"),
				individualFieldMappingJSONObject.getString("id")
			},
			Matchers.arrayContainingInAnyOrder(
				_getIds("field-mappings", referencedObjectsJSONObject)));
		Assert.assertThat(
			new String[] {groupJSONObject.getString("id")},
			Matchers.arrayContainingInAnyOrder(
				_getIds("groups", referencedObjectsJSONObject)));
		Assert.assertThat(
			new String[] {organizationJSONObject.getString("id")},
			Matchers.arrayContainingInAnyOrder(
				_getIds("organizations", referencedObjectsJSONObject)));
		Assert.assertThat(
			new String[] {roleJSONObject.getString("id")},
			Matchers.arrayContainingInAnyOrder(
				_getIds("roles", referencedObjectsJSONObject)));
		Assert.assertThat(
			new String[] {teamJSONObject.getString("id")},
			Matchers.arrayContainingInAnyOrder(
				_getIds("teams", referencedObjectsJSONObject)));
		Assert.assertThat(
			new String[] {userGroupJSONObject.getString("id")},
			Matchers.arrayContainingInAnyOrder(
				_getIds("user-groups", referencedObjectsJSONObject)));
		Assert.assertThat(
			new String[] {userJSONObject.getString("id")},
			Matchers.arrayContainingInAnyOrder(
				_getIds("users", referencedObjectsJSONObject)));
	}

	private String[] _getIds(
		String key, JSONObject referencedObjectsJSONObject) {

		return JSONUtil.toStringArray(
			referencedObjectsJSONObject.getJSONArray(key), "id");
	}

	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}