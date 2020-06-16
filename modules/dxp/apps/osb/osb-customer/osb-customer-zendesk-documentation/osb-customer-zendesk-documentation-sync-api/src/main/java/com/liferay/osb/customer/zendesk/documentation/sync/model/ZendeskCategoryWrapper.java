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
 * This class is a wrapper for {@link ZendeskCategory}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskCategory
 * @generated
 */
@ProviderType
public class ZendeskCategoryWrapper implements ZendeskCategory,
	ModelWrapper<ZendeskCategory> {
	public ZendeskCategoryWrapper(ZendeskCategory zendeskCategory) {
		_zendeskCategory = zendeskCategory;
	}

	@Override
	public Class<?> getModelClass() {
		return ZendeskCategory.class;
	}

	@Override
	public String getModelClassName() {
		return ZendeskCategory.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("zendeskCategoryId", getZendeskCategoryId());
		attributes.put("documentationKey", getDocumentationKey());
		attributes.put("documentationOriginalURL", getDocumentationOriginalURL());
		attributes.put("articleLabels", getArticleLabels());
		attributes.put("remoteId", getRemoteId());
		attributes.put("remoteUserSegmentId", getRemoteUserSegmentId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long zendeskCategoryId = (Long)attributes.get("zendeskCategoryId");

		if (zendeskCategoryId != null) {
			setZendeskCategoryId(zendeskCategoryId);
		}

		String documentationKey = (String)attributes.get("documentationKey");

		if (documentationKey != null) {
			setDocumentationKey(documentationKey);
		}

		String documentationOriginalURL = (String)attributes.get(
				"documentationOriginalURL");

		if (documentationOriginalURL != null) {
			setDocumentationOriginalURL(documentationOriginalURL);
		}

		String articleLabels = (String)attributes.get("articleLabels");

		if (articleLabels != null) {
			setArticleLabels(articleLabels);
		}

		Long remoteId = (Long)attributes.get("remoteId");

		if (remoteId != null) {
			setRemoteId(remoteId);
		}

		Long remoteUserSegmentId = (Long)attributes.get("remoteUserSegmentId");

		if (remoteUserSegmentId != null) {
			setRemoteUserSegmentId(remoteUserSegmentId);
		}
	}

	@Override
	public ZendeskCategory toEscapedModel() {
		return new ZendeskCategoryWrapper(_zendeskCategory.toEscapedModel());
	}

	@Override
	public ZendeskCategory toUnescapedModel() {
		return new ZendeskCategoryWrapper(_zendeskCategory.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _zendeskCategory.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _zendeskCategory.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _zendeskCategory.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _zendeskCategory.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ZendeskCategory> toCacheModel() {
		return _zendeskCategory.toCacheModel();
	}

	@Override
	public int compareTo(ZendeskCategory zendeskCategory) {
		return _zendeskCategory.compareTo(zendeskCategory);
	}

	@Override
	public int hashCode() {
		return _zendeskCategory.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _zendeskCategory.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ZendeskCategoryWrapper((ZendeskCategory)_zendeskCategory.clone());
	}

	/**
	* Returns the article labels of this zendesk category.
	*
	* @return the article labels of this zendesk category
	*/
	@Override
	public java.lang.String getArticleLabels() {
		return _zendeskCategory.getArticleLabels();
	}

	/**
	* Returns the documentation key of this zendesk category.
	*
	* @return the documentation key of this zendesk category
	*/
	@Override
	public java.lang.String getDocumentationKey() {
		return _zendeskCategory.getDocumentationKey();
	}

	/**
	* Returns the documentation original url of this zendesk category.
	*
	* @return the documentation original url of this zendesk category
	*/
	@Override
	public java.lang.String getDocumentationOriginalURL() {
		return _zendeskCategory.getDocumentationOriginalURL();
	}

	@Override
	public java.lang.String toString() {
		return _zendeskCategory.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _zendeskCategory.toXmlString();
	}

	@Override
	public java.lang.String[] getRemoteLabelNames() {
		return _zendeskCategory.getRemoteLabelNames();
	}

	/**
	* Returns the primary key of this zendesk category.
	*
	* @return the primary key of this zendesk category
	*/
	@Override
	public long getPrimaryKey() {
		return _zendeskCategory.getPrimaryKey();
	}

	/**
	* Returns the remote ID of this zendesk category.
	*
	* @return the remote ID of this zendesk category
	*/
	@Override
	public long getRemoteId() {
		return _zendeskCategory.getRemoteId();
	}

	/**
	* Returns the remote user segment ID of this zendesk category.
	*
	* @return the remote user segment ID of this zendesk category
	*/
	@Override
	public long getRemoteUserSegmentId() {
		return _zendeskCategory.getRemoteUserSegmentId();
	}

	/**
	* Returns the zendesk category ID of this zendesk category.
	*
	* @return the zendesk category ID of this zendesk category
	*/
	@Override
	public long getZendeskCategoryId() {
		return _zendeskCategory.getZendeskCategoryId();
	}

	@Override
	public void persist() {
		_zendeskCategory.persist();
	}

	/**
	* Sets the article labels of this zendesk category.
	*
	* @param articleLabels the article labels of this zendesk category
	*/
	@Override
	public void setArticleLabels(java.lang.String articleLabels) {
		_zendeskCategory.setArticleLabels(articleLabels);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_zendeskCategory.setCachedModel(cachedModel);
	}

	/**
	* Sets the documentation key of this zendesk category.
	*
	* @param documentationKey the documentation key of this zendesk category
	*/
	@Override
	public void setDocumentationKey(java.lang.String documentationKey) {
		_zendeskCategory.setDocumentationKey(documentationKey);
	}

	/**
	* Sets the documentation original url of this zendesk category.
	*
	* @param documentationOriginalURL the documentation original url of this zendesk category
	*/
	@Override
	public void setDocumentationOriginalURL(
		java.lang.String documentationOriginalURL) {
		_zendeskCategory.setDocumentationOriginalURL(documentationOriginalURL);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_zendeskCategory.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_zendeskCategory.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_zendeskCategory.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_zendeskCategory.setNew(n);
	}

	/**
	* Sets the primary key of this zendesk category.
	*
	* @param primaryKey the primary key of this zendesk category
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_zendeskCategory.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_zendeskCategory.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the remote ID of this zendesk category.
	*
	* @param remoteId the remote ID of this zendesk category
	*/
	@Override
	public void setRemoteId(long remoteId) {
		_zendeskCategory.setRemoteId(remoteId);
	}

	/**
	* Sets the remote user segment ID of this zendesk category.
	*
	* @param remoteUserSegmentId the remote user segment ID of this zendesk category
	*/
	@Override
	public void setRemoteUserSegmentId(long remoteUserSegmentId) {
		_zendeskCategory.setRemoteUserSegmentId(remoteUserSegmentId);
	}

	/**
	* Sets the zendesk category ID of this zendesk category.
	*
	* @param zendeskCategoryId the zendesk category ID of this zendesk category
	*/
	@Override
	public void setZendeskCategoryId(long zendeskCategoryId) {
		_zendeskCategory.setZendeskCategoryId(zendeskCategoryId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ZendeskCategoryWrapper)) {
			return false;
		}

		ZendeskCategoryWrapper zendeskCategoryWrapper = (ZendeskCategoryWrapper)obj;

		if (Objects.equals(_zendeskCategory,
					zendeskCategoryWrapper._zendeskCategory)) {
			return true;
		}

		return false;
	}

	@Override
	public ZendeskCategory getWrappedModel() {
		return _zendeskCategory;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _zendeskCategory.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _zendeskCategory.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_zendeskCategory.resetOriginalValues();
	}

	private final ZendeskCategory _zendeskCategory;
}