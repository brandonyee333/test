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

package com.liferay.osb.customer.zendesk.documentation.sync.model;

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
public class ZendeskSectionSoap implements Serializable {

	public static ZendeskSectionSoap toSoapModel(ZendeskSection model) {
		ZendeskSectionSoap soapModel = new ZendeskSectionSoap();

		soapModel.setZendeskSectionId(model.getZendeskSectionId());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setZendeskCategoryId(model.getZendeskCategoryId());
		soapModel.setDocumentationKey(model.getDocumentationKey());
		soapModel.setRemoteId(model.getRemoteId());
		soapModel.setRemoteHtmlURL(model.getRemoteHtmlURL());
		soapModel.setRemoteName(model.getRemoteName());

		return soapModel;
	}

	public static ZendeskSectionSoap[] toSoapModels(ZendeskSection[] models) {
		ZendeskSectionSoap[] soapModels = new ZendeskSectionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ZendeskSectionSoap[][] toSoapModels(
		ZendeskSection[][] models) {

		ZendeskSectionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new ZendeskSectionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ZendeskSectionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ZendeskSectionSoap[] toSoapModels(
		List<ZendeskSection> models) {

		List<ZendeskSectionSoap> soapModels = new ArrayList<ZendeskSectionSoap>(
			models.size());

		for (ZendeskSection model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ZendeskSectionSoap[soapModels.size()]);
	}

	public ZendeskSectionSoap() {
	}

	public long getPrimaryKey() {
		return _zendeskSectionId;
	}

	public void setPrimaryKey(long pk) {
		setZendeskSectionId(pk);
	}

	public long getZendeskSectionId() {
		return _zendeskSectionId;
	}

	public void setZendeskSectionId(long zendeskSectionId) {
		_zendeskSectionId = zendeskSectionId;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getZendeskCategoryId() {
		return _zendeskCategoryId;
	}

	public void setZendeskCategoryId(long zendeskCategoryId) {
		_zendeskCategoryId = zendeskCategoryId;
	}

	public String getDocumentationKey() {
		return _documentationKey;
	}

	public void setDocumentationKey(String documentationKey) {
		_documentationKey = documentationKey;
	}

	public long getRemoteId() {
		return _remoteId;
	}

	public void setRemoteId(long remoteId) {
		_remoteId = remoteId;
	}

	public String getRemoteHtmlURL() {
		return _remoteHtmlURL;
	}

	public void setRemoteHtmlURL(String remoteHtmlURL) {
		_remoteHtmlURL = remoteHtmlURL;
	}

	public String getRemoteName() {
		return _remoteName;
	}

	public void setRemoteName(String remoteName) {
		_remoteName = remoteName;
	}

	private long _zendeskSectionId;
	private Date _modifiedDate;
	private long _zendeskCategoryId;
	private String _documentationKey;
	private long _remoteId;
	private String _remoteHtmlURL;
	private String _remoteName;

}