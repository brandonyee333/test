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

import com.liferay.osb.asah.common.array.ArrayUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;

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
			long count = faroInfoElasticsearchInvoker.count(
				"field-mappings",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("fieldName", label)
				).filter(
					QueryBuilders.termQuery("ownerType", ownerType)
				));

			if (count > 0) {
				fieldNames.add(label);
			}

			List<String> dataSourceIds = JSONUtil.toStringList(
				new JSONArray(
					faroInfoElasticsearchInvoker.get(
						"data-sources",
						searchSourceBuilder -> searchSourceBuilder.fetchSource(
							"id", null))),
				"id");

			for (String dataSourceId : dataSourceIds) {
				JSONArray fieldMappingsJSONArray =
					faroInfoElasticsearchInvoker.get(
						"field-mappings",
						BoolQueryBuilderUtil.filter(
							QueryBuilders.termQuery(
								"dataSourceFieldNames." + dataSourceId, label)
						).filter(
							QueryBuilders.termQuery("ownerType", ownerType)
						));

				JSONUtil.addToStringCollection(
					fieldNames, fieldMappingsJSONArray, "fieldName");
			}
		}

		if (ArrayUtil.isNotEmpty(values)) {
			JSONArray fieldsJSONArray = faroInfoElasticsearchInvoker.get(
				"fields",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("fieldType", "Text")
				).filter(
					QueryBuilders.termQuery("ownerType", ownerType)
				).filter(
					QueryBuilders.termsQuery("value", Arrays.asList(values))
				));

			JSONUtil.addToStringCollection(fieldNames, fieldsJSONArray, "name");
		}

		JSONArray fieldNamesJSONArray = new JSONArray(fieldNames);

		return fieldNamesJSONArray.toString();
	}

}