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
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
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
public class FaroInfoFieldMappingDog extends BaseFaroInfoDog {

	public void addEmailFieldMapping(Long dataSourceId) {
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
		String context, String dataSourceFieldName, Long dataSourceId,
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
				String.valueOf(dataSourceId), dataSourceFieldName);

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
					"dataSourceFieldNames." + String.valueOf(dataSourceId),
					fieldName)
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
				JSONUtil.put(String.valueOf(dataSourceId), dataSourceFieldName)
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

	public List<String> getDataSourceFieldMappingIds(
		Long dataSourceId, boolean previewDelete) {

		List<String> dataSourceFieldMappingIds = new ArrayList<>();

		JSONArray jsonArray = elasticsearchInvoker.get(
			"field-mappings",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.existsQuery(
					"dataSourceFieldNames." + dataSourceId)));

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			JSONObject dataSourceFieldNamesJSONObject =
				jsonObject.getJSONObject("dataSourceFieldNames");

			if (previewDelete &&
				(dataSourceFieldNamesJSONObject.length() > 1)) {

				continue;
			}

			dataSourceFieldMappingIds.add(jsonObject.getString("id"));
		}

		return dataSourceFieldMappingIds;
	}

	public JSONObject removeDataSourceFieldName(
		JSONObject fieldMappingJSONObject, Long dataSourceId) {

		JSONObject dataSourceFieldNamesJSONObject =
			fieldMappingJSONObject.getJSONObject("dataSourceFieldNames");

		dataSourceFieldNamesJSONObject.remove(String.valueOf(dataSourceId));

		return elasticsearchInvoker.replace(
			"field-mappings", fieldMappingJSONObject);
	}

	private JSONObject _getDataSourceAuthorJSONObject(Long dataSourceId) {
		DataSource dataSource = _dataSourceDog.getDataSource(dataSourceId);

		return JSONUtil.put(
			"id", String.valueOf(dataSourceId)
		).put(
			"name", dataSource.getName()
		);
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
	private DataSourceDog _dataSourceDog;

}