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

import com.liferay.osb.model.TrainingLocation;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing TrainingLocation in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TrainingLocation
 * @generated
 */
public class TrainingLocationCacheModel implements CacheModel<TrainingLocation>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{trainingLocationId=");
		sb.append(trainingLocationId);
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
		sb.append(", addressId=");
		sb.append(addressId);
		sb.append("}");

		return sb.toString();
	}

	public TrainingLocation toEntityModel() {
		TrainingLocationImpl trainingLocationImpl = new TrainingLocationImpl();

		trainingLocationImpl.setTrainingLocationId(trainingLocationId);
		trainingLocationImpl.setUserId(userId);

		if (userName == null) {
			trainingLocationImpl.setUserName(StringPool.BLANK);
		}
		else {
			trainingLocationImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			trainingLocationImpl.setCreateDate(null);
		}
		else {
			trainingLocationImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			trainingLocationImpl.setModifiedDate(null);
		}
		else {
			trainingLocationImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			trainingLocationImpl.setName(StringPool.BLANK);
		}
		else {
			trainingLocationImpl.setName(name);
		}

		trainingLocationImpl.setAddressId(addressId);

		trainingLocationImpl.resetOriginalValues();

		return trainingLocationImpl;
	}

	public long trainingLocationId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public long addressId;
}