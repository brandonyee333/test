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

import com.liferay.osb.model.TrainingCourse;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing TrainingCourse in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TrainingCourse
 * @generated
 */
public class TrainingCourseCacheModel implements CacheModel<TrainingCourse>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{trainingCourseId=");
		sb.append(trainingCourseId);
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
		sb.append(", description=");
		sb.append(description);
		sb.append(", creditAmount=");
		sb.append(creditAmount);
		sb.append(", courseURL=");
		sb.append(courseURL);
		sb.append(", archived=");
		sb.append(archived);
		sb.append("}");

		return sb.toString();
	}

	public TrainingCourse toEntityModel() {
		TrainingCourseImpl trainingCourseImpl = new TrainingCourseImpl();

		trainingCourseImpl.setTrainingCourseId(trainingCourseId);
		trainingCourseImpl.setUserId(userId);

		if (userName == null) {
			trainingCourseImpl.setUserName(StringPool.BLANK);
		}
		else {
			trainingCourseImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			trainingCourseImpl.setCreateDate(null);
		}
		else {
			trainingCourseImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			trainingCourseImpl.setModifiedDate(null);
		}
		else {
			trainingCourseImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			trainingCourseImpl.setName(StringPool.BLANK);
		}
		else {
			trainingCourseImpl.setName(name);
		}

		if (description == null) {
			trainingCourseImpl.setDescription(StringPool.BLANK);
		}
		else {
			trainingCourseImpl.setDescription(description);
		}

		trainingCourseImpl.setCreditAmount(creditAmount);

		if (courseURL == null) {
			trainingCourseImpl.setCourseURL(StringPool.BLANK);
		}
		else {
			trainingCourseImpl.setCourseURL(courseURL);
		}

		trainingCourseImpl.setArchived(archived);

		trainingCourseImpl.resetOriginalValues();

		return trainingCourseImpl;
	}

	public long trainingCourseId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
	public int creditAmount;
	public String courseURL;
	public boolean archived;
}