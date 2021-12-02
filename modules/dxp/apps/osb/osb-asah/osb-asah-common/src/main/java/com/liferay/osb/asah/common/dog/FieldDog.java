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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.util.SortUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.util.BeanUtils;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.math.BigDecimal;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Persistable;
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
public class FieldDog {

	public static Map<String, String> toMap(Set<Field> fields) {
		Map<String, String> map = new HashMap<>();

		for (Field field : fields) {
			Object value = field.getValue();

			if (value == null) {
				continue;
			}

			map.put(field.getName(), String.valueOf(value));
		}

		return map;
	}

	public List<Field> addFields(
			String context, JSONObject dataJSONObject, DataSource dataSource,
			Long ownerId, String ownerType)
		throws Exception {

		List<Field> fields = buildFields(
			context, dataJSONObject, dataSource, ownerId, ownerType);

		return IterableUtils.toList(_fieldRepository.saveAll(fields));
	}

	public List<Field> buildFields(
			String context, JSONObject dataJSONObject, DataSource dataSource,
			Long ownerId, String ownerType)
		throws Exception {

		List<Field> fields = new ArrayList<>();

		JSONObject fieldsJSONObject = getFieldsJSONObject(
			context, dataJSONObject, dataSource);

		Long dataSourceId = dataSource.getId();

		List<FieldMapping> fieldMappings = _getFieldMappings(
			context, dataSourceId, ownerType);

		Stream<FieldMapping> stream = fieldMappings.stream();

		Map<String, List<FieldMapping>> fieldMappingsMap = stream.collect(
			Collectors.groupingBy(
				fieldMapping -> _getDataSourceFieldName(
					dataSourceId, fieldMapping)));

		for (Map.Entry<String, List<FieldMapping>> entry :
				fieldMappingsMap.entrySet()) {

			String dataSourceFieldName = entry.getKey();

			List<FieldMapping> fieldMappingsList = entry.getValue();

			Iterator<FieldMapping> iterator = fieldMappingsList.iterator();

			while (iterator.hasNext()) {
				FieldMapping fieldMapping = iterator.next();

				Object initialValue = fieldsJSONObject.opt(dataSourceFieldName);

				if (initialValue == null) {
					continue;
				}

				String fieldName = fieldMapping.getFieldName();
				String fieldType = fieldMapping.getFieldType();

				Object value = _deserializeValue(
					fieldMapping.getDisplayType(), fieldName, fieldType,
					!iterator.hasNext(), initialValue.toString());

				if (value == null) {
					continue;
				}

				String modifiedDateString = _getModifiedDateString(
					dataJSONObject, dataSource, ownerType);

			if (value instanceof List) {
				for (Object currentValue : (List<?>)value) {
					Field field = _buildField(
						context, dataSourceId, dataSource.getName(), fieldType,
						modifiedDateString, fieldName, ownerId, ownerType,
						dataSourceFieldName, currentValue);

					fields.add(field);
				}
			}
			else {
				Field field = _buildField(
					context, dataSourceId, dataSource.getName(), fieldType,
					modifiedDateString, fieldName, ownerId, ownerType,
					dataSourceFieldName, value);

				fields.add(field);

				break;
			}
		}

		return fields;
	}

	public void deleteField(Long fieldId) {
		_fieldRepository.deleteById(fieldId);
	}

	public void deleteFields(Long dataSourceId) {
		_fieldRepository.deleteByDataSourceId(dataSourceId);
	}

	public void deleteFields(String context, Long ownerId) {
		_fieldRepository.deleteByContextAndOwnerId(context, ownerId);
	}

	public Field getField(Long fieldId) {
		Optional<Field> fieldOptional = _fieldRepository.findById(fieldId);

		return fieldOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"There is no field with ID " + fieldId));
	}

	public List<Field> getFields(String context, List<Long> ownerIds) {
		return _fieldRepository.
			findByContextAndOwnerIdInGroupByMaxModifiedDateAndName(
				context, ownerIds);
	}

	public JSONObject getFieldsJSONObject(
			String context, JSONObject dataJSONObject, DataSource dataSource)
		throws Exception {

		String providerType = dataSource.getProviderType();

		if (providerType.equals("CSV")) {
			return dataJSONObject;
		}

		if (providerType.equals("LIFERAY")) {
			if (context.equals("custom")) {
				JSONObject expandoJSONObject = dataJSONObject.optJSONObject(
					"expando");

				if (expandoJSONObject != null) {
					return expandoJSONObject;
				}

				return new JSONObject();
			}

			JSONObject identityJSONObject = dataJSONObject.optJSONObject(
				"identity");

			if (identityJSONObject != null) {
				return identityJSONObject;
			}

			JSONObject contactJSONObject = dataJSONObject.optJSONObject(
				"contact");

			if (contactJSONObject != null) {
				JSONArray contactsJSONArray = contactJSONObject.names();

				if (contactsJSONArray == null) {
					return contactJSONObject;
				}

				for (int i = 0; i < contactsJSONArray.length(); i++) {
					String key = contactsJSONArray.getString(i);

					if (dataJSONObject.has(key)) {
						contactJSONObject.put(key, dataJSONObject.get(key));
					}
				}

				return contactJSONObject;
			}

			return dataJSONObject;
		}

		if (providerType.equals("SALESFORCE")) {
			return dataJSONObject;
		}

		throw new Exception(
			"Invalid data source type " + providerType + " for data source " +
				dataSource.getId());
	}

	public List<Field> getOwnerIdFields(String context, Long ownerId) {
		return _fieldRepository.
			findByContextAndOwnerIdGroupByMaxModifiedDateAndName(
				context, ownerId);
	}

	public Page<Transformation> getTransformationsPage(
		String apply, @Nullable String filterString, int page, int size) {

		PageRequest pageRequest = PageRequest.of(
			page, size,
			SortUtil.getSort(
				Sort.by(Sort.Order.desc("totalElements")),
				new String[] {"totalElements", "desc", "terms", "asc"}));

		List<Transformation> transformations =
			_fieldRepository.getFieldTransformations(
				apply, new FilterHelper(filterString), pageRequest);

		return PageableExecutionUtils.getPage(
			transformations, pageRequest, transformations::size);
	}

	public boolean isKnownIndividual(Individual individual) {
		return _fieldRepository.existsByNameAndOwnerId(
			"email", individual.getId());
	}

	public Page<Field> searchFieldsPage(
		@Nullable String filterString, int page, int size,
		@Nullable String[] sorts) {

		FilterHelper filterHelper = new FilterHelper(filterString);

		PageRequest pageRequest = PageRequest.of(
			page, size, SortUtil.getSort(sorts));

		return PageableExecutionUtils.getPage(
			_fieldRepository.searchFields(filterHelper, pageRequest),
			pageRequest, () -> _fieldRepository.countFields(filterHelper));
	}

	public void updateDataSourceName(Long dataSourceId, String dataSourceName) {
		_fieldRepository.updateDataSourceNameByDataSourceId(
			dataSourceId, dataSourceName);
	}

	public Field updateField(Long fieldId, Field field) {
		field.setId(fieldId);

		return _fieldRepository.save(field);
	}

	public void updateFields(
			String context, JSONObject dataJSONObject, DataSource dataSource,
			List<Long> ownerIds, String ownerType, String uniqueIdContext,
			String uniqueIdFieldName)
		throws Exception {

		Long dataSourceId = dataSource.getId();

		Map<String, List<Field>> multiValueFieldsMap = new HashMap<>();

		List<Field> existingFields =
			_fieldRepository.
				findByContextAndDataSourceIdAndOwnerIdInAndOwnerType(
					context, dataSourceId, ownerIds, ownerType);

		Stream<Field> stream = existingFields.stream();

		Map<String, List<Field>> existingFieldsMap = stream.collect(
			Collectors.groupingBy(Field::getName));

		List<Field> updatedFields = new ArrayList<>();

		List<Field> newFields = _buildFields(
			context, dataJSONObject, dataSource, ownerIds, ownerType);

		Iterator<Field> iterator = newFields.iterator();

		while (iterator.hasNext()) {
			Field newField = iterator.next();

			String fieldName = newField.getName();

			List<Field> oldFields = existingFieldsMap.getOrDefault(
				fieldName, new ArrayList<>());

			if (_isMultiValueField(
					context, dataSourceId, fieldName, ownerType)) {

				List<Field> multiValueFields =
					multiValueFieldsMap.computeIfAbsent(
						fieldName, key -> new ArrayList<>());

				multiValueFields.add(newField);
			}
			else if (oldFields.isEmpty()) {
				if (!Objects.isNull(newField.getValue())) {
					updatedFields.add(newField);
				}
			}
			else {
				Field oldField = oldFields.get(0);

				if (_isUpdateField(
						context, dataSourceId, newField, oldField, ownerType)) {

					BeanUtils.copyProperties(newField, oldField);

					updatedFields.add(oldField);
				}
				else {
					iterator.remove();
				}
			}
		}

		_fieldRepository.saveAll(updatedFields);

		for (Map.Entry<String, List<Field>> entry :
				multiValueFieldsMap.entrySet()) {

			_fieldRepository.
				deleteByContextAndDataSourceIdAndNameAndOwnerIdInAndOwnerType(
					context, dataSourceId, entry.getKey(),
					ListUtil.map(entry.getValue(), Field::getOwnerId),
					ownerType);

			_fieldRepository.saveAll(entry.getValue());
		}

		_replaceOrDeleteOldFields(
			context, multiValueFieldsMap.keySet(), newFields, existingFields,
			ownerIds, ownerType, uniqueIdContext, uniqueIdFieldName);
	}

	public void updateFields(
			String context, JSONObject dataJSONObject, DataSource dataSource,
			Persistable<Long> owner, String ownerType, String uniqueIdContext,
			String uniqueIdFieldName)
		throws Exception {

		Long dataSourceId = dataSource.getId();
		Long ownerId = owner.getId();
		Map<String, List<Field>> multiValueFieldsMap = new HashMap<>();

		List<Field> existingFields = _getFields(
			context, dataSourceId, null, ownerId, ownerType);

		Stream<Field> stream = existingFields.stream();

		Map<String, List<Field>> existingFieldsMap = stream.collect(
			Collectors.groupingBy(Field::getName));

		List<Field> newFields = buildFields(
			context, dataJSONObject, dataSource, ownerId, ownerType);

		Iterator<Field> iterator = newFields.iterator();

		while (iterator.hasNext()) {
			Field newField = iterator.next();

			String fieldName = newField.getName();

			List<Field> oldFields = existingFieldsMap.getOrDefault(
				fieldName, new ArrayList<>());

			if (_isMultiValueField(
					context, dataSourceId, fieldName, ownerType)) {

				List<Field> multiValueFields =
					multiValueFieldsMap.computeIfAbsent(
						fieldName, key -> new ArrayList<>());

				multiValueFields.add(newField);
			}
			else if (oldFields.isEmpty()) {
				if (!Objects.isNull(newField.getValue())) {
					_fieldRepository.save(newField);
				}
			}
			else {
				Field oldField = oldFields.get(0);

				JSONObject newFieldJSONObject = _objectMapper.convertValue(
					newField, JSONObject.class);
				JSONObject oldFieldJSONObject = _objectMapper.convertValue(
					oldField, JSONObject.class);

				if (_isUpdateField(
						context, dataSourceId, newFieldJSONObject,
						oldFieldJSONObject, ownerType)) {

					BeanUtils.copyProperties(newField, oldField);

					updateField(oldField.getId(), oldField);
				}
				else {
					iterator.remove();
				}
			}
		}

		_updateMultiValueFields(
			context, dataSourceId, multiValueFieldsMap, ownerId, ownerType);

		_replaceOrDeleteOldFields(
			context, multiValueFieldsMap.keySet(), newFields, existingFields,
			ownerId, ownerType, uniqueIdContext, uniqueIdFieldName);
	}

	private Field _buildField(
		String context, Long dataSourceId, String dataSourceName,
		String fieldType, String modifiedDateString, String name, Long ownerId,
		String ownerType, String sourceName, Object value) {

		Field field = new Field();

		field.setContext(context);
		field.setDataSourceId(dataSourceId);
		field.setDataSourceName(dataSourceName);
		field.setFieldType(fieldType);
		field.setModifiedDate(DateUtil.toUTCDate(modifiedDateString));
		field.setName(name);
		field.setOwnerId(ownerId);
		field.setOwnerType(ownerType);
		field.setSourceName(sourceName);

		if (value != null) {
			field.setValue(String.valueOf(value));
		}

		return field;
	}

	private List<Field> _buildFields(
			String context, JSONObject dataJSONObject, DataSource dataSource,
			List<Long> ownerIds, String ownerType)
		throws Exception {

		List<Field> fields = new ArrayList<>();

		JSONObject fieldsJSONObject = getFieldsJSONObject(
			context, dataJSONObject, dataSource);

		Long dataSourceId = dataSource.getId();

		List<FieldMapping> fieldMappings = _getFieldMappings(
			context, dataSourceId, ownerType);

		for (FieldMapping fieldMapping : fieldMappings) {
			Map<String, String> dataSourceFieldNames =
				fieldMapping.getDataSourceFieldNames();

			String dataSourceFieldName = dataSourceFieldNames.getOrDefault(
				String.valueOf(dataSourceId), null);

			Object value = fieldsJSONObject.opt(dataSourceFieldName);

			if (value == null) {
				continue;
			}

			String fieldName = fieldMapping.getFieldName();
			String fieldType = fieldMapping.getFieldType();

			value = _deserializeValue(
				fieldMapping.getDisplayType(), fieldName, fieldType, true,
				value.toString());

			String modifiedDateString = _getModifiedDateString(
				dataJSONObject, dataSource, ownerType);

			if (value instanceof List) {
				for (Object currentValue : (List<?>)value) {
					for (Long ownerId : ownerIds) {
						Field field = _buildField(
							context, dataSourceId, dataSource.getName(),
							fieldType, modifiedDateString, fieldName, ownerId,
							ownerType, dataSourceFieldName, currentValue);

						fields.add(field);
					}
				}
			}
			else {
				for (Long ownerId : ownerIds) {
					Field field = _buildField(
						context, dataSourceId, dataSource.getName(), fieldType,
						modifiedDateString, fieldName, ownerId, ownerType,
						dataSourceFieldName, value);

					fields.add(field);
				}
			}
		}

		return fields;
	}

	private Object _deserializeValue(
		String displayType, String fieldName, String fieldType,
		boolean logException, String valueString) {

		if (StringUtils.isEmpty(valueString)) {
			return null;
		}

		try {
			if ((displayType != null) &&
				(displayType.equals("checkbox") ||
				 displayType.equals("radio") ||
				 displayType.equals("selection-list"))) {

				Stream<Object> stream = JSONUtil.toObjectStream(
					new JSONArray(valueString));

				return stream.map(
					value -> _deserializeValue(
						null, fieldName, fieldType, logException,
						value.toString())
				).collect(
					Collectors.toList()
				);
			}

			if (fieldType.equals("Boolean")) {
				return Boolean.valueOf(valueString);
			}

			if (fieldType.equals("Date")) {
				if (NumberUtils.isCreatable(valueString)) {
					if (Long.parseLong(valueString) < 0) {
						return DateUtil.toString(valueString);
					}

					return DateUtil.toUTCString(
						new Date(Long.parseLong(valueString)));
				}

				try {

					// TODO Catching an exception is not ideal

					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"EEE MMM dd HH:mm:ss Z yyyy");

					return DateUtil.toUTCString(
						simpleDateFormat.parse(valueString));
				}
				catch (Exception exception) {
					if (_log.isDebugEnabled()) {
						_log.debug(exception, exception);
					}
				}

				List<DateGroup> dateGroups = _parser.parse(valueString);

				DateGroup dateGroup = dateGroups.get(0);

				List<Date> dates = dateGroup.getDates();

				return DateUtil.toString(dates.get(0));
			}

			if (fieldType.equals("Number")) {
				return new BigDecimal(valueString);
			}

			return valueString;
		}
		catch (Exception exception) {
			if (logException) {
				_log.error(
					"Unable to deserialize value '" + valueString +
						"' from field " + fieldName + " to " + fieldType,
					exception);
			}
		}

		return null;
	}

	private JSONObject _fetchDataJSONObject(
		DataSource dataSource, String ownerType, String uniqueId,
		String uniqueIdFieldName) {

		String providerType = dataSource.getProviderType();

		if (providerType.equals("CSV")) {
			return _objectMapper.convertValue(
				_csvIndividualDog.fetchCSVIndividual(
					dataSource.getId(), uniqueIdFieldName, uniqueId),
				JSONObject.class);
		}

		if (providerType.equals("LIFERAY")) {
			DXPEntity dxpEntity = _dxpEntityDog.fetchByFieldsAndType(
				new HashMap<String, Object>() {
					{
						put("dataSourceId", String.valueOf(dataSource.getId()));
						put("fields.contact." + uniqueIdFieldName, uniqueId);
					}
				},
				DXPEntity.Type.USER);

			if (dxpEntity != null) {
				return _objectMapper.convertValue(dxpEntity, JSONObject.class);
			}

			return null;
		}

		if (providerType.equals("SALESFORCE")) {
			if (ownerType.equals("account")) {
				return _salesforceRawElasticsearchInvoker.fetch(
					"Account",
					QueryBuilders.termQuery(uniqueIdFieldName, uniqueId));
			}

			if (ownerType.equals("individual")) {
				return _salesforceRawElasticsearchInvoker.fetch(
					"individuals",
					QueryBuilders.termQuery(uniqueIdFieldName, uniqueId));
			}

			if (_log.isWarnEnabled()) {
				_log.warn("Invalid owner type: " + ownerType);
			}

			return null;
		}

		if (_log.isWarnEnabled()) {
			_log.warn(
				"Invalid data source type " + providerType +
					" for data source " + dataSource.getId());
		}

		return null;
	}

	private String _getDataSourceFieldName(
		Long dataSourceId, FieldMapping fieldMapping) {

		Map<String, String> dataSourceFieldNames =
			fieldMapping.getDataSourceFieldNames();

		return dataSourceFieldNames.get(String.valueOf(dataSourceId));
	}

	private List<FieldMapping> _getFieldMappings(
		String context, Long dataSourceId, String ownerType) {

		return _fieldMappingRepository.findByContextAndDataSourceIdAndOwnerType(
			context, dataSourceId, ownerType);
	}

	private List<FieldMapping> _getFieldMappings(
		String context, Long dataSourceId, String fieldName, String ownerType) {

		return _fieldMappingRepository.
			findByContextAndDataSourceIdAndFieldNameAndOwnerType(
				context, dataSourceId, fieldName, ownerType);
	}

	private Map<String, List<Field>> _getFieldNames(
		Set<String> multiValueFieldNames, List<Field> newFields,
		List<Field> oldFields) {

		Map<String, List<Field>> oldFieldNames = new HashMap<>();

		Map<String, List<Field>> newFieldNamesMap = new HashMap<>();

		for (Field field : newFields) {
			List<Field> fields = new ArrayList<>();

			if (newFieldNamesMap.containsKey(field.getName())) {
				fields = newFieldNamesMap.get(field.getName());
			}

			fields.add(field);

			newFieldNamesMap.put(field.getName(), fields);
		}

		for (Field oldField : oldFields) {
			String oldFieldName = oldField.getName();

			if (multiValueFieldNames.contains(oldFieldName)) {
				continue;
			}

			List<Field> fields = new ArrayList<>();

			if (newFieldNamesMap.containsKey(oldFieldName)) {
				fields = newFieldNamesMap.get(oldFieldName);
			}

			List<Field> curNewFieldNames = newFieldNamesMap.getOrDefault(
				oldFieldName, new ArrayList<>());

			if (curNewFieldNames.isEmpty() ||
				(curNewFieldNames.size() < fields.size())) {

				fields.add(oldField);

				oldFieldNames.put(oldFieldName, fields);
			}
		}

		return oldFieldNames;
	}

	private List<Field> _getFields(
		String context, Long dataSourceId, String name, Long ownerId,
		String ownerType) {

		if (name == null) {
			if (dataSourceId == null) {
				return _fieldRepository.findByContextAndOwnerIdAndOwnerType(
					context, ownerId, ownerType);
			}

			return _fieldRepository.
				findByContextAndDataSourceIdAndOwnerIdAndOwnerType(
					context, dataSourceId, ownerId, ownerType);
		}

		if (dataSourceId == null) {
			return _fieldRepository.findByContextAndNameAndOwnerIdAndOwnerType(
				context, name, ownerId, ownerType);
		}

		return _fieldRepository.
			findByContextAndDataSourceIdAndNameAndOwnerIdAndOwnerType(
				context, dataSourceId, name, ownerId, ownerType);
	}

	private String _getModifiedDateString(
			JSONObject dataJSONObject, DataSource dataSource, String ownerType)
		throws Exception {

		String providerType = dataSource.getProviderType();

		if (providerType.equals("CSV")) {
			return DateUtil.toUTCString(dataSource.getModifiedDate());
		}

		if (providerType.equals("LIFERAY")) {
			if (dataJSONObject.isNull("modifiedDate")) {
				return DateUtil.toUTCString(
					new Date(dataJSONObject.getLong("createDate")));
			}

			return DateUtil.toUTCString(
				new Date(dataJSONObject.getLong("modifiedDate")));
		}

		if (providerType.equals("SALESFORCE")) {
			if (ownerType.equals("account")) {
				return dataJSONObject.getString("LastModifiedDate");
			}

			if (ownerType.equals("individual")) {
				return dataJSONObject.getString("modifiedDate");
			}

			throw new Exception("Invalid owner type: " + ownerType);
		}

		throw new Exception(
			"Invalid data source provider type: " + providerType);
	}

	private List<Field> _getNewFields(
			String context, String fieldName, List<Long> ownerIds,
			String ownerType, String uniqueIdContext, String uniqueIdFieldName)
		throws Exception {

		if (uniqueIdFieldName == null) {
			return Collections.emptyList();
		}

		FieldMapping uniqueIdFieldMapping = _fieldMappingDog.fetchFieldMapping(
			uniqueIdContext, uniqueIdFieldName, ownerType);

		if (uniqueIdFieldMapping == null) {
			return Collections.emptyList();
		}

		FieldMapping fieldMapping = _fieldMappingDog.fetchFieldMapping(
			context, fieldName, ownerType);

		if (fieldMapping == null) {
			return Collections.emptyList();
		}

		List<Field> uniqueIdFields =
			_fieldRepository.findByContextAndNameAndOwnerIdInAndOwnerType(
				uniqueIdContext, uniqueIdFieldName, ownerIds, ownerType);

		if (uniqueIdFields.isEmpty()) {
			return Collections.emptyList();
		}

		List<Field> newFields = new ArrayList<>();

		Map<String, String> dataSourceFieldNames =
			fieldMapping.getDataSourceFieldNames();

		for (Map.Entry<String, String> entry :
				dataSourceFieldNames.entrySet()) {

			String dataSourceId = entry.getKey();

			Map<String, String> uniqueIdDataSourceFieldNames =
				uniqueIdFieldMapping.getDataSourceFieldNames();

			if (!uniqueIdDataSourceFieldNames.containsKey(dataSourceId)) {
				continue;
			}

			DataSource dataSource = _dataSourceDog.fetchDataSource(
				Long.valueOf(dataSourceId));

			if (dataSource == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to get data source " + dataSourceId);
				}

				continue;
			}

			Field uniqueIdField = uniqueIdFields.get(0);

			JSONObject dataJSONObject = _fetchDataJSONObject(
				dataSource, ownerType, String.valueOf(uniqueIdField.getValue()),
				uniqueIdDataSourceFieldNames.get(dataSourceId));

			if (dataJSONObject == null) {
				continue;
			}

			JSONObject fieldsJSONObject = getFieldsJSONObject(
				context, dataJSONObject, dataSource);

			String dataSourceFieldName = dataSourceFieldNames.get(dataSourceId);

			Object value = fieldsJSONObject.opt(dataSourceFieldName);

			if (value == null) {
				continue;
			}

			String fieldType = fieldMapping.getFieldType();

			value = _deserializeValue(
				fieldMapping.getDisplayType(), fieldName, fieldType, true,
				value.toString());

			String modifiedDateString = _getModifiedDateString(
				dataJSONObject, dataSource, ownerType);

			if (value instanceof List) {
				for (Object currentValue : (List<?>)value) {
					for (Long ownerId : ownerIds) {
						Field field = _buildField(
							context, dataSource.getId(), dataSource.getName(),
							fieldType, modifiedDateString,
							fieldMapping.getFieldName(), ownerId, ownerType,
							dataSourceFieldName, currentValue);

						newFields.add(field);
					}
				}
			}
			else {
				for (Long ownerId : ownerIds) {
					Field field = _buildField(
						context, dataSource.getId(), dataSource.getName(),
						fieldType, modifiedDateString,
						fieldMapping.getFieldName(), ownerId, ownerType,
						dataSourceFieldName, value);

					if (newFields.isEmpty() ||
						_isUpdateField(
							context, dataSource.getId(), field,
							newFields.get(0), ownerType)) {

						newFields.add(0, field);
					}
				}
			}
		}

		return newFields;
	}

	private List<Field> _getNewFields(
			String context, String fieldName, Long ownerId, String ownerType,
			String uniqueIdContext, String uniqueIdFieldName)
		throws Exception {

		if (uniqueIdFieldName == null) {
			return Collections.emptyList();
		}

		FieldMapping uniqueIdFieldMapping = _fieldMappingDog.fetchFieldMapping(
			uniqueIdContext, uniqueIdFieldName, ownerType);

		if (uniqueIdFieldMapping == null) {
			return Collections.emptyList();
		}

		FieldMapping fieldMapping = _fieldMappingDog.fetchFieldMapping(
			context, fieldName, ownerType);

		if (fieldMapping == null) {
			return Collections.emptyList();
		}

		List<Field> uniqueIdFields = _getFields(
			uniqueIdContext, null, uniqueIdFieldName, ownerId, ownerType);

		if (uniqueIdFields.isEmpty()) {
			return Collections.emptyList();
		}

		List<Field> newFields = new ArrayList<>();

		Map<String, String> dataSourceFieldNames =
			fieldMapping.getDataSourceFieldNames();

		for (Map.Entry<String, String> entry :
				dataSourceFieldNames.entrySet()) {

			String dataSourceId = entry.getKey();

			Map<String, String> uniqueIdDataSourceFieldNames =
				uniqueIdFieldMapping.getDataSourceFieldNames();

			if (!uniqueIdDataSourceFieldNames.containsKey(dataSourceId)) {
				continue;
			}

			DataSource dataSource = _dataSourceDog.fetchDataSource(
				Long.valueOf(dataSourceId));

			if (dataSource == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to get data source " + dataSourceId);
				}

				continue;
			}

			Field uniqueIdField = uniqueIdFields.get(0);

			JSONObject dataJSONObject = _fetchDataJSONObject(
				dataSource, ownerType, String.valueOf(uniqueIdField.getValue()),
				uniqueIdDataSourceFieldNames.get(dataSourceId));

			if (dataJSONObject == null) {
				continue;
			}

			JSONObject fieldsJSONObject = getFieldsJSONObject(
				context, dataJSONObject, dataSource);

			String dataSourceFieldName = dataSourceFieldNames.get(dataSourceId);

			Object value = fieldsJSONObject.opt(dataSourceFieldName);

			if (value == null) {
				continue;
			}

			String fieldType = fieldMapping.getFieldType();

			value = _deserializeValue(
				fieldMapping.getDisplayType(), fieldName, fieldType, true,
				value.toString());

			String modifiedDateString = _getModifiedDateString(
				dataJSONObject, dataSource, ownerType);

			if (value instanceof List) {
				for (Object currentValue : (List<?>)value) {
					Field field = _buildField(
						context, dataSource.getId(), dataSource.getName(),
						fieldType, modifiedDateString,
						fieldMapping.getFieldName(), ownerId, ownerType,
						dataSourceFieldName, currentValue);

					newFields.add(field);
				}
			}
			else {
				Field field = _buildField(
					context, dataSource.getId(), dataSource.getName(),
					fieldType, modifiedDateString, fieldMapping.getFieldName(),
					ownerId, ownerType, dataSourceFieldName, value);

				if (newFields.isEmpty() ||
					_isUpdateField(
						context, dataSource.getId(), field, newFields.get(0),
						ownerType)) {

					newFields.add(0, field);
				}
			}
		}

		return newFields;
	}

	private FieldMapping.Strategy _getStrategy(
		String context, Long dataSourceId, String fieldName, String ownerType) {

		List<FieldMapping> fieldMappings = _getFieldMappings(
			context, dataSourceId, fieldName, ownerType);

		FieldMapping fieldMapping = fieldMappings.get(0);

		FieldMapping.Strategy strategy = fieldMapping.getStrategy();

		if (strategy == null) {
			return FieldMapping.Strategy.DEFAULT;
		}

		return strategy;
	}

	private boolean _isMultiValueField(
		String context, Long dataSourceId, String fieldName, String ownerType) {

		List<FieldMapping> fieldMappings = _getFieldMappings(
			context, dataSourceId, fieldName, ownerType);

		FieldMapping fieldMapping = fieldMappings.get(0);

		if (Objects.equals(fieldMapping.getDisplayType(), "checkbox")) {
			return true;
		}

		return false;
	}

	private boolean _isUpdateField(
		String context, Long dataSourceId, Field newField, Field oldField,
		String ownerType) {

		if (Objects.equals(dataSourceId, oldField.getDataSourceId())) {
			return true;
		}

		if (newField.getValue() == null) {
			return false;
		}

		FieldMapping.Strategy fieldMappingStrategy = _getStrategy(
			context, dataSourceId, oldField.getName(), ownerType);

		if (Objects.equals(
				fieldMappingStrategy.getKey(), "PRIORITY_DATASOURCE")) {

			JSONObject configurationJSONObject =
				fieldMappingStrategy.getConfigurationJSONObject();

			Long configurationDataSourceId = configurationJSONObject.getLong(
				"dataSourceId");

			if (!configurationDataSourceId.equals(dataSourceId) &&
				configurationDataSourceId.equals(oldField.getDataSourceId())) {

				return false;
			}

			if (configurationDataSourceId.equals(dataSourceId) &&
				!configurationDataSourceId.equals(oldField.getDataSourceId())) {

				return true;
			}
		}

		Date newModifiedDate = newField.getModifiedDate();

		Date oldModifiedDate = oldField.getModifiedDate();

		if (newModifiedDate.compareTo(oldModifiedDate) > 0) {
			return true;
		}

		return false;
	}

	private boolean _isUpdateField(
		String context, Long dataSourceId, JSONObject newFieldJSONObject,
		JSONObject oldFieldJSONObject, String ownerType) {

		String newDataSourceId = String.valueOf(dataSourceId);
		String oldDataSourceId = String.valueOf(
			oldFieldJSONObject.get("dataSourceId"));

		if (oldDataSourceId.equals(newDataSourceId)) {
			return true;
		}

		if (newFieldJSONObject.opt("value") == null) {
			return false;
		}

		FieldMapping.Strategy fieldMappingStrategy = _getStrategy(
			context, dataSourceId, oldFieldJSONObject.getString("name"),
			ownerType);

		if (Objects.equals(
				fieldMappingStrategy.getKey(), "PRIORITY_DATASOURCE")) {

			JSONObject configurationJSONObject =
				fieldMappingStrategy.getConfigurationJSONObject();

			String configurationDataSourceId =
				configurationJSONObject.getString("dataSourceId");

			if (!configurationDataSourceId.equals(newDataSourceId) &&
				configurationDataSourceId.equals(oldDataSourceId)) {

				return false;
			}

			if (configurationDataSourceId.equals(newDataSourceId) &&
				!configurationDataSourceId.equals(oldDataSourceId)) {

				return true;
			}
		}

		String newModifiedDateString = newFieldJSONObject.getString(
			"dateModified");
		String oldModifiedDateString = oldFieldJSONObject.optString(
			"dateModified", oldFieldJSONObject.optString("modifiedDate"));

		if (newModifiedDateString.compareTo(oldModifiedDateString) > 0) {
			return true;
		}

		return false;
	}

	private void _replaceOrDeleteOldFields(
			String context, Set<String> multiValueFieldNames,
			List<Field> newFields, List<Field> oldFields, List<Long> ownerIds,
			String ownerType, String uniqueIdContext, String uniqueIdFieldName)
		throws Exception {

		if (oldFields.isEmpty()) {
			return;
		}

		Map<String, List<Field>> oldFieldNames = _getFieldNames(
			multiValueFieldNames, newFields, oldFields);

		for (Map.Entry<String, List<Field>> entry : oldFieldNames.entrySet()) {
			List<Field> curNewFields = _getNewFields(
				context, entry.getKey(), ownerIds, ownerType, uniqueIdContext,
				uniqueIdFieldName);
			List<Field> curOldFields = entry.getValue();

			if (curNewFields.isEmpty()) {
				_fieldRepository.deleteAll(curOldFields);
			}
			else if (curOldFields.isEmpty()) {
				_fieldRepository.saveAll(curNewFields);
			}
			else {
				Field curOldField = curOldFields.get(0);

				BeanUtils.copyProperties(curNewFields.get(0), curOldField);

				_fieldRepository.save(curOldField);
			}
		}
	}

	private void _replaceOrDeleteOldFields(
			String context, Set<String> multiValueFieldNames,
			List<Field> newFields, List<Field> oldFields, Long ownerId,
			String ownerType, String uniqueIdContext, String uniqueIdFieldName)
		throws Exception {

		if (oldFields.isEmpty()) {
			return;
		}

		Map<String, List<Field>> oldFieldNames = _getFieldNames(
			multiValueFieldNames, newFields, oldFields);

		for (Map.Entry<String, List<Field>> entry : oldFieldNames.entrySet()) {
			List<Field> curNewFields = _getNewFields(
				context, entry.getKey(), ownerId, ownerType, uniqueIdContext,
				uniqueIdFieldName);
			List<Field> curOldFields = entry.getValue();

			if (curNewFields.isEmpty()) {
				_fieldRepository.deleteAll(curOldFields);
			}
			else if (curOldFields.isEmpty()) {
				_fieldRepository.saveAll(curNewFields);
			}
			else {
				Field curOldField = curOldFields.get(0);

				BeanUtils.copyProperties(curNewFields.get(0), curOldField);

				_fieldRepository.save(curOldField);
			}
		}
	}

	private void _updateMultiValueFields(
		String context, Long dataSourceId,
		Map<String, List<Field>> multiValueFieldsMap, Long ownerId,
		String ownerType) {

		for (Map.Entry<String, List<Field>> entry :
				multiValueFieldsMap.entrySet()) {

			_fieldRepository.deleteAll(
				_fieldRepository.
					findByContextAndDataSourceIdAndNameAndOwnerIdAndOwnerType(
						context, dataSourceId, entry.getKey(), ownerId,
						ownerType));

			_fieldRepository.saveAll(entry.getValue());
		}
	}

	private static final Log _log = LogFactory.getLog(FieldDog.class);

	@Autowired
	private CSVIndividualDog _csvIndividualDog;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private DXPEntityDog _dxpEntityDog;

	@Autowired
	private FieldMappingDog _fieldMappingDog;

	@Autowired
	private FieldMappingRepository _fieldMappingRepository;

	@Autowired
	private FieldRepository _fieldRepository;

	@Autowired
	private ObjectMapper _objectMapper;

	private final Parser _parser = new Parser();

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

}