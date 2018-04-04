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

package com.liferay.osb.community.akismet.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.community.akismet.model.Akismet;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Akismet in entity cache.
 *
 * @author Jamie Sammons
 * @see Akismet
 * @generated
 */
@ProviderType
public class AkismetCacheModel implements CacheModel<Akismet>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AkismetCacheModel)) {
			return false;
		}

		AkismetCacheModel akismetCacheModel = (AkismetCacheModel)obj;

		if (akismetId == akismetCacheModel.akismetId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, akismetId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{akismetId=");
		sb.append(akismetId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", type=");
		sb.append(type);
		sb.append(", permalink=");
		sb.append(permalink);
		sb.append(", referrer=");
		sb.append(referrer);
		sb.append(", userAgent=");
		sb.append(userAgent);
		sb.append(", userIP=");
		sb.append(userIP);
		sb.append(", userURL=");
		sb.append(userURL);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Akismet toEntityModel() {
		AkismetImpl akismetImpl = new AkismetImpl();

		akismetImpl.setAkismetId(akismetId);

		if (modifiedDate == Long.MIN_VALUE) {
			akismetImpl.setModifiedDate(null);
		}
		else {
			akismetImpl.setModifiedDate(new Date(modifiedDate));
		}

		akismetImpl.setClassNameId(classNameId);
		akismetImpl.setClassPK(classPK);

		if (type == null) {
			akismetImpl.setType("");
		}
		else {
			akismetImpl.setType(type);
		}

		if (permalink == null) {
			akismetImpl.setPermalink("");
		}
		else {
			akismetImpl.setPermalink(permalink);
		}

		if (referrer == null) {
			akismetImpl.setReferrer("");
		}
		else {
			akismetImpl.setReferrer(referrer);
		}

		if (userAgent == null) {
			akismetImpl.setUserAgent("");
		}
		else {
			akismetImpl.setUserAgent(userAgent);
		}

		if (userIP == null) {
			akismetImpl.setUserIP("");
		}
		else {
			akismetImpl.setUserIP(userIP);
		}

		if (userURL == null) {
			akismetImpl.setUserURL("");
		}
		else {
			akismetImpl.setUserURL(userURL);
		}

		akismetImpl.resetOriginalValues();

		return akismetImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		akismetId = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();
		type = objectInput.readUTF();
		permalink = objectInput.readUTF();
		referrer = objectInput.readUTF();
		userAgent = objectInput.readUTF();
		userIP = objectInput.readUTF();
		userURL = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(akismetId);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		if (type == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(type);
		}

		if (permalink == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(permalink);
		}

		if (referrer == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(referrer);
		}

		if (userAgent == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userAgent);
		}

		if (userIP == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userIP);
		}

		if (userURL == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userURL);
		}
	}

	public long akismetId;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public String type;
	public String permalink;
	public String referrer;
	public String userAgent;
	public String userIP;
	public String userURL;
}