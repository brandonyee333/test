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

import java.util.Date;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * @author Inácio Nery
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
	defaultImpl = FormField.class, include = JsonTypeInfo.As.EXISTING_PROPERTY,
	property = "messageFormat", use = JsonTypeInfo.Id.NAME, visible = true
)
public class FormField implements Serializable {

	public void addInteractionsDuration(long interactionsDuration) {
		_interactionsDuration += interactionsDuration;
	}

	public void addRefilled(long refilled) {
		_refilled += refilled;
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

	public long getInteractions() {
		return _interactions;
	}

	public long getInteractionsDuration() {
		return _interactionsDuration;
	}

	public String getName() {
		return _name;
	}

	public long getRefilled() {
		return _refilled;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public NavigableSet<Date> getViewDates() {
		return _viewDates;
	}

	public void setAbandonments(long abandonments) {
		_abandonments = abandonments;
	}

	public void setInteractions(long interactions) {
		_interactions = interactions;
	}

	public void setInteractionsDuration(long interactionsDuration) {
		_interactionsDuration = interactionsDuration;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setRefilled(long refilled) {
		_refilled = refilled;
	}

	public void setViewDates(NavigableSet<Date> viewDates) {
		_viewDates = viewDates;
	}

	private long _abandonments;
	private long _interactions;
	private long _interactionsDuration;
	private String _name;
	private long _refilled;
	private NavigableSet<Date> _viewDates = new TreeSet<>();

}