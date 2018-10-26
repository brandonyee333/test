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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ZendeskSection}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskSection
 * @generated
 */
@ProviderType
public class ZendeskSectionWrapper implements ZendeskSection,
	ModelWrapper<ZendeskSection> {
	public ZendeskSectionWrapper(ZendeskSection zendeskSection) {
		_zendeskSection = zendeskSection;
	}

	@Override
	public Class<?> getModelClass() {
		return ZendeskSection.class;
	}

	@Override
	public String getModelClassName() {
		return ZendeskSection.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("zendeskSectionId", getZendeskSectionId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("zendeskCategoryId", getZendeskCategoryId());
		attributes.put("documentationKey", getDocumentationKey());
		attributes.put("remoteId", getRemoteId());
		attributes.put("remoteHtmlURL", getRemoteHtmlURL());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long zendeskSectionId = (Long)attributes.get("zendeskSectionId");

		if (zendeskSectionId != null) {
			setZendeskSectionId(zendeskSectionId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long zendeskCategoryId = (Long)attributes.get("zendeskCategoryId");

		if (zendeskCategoryId != null) {
			setZendeskCategoryId(zendeskCategoryId);
		}

		String documentationKey = (String)attributes.get("documentationKey");

		if (documentationKey != null) {
			setDocumentationKey(documentationKey);
		}

		Long remoteId = (Long)attributes.get("remoteId");

		if (remoteId != null) {
			setRemoteId(remoteId);
		}

		String remoteHtmlURL = (String)attributes.get("remoteHtmlURL");

		if (remoteHtmlURL != null) {
			setRemoteHtmlURL(remoteHtmlURL);
		}
	}

	@Override
	public Object clone() {
		return new ZendeskSectionWrapper((ZendeskSection)_zendeskSection.clone());
	}

	@Override
	public int compareTo(ZendeskSection zendeskSection) {
		return _zendeskSection.compareTo(zendeskSection);
	}

	/**
	* Returns the documentation key of this zendesk section.
	*
	* @return the documentation key of this zendesk section
	*/
	@Override
	public String getDocumentationKey() {
		return _zendeskSection.getDocumentationKey();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _zendeskSection.getExpandoBridge();
	}

	/**
	* Returns the modified date of this zendesk section.
	*
	* @return the modified date of this zendesk section
	*/
	@Override
	public Date getModifiedDate() {
		return _zendeskSection.getModifiedDate();
	}

	/**
	* Returns the primary key of this zendesk section.
	*
	* @return the primary key of this zendesk section
	*/
	@Override
	public long getPrimaryKey() {
		return _zendeskSection.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _zendeskSection.getPrimaryKeyObj();
	}

	/**
	* Returns the remote html url of this zendesk section.
	*
	* @return the remote html url of this zendesk section
	*/
	@Override
	public String getRemoteHtmlURL() {
		return _zendeskSection.getRemoteHtmlURL();
	}

	/**
	* Returns the remote ID of this zendesk section.
	*
	* @return the remote ID of this zendesk section
	*/
	@Override
	public long getRemoteId() {
		return _zendeskSection.getRemoteId();
	}

	/**
	* Returns the zendesk category ID of this zendesk section.
	*
	* @return the zendesk category ID of this zendesk section
	*/
	@Override
	public long getZendeskCategoryId() {
		return _zendeskSection.getZendeskCategoryId();
	}

	/**
	* Returns the zendesk section ID of this zendesk section.
	*
	* @return the zendesk section ID of this zendesk section
	*/
	@Override
	public long getZendeskSectionId() {
		return _zendeskSection.getZendeskSectionId();
	}

	@Override
	public int hashCode() {
		return _zendeskSection.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _zendeskSection.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _zendeskSection.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _zendeskSection.isNew();
	}

	@Override
	public void persist() {
		_zendeskSection.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_zendeskSection.setCachedModel(cachedModel);
	}

	/**
	* Sets the documentation key of this zendesk section.
	*
	* @param documentationKey the documentation key of this zendesk section
	*/
	@Override
	public void setDocumentationKey(String documentationKey) {
		_zendeskSection.setDocumentationKey(documentationKey);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_zendeskSection.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_zendeskSection.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_zendeskSection.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified date of this zendesk section.
	*
	* @param modifiedDate the modified date of this zendesk section
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_zendeskSection.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_zendeskSection.setNew(n);
	}

	/**
	* Sets the primary key of this zendesk section.
	*
	* @param primaryKey the primary key of this zendesk section
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_zendeskSection.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_zendeskSection.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the remote html url of this zendesk section.
	*
	* @param remoteHtmlURL the remote html url of this zendesk section
	*/
	@Override
	public void setRemoteHtmlURL(String remoteHtmlURL) {
		_zendeskSection.setRemoteHtmlURL(remoteHtmlURL);
	}

	/**
	* Sets the remote ID of this zendesk section.
	*
	* @param remoteId the remote ID of this zendesk section
	*/
	@Override
	public void setRemoteId(long remoteId) {
		_zendeskSection.setRemoteId(remoteId);
	}

	/**
	* Sets the zendesk category ID of this zendesk section.
	*
	* @param zendeskCategoryId the zendesk category ID of this zendesk section
	*/
	@Override
	public void setZendeskCategoryId(long zendeskCategoryId) {
		_zendeskSection.setZendeskCategoryId(zendeskCategoryId);
	}

	/**
	* Sets the zendesk section ID of this zendesk section.
	*
	* @param zendeskSectionId the zendesk section ID of this zendesk section
	*/
	@Override
	public void setZendeskSectionId(long zendeskSectionId) {
		_zendeskSection.setZendeskSectionId(zendeskSectionId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ZendeskSection> toCacheModel() {
		return _zendeskSection.toCacheModel();
	}

	@Override
	public ZendeskSection toEscapedModel() {
		return new ZendeskSectionWrapper(_zendeskSection.toEscapedModel());
	}

	@Override
	public String toString() {
		return _zendeskSection.toString();
	}

	@Override
	public ZendeskSection toUnescapedModel() {
		return new ZendeskSectionWrapper(_zendeskSection.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _zendeskSection.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ZendeskSectionWrapper)) {
			return false;
		}

		ZendeskSectionWrapper zendeskSectionWrapper = (ZendeskSectionWrapper)obj;

		if (Objects.equals(_zendeskSection,
					zendeskSectionWrapper._zendeskSection)) {
			return true;
		}

		return false;
	}

	@Override
	public ZendeskSection getWrappedModel() {
		return _zendeskSection;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _zendeskSection.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _zendeskSection.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_zendeskSection.resetOriginalValues();
	}

	private final ZendeskSection _zendeskSection;
}