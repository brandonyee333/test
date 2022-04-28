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
import com.liferay.osb.asah.backend.dto.ReportAccountDTO;
import com.liferay.osb.asah.backend.dto.ReportIndividualDTO;
import com.liferay.osb.asah.backend.dto.ReportSegmentDTO;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.AccountDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.entity.Individual;
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
		@RequestParam(defaultValue = "") String after,
		@RequestParam("fromDate") String fromDateString,
		@RequestParam("toDate") String toDateString) {

		return _toReportAccountDTOPageDTO(
			_accountDog.getAccountPage(
				_getId(after), DateUtil.toUTCDate(fromDateString), _PAGE_SIZE,
				Sort.by(Sort.Order.asc("id")),
				DateUtil.toUTCDate(toDateString)));
	}

	@GetMapping("/individuals")
	public PageDTO<ReportIndividualDTO> getReportIndividualDTOPageDTO(
		@RequestParam(defaultValue = "") String after,
		@RequestParam("fromDate") String fromDateString,
		@RequestParam("toDate") String toDateString) {

		return _toReportIndividualDTOPageDTO(
			_individualDog.getIndividualPage(
				DateUtil.toUTCDate(fromDateString), _getId(after), _PAGE_SIZE,
				Sort.by(Sort.Order.asc("id")),
				DateUtil.toUTCDate(toDateString)));
	}

	@GetMapping("/segments")
	public PageDTO<ReportSegmentDTO> getReportSegmentDTOPageDTO(
		@RequestParam(defaultValue = "") String after,
		@RequestParam("fromDate") String fromDateString,
		@RequestParam("toDate") String toDateString) {

		return _toReportSegmentDTOPageDTO(
			_segmentDog.getSegmentPage(
				DateUtil.toUTCDate(fromDateString), _getId(after), _PAGE_SIZE,
				Sort.by(Sort.Order.asc("id")),
				DateUtil.toUTCDate(toDateString)));
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

	private PageDTO<ReportSegmentDTO> _toReportSegmentDTOPageDTO(
		Page<Segment> segmentPage) {

		return new PageDTO<>(
			ListUtil.map(segmentPage.toList(), ReportSegmentDTO::new),
			segmentPage.getTotalElements());
	}

	private static final int _PAGE_SIZE = 100;

	@Autowired
	private AccountDog _accountDog;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private SegmentDog _segmentDog;

}