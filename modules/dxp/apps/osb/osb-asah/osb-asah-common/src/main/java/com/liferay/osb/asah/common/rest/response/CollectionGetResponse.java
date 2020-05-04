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

package com.liferay.osb.asah.common.rest.response;

import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.rest.response.embedded.EmbeddedJSONObjectCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Brian Wing Shun Chan
 * @author Michael Bowerman
 * @author David Bhasme
 */
public class CollectionGetResponse extends BaseGetResponse {

	public String getEmbeddedJSONArrayKey() {
		return Optional.ofNullable(
			_embeddedJSONArrayKey
		).orElse(
			collectionName
		);
	}

	@Override
	public String respond() throws Exception {
		totalElements = elasticsearchInvoker.count(
			collectionName, queryBuilder);

		JSONObject responseJSONObject = new JSONObject();

		responseJSONObject.put("_embedded", getEmbeddedJSONObject());
		responseJSONObject.put("page", _getPageJSONObject());

		return responseJSONObject.toString();
	}

	public void setEmbeddedJSONArray(JSONArray embeddedJSONArray) {
		_embeddedJSONArray = embeddedJSONArray;
	}

	public void setEmbeddedJSONArrayKey(String embeddedJSONArrayKey) {
		_embeddedJSONArrayKey = embeddedJSONArrayKey;
	}

	public void setEmbeddedJSONObjectCreator(
		EmbeddedJSONObjectCreator embeddedJSONObjectCreator) {

		_embeddedJSONObjectCreator = embeddedJSONObjectCreator;
	}

	public void setFieldSortBuilders(List<FieldSortBuilder> fieldSortBuilders) {
		if ((fieldSortBuilders == null) || fieldSortBuilders.isEmpty()) {
			return;
		}

		this.fieldSortBuilders.clear();

		this.fieldSortBuilders.addAll(fieldSortBuilders);
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setQueryBuilder(QueryBuilder queryBuilder) {
		this.queryBuilder = queryBuilder;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setSorts(String[] sorts) {
		if (sorts == null) {
			return;
		}

		sortOrderPairs.clear();

		sortOrderPairs.addAll(SortBuilderUtil.getSortOrderPairs(sorts));
	}

	protected JSONObject getEmbeddedJSONObject() throws Exception {
		String embeddedJSONArrayKey = getEmbeddedJSONArrayKey();

		if (totalElements == 0) {
			return JSONUtil.put(embeddedJSONArrayKey, new JSONArray());
		}

		JSONArray embeddedJSONArray = _getEmbeddedJSONArray();

		if (_embeddedJSONObjectCreator != null) {
			Map<String, JSONObject> embeddedJSONObjects =
				_embeddedJSONObjectCreator.create(embeddedJSONArray);

			if ((embeddedJSONObjects == null) ||
				embeddedJSONObjects.isEmpty()) {

				return JSONUtil.put(embeddedJSONArrayKey, embeddedJSONArray);
			}

			for (int i = 0; i < embeddedJSONArray.length(); i++) {
				JSONObject embeddedJSONObject = embeddedJSONArray.getJSONObject(
					i);

				embeddedJSONArray.put(
					i,
					embeddedJSONObject.put(
						"_embedded",
						embeddedJSONObjects.get(
							embeddedJSONObject.getString("id"))));
			}
		}

		return JSONUtil.put(embeddedJSONArrayKey, embeddedJSONArray);
	}

	protected final List<FieldSortBuilder> fieldSortBuilders =
		new ArrayList<>();
	protected int page;
	protected QueryBuilder queryBuilder;
	protected int size;
	protected final List<Pair<String, SortOrder>> sortOrderPairs =
		new ArrayList<>();
	protected long totalElements;

	private JSONArray _getEmbeddedJSONArray() {
		if (_embeddedJSONArray != null) {
			return _embeddedJSONArray;
		}

		return new JSONArray(
			elasticsearchInvoker.get(
				collectionName,
				searchSourceBuilder -> {
					searchSourceBuilder.from(page * size);
					searchSourceBuilder.size(size);

					if (queryBuilder != null) {
						searchSourceBuilder.query(queryBuilder);
					}

					for (FieldSortBuilder fieldSortBuilder :
							fieldSortBuilders) {

						searchSourceBuilder.sort(fieldSortBuilder);
					}

					for (Pair<String, SortOrder> sortOrderPair :
							sortOrderPairs) {

						searchSourceBuilder.sort(
							SortBuilderUtil.fieldSort(
								sortOrderPair.getKey(),
								sortOrderPair.getValue()));
					}
				}));
	}

	private JSONObject _getPageJSONObject() {
		JSONObject pageJSONObject = new JSONObject();

		pageJSONObject.put("number", page);
		pageJSONObject.put("size", size);
		pageJSONObject.put("totalElements", totalElements);
		pageJSONObject.put(
			"totalPages", ((totalElements - 1) / Math.max(size, 1)) + 1);

		return pageJSONObject;
	}

	private JSONArray _embeddedJSONArray;
	private String _embeddedJSONArrayKey;
	private EmbeddedJSONObjectCreator _embeddedJSONObjectCreator;

}