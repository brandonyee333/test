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

package com.liferay.osb.asah.common.dog;

import com.liferay.osb.asah.common.dog.util.SortUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.DataSourceFieldMapping;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.DataSourceFieldMappingRepository;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.util.BeanUtils;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 * @author Rachael Koestartyo
 */
@Component
public class FieldMappingDog {

	public void addEmailFieldMapping(Long dataSourceId) {
		String dataSourceFieldName = _getEmailDataSourceFieldName(dataSourceId);

		if (dataSourceFieldName == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to get data source field name for data source ID " +
						dataSourceId);
			}

			return;
		}

		addFieldMapping(
			"demographics", dataSourceFieldName, dataSourceId, null, "email",
			"http://schema.org/email", "individual");
	}

	public FieldMapping addFieldMapping(FieldMapping fieldMapping) {
		fieldMapping = _fieldMappingRepository.save(fieldMapping);

		return fetchFieldMapping(fieldMapping.getId());
	}

	public void addFieldMapping(
		String context, String dataSourceFieldName, Long dataSourceId,
		String displayType, String fieldName, String fieldType,
		String ownerType) {

		if (StringUtils.isBlank(context) || StringUtils.isBlank(fieldName) ||
			StringUtils.isBlank(fieldType) || StringUtils.isBlank(ownerType)) {

			return;
		}

		Optional<FieldMapping> fieldMappingOptional =
			_fieldMappingRepository.
				findByContextAndDisplayNameAndDisplayTypeAndFieldTypeAndOwnerType(
					context, fieldName, displayType, fieldType, ownerType);

		if (fieldMappingOptional.isPresent()) {
			FieldMapping fieldMapping = fieldMappingOptional.get();

			fieldMapping.addDataSourceFieldMapping(
				new DataSourceFieldMapping(
					dataSourceId, fieldMapping.getId(), dataSourceFieldName));
			fieldMapping.setModifiedDate(new Date());

			_fieldMappingRepository.save(fieldMapping);

			return;
		}

		fieldMappingOptional =
			_fieldMappingRepository.findByContextAndDisplayNameAndOwnerType(
				context, fieldName, ownerType);

		if (fieldMappingOptional.isPresent()) {
			FieldMapping fieldMapping = fieldMappingOptional.get();

			removeDataSourceFieldName(dataSourceId, fieldMapping.getId());
		}

		Date date = new Date();

		FieldMapping fieldMapping = new FieldMapping();

		fieldMapping.addDataSourceFieldMapping(
			new DataSourceFieldMapping(dataSourceId, dataSourceFieldName));

		fieldMapping.setAuthor(_getDataSourceAuthor(dataSourceId));
		fieldMapping.setContext(context);
		fieldMapping.setCreateDate(date);
		fieldMapping.setModifiedDate(date);
		fieldMapping.setDisplayName(fieldName);
		fieldMapping.setDisplayType(displayType);
		fieldMapping.setFieldName(
			_getSanitizedFieldName(context, fieldName, ownerType));
		fieldMapping.setFieldType(fieldType);
		fieldMapping.setOwnerType(ownerType);
		fieldMapping.setStrategy(FieldMapping.Strategy.DEFAULT);

		_fieldMappingRepository.save(fieldMapping);
	}

	public long countIndividualFieldMappings(String name) {
		return _fieldMappingRepository.countIndividualFieldMappings(name);
	}

	public boolean deleteFieldMapping(FieldMapping fieldMapping) {
		if (Objects.equals(fieldMapping.getAuthorId(), "FARO_SYSTEM") &&
			Objects.equals(fieldMapping.getAuthorName(), "FARO_SYSTEM")) {

			return false;
		}

		_fieldMappingRepository.delete(fieldMapping);

		return true;
	}

	public boolean existsById(Long fieldMappingId) {
		if (fieldMappingId == null) {
			return false;
		}

		return _fieldMappingRepository.existsById(fieldMappingId);
	}

	public FieldMapping fetchFieldMapping(Long fieldMappingId) {
		if (fieldMappingId == null) {
			return null;
		}

		Optional<FieldMapping> fieldMappingOptional =
			_fieldMappingRepository.findById(fieldMappingId);

		FieldMapping fieldMapping = fieldMappingOptional.orElse(null);

		_setDataSourceFieldNames(fieldMapping);

		return fieldMapping;
	}

	public FieldMapping fetchFieldMapping(
		String context, String fieldName, String ownerType) {

		Optional<FieldMapping> fieldMappingOptional =
			_fieldMappingRepository.findByContextAndFieldNameAndOwnerType(
				context, fieldName, ownerType);

		FieldMapping fieldMapping = fieldMappingOptional.orElse(null);

		_setDataSourceFieldNames(fieldMapping);

		return fieldMapping;
	}

	public List<Long> getDataSourceFieldMappingIds(
		Long dataSourceId, boolean previewDelete) {

		List<Long> dataSourceFieldMappingIds = new ArrayList<>();

		List<DataSourceFieldMapping> dataSourceFieldMappings =
			_dataSourceFieldMappingRepository.findByDataSourceId(dataSourceId);

		Map<Long, List<DataSourceFieldMapping>> dataSourceFieldMappingsMap =
			new HashMap<>();

		for (DataSourceFieldMapping dataSourceFieldMapping :
				dataSourceFieldMappings) {

			List<DataSourceFieldMapping> curDataSourceFieldMappings =
				new ArrayList<>();

			if (dataSourceFieldMappingsMap.containsKey(
					dataSourceFieldMapping.getFieldMappingId())) {

				curDataSourceFieldMappings = dataSourceFieldMappingsMap.get(
					dataSourceFieldMapping.getFieldMappingId());
			}

			curDataSourceFieldMappings.add(dataSourceFieldMapping);

			dataSourceFieldMappingsMap.put(
				dataSourceFieldMapping.getFieldMappingId(),
				curDataSourceFieldMappings);
		}

		for (Map.Entry<Long, List<DataSourceFieldMapping>> entry :
				dataSourceFieldMappingsMap.entrySet()) {

			List<DataSourceFieldMapping> curDataSourceFieldMappings =
				entry.getValue();

			if (previewDelete && (curDataSourceFieldMappings.size() > 1)) {
				continue;
			}

			dataSourceFieldMappingIds.add(entry.getKey());
		}

		return dataSourceFieldMappingIds;
	}

	public Map<String, String> getDataSourceFieldNames(
		FieldMapping fieldMapping) {

		if (fieldMapping == null) {
			return Collections.emptyMap();
		}

		Map<String, String> dataSourceFieldNames = new HashMap<>();

		List<DataSourceFieldMapping> dataSourceFieldMappings =
			_dataSourceFieldMappingRepository.findByFieldMappingId(
				fieldMapping.getId());

		for (DataSourceFieldMapping dataSourceFieldMapping :
				dataSourceFieldMappings) {

			dataSourceFieldNames.put(
				String.valueOf(dataSourceFieldMapping.getDataSourceId()),
				dataSourceFieldMapping.getFieldName());
		}

		return dataSourceFieldNames;
	}

	public FieldMapping getFieldMapping(Long fieldMappingId) {
		Optional<FieldMapping> fieldOptional = _fieldMappingRepository.findById(
			fieldMappingId);

		FieldMapping fieldMapping = fieldOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"There is no field with ID " + fieldMappingId));

		_setDataSourceFieldNames(fieldMapping);

		return fieldMapping;
	}

	public List<FieldMapping> getFieldMappings(Set<Long> fieldMappingIds) {
		return IterableUtils.toList(
			_fieldMappingRepository.findAllById(fieldMappingIds));
	}

	public Page<Transformation> getTransformationsPage(
		String apply, @Nullable String filterString, int page, int size) {

		PageRequest pageRequest = PageRequest.of(
			page, size,
			SortUtil.getSort(
				Sort.by(Sort.Order.desc("totalElements")),
				new String[] {"totalElements", "desc", "terms", "asc"}));

		List<Transformation> transformations =
			_fieldMappingRepository.getFieldMappingTransformations(
				apply, filterString, pageRequest);

		return PageableExecutionUtils.getPage(
			transformations, pageRequest, transformations::size);
	}

	public FieldMapping removeDataSourceFieldName(
		Long dataSourceId, Long fieldMappingId) {

		FieldMapping fieldMapping = fetchFieldMapping(fieldMappingId);

		if (fieldMapping != null) {
			Map<String, String> dataSourceFieldNames =
				fieldMapping.getDataSourceFieldNames();

			dataSourceFieldNames.remove(String.valueOf(dataSourceId));

			fieldMapping.setDataSourceFieldNames(dataSourceFieldNames);

			_fieldMappingRepository.save(fieldMapping);
		}

		return fetchFieldMapping(fieldMappingId);
	}

	public Page<FieldMapping> searchFieldMappingsPage(
		@Nullable String filterString, int page, int size,
		@Nullable String[] sorts) {

		PageRequest pageRequest = PageRequest.of(
			page, size,
			SortUtil.getSort(Sort.by(Sort.Order.asc("fieldName")), sorts));

		return PageableExecutionUtils.getPage(
			_fieldMappingRepository.searchFieldMappings(
				filterString, pageRequest),
			pageRequest,
			() -> _fieldMappingRepository.countFieldMappings(filterString));
	}

	public Page<FieldMapping> searchIndividualFieldMappingsPage(
		@Nullable String name, int page, int size, String[] sorts) {

		PageRequest pageRequest = PageRequest.of(
			page, size, SortUtil.getSort(sorts));

		return PageableExecutionUtils.getPage(
			_fieldMappingRepository.searchIndividualFieldMappings(
				name, pageRequest),
			pageRequest,
			() -> _fieldMappingRepository.countIndividualFieldMappings(name));
	}

	public FieldMapping updateFieldMapping(
		Long fieldMappingId, FieldMapping fieldMapping) {

		FieldMapping existingFieldMapping = fetchFieldMapping(fieldMappingId);

		BeanUtils.copyProperties(fieldMapping, existingFieldMapping);

		return _fieldMappingRepository.save(fieldMapping);
	}

	private FieldMapping.Author _getDataSourceAuthor(Long dataSourceId) {
		DataSource dataSource = _dataSourceDog.getDataSource(dataSourceId);

		FieldMapping.Author author = new FieldMapping.Author();

		author.setId(String.valueOf(dataSourceId));
		author.setName(dataSource.getName());

		return author;
	}

	private String _getEmailDataSourceFieldName(Long dataSourceId) {
		DataSource dataSource = _dataSourceDog.fetchDataSource(dataSourceId);

		if (dataSource == null) {
			return null;
		}

		String dataSourceFieldName = "email";

		String providerType = dataSource.getProviderType();

		if (providerType.equals("LIFERAY")) {
			dataSourceFieldName = "emailAddress";
		}
		else if (_log.isWarnEnabled()) {
			_log.warn(
				"Default to using \"email\" as the data source field name " +
					"because of unknown provider type " + providerType);
		}

		return dataSourceFieldName;
	}

	private String _getSanitizedFieldName(
		String context, String fieldName, String ownerType) {

		fieldName = fieldName.replaceAll("[\\W]", "_");

		int nameCount = 0;
		String originalName = fieldName;

		while (_hasFieldMapping(context, fieldName, ownerType)) {
			fieldName = String.format("%s_%d", originalName, ++nameCount);
		}

		return fieldName;
	}

	private boolean _hasFieldMapping(
		String context, String fieldName, String ownerType) {

		return _fieldMappingRepository.existsByContextAndFieldNameAndOwnerType(
			context, fieldName, ownerType);
	}

	private void _setDataSourceFieldNames(FieldMapping fieldMapping) {
		if (fieldMapping == null) {
			return;
		}

		fieldMapping.setDataSourceFieldNames(
			getDataSourceFieldNames(fieldMapping));
	}

	private static final Log _log = LogFactory.getLog(FieldMappingDog.class);

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private DataSourceFieldMappingRepository _dataSourceFieldMappingRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private FieldMappingRepository _fieldMappingRepository;

}