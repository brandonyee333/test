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

package com.liferay.osb.asah.upgrade.v4_0_0;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.entity.BQCSVUser;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.BQCSVUserRepository;
import com.liferay.osb.asah.upgrade.BaseMigrationUpgradeStep;

import java.util.function.Consumer;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leilany Ulisses
 */
@Component
public class CSVUserMigrationUpgradeStep extends BaseMigrationUpgradeStep {

	@Override
	protected Consumer<JSONObject> getConsumer() {
		return jsonObject -> {
			BQCSVUser bqCSVUser = new BQCSVUser();

			bqCSVUser.setDataSourceId(
				Long.valueOf(jsonObject.getString("dataSourceId")));
			bqCSVUser.setFieldsJSONArray(
				_toFieldsJSONArray(jsonObject.getJSONObject("fields")));
			bqCSVUser.setIsNew(Boolean.TRUE);

			_bqCSVUserRepository.save(bqCSVUser);
		};
	}

	@Override
	protected String getElasticsearchCollectionName() {
		return "csv-individuals";
	}

	@Override
	protected String getSelectLatestIdSQL() {
		return "SELECT id FROM bqcsvuser ORDER BY id DESC LIMIT 1";
	}

	@Override
	protected String getSequenceName() {
		return "bqcsvuser_id_seq";
	}

	private JSONArray _toFieldsJSONArray(JSONObject fieldsJSONObject) {
		JSONArray fieldsJSONArray = new JSONArray();

		for (String key : fieldsJSONObject.keySet()) {
			fieldsJSONArray.put(
				JSONUtil.put(
					"name", key
				).put(
					"value", fieldsJSONObject.getString(key)
				));
		}

		return fieldsJSONArray;
	}

	@Autowired
	private BQCSVUserRepository _bqCSVUserRepository;

	@Autowired
	private ObjectMapper _objectMapper;

}