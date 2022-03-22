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

package com.liferay.osb.asah.upgrade.v3_2_0;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.entity.BlockedKeyword;
import com.liferay.osb.asah.common.repository.BlockedKeywordRepository;
import com.liferay.osb.asah.upgrade.BaseMigrationUpgradeStep;

import java.util.function.Consumer;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Robson Pastor
 */
@Component
public class BlockedKeywordMigrationUpgradeStep
	extends BaseMigrationUpgradeStep {

	@Override
	protected Consumer<JSONObject> getConsumer() {
		return jsonObject -> {
			BlockedKeyword blockedKeyword = _objectMapper.convertValue(
				jsonObject, BlockedKeyword.class);

			blockedKeyword.setIsNew(Boolean.TRUE);

			_blockedKeywordRepository.save(blockedKeyword);
		};
	}

	@Override
	protected String getElasticsearchCollectionName() {
		return "blocked-keywords";
	}

	@Override
	protected String getSelectLatestIdSQL() {
		return "SELECT id FROM blockedkeyword ORDER BY id DESC LIMIT 1";
	}

	@Override
	protected String getSequenceName() {
		return "blockedkeyword_id_seq";
	}

	@Autowired
	private BlockedKeywordRepository _blockedKeywordRepository;

	@Autowired
	private ObjectMapper _objectMapper;

}