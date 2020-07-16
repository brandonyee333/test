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

package com.liferay.osb.community.doc.project.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.community.doc.project.service.http.DocProjectServiceSoap}.
 *
 * @author Ryan Park
 * @generated
 */
public class DocProjectSoap implements Serializable {

	public static DocProjectSoap toSoapModel(DocProject model) {
		DocProjectSoap soapModel = new DocProjectSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDocProjectId(model.getDocProjectId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setIconFileName(model.getIconFileName());
		soapModel.setUnlisted(model.isUnlisted());
		soapModel.setType(model.getType());
		soapModel.setTypeSettings(model.getTypeSettings());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static DocProjectSoap[] toSoapModels(DocProject[] models) {
		DocProjectSoap[] soapModels = new DocProjectSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DocProjectSoap[][] toSoapModels(DocProject[][] models) {
		DocProjectSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DocProjectSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DocProjectSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DocProjectSoap[] toSoapModels(List<DocProject> models) {
		List<DocProjectSoap> soapModels = new ArrayList<DocProjectSoap>(
			models.size());

		for (DocProject model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DocProjectSoap[soapModels.size()]);
	}

	public DocProjectSoap() {
	}

	public long getPrimaryKey() {
		return _docProjectId;
	}

	public void setPrimaryKey(long pk) {
		setDocProjectId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getDocProjectId() {
		return _docProjectId;
	}

	public void setDocProjectId(long docProjectId) {
		_docProjectId = docProjectId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getIconFileName() {
		return _iconFileName;
	}

	public void setIconFileName(String iconFileName) {
		_iconFileName = iconFileName;
	}

	public boolean getUnlisted() {
		return _unlisted;
	}

	public boolean isUnlisted() {
		return _unlisted;
	}

	public void setUnlisted(boolean unlisted) {
		_unlisted = unlisted;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public String getTypeSettings() {
		return _typeSettings;
	}

	public void setTypeSettings(String typeSettings) {
		_typeSettings = typeSettings;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private String _uuid;
	private long _docProjectId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _description;
	private String _iconFileName;
	private boolean _unlisted;
	private String _type;
	private String _typeSettings;
	private int _status;

}