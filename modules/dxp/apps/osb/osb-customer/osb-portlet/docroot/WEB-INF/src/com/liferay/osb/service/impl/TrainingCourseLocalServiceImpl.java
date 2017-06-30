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

package com.liferay.osb.service.impl;

import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.RequiredTrainingCourseException;
import com.liferay.osb.TrainingCourseNameException;
import com.liferay.osb.model.TrainingCourse;
import com.liferay.osb.service.base.TrainingCourseLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.User;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class TrainingCourseLocalServiceImpl
	extends TrainingCourseLocalServiceBaseImpl {

	public TrainingCourse addTrainingCourse(
			long userId, String name, String description, int creditAmount,
			String courseURL, boolean archived)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(name);

		long trainingCourseId = counterLocalService.increment();

		TrainingCourse trainingCourse = trainingCoursePersistence.create(
			trainingCourseId);

		trainingCourse.setUserId(user.getUserId());
		trainingCourse.setUserName(user.getFullName());
		trainingCourse.setCreateDate(now);
		trainingCourse.setModifiedDate(now);
		trainingCourse.setName(name);
		trainingCourse.setDescription(description);
		trainingCourse.setCreditAmount(creditAmount);
		trainingCourse.setCourseURL(courseURL);
		trainingCourse.setArchived(archived);

		trainingCoursePersistence.update(trainingCourse, false);

		return trainingCourse;
	}

	@Override
	public TrainingCourse deleteTrainingCourse(long trainingCourseId)
		throws PortalException, SystemException {

		if (trainingEventPersistence.countByTrainingCourseId(
				trainingCourseId) > 0) {

			throw new RequiredTrainingCourseException();
		}

		return trainingCoursePersistence.remove(trainingCourseId);
	}

	public List<TrainingCourse> getTrainingCourses(
			int start, int end, OrderByComparator obc)
		throws SystemException {

		return trainingCoursePersistence.findAll(start, end, obc);
	}

	public TrainingCourse updateTrainingCourse(
			long trainingCourseId, String name, String description,
			int creditAmount, String courseURL, boolean archived)
		throws PortalException, SystemException {

		validate(name);

		TrainingCourse trainingCourse =
			trainingCoursePersistence.findByPrimaryKey(trainingCourseId);

		trainingCourse.setModifiedDate(new Date());
		trainingCourse.setName(name);
		trainingCourse.setDescription(description);
		trainingCourse.setCreditAmount(creditAmount);
		trainingCourse.setCourseURL(courseURL);
		trainingCourse.setArchived(archived);

		trainingCoursePersistence.update(trainingCourse, false);

		return trainingCourse;
	}

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new TrainingCourseNameException();
		}
	}

}