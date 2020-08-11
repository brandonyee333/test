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

package com.liferay.osb.asah.backend.model;

import java.util.List;

/**
 * @author Inácio Nery
 */
public interface AssetMetric {

	public String getAssetId();

	public List<AssetMetric> getAssetMetrics();

	public String getAssetTitle();

	public String getAssetType();

	public List<String> getCanonicalUrls();

	public String getDataSourceId();

	public Metric getDefaultMetric();

	public List<String> getURLs();

	public void setAssetId(String assetId);

	public void setAssetMetrics(List<AssetMetric> assetMetrics);

	public void setAssetTitle(String assetTitle);

	public void setCanonicalUrls(List<String> canonicalUrls);

	public void setDataSourceId(String dataSourceId);

	public void setURLs(List<String> urls);

}