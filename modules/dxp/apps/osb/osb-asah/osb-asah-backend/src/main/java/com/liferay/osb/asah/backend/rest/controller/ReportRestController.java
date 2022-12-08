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

package com.liferay.osb.asah.backend.rest.controller;

import com.liferay.osb.asah.backend.dto.PageDTO;
import com.liferay.osb.asah.backend.dto.ReportIndividualDTO;
import com.liferay.osb.asah.backend.dto.ReportSegmentDTO;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.BQMembershipChangeDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.entity.BQMembershipChange;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.model.Individual;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.util.ListUtil;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marcellus Tavares
 */
@RequestMapping(produces = "application/json", value = "/reports")
@RestController(
	"com.liferay.osb.asah.backend.rest.controller.ReportRestController"
)
public class ReportRestController extends BaseRestController {

	@GetMapping("/individuals")
	public PageDTO<ReportIndividualDTO> getReportIndividualDTOPageDTO(
		@RequestParam(defaultValue = "", value = "after") String afterId,
		@RequestParam("fromDate") String fromDate,
		@RequestParam("toDate") String toDate) {

		// TODO Implement operation

		return _toReportIndividualDTOPageDTO(Page.empty());
	}

	@GetMapping("/segments")
	public PageDTO<ReportSegmentDTO> getReportSegmentDTOPageDTO(
		@RequestParam(defaultValue = "", value = "after") String afterId,
		@RequestParam("fromDate") String fromDate,
		@RequestParam("toDate") String toDate) {

		return _toReportSegmentDTOPageDTO(
			_segmentDog.getSegmentPage(
				DateUtil.toUTCDate(fromDate), _getId(afterId), _PAGE_SIZE,
				Sort.by(Sort.Order.asc("id")), DateUtil.toUTCDate(toDate)));
	}

	private Long _getId(String id) {
		if (StringUtils.isBlank(id)) {
			return 0L;
		}

		return Long.valueOf(id);
	}

	private PageDTO<ReportIndividualDTO> _toReportIndividualDTOPageDTO(
		Page<Individual> individualPage) {

		return new PageDTO<>(
			ListUtil.map(individualPage.toList(), ReportIndividualDTO::new),
			individualPage.getTotalElements());
	}

	private PageDTO<ReportSegmentDTO> _toReportSegmentDTOPageDTO(
		Page<Segment> segmentPage) {

		List<Segment> segments = segmentPage.toList();

		Map<Long, BQMembershipChange> bqMembershipChanges =
			_bqMembershipChangeDog.getBQMembershipChanges(segments);

		return new PageDTO<>(
			ListUtil.map(
				segments,
				segment -> new ReportSegmentDTO(
					bqMembershipChanges.getOrDefault(segment.getId(), null),
					segment)),
			segmentPage.getTotalElements());
	}

	private static final int _PAGE_SIZE = 100;

	@Autowired
	private BQMembershipChangeDog _bqMembershipChangeDog;

	@Autowired
	private SegmentDog _segmentDog;

}