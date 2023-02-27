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
import com.liferay.osb.asah.backend.rest.controller.ReportRestController;
import com.liferay.osb.asah.common.repository.BQIndividualRepository;
import com.liferay.osb.asah.common.repository.BQMembershipChangeRepository;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import org.json.JSONObject;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Alejo Ceballos
 */
@Disabled
public class ReportRestControllerTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@RepositoryResource(
		repositoryClass = BQIndividualRepository.class,
		resourcePath = "osbasahfaroinfo/individuals_3.json"
	)
	@Test
	public void testGetReportIndividualDTOPageDTO() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_individuals.json", this),
			_objectMapper.convertValue(
				_reportRestController.getReportIndividualDTOPageDTO(
					"0", "2019-02-09T10:00:00.000Z",
					"2019-02-14T13:00:00.000Z"),
				JSONObject.class),
			false);
	}

	@RepositoryResource(
		repositoryClass = BQMembershipChangeRepository.class,
		resourcePath = "osbasahfaroinfo/bq_membership_changes.json"
	)
	@RepositoryResource(
		repositoryClass = ChannelRepository.class,
		resourcePath = "osbasahfaroinfo/channels_2.json"
	)
	@RepositoryResource(
		repositoryClass = SegmentRepository.class,
		resourcePath = "osbasahfaroinfo/individual_segments_4.json"
	)
	@Test
	public void testGetReportSegmentDTOPageDTO() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_segments.json", this),
			_objectMapper.convertValue(
				_reportRestController.getReportSegmentDTOPageDTO(
					"0", "2019-02-03T10:00:00.000Z",
					"2019-02-06T13:00:00.000Z"),
				JSONObject.class),
			false);
	}

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private ReportRestController _reportRestController;

}