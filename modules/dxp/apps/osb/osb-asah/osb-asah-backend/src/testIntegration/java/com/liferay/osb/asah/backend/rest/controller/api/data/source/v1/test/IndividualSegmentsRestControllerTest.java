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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.backend.dto.SegmentDTO;
import com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.IndividualSegmentsRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import io.restassured.http.Method;
import io.restassured.response.ValidatableResponse;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

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
		FieldMapping accountFieldMapping = _fieldMappingRepository.save(
			FaroInfoTestUtil.buildAccountFieldMapping(
				"1", "shippingPostalCode", "shippingPostalCode", "Text"));
		FieldMapping individualFieldMapping = _fieldMappingRepository.save(
			FaroInfoTestUtil.buildIndividualFieldMapping(
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

		Segment segment = new Segment();

		segment.setReferencedAssetIds(
			Collections.singleton(
				Long.valueOf(assetJSONObject.getString("id"))));
		segment.setReferencedFieldMappingIds(
			SetUtil.of(
				accountFieldMapping.getId(), individualFieldMapping.getId()));
		segment.setReferencedGroupIds(
			Collections.singleton(
				Long.valueOf(groupJSONObject.getString("id"))));
		segment.setReferencedOrganizationIds(
			Collections.singleton(
				Long.valueOf(organizationJSONObject.getString("id"))));
		segment.setReferencedRoleIds(
			Collections.singleton(
				Long.valueOf(roleJSONObject.getString("id"))));
		segment.setReferencedTeamIds(
			Collections.singleton(
				Long.valueOf(teamJSONObject.getString("id"))));
		segment.setReferencedUserGroupIds(
			Collections.singleton(
				Long.valueOf(userGroupJSONObject.getString("id"))));
		segment.setReferencedUserIds(
			Collections.singleton(
				Long.valueOf(userJSONObject.getString("id"))));

		segment = _segmentRepository.save(segment);

		SegmentDTO segmentDTO = _individualSegmentsRestController.getSegmentDTO(
			segment.getId(), "referenced-objects");

		Map<String, JSONObject> embedded = segmentDTO.getEmbedded();

		JSONObject referencedObjectsJSONObject = embedded.get(
			"referenced-objects");

		Assert.assertThat(
			new String[] {assetJSONObject.getString("id")},
			Matchers.arrayContainingInAnyOrder(
				_getIds("assets", referencedObjectsJSONObject)));
		Assert.assertThat(
			new String[] {
				String.valueOf(accountFieldMapping.getId()),
				String.valueOf(individualFieldMapping.getId())
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
		JSONObject responseJSONObject = _objectMapper.convertValue(
			_individualSegmentsRestController.getSegmentDTOsPageDTOs(
				327968823603501234L, "((individualCount ge 1))", 0, 20,
				new String[] {"dateModified"}),
			JSONObject.class);

		JSONObject embeddedJSONObject = responseJSONObject.getJSONObject(
			"_embedded");

		JSONArray individualSegmentsJSONArray = embeddedJSONObject.getJSONArray(
			"individual-segments");

		Assert.assertEquals(2, individualSegmentsJSONArray.length());

		responseJSONObject = _objectMapper.convertValue(
			_individualSegmentsRestController.getSegmentDTOsPageDTOs(
				331238757269547423L, "((individualCount ge 1))", 0, 20,
				new String[] {"dateModified"}),
			JSONObject.class);

		embeddedJSONObject = responseJSONObject.getJSONObject("_embedded");

		individualSegmentsJSONArray = embeddedJSONObject.getJSONArray(
			"individual-segments");

		Assert.assertEquals(1, individualSegmentsJSONArray.length());
	}

	@ElasticsearchIndex(
		name = "individual-segments",
		resourcePath = "individual_segments_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetSegmentDTO() throws Exception {
		SegmentDTO segmentDTO = _individualSegmentsRestController.getSegmentDTO(
			327968823603500655L, "referenced-objects");

		Assert.assertEquals("d-age-gt-50", segmentDTO.getName());

		Set<String> referencedAssetDataSourceIds =
			segmentDTO.getReferencedAssetDataSourceIds();

		Assert.assertTrue(referencedAssetDataSourceIds.isEmpty());

		Set<String> referencedAssetIds = segmentDTO.getReferencedAssetIds();

		Assert.assertTrue(referencedAssetIds.isEmpty());

		Set<String> referencedFieldMappingIds =
			segmentDTO.getReferencedFieldMappingIds();

		Assert.assertTrue(referencedFieldMappingIds.isEmpty());

		Set<String> referencedGroupIds = segmentDTO.getReferencedGroupIds();

		Assert.assertTrue(referencedGroupIds.isEmpty());

		Set<String> referencedOrganizationIds =
			segmentDTO.getReferencedOrganizationIds();

		Assert.assertTrue(referencedOrganizationIds.isEmpty());

		Set<String> referencedRoleIds = segmentDTO.getReferencedRoleIds();

		Assert.assertTrue(referencedRoleIds.isEmpty());

		Set<String> referencedTeamIds = segmentDTO.getReferencedTeamIds();

		Assert.assertTrue(referencedTeamIds.isEmpty());

		Set<String> referencedUserGroupIds =
			segmentDTO.getReferencedUserGroupIds();

		Assert.assertTrue(referencedUserGroupIds.isEmpty());

		Set<String> referencedUserIds = segmentDTO.getReferencedUserIds();

		Assert.assertTrue(referencedUserIds.isEmpty());
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
	private FieldMappingRepository _fieldMappingRepository;

	@Autowired
	@Qualifier(
		"com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.IndividualSegmentsRestController"
	)
	private IndividualSegmentsRestController _individualSegmentsRestController;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentRepository _segmentRepository;

}