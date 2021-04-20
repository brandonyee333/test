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
import com.liferay.osb.asah.backend.dto.TransformationDTO;
import com.liferay.osb.asah.common.dog.AccountDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.model.Transformation;

import org.json.JSONObject;

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
	public PageDTO<AccountDTO> getAccountDTOsPageDTO(
		@RequestParam(required = false) Long channelId,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size,
		@RequestParam(name = "sort", required = false) String[] sorts) {

		Page<Account> accounts = _accountDog.searchAccountsPage(
			channelId, filterString, page, size, sorts);

		return _toPageDTO(accounts);
	}

	@GetMapping("/distribution")
	public PageDTO<DistributionDTO> getDistributionDTOsPageDTO(
		@RequestParam(required = false) Long channelId,
		@RequestParam Long fieldMappingId,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(required = false) Long individualSegmentId,
		@RequestParam(defaultValue = "10") int numberOfBins,
		@RequestParam(defaultValue = "100") int size,
		@RequestParam(name = "sort", required = false) String[] sorts) {

		JSONObject fieldMappingJSONObject = faroInfoElasticsearchInvoker.fetch(
			"field-mappings", String.valueOf(fieldMappingId));

		if (fieldMappingJSONObject == null) {
			throw new IllegalArgumentException(
				"Invalid field mapping ID " + fieldMappingId);
		}

		String ownerType = fieldMappingJSONObject.getString("ownerType");

		if (!ownerType.equals("account")) {
			throw new IllegalArgumentException(
				"Unable to use non-account field " +
					fieldMappingJSONObject.getString("fieldName") + " to " +
						"distribute accounts");
		}

		return _toDistributionDTOsPageDTO(
			_accountDog.getDistributionsPage(
				channelId, fieldMappingJSONObject.getString("fieldName"),
				fieldMappingJSONObject.getString("fieldType"), filterString,
				individualSegmentId, numberOfBins, size, sorts));
	}

	@GetMapping(params = "!apply", value = "/{id}/individual-segments")
	public String getIndividualSegments(
			@PathVariable String id,
			@RequestParam(name = "filter", required = false) String
				filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		return toCollectionGetResponse(
			"individual-segments", null, page,
			_getIndividualSegmentsQueryBuilder(id, filterString), size, sorts);
	}

	@GetMapping(params = "apply", value = "/{id}/individual-segments")
	public PageDTO<TransformationDTO> getSegmentTransformationDTOsPageDTO(
			@PathVariable Long id, @RequestParam String apply,
			@RequestParam(name = "filter", required = false) String
				filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size)
		throws Exception {

		return _toTransformationDTOsPageDTO(
			"individual-segment-transformations",
			_segmentDog.getTransformationsPage(
				id, apply, filterString, page, size));
	}

	@GetMapping(params = "apply")
	public PageDTO<TransformationDTO> getTransformationDTOsPageDTO(
		@RequestParam String apply,
		@RequestParam(required = false) Long channelId,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size) {

		return _toTransformationDTOsPageDTO(
			_accountDog.getTransformationsPage(
				apply, channelId, filterString, page, size));
	}

	private PageDTO<DistributionDTO> _toDistributionDTOsPageDTO(
		DistributionDTO distributionDTO, Page<Distribution> distributions) {

		return new PageDTO<>(
			"_embedded", distributionDTO, distributions.getNumber(),
			distributions.getSize(), distributions.getTotalElements(),
			distributions.getTotalPages());
	}

	private PageDTO<DistributionDTO> _toDistributionDTOsPageDTO(
		Page<Distribution> distributions) {

		return _toDistributionDTOsPageDTO(
			new DistributionDTO(
				distributions.getContent(),
				"accounts-distribution-transformations"),
			distributions);
	}

	private PageDTO<AccountDTO> _toPageDTO(
		AccountDTO accountDTO, Page<Account> accounts) {

		return new PageDTO<>(
			"_embedded", accountDTO, accounts.getNumber(), accounts.getSize(),
			accounts.getTotalElements(), accounts.getTotalPages());
	}

	private PageDTO<AccountDTO> _toPageDTO(Page<Account> accounts) {
		return _toPageDTO(new AccountDTO(accounts.getContent()), accounts);
	}

	private PageDTO<TransformationDTO> _toTransformationDTOsPageDTO(
		Page<Transformation> transformationsPage) {

		return _toTransformationDTOsPageDTO(
			"account-transformations", transformationsPage);
	}

	private PageDTO<TransformationDTO> _toTransformationDTOsPageDTO(
		String transformationKey, Page<Transformation> transformationsPage) {

		return _toTransformationDTOsPageDTO(
			new TransformationDTO(
				transformationKey, transformationsPage.getContent()),
			transformationsPage);
	}

	private PageDTO<TransformationDTO> _toTransformationDTOsPageDTO(
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
	private SegmentDog _segmentDog;

}