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

package com.liferay.osb.testray.model;

import java.io.Serializable;

import java.sql.Blob;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Ethan Bustad
 * @generated
 */
public class TestrayArchiveSoap implements Serializable {

	public static TestrayArchiveSoap toSoapModel(TestrayArchive model) {
		TestrayArchiveSoap soapModel = new TestrayArchiveSoap();

		soapModel.setTestrayArchiveId(model.getTestrayArchiveId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setCompressedData(model.getCompressedData());

		return soapModel;
	}

	public static TestrayArchiveSoap[] toSoapModels(TestrayArchive[] models) {
		TestrayArchiveSoap[] soapModels = new TestrayArchiveSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TestrayArchiveSoap[][] toSoapModels(
		TestrayArchive[][] models) {

		TestrayArchiveSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new TestrayArchiveSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TestrayArchiveSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TestrayArchiveSoap[] toSoapModels(
		List<TestrayArchive> models) {

		List<TestrayArchiveSoap> soapModels = new ArrayList<TestrayArchiveSoap>(
			models.size());

		for (TestrayArchive model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TestrayArchiveSoap[soapModels.size()]);
	}

	public TestrayArchiveSoap() {
	}

	public long getPrimaryKey() {
		return _testrayArchiveId;
	}

	public void setPrimaryKey(long pk) {
		setTestrayArchiveId(pk);
	}

	public long getTestrayArchiveId() {
		return _testrayArchiveId;
	}

	public void setTestrayArchiveId(long testrayArchiveId) {
		_testrayArchiveId = testrayArchiveId;
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

	public Blob getCompressedData() {
		return _compressedData;
	}

	public void setCompressedData(Blob compressedData) {
		_compressedData = compressedData;
	}

	private long _testrayArchiveId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _classNameId;
	private long _classPK;
	private Blob _compressedData;

}