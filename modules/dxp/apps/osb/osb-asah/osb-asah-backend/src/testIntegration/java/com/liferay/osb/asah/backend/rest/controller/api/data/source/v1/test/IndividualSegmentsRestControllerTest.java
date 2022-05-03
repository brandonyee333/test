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

import com.liferay.osb.asah.backend.dto.PageDTO;
import com.liferay.osb.asah.backend.dto.SegmentDTO;
import com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.IndividualSegmentsRestController;
import com.liferay.osb.asah.common.dog.DXPEntityDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.repository.BQMembershipChangeRepository;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DXPEntityRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;

import io.restassured.http.Method;
import io.restassured.response.ValidatableResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author Vishal Reddy
 */
public class IndividualSegmentsRestControllerTest
	extends BaseRestControllerTestCase {

	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@Test
	public void testExpandReferencedObjects() throws Exception {
		Asset asset = _assetRepository.save(
			_objectMapper.convertValue(
				FaroInfoTestUtil.buildAssetJSONObject("Page", 1L),
				Asset.class));

		FieldMapping accountFieldMapping = _fieldMappingRepository.save(
			FaroInfoTestUtil.buildAccountFieldMapping(
				"1", "shippingPostalCode", "shippingPostalCode", "Text"));
		FieldMapping individualFieldMapping = _fieldMappingRepository.save(
			FaroInfoTestUtil.buildIndividualFieldMapping(
				1L, "email", "email", "Text"));

		JSONObject organizationJSONObject = _faroInfoElasticsearchInvoker.add(
			"organizations",
			JSONUtil.put(
				"dataSourceId", 1L
			).put(
				"name", "organizationName"
			));

		DXPEntity groupDXPEntity = new DXPEntity();

		groupDXPEntity.setDataSourceId(331238757269547423L);
		groupDXPEntity.setFieldsJSONObject(JSONUtil.put("name", "groupName"));
		groupDXPEntity.setType(DXPEntity.Type.GROUP);

		groupDXPEntity = _dxpEntityDog.addDXPEntity(
			groupDXPEntity, DXPEntity.Type.GROUP);

		DXPEntity roleDXPEntity = new DXPEntity();

		roleDXPEntity.setDataSourceId(331238757269547423L);
		roleDXPEntity.setFieldsJSONObject(JSONUtil.put("name", "roleName"));
		roleDXPEntity.setType(DXPEntity.Type.ROLE);

		roleDXPEntity = _dxpEntityDog.addDXPEntity(
			roleDXPEntity, DXPEntity.Type.ROLE);

		DXPEntity teamDXPEntity = new DXPEntity();

		teamDXPEntity.setDataSourceId(331238757269547423L);
		teamDXPEntity.setFieldsJSONObject(JSONUtil.put("name", "teamName"));
		teamDXPEntity.setType(DXPEntity.Type.TEAM);

		teamDXPEntity = _dxpEntityDog.addDXPEntity(
			teamDXPEntity, DXPEntity.Type.TEAM);

		DXPEntity userDXPEntity = new DXPEntity();

		userDXPEntity.setDataSourceId(331238757269547423L);
		userDXPEntity.setFieldsJSONObject(
			JSONUtil.put("firstName", "userName"));
		userDXPEntity.setType(DXPEntity.Type.USER);

		userDXPEntity = _dxpEntityDog.addDXPEntity(
			userDXPEntity, DXPEntity.Type.USER);

		DXPEntity userGroupDXPEntity = new DXPEntity();

		userGroupDXPEntity.setDataSourceId(331238757269547423L);
		userGroupDXPEntity.setFieldsJSONObject(
			JSONUtil.put("name", "userGroupName"));
		userGroupDXPEntity.setType(DXPEntity.Type.USER_GROUP);

		userGroupDXPEntity = _dxpEntityDog.addDXPEntity(
			userGroupDXPEntity, DXPEntity.Type.USER_GROUP);

		Segment segment = new Segment();

		segment.setChannelId(1L);
		segment.setReferencedAssetIds(Collections.singleton(asset.getId()));
		segment.setReferencedFieldMappingIds(
			SetUtil.of(
				accountFieldMapping.getId(), individualFieldMapping.getId()));
		segment.setReferencedGroupIds(
			Collections.singleton(groupDXPEntity.getId()));
		segment.setReferencedOrganizationIds(
			Collections.singleton(
				Long.valueOf(organizationJSONObject.getString("id"))));
		segment.setReferencedRoleIds(
			Collections.singleton(roleDXPEntity.getId()));
		segment.setReferencedTeamIds(
			Collections.singleton(teamDXPEntity.getId()));
		segment.setReferencedUserGroupIds(
			Collections.singleton(userGroupDXPEntity.getId()));
		segment.setReferencedUserIds(
			Collections.singleton(userDXPEntity.getId()));

		segment = _segmentRepository.save(segment);

		SegmentDTO segmentDTO = _individualSegmentsRestController.getSegmentDTO(
			segment.getId(), "referenced-objects");

		Map<String, Object> embedded = segmentDTO.getEmbedded();

		JSONObject referencedObjectsJSONObject = (JSONObject)embedded.get(
			"referenced-objects");

		MatcherAssert.assertThat(
			new String[] {String.valueOf(asset.getId())},
			Matchers.arrayContainingInAnyOrder(
				_getIds("assets", referencedObjectsJSONObject)));
		MatcherAssert.assertThat(
			new String[] {
				String.valueOf(accountFieldMapping.getId()),
				String.valueOf(individualFieldMapping.getId())
			},
			Matchers.arrayContainingInAnyOrder(
				_getIds("field-mappings", referencedObjectsJSONObject)));
		MatcherAssert.assertThat(
			new String[] {String.valueOf(groupDXPEntity.getId())},
			Matchers.arrayContainingInAnyOrder(
				_getIds("groups", referencedObjectsJSONObject)));
		MatcherAssert.assertThat(
			new String[] {String.valueOf(organizationJSONObject.getLong("id"))},
			Matchers.arrayContainingInAnyOrder(
				_getIds("organizations", referencedObjectsJSONObject)));
		MatcherAssert.assertThat(
			new String[] {String.valueOf(roleDXPEntity.getId())},
			Matchers.arrayContainingInAnyOrder(
				_getIds("roles", referencedObjectsJSONObject)));
		MatcherAssert.assertThat(
			new String[] {String.valueOf(teamDXPEntity.getId())},
			Matchers.arrayContainingInAnyOrder(
				_getIds("teams", referencedObjectsJSONObject)));
		MatcherAssert.assertThat(
			new String[] {String.valueOf(userGroupDXPEntity.getId())},
			Matchers.arrayContainingInAnyOrder(
				_getIds("user-groups", referencedObjectsJSONObject)));
		MatcherAssert.assertThat(
			new String[] {String.valueOf(userDXPEntity.getId())},
			Matchers.arrayContainingInAnyOrder(
				_getIds("users", referencedObjectsJSONObject)));
	}

	/**
	 * The JSON files are named after the WeDeploy collections that they are
	 * populated into. The JSON files were generated by running the
	 * DXPIndividualsNanite test, and retrieved from the WeDeploy data container
	 * before the tests cleaned up the data.
	 */
	@Disabled
	@ElasticsearchIndex(
		name = "field-mappings", resourcePath = "field_mappings.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "fields", resourcePath = "fields_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individual-segments", resourcePath = "individual_segments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@RepositoryResource(
		repositoryClass = DXPEntityRepository.class,
		resourcePath = "osbasahdxpraw/users.json"
	)
	@Test
	public void testGetIndividualSegments() {
		ValidatableResponse validatableResponse = getValidatableResponse(
			Method.GET,
			"/api/1.0/individual-segments/327968823603500655/individuals");

		validateIndividuals(validatableResponse);
	}

	@ElasticsearchIndex(
		name = "individual-segments",
		resourcePath = "individual_segments_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = ChannelRepository.class,
		resourcePath = "osbasahfaroinfo/channels.json"
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources_1.json"
	)
	@Test
	public void testGetIndividualSegmentsForDataSourceId() throws Exception {
		JSONObject responseJSONObject = _objectMapper.convertValue(
			_individualSegmentsRestController.getSegmentDTOPageDTO(
				327968823603501234L, "((individualCount ge 1))", 0, 20,
				new String[] {"dateModified"}),
			JSONObject.class);

		JSONObject embeddedJSONObject = responseJSONObject.getJSONObject(
			"_embedded");

		JSONArray individualSegmentsJSONArray = embeddedJSONObject.getJSONArray(
			"individual-segments");

		Assertions.assertEquals(2, individualSegmentsJSONArray.length());

		responseJSONObject = _objectMapper.convertValue(
			_individualSegmentsRestController.getSegmentDTOPageDTO(
				331238757269547423L, "((individualCount ge 1))", 0, 20,
				new String[] {"dateModified"}),
			JSONObject.class);

		embeddedJSONObject = responseJSONObject.getJSONObject("_embedded");

		individualSegmentsJSONArray = embeddedJSONObject.getJSONArray(
			"individual-segments");

		Assertions.assertEquals(1, individualSegmentsJSONArray.length());
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individual-segments",
		resourcePath = "individual_segments_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = BQMembershipChangeRepository.class,
		resourcePath = "osbasahfaroinfo/bq_membership_changes_1.json"
	)
	@Test
	public void testGetSegmentDTO1() throws Exception {
		SegmentDTO segmentDTO = _individualSegmentsRestController.getSegmentDTO(
			327968823603500655L, "referenced-objects");

		long activeIndividualsCount = Optional.ofNullable(
			segmentDTO.getActiveIndividualsCount()
		).orElse(
			-1L
		);

		Assertions.assertEquals(10L, activeIndividualsCount);

		long anonymousIndividualsCount = Optional.ofNullable(
			segmentDTO.getAnonymousIndividualsCount()
		).orElse(
			-1L
		);

		Assertions.assertEquals(8L, anonymousIndividualsCount);

		long individualsCount = Optional.ofNullable(
			segmentDTO.getIndividualsCount()
		).orElse(
			-1L
		);

		Assertions.assertEquals(10L, individualsCount);

		long knownIndividualsCount = Optional.ofNullable(
			segmentDTO.getKnownIndividualsCount()
		).orElse(
			-1L
		);

		Assertions.assertEquals(2L, knownIndividualsCount);

		Assertions.assertEquals("d-age-gt-50", segmentDTO.getName());

		Set<String> referencedAssetDataSourceIds =
			segmentDTO.getReferencedAssetDataSourceIds();

		Assertions.assertTrue(referencedAssetDataSourceIds.isEmpty());

		Set<String> referencedAssetIds = segmentDTO.getReferencedAssetIds();

		Assertions.assertTrue(referencedAssetIds.isEmpty());

		Set<String> referencedFieldMappingIds =
			segmentDTO.getReferencedFieldMappingIds();

		Assertions.assertTrue(referencedFieldMappingIds.isEmpty());

		Set<String> referencedGroupIds = segmentDTO.getReferencedGroupIds();

		Assertions.assertTrue(referencedGroupIds.isEmpty());

		Set<String> referencedOrganizationIds =
			segmentDTO.getReferencedOrganizationIds();

		Assertions.assertTrue(referencedOrganizationIds.isEmpty());

		Set<String> referencedRoleIds = segmentDTO.getReferencedRoleIds();

		Assertions.assertTrue(referencedRoleIds.isEmpty());

		Set<String> referencedTeamIds = segmentDTO.getReferencedTeamIds();

		Assertions.assertTrue(referencedTeamIds.isEmpty());

		Set<String> referencedUserGroupIds =
			segmentDTO.getReferencedUserGroupIds();

		Assertions.assertTrue(referencedUserGroupIds.isEmpty());

		Set<String> referencedUserIds = segmentDTO.getReferencedUserIds();

		Assertions.assertTrue(referencedUserIds.isEmpty());
	}

	@ElasticsearchIndex(
		name = "individual-segments",
		resourcePath = "individual_segments_3.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetSegmentDTO2() throws Exception {
		SegmentDTO segmentDTO = _individualSegmentsRestController.getSegmentDTO(
			327968823603500655L, null);

		Assertions.assertNotEquals(0L, segmentDTO.getKnownIndividualsCount());
	}

	@ElasticsearchIndex(
		name = "individual-segments",
		resourcePath = "individual_segments_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = BQMembershipChangeRepository.class,
		resourcePath = "osbasahfaroinfo/bq_membership_changes_2.json"
	)
	@Test
	public void testGetSegmentDTOCreatedToday() throws Exception {
		SegmentDTO segmentDTO = _individualSegmentsRestController.getSegmentDTO(
			1L, null);

		long anonymousIndividualsCount = Optional.ofNullable(
			segmentDTO.getAnonymousIndividualsCount()
		).orElse(
			-1L
		);

		Assertions.assertEquals(0L, anonymousIndividualsCount);

		long individualsCount = Optional.ofNullable(
			segmentDTO.getIndividualsCount()
		).orElse(
			-1L
		);

		Assertions.assertEquals(0L, individualsCount);

		long knownIndividualsCount = Optional.ofNullable(
			segmentDTO.getKnownIndividualsCount()
		).orElse(
			-1L
		);

		Assertions.assertEquals(0L, knownIndividualsCount);
	}

	@ElasticsearchIndex(
		name = "individual-segments",
		resourcePath = "individual_segments_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = BQMembershipChangeRepository.class,
		resourcePath = "osbasahfaroinfo/bq_membership_changes_2.json"
	)
	@RepositoryResource(
		repositoryClass = ChannelRepository.class,
		resourcePath = "osbasahfaroinfo/channels_2.json"
	)
	@Test
	public void testGetSegmentDTOPageDTO1() throws Exception {
		PageDTO<SegmentDTO> pageDTO =
			_individualSegmentsRestController.getSegmentDTOPageDTO(
				1L, null, 0, 50, new String[0]);

		Map<String, SegmentDTO> content = pageDTO.getContent();

		SegmentDTO embedded = content.get("_embedded");

		Set<SegmentDTO> segmentDTOs = embedded.getSegmentDTOs();

		Assertions.assertEquals(2, segmentDTOs.size(), segmentDTOs.toString());

		Stream<SegmentDTO> stream = segmentDTOs.stream();

		List<String> ids = stream.map(
			SegmentDTO::getId
		).collect(
			Collectors.toList()
		);

		Assertions.assertTrue(ids.containsAll(Arrays.asList("1", "2")));

		for (SegmentDTO segmentDTO : segmentDTOs) {
			long anonymousIndividualsCount = Optional.ofNullable(
				segmentDTO.getAnonymousIndividualsCount()
			).orElse(
				-1L
			);

			long individualsCount = Optional.ofNullable(
				segmentDTO.getIndividualsCount()
			).orElse(
				-1L
			);

			long knownIndividualsCount = Optional.ofNullable(
				segmentDTO.getKnownIndividualsCount()
			).orElse(
				-1L
			);

			if (Objects.equals(segmentDTO.getId(), "1")) {
				Assertions.assertEquals(0L, anonymousIndividualsCount);
				Assertions.assertEquals(0L, individualsCount);
				Assertions.assertEquals(0L, knownIndividualsCount);
			}
			else if (Objects.equals(segmentDTO.getId(), "2")) {
				Assertions.assertEquals(0L, anonymousIndividualsCount);
				Assertions.assertEquals(5L, individualsCount);
				Assertions.assertEquals(5L, knownIndividualsCount);
			}
		}
	}

	@ElasticsearchIndex(
		name = "individual-segments",
		resourcePath = "individual_segments_3.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetSegmentDTOPageDTO2() {
		PageDTO<SegmentDTO> pageDTO =
			_individualSegmentsRestController.getSegmentDTOPageDTO(
				1L, null, 0, 50, new String[0]);

		Map<String, SegmentDTO> content = pageDTO.getContent();

		SegmentDTO embedded = content.get("_embedded");

		List<SegmentDTO> segmentDTOs = new ArrayList(embedded.getSegmentDTOs());

		Assertions.assertEquals(1, segmentDTOs.size(), segmentDTOs.toString());

		SegmentDTO segmentDTO = segmentDTOs.get(0);

		Assertions.assertNotEquals(0L, segmentDTO.getKnownIndividualsCount());
	}

	private String[] _getIds(
		String key, JSONObject referencedObjectsJSONObject) {

		return JSONUtil.toStringArray(
			referencedObjectsJSONObject.getJSONArray(key), "id");
	}

	@Autowired
	private AssetRepository _assetRepository;

	@Autowired
	private DXPEntityDog _dxpEntityDog;

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