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

import com.liferay.osb.asah.backend.dto.FieldMappingDTO;
import com.liferay.osb.asah.backend.dto.PageDTO;
import com.liferay.osb.asah.common.dog.BQFieldMappingDog;
import com.liferay.osb.asah.common.entity.BQFieldMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 * @author David Bhasme
 */
@RequestMapping("/field-mappings")
@RestController
public class FieldMappingsRestController extends BaseRestController {

	@GetMapping("/{id}")
	public FieldMappingDTO getFieldMappingDTO(@PathVariable String id) {
		return new FieldMappingDTO(_bqFieldMappingDog.getBQFieldMapping(id));
	}

	@GetMapping(params = "!apply")
	public PageDTO<FieldMappingDTO> getFieldMappingDTOPageDTO(
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size,
		@RequestParam(name = "sort", required = false) String[] sorts) {

		return _toPageDTO(
			_bqFieldMappingDog.searchBQFieldMappingPage(
				filterString, page, size, sorts));
	}

	private PageDTO<FieldMappingDTO> _toPageDTO(
		Page<BQFieldMapping> bqFieldMappingPage) {

		return new PageDTO<>(
			"_embedded", new FieldMappingDTO(bqFieldMappingPage.getContent()),
			bqFieldMappingPage.getNumber(), bqFieldMappingPage.getSize(),
			bqFieldMappingPage.getTotalElements(),
			bqFieldMappingPage.getTotalPages());
	}

	@Autowired
	private BQFieldMappingDog _bqFieldMappingDog;

}