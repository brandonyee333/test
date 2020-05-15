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

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.rest.response.embedded.BaseEmbeddedJSONObjectCreator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Marcellus Tavares
 */
public class BlockedKeywordEmbeddedJSONObjectCreator
	extends BaseEmbeddedJSONObjectCreator {

	public BlockedKeywordEmbeddedJSONObjectCreator(
		ElasticsearchInvoker elasticsearchInvoker) {

		_elasticsearchInvoker = elasticsearchInvoker;
	}

	@Override
	public Map<String, JSONObject> create(JSONArray jsonArray) {
		return _getDataSourceJSONObjects(jsonArray);
	}

	@Override
	public JSONObject create(String id) throws Exception {
		return create(id, _elasticsearchInvoker.get("blocked-keywords", id));
	}

	private Map<String, JSONObject> _getDataSourceJSONObjects(
		JSONArray blockedKeywordsJSONArray) {

		Map<String, JSONObject> dataSourceJSONObjects = new HashMap<>();

		for (int i = 0; i < blockedKeywordsJSONArray.length(); i++) {
			JSONObject blockedKeywordJSONObject =
				blockedKeywordsJSONArray.getJSONObject(i);

			List<String> dataSourceIds = JSONUtil.toStringList(
				blockedKeywordJSONObject.getJSONArray("dataSourceIds"));

			JSONArray dataSourcesJSONArray = new JSONArray(
				_elasticsearchInvoker.get(
					"data-sources",
					searchSourceBuilder -> {
						searchSourceBuilder.fetchSource(
							new String[] {"id", "name", "status"}, null);
						searchSourceBuilder.query(
							QueryBuilders.termsQuery("id", dataSourceIds));
					}));

			dataSourceJSONObjects.put(
				blockedKeywordJSONObject.getString("id"),
				JSONUtil.put("data-sources", dataSourcesJSONArray));
		}

		return dataSourceJSONObjects;
	}

	private final ElasticsearchInvoker _elasticsearchInvoker;

}