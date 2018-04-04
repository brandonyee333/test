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

package com.liferay.akismet.model;

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
 * This class is a wrapper for {@link Akismet}.
 * </p>
 *
 * @author Jamie Sammons
 * @see Akismet
 * @generated
 */
@ProviderType
public class AkismetWrapper implements Akismet, ModelWrapper<Akismet> {
	public AkismetWrapper(Akismet akismet) {
		_akismet = akismet;
	}

	@Override
	public Class<?> getModelClass() {
		return Akismet.class;
	}

	@Override
	public String getModelClassName() {
		return Akismet.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("akismetId", getAkismetId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("type", getType());
		attributes.put("permalink", getPermalink());
		attributes.put("referrer", getReferrer());
		attributes.put("userAgent", getUserAgent());
		attributes.put("userIP", getUserIP());
		attributes.put("userURL", getUserURL());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long akismetId = (Long)attributes.get("akismetId");

		if (akismetId != null) {
			setAkismetId(akismetId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String permalink = (String)attributes.get("permalink");

		if (permalink != null) {
			setPermalink(permalink);
		}

		String referrer = (String)attributes.get("referrer");

		if (referrer != null) {
			setReferrer(referrer);
		}

		String userAgent = (String)attributes.get("userAgent");

		if (userAgent != null) {
			setUserAgent(userAgent);
		}

		String userIP = (String)attributes.get("userIP");

		if (userIP != null) {
			setUserIP(userIP);
		}

		String userURL = (String)attributes.get("userURL");

		if (userURL != null) {
			setUserURL(userURL);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new AkismetWrapper((Akismet)_akismet.clone());
	}

	@Override
	public int compareTo(Akismet akismet) {
		return _akismet.compareTo(akismet);
	}

	/**
	* Returns the akismet ID of this akismet.
	*
	* @return the akismet ID of this akismet
	*/
	@Override
	public long getAkismetId() {
		return _akismet.getAkismetId();
	}

	/**
	* Returns the fully qualified class name of this akismet.
	*
	* @return the fully qualified class name of this akismet
	*/
	@Override
	public java.lang.String getClassName() {
		return _akismet.getClassName();
	}

	/**
	* Returns the class name ID of this akismet.
	*
	* @return the class name ID of this akismet
	*/
	@Override
	public long getClassNameId() {
		return _akismet.getClassNameId();
	}

	/**
	* Returns the class pk of this akismet.
	*
	* @return the class pk of this akismet
	*/
	@Override
	public long getClassPK() {
		return _akismet.getClassPK();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _akismet.getExpandoBridge();
	}

	/**
	* Returns the modified date of this akismet.
	*
	* @return the modified date of this akismet
	*/
	@Override
	public Date getModifiedDate() {
		return _akismet.getModifiedDate();
	}

	/**
	* Returns the permalink of this akismet.
	*
	* @return the permalink of this akismet
	*/
	@Override
	public java.lang.String getPermalink() {
		return _akismet.getPermalink();
	}

	/**
	* Returns the primary key of this akismet.
	*
	* @return the primary key of this akismet
	*/
	@Override
	public long getPrimaryKey() {
		return _akismet.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _akismet.getPrimaryKeyObj();
	}

	/**
	* Returns the referrer of this akismet.
	*
	* @return the referrer of this akismet
	*/
	@Override
	public java.lang.String getReferrer() {
		return _akismet.getReferrer();
	}

	/**
	* Returns the type of this akismet.
	*
	* @return the type of this akismet
	*/
	@Override
	public java.lang.String getType() {
		return _akismet.getType();
	}

	/**
	* Returns the user agent of this akismet.
	*
	* @return the user agent of this akismet
	*/
	@Override
	public java.lang.String getUserAgent() {
		return _akismet.getUserAgent();
	}

	/**
	* Returns the user ip of this akismet.
	*
	* @return the user ip of this akismet
	*/
	@Override
	public java.lang.String getUserIP() {
		return _akismet.getUserIP();
	}

	/**
	* Returns the user url of this akismet.
	*
	* @return the user url of this akismet
	*/
	@Override
	public java.lang.String getUserURL() {
		return _akismet.getUserURL();
	}

	@Override
	public int hashCode() {
		return _akismet.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _akismet.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _akismet.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _akismet.isNew();
	}

	@Override
	public void persist() {
		_akismet.persist();
	}

	/**
	* Sets the akismet ID of this akismet.
	*
	* @param akismetId the akismet ID of this akismet
	*/
	@Override
	public void setAkismetId(long akismetId) {
		_akismet.setAkismetId(akismetId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_akismet.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(java.lang.String className) {
		_akismet.setClassName(className);
	}

	/**
	* Sets the class name ID of this akismet.
	*
	* @param classNameId the class name ID of this akismet
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_akismet.setClassNameId(classNameId);
	}

	/**
	* Sets the class pk of this akismet.
	*
	* @param classPK the class pk of this akismet
	*/
	@Override
	public void setClassPK(long classPK) {
		_akismet.setClassPK(classPK);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_akismet.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_akismet.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_akismet.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified date of this akismet.
	*
	* @param modifiedDate the modified date of this akismet
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_akismet.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_akismet.setNew(n);
	}

	/**
	* Sets the permalink of this akismet.
	*
	* @param permalink the permalink of this akismet
	*/
	@Override
	public void setPermalink(java.lang.String permalink) {
		_akismet.setPermalink(permalink);
	}

	/**
	* Sets the primary key of this akismet.
	*
	* @param primaryKey the primary key of this akismet
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_akismet.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_akismet.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the referrer of this akismet.
	*
	* @param referrer the referrer of this akismet
	*/
	@Override
	public void setReferrer(java.lang.String referrer) {
		_akismet.setReferrer(referrer);
	}

	/**
	* Sets the type of this akismet.
	*
	* @param type the type of this akismet
	*/
	@Override
	public void setType(java.lang.String type) {
		_akismet.setType(type);
	}

	/**
	* Sets the user agent of this akismet.
	*
	* @param userAgent the user agent of this akismet
	*/
	@Override
	public void setUserAgent(java.lang.String userAgent) {
		_akismet.setUserAgent(userAgent);
	}

	/**
	* Sets the user ip of this akismet.
	*
	* @param userIP the user ip of this akismet
	*/
	@Override
	public void setUserIP(java.lang.String userIP) {
		_akismet.setUserIP(userIP);
	}

	/**
	* Sets the user url of this akismet.
	*
	* @param userURL the user url of this akismet
	*/
	@Override
	public void setUserURL(java.lang.String userURL) {
		_akismet.setUserURL(userURL);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Akismet> toCacheModel() {
		return _akismet.toCacheModel();
	}

	@Override
	public Akismet toEscapedModel() {
		return new AkismetWrapper(_akismet.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _akismet.toString();
	}

	@Override
	public Akismet toUnescapedModel() {
		return new AkismetWrapper(_akismet.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _akismet.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AkismetWrapper)) {
			return false;
		}

		AkismetWrapper akismetWrapper = (AkismetWrapper)obj;

		if (Objects.equals(_akismet, akismetWrapper._akismet)) {
			return true;
		}

		return false;
	}

	@Override
	public Akismet getWrappedModel() {
		return _akismet;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _akismet.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _akismet.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_akismet.resetOriginalValues();
	}

	private final Akismet _akismet;
}