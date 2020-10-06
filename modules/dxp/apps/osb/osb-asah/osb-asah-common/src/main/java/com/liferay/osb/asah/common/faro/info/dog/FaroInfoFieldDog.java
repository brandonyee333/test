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

package com.liferay.osb.asah.common.faro.info.dog;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
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
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class FaroInfoFieldDog extends BaseFaroInfoDog {

	public JSONObject addOwnerJSONObject(
		String collectionName, JSONObject ownerJSONObject, String... contexts) {

		ownerJSONObject = elasticsearchInvoker.add(
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
			String context, JSONObject dataJSONObject,
			JSONObject dataSourceJSONObject, String ownerType)
		throws Exception {

		JSONArray fieldsJSONArray = _buildFieldsJSONArray(
			context, dataJSONObject, dataSourceJSONObject, null, ownerType);

		return _buildContextJSONObject(fieldsJSONArray);
	}

	public JSONObject getFieldsJSONObject(
			String context, JSONObject dataJSONObject,
			JSONObject dataSourceJSONObject)
		throws Exception {

		String type = _faroInfoDataSourceDog.getDataSourceType(
			dataSourceJSONObject);

		if (type.equals("CSV")) {
			return dataJSONObject.getJSONObject("fields");
		}
		else if (type.equals("LIFERAY")) {
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
		else if (type.equals("SALESFORCE")) {
			return dataJSONObject;
		}

		throw new Exception(
			"Invalid data source type " + type + " for data source " +
				dataSourceJSONObject.getString("id"));
	}

	public JSONObject updateContextFields(
			String context, JSONObject dataJSONObject,
			JSONObject dataSourceJSONObject, JSONObject ownerJSONObject,
			String ownerType, String uniqueIdContext, String uniqueIdFieldName)
		throws Exception {

		String dataSourceId = dataSourceJSONObject.getString("id");
		String ownerId = ownerJSONObject.getString("id");
		Map<String, List<JSONObject>> multiValueFieldsMap = new HashMap<>();

		JSONArray newFieldsJSONArray = _buildFieldsJSONArray(
			context, dataJSONObject, dataSourceJSONObject, ownerId, ownerType);

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
				elasticsearchInvoker.add("fields", newFieldJSONObject);
			}
			else if (_isAllowMultiple(
						context, dataSourceId, fieldName, ownerType)) {

				JSONObject oldFieldJSONObject = elasticsearchInvoker.fetch(
					"fields",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery("context", context)
					).filter(
						QueryBuilders.termQuery("dataSourceId", dataSourceId)
					).filter(
						QueryBuilders.termQuery("name", fieldName)
					).filter(
						QueryBuilders.termQuery("ownerId", ownerId)
					).filter(
						QueryBuilders.termQuery("ownerType", ownerType)
					));

				if (oldFieldJSONObject == null) {
					elasticsearchInvoker.add("fields", newFieldJSONObject);
				}
				else {
					elasticsearchInvoker.update(
						"fields", oldFieldJSONObject.getString("id"),
						newFieldJSONObject);
				}
			}
			else {
				JSONObject oldFieldJSONObject =
					oldFieldsJSONArray.getJSONObject(0);

				if (_isUpdateField(
						context, dataSourceId, newFieldJSONObject,
						oldFieldJSONObject, ownerType)) {

					elasticsearchInvoker.update(
						"fields", oldFieldJSONObject.getString("id"),
						newFieldJSONObject);
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

	private void _addFields(JSONObject contextJSONObject) {
		JSONArray fieldsJSONArray = new JSONArray();

		for (String fieldName : contextJSONObject.keySet()) {
			JSONArray nameFieldsJSONArray = contextJSONObject.getJSONArray(
				fieldName);

			for (int i = 0; i < nameFieldsJSONArray.length(); i++) {
				fieldsJSONArray.put(nameFieldsJSONArray.getJSONObject(i));
			}
		}

		elasticsearchInvoker.add("fields", fieldsJSONArray);
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
			String context, JSONObject dataJSONObject,
			JSONObject dataSourceJSONObject, String ownerId, String ownerType)
		throws Exception {

		JSONArray fieldsJSONArray = new JSONArray();

		JSONObject fieldsJSONObject = getFieldsJSONObject(
			context, dataJSONObject, dataSourceJSONObject);

		String dataSourceId = dataSourceJSONObject.getString("id");

		JSONArray fieldMappingsJSONArray = _getFieldMappingsJSONArray(
			context, dataSourceId, ownerType);

		for (int i = 0; i < fieldMappingsJSONArray.length(); i++) {
			JSONObject fieldMappingJSONObject =
				fieldMappingsJSONArray.getJSONObject(i);

			JSONObject dataSourceFieldNamesJSONObject =
				fieldMappingJSONObject.getJSONObject("dataSourceFieldNames");

			String dataSourceFieldName =
				dataSourceFieldNamesJSONObject.optString(dataSourceId, null);

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
				dataJSONObject, dataSourceJSONObject, ownerType);

			if (value instanceof List) {
				for (Object currentValue : (List<?>)value) {
					JSONObject fieldJSONObject = _buildFieldJSONObject(
						context, dataSourceJSONObject.getString("id"),
						dataSourceJSONObject.getString("name"), fieldType,
						modifiedDateString, fieldName, ownerId, ownerType,
						dataSourceFieldName, currentValue);

					fieldsJSONArray.put(fieldJSONObject);
				}
			}
			else {
				JSONObject fieldJSONObject = _buildFieldJSONObject(
					context, dataSourceJSONObject.getString("id"),
					dataSourceJSONObject.getString("name"), fieldType,
					modifiedDateString, fieldName, ownerId, ownerType,
					dataSourceFieldName, value);

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
		JSONObject dataSourceJSONObject, String ownerType, String uniqueId,
		String uniqueIdFieldName) {

		String type = _faroInfoDataSourceDog.getDataSourceType(
			dataSourceJSONObject);

		if (type.equals("CSV")) {
			return elasticsearchInvoker.fetch(
				"csv-individuals",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"dataSourceId", dataSourceJSONObject.getString("id"))
				).filter(
					QueryBuilders.termQuery(
						"fields." + uniqueIdFieldName, uniqueId)
				));
		}
		else if (type.equals("LIFERAY")) {
			return _dxpRawElasticsearchInvoker.fetch(
				"users",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"osbAsahDataSourceId",
						dataSourceJSONObject.getString("id"))
				).filter(
					QueryBuilders.termQuery(
						"contact." + uniqueIdFieldName, uniqueId)
				));
		}
		else if (type.equals("SALESFORCE")) {
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
				"Invalid data source type " + type + " for data source " +
					dataSourceJSONObject.getString("id"));
		}

		return null;
	}

	private JSONArray _getFieldMappingsJSONArray(
		String context, String dataSourceId, String ownerType) {

		return elasticsearchInvoker.get(
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

		return elasticsearchInvoker.get(
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
		String context, String name, String ownerId, String ownerType) {

		return elasticsearchInvoker.get(
			"fields",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("context", context)
			).filter(
				QueryBuilders.termQuery("name", name)
			).filter(
				QueryBuilders.termQuery("ownerId", ownerId)
			).filter(
				QueryBuilders.termQuery("ownerType", ownerType)
			));
	}

	private String _getModifiedDateString(
			JSONObject dataJSONObject, JSONObject dataSourceJSONObject,
			String ownerType)
		throws Exception {

		String type = _faroInfoDataSourceDog.getDataSourceType(
			dataSourceJSONObject);

		if (type.equals("CSV")) {
			return dataSourceJSONObject.getString("dateModified");
		}
		else if (type.equals("LIFERAY")) {
			if (dataJSONObject.isNull("modifiedDate")) {
				return DateUtil.toUTCString(
					new Date(dataJSONObject.getLong("createDate")));
			}

			return DateUtil.toUTCString(
				new Date(dataJSONObject.getLong("modifiedDate")));
		}
		else if (type.equals("SALESFORCE")) {
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

		throw new Exception("Invalid data source provider type: " + type);
	}

	private JSONArray _getNewFieldsJSONArray(
			String context, String fieldName, String ownerId, String ownerType,
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

			JSONObject dataSourceJSONObject =
				_faroInfoDataSourceDog.getDataSourceJSONObject(dataSourceId);

			if (dataSourceJSONObject == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to get data source " + dataSourceId);
				}

				continue;
			}

			JSONObject uniqueIdFieldJSONObject =
				uniqueIdFieldsJSONArray.getJSONObject(0);

			JSONObject dataJSONObject = _fetchDataJSONObject(
				dataSourceJSONObject, ownerType,
				uniqueIdFieldJSONObject.getString("value"),
				uniqueIdDataSourceFieldNamesJSONObject.getString(dataSourceId));

			if (dataJSONObject == null) {
				continue;
			}

			JSONObject fieldsJSONObject = getFieldsJSONObject(
				context, dataJSONObject, dataSourceJSONObject);

			String dataSourceFieldName =
				dataSourceFieldNamesJSONObject.getString(
					dataSourceJSONObject.getString("id"));

			Object value = fieldsJSONObject.opt(dataSourceFieldName);

			if (value == null) {
				continue;
			}

			String fieldType = fieldMappingJSONObject.getString("fieldType");

			value = _deserializeValue(
				fieldMappingJSONObject.optString("displayType"), fieldName,
				fieldType, value.toString());

			String modifiedDateString = _getModifiedDateString(
				dataJSONObject, dataSourceJSONObject, ownerType);

			if (value instanceof List) {
				for (Object currentValue : (List<?>)value) {
					JSONObject fieldJSONObject = _buildFieldJSONObject(
						context, dataSourceJSONObject.getString("id"),
						dataSourceJSONObject.getString("name"), fieldType,
						modifiedDateString,
						fieldMappingJSONObject.getString("fieldName"), ownerId,
						ownerType, dataSourceFieldName, currentValue);

					newFieldsJSONArray.put(fieldJSONObject);
				}
			}
			else {
				JSONObject fieldJSONObject = _buildFieldJSONObject(
					context, dataSourceJSONObject.getString("id"),
					dataSourceJSONObject.getString("name"), fieldType,
					modifiedDateString,
					fieldMappingJSONObject.getString("fieldName"), ownerId,
					ownerType, dataSourceFieldName, value);

				if (_isAllowMultiple(
						fieldMappingJSONObject.getJSONObject("strategy"))) {

					newFieldsJSONArray.put(fieldJSONObject);
				}
				else if ((newFieldsJSONArray.length() == 0) ||
						 _isUpdateField(
							 context, dataSourceId, fieldJSONObject,
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
		String context, String dataSourceId, String fieldName,
		String ownerType) {

		JSONObject strategyJSONObject = _getStrategyJSONObject(
			context, dataSourceId, fieldName, ownerType);

		return _isAllowMultiple(strategyJSONObject);
	}

	private boolean _isMultiValueField(
		String context, String dataSourceId, String fieldName,
		String ownerType) {

		JSONArray fieldMappingsJSONArray = _getFieldMappingsJSONArray(
			context, dataSourceId, fieldName, ownerType);

		JSONObject fieldMappingJSONObject =
			fieldMappingsJSONArray.getJSONObject(0);

		if (Objects.equals(
				fieldMappingJSONObject.optString("displayType"), "checkbox")) {

			return true;
		}

		return false;
	}

	private boolean _isUpdateField(
		String context, String dataSourceId, JSONObject newFieldJSONObject,
		JSONObject oldFieldJSONObject, String ownerType) {

		String oldDataSourceId = oldFieldJSONObject.getString("dataSourceId");

		if (dataSourceId.equals(oldDataSourceId)) {
			return true;
		}
		else if (newFieldJSONObject.opt("value") == null) {
			return false;
		}

		JSONObject fieldMappingStrategyJSONObject = _getStrategyJSONObject(
			context, dataSourceId, oldFieldJSONObject.getString("name"),
			ownerType);

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
		String context, String dataSourceId, JSONArray newFieldsJSONArray,
		String ownerId, String ownerType) {

		List<String> newSingleFieldNames = new ArrayList<>();

		for (int i = 0; i < newFieldsJSONArray.length(); i++) {
			JSONObject newFieldJSONObject = newFieldsJSONArray.getJSONObject(i);

			String newFieldName = newFieldJSONObject.getString("name");

			if (!_isAllowMultiple(
					context, dataSourceId, newFieldName, ownerType)) {

				newSingleFieldNames.add(newFieldJSONObject.getString("name"));
			}
		}

		// Skip JavaParser, will fix

		JSONArray oldFieldsJSONArray = elasticsearchInvoker.get(
			"fields",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("context", context)
			).filter(
				QueryBuilders.boolQuery(
				).mustNot(
					QueryBuilders.termQuery("dataSourceId", dataSourceId)
				)
			).filter(
				BoolQueryBuilderUtil.mustNot(
					QueryBuilders.termsQuery("name", newSingleFieldNames))
			).filter(
				QueryBuilders.termQuery("ownerId", ownerId)
			).filter(
				QueryBuilders.termQuery("ownerType", ownerType)
			));

		for (int i = 0; i < oldFieldsJSONArray.length(); i++) {
			JSONObject oldFieldJSONObject = oldFieldsJSONArray.getJSONObject(i);

			oldFieldJSONObject.remove("id");

			newFieldsJSONArray.put(oldFieldJSONObject);
		}

		return _buildContextJSONObject(newFieldsJSONArray);
	}

	private void _replaceOrDeleteOldFields(
			String context, Set<String> multiValueFieldNames,
			JSONObject newContextJSONObject, JSONObject oldContextJSONObject,
			String ownerId, String ownerType, String uniqueIdContext,
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
					elasticsearchInvoker.delete(
						"fields", oldFieldJSONObject.getString("id"));
				}
				else if (oldFieldJSONObject == null) {
					elasticsearchInvoker.add("fields", newFieldJSONObject);
				}
				else {
					elasticsearchInvoker.update(
						"fields", oldFieldJSONObject.getString("id"),
						newFieldJSONObject);
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

		elasticsearchInvoker.update(
			collectionName, ownerId, JSONUtil.put(context, contextJSONObject));

		return contextJSONObject;
	}

	private void _updateMultiValueFields(
		String context, String dataSourceId,
		Map<String, List<JSONObject>> multiValueFieldsMap, String ownerId,
		String ownerType) {

		for (Map.Entry<String, List<JSONObject>> entry :
				multiValueFieldsMap.entrySet()) {

			elasticsearchInvoker.delete(
				"fields",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("context", context)
				).filter(
					QueryBuilders.termQuery("dataSourceId", dataSourceId)
				).filter(
					QueryBuilders.termQuery("name", entry.getKey())
				).filter(
					QueryBuilders.termQuery("ownerId", ownerId)
				).filter(
					QueryBuilders.termQuery("ownerType", ownerType)
				));

			for (JSONObject newFieldJSONObject : entry.getValue()) {
				elasticsearchInvoker.add("fields", newFieldJSONObject);
			}
		}
	}

	private static final Log _log = LogFactory.getLog(FaroInfoFieldDog.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@Autowired
	private FaroInfoDataSourceDog _faroInfoDataSourceDog;

	@Autowired
	private FaroInfoFieldMappingDog _faroInfoFieldMappingDog;

	private final Parser _parser = new Parser();

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

}