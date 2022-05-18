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
import com.liferay.osb.asah.backend.dto.DistributionDTO;
import com.liferay.osb.asah.backend.dto.PageDTO;
import com.liferay.osb.asah.backend.dto.SegmentDTO;
import com.liferay.osb.asah.backend.dto.TransformationDTO;
import com.liferay.osb.asah.common.dog.AccountDog;
import com.liferay.osb.asah.common.dog.FieldMappingDog;
import com.liferay.osb.asah.common.dog.MembershipChangeDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.model.Transformation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 * @author Rachael Koestartyo
 */
@RequestMapping("/accounts")
@RestController(
	"com.liferay.osb.asah.backend.rest.controller.AccountsRestController"
)
public class AccountsRestController extends BaseRestController {

	@GetMapping("/{id}")
	public AccountDTO getAccountDTO(
		@PathVariable Long id, @RequestParam(required = false) Long channelId) {

		return new AccountDTO(_accountDog.getAccount(id, channelId));
	}

	@GetMapping(params = "!apply")
	public PageDTO<AccountDTO> getAccountDTOPageDTO(
		@RequestParam(required = false) Long channelId,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size,
		@RequestParam(name = "sort", required = false) String[] sorts) {

		return _toPageDTO(
			_accountDog.searchAccountPage(
				channelId, filterString, page, null, Math.max(1, size), sorts));
	}

	@GetMapping("/distribution")
	public PageDTO<DistributionDTO> getDistributionDTOPageDTO(
		@RequestParam(required = false) Long channelId,
		@RequestParam Long fieldMappingId,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(required = false) Long individualSegmentId,
		@RequestParam(defaultValue = "10") int numberOfBins,
		@RequestParam(defaultValue = "100") int size,
		@RequestParam(name = "sort", required = false) String[] sorts) {

		FieldMapping fieldMapping = _fieldMappingDog.fetchFieldMapping(
			fieldMappingId);

		if (fieldMapping == null) {
			throw new IllegalArgumentException(
				"Invalid field mapping ID " + fieldMappingId);
		}

		String ownerType = fieldMapping.getOwnerType();

		if (!ownerType.equals("account")) {
			throw new IllegalArgumentException(
				"Unable to use non-account field " +
					fieldMapping.getFieldName() + " to distribute accounts");
		}

		return _toDistributionDTOPageDTO(
			_accountDog.getDistributionPage(
				channelId, fieldMapping.getFieldName(),
				fieldMapping.getFieldType(), filterString, individualSegmentId,
				numberOfBins, size, sorts));
	}

	@GetMapping(params = "!apply", value = "/{id}/individual-segments")
	public PageDTO<SegmentDTO> getSegmentDTOPageDTO(
		@PathVariable Long id,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size,
		@RequestParam(name = "sort", required = false) String[] sorts) {

		return _toSegmentPageDTO(
			_segmentDog.searchAccountSegmentPage(
				id, filterString, page, size, sorts));
	}

	@GetMapping(params = "apply", value = "/{id}/individual-segments")
	public PageDTO<TransformationDTO> getSegmentTransformationDTOPageDTO(
			@PathVariable Long id, @RequestParam String apply,
			@RequestParam(name = "filter", required = false) String
				filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size)
		throws Exception {

		return _toTransformationDTOPageDTO(
			"individual-segment-transformations",
			_segmentDog.getTransformationPage(
				id, apply, filterString, page, size));
	}

	@GetMapping(params = "apply")
	public PageDTO<TransformationDTO> getTransformationDTOPageDTO(
		@RequestParam String apply,
		@RequestParam(required = false) Long channelId,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size) {

		return _toTransformationDTOPageDTO(
			_accountDog.getTransformationPage(
				apply, channelId, filterString, page, size));
	}

	private PageDTO<DistributionDTO> _toDistributionDTOPageDTO(
		DistributionDTO distributionDTO, Page<Distribution> distributionsPage) {

		return new PageDTO<>(
			"_embedded", distributionDTO, distributionsPage.getNumber(),
			distributionsPage.getSize(), distributionsPage.getTotalElements(),
			distributionsPage.getTotalPages());
	}

	private PageDTO<DistributionDTO> _toDistributionDTOPageDTO(
		Page<Distribution> distributionsPage) {

		return _toDistributionDTOPageDTO(
			new DistributionDTO(
				distributionsPage.getContent(),
				"accounts-distribution-transformations"),
			distributionsPage);
	}

	private PageDTO<AccountDTO> _toPageDTO(
		AccountDTO accountDTO, Page<Account> accountsPage) {

		return new PageDTO<>(
			"_embedded", accountDTO, accountsPage.getNumber(),
			accountsPage.getSize(), accountsPage.getTotalElements(),
			accountsPage.getTotalPages());
	}

	private PageDTO<AccountDTO> _toPageDTO(Page<Account> accountsPage) {
		return _toPageDTO(
			new AccountDTO(accountsPage.getContent()), accountsPage);
	}

	private PageDTO<SegmentDTO> _toSegmentPageDTO(Page<Segment> segmentsPage) {
		List<Segment> segments = segmentsPage.getContent();

		return _toSegmentPageDTO(
			new SegmentDTO(
				_bqMembershipChangeDog.getBQMembershipChanges(segments),
				segments),
			segmentsPage);
	}

	private PageDTO<SegmentDTO> _toSegmentPageDTO(
		SegmentDTO segmentDTO, Page<Segment> segmentsPage) {

		return new PageDTO<>(
			"_embedded", segmentDTO, segmentsPage.getNumber(),
			segmentsPage.getSize(), segmentsPage.getTotalElements(),
			segmentsPage.getTotalPages());
	}

	private PageDTO<TransformationDTO> _toTransformationDTOPageDTO(
		Page<Transformation> transformationsPage) {

		return _toTransformationDTOPageDTO(
			"account-transformations", transformationsPage);
	}

	private PageDTO<TransformationDTO> _toTransformationDTOPageDTO(
		String transformationKey, Page<Transformation> transformationsPage) {

		return _toTransformationDTOPageDTO(
			new TransformationDTO(
				transformationKey, transformationsPage.getContent()),
			transformationsPage);
	}

	private PageDTO<TransformationDTO> _toTransformationDTOPageDTO(
		TransformationDTO transformationDTO,
		Page<Transformation> transformationsPage) {

		return new PageDTO<>(
			"_embedded", transformationDTO, transformationsPage.getNumber(),
			transformationsPage.getSize(),
			transformationsPage.getTotalElements(),
			transformationsPage.getTotalPages());
	}

	@Autowired
	private AccountDog _accountDog;

	@Autowired
	private MembershipChangeDog _bqMembershipChangeDog;

	@Autowired
	private FieldMappingDog _fieldMappingDog;

	@Autowired
	private SegmentDog _segmentDog;

}