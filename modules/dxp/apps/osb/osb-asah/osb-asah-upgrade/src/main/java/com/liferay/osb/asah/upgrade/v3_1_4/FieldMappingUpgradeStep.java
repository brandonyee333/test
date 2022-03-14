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

package com.liferay.osb.asah.upgrade.v3_1_4;

import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.elasticsearch.action.support.WriteRequest;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class FieldMappingUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			_faroInfoElasticsearchInvoker.
				createElasticsearchBulkRequestBuilder();

		elasticsearchBulkRequestBuilder.refreshPolicy(
			WriteRequest.RefreshPolicy.IMMEDIATE);

		List<String> dataSourceIds = ListUtil.map(
			_dataSourceDog.getDataSources(),
			dataSource -> String.valueOf(dataSource.getId()));

		JSONArrayIterator.of(
			"field-mappings", _faroInfoElasticsearchInvoker,
			jsonObject -> {
				JSONObject dataSourceFieldNamesJSONObject =
					jsonObject.optJSONObject("dataSourceFieldNames");

				if (dataSourceFieldNamesJSONObject == null) {
					return elasticsearchBulkRequestBuilder;
				}

				Iterator<String> iterator =
					dataSourceFieldNamesJSONObject.keys();

				while (iterator.hasNext()) {
					String key = iterator.next();

					if (!dataSourceIds.contains(key)) {
						iterator.remove();
					}
				}

				if (dataSourceFieldNamesJSONObject.isEmpty()) {
					JSONObject authorJSONObject = jsonObject.optJSONObject(
						"author");

					if ((authorJSONObject != null) &&
						!Objects.equals(
							authorJSONObject.getString("id"), "FARO_SYSTEM")) {

						elasticsearchBulkRequestBuilder.delete(
							"field-mappings", jsonObject.getString("id"));

						return elasticsearchBulkRequestBuilder;
					}
				}

				elasticsearchBulkRequestBuilder.replace(
					"field-mappings", jsonObject);

				return elasticsearchBulkRequestBuilder;
			}
		).iterate();
	}

	@Autowired
	private DataSourceDog _dataSourceDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}