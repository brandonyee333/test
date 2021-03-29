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
import com.liferay.osb.asah.backend.rest.response.TermsAggregationTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.dog.FieldDog;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.model.Field;

import org.springframework.beans.factory.annotation.Autowired;
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
	public FieldDTO getField(@PathVariable Long id) throws Exception {
		return new FieldDTO(_fieldDog.getField(id));
	}

	@GetMapping(params = "!apply")
	public String getFields(
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		return toCollectionGetResponse(
			"fields", null, page,
			FilterStringToQueryBuilderConverter.convert(filterString), size,
			sorts);
	}

	@GetMapping(params = "apply")
	public String getFieldTransformations(
			@RequestParam String apply,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size)
		throws Exception {

		return toTransformationGetResponse(
			"fields", page,
			FilterStringToQueryBuilderConverter.convert(filterString), size,
			null, null,
			new TermsAggregationTransformationJSONArrayFunction(apply, null),
			"field-transformations");
	}

	@PutMapping("/{id}")
	public FieldDTO putField(
			@PathVariable Long id, @RequestBody FieldDTO fieldDTO)
		throws Exception {

		Field field = _fieldDog.updateField(
			id, _objectMapper.convertValue(fieldDTO, Field.class));

		return new FieldDTO(field);
	}

	@Autowired
	private FieldDog _fieldDog;

	@Autowired
	private ObjectMapper _objectMapper;

}