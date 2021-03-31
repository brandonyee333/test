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

import com.liferay.osb.asah.backend.dog.AccountDog;
import com.liferay.osb.asah.backend.dog.IndividualDog;
import com.liferay.osb.asah.backend.dto.PageDTO;
import com.liferay.osb.asah.backend.dto.SegmentDTO;
import com.liferay.osb.asah.backend.model.Account;
import com.liferay.osb.asah.backend.model.Individual;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Segment;
import com.liferay.osb.asah.common.util.ListUtil;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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
	public ResultBag<Account> getAccountResultBag(
		@RequestParam(defaultValue = "") String after) {

		return _accountDog.getAccountResultBag(
			_createSearchAfter(after), _PAGE_SIZE,
			SortBuilderUtil.fieldSort("id"));
	}

	@GetMapping("/individuals")
	public ResultBag<Individual> getIndividualResultBag(
		@RequestParam(defaultValue = "") String after) {

		return _individualDog.getIndividualResultBag(
			_createSearchAfter(after), _PAGE_SIZE,
			SortBuilderUtil.fieldSort("id"));
	}

	@GetMapping("/segments")
	public PageDTO<SegmentDTO> getSegmentDTOPageDTO(
		@RequestParam(defaultValue = "") String after) {

		return _toPageDTO(
			_segmentDog.getSegmentPage(
				_getId(after), _PAGE_SIZE, Sort.by(Sort.Order.asc("id"))));
	}

	private String[] _createSearchAfter(String after) {
		if (StringUtils.isBlank(after)) {
			return null;
		}

		return new String[] {after};
	}

	private Long _getId(String id) {
		if (StringUtils.isBlank(id)) {
			return 0L;
		}

		return Long.valueOf(id);
	}

	private PageDTO<SegmentDTO> _toPageDTO(Page<Segment> segmentPage) {
		return new PageDTO<>(
			ListUtil.map(segmentPage.toList(), SegmentDTO::new),
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