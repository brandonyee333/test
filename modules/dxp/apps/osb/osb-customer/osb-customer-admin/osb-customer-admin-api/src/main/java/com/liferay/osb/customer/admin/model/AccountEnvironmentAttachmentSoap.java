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

package com.liferay.osb.customer.admin.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.customer.admin.service.http.AccountEnvironmentAttachmentServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AccountEnvironmentAttachmentSoap implements Serializable {

	public static AccountEnvironmentAttachmentSoap toSoapModel(
		AccountEnvironmentAttachment model) {

		AccountEnvironmentAttachmentSoap soapModel =
			new AccountEnvironmentAttachmentSoap();

		soapModel.setAccountEnvironmentAttachmentId(
			model.getAccountEnvironmentAttachmentId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAccountEnvironmentId(model.getAccountEnvironmentId());
		soapModel.setFileName(model.getFileName());
		soapModel.setFileSize(model.getFileSize());
		soapModel.setType(model.getType());

		return soapModel;
	}

	public static AccountEnvironmentAttachmentSoap[] toSoapModels(
		AccountEnvironmentAttachment[] models) {

		AccountEnvironmentAttachmentSoap[] soapModels =
			new AccountEnvironmentAttachmentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AccountEnvironmentAttachmentSoap[][] toSoapModels(
		AccountEnvironmentAttachment[][] models) {

		AccountEnvironmentAttachmentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AccountEnvironmentAttachmentSoap
				[models.length][models[0].length];
		}
		else {
			soapModels = new AccountEnvironmentAttachmentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AccountEnvironmentAttachmentSoap[] toSoapModels(
		List<AccountEnvironmentAttachment> models) {

		List<AccountEnvironmentAttachmentSoap> soapModels =
			new ArrayList<AccountEnvironmentAttachmentSoap>(models.size());

		for (AccountEnvironmentAttachment model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new AccountEnvironmentAttachmentSoap[soapModels.size()]);
	}

	public AccountEnvironmentAttachmentSoap() {
	}

	public long getPrimaryKey() {
		return _accountEnvironmentAttachmentId;
	}

	public void setPrimaryKey(long pk) {
		setAccountEnvironmentAttachmentId(pk);
	}

	public long getAccountEnvironmentAttachmentId() {
		return _accountEnvironmentAttachmentId;
	}

	public void setAccountEnvironmentAttachmentId(
		long accountEnvironmentAttachmentId) {

		_accountEnvironmentAttachmentId = accountEnvironmentAttachmentId;
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

	public long getAccountEnvironmentId() {
		return _accountEnvironmentId;
	}

	public void setAccountEnvironmentId(long accountEnvironmentId) {
		_accountEnvironmentId = accountEnvironmentId;
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

	private long _accountEnvironmentAttachmentId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _accountEnvironmentId;
	private String _fileName;
	private long _fileSize;
	private int _type;

}