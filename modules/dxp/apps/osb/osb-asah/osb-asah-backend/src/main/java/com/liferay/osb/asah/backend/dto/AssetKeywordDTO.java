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

package com.liferay.osb.asah.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.osb.asah.common.model.AssetKeyword;

/**
 * @author Marcellus Tavares
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssetKeywordDTO {

	public AssetKeywordDTO(AssetKeyword assetKeyword) {
		_assetKeyword = assetKeyword;
	}

	@JsonProperty("type")
	public String getType() {
		return _assetKeyword.getType();
	}

	@JsonProperty("keyword")
	public String getValue() {
		return _assetKeyword.getKeyword();
	}

	private final AssetKeyword _assetKeyword;

}