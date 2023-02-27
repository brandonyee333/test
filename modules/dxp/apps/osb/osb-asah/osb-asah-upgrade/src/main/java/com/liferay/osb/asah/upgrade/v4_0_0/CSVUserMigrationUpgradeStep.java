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

import com.liferay.osb.asah.common.entity.BQCSVUser;
import com.liferay.osb.asah.common.repository.BQCSVUserRepository;
import com.liferay.osb.asah.upgrade.BaseMigrationUpgradeStep;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
			bqCSVUser.setFields(_toFields(jsonObject.getJSONObject("fields")));

			_bqCSVUserRepository.insert(bqCSVUser);
		};
	}

	@Override
	protected String getElasticsearchCollectionName() {
		return "csv-individuals";
	}

	@Override
	protected String getSelectLatestIdSQL() {
		return null;
	}

	@Override
	protected String getSequenceName() {
		return null;
	}

	@Override
	protected void syncSequenceStart() {
	}

	private List<BQCSVUser.Field> _toFields(JSONObject fieldsJSONObject) {
		List<BQCSVUser.Field> fields = new ArrayList<>();

		for (String key : fieldsJSONObject.keySet()) {
			fields.add(
				new BQCSVUser.Field(key, fieldsJSONObject.getString(key)));
		}

		return fields;
	}

	@Autowired
	private BQCSVUserRepository _bqCSVUserRepository;

}