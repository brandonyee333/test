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
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.FieldMappingDog;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.commons.math3.util.Pair;

import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rachael Koestartyo
 */
@RequestMapping("/definitions")
@RestController
public class DefinitionsRestController extends BaseRestController {

	@GetMapping("/individual-attributes")
	public PageDTO<FieldMappingDTO> getIndividualFieldMappingDTOsPageDTO(
		@RequestParam(required = false) String name) {

		Page<FieldMapping> fieldMappingsPage =
			_fieldMappingDog.searchIndividualFieldMappingsPage(
				name, 0,
				Math.max(
					1,
					(int)_fieldMappingDog.countIndividualFieldMappings(name)),
				new String[] {"fieldName", "asc"});

		Map<String, FieldMappingDTO> fieldMappingDTOs = Stream.of(
			fieldMappingsPage.getContent()
		).flatMap(
			List::stream
		).map(
			FieldMappingDTO::new
		).collect(
			LinkedHashMap::new,
			(map, fieldMappingDTO) -> map.put(
				fieldMappingDTO.getId(), fieldMappingDTO),
			Map::putAll
		);

		_addDataSources(fieldMappingDTOs);

		return _toPageDTO(
			new FieldMappingDTO(fieldMappingDTOs.values()), fieldMappingsPage);
	}

	private void _addDataSources(
		Map<String, FieldMappingDTO> fieldMappingDTOs) {

		for (Map.Entry<String, FieldMappingDTO> entry :
				fieldMappingDTOs.entrySet()) {

			FieldMappingDTO fieldMappingDTO = entry.getValue();

			List<Pair<String, String>> pairs = new ArrayList<>();

			Map<String, String> dataSourceFieldNames =
				fieldMappingDTO.getDataSourceFieldNames();

			for (Map.Entry<String, String> dataSourceFieldNameEntry :
					dataSourceFieldNames.entrySet()) {

				pairs.add(
					new Pair<>(
						_dataSourceDog.getDataSourceName(
							Long.valueOf(dataSourceFieldNameEntry.getKey())),
						dataSourceFieldNameEntry.getValue()));
			}

			pairs.sort(Comparator.comparing(Pair::getKey));

			JSONArray pairsJSONArray = new JSONArray();

			for (Pair<String, String> pair : pairs) {
				pairsJSONArray.put(
					JSONUtil.put(
						"dataSourceFieldName", pair.getValue()
					).put(
						"dataSourceName", pair.getKey()
					));
			}

			fieldMappingDTO.setDataSourcesJSONArray(pairsJSONArray);
		}
	}

	private PageDTO<FieldMappingDTO> _toPageDTO(
		FieldMappingDTO fieldMappingDTO, Page<FieldMapping> fieldMappingsPage) {

		return new PageDTO<>(
			"_embedded", fieldMappingDTO, fieldMappingsPage.getNumber(),
			fieldMappingsPage.getSize(), fieldMappingsPage.getTotalElements(),
			fieldMappingsPage.getTotalPages());
	}

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private FieldMappingDog _fieldMappingDog;

}