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

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoDataSourceDog;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.util.Pair;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rachael Koestartyo
 */
@RequestMapping("/definitions")
@RestController
public class DefinitionsRestController extends BaseRestController {

	@GetMapping("/individual-attributes")
	public String getIndividualAttributes(
			@RequestParam(required = false) String name)
		throws Exception {

		String responseJSON = toCollectionGetResponse(
			"field-mappings", null, 0, _getQueryBuilder(name),
			(int)faroInfoElasticsearchInvoker.count(
				"field-mappings", _getQueryBuilder(name)),
			new String[] {"fieldName", "asc"});

		JSONObject responseJSONObject = new JSONObject(responseJSON);

		_addDataSources(responseJSONObject);

		return responseJSONObject.toString();
	}

	private void _addDataSources(JSONObject responseJSONObject) {
		JSONObject embeddedJSONObject = responseJSONObject.getJSONObject(
			"_embedded");

		JSONArray fieldMappingsJSONArray = embeddedJSONObject.getJSONArray(
			"field-mappings");

		for (int i = 0; i < fieldMappingsJSONArray.length(); i++) {
			JSONObject jsonObject = fieldMappingsJSONArray.getJSONObject(i);

			JSONObject dataSourceFieldNamesJSONObject =
				jsonObject.getJSONObject("dataSourceFieldNames");

			Iterator<String> keys = dataSourceFieldNamesJSONObject.keys();

			List<Pair<String, String>> pairs = new ArrayList<>();

			while (keys.hasNext()) {
				String key = keys.next();

				pairs.add(
					new Pair<>(
						_faroInfoDataSourceDog.getDataSourceName(key),
						dataSourceFieldNamesJSONObject.getString(key)));
			}

			pairs.sort(Comparator.comparing(Pair::getKey));

			JSONArray pairsJSONArray = new JSONArray();

			for (Pair<String, String> pair : pairs) {
				JSONObject pairJSONObject = JSONUtil.put(
					"dataSourceFieldName", pair.getValue()
				).put(
					"dataSourceName", pair.getKey()
				);

				pairsJSONArray.put(pairJSONObject);
			}

			jsonObject.put("dataSources", pairsJSONArray);
		}
	}

	private QueryBuilder _getQueryBuilder(String name) {
		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.existsQuery("dataSourceFieldNames")
		).filter(
			QueryBuilders.termQuery("ownerType", "individual")
		);

		if (StringUtils.isNotBlank(name)) {
			boolQueryBuilder.filter(
				BoolQueryBuilderUtil.should(
					QueryBuilders.queryStringQuery(
						String.format(
							"%s:*%s*", "fieldName",
							QueryUtil.escapeKeywords(name)))
				).should(
					QueryBuilders.matchQuery(
						"fieldName", name
					).fuzziness(
						Fuzziness.AUTO
					)
				));
		}

		return boolQueryBuilder;
	}

	@Autowired
	private FaroInfoDataSourceDog _faroInfoDataSourceDog;

}