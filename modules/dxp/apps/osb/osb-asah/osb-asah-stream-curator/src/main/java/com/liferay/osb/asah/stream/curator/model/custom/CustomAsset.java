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

package com.liferay.osb.asah.stream.curator.model.custom;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.stream.curator.model.BaseAssetModel;

import java.util.Date;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Marcellus Tavares
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
	defaultImpl = CustomAsset.class,
	include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "messageFormat",
	use = JsonTypeInfo.Id.NAME, visible = true
)
public class CustomAsset extends BaseAssetModel {

	public void addAbandonments(long abandonments) {
		_abandonments = abandonments;
	}

	public void addClicks(long clicks) {
		_clicks += clicks;
	}

	public void addDownloads(long downloads) {
		_downloads += downloads;
	}

	public void addSubmissionDate(Date submissionDate) {
		_submissionDates.add(submissionDate);
	}

	public void addSubmissionDates(NavigableSet<Date> submissionDates) {
		_submissionDates.addAll(submissionDates);
	}

	public void addViewDate(Date viewDate) {
		_viewDates.add(viewDate);
	}

	public void addViewDates(NavigableSet<Date> viewDates) {
		_viewDates.addAll(viewDates);
	}

	public long getAbandonments() {
		return _abandonments;
	}

	public String getCategory() {
		return _category;
	}

	public long getClicks() {
		return _clicks;
	}

	public long getDownloads() {
		return _downloads;
	}

	public String getElementText() {
		return _elementText;
	}

	public String getElementType() {
		return _elementType;
	}

	public String getElementURL() {
		return _elementURL;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getFirstEventDate() {
		if (_firstEventDate == null) {
			return null;
		}

		return new Date(_firstEventDate.getTime());
	}

	public int getReadPercentile() {
		return _readPercentile;
	}

	public long getReadTime() {
		return _readTime;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public NavigableSet<Date> getSubmissionDates() {
		return _submissionDates;
	}

	public long getSubmissions() {
		return _submissionDates.size();
	}

	public long getSubmissionsTime() {
		return _submissionsTime;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public NavigableSet<Date> getViewDates() {
		return _viewDates;
	}

	public long getViews() {
		return _viewDates.size();
	}

	@JsonIgnore
	public boolean isFormEnabled() {
		return _formEnabled;
	}

	public void setAbandonments(long abandonments) {
		_abandonments = abandonments;
	}

	public void setCategory(String category) {
		_category = category;
	}

	public void setClicks(long clicks) {
		_clicks = clicks;
	}

	public void setDownloads(long downloads) {
		_downloads = downloads;
	}

	public void setElement(Map<String, String> eventProperties) {
		String elementText = eventProperties.get("text");

		if (elementText == null) {
			elementText = "";
		}

		setElementText(elementText);

		String elementType = eventProperties.get("tagName");

		if (Objects.equals(elementType, "a")) {
			elementType = "link";
		}
		else if (Objects.equals(elementType, "img")) {
			elementType = "image";
		}
		else {
			elementType = "other";
		}

		setElementType(elementType);

		String elementURL = eventProperties.get("href");

		if (StringUtils.isEmpty(elementURL)) {
			elementURL = eventProperties.get("src");

			if (StringUtils.isEmpty(elementURL)) {
				elementURL = "";
			}
		}

		setElementURL(elementURL);
	}

	public void setElementText(String elementText) {
		_elementText = elementText;
	}

	public void setElementType(String elementType) {
		_elementType = elementType;
	}

	public void setElementURL(String elementURL) {
		_elementURL = elementURL;
	}

	public void setFirstEventDate(Date firstEventDate) {
		if (firstEventDate != null) {
			_firstEventDate = new Date(firstEventDate.getTime());
		}
	}

	public void setFormEnabled(boolean formEnabled) {
		_formEnabled = formEnabled;
	}

	public void setReadPercentile(int readPercentile) {
		_readPercentile = readPercentile;
	}

	public void setReadTime(long read) {
		_readTime = read;
	}

	public void setSubmissionsTime(long submissionsTime) {
		_submissionsTime = submissionsTime;
	}

	public void setViewDates(NavigableSet<Date> viewDates) {
		_viewDates = viewDates;
	}

	private long _abandonments;
	private String _category;
	private long _clicks;
	private long _downloads;
	private String _elementText;
	private String _elementType;
	private String _elementURL;
	private Date _firstEventDate;
	private boolean _formEnabled;
	private int _readPercentile;
	private long _readTime;
	private final NavigableSet<Date> _submissionDates = new TreeSet<>();
	private long _submissionsTime;
	private NavigableSet<Date> _viewDates = new TreeSet<>();

}