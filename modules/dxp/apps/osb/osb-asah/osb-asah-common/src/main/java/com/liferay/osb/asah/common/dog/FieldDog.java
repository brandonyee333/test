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

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.util.SortUtil;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.util.BeanUtils;
import com.liferay.osb.asah.common.util.ListUtil;

import java.math.BigDecimal;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONArray;
import org.json.JSONObject;

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

		return Collections.emptyList();
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
			findByContextAndOwnerIdInGroupByMaxModifiedDateAndNameAndOwnerId(
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

	public Page<Transformation> getTransformationPage(
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

	public Page<Field> searchFieldPage(
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
			List<Long> ownerIds, String ownerType)
		throws Exception {

		Long dataSourceId = dataSource.getId();

		Map<String, List<Field>> multiValueFieldsMap = new HashMap<>();

		List<Field> existingFields =
			_fieldRepository.
				findByContextAndDataSourceIdAndOwnerIdInAndOwnerType(
					context, dataSourceId, ownerIds, ownerType);

		Stream<Field> stream = existingFields.stream();

		Map<Long, List<Field>> existingFieldsMap = stream.collect(
			Collectors.groupingBy(Field::getOwnerId));

		List<Field> updatedFields = new ArrayList<>();

		Map<Long, List<Field>> newFieldsMap = _buildFields(
			context, dataJSONObject, dataSource, ownerIds, ownerType);

		for (Map.Entry<Long, List<Field>> entry : newFieldsMap.entrySet()) {
			Long ownerId = entry.getKey();

			List<Field> curNewFields = newFieldsMap.get(ownerId);

			for (Field curNewField : curNewFields) {
				String fieldName = curNewField.getName();

				List<Field> oldFields = existingFieldsMap.getOrDefault(
					ownerId, new ArrayList<>());

				if (_isMultiValueField(
						context, dataSourceId, fieldName, ownerType)) {

					List<Field> multiValueFields =
						multiValueFieldsMap.computeIfAbsent(
							fieldName, key -> new ArrayList<>());

					multiValueFields.add(curNewField);
				}
				else if (oldFields.isEmpty()) {
					if (!Objects.isNull(curNewField.getValue())) {
						updatedFields.add(curNewField);
					}
				}
				else {
					Stream<Field> oldFieldsStream = oldFields.stream();

					Map<String, List<Field>> oldFieldNames =
						oldFieldsStream.collect(
							Collectors.groupingBy(Field::getName));

					List<Field> curOldFields = oldFieldNames.getOrDefault(
						fieldName, new ArrayList<>());

					if (curOldFields.isEmpty()) {
						if (!Objects.isNull(curNewField.getValue())) {
							updatedFields.add(curNewField);
						}

						continue;
					}

					Field curOldField = curOldFields.get(0);

					if (_isUpdateField(
							dataSourceId, curNewField, curOldField)) {

						BeanUtils.copyProperties(curNewField, curOldField);

						updatedFields.add(curOldField);
					}
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
			multiValueFieldsMap.keySet(), newFieldsMap, existingFieldsMap,
			ownerIds);
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

	private Map<Long, List<Field>> _buildFields(
			String context, JSONObject dataJSONObject, DataSource dataSource,
			List<Long> ownerIds, String ownerType)
		throws Exception {

		return Collections.emptyMap();
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

	private Map<String, List<Field>> _getFieldNames(
		Set<String> multiValueFieldNames, Map<Long, List<Field>> newFields,
		Map<Long, List<Field>> oldFields) {

		Map<String, List<Field>> newFieldNamesMap = new HashMap<>();

		for (Map.Entry<Long, List<Field>> entry : newFields.entrySet()) {
			List<Field> fields = entry.getValue();

			for (Field field : fields) {
				List<Field> curFields = new ArrayList<>();

				if (newFieldNamesMap.containsKey(field.getName())) {
					curFields = newFieldNamesMap.get(field.getName());
				}

				curFields.add(field);

				newFieldNamesMap.put(field.getName(), curFields);
			}
		}

		Map<String, List<Field>> oldFieldNamesMap = new HashMap<>();

		for (Map.Entry<Long, List<Field>> entry : oldFields.entrySet()) {
			for (Field field : entry.getValue()) {
				String oldFieldName = field.getName();

				if (multiValueFieldNames.contains(oldFieldName)) {
					continue;
				}

				List<Field> curFields = new ArrayList<>();

				if (oldFieldNamesMap.containsKey(oldFieldName)) {
					curFields.addAll(oldFieldNamesMap.get(oldFieldName));
				}

				if (newFieldNamesMap.containsKey(oldFieldName)) {
					curFields.addAll(newFieldNamesMap.get(oldFieldName));
				}

				List<Field> curNewFieldNames = newFieldNamesMap.getOrDefault(
					oldFieldName, new ArrayList<>());

				if (curNewFieldNames.isEmpty() ||
					(curNewFieldNames.size() < curFields.size())) {

					curFields.add(field);

					oldFieldNamesMap.put(oldFieldName, curFields);
				}
			}
		}

		return oldFieldNamesMap;
	}

	private String _getModifiedDateString(
			JSONObject dataJSONObject, DataSource dataSource)
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

		throw new Exception(
			"Invalid data source provider type: " + providerType);
	}

	private boolean _isMultiValueField(
		String context, Long dataSourceId, String fieldName, String ownerType) {

		// TODO Check if fieldName is multi value

		return false;
	}

	private boolean _isUpdateField(
		Long dataSourceId, Field newField, Field oldField) {

		if (Objects.equals(dataSourceId, oldField.getDataSourceId())) {
			return true;
		}

		if (newField.getValue() == null) {
			return false;
		}

		Date newModifiedDate = newField.getModifiedDate();

		Date oldModifiedDate = oldField.getModifiedDate();

		if (newModifiedDate.compareTo(oldModifiedDate) > 0) {
			return true;
		}

		return false;
	}

	private void _replaceOrDeleteOldFields(
		Set<String> multiValueFieldNames, Map<Long, List<Field>> newFields,
		Map<Long, List<Field>> oldFields, List<Long> ownerIds) {

		if (oldFields.isEmpty()) {
			return;
		}

		Map<String, List<Field>> fieldNames = _getFieldNames(
			multiValueFieldNames, newFields, oldFields);

		List<Field> deleteFields = new ArrayList<>();
		List<Field> updateFields = new ArrayList<>();

		for (Long ownerId : ownerIds) {
			List<Field> curNewFields = newFields.getOrDefault(
				ownerId, Collections.emptyList());
			List<Field> curOldFields = oldFields.getOrDefault(
				ownerId, Collections.emptyList());

			if (curNewFields.isEmpty()) {
				deleteFields.addAll(curOldFields);
			}
			else if (curOldFields.isEmpty()) {
				updateFields.addAll(curNewFields);
			}
			else {
				Stream<Field> curNewFieldsStream = curNewFields.stream();

				Map<String, Field> curNewFieldsMap = curNewFieldsStream.collect(
					Collectors.toMap(
						Field::getName, Function.identity(),
						(field1, field2) -> field1));

				Stream<Field> curOldFieldsStream = curOldFields.stream();

				Map<String, Field> curOldFieldsMap = curOldFieldsStream.collect(
					Collectors.toMap(
						Field::getName, Function.identity(),
						(field1, field2) -> field1));

				for (String fieldName : fieldNames.keySet()) {
					Field newField = curNewFieldsMap.getOrDefault(
						fieldName, null);
					Field oldField = curOldFieldsMap.getOrDefault(
						fieldName, null);

					if (newField == null) {
						deleteFields.add(oldField);
					}
					else if (oldField == null) {
						updateFields.add(newField);
					}
					else {
						Long oldFieldId = oldField.getId();

						BeanUtils.copyProperties(newField, oldField);

						oldField.setId(oldFieldId);

						updateFields.add(oldField);
					}
				}
			}
		}

		_fieldRepository.saveAll(updateFields);

		_fieldRepository.deleteAll(deleteFields);
	}

	private static final Log _log = LogFactory.getLog(FieldDog.class);

	@Autowired
	private FieldRepository _fieldRepository;

	private final Parser _parser = new Parser();

}