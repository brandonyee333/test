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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ZendeskArticleAttachment}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskArticleAttachment
 * @generated
 */
@ProviderType
public class ZendeskArticleAttachmentWrapper implements ZendeskArticleAttachment,
	ModelWrapper<ZendeskArticleAttachment> {
	public ZendeskArticleAttachmentWrapper(
		ZendeskArticleAttachment zendeskArticleAttachment) {
		_zendeskArticleAttachment = zendeskArticleAttachment;
	}

	@Override
	public Class<?> getModelClass() {
		return ZendeskArticleAttachment.class;
	}

	@Override
	public String getModelClassName() {
		return ZendeskArticleAttachment.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("zendeskArticleAttachmentId",
			getZendeskArticleAttachmentId());
		attributes.put("zendeskArticleId", getZendeskArticleId());
		attributes.put("filePath", getFilePath());
		attributes.put("checksum", getChecksum());
		attributes.put("remoteId", getRemoteId());
		attributes.put("remoteContentURL", getRemoteContentURL());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long zendeskArticleAttachmentId = (Long)attributes.get(
				"zendeskArticleAttachmentId");

		if (zendeskArticleAttachmentId != null) {
			setZendeskArticleAttachmentId(zendeskArticleAttachmentId);
		}

		Long zendeskArticleId = (Long)attributes.get("zendeskArticleId");

		if (zendeskArticleId != null) {
			setZendeskArticleId(zendeskArticleId);
		}

		String filePath = (String)attributes.get("filePath");

		if (filePath != null) {
			setFilePath(filePath);
		}

		String checksum = (String)attributes.get("checksum");

		if (checksum != null) {
			setChecksum(checksum);
		}

		Long remoteId = (Long)attributes.get("remoteId");

		if (remoteId != null) {
			setRemoteId(remoteId);
		}

		String remoteContentURL = (String)attributes.get("remoteContentURL");

		if (remoteContentURL != null) {
			setRemoteContentURL(remoteContentURL);
		}
	}

	@Override
	public ZendeskArticleAttachment toEscapedModel() {
		return new ZendeskArticleAttachmentWrapper(_zendeskArticleAttachment.toEscapedModel());
	}

	@Override
	public ZendeskArticleAttachment toUnescapedModel() {
		return new ZendeskArticleAttachmentWrapper(_zendeskArticleAttachment.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _zendeskArticleAttachment.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _zendeskArticleAttachment.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _zendeskArticleAttachment.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _zendeskArticleAttachment.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ZendeskArticleAttachment> toCacheModel() {
		return _zendeskArticleAttachment.toCacheModel();
	}

	@Override
	public int compareTo(ZendeskArticleAttachment zendeskArticleAttachment) {
		return _zendeskArticleAttachment.compareTo(zendeskArticleAttachment);
	}

	@Override
	public int hashCode() {
		return _zendeskArticleAttachment.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _zendeskArticleAttachment.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ZendeskArticleAttachmentWrapper((ZendeskArticleAttachment)_zendeskArticleAttachment.clone());
	}

	/**
	* Returns the checksum of this zendesk article attachment.
	*
	* @return the checksum of this zendesk article attachment
	*/
	@Override
	public java.lang.String getChecksum() {
		return _zendeskArticleAttachment.getChecksum();
	}

	/**
	* Returns the file path of this zendesk article attachment.
	*
	* @return the file path of this zendesk article attachment
	*/
	@Override
	public java.lang.String getFilePath() {
		return _zendeskArticleAttachment.getFilePath();
	}

	/**
	* Returns the remote content url of this zendesk article attachment.
	*
	* @return the remote content url of this zendesk article attachment
	*/
	@Override
	public java.lang.String getRemoteContentURL() {
		return _zendeskArticleAttachment.getRemoteContentURL();
	}

	@Override
	public java.lang.String toString() {
		return _zendeskArticleAttachment.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _zendeskArticleAttachment.toXmlString();
	}

	/**
	* Returns the primary key of this zendesk article attachment.
	*
	* @return the primary key of this zendesk article attachment
	*/
	@Override
	public long getPrimaryKey() {
		return _zendeskArticleAttachment.getPrimaryKey();
	}

	/**
	* Returns the remote ID of this zendesk article attachment.
	*
	* @return the remote ID of this zendesk article attachment
	*/
	@Override
	public long getRemoteId() {
		return _zendeskArticleAttachment.getRemoteId();
	}

	/**
	* Returns the zendesk article attachment ID of this zendesk article attachment.
	*
	* @return the zendesk article attachment ID of this zendesk article attachment
	*/
	@Override
	public long getZendeskArticleAttachmentId() {
		return _zendeskArticleAttachment.getZendeskArticleAttachmentId();
	}

	/**
	* Returns the zendesk article ID of this zendesk article attachment.
	*
	* @return the zendesk article ID of this zendesk article attachment
	*/
	@Override
	public long getZendeskArticleId() {
		return _zendeskArticleAttachment.getZendeskArticleId();
	}

	@Override
	public void persist() {
		_zendeskArticleAttachment.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_zendeskArticleAttachment.setCachedModel(cachedModel);
	}

	/**
	* Sets the checksum of this zendesk article attachment.
	*
	* @param checksum the checksum of this zendesk article attachment
	*/
	@Override
	public void setChecksum(java.lang.String checksum) {
		_zendeskArticleAttachment.setChecksum(checksum);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_zendeskArticleAttachment.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_zendeskArticleAttachment.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_zendeskArticleAttachment.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the file path of this zendesk article attachment.
	*
	* @param filePath the file path of this zendesk article attachment
	*/
	@Override
	public void setFilePath(java.lang.String filePath) {
		_zendeskArticleAttachment.setFilePath(filePath);
	}

	@Override
	public void setNew(boolean n) {
		_zendeskArticleAttachment.setNew(n);
	}

	/**
	* Sets the primary key of this zendesk article attachment.
	*
	* @param primaryKey the primary key of this zendesk article attachment
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_zendeskArticleAttachment.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_zendeskArticleAttachment.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the remote content url of this zendesk article attachment.
	*
	* @param remoteContentURL the remote content url of this zendesk article attachment
	*/
	@Override
	public void setRemoteContentURL(java.lang.String remoteContentURL) {
		_zendeskArticleAttachment.setRemoteContentURL(remoteContentURL);
	}

	/**
	* Sets the remote ID of this zendesk article attachment.
	*
	* @param remoteId the remote ID of this zendesk article attachment
	*/
	@Override
	public void setRemoteId(long remoteId) {
		_zendeskArticleAttachment.setRemoteId(remoteId);
	}

	/**
	* Sets the zendesk article attachment ID of this zendesk article attachment.
	*
	* @param zendeskArticleAttachmentId the zendesk article attachment ID of this zendesk article attachment
	*/
	@Override
	public void setZendeskArticleAttachmentId(long zendeskArticleAttachmentId) {
		_zendeskArticleAttachment.setZendeskArticleAttachmentId(zendeskArticleAttachmentId);
	}

	/**
	* Sets the zendesk article ID of this zendesk article attachment.
	*
	* @param zendeskArticleId the zendesk article ID of this zendesk article attachment
	*/
	@Override
	public void setZendeskArticleId(long zendeskArticleId) {
		_zendeskArticleAttachment.setZendeskArticleId(zendeskArticleId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ZendeskArticleAttachmentWrapper)) {
			return false;
		}

		ZendeskArticleAttachmentWrapper zendeskArticleAttachmentWrapper = (ZendeskArticleAttachmentWrapper)obj;

		if (Objects.equals(_zendeskArticleAttachment,
					zendeskArticleAttachmentWrapper._zendeskArticleAttachment)) {
			return true;
		}

		return false;
	}

	@Override
	public ZendeskArticleAttachment getWrappedModel() {
		return _zendeskArticleAttachment;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _zendeskArticleAttachment.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _zendeskArticleAttachment.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_zendeskArticleAttachment.resetOriginalValues();
	}

	private final ZendeskArticleAttachment _zendeskArticleAttachment;
}