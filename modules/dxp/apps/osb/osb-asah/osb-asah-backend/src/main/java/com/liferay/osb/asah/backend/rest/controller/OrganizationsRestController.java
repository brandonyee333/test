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
import com.liferay.osb.asah.common.dog.OrganizationDog;
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
	public PageDTO<TransformationDTO> getTransformationDTOsPageDTO(
		@RequestParam String apply,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size) {

		return _toTransformationDTOsPageDTO(
			_organizationDog.getTransformationsPage(
				apply, filterString, page, size));
	}

	private PageDTO<TransformationDTO> _toTransformationDTOsPageDTO(
		Page<Transformation> transformationsPage) {

		return _toTransformationDTOsPageDTO(
			"organization-transformations", transformationsPage);
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
	private OrganizationDog _organizationDog;

}