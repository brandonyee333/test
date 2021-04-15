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
import com.fasterxml.jackson.annotation.JsonRootName;

import com.liferay.osb.asah.common.model.Asset;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.List;
import java.util.Set;

/**
 * @author Marcellus Tavares
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("assets")
public class AssetDTO {

	public AssetDTO(Asset asset) {
		_assetKeywordDTOs = ListUtil.map(
			asset.getAssetKeywords(), AssetKeywordDTO::new);
		_assetType = asset.getAssetType();
		_canonicalURL = asset.getCanonicalURL();
		_dataSourceAssetPK = asset.getDataSourceAssetPK();
		_dataSourceId = StringUtil.get(asset.getDataSourceId(), null);
		_description = asset.getDescription();
		_id = StringUtil.get(asset.getId(), null);
		_title = asset.getTitle();
		_url = asset.getURL();
	}

	public AssetDTO(List<Asset> assets) {
		_assetDTOs = SetUtil.map(assets, AssetDTO::new);
	}

	@JsonProperty("assets")
	public Set<AssetDTO> getAssetDTOs() {
		return _assetDTOs;
	}

	@JsonProperty("keywords")
	public List<AssetKeywordDTO> getAssetKeywordDTOs() {
		return _assetKeywordDTOs;
	}

	@JsonProperty("assetType")
	public String getAssetType() {
		return _assetType;
	}

	@JsonProperty("canonicalUrl")
	public String getCanonicalURL() {
		return _canonicalURL;
	}

	@JsonProperty("dataSourceAssetPK")
	public String getDataSourceAssetPK() {
		return _dataSourceAssetPK;
	}

	@JsonProperty("dataSourceId")
	public String getDataSourceId() {
		return _dataSourceId;
	}

	@JsonProperty("description")
	public String getDescription() {
		return _description;
	}

	@JsonProperty("id")
	public String getId() {
		return _id;
	}

	@JsonProperty("name")
	public String getTitle() {
		return _title;
	}

	@JsonProperty("url")
	public String getURL() {
		return _url;
	}

	private Set<AssetDTO> _assetDTOs;
	private List<AssetKeywordDTO> _assetKeywordDTOs;
	private String _assetType;
	private String _canonicalURL;
	private String _dataSourceAssetPK;
	private String _dataSourceId;
	private String _description;
	private String _id;
	private String _title;
	private String _url;

}