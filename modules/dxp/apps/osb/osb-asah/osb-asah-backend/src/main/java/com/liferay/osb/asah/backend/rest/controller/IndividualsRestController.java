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

import com.liferay.osb.asah.backend.dto.DistributionDTO;
import com.liferay.osb.asah.backend.dto.PageDTO;
import com.liferay.osb.asah.common.findbugs.SuppressFBWarnings;
import com.liferay.osb.asah.common.model.Distribution;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 * @author David Bhasme
 */
@RequestMapping("/individuals")
@RestController(
	"com.liferay.osb.asah.backend.rest.controller.IndividualsRestController"
)
@SuppressFBWarnings("NM_SAME_SIMPLE_NAME_AS_SUPERCLASS")
public class IndividualsRestController
	extends com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.
				IndividualsRestController {

	@GetMapping("/distribution")
	public PageDTO<DistributionDTO> getDistributionDTOPageDTO(
			@RequestParam Long fieldMappingId,
			@RequestParam(name = "filter", required = false) String
				filterString,
			@RequestParam(defaultValue = "10") int numberOfBins,
			@RequestParam(defaultValue = "100") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		// TODO Implement operation

		return _toDistributionDTOPageDTO(Page.empty());
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
				"individuals-distribution-transformations"),
			distributionsPage);
	}

}