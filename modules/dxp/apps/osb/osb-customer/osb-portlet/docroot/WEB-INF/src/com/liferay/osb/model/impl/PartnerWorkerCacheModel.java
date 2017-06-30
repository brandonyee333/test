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

import com.liferay.osb.model.PartnerWorker;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing PartnerWorker in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PartnerWorker
 * @generated
 */
public class PartnerWorkerCacheModel implements CacheModel<PartnerWorker>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{partnerWorkerId=");
		sb.append(partnerWorkerId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", partnerEntryId=");
		sb.append(partnerEntryId);
		sb.append(", role=");
		sb.append(role);
		sb.append(", notifications=");
		sb.append(notifications);
		sb.append("}");

		return sb.toString();
	}

	public PartnerWorker toEntityModel() {
		PartnerWorkerImpl partnerWorkerImpl = new PartnerWorkerImpl();

		partnerWorkerImpl.setPartnerWorkerId(partnerWorkerId);
		partnerWorkerImpl.setUserId(userId);
		partnerWorkerImpl.setPartnerEntryId(partnerEntryId);
		partnerWorkerImpl.setRole(role);
		partnerWorkerImpl.setNotifications(notifications);

		partnerWorkerImpl.resetOriginalValues();

		return partnerWorkerImpl;
	}

	public long partnerWorkerId;
	public long userId;
	public long partnerEntryId;
	public int role;
	public int notifications;
}