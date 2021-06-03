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
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.util.BeanUtils;
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

	public List<Field> addFields(
			String context, JSONObject dataJSONObject, DataSource dataSource,
			Long ownerId, String ownerType)
		throws Exception {

		List<Field> fields = _buildFields(
			context, dataJSONObject, dataSource, ownerId, ownerType);

		return IterableUtils.toList(_fieldRepository.saveAll(fields));
	}

	public JSONObject addOwnerJSONObject(
		String collectionName, JSONObject ownerJSONObject, String... contexts) {

		ownerJSONObject = _elasticsearchInvoker.add(
			collectionName, ownerJSONObject);

		String ownerId = ownerJSONObject.getString("id");

		for (String context : contexts) {
			JSONObject contextJSONObject = ownerJSONObject.optJSONObject(
				context);

			if (contextJSONObject == null) {
				continue;
			}

			_updateContextFields(
				collectionName, context, contextJSONObject, ownerId);

			_addFields(contextJSONObject);
		}

		return ownerJSONObject;
	}

	public JSONObject buildContextJSONObject(
			String context, JSONObject dataJSONObject, DataSource dataSource,
			String ownerType)
		throws Exception {

		JSONArray fieldsJSONArray = _buildFieldsJSONArray(
			context, dataJSONObject, dataSource, null, ownerType);

		return _buildContextJSONObject(fieldsJSONArray);
	}

	public void deleteField(Long fieldId) {
		_fieldRepository.deleteById(fieldId);
	}

	public void deleteFields(Long dataSourceId) {
		_fieldRepository.deleteByDataSourceId(dataSourceId);
	}

	public Field getField(Long fieldId) {
		Optional<Field> fieldOptional = _fieldRepository.findById(fieldId);

		return fieldOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"There is no field with ID " + fieldId));
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
				apply, filterString, pageRequest);

		return PageableExecutionUtils.getPage(
			transformations, pageRequest, transformations::size);
	}

	public Page<Field> searchFieldsPage(
		@Nullable String filterString, int page, int size,
		@Nullable String[] sorts) {

		PageRequest pageRequest = PageRequest.of(
			page, size, SortUtil.getSort(sorts));

		return PageableExecutionUtils.getPage(
			_fieldRepository.searchFields(filterString, pageRequest),
			pageRequest, () -> _fieldRepository.countFields(filterString));
	}

	public JSONObject updateContextFields(
			String context, JSONObject dataJSONObject, DataSource dataSource,
			JSONObject ownerJSONObject, String ownerType,
			String uniqueIdContext, String uniqueIdFieldName)
		throws Exception {

		Long dataSourceId = dataSource.getId();
		Long ownerId = ownerJSONObject.getLong("id");
		Map<String, List<JSONObject>> multiValueFieldsMap = new HashMap<>();

		JSONArray newFieldsJSONArray = _buildFieldsJSONArray(
			context, dataJSONObject, dataSource, ownerId, ownerType);

		Iterator<Object> iterator = newFieldsJSONArray.iterator();

		while (iterator.hasNext()) {
			JSONObject newFieldJSONObject = (JSONObject)iterator.next();

			String fieldName = newFieldJSONObject.getString("name");

			JSONArray oldFieldsJSONArray = _getFieldsJSONArray(
				context, fieldName, ownerId, ownerType);

			if (_isMultiValueField(
					context, dataSourceId, fieldName, ownerType)) {

				List<JSONObject> multiValueFieldsJSONObjects =
					multiValueFieldsMap.computeIfAbsent(
						fieldName, key -> new ArrayList<>());

				multiValueFieldsJSONObjects.add(newFieldJSONObject);
			}
			else if (oldFieldsJSONArray.length() == 0) {
				_fieldRepository.save(
					_objectMapper.convertValue(
						newFieldJSONObject, Field.class));
			}
			else {
				JSONObject oldFieldJSONObject =
					oldFieldsJSONArray.getJSONObject(0);

				if (_isUpdateField(
						context, dataSourceId, newFieldJSONObject,
						oldFieldJSONObject, ownerType)) {

					Field newField = _objectMapper.convertValue(
						newFieldJSONObject, Field.class);
					Field oldField = _objectMapper.convertValue(
						oldFieldJSONObject, Field.class);

					String newDataSourceId = String.valueOf(dataSourceId);
					String oldDataSourceId = String.valueOf(
						oldFieldJSONObject.get("dataSourceId"));

					if (oldDataSourceId.equals(newDataSourceId)) {
						BeanUtils.copyProperties(newField, oldField);

						updateField(oldField.getId(), oldField);
					}
					else {
						_fieldRepository.save(newField);
					}
				}
				else {
					iterator.remove();
				}
			}
		}

		_updateMultiValueJSONObjectFields(
			context, dataSourceId, multiValueFieldsMap, ownerId, ownerType);

		JSONObject contextJSONObject = _mergeContextJSONObject(
			context, dataSourceId, newFieldsJSONArray, ownerId, ownerType);

		_replaceOrDeleteOldFields(
			context, multiValueFieldsMap.keySet(), contextJSONObject,
			ownerJSONObject.optJSONObject(context), ownerId, ownerType,
			uniqueIdContext, uniqueIdFieldName);

		return ownerJSONObject.put(
			context, contextJSONObject
		).put(
			"dateModified", DateUtil.newDateString()
		);
	}

	public Field updateField(Long fieldId, Field field) {
		field.setId(fieldId);

		return _fieldRepository.save(field);
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
			context, null, ownerId, ownerType);

		Map<String, List<Field>> existingFieldsMap = new HashMap<>();

		for (Field existingField : existingFields) {
			List<Field> fields = new ArrayList<>();

			if (existingFieldsMap.containsKey(existingField.getName())) {
				fields = existingFieldsMap.get(existingField.getName());
			}

			fields.add(existingField);

			existingFieldsMap.put(existingField.getName(), fields);
		}

		List<Field> newFields = _buildFields(
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
				_fieldRepository.save(newField);
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

	private void _addFields(JSONObject contextJSONObject) {
		List<Field> fields = new ArrayList<>();

		for (String fieldName : contextJSONObject.keySet()) {
			JSONArray nameFieldsJSONArray = contextJSONObject.getJSONArray(
				fieldName);

			for (int i = 0; i < nameFieldsJSONArray.length(); i++) {
				JSONObject fieldJSONObject = nameFieldsJSONArray.getJSONObject(
					i);

				fields.add(
					_objectMapper.convertValue(fieldJSONObject, Field.class));
			}
		}

		_fieldRepository.saveAll(fields);
	}

	private JSONObject _buildContextJSONObject(JSONArray fieldsJSONArray) {
		JSONObject contextJSONObject = new JSONObject();

		for (int i = 0; i < fieldsJSONArray.length(); i++) {
			JSONObject fieldJSONObject = fieldsJSONArray.getJSONObject(i);

			String name = fieldJSONObject.getString("name");

			JSONArray fieldNameFieldsJSONArray = contextJSONObject.optJSONArray(
				name);

			if (fieldNameFieldsJSONArray == null) {
				fieldNameFieldsJSONArray = new JSONArray();

				contextJSONObject.put(name, fieldNameFieldsJSONArray);
			}

			fieldNameFieldsJSONArray.put(fieldJSONObject);
		}

		return contextJSONObject;
	}

	private Field _buildField(
			String context, Long dataSourceId, String dataSourceName,
			String fieldType, String modifiedDateString, String name,
			Long ownerId, String ownerType, String sourceName, Object value)
		throws Exception {

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
		field.setValue(String.valueOf(value));

		return field;
	}

	private JSONObject _buildFieldJSONObject(
		String context, String dataSourceId, String dataSourceName,
		String fieldType, String modifiedDateString, String name,
		String ownerId, String ownerType, String sourceName, Object value) {

		return JSONUtil.put(
			"context", context
		).put(
			"dataSourceId", dataSourceId
		).put(
			"dataSourceName", dataSourceName
		).put(
			"dateModified", modifiedDateString
		).put(
			"fieldType", fieldType
		).put(
			"name", name
		).put(
			"ownerId", ownerId
		).put(
			"ownerType", ownerType
		).put(
			"sourceName", sourceName
		).put(
			"value", value
		);
	}

	private List<Field> _buildFields(
			String context, JSONObject dataJSONObject, DataSource dataSource,
			Long ownerId, String ownerType)
		throws Exception {

		List<Field> fields = new ArrayList<>();

		JSONObject fieldsJSONObject = getFieldsJSONObject(
			context, dataJSONObject, dataSource);

		Long dataSourceId = dataSource.getId();

		List<FieldMapping> fieldMappings = _getFieldMappings(
			context, dataSourceId, ownerType);

		for (FieldMapping fieldMapping : fieldMappings) {
			Map<String, String> dataSourceFieldNames =
				_fieldMappingDog.getDataSourceFieldNames(fieldMapping);

			String dataSourceFieldName = dataSourceFieldNames.getOrDefault(
				String.valueOf(dataSourceId), null);

			Object value = fieldsJSONObject.opt(dataSourceFieldName);

			if (value == null) {
				continue;
			}

			String fieldName = fieldMapping.getFieldName();
			String fieldType = fieldMapping.getFieldType();

			value = _deserializeValue(
				fieldMapping.getDisplayType(), fieldName, fieldType,
				value.toString());

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
			}
		}

		return fields;
	}

	private JSONArray _buildFieldsJSONArray(
			String context, JSONObject dataJSONObject, DataSource dataSource,
			Long ownerId, String ownerType)
		throws Exception {

		JSONArray fieldsJSONArray = new JSONArray();

		JSONObject fieldsJSONObject = getFieldsJSONObject(
			context, dataJSONObject, dataSource);

		Long dataSourceId = dataSource.getId();

		List<FieldMapping> fieldMappings = _getFieldMappings(
			context, dataSourceId, ownerType);

		for (FieldMapping fieldMapping : fieldMappings) {
			Map<String, String> dataSourceFieldNames =
				_fieldMappingDog.getDataSourceFieldNames(fieldMapping);

			String dataSourceFieldName = dataSourceFieldNames.getOrDefault(
				String.valueOf(dataSourceId), null);

			Object value = fieldsJSONObject.opt(dataSourceFieldName);

			if (value == null) {
				continue;
			}

			String fieldName = fieldMapping.getFieldName();
			String fieldType = fieldMapping.getFieldType();

			value = _deserializeValue(
				fieldMapping.getDisplayType(), fieldName, fieldType,
				value.toString());

			String modifiedDateString = _getModifiedDateString(
				dataJSONObject, dataSource, ownerType);

			if (value instanceof List) {
				for (Object currentValue : (List<?>)value) {
					JSONObject fieldJSONObject = _buildFieldJSONObject(
						context, String.valueOf(dataSourceId),
						dataSource.getName(), fieldType, modifiedDateString,
						fieldName, String.valueOf(ownerId), ownerType,
						dataSourceFieldName, currentValue);

					fieldsJSONArray.put(fieldJSONObject);
				}
			}
			else {
				JSONObject fieldJSONObject = _buildFieldJSONObject(
					context, String.valueOf(dataSourceId), dataSource.getName(),
					fieldType, modifiedDateString, fieldName,
					String.valueOf(ownerId), ownerType, dataSourceFieldName,
					value);

				fieldsJSONArray.put(fieldJSONObject);
			}
		}

		return fieldsJSONArray;
	}

	private Object _deserializeValue(
		String displayType, String fieldName, String fieldType,
		String valueString) {

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
						null, fieldName, fieldType, value.toString())
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
			_log.error(
				"Unable to deserialize value " + valueString + " from field " +
					fieldName + " to " + fieldType,
				exception);
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
			return _dxpRawElasticsearchInvoker.fetch(
				"users",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"osbAsahDataSourceId",
						String.valueOf(dataSource.getId()))
				).filter(
					QueryBuilders.termQuery(
						"contact." + uniqueIdFieldName, uniqueId)
				));
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

	private List<Field> _getFields(
		String context, String name, Long ownerId, String ownerType) {

		if (name == null) {
			return _fieldRepository.findByContextAndOwnerIdAndOwnerType(
				context, ownerId, ownerType);
		}

		return _fieldRepository.findByContextAndNameAndOwnerIdAndOwnerType(
			context, name, ownerId, ownerType);
	}

	private JSONArray _getFieldsJSONArray(
		String context, String name, Long ownerId, String ownerType) {

		JSONArray fieldsJSONArray = new JSONArray();

		List<Field> fields =
			_fieldRepository.findByContextAndNameAndOwnerIdAndOwnerType(
				context, name, ownerId, ownerType);

		for (Field field : fields) {
			fieldsJSONArray.put(
				_objectMapper.convertValue(field, JSONObject.class));
		}

		return fieldsJSONArray;
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
			uniqueIdContext, uniqueIdFieldName, ownerId, ownerType);

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
				fieldMapping.getDisplayType(), fieldName, fieldType,
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

	private JSONArray _getNewFieldsJSONArray(
			String context, String fieldName, Long ownerId, String ownerType,
			String uniqueIdContext, String uniqueIdFieldName)
		throws Exception {

		if (uniqueIdFieldName == null) {
			return new JSONArray();
		}

		FieldMapping uniqueIdFieldMapping = _fieldMappingDog.fetchFieldMapping(
			uniqueIdContext, uniqueIdFieldName, ownerType);

		if (uniqueIdFieldMapping == null) {
			return new JSONArray();
		}

		FieldMapping fieldMapping = _fieldMappingDog.fetchFieldMapping(
			context, fieldName, ownerType);

		if (fieldMapping == null) {
			return new JSONArray();
		}

		JSONArray uniqueIdFieldsJSONArray = _getFieldsJSONArray(
			uniqueIdContext, uniqueIdFieldName, ownerId, ownerType);

		if (uniqueIdFieldsJSONArray.length() == 0) {
			return new JSONArray();
		}

		JSONArray newFieldsJSONArray = new JSONArray();

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

			JSONObject uniqueIdFieldJSONObject =
				uniqueIdFieldsJSONArray.getJSONObject(0);

			JSONObject dataJSONObject = _fetchDataJSONObject(
				dataSource, ownerType,
				uniqueIdFieldJSONObject.getString("value"),
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
				fieldMapping.getDisplayType(), fieldName, fieldType,
				value.toString());

			String modifiedDateString = _getModifiedDateString(
				dataJSONObject, dataSource, ownerType);

			if (value instanceof List) {
				for (Object currentValue : (List<?>)value) {
					JSONObject fieldJSONObject = _buildFieldJSONObject(
						context, dataSourceId, dataSource.getName(), fieldType,
						modifiedDateString, fieldMapping.getFieldName(),
						String.valueOf(ownerId), ownerType, dataSourceFieldName,
						currentValue);

					newFieldsJSONArray.put(fieldJSONObject);
				}
			}
			else {
				JSONObject fieldJSONObject = _buildFieldJSONObject(
					context, dataSourceId, dataSource.getName(), fieldType,
					modifiedDateString, fieldMapping.getFieldName(),
					String.valueOf(ownerId), ownerType, dataSourceFieldName,
					value);

				if ((newFieldsJSONArray.length() == 0) ||
					_isUpdateField(
						context, Long.valueOf(dataSourceId), fieldJSONObject,
						newFieldsJSONArray.getJSONObject(0), ownerType)) {

					newFieldsJSONArray.put(0, fieldJSONObject);
				}
			}
		}

		return newFieldsJSONArray;
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

		String newDataSourceId = String.valueOf(dataSourceId);
		String oldDataSourceId = String.valueOf(oldField.getDataSourceId());

		if (oldDataSourceId.equals(newDataSourceId)) {
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

		String newModifiedDateString = DateUtil.toUTCString(
			newField.getModifiedDate());
		String oldModifiedDateString = DateUtil.toUTCString(
			oldField.getModifiedDate());

		if (newModifiedDateString.compareTo(oldModifiedDateString) > 0) {
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

	private JSONObject _mergeContextJSONObject(
		String context, Long dataSourceId, JSONArray newFieldsJSONArray,
		Long ownerId, String ownerType) {

		List<String> newSingleFieldNames = new ArrayList<>();

		for (int i = 0; i < newFieldsJSONArray.length(); i++) {
			JSONObject newFieldJSONObject = newFieldsJSONArray.getJSONObject(i);

			String newFieldName = newFieldJSONObject.getString("name");

			newSingleFieldNames.add(newFieldName);
		}

		List<Field> oldFields =
			_fieldRepository.
				findByContextAndDataSourceIdNotAndNameNotInAndOwnerIdAndOwnerType(
					context, dataSourceId, newSingleFieldNames, ownerId,
					ownerType);

		for (Field oldField : oldFields) {
			JSONObject oldFieldJSONObject = _objectMapper.convertValue(
				oldField, JSONObject.class);

			oldFieldJSONObject.remove("id");

			newFieldsJSONArray.put(oldFieldJSONObject);
		}

		return _buildContextJSONObject(newFieldsJSONArray);
	}

	private void _replaceOrDeleteOldFields(
			String context, Set<String> multiValueFieldNames,
			JSONObject newContextJSONObject, JSONObject oldContextJSONObject,
			Long ownerId, String ownerType, String uniqueIdContext,
			String uniqueIdFieldName)
		throws Exception {

		if (oldContextJSONObject == null) {
			return;
		}

		List<String> oldFieldNames = new ArrayList<>();

		for (String fieldName : oldContextJSONObject.keySet()) {
			if (multiValueFieldNames.contains(fieldName)) {
				continue;
			}

			JSONArray newFieldsJSONArray = newContextJSONObject.optJSONArray(
				fieldName);

			if (newFieldsJSONArray == null) {
				oldFieldNames.add(fieldName);

				continue;
			}

			JSONArray oldFieldsJSONArray = oldContextJSONObject.getJSONArray(
				fieldName);

			if (newFieldsJSONArray.length() < oldFieldsJSONArray.length()) {
				oldFieldNames.add(fieldName);
			}
		}

		for (String oldFieldName : oldFieldNames) {
			JSONArray newFieldsJSONArray = _getNewFieldsJSONArray(
				context, oldFieldName, ownerId, ownerType, uniqueIdContext,
				uniqueIdFieldName);
			JSONArray oldFieldsJSONArray = _getFieldsJSONArray(
				context, oldFieldName, ownerId, ownerType);

			int length = Math.max(
				newFieldsJSONArray.length(), oldFieldsJSONArray.length());

			for (int i = 0; i < length; i++) {
				JSONObject newFieldJSONObject =
					newFieldsJSONArray.optJSONObject(i);
				JSONObject oldFieldJSONObject =
					oldFieldsJSONArray.optJSONObject(i);

				if (newFieldJSONObject == null) {
					_fieldRepository.deleteById(
						oldFieldJSONObject.getLong("id"));
				}
				else if (oldFieldJSONObject == null) {
					_fieldRepository.save(
						_objectMapper.convertValue(
							newFieldJSONObject, Field.class));
				}
				else {
					Field newField = _objectMapper.convertValue(
						newFieldJSONObject, Field.class);
					Field oldField = _objectMapper.convertValue(
						oldFieldJSONObject, Field.class);

					BeanUtils.copyProperties(newField, oldField);

					updateField(oldField.getId(), oldField);
				}
			}

			if (newFieldsJSONArray.length() > 0) {
				newContextJSONObject.put(oldFieldName, newFieldsJSONArray);
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

		Map<String, List<Field>> newFieldNamesMap = new HashMap<>();

		for (Field field : newFields) {
			List<Field> fields = new ArrayList<>();

			if (newFieldNamesMap.containsKey(field.getName())) {
				fields = newFieldNamesMap.get(field.getName());
			}

			fields.add(field);

			newFieldNamesMap.put(field.getName(), fields);
		}

		Map<String, List<Field>> oldFieldNames = new HashMap<>();

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

			if (curNewFieldNames.size() >= fields.size()) {
				continue;
			}

			fields.add(oldField);

			oldFieldNames.put(oldFieldName, fields);
		}

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
				Field curNewField = curNewFields.get(0);
				Field curOldField = curOldFields.get(0);

				BeanUtils.copyProperties(curNewField, curOldField);

				updateField(curOldField.getId(), curOldField);
			}
		}
	}

	private JSONObject _updateContextFields(
		String collectionName, String context, JSONObject contextJSONObject,
		String ownerId) {

		for (String fieldName : contextJSONObject.keySet()) {
			JSONArray fieldsJSONArray = contextJSONObject.getJSONArray(
				fieldName);

			for (int i = 0; i < fieldsJSONArray.length(); i++) {
				JSONObject fieldJSONObject = fieldsJSONArray.getJSONObject(i);

				fieldJSONObject.put("ownerId", ownerId);
			}
		}

		_elasticsearchInvoker.update(
			collectionName, ownerId, JSONUtil.put(context, contextJSONObject));

		return contextJSONObject;
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

	private void _updateMultiValueJSONObjectFields(
		String context, Long dataSourceId,
		Map<String, List<JSONObject>> multiValueFieldsMap, Long ownerId,
		String ownerType) {

		for (Map.Entry<String, List<JSONObject>> entry :
				multiValueFieldsMap.entrySet()) {

			_fieldRepository.deleteAll(
				_fieldRepository.
					findByContextAndDataSourceIdAndNameAndOwnerIdAndOwnerType(
						context, dataSourceId, entry.getKey(), ownerId,
						ownerType));

			List<Field> newFields = new ArrayList<>();

			for (JSONObject newFieldJSONObject : entry.getValue()) {
				Field newField = _objectMapper.convertValue(
					newFieldJSONObject, Field.class);

				newFields.add(newField);
			}

			_fieldRepository.saveAll(newFields);
		}
	}

	private static final Log _log = LogFactory.getLog(FieldDog.class);

	@Autowired
	private CSVIndividualDog _csvIndividualDog;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

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