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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.AssetAttachmentServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.AssetAttachmentServiceSoap
 * @generated
 */
public class AssetAttachmentSoap implements Serializable {
	public static AssetAttachmentSoap toSoapModel(AssetAttachment model) {
		AssetAttachmentSoap soapModel = new AssetAttachmentSoap();

		soapModel.setAssetAttachmentId(model.getAssetAttachmentId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setFileName(model.getFileName());
		soapModel.setType(model.getType());
		soapModel.setRank(model.getRank());

		return soapModel;
	}

	public static AssetAttachmentSoap[] toSoapModels(AssetAttachment[] models) {
		AssetAttachmentSoap[] soapModels = new AssetAttachmentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AssetAttachmentSoap[][] toSoapModels(
		AssetAttachment[][] models) {
		AssetAttachmentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AssetAttachmentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AssetAttachmentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AssetAttachmentSoap[] toSoapModels(
		List<AssetAttachment> models) {
		List<AssetAttachmentSoap> soapModels = new ArrayList<AssetAttachmentSoap>(models.size());

		for (AssetAttachment model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AssetAttachmentSoap[soapModels.size()]);
	}

	public AssetAttachmentSoap() {
	}

	public long getPrimaryKey() {
		return _assetAttachmentId;
	}

	public void setPrimaryKey(long pk) {
		setAssetAttachmentId(pk);
	}

	public long getAssetAttachmentId() {
		return _assetAttachmentId;
	}

	public void setAssetAttachmentId(long assetAttachmentId) {
		_assetAttachmentId = assetAttachmentId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
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

	public String getFileName() {
		return _fileName;
	}

	public void setFileName(String fileName) {
		_fileName = fileName;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public int getRank() {
		return _rank;
	}

	public void setRank(int rank) {
		_rank = rank;
	}

	private long _assetAttachmentId;
	private long _userId;
	private Date _createDate;
	private long _classNameId;
	private long _classPK;
	private String _fileName;
	private int _type;
	private int _rank;
}