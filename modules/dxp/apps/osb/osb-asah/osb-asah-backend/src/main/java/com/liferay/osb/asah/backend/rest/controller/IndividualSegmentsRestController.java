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

import com.liferay.osb.asah.backend.dto.AccountDTO;
import com.liferay.osb.asah.backend.dto.PageDTO;
import com.liferay.osb.asah.backend.dto.SegmentDTO;
import com.liferay.osb.asah.common.dog.AccountDog;
import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.findbugs.SuppressFBWarnings;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 * @author David Bhasme
 * @author Rachael Koestartyo
 */
@RequestMapping("/individual-segments")
@RestController(
	"com.liferay.osb.asah.backend.rest.controller.IndividualSegmentsRestController"
)
@SuppressFBWarnings("NM_SAME_SIMPLE_NAME_AS_SUPERCLASS")
public class IndividualSegmentsRestController
	extends com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.
				IndividualSegmentsRestController {

	@PutMapping("/{id}/channel/{channelId}")
	public void assignChannel(
			@PathVariable Long channelId, @PathVariable Long id)
		throws Exception {

		segmentDog.assignChannel(channelId, id);
	}

	@DeleteMapping("/{id}/memberships/{individualId}")
	public void deleteIndividual(
		@PathVariable Long id, @PathVariable Long individualId) {

		membershipDog.deactivateBQMembership(new Date(), individualId, id);
	}

	@DeleteMapping("/{id}")
	public void deleteIndividualSegment(@PathVariable Long id) {
		segmentDog.deleteSegment(id);
	}

	@GetMapping("/{id}/accounts")
	public PageDTO<AccountDTO> getAccountDTOPageDTO(
		@PathVariable Long id,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size,
		@RequestParam(name = "sort", required = false) String[] sorts) {

		Segment segment = segmentDog.getSegment(id);

		return _toAccountDTOPageDTO(
			_accountDog.searchAccountPage(
				segment.getChannelId(), filterString, page, id, size, sorts));
	}

	@GetMapping("/preview-disabled-segments")
	public PageDTO<SegmentDTO> getPreviewDisabledSegmentDTOPageDTO(
		@RequestParam Long dataSourceId,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size,
		@RequestParam(name = "sort", required = false) String[] sorts) {

		return toSegmentDTOPageDTO(
			segmentDog.searchPreviewDisabledSegmentPage(
				dataSourceId, filterString, page, Math.max(1, size), sorts));
	}

	@PutMapping("/{id}")
	public SegmentDTO putIndividualSegment(
		@PathVariable Long id, @RequestBody SegmentDTO segmentDTO) {

		segmentDTO.setActiveIndividualsCount(null);
		segmentDTO.setActivitiesCount(null);
		segmentDTO.setAnonymousIndividualsCount(null);
		segmentDTO.setCreateDate(null);
		segmentDTO.setIndividualsCount(null);
		segmentDTO.setKnownIndividualsCount(null);
		segmentDTO.setModifiedDate(new Date());

		return objectMapper.convertValue(
			segmentDog.updateSegment(
				objectMapper.convertValue(segmentDTO, Segment.class), id),
			SegmentDTO.class);
	}

	private PageDTO<AccountDTO> _toAccountDTOPageDTO(
		AccountDTO accountDTO, Page<Account> accountsPage) {

		return new PageDTO<>(
			"_embedded", accountDTO, accountsPage.getNumber(),
			accountsPage.getSize(), accountsPage.getTotalElements(),
			accountsPage.getTotalPages());
	}

	private PageDTO<AccountDTO> _toAccountDTOPageDTO(
		Page<Account> accountsPage) {

		return _toAccountDTOPageDTO(
			new AccountDTO(accountsPage.getContent()), accountsPage);
	}

	@Autowired
	private AccountDog _accountDog;

}