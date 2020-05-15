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

package com.liferay.osb.asah.stream.curator.model.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.stream.curator.model.BaseAssetModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * @author Inácio Nery
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
	defaultImpl = Form.class, include = JsonTypeInfo.As.EXISTING_PROPERTY,
	property = "messageFormat", use = JsonTypeInfo.Id.NAME, visible = true
)
public class Form extends BaseAssetModel {

	public void addFormPage(FormPage formPage) {
		_formPages.add(formPage);
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

	public List<FormPage> getFormPages() {
		return _formPages;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public NavigableSet<Date> getSubmissionDates() {
		return _submissionDates;
	}

	public long getSubmissions() {
		return _submissions;
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
		return _views;
	}

	public void setAbandonments(long abandonments) {
		_abandonments = abandonments;
	}

	public void setFormPages(List<FormPage> formPages) {
		_formPages = formPages;
	}

	public void setSubmissionDates(NavigableSet<Date> submissionDates) {
		_submissionDates = submissionDates;
	}

	public void setSubmissions(long submissions) {
		_submissions = submissions;
	}

	public void setSubmissionsTime(long submissionsTime) {
		_submissionsTime = submissionsTime;
	}

	public void setViewDates(NavigableSet<Date> viewDates) {
		_viewDates = viewDates;
	}

	public void setViews(long formViews) {
		_views = formViews;
	}

	private long _abandonments;
	private List<FormPage> _formPages = new ArrayList<>();
	private NavigableSet<Date> _submissionDates = new TreeSet<>();
	private long _submissions;
	private long _submissionsTime;
	private NavigableSet<Date> _viewDates = new TreeSet<>();
	private long _views;

}