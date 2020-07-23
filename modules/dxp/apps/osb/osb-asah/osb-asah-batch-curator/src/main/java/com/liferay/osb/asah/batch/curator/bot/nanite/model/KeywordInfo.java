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

package com.liferay.osb.asah.batch.curator.bot.nanite.model;

/**
 * @author Gabriel Ibson
 * @author Geyson Silva
 */
public class KeywordInfo {

	public KeywordInfo(
		String canonicalUrl, String dataSourceAssetPK, String name) {

		_canonicalUrl = canonicalUrl;
		_dataSourceAssetPK = dataSourceAssetPK;
		_name = name;
	}

	public String getCanonicalUrl() {
		return _canonicalUrl;
	}

	public String getDataSourceAssetPK() {
		return _dataSourceAssetPK;
	}

	public String getName() {
		return _name;
	}

	private final String _canonicalUrl;
	private final String _dataSourceAssetPK;
	private final String _name;

}