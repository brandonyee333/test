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

package com.liferay.osb.asah.upgrade.v2_12_0;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
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
public class AsahMarkerUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		_upgradeOSBAsahMarkerIndexMapping(
			WeDeployDataService.OSB_ASAH_CEREBRO_INFO);
		_upgradeOSBAsahMarkerIndexMapping(WeDeployDataService.OSB_ASAH_DXP_RAW);
		_upgradeOSBAsahMarkerIndexMapping(
			WeDeployDataService.OSB_ASAH_FARO_INFO);
		_upgradeOSBAsahMarkerIndexMapping(
			WeDeployDataService.OSB_ASAH_SALESFORCE_RAW);

		_upgradeOSBAsahMarkerJSONObjects(_cerebroInfoElasticsearchInvoker);
		_upgradeOSBAsahMarkerJSONObjects(_dxpRawElasticsearchInvoker);
		_upgradeOSBAsahMarkerJSONObjects(_faroInfoElasticsearchInvoker);
		_upgradeSalesforceRawOSBAsahMarkerJSONObjects();
	}

	private void _upgradeOSBAsahMarkerIndexMapping(
		WeDeployDataService weDeployDataService) {

		JSONObject indexConfigurationJSONObject = new JSONObject(
			_elasticsearchIndexManager.readIndexConfiguration(
				"OSBAsahMarkers", weDeployDataService));

		JSONObject mappingPropertiesJSONObject = (JSONObject)JSONUtil.getValue(
			indexConfigurationJSONObject, "JSONObject/mappings",
			"JSONObject/OSBAsahMarkers", "JSONObject/properties");

		_elasticsearchIndexManager.updateMapping(
			"OSBAsahMarkers",
			JSONUtil.put(
				"properties",
				JSONUtil.put(
					"context",
					mappingPropertiesJSONObject.getJSONObject("context"))
			).toString(),
			"OSBAsahMarkers", weDeployDataService);
	}

	private JSONObject _upgradeOSBAsahMarkerJSONObject(
		JSONObject oldRunLogJSONObject) {

		JSONObject newOSBAsahMarkerJSONObject = new JSONObject();

		JSONObject newOSBAsahMarkerContextJSONObject = new JSONObject();

		for (String key : oldRunLogJSONObject.keySet()) {
			if (Objects.equals(key, "id")) {
				newOSBAsahMarkerJSONObject.put(
					key, oldRunLogJSONObject.get(key));
			}
			else {
				newOSBAsahMarkerContextJSONObject.put(
					key, oldRunLogJSONObject.get(key));
			}
		}

		newOSBAsahMarkerJSONObject.put(
			"context", newOSBAsahMarkerContextJSONObject);

		return newOSBAsahMarkerJSONObject;
	}

	private void _upgradeOSBAsahMarkerJSONObjects(
			ElasticsearchInvoker elasticsearchInvoker)
		throws Exception {

		JSONArrayIterator.of(
			"OSBAsahMarkers", elasticsearchInvoker,
			osbAsahMarker -> elasticsearchInvoker.replace(
				"OSBAsahMarkers",
				_upgradeOSBAsahMarkerJSONObject(osbAsahMarker))
		).setQueryBuilder(
			BoolQueryBuilderUtil.mustNot(QueryBuilders.existsQuery("context"))
		).iterate();
	}

	private void _upgradeSalesforceRawOSBAsahMarkerJSONObjects()
		throws Exception {

		JSONArrayIterator.of(
			"OSBAsahMarkers", _salesforceRawElasticsearchInvoker,
			osbAsahMarker -> {
				String osbAsahDataSourceId = osbAsahMarker.getString(
					"osbAsahDataSourceId");

				JSONObject newOSBAsahMarkerJSONObject =
					_upgradeOSBAsahMarkerJSONObject(osbAsahMarker);

				newOSBAsahMarkerJSONObject.put("id", osbAsahDataSourceId);

				_salesforceRawElasticsearchInvoker.add(
					"OSBAsahMarkers", newOSBAsahMarkerJSONObject);

				_salesforceRawElasticsearchInvoker.delete(
					"OSBAsahMarkers",
					QueryBuilders.termQuery(
						"osbAsahDataSourceId", osbAsahDataSourceId));

				return null;
			}
		).setQueryBuilder(
			BoolQueryBuilderUtil.mustNot(QueryBuilders.existsQuery("context"))
		).iterate();
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

}