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
import com.liferay.osb.asah.backend.dto.TransformationDTO;
import com.liferay.osb.asah.common.dog.BQOrganizationDog;
import com.liferay.osb.asah.common.model.Transformation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Matthew Kong
 */
@RequestMapping("/organizations")
@RestController
public class OrganizationsRestController extends BaseRestController {

	@GetMapping(params = "apply")
	public PageDTO<TransformationDTO> getTransformationDTOPageDTO(
		@RequestParam String apply,
		@RequestParam(required = false) Long channelId,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size) {

		Page<Transformation> bqOrganizationFieldValuePage =
			_bqOrganizationDog.getTransformationPage(
				apply, channelId, filterString, page, size);

		return new PageDTO<>(
			"_embedded",
			new TransformationDTO(
				"organization-transformations",
				bqOrganizationFieldValuePage.getContent()),
			bqOrganizationFieldValuePage.getNumber(),
			bqOrganizationFieldValuePage.getSize(),
			bqOrganizationFieldValuePage.getTotalElements(),
			bqOrganizationFieldValuePage.getTotalPages());
	}

	@Autowired
	private BQOrganizationDog _bqOrganizationDog;

}