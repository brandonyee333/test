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

import java.math.BigDecimal;

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

	public long getAvgTimeOnPage() {
		if ((_timeOnPage == null) || (getSessions() <= 0)) {
			return 0;
		}

		return _timeOnPage.longValue() / getSessions();
	}

	public String getCanonicalUrl() {
		return _canonicalUrl;
	}

	public double getExitRate() {
		if ((_exits == null) || (getSessions() <= 0)) {
			return 0;
		}

		return _exits.doubleValue() / getSessions();
	}

	public long getExits() {
		return _exits.longValue();
	}

	public long getTimeOnPage() {
		if (_timeOnPage == null) {
			return 0;
		}

		return _timeOnPage.longValue();
	}

	public String getTitle() {
		return _title;
	}

	public long getViews() {
		if (_views == null) {
			return 0;
		}

		return _views.longValue();
	}

	public void setCanonicalUrl(String canonicalUrl) {
		_canonicalUrl = canonicalUrl;
	}

	public void setExits(BigDecimal exits) {
		_exits = exits;
	}

	public void setTimeOnPage(BigDecimal timeOnPage) {
		_timeOnPage = timeOnPage;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public void setViews(BigDecimal views) {
		_views = views;
	}

	private String _canonicalUrl;
	private BigDecimal _exits;
	private BigDecimal _timeOnPage;
	private String _title;
	private BigDecimal _views;

}