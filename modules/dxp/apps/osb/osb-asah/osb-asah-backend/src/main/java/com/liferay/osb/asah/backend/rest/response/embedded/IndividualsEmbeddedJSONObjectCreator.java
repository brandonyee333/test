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

package com.liferay.osb.asah.backend.rest.response.embedded;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.faro.info.util.FaroInfoIndividualUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.rest.response.embedded.BaseEmbeddedJSONObjectCreator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Michael Bowerman
 * @author Rachael Koestartyo
 */
public class IndividualsEmbeddedJSONObjectCreator
	extends BaseEmbeddedJSONObjectCreator {

	public IndividualsEmbeddedJSONObjectCreator(
		DataSourceDog dataSourceDog, ElasticsearchInvoker elasticsearchInvoker,
		String expand, ObjectMapper objectMapper) {

		_dataSourceDog = dataSourceDog;
		_elasticsearchInvoker = elasticsearchInvoker;
		_expand = expand;
		_objectMapper = objectMapper;
	}

	@Override
	public Map<String, JSONObject> create(JSONArray jsonArray)
		throws Exception {

		if (StringUtils.isEmpty(_expand)) {
			return null;
		}

		String[] expandParts = _expand.split(",");

		for (String expandPart : expandParts) {
			if (expandPart.equals("account-names")) {
				return FaroInfoIndividualUtil.
					getIndividualAccountNamesJSONObjects(
						_elasticsearchInvoker, jsonArray);
			}
			else if (expandPart.equals("accounts")) {
				return FaroInfoIndividualUtil.getIndividualAccountsJSONObjects(
					_elasticsearchInvoker, jsonArray);
			}
			else if (expandPart.equals("data-sources")) {
				return _getDataSourceJSONObjects(jsonArray);
			}
			else if (expandPart.equals("individual-segments")) {
				return _getIndividualSegmentJSONObjects(jsonArray);
			}
			else if (_log.isWarnEnabled()) {
				_log.warn("Invalid expand: " + expandPart);
			}
		}

		return null;
	}

	@Override
	public JSONObject create(String id) throws Exception {
		return create(id, _elasticsearchInvoker.get("individuals", id));
	}

	private Map<String, JSONObject> _getDataSourceJSONObjects(
			JSONArray individualsJSONArray)
		throws Exception {

		Map<String, JSONObject> dataSourceJSONObjects = new HashMap<>();

		for (int i = 0; i < individualsJSONArray.length(); i++) {
			JSONObject individualJSONObject =
				individualsJSONArray.getJSONObject(i);

			List<Long> dataSourceIds = JSONUtil.toLongList(
				individualJSONObject.getJSONArray("dataSourceIndividualPKs"),
				"dataSourceId");

			dataSourceJSONObjects.put(
				individualJSONObject.getString("id"),
				JSONUtil.put(
					"data-sources",
					JSONUtil.toJSONArray(
						_dataSourceDog.getDataSources(dataSourceIds),
						dataSource -> _objectMapper.convertValue(
							dataSource, JSONObject.class))));
		}

		return dataSourceJSONObjects;
	}

	private Map<String, JSONObject> _getIndividualSegmentJSONObjects(
		JSONArray individualsJSONArray) {

		Map<String, JSONObject> individualSegmentJSONObjects = new HashMap<>();

		for (int i = 0; i < individualsJSONArray.length(); i++) {
			JSONObject individualJSONObject =
				individualsJSONArray.getJSONObject(i);

			List<String> individualSegmentIds = JSONUtil.toStringList(
				new JSONArray(
					_elasticsearchInvoker.get(
						"memberships",
						searchSourceBuilder -> {
							searchSourceBuilder.fetchSource(
								"individualSegmentId", null);
							searchSourceBuilder.query(
								QueryBuilders.termQuery(
									"individualId",
									individualJSONObject.getString("id")));
							searchSourceBuilder.size(20);
						})),
				"individualSegmentId");

			JSONArray individualSegmentsJSONArray = _elasticsearchInvoker.get(
				"individual-segments",
				QueryBuilders.termsQuery("id", individualSegmentIds));

			individualSegmentJSONObjects.put(
				individualJSONObject.getString("id"),
				JSONUtil.put(
					"individual-segments", individualSegmentsJSONArray));
		}

		return individualSegmentJSONObjects;
	}

	private static final Log _log = LogFactory.getLog(
		IndividualsEmbeddedJSONObjectCreator.class);

	private final DataSourceDog _dataSourceDog;
	private final ElasticsearchInvoker _elasticsearchInvoker;
	private final String _expand;
	private final ObjectMapper _objectMapper;

}