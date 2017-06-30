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

import com.liferay.osb.model.OfferingBundle;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing OfferingBundle in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see OfferingBundle
 * @generated
 */
public class OfferingBundleCacheModel implements CacheModel<OfferingBundle>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{offeringBundleId=");
		sb.append(offeringBundleId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	public OfferingBundle toEntityModel() {
		OfferingBundleImpl offeringBundleImpl = new OfferingBundleImpl();

		offeringBundleImpl.setOfferingBundleId(offeringBundleId);
		offeringBundleImpl.setUserId(userId);

		if (userName == null) {
			offeringBundleImpl.setUserName(StringPool.BLANK);
		}
		else {
			offeringBundleImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			offeringBundleImpl.setCreateDate(null);
		}
		else {
			offeringBundleImpl.setCreateDate(new Date(createDate));
		}

		if (name == null) {
			offeringBundleImpl.setName(StringPool.BLANK);
		}
		else {
			offeringBundleImpl.setName(name);
		}

		offeringBundleImpl.resetOriginalValues();

		return offeringBundleImpl;
	}

	public long offeringBundleId;
	public long userId;
	public String userName;
	public long createDate;
	public String name;
}