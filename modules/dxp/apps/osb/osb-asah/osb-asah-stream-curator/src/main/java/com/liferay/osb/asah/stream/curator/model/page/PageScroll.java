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

import java.io.Serializable;

import java.util.Date;
import java.util.Objects;

/**
 * @author Marcellus Tavares
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
	defaultImpl = PageScroll.class, include = JsonTypeInfo.As.EXISTING_PROPERTY,
	property = "messageFormat", use = JsonTypeInfo.Id.NAME, visible = true
)
public class PageScroll implements Comparable<PageScroll>, Serializable {

	public PageScroll() {
	}

	public PageScroll(int depth, Date eventDate) {
		_depth = depth;

		if (eventDate != null) {
			_eventDate = new Date(eventDate.getTime());
		}
	}

	@Override
	public int compareTo(PageScroll pageScroll) {
		return _eventDate.compareTo(pageScroll._eventDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PageScroll)) {
			return false;
		}

		PageScroll pageScroll = (PageScroll)obj;

		if (Objects.equals(_depth, pageScroll._depth) &&
			Objects.equals(_eventDate, pageScroll._eventDate)) {

			return true;
		}

		return false;
	}

	public int getDepth() {
		return _depth;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getEventDate() {
		if (_eventDate == null) {
			return null;
		}

		return new Date(_eventDate.getTime());
	}

	@Override
	public int hashCode() {
		return Objects.hash(_depth, _eventDate);
	}

	public void setDepth(int depth) {
		_depth = depth;
	}

	public void setEventDate(Date eventDate) {
		if (eventDate != null) {
			_eventDate = new Date(eventDate.getTime());
		}
	}

	private int _depth;
	private Date _eventDate;

}