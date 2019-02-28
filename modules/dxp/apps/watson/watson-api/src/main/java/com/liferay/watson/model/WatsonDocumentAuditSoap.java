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

package com.liferay.watson.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Steven Smith
 * @generated
 */
@ProviderType
public class WatsonDocumentAuditSoap implements Serializable {

	public static WatsonDocumentAuditSoap toSoapModel(
		WatsonDocumentAudit model) {

		WatsonDocumentAuditSoap soapModel = new WatsonDocumentAuditSoap();

		soapModel.setWatsonDocumentAuditId(model.getWatsonDocumentAuditId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setParentTypeWatsonListTypeId(
			model.getParentTypeWatsonListTypeId());
		soapModel.setSubtypeWatsonListTypeId(
			model.getSubtypeWatsonListTypeId());
		soapModel.setTypeWatsonListTypeId(model.getTypeWatsonListTypeId());
		soapModel.setWatsonChildId(model.getWatsonChildId());
		soapModel.setWatsonDocumentId(model.getWatsonDocumentId());
		soapModel.setOriginalDocument(model.isOriginalDocument());
		soapModel.setReceivedDate(model.getReceivedDate());
		soapModel.setImagePayload(model.getImagePayload());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static WatsonDocumentAuditSoap[] toSoapModels(
		WatsonDocumentAudit[] models) {

		WatsonDocumentAuditSoap[] soapModels =
			new WatsonDocumentAuditSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WatsonDocumentAuditSoap[][] toSoapModels(
		WatsonDocumentAudit[][] models) {

		WatsonDocumentAuditSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new WatsonDocumentAuditSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WatsonDocumentAuditSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WatsonDocumentAuditSoap[] toSoapModels(
		List<WatsonDocumentAudit> models) {

		List<WatsonDocumentAuditSoap> soapModels =
			new ArrayList<WatsonDocumentAuditSoap>(models.size());

		for (WatsonDocumentAudit model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new WatsonDocumentAuditSoap[soapModels.size()]);
	}

	public WatsonDocumentAuditSoap() {
	}

	public long getPrimaryKey() {
		return _watsonDocumentAuditId;
	}

	public void setPrimaryKey(long pk) {
		setWatsonDocumentAuditId(pk);
	}

	public long getWatsonDocumentAuditId() {
		return _watsonDocumentAuditId;
	}

	public void setWatsonDocumentAuditId(long watsonDocumentAuditId) {
		_watsonDocumentAuditId = watsonDocumentAuditId;
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

	public long getParentTypeWatsonListTypeId() {
		return _parentTypeWatsonListTypeId;
	}

	public void setParentTypeWatsonListTypeId(long parentTypeWatsonListTypeId) {
		_parentTypeWatsonListTypeId = parentTypeWatsonListTypeId;
	}

	public long getSubtypeWatsonListTypeId() {
		return _subtypeWatsonListTypeId;
	}

	public void setSubtypeWatsonListTypeId(long subtypeWatsonListTypeId) {
		_subtypeWatsonListTypeId = subtypeWatsonListTypeId;
	}

	public long getTypeWatsonListTypeId() {
		return _typeWatsonListTypeId;
	}

	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_typeWatsonListTypeId = typeWatsonListTypeId;
	}

	public long getWatsonChildId() {
		return _watsonChildId;
	}

	public void setWatsonChildId(long watsonChildId) {
		_watsonChildId = watsonChildId;
	}

	public long getWatsonDocumentId() {
		return _watsonDocumentId;
	}

	public void setWatsonDocumentId(long watsonDocumentId) {
		_watsonDocumentId = watsonDocumentId;
	}

	public boolean getOriginalDocument() {
		return _originalDocument;
	}

	public boolean isOriginalDocument() {
		return _originalDocument;
	}

	public void setOriginalDocument(boolean originalDocument) {
		_originalDocument = originalDocument;
	}

	public Date getReceivedDate() {
		return _receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		_receivedDate = receivedDate;
	}

	public String getImagePayload() {
		return _imagePayload;
	}

	public void setImagePayload(String imagePayload) {
		_imagePayload = imagePayload;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _watsonDocumentAuditId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _parentTypeWatsonListTypeId;
	private long _subtypeWatsonListTypeId;
	private long _typeWatsonListTypeId;
	private long _watsonChildId;
	private long _watsonDocumentId;
	private boolean _originalDocument;
	private Date _receivedDate;
	private String _imagePayload;
	private int _status;

}