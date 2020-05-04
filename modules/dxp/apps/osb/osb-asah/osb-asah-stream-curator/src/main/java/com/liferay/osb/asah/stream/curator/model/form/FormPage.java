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

import java.io.Serializable;

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
	defaultImpl = FormPage.class, include = JsonTypeInfo.As.EXISTING_PROPERTY,
	property = "messageFormat", use = JsonTypeInfo.Id.NAME, visible = true
)
public class FormPage implements Serializable {

	public void addFormField(FormField formField) {
		_formFields.add(formField);
	}

	public void addViewDates(NavigableSet<Date> viewDates) {
		_viewDates.addAll(viewDates);
	}

	public void addViewsDate(Date viewsDate) {
		_viewDates.add(viewsDate);
	}

	public long getAbandonments() {
		return _abandonments;
	}

	public List<FormField> getFormFields() {
		return _formFields;
	}

	public String getName() {
		return _name;
	}

	public int getPageIndex() {
		return _pageIndex;
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

	public void setFormFields(List<FormField> formFields) {
		_formFields = formFields;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setPageIndex(int pageIndex) {
		_pageIndex = pageIndex;
	}

	public void setViewDates(NavigableSet<Date> viewDates) {
		_viewDates = viewDates;
	}

	public void setViews(long formViews) {
		_views = formViews;
	}

	private long _abandonments;
	private List<FormField> _formFields = new ArrayList<>();
	private String _name;
	private int _pageIndex;
	private NavigableSet<Date> _viewDates = new TreeSet<>();
	private long _views;

}