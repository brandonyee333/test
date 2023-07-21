/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.networking.model;

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
public class WallEntrySoap implements Serializable {

	public static WallEntrySoap toSoapModel(WallEntry model) {
		WallEntrySoap soapModel = new WallEntrySoap();

		soapModel.setWallEntryId(model.getWallEntryId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setComments(model.getComments());

		return soapModel;
	}

	public static WallEntrySoap[] toSoapModels(WallEntry[] models) {
		WallEntrySoap[] soapModels = new WallEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WallEntrySoap[][] toSoapModels(WallEntry[][] models) {
		WallEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WallEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new WallEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WallEntrySoap[] toSoapModels(List<WallEntry> models) {
		List<WallEntrySoap> soapModels = new ArrayList<WallEntrySoap>(
			models.size());

		for (WallEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WallEntrySoap[soapModels.size()]);
	}

	public WallEntrySoap() {
	}

	public long getPrimaryKey() {
		return _wallEntryId;
	}

	public void setPrimaryKey(long pk) {
		setWallEntryId(pk);
	}

	public long getWallEntryId() {
		return _wallEntryId;
	}

	public void setWallEntryId(long wallEntryId) {
		_wallEntryId = wallEntryId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
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

	public String getComments() {
		return _comments;
	}

	public void setComments(String comments) {
		_comments = comments;
	}

	private long _wallEntryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _comments;

}