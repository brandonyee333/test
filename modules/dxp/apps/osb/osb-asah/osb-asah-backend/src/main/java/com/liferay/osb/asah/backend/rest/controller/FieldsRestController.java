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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.backend.dto.FieldDTO;
import com.liferay.osb.asah.backend.dto.PageDTO;
import com.liferay.osb.asah.backend.dto.TransformationDTO;
import com.liferay.osb.asah.common.dog.FieldDog;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.model.Transformation;

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
 */
@RequestMapping("/fields")
@RestController
public class FieldsRestController extends BaseRestController {

	@DeleteMapping("/{id}")
	public void deleteField(@PathVariable Long id) {
		_fieldDog.deleteField(id);
	}

	@GetMapping("/{id}")
	public FieldDTO getFieldDTO(@PathVariable Long id) {
		return new FieldDTO(_fieldDog.getField(id));
	}

	@GetMapping(params = "!apply")
	public PageDTO<FieldDTO> getFieldDTOsPageDTO(
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size,
		@RequestParam(name = "sort", required = false) String[] sorts) {

		return _toPageDTO(
			_fieldDog.searchFieldPage(
				filterString, page, Math.max(1, size), sorts));
	}

	@GetMapping(params = "apply")
	public PageDTO<TransformationDTO> getTransformationDTOsPageDTO(
		@RequestParam String apply,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size) {

		return _toTransformationDTOsPageDTO(
			"field-transformations",
			_fieldDog.getTransformationPage(apply, filterString, page, size));
	}

	@PutMapping("/{id}")
	public FieldDTO putField(
		@PathVariable Long id, @RequestBody FieldDTO fieldDTO) {

		Field field = _fieldDog.updateField(
			id, _objectMapper.convertValue(fieldDTO, Field.class));

		return new FieldDTO(field);
	}

	private PageDTO<FieldDTO> _toPageDTO(
		FieldDTO fieldDTO, Page<Field> fieldsPage) {

		return new PageDTO<>(
			"_embedded", fieldDTO, fieldsPage.getNumber(), fieldsPage.getSize(),
			fieldsPage.getTotalElements(), fieldsPage.getTotalPages());
	}

	private PageDTO<FieldDTO> _toPageDTO(Page<Field> fieldsPage) {
		return _toPageDTO(new FieldDTO(fieldsPage.getContent()), fieldsPage);
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
	private FieldDog _fieldDog;

	@Autowired
	private ObjectMapper _objectMapper;

}