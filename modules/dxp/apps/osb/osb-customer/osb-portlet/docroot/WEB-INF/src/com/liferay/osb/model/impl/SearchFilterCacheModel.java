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

import com.liferay.osb.model.SearchFilter;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SearchFilter in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SearchFilter
 * @generated
 */
@ProviderType
public class SearchFilterCacheModel implements CacheModel<SearchFilter>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SearchFilterCacheModel)) {
			return false;
		}

		SearchFilterCacheModel searchFilterCacheModel = (SearchFilterCacheModel)obj;

		if (searchFilterId == searchFilterCacheModel.searchFilterId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, searchFilterId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{searchFilterId=");
		sb.append(searchFilterId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", filter=");
		sb.append(filter);
		sb.append(", visibility=");
		sb.append(visibility);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SearchFilter toEntityModel() {
		SearchFilterImpl searchFilterImpl = new SearchFilterImpl();

		searchFilterImpl.setSearchFilterId(searchFilterId);
		searchFilterImpl.setUserId(userId);

		if (userName == null) {
			searchFilterImpl.setUserName(StringPool.BLANK);
		}
		else {
			searchFilterImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			searchFilterImpl.setCreateDate(null);
		}
		else {
			searchFilterImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			searchFilterImpl.setModifiedDate(null);
		}
		else {
			searchFilterImpl.setModifiedDate(new Date(modifiedDate));
		}

		searchFilterImpl.setClassNameId(classNameId);

		if (name == null) {
			searchFilterImpl.setName(StringPool.BLANK);
		}
		else {
			searchFilterImpl.setName(name);
		}

		if (filter == null) {
			searchFilterImpl.setFilter(StringPool.BLANK);
		}
		else {
			searchFilterImpl.setFilter(filter);
		}

		searchFilterImpl.setVisibility(visibility);

		searchFilterImpl.resetOriginalValues();

		return searchFilterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		searchFilterId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		classNameId = objectInput.readLong();
		name = objectInput.readUTF();
		filter = objectInput.readUTF();

		visibility = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(searchFilterId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(classNameId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (filter == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(filter);
		}

		objectOutput.writeInt(visibility);
	}

	public long searchFilterId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public String name;
	public String filter;
	public int visibility;
}