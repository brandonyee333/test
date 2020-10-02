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

package com.liferay.osb.asah.backend.dog;

import com.liferay.osb.asah.backend.model.DataSource;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoDataSourceDog;
import com.liferay.osb.asah.common.model.Sort;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 * @author André Miranda
 */
@Component
public class DataSourceDog {

	public DataSource getDataSource(String dataSourceId) {
		JSONObject dataSourceJSONObject =
			_faroInfoDataSourceDog.fetchDataSourceJSONObject(dataSourceId);

		if (dataSourceJSONObject == null) {
			return null;
		}

		return new DataSource(
			dataSourceJSONObject.getString("id"),
			dataSourceJSONObject.getString("name"),
			dataSourceJSONObject.optString("url"));
	}

	public List<DataSource> getDataSources(
		String credentialsType, Integer size, Sort sort, String type) {

		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if (credentialsType != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("credentials.type", credentialsType));
		}

		if (type != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("provider.type", type.toUpperCase()));
		}

		JSONArray dataSourcesJSONArray = new JSONArray(
			elasticsearchInvoker.get(
				"data-sources",
				searchSourceBuilder -> {
					searchSourceBuilder.query(boolQueryBuilder);

					if (size != null) {
						searchSourceBuilder.size(size);
					}

					if (sort != null) {
						searchSourceBuilder.sort(
							SortBuilderUtil.fieldSort(sort));
					}
				}));

		return _toDataSources(dataSourcesJSONArray);
	}

	private List<DataSource> _toDataSources(JSONArray jsonArray) {
		List<DataSource> dataSources = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject dataSourceJSONObject = jsonArray.getJSONObject(i);

			dataSources.add(
				new DataSource(
					dataSourceJSONObject.getString("id"),
					dataSourceJSONObject.getString("name"),
					dataSourceJSONObject.optString("url")));
		}

		return dataSources;
	}

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	@Autowired
	private FaroInfoDataSourceDog _faroInfoDataSourceDog;

}