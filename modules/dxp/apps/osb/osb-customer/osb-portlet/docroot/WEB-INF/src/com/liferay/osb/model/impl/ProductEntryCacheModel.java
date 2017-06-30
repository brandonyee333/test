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

import com.liferay.osb.model.ProductEntry;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing ProductEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ProductEntry
 * @generated
 */
public class ProductEntryCacheModel implements CacheModel<ProductEntry>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{productEntryId=");
		sb.append(productEntryId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", type=");
		sb.append(type);
		sb.append(", environment=");
		sb.append(environment);
		sb.append(", versionsListType=");
		sb.append(versionsListType);
		sb.append("}");

		return sb.toString();
	}

	public ProductEntry toEntityModel() {
		ProductEntryImpl productEntryImpl = new ProductEntryImpl();

		productEntryImpl.setProductEntryId(productEntryId);
		productEntryImpl.setUserId(userId);

		if (userName == null) {
			productEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			productEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			productEntryImpl.setCreateDate(null);
		}
		else {
			productEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			productEntryImpl.setModifiedDate(null);
		}
		else {
			productEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			productEntryImpl.setName(StringPool.BLANK);
		}
		else {
			productEntryImpl.setName(name);
		}

		productEntryImpl.setType(type);
		productEntryImpl.setEnvironment(environment);

		if (versionsListType == null) {
			productEntryImpl.setVersionsListType(StringPool.BLANK);
		}
		else {
			productEntryImpl.setVersionsListType(versionsListType);
		}

		productEntryImpl.resetOriginalValues();

		return productEntryImpl;
	}

	public long productEntryId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public int type;
	public int environment;
	public String versionsListType;
}