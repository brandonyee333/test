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

import com.liferay.osb.model.SupportWorkerComponent;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing SupportWorkerComponent in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerComponent
 * @generated
 */
public class SupportWorkerComponentCacheModel implements CacheModel<SupportWorkerComponent>,
	Serializable {
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

	public SupportWorkerComponent toEntityModel() {
		SupportWorkerComponentImpl supportWorkerComponentImpl = new SupportWorkerComponentImpl();

		supportWorkerComponentImpl.setSupportWorkerComponentId(supportWorkerComponentId);
		supportWorkerComponentImpl.setSupportWorkerId(supportWorkerId);
		supportWorkerComponentImpl.setComponent(component);

		supportWorkerComponentImpl.resetOriginalValues();

		return supportWorkerComponentImpl;
	}

	public long supportWorkerComponentId;
	public long supportWorkerId;
	public int component;
}