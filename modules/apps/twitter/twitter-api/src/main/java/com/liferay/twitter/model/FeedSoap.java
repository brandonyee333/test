/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.twitter.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class FeedSoap implements Serializable {

	public static FeedSoap toSoapModel(Feed model) {
		FeedSoap soapModel = new FeedSoap();

		soapModel.setFeedId(model.getFeedId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTwitterUserId(model.getTwitterUserId());
		soapModel.setTwitterScreenName(model.getTwitterScreenName());
		soapModel.setLastStatusId(model.getLastStatusId());

		return soapModel;
	}

	public static FeedSoap[] toSoapModels(Feed[] models) {
		FeedSoap[] soapModels = new FeedSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static FeedSoap[][] toSoapModels(Feed[][] models) {
		FeedSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new FeedSoap[models.length][models[0].length];
		}
		else {
			soapModels = new FeedSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static FeedSoap[] toSoapModels(List<Feed> models) {
		List<FeedSoap> soapModels = new ArrayList<FeedSoap>(models.size());

		for (Feed model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new FeedSoap[soapModels.size()]);
	}

	public FeedSoap() {
	}

	public long getPrimaryKey() {
		return _feedId;
	}

	public void setPrimaryKey(long pk) {
		setFeedId(pk);
	}

	public long getFeedId() {
		return _feedId;
	}

	public void setFeedId(long feedId) {
		_feedId = feedId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
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

	public long getTwitterUserId() {
		return _twitterUserId;
	}

	public void setTwitterUserId(long twitterUserId) {
		_twitterUserId = twitterUserId;
	}

	public String getTwitterScreenName() {
		return _twitterScreenName;
	}

	public void setTwitterScreenName(String twitterScreenName) {
		_twitterScreenName = twitterScreenName;
	}

	public long getLastStatusId() {
		return _lastStatusId;
	}

	public void setLastStatusId(long lastStatusId) {
		_lastStatusId = lastStatusId;
	}

	private long _feedId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _twitterUserId;
	private String _twitterScreenName;
	private long _lastStatusId;

}