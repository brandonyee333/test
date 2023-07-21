/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.customer.admin.service.http.AccountAttachmentServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AccountAttachmentSoap implements Serializable {

	public static AccountAttachmentSoap toSoapModel(AccountAttachment model) {
		AccountAttachmentSoap soapModel = new AccountAttachmentSoap();

		soapModel.setAccountAttachmentId(model.getAccountAttachmentId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setAccountEntryId(model.getAccountEntryId());
		soapModel.setAccountProjectId(model.getAccountProjectId());
		soapModel.setFileName(model.getFileName());
		soapModel.setFileSize(model.getFileSize());
		soapModel.setType(model.getType());

		return soapModel;
	}

	public static AccountAttachmentSoap[] toSoapModels(
		AccountAttachment[] models) {

		AccountAttachmentSoap[] soapModels =
			new AccountAttachmentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AccountAttachmentSoap[][] toSoapModels(
		AccountAttachment[][] models) {

		AccountAttachmentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new AccountAttachmentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AccountAttachmentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AccountAttachmentSoap[] toSoapModels(
		List<AccountAttachment> models) {

		List<AccountAttachmentSoap> soapModels =
			new ArrayList<AccountAttachmentSoap>(models.size());

		for (AccountAttachment model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AccountAttachmentSoap[soapModels.size()]);
	}

	public AccountAttachmentSoap() {
	}

	public long getPrimaryKey() {
		return _accountAttachmentId;
	}

	public void setPrimaryKey(long pk) {
		setAccountAttachmentId(pk);
	}

	public long getAccountAttachmentId() {
		return _accountAttachmentId;
	}

	public void setAccountAttachmentId(long accountAttachmentId) {
		_accountAttachmentId = accountAttachmentId;
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

	public long getAccountEntryId() {
		return _accountEntryId;
	}

	public void setAccountEntryId(long accountEntryId) {
		_accountEntryId = accountEntryId;
	}

	public long getAccountProjectId() {
		return _accountProjectId;
	}

	public void setAccountProjectId(long accountProjectId) {
		_accountProjectId = accountProjectId;
	}

	public String getFileName() {
		return _fileName;
	}

	public void setFileName(String fileName) {
		_fileName = fileName;
	}

	public long getFileSize() {
		return _fileSize;
	}

	public void setFileSize(long fileSize) {
		_fileSize = fileSize;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	private long _accountAttachmentId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _accountEntryId;
	private long _accountProjectId;
	private String _fileName;
	private long _fileSize;
	private int _type;

}