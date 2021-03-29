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

import com.liferay.osb.asah.backend.rest.controller.IndividualSegmentsRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.dto.SegmentDTO;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Segment;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Vishal Reddy
 * @author Rachael Koestartyo
 */
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class IndividualSegmentsRestControllerTest {

	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individual-segments", resourcePath = "individual_segments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test(expected = OSBAsahException.class)
	public void testAssignChannelAlreadyAssigned() throws Exception {
		_individualSegmentsRestController.assignChannel(
			1L, 327968823603500655L);

		_individualSegmentsRestController.assignChannel(
			1L, 327968823603500655L);
	}

	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels_2.json",
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
		name = "memberships", resourcePath = "memberships_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testAssignChannelModifiesIndividualCount() throws Exception {
		_individualSegmentsRestController.assignChannel(
			1L, 366637689379787789L);

		_elasticsearchInvoker.refresh();

		JSONObject individualSegmentJSONObject = _elasticsearchInvoker.fetch(
			"individual-segments", "366637689379787789");

		Assert.assertEquals(
			"1", individualSegmentJSONObject.getString("channelId"));
		Assert.assertEquals(
			1, individualSegmentJSONObject.getInt("individualCount"));
	}

	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels_2.json",
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
	public void testAssignChannelStaticSegment() throws Exception {
		_individualSegmentsRestController.assignChannel(
			1L, 338511451975440187L);

		JSONObject individualSegmentJSONObject = _elasticsearchInvoker.get(
			"individual-segments", "338511451975440187");

		Assert.assertEquals(
			"1", individualSegmentJSONObject.optString("channelId"));

		Assert.assertEquals(
			1,
			_elasticsearchInvoker.count(
				"memberships",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"individualSegmentId", "338511451975440187")
				).filter(
					QueryBuilders.termQuery("status", "ACTIVE")
				)));
	}

	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetAccounts() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_individual_segment_accounts.json", this),
			new JSONObject(
				_individualSegmentsRestController.getAccounts(
					"366637689379787789", null, 0, 20, null)),
			false);
	}

	@ElasticsearchIndex(
		name = "activities", resourcePath = "activities.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources.json",
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
	@Test
	public void testGetIndividuals() throws Exception {

		// Include anonymous users

		JSONObject individualsJSONObject = new JSONObject(
			_individualSegmentsRestController.getIndividuals(
				"327968823603500666", null, true, 0, 20, null));

		JSONObject embeddedJSONObject = individualsJSONObject.getJSONObject(
			"_embedded");

		JSONArray individualsJSONArray = embeddedJSONObject.getJSONArray(
			"individuals");

		Assert.assertEquals(2, individualsJSONArray.length());

		// Does not include anonymous users

		individualsJSONObject = new JSONObject(
			_individualSegmentsRestController.getIndividuals(
				"327968823603500666", null, false, 0, 20, null));

		embeddedJSONObject = individualsJSONObject.getJSONObject("_embedded");

		individualsJSONArray = embeddedJSONObject.getJSONArray("individuals");

		Assert.assertEquals(1, individualsJSONArray.length());
	}

	@ElasticsearchIndex(
		name = "activities", resourcePath = "activities.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources.json",
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
	@ElasticsearchIndex(
		name = "membership-changes", resourcePath = "membership_changes.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "memberships", resourcePath = "memberships.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetMembershipChanges() throws Exception {

		// Include anonymous users

		JSONObject membershipChangesJSONObject = new JSONObject(
			_individualSegmentsRestController.getMembershipChanges(
				"327968823603500666", null, null, 0, 20, null));

		JSONObject embeddedJSONObject =
			membershipChangesJSONObject.getJSONObject("_embedded");

		JSONArray membershipChangesJSONArray = embeddedJSONObject.getJSONArray(
			"membership-changes");

		Assert.assertEquals(2, membershipChangesJSONArray.length());

		// Does not include anonymous users

		membershipChangesJSONObject = _objectMapper.convertValue(
			_individualSegmentsRestController.getMembershipDTOsPageDTO(
				327968823603500677L, null, 0, 20, null),
			JSONObject.class);

		embeddedJSONObject = membershipChangesJSONObject.getJSONObject(
			"_embedded");

		membershipChangesJSONArray = embeddedJSONObject.getJSONArray(
			"memberships");

		Assert.assertEquals(1, membershipChangesJSONArray.length());
	}

	@ElasticsearchIndex(
		name = "activities", resourcePath = "activities.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources.json",
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
	@ElasticsearchIndex(
		name = "memberships", resourcePath = "memberships.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetMemberships() throws Exception {

		// Include anonymous users

		JSONObject membershipsJSONObject = _objectMapper.convertValue(
			_individualSegmentsRestController.getMembershipDTOsPageDTO(
				327968823603500666L, null, 0, 20, null),
			JSONObject.class);

		JSONObject embeddedJSONObject = membershipsJSONObject.getJSONObject(
			"_embedded");

		JSONArray membershipsJSONArray = embeddedJSONObject.getJSONArray(
			"memberships");

		Assert.assertEquals(2, membershipsJSONArray.length());

		// Does not include anonymous users

		membershipsJSONObject = _objectMapper.convertValue(
			_individualSegmentsRestController.getMembershipDTOsPageDTO(
				327968823603500677L, null, 0, 20, null),
			JSONObject.class);

		embeddedJSONObject = membershipsJSONObject.getJSONObject("_embedded");

		membershipsJSONArray = embeddedJSONObject.getJSONArray("memberships");

		Assert.assertEquals(1, membershipsJSONArray.length());
	}

	@Test
	public void testPostIndividualSegment1() throws Exception {
		Segment segment = FaroInfoTestUtil.buildDynamicSegment("");

		_segmentDog.addSegment(segment);

		JSONObject responseJSONObject = _objectMapper.convertValue(
			_individualSegmentsRestController.postSegment(
				_objectMapper.convertValue(segment, SegmentDTO.class)),
			JSONObject.class);

		Assert.assertEquals(
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

		Assert.assertEquals("READY", responseJSONObject.getString("state"));
	}

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources.json",
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
	@Test
	public void testPreviewDisabledSegments() throws Exception {
		JSONArray dataSourcesJSONArray = _elasticsearchInvoker.get(
			"data-sources");

		JSONObject dataSourceJSONObject = dataSourcesJSONArray.getJSONObject(0);

		Assert.assertEquals(
			3L,
			JSONUtil.getValue(
				_objectMapper.convertValue(
					_individualSegmentsRestController.
						getPreviewDisabledSegmentDTOsPageDTOs(
							Long.valueOf(dataSourceJSONObject.getString("id")),
							null, 0, 10, null),
					JSONObject.class),
				"JSONObject/page", "Object/totalElements"));
		Assert.assertEquals(
			2L,
			JSONUtil.getValue(
				_objectMapper.convertValue(
					_individualSegmentsRestController.
						getPreviewDisabledSegmentDTOsPageDTOs(
							Long.valueOf(dataSourceJSONObject.getString("id")),
							"((status eq 'ACTIVE'))", 0, 10, null),
					JSONObject.class),
				"JSONObject/page", "Object/totalElements"));
	}

	@Test
	public void testPutIndividualSegment1() throws Exception {
		Segment segment = _segmentDog.addSegment(
			FaroInfoTestUtil.buildDynamicSegment(""));

		JSONObject responseJSONObject = _objectMapper.convertValue(
			_individualSegmentsRestController.putIndividualSegment(
				segment.getId(), new SegmentDTO(segment)),
			JSONObject.class);

		Assert.assertEquals(
			"IN_PROGRESS", responseJSONObject.getString("state"));
	}

	@Test
	public void testPutIndividualSegment2() throws Exception {
		Segment segment = _segmentDog.addSegment(
			FaroInfoTestUtil.buildStaticSegment());

		JSONObject responseJSONObject = _objectMapper.convertValue(
			_individualSegmentsRestController.putIndividualSegment(
				segment.getId(), new SegmentDTO(segment)),
			JSONObject.class);

		Assert.assertEquals("READY", responseJSONObject.getString("state"));
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private IndividualSegmentsRestController _individualSegmentsRestController;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentDog _segmentDog;

}