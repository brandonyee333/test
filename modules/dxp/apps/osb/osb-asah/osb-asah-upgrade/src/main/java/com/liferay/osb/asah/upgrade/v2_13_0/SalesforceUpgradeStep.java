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

package com.liferay.osb.asah.upgrade.v2_13_0;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.SalesforceEntity;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.Objects;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class SalesforceUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		_upgradeSalesforceEntityIndexMapping("Account");
		_upgradeSalesforceEntityIndexMapping("individuals");

		_upgradeSalesforceEntityJSONObjects(
			"Account", SalesforceEntity.Type.ACCOUNT);
		_upgradeSalesforceEntityJSONObjects(
			"Contact", SalesforceEntity.Type.CONTACT);
		_upgradeSalesforceEntityJSONObjects(
			"individuals", SalesforceEntity.Type.INDIVIDUAL);
		_upgradeSalesforceEntityJSONObjects("Lead", SalesforceEntity.Type.LEAD);
	}

	private void _upgradeSalesforceEntityIndexMapping(String collectionName) {
		_elasticsearchIndexManager.updateMapping(
			collectionName,
			JSONUtil.put(
				"dynamic_templates",
				JSONUtil.putAll(
					JSONUtil.put(
						"template_string_properties",
						JSONUtil.put(
							"mapping", JSONUtil.put("type", "keyword")
						).put(
							"match", "*"
						).put(
							"match_mapping_type", "string"
						)))
			).toString(),
			collectionName, WeDeployDataService.OSB_ASAH_SALESFORCE_RAW);
	}

	private JSONObject _upgradeSalesforceEntityJSONObjects(
		JSONObject salesforceEntityJSONObject,
		SalesforceEntity.Type salesforceEntityType) {

		JSONObject newSalesforceAccountJSONObject = new JSONObject();

		newSalesforceAccountJSONObject.put(
			"type", salesforceEntityType.toString());

		for (String key : salesforceEntityJSONObject.keySet()) {
			if (Objects.equals(key, "id")) {
				newSalesforceAccountJSONObject.put(
					key, salesforceEntityJSONObject.get(key));
			}
			else if (Objects.equals(key, "osbAsahDataSourceId")) {
				newSalesforceAccountJSONObject.put(
					"dataSourceId", salesforceEntityJSONObject.get(key));
			}
		}

		newSalesforceAccountJSONObject.put(
			"fields", salesforceEntityJSONObject);

		return newSalesforceAccountJSONObject;
	}

	private void _upgradeSalesforceEntityJSONObjects(
			String collectionName, SalesforceEntity.Type salesforceEntityType)
		throws Exception {

		JSONArrayIterator.of(
			collectionName, _salesforceRawElasticsearchInvoker,
			salesforceEntityJSONObject ->
				_salesforceRawElasticsearchInvoker.replace(
					collectionName,
					_upgradeSalesforceEntityJSONObjects(
						salesforceEntityJSONObject, salesforceEntityType))
		).setQueryBuilder(
			BoolQueryBuilderUtil.mustNot(QueryBuilders.existsQuery("fields"))
		).iterate();
	}

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

}