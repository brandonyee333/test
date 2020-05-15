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

package com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter;

import com.fasterxml.jackson.core.JsonFactory;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.io.OutputStream;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Matthew Kong
 */
public class RawDataExporter extends BaseDataExporter {

	public RawDataExporter(
			String collectionName, ElasticsearchInvoker elasticsearchInvoker,
			JsonFactory jsonFactory, OutputStream outputStream,
			QueryBuilder queryBuilder)
		throws Exception {

		super(jsonFactory, outputStream, null);

		_collectionName = collectionName;
		_elasticsearchInvoker = elasticsearchInvoker;
		_queryBuilder = queryBuilder;

		jsonGenerator.useDefaultPrettyPrinter();
	}

	protected JSONObject doGetResultPageJSONObject(String after) {
		JSONArray jsonArray = new JSONArray(
			_elasticsearchInvoker.get(
				_collectionName,
				searchSourceBuilder -> {
					searchSourceBuilder.query(_queryBuilder);
					searchSourceBuilder.searchAfter(new String[] {after});
					searchSourceBuilder.size(_PAGE_SIZE);
					searchSourceBuilder.sort(
						SortBuilderUtil.fieldSort("id", SortOrder.ASC));
				}));

		return JSONUtil.put("results", jsonArray);
	}

	private static final int _PAGE_SIZE = 50;

	private final String _collectionName;
	private final ElasticsearchInvoker _elasticsearchInvoker;
	private final QueryBuilder _queryBuilder;

}