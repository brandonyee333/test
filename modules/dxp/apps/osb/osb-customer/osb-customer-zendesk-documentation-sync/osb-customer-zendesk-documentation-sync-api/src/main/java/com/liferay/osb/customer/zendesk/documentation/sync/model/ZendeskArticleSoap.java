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
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class ZendeskArticleSoap implements Serializable {
	public static ZendeskArticleSoap toSoapModel(ZendeskArticle model) {
		ZendeskArticleSoap soapModel = new ZendeskArticleSoap();

		soapModel.setZendeskArticleId(model.getZendeskArticleId());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setZendeskSectionId(model.getZendeskSectionId());
		soapModel.setDocumentationKey(model.getDocumentationKey());
		soapModel.setRemoteId(model.getRemoteId());
		soapModel.setRemoteHtmlURL(model.getRemoteHtmlURL());

		return soapModel;
	}

	public static ZendeskArticleSoap[] toSoapModels(ZendeskArticle[] models) {
		ZendeskArticleSoap[] soapModels = new ZendeskArticleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ZendeskArticleSoap[][] toSoapModels(ZendeskArticle[][] models) {
		ZendeskArticleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ZendeskArticleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ZendeskArticleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ZendeskArticleSoap[] toSoapModels(List<ZendeskArticle> models) {
		List<ZendeskArticleSoap> soapModels = new ArrayList<ZendeskArticleSoap>(models.size());

		for (ZendeskArticle model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ZendeskArticleSoap[soapModels.size()]);
	}

	public ZendeskArticleSoap() {
	}

	public long getPrimaryKey() {
		return _zendeskArticleId;
	}

	public void setPrimaryKey(long pk) {
		setZendeskArticleId(pk);
	}

	public long getZendeskArticleId() {
		return _zendeskArticleId;
	}

	public void setZendeskArticleId(long zendeskArticleId) {
		_zendeskArticleId = zendeskArticleId;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getZendeskSectionId() {
		return _zendeskSectionId;
	}

	public void setZendeskSectionId(long zendeskSectionId) {
		_zendeskSectionId = zendeskSectionId;
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

	private long _zendeskArticleId;
	private Date _modifiedDate;
	private long _zendeskSectionId;
	private String _documentationKey;
	private long _remoteId;
	private String _remoteHtmlURL;
}