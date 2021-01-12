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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class FaroInfoFieldMappingDog extends BaseFaroInfoDog {

	public void addEmailFieldMapping(String dataSourceId) {
		String dataSourceFieldName = _getEmailDataSourceFieldName(dataSourceId);

		if (dataSourceFieldName == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to get data source field name for data source id" +
						dataSourceId);
			}

			return;
		}

		addFieldMapping(
			"demographics", dataSourceFieldName, dataSourceId, null, "email",
			"http://schema.org/email", "individual");
	}

	public void addFieldMapping(
		String context, String dataSourceFieldName, String dataSourceId,
		String displayType, String fieldName, String fieldType,
		String ownerType) {

		if (StringUtils.isBlank(context) || StringUtils.isBlank(fieldName) ||
			StringUtils.isBlank(fieldType) || StringUtils.isBlank(ownerType)) {

			return;
		}

		JSONObject fieldMappingJSONObject = elasticsearchInvoker.fetch(
			"field-mappings",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("context", context)
			).filter(
				QueryBuilders.termQuery("displayName", fieldName)
			).filter(
				QueryBuilders.termQuery("fieldType", fieldType)
			).filter(
				QueryBuilders.termQuery("ownerType", ownerType)
			));

		if (fieldMappingJSONObject != null) {
			JSONObject dataSourceFieldNamesJSONObject =
				fieldMappingJSONObject.getJSONObject("dataSourceFieldNames");

			dataSourceFieldNamesJSONObject.put(
				dataSourceId, dataSourceFieldName);

			elasticsearchInvoker.update(
				"field-mappings",
				fieldMappingJSONObject.put(
					"dateModified", DateUtil.newDateString()));

			return;
		}

		fieldMappingJSONObject = elasticsearchInvoker.fetch(
			"field-mappings",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("context", context)
			).filter(
				QueryBuilders.termQuery(
					"dataSourceFieldNames." + dataSourceId, fieldName)
			).filter(
				QueryBuilders.termQuery("displayName", fieldName)
			).filter(
				QueryBuilders.termQuery("ownerType", ownerType)
			));

		if (fieldMappingJSONObject != null) {
			removeDataSourceFieldName(fieldMappingJSONObject, dataSourceId);
		}

		String dateString = DateUtil.newDateString();

		elasticsearchInvoker.add(
			"field-mappings",
			JSONUtil.put(
				"author", _getDataSourceAuthorJSONObject(dataSourceId)
			).put(
				"context", context
			).put(
				"dataSourceFieldNames",
				JSONUtil.put(dataSourceId, dataSourceFieldName)
			).put(
				"dateCreated", dateString
			).put(
				"dateModified", dateString
			).put(
				"displayName", fieldName
			).put(
				"displayType", displayType
			).put(
				"fieldName",
				_getSanitizedFieldName(context, fieldName, ownerType)
			).put(
				"fieldType", fieldType
			).put(
				"ownerType", ownerType
			).put(
				"strategy", JSONUtil.put("key", "MOST_RECENT")
			));
	}

	public boolean deleteFieldMapping(JSONObject fieldMappingJSONObject) {
		JSONObject authorJSONObject = fieldMappingJSONObject.getJSONObject(
			"author");

		if (Objects.equals(authorJSONObject.getString("id"), "FARO_SYSTEM") &&
			Objects.equals(authorJSONObject.getString("name"), "FARO_SYSTEM")) {

			return false;
		}

		elasticsearchInvoker.delete("field-mappings", fieldMappingJSONObject);

		return true;
	}

	public JSONObject fetchFieldMappingJSONObject(
		String context, String fieldName, String ownerType) {

		return elasticsearchInvoker.fetch(
			"field-mappings",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("context", context)
			).filter(
				QueryBuilders.termQuery("fieldName", fieldName)
			).filter(
				QueryBuilders.termQuery("ownerType", ownerType)
			));
	}

	public JSONObject removeDataSourceFieldName(
		JSONObject fieldMappingJSONObject, String dataSourceId) {

		JSONObject dataSourceFieldNamesJSONObject =
			fieldMappingJSONObject.getJSONObject("dataSourceFieldNames");

		dataSourceFieldNamesJSONObject.remove(dataSourceId);

		return elasticsearchInvoker.replace(
			"field-mappings", fieldMappingJSONObject);
	}

	private JSONObject _getDataSourceAuthorJSONObject(String dataSourceId) {
		JSONObject dataSourceJSONObject =
			_faroInfoDataSourceDog.getDataSourceJSONObject(dataSourceId);

		return JSONUtil.put(
			"id", dataSourceId
		).put(
			"name", dataSourceJSONObject.getString("name")
		);
	}

	private String _getEmailDataSourceFieldName(String dataSourceId) {
		JSONObject dataSourceJSONObject =
			_faroInfoDataSourceDog.getDataSourceJSONObject(dataSourceId);

		if (dataSourceJSONObject == null) {
			return null;
		}

		String dataSourceFieldName = "email";

		String type = _faroInfoDataSourceDog.getDataSourceType(
			dataSourceJSONObject);

		if (type.equals("LIFERAY")) {
			dataSourceFieldName = "emailAddress";
		}
		else if (_log.isWarnEnabled()) {
			_log.warn(
				"Default to using \"email\" as the data source field name " +
					"because of unknown provider type " + type);
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

		return elasticsearchInvoker.exists(
			"field-mappings",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("context", context)
			).filter(
				QueryBuilders.termQuery("fieldName", fieldName)
			).filter(
				QueryBuilders.termQuery("ownerType", ownerType)
			));
	}

	private static final Log _log = LogFactory.getLog(
		FaroInfoFieldMappingDog.class);

	@Autowired
	private FaroInfoDataSourceDog _faroInfoDataSourceDog;

}