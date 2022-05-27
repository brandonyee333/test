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

import com.liferay.osb.asah.backend.dto.FieldMappingDTO;
import com.liferay.osb.asah.backend.dto.PageDTO;
import com.liferay.osb.asah.backend.dto.TransformationDTO;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.FieldMappingDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.DataSourceFieldMapping;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@DeleteMapping("/{id}")
	public void deleteFieldMapping(@PathVariable Long id) {
		FieldMapping fieldMapping = _fieldMappingDog.fetchFieldMapping(id);

		if (fieldMapping == null) {
			return;
		}

		_fieldMappingDog.deleteFieldMapping(fieldMapping);

		_addReprocessAsahTask(fieldMapping);
	}

	@GetMapping("/{id}")
	public FieldMappingDTO getFieldMappingDTO(@PathVariable Long id) {
		return new FieldMappingDTO(_fieldMappingDog.getFieldMapping(id));
	}

	@GetMapping(params = "!apply")
	public PageDTO<FieldMappingDTO> getFieldMappingDTOPageDTO(
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size,
		@RequestParam(name = "sort", required = false) String[] sorts) {

		return _toPageDTO(
			_fieldMappingDog.searchFieldMappingPage(
				filterString, page, Math.max(1, size), sorts));
	}

	@GetMapping(params = "apply")
	public PageDTO<TransformationDTO> getTransformationDTOPageDTO(
		@RequestParam String apply,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size) {

		return _toTransformationDTOPageDTO(
			"field-mapping-transformations",
			_fieldMappingDog.getTransformationPage(
				apply, filterString, page, size));
	}

	@PatchMapping("/{id}")
	public String patchFieldMapping(
		@PathVariable Long id, @RequestBody Map<String, Object> map) {

		FieldMapping fieldMapping = _fieldMappingDog.getFieldMapping(id);

		String dataSourceFieldName = String.valueOf(map.get("fieldName"));
		Long dataSourceId = Long.valueOf(
			String.valueOf(map.get("dataSourceId")));
		List<Long> deletedFieldMappingIds = new ArrayList<>();

		if ((dataSourceFieldName == null) ||
			StringUtils.isEmpty(dataSourceFieldName)) {

			fieldMapping = _fieldMappingDog.removeDataSourceFieldName(
				dataSourceId, fieldMapping);

			Set<DataSourceFieldMapping> dataSourceFieldMappings =
				fieldMapping.getDataSourceFieldMappings();

			if (dataSourceFieldMappings.isEmpty() &&
				_fieldMappingDog.deleteFieldMapping(fieldMapping)) {

				deletedFieldMappingIds.add(id);
			}
		}
		else {
			fieldMapping.addDataSourceFieldMapping(
				new DataSourceFieldMapping(dataSourceId, dataSourceFieldName));
		}

		if (deletedFieldMappingIds.isEmpty()) {
			fieldMapping = _fieldMappingDog.updateFieldMapping(
				fieldMapping.getId(), fieldMapping);
		}

		_segmentDog.disableDynamicSegments(null, deletedFieldMappingIds);

		_addReprocessAsahTask(dataSourceId, fieldMapping.getOwnerType());

		return null;
	}

	@PatchMapping
	public String patchFieldMappings(
		@RequestParam String context, @RequestParam Long dataSourceId,
		@RequestParam String ownerType, @RequestBody Map<String, String> map) {

		JSONArray responseJSONArray = new JSONArray();

		List<Long> deletedFieldMappingIds = new ArrayList<>();

		for (Map.Entry<String, String> entry : map.entrySet()) {
			String fieldName = entry.getKey();

			FieldMapping fieldMapping = _fieldMappingDog.fetchFieldMapping(
				context, fieldName, ownerType);

			if (fieldMapping == null) {
				_log.error(
					"Unable to get field mapping with context \"" + context +
						"\", field name \"" + fieldName +
							"\", and owner type \"" + ownerType + "\"");

				continue;
			}

			String dataSourceFieldName = entry.getValue();
			boolean fieldMappingDeleted = false;
			Long fieldMappingId = fieldMapping.getId();

			if (StringUtils.isEmpty(dataSourceFieldName)) {
				fieldMapping = _fieldMappingDog.removeDataSourceFieldName(
					dataSourceId, fieldMapping);

				Set<DataSourceFieldMapping> dataSourceFieldMappings =
					fieldMapping.getDataSourceFieldMappings();

				if (dataSourceFieldMappings.isEmpty() &&
					_fieldMappingDog.deleteFieldMapping(fieldMapping)) {

					deletedFieldMappingIds.add(fieldMappingId);

					fieldMappingDeleted = true;
				}
			}
			else {
				fieldMapping.addDataSourceFieldMapping(
					new DataSourceFieldMapping(
						dataSourceId, dataSourceFieldName));

				_fieldMappingDog.updateFieldMapping(
					fieldMapping.getId(), fieldMapping);
			}

			if (!fieldMappingDeleted) {
				responseJSONArray.put(
					_objectMapper.convertValue(fieldMapping, JSONObject.class));
			}
			else {
				responseJSONArray.put(JSONObject.NULL);
			}
		}

		_segmentDog.disableDynamicSegments(null, deletedFieldMappingIds);

		_addReprocessAsahTask(dataSourceId, ownerType);

		return responseJSONArray.toString();
	}

	@PostMapping
	public String postFieldMapping(
		@RequestBody FieldMappingDTO fieldMappingDTO) {

		String fieldName = fieldMappingDTO.getFieldName();

		FieldMapping fieldMapping = _fieldMappingDog.fetchFieldMapping(
			fieldMappingDTO.getContext(), fieldName,
			fieldMappingDTO.getOwnerType());

		if (fieldMapping != null) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST, "Duplicate field name " + fieldName);
		}

		_beforeAdd(fieldMappingDTO);

		fieldMapping = _fieldMappingDog.addFieldMapping(
			_objectMapper.convertValue(fieldMappingDTO, FieldMapping.class));

		_addReprocessAsahTask(fieldMapping);

		JSONObject fieldMappingJSONObject = _objectMapper.convertValue(
			fieldMapping, JSONObject.class);

		return fieldMappingJSONObject.toString();
	}

	@PutMapping("/{id}")
	public String putFieldMapping(
		@PathVariable String id, @RequestBody FieldMappingDTO fieldMappingDTO) {

		fieldMappingDTO.setId(id);

		_beforeUpdate(fieldMappingDTO);

		FieldMapping fieldMapping = _fieldMappingDog.updateFieldMapping(
			Long.valueOf(id),
			_objectMapper.convertValue(fieldMappingDTO, FieldMapping.class));

		_addReprocessAsahTask(fieldMapping);

		JSONObject fieldMappingJSONObject = _objectMapper.convertValue(
			fieldMapping, JSONObject.class);

		return fieldMappingJSONObject.toString();
	}

	private void _addReprocessAsahTask(FieldMapping fieldMapping) {
		Map<String, String> dataSourceFieldNames =
			fieldMapping.getDataSourceFieldNames();

		for (String dataSourceId : dataSourceFieldNames.keySet()) {
			_addReprocessAsahTask(
				Long.valueOf(dataSourceId), fieldMapping.getOwnerType());
		}
	}

	private void _addReprocessAsahTask(Long dataSourceId, String ownerType) {
		DataSource dataSource = _dataSourceDog.getDataSource(dataSourceId);

		String naniteClassName = _naniteClassNames.get(
			dataSource.getProviderType() + "#" + ownerType);

		if (naniteClassName == null) {
			if (_log.isInfoEnabled()) {
				StringBuilder sb = new StringBuilder();

				sb.append("Skip adding reprocess task for data source ");
				sb.append(dataSourceId);
				sb.append(" because reprocess operations are not supported ");
				sb.append("for data source type ");
				sb.append(dataSource.getProviderType());
				sb.append(" and owner type ");
				sb.append(ownerType);

				_log.info(sb.toString());
			}

			return;
		}

		_asahTaskDog.scheduleAsahTask(
			naniteClassName,
			JSONUtil.put(
				"dataSourceId", String.valueOf(dataSourceId)
			).put(
				"type", "reprocess"
			));
	}

	private void _beforeAdd(FieldMappingDTO fieldMappingDTO) {
		Date date = DateUtil.newDate();

		fieldMappingDTO.setCreateDate(date);

		fieldMappingDTO.setId(null);

		fieldMappingDTO.setModifiedDate(date);
	}

	private void _beforeUpdate(FieldMappingDTO fieldMappingDTO) {
		fieldMappingDTO.setModifiedDate(new Date());
	}

	private PageDTO<FieldMappingDTO> _toPageDTO(
		Page<FieldMapping> fieldMappingsPage) {

		return new PageDTO<>(
			"_embedded", new FieldMappingDTO(fieldMappingsPage.getContent()),
			fieldMappingsPage.getNumber(), fieldMappingsPage.getSize(),
			fieldMappingsPage.getTotalElements(),
			fieldMappingsPage.getTotalPages());
	}

	private PageDTO<TransformationDTO> _toTransformationDTOPageDTO(
		String transformationKey, Page<Transformation> transformationsPage) {

		return _toTransformationDTOPageDTO(
			new TransformationDTO(
				transformationKey, transformationsPage.getContent()),
			transformationsPage);
	}

	private PageDTO<TransformationDTO> _toTransformationDTOPageDTO(
		TransformationDTO transformationDTO,
		Page<Transformation> transformationsPage) {

		return new PageDTO<>(
			"_embedded", transformationDTO, transformationsPage.getNumber(),
			transformationsPage.getSize(),
			transformationsPage.getTotalElements(),
			transformationsPage.getTotalPages());
	}

	private static final Log _log = LogFactory.getLog(
		FieldMappingsRestController.class);

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private FieldMappingDog _fieldMappingDog;

	private final Map<String, String> _naniteClassNames =
		Collections.singletonMap("CSV#individual", "CSVIndividualsNanite");

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentDog _segmentDog;

}