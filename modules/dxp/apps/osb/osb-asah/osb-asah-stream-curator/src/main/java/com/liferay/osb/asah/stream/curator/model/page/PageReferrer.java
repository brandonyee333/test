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

package com.liferay.osb.asah.stream.curator.model.page;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import com.liferay.osb.asah.common.date.DateUtil;

import java.util.Date;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * @author Marcellus Tavares
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
	defaultImpl = PageReferrer.class,
	include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "messageFormat",
	use = JsonTypeInfo.Id.NAME, visible = true
)
public class PageReferrer extends BasePageModel {

	public void addAccessDate(Date accessDate) {
		_accessDates.add(accessDate);
	}

	public void addAccessDates(NavigableSet<Date> accessDates) {
		_accessDates.addAll(accessDates);
	}

	public long getAccess() {
		return _access;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public NavigableSet<Date> getAccessDates() {
		return _accessDates;
	}

	public String getReferrer() {
		return _referrer;
	}

	public void setAccess(long access) {
		_access = access;
	}

	public void setAccessDates(NavigableSet<Date> accessDates) {
		_accessDates = accessDates;
	}

	public void setReferrer(String referrer) {
		_referrer = referrer;
	}

	private long _access;
	private NavigableSet<Date> _accessDates = new TreeSet<>();
	private String _referrer;

}