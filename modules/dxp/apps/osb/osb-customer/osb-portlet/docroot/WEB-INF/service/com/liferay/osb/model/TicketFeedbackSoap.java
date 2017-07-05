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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.TicketFeedbackServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.http.TicketFeedbackServiceSoap
 * @generated
 */
@ProviderType
public class TicketFeedbackSoap implements Serializable {
	public static TicketFeedbackSoap toSoapModel(TicketFeedback model) {
		TicketFeedbackSoap soapModel = new TicketFeedbackSoap();

		soapModel.setTicketFeedbackId(model.getTicketFeedbackId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAccountEntryId(model.getAccountEntryId());
		soapModel.setTicketEntryId(model.getTicketEntryId());
		soapModel.setSubject(model.getSubject());
		soapModel.setSatisfied(model.getSatisfied());
		soapModel.setAnswer1(model.getAnswer1());
		soapModel.setAnswer2(model.getAnswer2());
		soapModel.setAnswer3(model.getAnswer3());
		soapModel.setRating1(model.getRating1());
		soapModel.setRating2(model.getRating2());
		soapModel.setRating3(model.getRating3());
		soapModel.setRating4(model.getRating4());
		soapModel.setComments(model.getComments());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static TicketFeedbackSoap[] toSoapModels(TicketFeedback[] models) {
		TicketFeedbackSoap[] soapModels = new TicketFeedbackSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TicketFeedbackSoap[][] toSoapModels(TicketFeedback[][] models) {
		TicketFeedbackSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TicketFeedbackSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TicketFeedbackSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TicketFeedbackSoap[] toSoapModels(List<TicketFeedback> models) {
		List<TicketFeedbackSoap> soapModels = new ArrayList<TicketFeedbackSoap>(models.size());

		for (TicketFeedback model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TicketFeedbackSoap[soapModels.size()]);
	}

	public TicketFeedbackSoap() {
	}

	public long getPrimaryKey() {
		return _ticketFeedbackId;
	}

	public void setPrimaryKey(long pk) {
		setTicketFeedbackId(pk);
	}

	public long getTicketFeedbackId() {
		return _ticketFeedbackId;
	}

	public void setTicketFeedbackId(long ticketFeedbackId) {
		_ticketFeedbackId = ticketFeedbackId;
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

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getAccountEntryId() {
		return _accountEntryId;
	}

	public void setAccountEntryId(long accountEntryId) {
		_accountEntryId = accountEntryId;
	}

	public long getTicketEntryId() {
		return _ticketEntryId;
	}

	public void setTicketEntryId(long ticketEntryId) {
		_ticketEntryId = ticketEntryId;
	}

	public int getSubject() {
		return _subject;
	}

	public void setSubject(int subject) {
		_subject = subject;
	}

	public int getSatisfied() {
		return _satisfied;
	}

	public void setSatisfied(int satisfied) {
		_satisfied = satisfied;
	}

	public int getAnswer1() {
		return _answer1;
	}

	public void setAnswer1(int answer1) {
		_answer1 = answer1;
	}

	public int getAnswer2() {
		return _answer2;
	}

	public void setAnswer2(int answer2) {
		_answer2 = answer2;
	}

	public int getAnswer3() {
		return _answer3;
	}

	public void setAnswer3(int answer3) {
		_answer3 = answer3;
	}

	public int getRating1() {
		return _rating1;
	}

	public void setRating1(int rating1) {
		_rating1 = rating1;
	}

	public int getRating2() {
		return _rating2;
	}

	public void setRating2(int rating2) {
		_rating2 = rating2;
	}

	public int getRating3() {
		return _rating3;
	}

	public void setRating3(int rating3) {
		_rating3 = rating3;
	}

	public int getRating4() {
		return _rating4;
	}

	public void setRating4(int rating4) {
		_rating4 = rating4;
	}

	public String getComments() {
		return _comments;
	}

	public void setComments(String comments) {
		_comments = comments;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _ticketFeedbackId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _accountEntryId;
	private long _ticketEntryId;
	private int _subject;
	private int _satisfied;
	private int _answer1;
	private int _answer2;
	private int _answer3;
	private int _rating1;
	private int _rating2;
	private int _rating3;
	private int _rating4;
	private String _comments;
	private int _status;
}