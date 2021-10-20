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

package com.liferay.osb.asah.upgrade.v3_0_0;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.text.SimpleDateFormat;

import java.util.Date;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcos Martins
 */
@Component
public class DXPEntityUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		_upgradeDXPEntityIndexMapping("groups");
		_upgradeDXPEntityIndexMapping("organizations");
		_upgradeDXPEntityIndexMapping("roles");
		_upgradeDXPEntityIndexMapping("teams");
		_upgradeDXPEntityIndexMapping("user-groups");
		_upgradeDXPEntityIndexMapping("users");

		_upgradeDXPEntityJSONArray("groups");
		_upgradeDXPEntityJSONArray("organizations");
		_upgradeDXPEntityJSONArray("roles");
		_upgradeDXPEntityJSONArray("teams");
		_upgradeDXPEntityJSONArray("user-groups");
		_upgradeDXPEntityJSONArray("users");
	}

	private void _upgradeDXPEntityIndexMapping(String collectionName) {
		JSONObject indexConfigurationJSONObject = new JSONObject(
			_elasticsearchIndexManager.readIndexConfiguration(
				collectionName, WeDeployDataService.OSB_ASAH_DXP_RAW));

		JSONObject mappingPropertiesJSONObject = (JSONObject)JSONUtil.getValue(
			indexConfigurationJSONObject, "JSONObject/mappings",
			"JSONObject/" + collectionName, "JSONObject/properties");

		mappingPropertiesJSONObject.remove("dataSourceId");
		mappingPropertiesJSONObject.remove("id");
		mappingPropertiesJSONObject.remove("type");

		_elasticsearchIndexManager.updateMapping(
			collectionName,
			JSONUtil.put(
				"properties",
				JSONUtil.put(
					"fields",
					JSONUtil.put(
						"properties", mappingPropertiesJSONObject
					).put(
						"type", "object"
					))
			).toString(),
			collectionName, WeDeployDataService.OSB_ASAH_DXP_RAW);
	}

	private void _upgradeDXPEntityJSONArray(String collectionName)
		throws Exception {

		JSONArrayIterator.of(
			collectionName, _dxpRawElasticsearchInvoker, null
		).setBatchSize(
			10000
		).setProcessJSONArrayUnsafeFunction(
			dxpEntityJSONArray -> _dxpRawElasticsearchInvoker.add(
				collectionName,
				_upgradeDXPEntityJSONArray(collectionName, dxpEntityJSONArray))
		).setQueryBuilder(
			BoolQueryBuilderUtil.mustNot(QueryBuilders.existsQuery("fields"))
		).iterate();
	}

	private JSONArray _upgradeDXPEntityJSONArray(
		String collectionName, JSONArray dxpEntityJSONArray) {

		JSONArray jsonArray = new JSONArray();

		for (int i = 0; i < dxpEntityJSONArray.length(); i++) {
			JSONObject dxpEntityJSONObject = dxpEntityJSONArray.getJSONObject(
				i);

			JSONObject jsonObject = new JSONObject();

			if (dxpEntityJSONObject.has("contact")) {
				JSONObject contactJSONObject =
					dxpEntityJSONObject.getJSONObject("contact");

				if (contactJSONObject.has("birthday")) {
					Object birthday = contactJSONObject.get("birthday");

					try {
						if (!NumberUtils.isCreatable(
								String.valueOf(birthday))) {

							SimpleDateFormat simpleDateFormat =
								new SimpleDateFormat(
									"EEE MMM dd HH:mm:ss Z yyyy");

							Date birthdayDate = simpleDateFormat.parse(
								String.valueOf(birthday));

							contactJSONObject.put(
								"birthday", birthdayDate.getTime());
						}
					}
					catch (Exception exception) {
						_log.error(
							"Could not parse the birthday " + birthday,
							exception);

						contactJSONObject.put("birthday", 0L);
					}
				}
			}

			if (dxpEntityJSONObject.has("osbAsahDataSourceId")) {
				jsonObject.put(
					"dataSourceId",
					dxpEntityJSONObject.get("osbAsahDataSourceId"));
			}

			jsonObject.put("fields", dxpEntityJSONObject);

			if (dxpEntityJSONObject.has("id")) {
				jsonObject.put("id", dxpEntityJSONObject.get("id"));
			}

			jsonObject.put(
				"type", DXPEntity.Type.ofCollectionName(collectionName));

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	private static final Log _log = LogFactory.getLog(
		DXPEntityUpgradeStep.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

}