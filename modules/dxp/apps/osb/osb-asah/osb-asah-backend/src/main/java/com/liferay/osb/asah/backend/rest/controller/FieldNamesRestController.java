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

import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.util.ArrayUtil;
import com.liferay.osb.asah.common.util.ListUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author David Bhasme
 * @author Shinn Lok
 */
@RequestMapping("/field-names")
@RestController
public class FieldNamesRestController extends BaseRestController {

	@GetMapping
	public String getFieldNames(
		@RequestParam(required = false) String label,
		@RequestParam(required = false) String ownerType,
		@RequestParam(required = false) String[] values) {

		Set<String> fieldNames = new TreeSet<>();

		if (StringUtils.isNotEmpty(label)) {
			long count = _fieldMappingRepository.countByFieldNameAndOwnerType(
				label, ownerType);

			if (count > 0) {
				fieldNames.add(label);
			}

			for (Long dataSourceId :
					ListUtil.map(
						_dataSourceDog.getDataSources(), DataSource::getId)) {

				List<FieldMapping> fieldMappings =
					_fieldMappingRepository.
						findByDataSourceFieldNameAndDataSourceIdAndOwnerType(
							label, dataSourceId, ownerType);

				for (FieldMapping fieldMapping : fieldMappings) {
					fieldNames.add(fieldMapping.getFieldName());
				}
			}
		}

		if (ArrayUtil.isNotEmpty(values)) {
			List<Field> fields =
				_fieldRepository.findByFieldTypeAndOwnerTypeAndValueIn(
					"Text", ownerType, Arrays.asList(values));

			for (Field field : fields) {
				fieldNames.add(field.getName());
			}
		}

		JSONArray fieldNamesJSONArray = new JSONArray(fieldNames);

		return fieldNamesJSONArray.toString();
	}

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private FieldMappingRepository _fieldMappingRepository;

	@Autowired
	private FieldRepository _fieldRepository;

}