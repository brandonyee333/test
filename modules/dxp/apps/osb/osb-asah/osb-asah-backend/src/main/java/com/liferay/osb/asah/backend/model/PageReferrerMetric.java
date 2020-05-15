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

import java.util.Objects;

/**
 * @author Leonardo Barros
 */
public class PageReferrerMetric {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PageReferrerMetric)) {
			return false;
		}

		PageReferrerMetric pageReferrerMetric = (PageReferrerMetric)obj;

		if (Objects.equals(_accessMetric, pageReferrerMetric._accessMetric) &&
			Objects.equals(_assetTitle, pageReferrerMetric._assetTitle) &&
			Objects.equals(_external, pageReferrerMetric._external) &&
			Objects.equals(_referrer, pageReferrerMetric._referrer)) {

			return true;
		}

		return false;
	}

	public Metric getAccessMetric() {
		return _accessMetric;
	}

	public String getAssetTitle() {
		return _assetTitle;
	}

	public boolean getExternal() {
		return _external;
	}

	public String getReferrer() {
		return _referrer;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_accessMetric, _assetTitle, _external, _referrer);
	}

	public boolean isExternal() {
		return _external;
	}

	public void setAccessMetric(Metric accessMetric) {
		_accessMetric = accessMetric;
	}

	public void setAssetTitle(String assetTitle) {
		_assetTitle = assetTitle;
	}

	public void setExternal(boolean external) {
		_external = external;
	}

	public void setReferrer(String referrer) {
		_referrer = referrer;
	}

	private Metric _accessMetric = new Metric(PageReferrerMetricType.ACCESS);
	private String _assetTitle;
	private boolean _external;
	private String _referrer;

}