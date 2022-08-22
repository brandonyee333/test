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

package com.liferay.osb.asah.common.model;

import com.liferay.osb.asah.common.util.BeanUtils;

import java.util.Map;

/**
 * @author Leslie Wong
 */
public class PageVisitorBehaviorMetric extends SiteVisitorBehaviorMetric {

	public PageVisitorBehaviorMetric() {
	}

	public PageVisitorBehaviorMetric(Map<String, Object> source) {
		super(source);

		BeanUtils.copyProperties(source, this);
	}

	public String getCanonicalUrl() {
		return _canonicalUrl;
	}

	public String getTitle() {
		return _title;
	}

	public void setCanonicalUrl(String canonicalUrl) {
		_canonicalUrl = canonicalUrl;
	}

	public void setTitle(String title) {
		_title = title;
	}

	private String _canonicalUrl;
	private String _title;

}