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

import com.liferay.osb.asah.common.entity.Suppression;
import com.liferay.osb.asah.common.repository.SuppressionRepository;
import com.liferay.osb.asah.upgrade.BaseMigrationUpgradeStep;

import java.util.function.Consumer;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leilany Ulisses
 */
@Component
public class SuppressionMigrationUpgradeStep extends BaseMigrationUpgradeStep {

	@Override
	protected Consumer<JSONObject> getConsumer() {
		return jsonObject -> {
			Suppression suppression = _objectMapper.convertValue(
				jsonObject, Suppression.class);

			suppression.setIsNew(Boolean.TRUE);

			_suppressionRepository.save(suppression);
		};
	}

	@Override
	protected String getElasticsearchCollectionName() {
		return "suppressions";
	}

	@Override
	protected String getSelectLatestIdSQL() {
		return "SELECT id FROM suppression ORDER BY id DESC LIMIT 1";
	}

	@Override
	protected String getSequenceName() {
		return "suppression_id_seq";
	}

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SuppressionRepository _suppressionRepository;

}