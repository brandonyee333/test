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

import com.liferay.osb.model.SupportWorkerComponent;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SupportWorkerComponent in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerComponent
 * @generated
 */
@ProviderType
public class SupportWorkerComponentCacheModel implements CacheModel<SupportWorkerComponent>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SupportWorkerComponentCacheModel)) {
			return false;
		}

		SupportWorkerComponentCacheModel supportWorkerComponentCacheModel = (SupportWorkerComponentCacheModel)obj;

		if (supportWorkerComponentId == supportWorkerComponentCacheModel.supportWorkerComponentId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, supportWorkerComponentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{supportWorkerComponentId=");
		sb.append(supportWorkerComponentId);
		sb.append(", supportWorkerId=");
		sb.append(supportWorkerId);
		sb.append(", component=");
		sb.append(component);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SupportWorkerComponent toEntityModel() {
		SupportWorkerComponentImpl supportWorkerComponentImpl = new SupportWorkerComponentImpl();

		supportWorkerComponentImpl.setSupportWorkerComponentId(supportWorkerComponentId);
		supportWorkerComponentImpl.setSupportWorkerId(supportWorkerId);
		supportWorkerComponentImpl.setComponent(component);

		supportWorkerComponentImpl.resetOriginalValues();

		return supportWorkerComponentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		supportWorkerComponentId = objectInput.readLong();

		supportWorkerId = objectInput.readLong();

		component = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(supportWorkerComponentId);

		objectOutput.writeLong(supportWorkerId);

		objectOutput.writeInt(component);
	}

	public long supportWorkerComponentId;
	public long supportWorkerId;
	public int component;
}