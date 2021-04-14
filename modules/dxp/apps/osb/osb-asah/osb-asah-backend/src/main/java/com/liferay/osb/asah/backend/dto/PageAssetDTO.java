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

import com.liferay.osb.asah.common.graphql.GraphQLProperty;
import com.liferay.osb.asah.common.graphql.GraphQLType;
import com.liferay.osb.asah.common.model.Asset;
import com.liferay.osb.asah.common.model.AssetKeyword;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.List;

/**
 * @author Marcellus Tavares
 */
@GraphQLType
public class PageAssetDTO {

	public PageAssetDTO(Asset asset) {
		_asset = asset;
	}

	@JsonProperty("keywords")
	public List<AssetKeywordDTO> getAssetKeywordDTOs() {
		return ListUtil.map(_asset.getAssetKeywords(), AssetKeywordDTO::new);
	}

	@JsonProperty("canonicalUrl")
	public String getCanonicalURL() {
		return _asset.getCanonicalURL();
	}

	public String getDescription() {
		return _asset.getDescription();
	}

	public String getId() {
		return StringUtil.get(_asset.getId(), null);
	}

	@JsonProperty("name")
	public String getTitle() {
		return _asset.getTitle();
	}

	@GraphQLProperty("url")
	public String getURL() {
		return _asset.getURL();
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class AssetKeywordDTO {

		public AssetKeywordDTO(AssetKeyword assetKeyword) {
			_assetKeyword = assetKeyword;
		}

		public String getType() {
			return _assetKeyword.getType();
		}

		@JsonProperty("keyword")
		public String getValue() {
			return _assetKeyword.getKeyword();
		}

		private final AssetKeyword _assetKeyword;

	}

	private final Asset _asset;

}