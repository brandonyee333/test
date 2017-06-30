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

package com.liferay.osb.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link TrainingCourseService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TrainingCourseService
 * @generated
 */
public class TrainingCourseServiceWrapper implements TrainingCourseService,
	ServiceWrapper<TrainingCourseService> {
	public TrainingCourseServiceWrapper(
		TrainingCourseService trainingCourseService) {
		_trainingCourseService = trainingCourseService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _trainingCourseService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_trainingCourseService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _trainingCourseService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TrainingCourseService getWrappedTrainingCourseService() {
		return _trainingCourseService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTrainingCourseService(
		TrainingCourseService trainingCourseService) {
		_trainingCourseService = trainingCourseService;
	}

	public TrainingCourseService getWrappedService() {
		return _trainingCourseService;
	}

	public void setWrappedService(TrainingCourseService trainingCourseService) {
		_trainingCourseService = trainingCourseService;
	}

	private TrainingCourseService _trainingCourseService;
}