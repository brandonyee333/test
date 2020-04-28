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

package com.liferay.osb.loop.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Ethan Bustad
 * @generated
 */
public class LoopTopicSoap implements Serializable {

	public static LoopTopicSoap toSoapModel(LoopTopic model) {
		LoopTopicSoap soapModel = new LoopTopicSoap();

		soapModel.setLoopTopicId(model.getLoopTopicId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setParentLoopTopicId(model.getParentLoopTopicId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setImagePayload(model.getImagePayload());
		soapModel.setMergeTime(model.getMergeTime());

		return soapModel;
	}

	public static LoopTopicSoap[] toSoapModels(LoopTopic[] models) {
		LoopTopicSoap[] soapModels = new LoopTopicSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LoopTopicSoap[][] toSoapModels(LoopTopic[][] models) {
		LoopTopicSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LoopTopicSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LoopTopicSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LoopTopicSoap[] toSoapModels(List<LoopTopic> models) {
		List<LoopTopicSoap> soapModels = new ArrayList<LoopTopicSoap>(
			models.size());

		for (LoopTopic model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LoopTopicSoap[soapModels.size()]);
	}

	public LoopTopicSoap() {
	}

	public long getPrimaryKey() {
		return _loopTopicId;
	}

	public void setPrimaryKey(long pk) {
		setLoopTopicId(pk);
	}

	public long getLoopTopicId() {
		return _loopTopicId;
	}

	public void setLoopTopicId(long loopTopicId) {
		_loopTopicId = loopTopicId;
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

	public long getParentLoopTopicId() {
		return _parentLoopTopicId;
	}

	public void setParentLoopTopicId(long parentLoopTopicId) {
		_parentLoopTopicId = parentLoopTopicId;
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

	public String getImagePayload() {
		return _imagePayload;
	}

	public void setImagePayload(String imagePayload) {
		_imagePayload = imagePayload;
	}

	public long getMergeTime() {
		return _mergeTime;
	}

	public void setMergeTime(long mergeTime) {
		_mergeTime = mergeTime;
	}

	private long _loopTopicId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _parentLoopTopicId;
	private String _name;
	private String _description;
	private String _imagePayload;
	private long _mergeTime;

}