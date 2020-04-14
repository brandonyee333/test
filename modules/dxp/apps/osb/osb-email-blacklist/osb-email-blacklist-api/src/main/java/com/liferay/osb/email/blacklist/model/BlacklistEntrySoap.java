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

package com.liferay.osb.email.blacklist.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Jamie Sammons
 * @generated
 */
public class BlacklistEntrySoap implements Serializable {

	public static BlacklistEntrySoap toSoapModel(BlacklistEntry model) {
		BlacklistEntrySoap soapModel = new BlacklistEntrySoap();

		soapModel.setBlacklistEntryId(model.getBlacklistEntryId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setEmailAddress(model.getEmailAddress());

		return soapModel;
	}

	public static BlacklistEntrySoap[] toSoapModels(BlacklistEntry[] models) {
		BlacklistEntrySoap[] soapModels = new BlacklistEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static BlacklistEntrySoap[][] toSoapModels(
		BlacklistEntry[][] models) {

		BlacklistEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new BlacklistEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new BlacklistEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static BlacklistEntrySoap[] toSoapModels(
		List<BlacklistEntry> models) {

		List<BlacklistEntrySoap> soapModels = new ArrayList<BlacklistEntrySoap>(
			models.size());

		for (BlacklistEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new BlacklistEntrySoap[soapModels.size()]);
	}

	public BlacklistEntrySoap() {
	}

	public long getPrimaryKey() {
		return _blacklistEntryId;
	}

	public void setPrimaryKey(long pk) {
		setBlacklistEntryId(pk);
	}

	public long getBlacklistEntryId() {
		return _blacklistEntryId;
	}

	public void setBlacklistEntryId(long blacklistEntryId) {
		_blacklistEntryId = blacklistEntryId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	private long _blacklistEntryId;
	private Date _createDate;
	private String _emailAddress;

}