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
import com.liferay.osb.asah.backend.dto.SegmentDTO;
import com.liferay.osb.asah.backend.rest.controller.IndividualSegmentsRestController;
import com.liferay.osb.asah.common.dog.MembershipChangeDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.BQMembershipChangeRepository;
import com.liferay.osb.asah.common.repository.BQMembershipRepository;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Vishal Reddy
 * @author Rachael Koestartyo
 */
public class IndividualSegmentsRestControllerTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@ElasticsearchIndex(
		name = "individual-segments", resourcePath = "individual_segments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = ChannelRepository.class,
		resourcePath = "osbasahfaroinfo/channels_2.json"
	)
	@Test
	public void testAssignChannelAlreadyAssigned() throws Exception {
		Assertions.assertThrows(
			OSBAsahException.class,
			() -> {
				_individualSegmentsRestController.assignChannel(
					1L, 327968823603500655L);

				_individualSegmentsRestController.assignChannel(
					1L, 327968823603500655L);
			});
	}

	@ElasticsearchIndex(
		name = "individual-segments", resourcePath = "individual_segments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = ChannelRepository.class,
		resourcePath = "osbasahfaroinfo/channels_2.json"
	)
	@RepositoryResource(
		repositoryClass = BQMembershipRepository.class,
		resourcePath = "osbasahfaroinfo/bq_memberships_1.json"
	)
	@Test
	public void testAssignChannelModifiesIndividualsCount() throws Exception {
		_individualSegmentsRestController.assignChannel(
			1L, 366637689379787789L);

		_elasticsearchInvoker.refresh();

		JSONObject individualSegmentJSONObject = _elasticsearchInvoker.fetch(
			"individual-segments", "366637689379787789");

		Assertions.assertEquals(
			"1", individualSegmentJSONObject.getString("channelId"));
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
		repositoryClass = ChannelRepository.class,
		resourcePath = "osbasahfaroinfo/channels_2.json"
	)
	@RepositoryResource(
		repositoryClass = BQMembershipRepository.class,
		resourcePath = "osbasahfaroinfo/bq_memberships.json"
	)
	@Test
	public void testAssignChannelStaticSegment() throws Exception {
		_individualSegmentsRestController.assignChannel(
			1L, 338511451975440187L);

		JSONObject individualSegmentJSONObject = _elasticsearchInvoker.get(
			"individual-segments", "338511451975440187");

		Assertions.assertEquals(
			"1", individualSegmentJSONObject.optString("channelId"));

		Assertions.assertEquals(
			1,
			_bqMembershipRepository.countByIndividualSegmentIdAndStatus(
				338511451975440187L, "ACTIVE"));
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
		name = "individual-segments", resourcePath = "individual_segments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = BQMembershipChangeRepository.class,
		resourcePath = "osbasahfaroinfo/bq_membership_changes.json"
	)
	@Test
	public void testGetAccounts() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_individual_segment_accounts.json", this),
			_objectMapper.convertValue(
				_individualSegmentsRestController.getAccountDTOPageDTO(
					366637689379787789L, null, 0, 20, null),
				JSONObject.class),
			false);
	}

	@ElasticsearchIndex(
		name = "activities", resourcePath = "activities.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individual-segments", resourcePath = "individual_segments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources_1.json"
	)
	@Test
	public void testGetIndividuals() throws Exception {

		// Include anonymous users

		JSONObject individualsJSONObject = _objectMapper.convertValue(
			_individualSegmentsRestController.getIndividualDTOPageDTO(
				327968823603500666L, null, true, 0, 20, null),
			JSONObject.class);

		JSONObject embeddedJSONObject = individualsJSONObject.getJSONObject(
			"_embedded");

		JSONArray individualsJSONArray = embeddedJSONObject.getJSONArray(
			"individuals");

		Assertions.assertEquals(2, individualsJSONArray.length());

		// Does not include anonymous users

		individualsJSONObject = _objectMapper.convertValue(
			_individualSegmentsRestController.getIndividualDTOPageDTO(
				327968823603500666L, null, false, 0, 20, null),
			JSONObject.class);

		embeddedJSONObject = individualsJSONObject.getJSONObject("_embedded");

		individualsJSONArray = embeddedJSONObject.getJSONArray("individuals");

		Assertions.assertEquals(1, individualsJSONArray.length());
	}

	@ElasticsearchIndex(
		name = "activities", resourcePath = "activities.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individual-segments", resourcePath = "individual_segments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources_1.json"
	)
	@RepositoryResource(
		repositoryClass = BQMembershipChangeRepository.class,
		resourcePath = "osbasahfaroinfo/bq_membership_changes.json"
	)
	@RepositoryResource(
		repositoryClass = BQMembershipRepository.class,
		resourcePath = "osbasahfaroinfo/bq_memberships.json"
	)
	@Test
	public void testGetMembershipChanges() throws Exception {

		// Include anonymous users

		JSONObject membershipChangesJSONObject = _objectMapper.convertValue(
			_individualSegmentsRestController.getMembershipChangeDTOPageDTO(
				327968823603500666L, null, null, 0, 20, null),
			JSONObject.class);

		JSONObject embeddedJSONObject =
			membershipChangesJSONObject.getJSONObject("_embedded");

		JSONArray membershipChangesJSONArray = embeddedJSONObject.getJSONArray(
			"membership-changes");

		Assertions.assertEquals(1, membershipChangesJSONArray.length());

		JSONObject membershipJSONObject =
			(JSONObject)membershipChangesJSONArray.get(0);

		Assertions.assertEquals(
			2, membershipJSONObject.getInt("individualsCount"));
		Assertions.assertEquals(
			1, membershipJSONObject.getInt("knownIndividualsCount"));

		// Does not include anonymous users

		membershipChangesJSONObject = _objectMapper.convertValue(
			_individualSegmentsRestController.getMembershipChangeDTOPageDTO(
				327968823603500677L, null, null, 0, 20, null),
			JSONObject.class);

		embeddedJSONObject = membershipChangesJSONObject.getJSONObject(
			"_embedded");

		membershipChangesJSONArray = embeddedJSONObject.getJSONArray(
			"membership-changes");

		membershipJSONObject = (JSONObject)membershipChangesJSONArray.get(0);

		Assertions.assertEquals(
			2, membershipJSONObject.getInt("individualsCount"));
		Assertions.assertEquals(
			0, membershipJSONObject.getInt("knownIndividualsCount"));
	}

	@ElasticsearchIndex(
		name = "activities", resourcePath = "activities.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individual-segments", resourcePath = "individual_segments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources_1.json"
	)
	@RepositoryResource(
		repositoryClass = BQMembershipRepository.class,
		resourcePath = "osbasahfaroinfo/bq_memberships.json"
	)
	@Test
	public void testGetMemberships() throws Exception {

		// Include anonymous users

		JSONObject membershipsJSONObject = _objectMapper.convertValue(
			_individualSegmentsRestController.getMembershipDTOPageDTO(
				327968823603500666L, null, 0, 20, null),
			JSONObject.class);

		JSONObject embeddedJSONObject = membershipsJSONObject.getJSONObject(
			"_embedded");

		JSONArray membershipsJSONArray = embeddedJSONObject.getJSONArray(
			"memberships");

		Assertions.assertEquals(2, membershipsJSONArray.length());

		// Does not include anonymous users

		membershipsJSONObject = _objectMapper.convertValue(
			_individualSegmentsRestController.getMembershipDTOPageDTO(
				327968823603500677L, null, 0, 20, null),
			JSONObject.class);

		embeddedJSONObject = membershipsJSONObject.getJSONObject("_embedded");

		membershipsJSONArray = embeddedJSONObject.getJSONArray("memberships");

		Assertions.assertEquals(1, membershipsJSONArray.length());
	}

	@Test
	public void testPostIndividualSegment1() throws Exception {
		Segment segment = FaroInfoTestUtil.buildDynamicSegment("");

		_segmentDog.addSegment(segment);

		JSONObject responseJSONObject = _objectMapper.convertValue(
			_individualSegmentsRestController.postSegment(
				_objectMapper.convertValue(segment, SegmentDTO.class)),
			JSONObject.class);

		Assertions.assertEquals(
			"IN_PROGRESS", responseJSONObject.getString("state"));
	}

	@Test
	public void testPostIndividualSegment2() throws Exception {
		Segment segment = FaroInfoTestUtil.buildStaticSegment();

		_segmentDog.addSegment(segment);

		JSONObject responseJSONObject = _objectMapper.convertValue(
			_individualSegmentsRestController.postSegment(
				_objectMapper.convertValue(segment, SegmentDTO.class)),
			JSONObject.class);

		Assertions.assertEquals("READY", responseJSONObject.getString("state"));
	}

	@ElasticsearchIndex(
		name = "field-mappings", resourcePath = "field_mappings.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individual-segments", resourcePath = "individual_segments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources_1.json"
	)
	@Test
	public void testPreviewDisabledSegments() throws Exception {
		Assertions.assertEquals(
			3L,
			JSONUtil.getValue(
				_objectMapper.convertValue(
					_individualSegmentsRestController.
						getPreviewDisabledSegmentDTOPageDTO(
							351238757269547424L, null, 0, 10, null),
					JSONObject.class),
				"JSONObject/page", "Object/totalElements"));
		Assertions.assertEquals(
			2L,
			JSONUtil.getValue(
				_objectMapper.convertValue(
					_individualSegmentsRestController.
						getPreviewDisabledSegmentDTOPageDTO(
							351238757269547424L, "((status eq 'ACTIVE'))", 0,
							10, null),
					JSONObject.class),
				"JSONObject/page", "Object/totalElements"));
	}

	@Test
	public void testPutIndividualSegment1() throws Exception {
		Segment segment = _segmentDog.addSegment(
			FaroInfoTestUtil.buildDynamicSegment(""));

		Long segmentId = segment.getId();

		JSONObject responseJSONObject = _objectMapper.convertValue(
			_individualSegmentsRestController.putIndividualSegment(
				segmentId,
				new SegmentDTO(
					_membershipChangeDog.
						getLastBeforeTodayByIndividualSegmentId(segmentId),
					_segmentDog.getLastActivityDate(segment), segment)),
			JSONObject.class);

		Assertions.assertEquals(
			"IN_PROGRESS", responseJSONObject.getString("state"));
	}

	@Test
	public void testPutIndividualSegment2() throws Exception {
		Segment segment = _segmentDog.addSegment(
			FaroInfoTestUtil.buildStaticSegment());

		Long segmentId = segment.getId();

		JSONObject responseJSONObject = _objectMapper.convertValue(
			_individualSegmentsRestController.putIndividualSegment(
				segmentId,
				new SegmentDTO(
					_membershipChangeDog.
						getLastBeforeTodayByIndividualSegmentId(segmentId),
					_segmentDog.getLastActivityDate(segment), segment)),
			JSONObject.class);

		Assertions.assertEquals("READY", responseJSONObject.getString("state"));
	}

	@Autowired
	private BQMembershipRepository _bqMembershipRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private IndividualSegmentsRestController _individualSegmentsRestController;

	@Autowired
	private MembershipChangeDog _membershipChangeDog;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentDog _segmentDog;

}