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

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Marcellus Tavares
 */
public class PageDataExporter extends BaseDataExporter {

	public PageDataExporter(
			JsonFactory jsonFactory, OutputStream outputStream,
			ElasticsearchInvoker elasticsearchInvoker)
		throws Exception {

		super(jsonFactory, outputStream, null);

		_elasticsearchInvoker = elasticsearchInvoker;
	}

	@Override
	protected JSONObject doGetResultPageJSONObject(String after) {
		JSONArray results = new JSONArray(
			_elasticsearchInvoker.get(
				"pages",
				searchSourceBuilder -> {
					searchSourceBuilder.searchAfter(new String[] {after});
					searchSourceBuilder.size(_PAGE_SIZE);
					searchSourceBuilder.sort(SortBuilderUtil.fieldSort("id"));
				}));

		return JSONUtil.put("results", results);
	}

	private static final int _PAGE_SIZE = 50;

	private final ElasticsearchInvoker _elasticsearchInvoker;

}