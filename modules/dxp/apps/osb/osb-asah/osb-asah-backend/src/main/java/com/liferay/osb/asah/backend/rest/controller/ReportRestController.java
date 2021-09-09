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

import com.liferay.osb.asah.backend.dog.ReportAccountDog;
import com.liferay.osb.asah.backend.dog.ReportIndividualDog;
import com.liferay.osb.asah.backend.dto.PageDTO;
import com.liferay.osb.asah.backend.dto.ReportAccountDTO;
import com.liferay.osb.asah.backend.dto.ReportIndividualDTO;
import com.liferay.osb.asah.backend.dto.SegmentDTO;
import com.liferay.osb.asah.backend.model.Account;
import com.liferay.osb.asah.backend.model.Individual;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.util.ListUtil;

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

	@GetMapping("/accounts")
	public PageDTO<ReportAccountDTO> getReportAccountDTOPageDTO(
		@RequestParam(defaultValue = "") String after) {

		return _toReportAccountDTOPageDTO(
			_reportAccountDog.getAccountPage(
				_getId(after), _PAGE_SIZE, Sort.by(Sort.Order.asc("id"))));
	}

	@GetMapping("/individuals")
	public PageDTO<ReportIndividualDTO> getReportIndividualDTOPageDTO(
		@RequestParam(defaultValue = "") String after) {

		return _toReportIndividualDTOPageDTO(
			_reportIndividualDog.getIndividualPage(
				_getId(after), _PAGE_SIZE, Sort.by(Sort.Order.asc("id"))));
	}

	@GetMapping("/segments")
	public PageDTO<SegmentDTO> getSegmentDTOPageDTO(
		@RequestParam(defaultValue = "") String after) {

		return _toSegmentDTOPageDTO(
			_segmentDog.getSegmentPage(
				_getId(after), _PAGE_SIZE, Sort.by(Sort.Order.asc("id"))));
	}

	private Long _getId(String id) {
		if (StringUtils.isBlank(id)) {
			return 0L;
		}

		return Long.valueOf(id);
	}

	private PageDTO<ReportAccountDTO> _toReportAccountDTOPageDTO(
		Page<Account> accountPage) {

		return new PageDTO<>(
			ListUtil.map(accountPage.toList(), ReportAccountDTO::new),
			accountPage.getTotalElements());
	}

	private PageDTO<ReportIndividualDTO> _toReportIndividualDTOPageDTO(
		Page<Individual> individualPage) {

		return new PageDTO<>(
			ListUtil.map(individualPage.toList(), ReportIndividualDTO::new),
			individualPage.getTotalElements());
	}

	private PageDTO<SegmentDTO> _toSegmentDTOPageDTO(
		Page<Segment> segmentPage) {

		return new PageDTO<>(
			ListUtil.map(segmentPage.toList(), SegmentDTO::new),
			segmentPage.getTotalElements());
	}

	private static final int _PAGE_SIZE = 100;

	@Autowired
	private ReportAccountDog _reportAccountDog;

	@Autowired
	private ReportIndividualDog _reportIndividualDog;

	@Autowired
	private SegmentDog _segmentDog;

}