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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import com.liferay.osb.asah.common.date.DateUtil;

import java.util.Date;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Marcellus Tavares
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
	defaultImpl = Page.class, include = JsonTypeInfo.As.EXISTING_PROPERTY,
	property = "messageFormat", use = JsonTypeInfo.Id.NAME, visible = true
)
public class Page extends BasePageModel {

	public void addCTAClicks(long ctaClicks) {
		_ctaClicks += ctaClicks;
	}

	public void addDirectAccessDate(Date directAccessDate) {
		_directAccessDates.add(directAccessDate);
	}

	public void addDirectAccessDates(NavigableSet<Date> directAccessDates) {
		_directAccessDates.addAll(directAccessDates);
	}

	public void addFormSubmission(long formSubmission) {
		_formSubmissions += formSubmission;
	}

	public void addIndirectAccessDate(Date indirectAccessDate) {
		_indirectAccessDates.add(indirectAccessDate);
	}

	public void addIndirectAccessDates(NavigableSet<Date> indirectAccessDates) {
		_indirectAccessDates.addAll(indirectAccessDates);
	}

	public void addInteractionDate(Date interactionDate) {
		_interactionDates.add(interactionDate);
	}

	public void addInteractionDates(NavigableSet<Date> interactionDates) {
		_interactionDates.addAll(interactionDates);
	}

	public void addPageScroll(PageScroll pageScroll) {
		_pageScrolls.add(pageScroll);
	}

	public void addPageScrolls(Set<PageScroll> pageScrolls) {
		_pageScrolls.addAll(pageScrolls);
	}

	public long getBounce() {
		return _bounce;
	}

	@JsonProperty("ctaClicks")
	public long getCTAClicks() {
		return _ctaClicks;
	}

	public long getDirectAccess() {
		return _directAccess;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public NavigableSet<Date> getDirectAccessDates() {
		return _directAccessDates;
	}

	public double getEngagementScore() {
		return _engagementScore;
	}

	public long getEntrances() {
		return _entrances;
	}

	public long getExits() {
		return _exits;
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

	public long getFormSubmissions() {
		return _formSubmissions;
	}

	public long getIndirectAccess() {
		return _indirectAccess;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public NavigableSet<Date> getIndirectAccessDates() {
		return _indirectAccessDates;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public NavigableSet<Date> getInteractionDates() {
		return _interactionDates;
	}

	public Set<PageScroll> getPageScrolls() {
		return _pageScrolls;
	}

	public String getSearchTerm() {
		return _searchTerm;
	}

	public long getTimeOnPage() {
		return _timeOnPage;
	}

	public long getViews() {
		return _views;
	}

	public void setBounce(long bounce) {
		_bounce = bounce;
	}

	public void setCtaClicks(long ctaClicks) {
		_ctaClicks = ctaClicks;
	}

	public void setDirectAccess(long directAccess) {
		_directAccess = directAccess;
	}

	public void setDirectAccessDates(NavigableSet<Date> directAccessDates) {
		_directAccessDates = directAccessDates;
	}

	public void setEngagementScore(double enagementScore) {
		_engagementScore = enagementScore;
	}

	public void setEntrances(long entrances) {
		_entrances = entrances;
	}

	public void setExits(long exits) {
		_exits = exits;
	}

	public void setFirstEventDate(Date firstEventDate) {
		if (firstEventDate != null) {
			_firstEventDate = new Date(firstEventDate.getTime());
		}
	}

	public void setFormSubmissions(long formSubmissions) {
		_formSubmissions = formSubmissions;
	}

	public void setIndirectAccess(long indirectAccess) {
		_indirectAccess = indirectAccess;
	}

	public void setIndirectAccessDates(NavigableSet<Date> indirectAccessDates) {
		_indirectAccessDates = indirectAccessDates;
	}

	public void setInteractionDates(NavigableSet<Date> interactionDates) {
		_interactionDates = interactionDates;
	}

	public void setPageScrolls(Set<PageScroll> pageScrolls) {
		_pageScrolls = pageScrolls;
	}

	public void setSearchTerm(String searchTerm) {
		_searchTerm = searchTerm;
	}

	public void setTimeOnPage(long timeOnPage) {
		_timeOnPage = timeOnPage;
	}

	public void setViews(long views) {
		_views = views;
	}

	private long _bounce;
	private long _ctaClicks;
	private long _directAccess;
	private NavigableSet<Date> _directAccessDates = new TreeSet<>();
	private double _engagementScore;
	private long _entrances;
	private long _exits;
	private Date _firstEventDate;
	private long _formSubmissions;
	private long _indirectAccess;
	private NavigableSet<Date> _indirectAccessDates = new TreeSet<>();
	private NavigableSet<Date> _interactionDates = new TreeSet<>();
	private Set<PageScroll> _pageScrolls = new TreeSet<>();
	private String _searchTerm;
	private long _timeOnPage;
	private long _views;

}