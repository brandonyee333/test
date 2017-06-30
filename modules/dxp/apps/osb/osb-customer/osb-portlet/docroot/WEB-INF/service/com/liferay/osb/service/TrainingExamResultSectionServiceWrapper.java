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
 * This class is a wrapper for {@link TrainingExamResultSectionService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TrainingExamResultSectionService
 * @generated
 */
public class TrainingExamResultSectionServiceWrapper
	implements TrainingExamResultSectionService,
		ServiceWrapper<TrainingExamResultSectionService> {
	public TrainingExamResultSectionServiceWrapper(
		TrainingExamResultSectionService trainingExamResultSectionService) {
		_trainingExamResultSectionService = trainingExamResultSectionService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _trainingExamResultSectionService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_trainingExamResultSectionService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _trainingExamResultSectionService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TrainingExamResultSectionService getWrappedTrainingExamResultSectionService() {
		return _trainingExamResultSectionService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTrainingExamResultSectionService(
		TrainingExamResultSectionService trainingExamResultSectionService) {
		_trainingExamResultSectionService = trainingExamResultSectionService;
	}

	public TrainingExamResultSectionService getWrappedService() {
		return _trainingExamResultSectionService;
	}

	public void setWrappedService(
		TrainingExamResultSectionService trainingExamResultSectionService) {
		_trainingExamResultSectionService = trainingExamResultSectionService;
	}

	private TrainingExamResultSectionService _trainingExamResultSectionService;
}