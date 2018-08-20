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

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class ZendeskCategorySoap implements Serializable {
	public static ZendeskCategorySoap toSoapModel(ZendeskCategory model) {
		ZendeskCategorySoap soapModel = new ZendeskCategorySoap();

		soapModel.setZendeskCategoryId(model.getZendeskCategoryId());
		soapModel.setDocumentationKey(model.getDocumentationKey());
		soapModel.setRemoteId(model.getRemoteId());

		return soapModel;
	}

	public static ZendeskCategorySoap[] toSoapModels(ZendeskCategory[] models) {
		ZendeskCategorySoap[] soapModels = new ZendeskCategorySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ZendeskCategorySoap[][] toSoapModels(
		ZendeskCategory[][] models) {
		ZendeskCategorySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ZendeskCategorySoap[models.length][models[0].length];
		}
		else {
			soapModels = new ZendeskCategorySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ZendeskCategorySoap[] toSoapModels(
		List<ZendeskCategory> models) {
		List<ZendeskCategorySoap> soapModels = new ArrayList<ZendeskCategorySoap>(models.size());

		for (ZendeskCategory model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ZendeskCategorySoap[soapModels.size()]);
	}

	public ZendeskCategorySoap() {
	}

	public long getPrimaryKey() {
		return _zendeskCategoryId;
	}

	public void setPrimaryKey(long pk) {
		setZendeskCategoryId(pk);
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

	private long _zendeskCategoryId;
	private String _documentationKey;
	private long _remoteId;
}