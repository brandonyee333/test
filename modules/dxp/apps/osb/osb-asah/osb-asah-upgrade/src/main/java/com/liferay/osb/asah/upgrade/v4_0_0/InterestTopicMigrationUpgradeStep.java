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

import com.liferay.osb.asah.common.entity.InterestTopic;
import com.liferay.osb.asah.common.repository.InterestTopicRepository;
import com.liferay.osb.asah.upgrade.BaseMigrationUpgradeStep;

import java.util.function.Consumer;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leilany Ulisses
 */
@Component
public class InterestTopicMigrationUpgradeStep
	extends BaseMigrationUpgradeStep {

	@Override
	protected Consumer<JSONObject> getConsumer() {
		return jsonObject -> {
			InterestTopic interestTopic = _objectMapper.convertValue(
				jsonObject, InterestTopic.class);

			interestTopic.setIsNew(Boolean.TRUE);

			_interestTopicRepository.save(interestTopic);
		};
	}

	@Override
	protected String getElasticsearchCollectionName() {
		return "interest-topics";
	}

	@Override
	protected String getSelectLatestIdSQL() {
		return "SELECT id FROM interesttopic ORDER BY id DESC LIMIT 1";
	}

	@Override
	protected String getSequenceName() {
		return "interesttopic_id_seq";
	}

	@Autowired
	private InterestTopicRepository _interestTopicRepository;

	@Autowired
	private ObjectMapper _objectMapper;

}