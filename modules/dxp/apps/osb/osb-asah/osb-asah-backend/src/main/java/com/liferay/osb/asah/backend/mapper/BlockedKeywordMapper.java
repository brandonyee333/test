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

package com.liferay.osb.asah.common.mapper;

import com.liferay.osb.asah.common.dto.BlockedKeywordDTO;
import com.liferay.osb.asah.common.model.BlockedKeyword;

import org.json.JSONObject;

import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class BlockedKeywordMapper
	extends Mapper<BlockedKeyword, BlockedKeywordDTO> {

	@Override
	protected BlockedKeywordDTO toDTO(BlockedKeyword blockedKeyword) {
		return new BlockedKeywordDTO(blockedKeyword);
	}

	@Override
	protected BlockedKeyword toModel(JSONObject jsonObject) {
		BlockedKeyword blockedKeyword = new BlockedKeyword();

		if (jsonObject.has("createDate") && !jsonObject.isNull("createDate")) {
			blockedKeyword.setCreateDate(
				toUTCDate(jsonObject.get("createDate")));
		}

		if (jsonObject.has("id") && !jsonObject.isNull("id")) {
			blockedKeyword.setId(jsonObject.getLong("id"));
		}

		if (jsonObject.has("keyword") && !jsonObject.isNull("keyword")) {
			blockedKeyword.setKeyword(jsonObject.getString("keyword"));
		}

		return blockedKeyword;
	}

}