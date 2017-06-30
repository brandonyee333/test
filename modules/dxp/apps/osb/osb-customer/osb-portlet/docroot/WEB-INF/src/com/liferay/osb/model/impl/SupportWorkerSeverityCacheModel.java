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

import com.liferay.osb.model.SupportWorkerSeverity;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing SupportWorkerSeverity in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerSeverity
 * @generated
 */
public class SupportWorkerSeverityCacheModel implements CacheModel<SupportWorkerSeverity>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{supportWorkerSeverityId=");
		sb.append(supportWorkerSeverityId);
		sb.append(", supportWorkerId=");
		sb.append(supportWorkerId);
		sb.append(", severity=");
		sb.append(severity);
		sb.append("}");

		return sb.toString();
	}

	public SupportWorkerSeverity toEntityModel() {
		SupportWorkerSeverityImpl supportWorkerSeverityImpl = new SupportWorkerSeverityImpl();

		supportWorkerSeverityImpl.setSupportWorkerSeverityId(supportWorkerSeverityId);
		supportWorkerSeverityImpl.setSupportWorkerId(supportWorkerId);
		supportWorkerSeverityImpl.setSeverity(severity);

		supportWorkerSeverityImpl.resetOriginalValues();

		return supportWorkerSeverityImpl;
	}

	public long supportWorkerSeverityId;
	public long supportWorkerId;
	public int severity;
}