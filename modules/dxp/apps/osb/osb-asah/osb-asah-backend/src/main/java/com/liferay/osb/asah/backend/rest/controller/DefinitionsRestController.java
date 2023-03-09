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

import com.liferay.osb.asah.backend.dto.BQFieldMappingDTO;
import com.liferay.osb.asah.backend.dto.PageDTO;
import com.liferay.osb.asah.common.dog.BQFieldMappingDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.entity.BQFieldMapping;
import com.liferay.osb.asah.common.entity.DataSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.commons.math3.util.Pair;

import org.jetbrains.annotations.NotNull;

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
	public PageDTO<BQFieldMappingDTO> getIndividualFieldMappingDTOPageDTO(
		@RequestParam(required = false) String name) {

		Page<BQFieldMapping> bqFieldMappingPage =
			_bqFieldMappingDog.searchIndividualFieldMappingPage(
				name, 0,
				Math.max(
					1,
					(int)_bqFieldMappingDog.countIndividualFieldMappings(name)),
				new String[] {"fieldName", "asc"});

		Map<String, BQFieldMappingDTO> fieldMappingDTOs = Stream.of(
			bqFieldMappingPage.getContent()
		).flatMap(
			List::stream
		).map(
			this::_toFieldMappingDTO
		).collect(
			LinkedHashMap::new,
			(map, bqFieldMappingDTO) -> map.put(
				bqFieldMappingDTO.getFieldName(), bqFieldMappingDTO),
			Map::putAll
		);

		_addDataSources(fieldMappingDTOs);

		return _toPageDTO(
			new BQFieldMappingDTO(fieldMappingDTOs.values()),
			bqFieldMappingPage);
	}

	private void _addDataSources(
		Map<String, BQFieldMappingDTO> fieldMappingDTOs) {

		for (Map.Entry<String, BQFieldMappingDTO> entry :
				fieldMappingDTOs.entrySet()) {

			BQFieldMappingDTO bqFieldMappingDTO = entry.getValue();

			List<Pair<String, String>> pairs = new ArrayList<>();

			Map<String, String> dataSourceFieldNames =
				bqFieldMappingDTO.getDataSourceFieldNames();

			for (Map.Entry<String, String> dataSourceFieldNameEntry :
					dataSourceFieldNames.entrySet()) {

				pairs.add(
					new Pair<>(
						_dataSourceDog.getDataSourceName(
							Long.valueOf(dataSourceFieldNameEntry.getKey())),
						dataSourceFieldNameEntry.getValue()));
			}

			pairs.sort(Comparator.comparing(Pair::getKey));

			List<Map<String, String>> dataSources = new ArrayList<>();

			for (Pair<String, String> pair : pairs) {
				dataSources.add(
					new HashMap<String, String>() {
						{
							put("dataSourceFieldName", pair.getValue());
							put("dataSourceName", pair.getKey());
						}
					});
			}

			bqFieldMappingDTO.setDataSources(dataSources);
		}
	}

	@NotNull
	private BQFieldMappingDTO _toFieldMappingDTO(
		BQFieldMapping bqFieldMapping) {

		BQFieldMappingDTO bqFieldMappingDTO = new BQFieldMappingDTO(
			bqFieldMapping);

		List<DataSource> dataSources = _dataSourceDog.getDataSources(
			new ArrayList<>(bqFieldMapping.getDataSourceIds()));

		Map<String, String> dataSourceFieldNames = new HashMap<>();

		for (DataSource dataSource : dataSources) {
			dataSourceFieldNames.put(
				String.valueOf(dataSource.getId()),
				bqFieldMapping.getFieldName());
		}

		bqFieldMappingDTO.setDataSourceFieldNames(dataSourceFieldNames);

		return bqFieldMappingDTO;
	}

	private PageDTO<BQFieldMappingDTO> _toPageDTO(
		BQFieldMappingDTO bqFieldMappingDTO,
		Page<BQFieldMapping> fieldMappingsPage) {

		return new PageDTO<>(
			"_embedded", bqFieldMappingDTO, fieldMappingsPage.getNumber(),
			fieldMappingsPage.getSize(), fieldMappingsPage.getTotalElements(),
			fieldMappingsPage.getTotalPages());
	}

	@Autowired
	private BQFieldMappingDog _bqFieldMappingDog;

	@Autowired
	private DataSourceDog _dataSourceDog;

}