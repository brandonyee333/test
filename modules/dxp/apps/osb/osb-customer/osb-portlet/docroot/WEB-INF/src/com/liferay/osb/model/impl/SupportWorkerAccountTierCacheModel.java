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

import com.liferay.osb.model.SupportWorkerAccountTier;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing SupportWorkerAccountTier in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerAccountTier
 * @generated
 */
public class SupportWorkerAccountTierCacheModel implements CacheModel<SupportWorkerAccountTier>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{supportWorkerAccountTierId=");
		sb.append(supportWorkerAccountTierId);
		sb.append(", supportWorkerId=");
		sb.append(supportWorkerId);
		sb.append(", accountTier=");
		sb.append(accountTier);
		sb.append("}");

		return sb.toString();
	}

	public SupportWorkerAccountTier toEntityModel() {
		SupportWorkerAccountTierImpl supportWorkerAccountTierImpl = new SupportWorkerAccountTierImpl();

		supportWorkerAccountTierImpl.setSupportWorkerAccountTierId(supportWorkerAccountTierId);
		supportWorkerAccountTierImpl.setSupportWorkerId(supportWorkerId);
		supportWorkerAccountTierImpl.setAccountTier(accountTier);

		supportWorkerAccountTierImpl.resetOriginalValues();

		return supportWorkerAccountTierImpl;
	}

	public long supportWorkerAccountTierId;
	public long supportWorkerId;
	public int accountTier;
}