/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.FeedbackEntryServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.FeedbackEntryServiceSoap
 * @generated
 */
@ProviderType
public class FeedbackEntrySoap implements Serializable {
	public static FeedbackEntrySoap toSoapModel(FeedbackEntry model) {
		FeedbackEntrySoap soapModel = new FeedbackEntrySoap();

		soapModel.setFeedbackEntryId(model.getFeedbackEntryId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setSatisfied(model.getSatisfied());
		soapModel.setComments(model.getComments());
		soapModel.setPageURL(model.getPageURL());

		return soapModel;
	}

	public static FeedbackEntrySoap[] toSoapModels(FeedbackEntry[] models) {
		FeedbackEntrySoap[] soapModels = new FeedbackEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static FeedbackEntrySoap[][] toSoapModels(FeedbackEntry[][] models) {
		FeedbackEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new FeedbackEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new FeedbackEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static FeedbackEntrySoap[] toSoapModels(List<FeedbackEntry> models) {
		List<FeedbackEntrySoap> soapModels = new ArrayList<FeedbackEntrySoap>(models.size());

		for (FeedbackEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new FeedbackEntrySoap[soapModels.size()]);
	}

	public FeedbackEntrySoap() {
	}

	public long getPrimaryKey() {
		return _feedbackEntryId;
	}

	public void setPrimaryKey(long pk) {
		setFeedbackEntryId(pk);
	}

	public long getFeedbackEntryId() {
		return _feedbackEntryId;
	}

	public void setFeedbackEntryId(long feedbackEntryId) {
		_feedbackEntryId = feedbackEntryId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public int getSatisfied() {
		return _satisfied;
	}

	public void setSatisfied(int satisfied) {
		_satisfied = satisfied;
	}

	public String getComments() {
		return _comments;
	}

	public void setComments(String comments) {
		_comments = comments;
	}

	public String getPageURL() {
		return _pageURL;
	}

	public void setPageURL(String pageURL) {
		_pageURL = pageURL;
	}

	private long _feedbackEntryId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _classNameId;
	private long _classPK;
	private int _satisfied;
	private String _comments;
	private String _pageURL;
}