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

package com.liferay.osb.asah.stream.curator.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author André Miranda
 */
public abstract class BaseAssetModel extends BaseModel {

	public void addURL(String url) {
		_urls.add(url);
	}

	public void addURLs(Set<String> urls) {
		_urls.addAll(urls);
	}

	public String getAssetId() {
		return _assetId;
	}

	public String getAssetPrimaryKey() {
		return _assetPrimaryKey;
	}

	public Set<String> getURLs() {
		return _urls;
	}

	@Override
	public boolean isAsset() {
		return true;
	}

	public void setAssetId(String assetId) {
		_assetId = assetId;
	}

	public void setAssetPrimaryKey(String assetPrimaryKey) {
		_assetPrimaryKey = assetPrimaryKey;
	}

	private String _assetId;
	private String _assetPrimaryKey;
	private final Set<String> _urls = new HashSet<>();

}