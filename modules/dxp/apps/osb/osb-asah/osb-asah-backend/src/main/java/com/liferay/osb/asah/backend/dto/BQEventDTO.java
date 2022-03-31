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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.BQEvent;
import com.liferay.osb.asah.common.graphql.GraphQLType;

import java.util.Date;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
@GraphQLType("Event")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BQEventDTO {

	public BQEventDTO(BQEvent bqEvent, String name) {
		_name = name;

		_createDate = bqEvent.getCreateDate();
		_canonicalUrl = bqEvent.getCanonicalUrl();
		_pageDescription = bqEvent.getDescription();
		_pageKeywords = bqEvent.getKeywords();
		_pageTitle = bqEvent.getTitle();
		_referrer = bqEvent.getReferrer();
		_url = bqEvent.getUrl();
	}

	public String getCanonicalUrl() {
		return _canonicalUrl;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getCreateDate() {
		if (_createDate == null) {
			return null;
		}

		return new Date(_createDate.getTime());
	}

	public String getName() {
		return _name;
	}

	public String getPageDescription() {
		return _pageDescription;
	}

	public String getPageKeywords() {
		return _pageKeywords;
	}

	public String getPageTitle() {
		return _pageTitle;
	}

	public String getReferrer() {
		return _referrer;
	}

	public String getUrl() {
		return _url;
	}

	private final String _canonicalUrl;
	private final Date _createDate;
	private final String _name;
	private final String _pageDescription;
	private final String _pageKeywords;
	private final String _pageTitle;
	private final String _referrer;
	private final String _url;

}