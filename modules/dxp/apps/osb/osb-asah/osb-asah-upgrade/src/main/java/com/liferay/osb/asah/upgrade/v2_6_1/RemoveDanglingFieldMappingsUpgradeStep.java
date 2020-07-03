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

package com.liferay.osb.asah.upgrade.v2_6_1;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualSegmentDog;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class RemoveDanglingFieldMappingsUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();

		Map<String, List<String>> invalidFieldMappings = new HashMap<>();

		Set<String> validDataSourceIds = JSONUtil.toStringSet(
			_faroInfoElasticsearchInvoker.get("data-sources"), "id");

		JSONArrayIterator.of(
			"field-mappings", _faroInfoElasticsearchInvoker,
			fieldMappingJSONObject -> {
				List<String> invalidDataSourceIds = new ArrayList<>();

				JSONObject dataSourceFieldNamesJSONObject =
					fieldMappingJSONObject.getJSONObject(
						"dataSourceFieldNames");

				Set<String> fieldMappingDataSourceIds = new HashSet<>(
					dataSourceFieldNamesJSONObject.keySet());

				for (String dataSourceId : fieldMappingDataSourceIds) {
					if (!validDataSourceIds.contains(dataSourceId)) {
						invalidDataSourceIds.add(dataSourceId);
						dataSourceFieldNamesJSONObject.remove(dataSourceId);
					}
				}

				if (dataSourceFieldNamesJSONObject.length() == 0) {
					_faroInfoElasticsearchInvoker.delete(
						"field-mappings", fieldMappingJSONObject);
				}
				else {
					fieldMappingJSONObject.put(
						"dateModified", DateUtil.newDateString());

					_faroInfoElasticsearchInvoker.replace(
						"field-mappings", fieldMappingJSONObject);
				}

				for (String dataSourceId : invalidDataSourceIds) {
					List<String> fieldMappingIds =
						invalidFieldMappings.computeIfAbsent(
							dataSourceId, key -> new ArrayList<>());

					fieldMappingIds.add(fieldMappingJSONObject.getString("id"));
				}

				return null;
			}
		).setQueryBuilder(
			QueryBuilders.rangeQuery(
				"dateModified"
			).lt(
				DateUtil.newDateString()
			)
		).iterate();

		for (Map.Entry<String, List<String>> entry :
				invalidFieldMappings.entrySet()) {

			_faroInfoIndividualSegmentDog.disableDynamicIndividualSegments(
				entry.getKey(), entry.getValue());
		}
	}

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private FaroInfoIndividualSegmentDog _faroInfoIndividualSegmentDog;

}