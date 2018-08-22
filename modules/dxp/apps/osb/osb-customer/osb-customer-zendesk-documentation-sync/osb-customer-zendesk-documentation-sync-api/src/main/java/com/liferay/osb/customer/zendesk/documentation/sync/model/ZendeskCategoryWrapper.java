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
		attributes.put("articleLabels", getArticleLabels());
		attributes.put("remoteId", getRemoteId());

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

		String articleLabels = (String)attributes.get("articleLabels");

		if (articleLabels != null) {
			setArticleLabels(articleLabels);
		}

		Long remoteId = (Long)attributes.get("remoteId");

		if (remoteId != null) {
			setRemoteId(remoteId);
		}
	}

	@Override
	public Object clone() {
		return new ZendeskCategoryWrapper((ZendeskCategory)_zendeskCategory.clone());
	}

	@Override
	public int compareTo(ZendeskCategory zendeskCategory) {
		return _zendeskCategory.compareTo(zendeskCategory);
	}

	/**
	* Returns the article labels of this zendesk category.
	*
	* @return the article labels of this zendesk category
	*/
	@Override
	public String getArticleLabels() {
		return _zendeskCategory.getArticleLabels();
	}

	/**
	* Returns the documentation key of this zendesk category.
	*
	* @return the documentation key of this zendesk category
	*/
	@Override
	public String getDocumentationKey() {
		return _zendeskCategory.getDocumentationKey();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _zendeskCategory.getExpandoBridge();
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

	@Override
	public Serializable getPrimaryKeyObj() {
		return _zendeskCategory.getPrimaryKeyObj();
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

	@Override
	public String[] getRemoteLabelNames() {
		return _zendeskCategory.getRemoteLabelNames();
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
	public int hashCode() {
		return _zendeskCategory.hashCode();
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
	public void persist() {
		_zendeskCategory.persist();
	}

	/**
	* Sets the article labels of this zendesk category.
	*
	* @param articleLabels the article labels of this zendesk category
	*/
	@Override
	public void setArticleLabels(String articleLabels) {
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
	public void setDocumentationKey(String documentationKey) {
		_zendeskCategory.setDocumentationKey(documentationKey);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_zendeskCategory.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_zendeskCategory.setExpandoBridgeAttributes(expandoBridge);
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
	* Sets the zendesk category ID of this zendesk category.
	*
	* @param zendeskCategoryId the zendesk category ID of this zendesk category
	*/
	@Override
	public void setZendeskCategoryId(long zendeskCategoryId) {
		_zendeskCategory.setZendeskCategoryId(zendeskCategoryId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ZendeskCategory> toCacheModel() {
		return _zendeskCategory.toCacheModel();
	}

	@Override
	public ZendeskCategory toEscapedModel() {
		return new ZendeskCategoryWrapper(_zendeskCategory.toEscapedModel());
	}

	@Override
	public String toString() {
		return _zendeskCategory.toString();
	}

	@Override
	public ZendeskCategory toUnescapedModel() {
		return new ZendeskCategoryWrapper(_zendeskCategory.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _zendeskCategory.toXmlString();
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