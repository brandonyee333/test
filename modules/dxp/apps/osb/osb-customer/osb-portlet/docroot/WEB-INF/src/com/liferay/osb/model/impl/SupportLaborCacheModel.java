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

package com.liferay.osb.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.model.SupportLabor;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SupportLabor in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SupportLabor
 * @generated
 */
@ProviderType
public class SupportLaborCacheModel implements CacheModel<SupportLabor>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SupportLaborCacheModel)) {
			return false;
		}

		SupportLaborCacheModel supportLaborCacheModel = (SupportLaborCacheModel)obj;

		if (supportLaborId == supportLaborCacheModel.supportLaborId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, supportLaborId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{supportLaborId=");
		sb.append(supportLaborId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", timeZoneId=");
		sb.append(timeZoneId);
		sb.append(", sunOpen=");
		sb.append(sunOpen);
		sb.append(", sunClose=");
		sb.append(sunClose);
		sb.append(", monOpen=");
		sb.append(monOpen);
		sb.append(", monClose=");
		sb.append(monClose);
		sb.append(", tueOpen=");
		sb.append(tueOpen);
		sb.append(", tueClose=");
		sb.append(tueClose);
		sb.append(", wedOpen=");
		sb.append(wedOpen);
		sb.append(", wedClose=");
		sb.append(wedClose);
		sb.append(", thuOpen=");
		sb.append(thuOpen);
		sb.append(", thuClose=");
		sb.append(thuClose);
		sb.append(", friOpen=");
		sb.append(friOpen);
		sb.append(", friClose=");
		sb.append(friClose);
		sb.append(", satOpen=");
		sb.append(satOpen);
		sb.append(", satClose=");
		sb.append(satClose);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SupportLabor toEntityModel() {
		SupportLaborImpl supportLaborImpl = new SupportLaborImpl();

		supportLaborImpl.setSupportLaborId(supportLaborId);

		if (name == null) {
			supportLaborImpl.setName(StringPool.BLANK);
		}
		else {
			supportLaborImpl.setName(name);
		}

		if (description == null) {
			supportLaborImpl.setDescription(StringPool.BLANK);
		}
		else {
			supportLaborImpl.setDescription(description);
		}

		if (timeZoneId == null) {
			supportLaborImpl.setTimeZoneId(StringPool.BLANK);
		}
		else {
			supportLaborImpl.setTimeZoneId(timeZoneId);
		}

		supportLaborImpl.setSunOpen(sunOpen);
		supportLaborImpl.setSunClose(sunClose);
		supportLaborImpl.setMonOpen(monOpen);
		supportLaborImpl.setMonClose(monClose);
		supportLaborImpl.setTueOpen(tueOpen);
		supportLaborImpl.setTueClose(tueClose);
		supportLaborImpl.setWedOpen(wedOpen);
		supportLaborImpl.setWedClose(wedClose);
		supportLaborImpl.setThuOpen(thuOpen);
		supportLaborImpl.setThuClose(thuClose);
		supportLaborImpl.setFriOpen(friOpen);
		supportLaborImpl.setFriClose(friClose);
		supportLaborImpl.setSatOpen(satOpen);
		supportLaborImpl.setSatClose(satClose);

		supportLaborImpl.resetOriginalValues();

		return supportLaborImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		supportLaborId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		timeZoneId = objectInput.readUTF();

		sunOpen = objectInput.readInt();

		sunClose = objectInput.readInt();

		monOpen = objectInput.readInt();

		monClose = objectInput.readInt();

		tueOpen = objectInput.readInt();

		tueClose = objectInput.readInt();

		wedOpen = objectInput.readInt();

		wedClose = objectInput.readInt();

		thuOpen = objectInput.readInt();

		thuClose = objectInput.readInt();

		friOpen = objectInput.readInt();

		friClose = objectInput.readInt();

		satOpen = objectInput.readInt();

		satClose = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(supportLaborId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (timeZoneId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(timeZoneId);
		}

		objectOutput.writeInt(sunOpen);

		objectOutput.writeInt(sunClose);

		objectOutput.writeInt(monOpen);

		objectOutput.writeInt(monClose);

		objectOutput.writeInt(tueOpen);

		objectOutput.writeInt(tueClose);

		objectOutput.writeInt(wedOpen);

		objectOutput.writeInt(wedClose);

		objectOutput.writeInt(thuOpen);

		objectOutput.writeInt(thuClose);

		objectOutput.writeInt(friOpen);

		objectOutput.writeInt(friClose);

		objectOutput.writeInt(satOpen);

		objectOutput.writeInt(satClose);
	}

	public long supportLaborId;
	public String name;
	public String description;
	public String timeZoneId;
	public int sunOpen;
	public int sunClose;
	public int monOpen;
	public int monClose;
	public int tueOpen;
	public int tueClose;
	public int wedOpen;
	public int wedClose;
	public int thuOpen;
	public int thuClose;
	public int friOpen;
	public int friClose;
	public int satOpen;
	public int satClose;
}