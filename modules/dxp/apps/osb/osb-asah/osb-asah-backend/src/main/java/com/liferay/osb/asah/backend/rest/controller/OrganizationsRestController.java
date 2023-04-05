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
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.util.MatcherUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

		Matcher matcher = MatcherUtil.getMatcher(apply);

		if (!matcher.matches()) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST, "Invalid apply string " + apply);
		}

		String groupByField = matcher.group("groupByField");

		Page<String> bqOrganizationFieldValuePage =
			_bqOrganizationDog.getBQOrganizationFieldValuePage(
				channelId, filterString, groupByField, page, size);

		List<Transformation> transformations = new ArrayList<>();

		for (String fieldValue : bqOrganizationFieldValuePage.getContent()) {
			Transformation transformation = new Transformation();

			transformation.setTerm(
				new Transformation.Term(
					Collections.singletonMap(groupByField, fieldValue)));
			transformation.setTotalElements(0);

			transformations.add(transformation);
		}

		return new PageDTO<>(
			"_embedded",
			new TransformationDTO(
				"organization-transformations", transformations),
			bqOrganizationFieldValuePage.getNumber(),
			bqOrganizationFieldValuePage.getSize(),
			bqOrganizationFieldValuePage.getTotalElements(),
			bqOrganizationFieldValuePage.getTotalPages());
	}

	@Autowired
	private BQOrganizationDog _bqOrganizationDog;

}