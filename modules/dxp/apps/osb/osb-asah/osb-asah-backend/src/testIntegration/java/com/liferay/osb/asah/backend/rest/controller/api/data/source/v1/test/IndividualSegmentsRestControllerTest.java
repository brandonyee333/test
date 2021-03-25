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

package com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.test;

import com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.IndividualSegmentsRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.dto.SegmentDTO;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import io.restassured.http.Method;
import io.restassured.response.ValidatableResponse;

import java.util.Map;

import org.hamcrest.Matchers;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Vishal Reddy
 */
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndividualSegmentsRestControllerTest
	extends BaseRestControllerTestCase {

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

		SegmentDTO segmentDTO = _individualSegmentsRestController.getSegmentDTO(
			individualSegmentJSONObject.getLong("id"), "referenced-objects");

		Map<String, JSONObject> embedded = segmentDTO.getEmbedded();

		JSONObject referencedObjectsJSONObject = embedded.get(
			"referenced-objects");

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

	/**
	 * The JSON files are named after the WeDeploy collections that they are
	 * populated into. The JSON files were generated by running the
	 * DXPIndividualsNanite test, and retrieved from the WeDeploy data container
	 * before the tests cleaned up the data.
	 */
	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "field-mappings", resourcePath = "field_mappings.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "fields", resourcePath = "fields.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individual-segments", resourcePath = "individual_segments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "users", resourcePath = "users.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_DXP_RAW
	)
	@Ignore
	@Test
	public void testGetIndividualSegments() {
		ValidatableResponse validatableResponse = getValidatableResponse(
			Method.GET,
			"/api/1.0/individual-segments/327968823603500655/individuals");

		validateIndividuals(validatableResponse);
	}

	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individual-segments",
		resourcePath = "individual_segments_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetIndividualSegmentsForDataSourceId() throws Exception {
		String responseJSON =
			_individualSegmentsRestController.getIndividualSegments(
				"327968823603501234", "((individualCount ge 1))", 0, 20,
				new String[] {"dateModified"});

		JSONObject responseJSONObject = new JSONObject(responseJSON);

		JSONObject embeddedJSONObject = responseJSONObject.getJSONObject(
			"_embedded");

		JSONArray individualSegmentsJSONArray = embeddedJSONObject.getJSONArray(
			"individual-segments");

		Assert.assertEquals(2, individualSegmentsJSONArray.length());

		responseJSON = _individualSegmentsRestController.getIndividualSegments(
			"331238757269547423", "((individualCount ge 1))", 0, 20,
			new String[] {"dateModified"});

		responseJSONObject = new JSONObject(responseJSON);

		embeddedJSONObject = responseJSONObject.getJSONObject("_embedded");

		individualSegmentsJSONArray = embeddedJSONObject.getJSONArray(
			"individual-segments");

		Assert.assertEquals(1, individualSegmentsJSONArray.length());
	}

	private String[] _getIds(
		String key, JSONObject referencedObjectsJSONObject) {

		return JSONUtil.toStringArray(
			referencedObjectsJSONObject.getJSONArray(key), "id");
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	@Qualifier(
		"com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.IndividualSegmentsRestController"
	)
	private IndividualSegmentsRestController _individualSegmentsRestController;

}