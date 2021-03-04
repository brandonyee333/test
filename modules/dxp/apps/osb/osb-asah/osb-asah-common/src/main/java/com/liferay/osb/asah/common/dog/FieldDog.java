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
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoFieldMappingDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.common.model.Field;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.math.BigDecimal;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 * @author Rachael Koestartyo
 */
@Component
public class FieldDog {

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

	public void deleteField(Long id) {
		_fieldRepository.deleteById(id);
	}

	public Field getField(Long id) {
		Optional<Field> fieldOptional = _fieldRepository.findById(id);

		return fieldOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST, "There is no field with ID " + id));
	}

	public JSONObject getFieldsJSONObject(
			String context, JSONObject dataJSONObject, DataSource dataSource)
		throws Exception {

		String providerType = dataSource.getProviderType();

		if (providerType.equals("CSV")) {
			return dataJSONObject.getJSONObject("fields");
		}
		else if (providerType.equals("LIFERAY")) {
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
		else if (providerType.equals("SALESFORCE")) {
			return dataJSONObject;
		}

		throw new Exception(
			"Invalid data source type " + providerType + " for data source " +
				dataSource.getId());
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
			else if (_isAllowMultiple(
						context, dataSourceId, fieldName, ownerType)) {

				Field oldField =
					_fieldRepository.
						findFirstByContextAndDataSourceIdAndNameAndOwnerIdAndOwnerType(
							context, dataSourceId, fieldName, ownerId,
							ownerType);

				if (oldField == null) {
					_fieldRepository.save(
						_objectMapper.convertValue(
							newFieldJSONObject, Field.class));
				}
				else {
					JSONObject oldFieldJSONObject = _objectMapper.convertValue(
						oldField, JSONObject.class);

					for (String key : JSONObject.getNames(newFieldJSONObject)) {
						oldFieldJSONObject.put(
							key, newFieldJSONObject.get(key));
					}

					oldField = _objectMapper.convertValue(
						oldFieldJSONObject, Field.class);

					_fieldRepository.save(oldField);
				}
			}
			else {
				JSONObject oldFieldJSONObject =
					oldFieldsJSONArray.getJSONObject(0);

				if (_isUpdateField(
						context, dataSourceId, newFieldJSONObject,
						oldFieldJSONObject, ownerType)) {

					for (String key : JSONObject.getNames(newFieldJSONObject)) {
						oldFieldJSONObject.put(
							key, newFieldJSONObject.get(key));
					}

					_fieldRepository.save(
						_objectMapper.convertValue(
							oldFieldJSONObject, Field.class));
				}
				else {
					iterator.remove();
				}
			}
		}

		_updateMultiValueFields(
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

	public Field updateField(Field field) {
		return _fieldRepository.save(field);
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

	private JSONArray _buildFieldsJSONArray(
			String context, JSONObject dataJSONObject, DataSource dataSource,
			Long ownerId, String ownerType)
		throws Exception {

		JSONArray fieldsJSONArray = new JSONArray();

		JSONObject fieldsJSONObject = getFieldsJSONObject(
			context, dataJSONObject, dataSource);

		Long dataSourceId = dataSource.getId();

		JSONArray fieldMappingsJSONArray = _getFieldMappingsJSONArray(
			context, String.valueOf(dataSourceId), ownerType);

		for (int i = 0; i < fieldMappingsJSONArray.length(); i++) {
			JSONObject fieldMappingJSONObject =
				fieldMappingsJSONArray.getJSONObject(i);

			JSONObject dataSourceFieldNamesJSONObject =
				fieldMappingJSONObject.getJSONObject("dataSourceFieldNames");

			String dataSourceFieldName =
				dataSourceFieldNamesJSONObject.optString(
					String.valueOf(dataSourceId), null);

			Object value = fieldsJSONObject.opt(dataSourceFieldName);

			if (value == null) {
				continue;
			}

			String fieldName = fieldMappingJSONObject.getString("fieldName");
			String fieldType = fieldMappingJSONObject.getString("fieldType");

			value = _deserializeValue(
				fieldMappingJSONObject.optString("displayType"), fieldName,
				fieldType, value.toString());

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
			else if (fieldType.equals("Boolean")) {
				return Boolean.valueOf(valueString);
			}
			else if (fieldType.equals("Date")) {
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
				catch (Exception e) {
					if (_log.isDebugEnabled()) {
						_log.debug(e, e);
					}
				}

				List<DateGroup> dateGroups = _parser.parse(valueString);

				DateGroup dateGroup = dateGroups.get(0);

				List<Date> dates = dateGroup.getDates();

				return DateUtil.toString(dates.get(0));
			}
			else if (fieldType.equals("Number")) {
				return new BigDecimal(valueString);
			}

			return valueString;
		}
		catch (Exception e) {
			_log.error(
				"Unable to deserialize value " + valueString + " from field " +
					fieldName + " to " + fieldType,
				e);
		}

		return null;
	}

	private JSONObject _fetchDataJSONObject(
		DataSource dataSource, String ownerType, String uniqueId,
		String uniqueIdFieldName) {

		String providerType = dataSource.getProviderType();

		if (providerType.equals("CSV")) {
			return _elasticsearchInvoker.fetch(
				"csv-individuals",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"dataSourceId", String.valueOf(dataSource.getId()))
				).filter(
					QueryBuilders.termQuery(
						"fields." + uniqueIdFieldName, uniqueId)
				));
		}
		else if (providerType.equals("LIFERAY")) {
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
		else if (providerType.equals("SALESFORCE")) {
			if (ownerType.equals("account")) {
				return _salesforceRawElasticsearchInvoker.fetch(
					"Account",
					QueryBuilders.termQuery(uniqueIdFieldName, uniqueId));
			}
			else if (ownerType.equals("individual")) {
				return _salesforceRawElasticsearchInvoker.fetch(
					"individuals",
					QueryBuilders.termQuery(uniqueIdFieldName, uniqueId));
			}
			else {
				if (_log.isWarnEnabled()) {
					_log.warn("Invalid owner type: " + ownerType);
				}

				return null;
			}
		}

		if (_log.isWarnEnabled()) {
			_log.warn(
				"Invalid data source type " + providerType +
					" for data source " + dataSource.getId());
		}

		return null;
	}

	private JSONArray _getFieldMappingsJSONArray(
		String context, String dataSourceId, String ownerType) {

		return _elasticsearchInvoker.get(
			"field-mappings",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("context", context)
			).filter(
				QueryBuilders.existsQuery(
					"dataSourceFieldNames." + dataSourceId)
			).filter(
				QueryBuilders.termQuery("ownerType", ownerType)
			));
	}

	private JSONArray _getFieldMappingsJSONArray(
		String context, String dataSourceId, String fieldName,
		String ownerType) {

		return _elasticsearchInvoker.get(
			"field-mappings",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("context", context)
			).filter(
				QueryBuilders.existsQuery(
					"dataSourceFieldNames." + dataSourceId)
			).filter(
				QueryBuilders.termQuery("fieldName", fieldName)
			).filter(
				QueryBuilders.termQuery("ownerType", ownerType)
			));
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
		else if (providerType.equals("LIFERAY")) {
			if (dataJSONObject.isNull("modifiedDate")) {
				return DateUtil.toUTCString(
					new Date(dataJSONObject.getLong("createDate")));
			}

			return DateUtil.toUTCString(
				new Date(dataJSONObject.getLong("modifiedDate")));
		}
		else if (providerType.equals("SALESFORCE")) {
			if (ownerType.equals("account")) {
				return dataJSONObject.getString("LastModifiedDate");
			}
			else if (ownerType.equals("individual")) {
				return dataJSONObject.getString("modifiedDate");
			}
			else {
				throw new Exception("Invalid owner type: " + ownerType);
			}
		}

		throw new Exception(
			"Invalid data source provider type: " + providerType);
	}

	private JSONArray _getNewFieldsJSONArray(
			String context, String fieldName, Long ownerId, String ownerType,
			String uniqueIdContext, String uniqueIdFieldName)
		throws Exception {

		if (uniqueIdFieldName == null) {
			return new JSONArray();
		}

		JSONObject uniqueIdFieldMappingJSONObject =
			_faroInfoFieldMappingDog.fetchFieldMappingJSONObject(
				uniqueIdContext, uniqueIdFieldName, ownerType);

		if (uniqueIdFieldMappingJSONObject == null) {
			return new JSONArray();
		}

		JSONObject fieldMappingJSONObject =
			_faroInfoFieldMappingDog.fetchFieldMappingJSONObject(
				context, fieldName, ownerType);

		if (fieldMappingJSONObject == null) {
			return new JSONArray();
		}

		JSONArray uniqueIdFieldsJSONArray = _getFieldsJSONArray(
			uniqueIdContext, uniqueIdFieldName, ownerId, ownerType);

		if (uniqueIdFieldsJSONArray.length() == 0) {
			return new JSONArray();
		}

		JSONArray newFieldsJSONArray = new JSONArray();

		JSONObject dataSourceFieldNamesJSONObject =
			fieldMappingJSONObject.getJSONObject("dataSourceFieldNames");

		for (String dataSourceId : dataSourceFieldNamesJSONObject.keySet()) {
			JSONObject uniqueIdDataSourceFieldNamesJSONObject =
				uniqueIdFieldMappingJSONObject.getJSONObject(
					"dataSourceFieldNames");

			if (!uniqueIdDataSourceFieldNamesJSONObject.has(dataSourceId)) {
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
				uniqueIdDataSourceFieldNamesJSONObject.getString(dataSourceId));

			if (dataJSONObject == null) {
				continue;
			}

			JSONObject fieldsJSONObject = getFieldsJSONObject(
				context, dataJSONObject, dataSource);

			String dataSourceFieldName =
				dataSourceFieldNamesJSONObject.getString(dataSourceId);

			Object value = fieldsJSONObject.opt(dataSourceFieldName);

			if (value == null) {
				continue;
			}

			String fieldType = fieldMappingJSONObject.getString("fieldType");

			value = _deserializeValue(
				fieldMappingJSONObject.optString("displayType"), fieldName,
				fieldType, value.toString());

			String modifiedDateString = _getModifiedDateString(
				dataJSONObject, dataSource, ownerType);

			if (value instanceof List) {
				for (Object currentValue : (List<?>)value) {
					JSONObject fieldJSONObject = _buildFieldJSONObject(
						context, dataSourceId, dataSource.getName(), fieldType,
						modifiedDateString,
						fieldMappingJSONObject.getString("fieldName"),
						String.valueOf(ownerId), ownerType, dataSourceFieldName,
						currentValue);

					newFieldsJSONArray.put(fieldJSONObject);
				}
			}
			else {
				JSONObject fieldJSONObject = _buildFieldJSONObject(
					context, dataSourceId, dataSource.getName(), fieldType,
					modifiedDateString,
					fieldMappingJSONObject.getString("fieldName"),
					String.valueOf(ownerId), ownerType, dataSourceFieldName,
					value);

				if (_isAllowMultiple(
						fieldMappingJSONObject.getJSONObject("strategy"))) {

					newFieldsJSONArray.put(fieldJSONObject);
				}
				else if ((newFieldsJSONArray.length() == 0) ||
						 _isUpdateField(
							 context, Long.valueOf(dataSourceId),
							 fieldJSONObject,
							 newFieldsJSONArray.getJSONObject(0), ownerType)) {

					newFieldsJSONArray.put(0, fieldJSONObject);
				}
			}
		}

		return newFieldsJSONArray;
	}

	private JSONObject _getStrategyJSONObject(
		String context, String dataSourceId, String fieldName,
		String ownerType) {

		JSONArray fieldMappingsJSONArray = _getFieldMappingsJSONArray(
			context, dataSourceId, fieldName, ownerType);

		JSONObject fieldMappingJSONObject =
			fieldMappingsJSONArray.getJSONObject(0);

		return fieldMappingJSONObject.getJSONObject("strategy");
	}

	private boolean _isAllowMultiple(JSONObject strategyJSONObject) {
		return Objects.equals(
			strategyJSONObject.getString("key"), "ALLOW_MULTIPLE");
	}

	private boolean _isAllowMultiple(
		String context, Long dataSourceId, String fieldName, String ownerType) {

		JSONObject strategyJSONObject = _getStrategyJSONObject(
			context, String.valueOf(dataSourceId), fieldName, ownerType);

		return _isAllowMultiple(strategyJSONObject);
	}

	private boolean _isMultiValueField(
		String context, Long dataSourceId, String fieldName, String ownerType) {

		JSONArray fieldMappingsJSONArray = _getFieldMappingsJSONArray(
			context, String.valueOf(dataSourceId), fieldName, ownerType);

		JSONObject fieldMappingJSONObject =
			fieldMappingsJSONArray.getJSONObject(0);

		if (Objects.equals(
				fieldMappingJSONObject.optString("displayType"), "checkbox")) {

			return true;
		}

		return false;
	}

	private boolean _isUpdateField(
		String context, Long dataSourceId, JSONObject newFieldJSONObject,
		JSONObject oldFieldJSONObject, String ownerType) {

		String oldDataSourceId = String.valueOf(
			oldFieldJSONObject.get("dataSourceId"));

		if (oldDataSourceId.equals(String.valueOf(dataSourceId))) {
			return true;
		}
		else if (newFieldJSONObject.opt("value") == null) {
			return false;
		}

		JSONObject fieldMappingStrategyJSONObject = _getStrategyJSONObject(
			context, String.valueOf(dataSourceId),
			oldFieldJSONObject.getString("name"), ownerType);

		if (Objects.equals(
				fieldMappingStrategyJSONObject.getString("key"),
				"PRIORITY_DATASOURCE")) {

			JSONObject configurationJSONObject =
				fieldMappingStrategyJSONObject.getJSONObject("configuration");

			String configurationDataSourceId =
				configurationJSONObject.getString("dataSourceId");

			if (!configurationDataSourceId.equals(dataSourceId) &&
				configurationDataSourceId.equals(oldDataSourceId)) {

				return false;
			}

			if (configurationDataSourceId.equals(dataSourceId) &&
				!configurationDataSourceId.equals(oldDataSourceId)) {

				return true;
			}
		}

		String newModifiedDateString = newFieldJSONObject.getString(
			"dateModified");
		String oldModifiedDateString = oldFieldJSONObject.getString(
			"dateModified");

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

			if (!_isAllowMultiple(
					context, dataSourceId, newFieldName, ownerType)) {

				newSingleFieldNames.add(newFieldName);
			}
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
					for (String key : JSONObject.getNames(newFieldJSONObject)) {
						oldFieldJSONObject.put(
							key, newFieldJSONObject.get(key));
					}

					_fieldRepository.save(
						_objectMapper.convertValue(
							oldFieldJSONObject, Field.class));
				}
			}

			if (newFieldsJSONArray.length() > 0) {
				newContextJSONObject.put(oldFieldName, newFieldsJSONArray);
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
	private DataSourceDog _dataSourceDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private FaroInfoFieldMappingDog _faroInfoFieldMappingDog;

	@Autowired
	private FieldRepository _fieldRepository;

	@Autowired
	private ObjectMapper _objectMapper;

	private final Parser _parser = new Parser();

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

}