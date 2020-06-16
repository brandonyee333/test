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
public class ZendeskArticleAttachmentSoap implements Serializable {
	public static ZendeskArticleAttachmentSoap toSoapModel(
		ZendeskArticleAttachment model) {
		ZendeskArticleAttachmentSoap soapModel = new ZendeskArticleAttachmentSoap();

		soapModel.setZendeskArticleAttachmentId(model.getZendeskArticleAttachmentId());
		soapModel.setZendeskArticleId(model.getZendeskArticleId());
		soapModel.setFilePath(model.getFilePath());
		soapModel.setChecksum(model.getChecksum());
		soapModel.setRemoteId(model.getRemoteId());
		soapModel.setRemoteContentURL(model.getRemoteContentURL());

		return soapModel;
	}

	public static ZendeskArticleAttachmentSoap[] toSoapModels(
		ZendeskArticleAttachment[] models) {
		ZendeskArticleAttachmentSoap[] soapModels = new ZendeskArticleAttachmentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ZendeskArticleAttachmentSoap[][] toSoapModels(
		ZendeskArticleAttachment[][] models) {
		ZendeskArticleAttachmentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ZendeskArticleAttachmentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ZendeskArticleAttachmentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ZendeskArticleAttachmentSoap[] toSoapModels(
		List<ZendeskArticleAttachment> models) {
		List<ZendeskArticleAttachmentSoap> soapModels = new ArrayList<ZendeskArticleAttachmentSoap>(models.size());

		for (ZendeskArticleAttachment model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ZendeskArticleAttachmentSoap[soapModels.size()]);
	}

	public ZendeskArticleAttachmentSoap() {
	}

	public long getPrimaryKey() {
		return _zendeskArticleAttachmentId;
	}

	public void setPrimaryKey(long pk) {
		setZendeskArticleAttachmentId(pk);
	}

	public long getZendeskArticleAttachmentId() {
		return _zendeskArticleAttachmentId;
	}

	public void setZendeskArticleAttachmentId(long zendeskArticleAttachmentId) {
		_zendeskArticleAttachmentId = zendeskArticleAttachmentId;
	}

	public long getZendeskArticleId() {
		return _zendeskArticleId;
	}

	public void setZendeskArticleId(long zendeskArticleId) {
		_zendeskArticleId = zendeskArticleId;
	}

	public String getFilePath() {
		return _filePath;
	}

	public void setFilePath(String filePath) {
		_filePath = filePath;
	}

	public String getChecksum() {
		return _checksum;
	}

	public void setChecksum(String checksum) {
		_checksum = checksum;
	}

	public long getRemoteId() {
		return _remoteId;
	}

	public void setRemoteId(long remoteId) {
		_remoteId = remoteId;
	}

	public String getRemoteContentURL() {
		return _remoteContentURL;
	}

	public void setRemoteContentURL(String remoteContentURL) {
		_remoteContentURL = remoteContentURL;
	}

	private long _zendeskArticleAttachmentId;
	private long _zendeskArticleId;
	private String _filePath;
	private String _checksum;
	private long _remoteId;
	private String _remoteContentURL;
}