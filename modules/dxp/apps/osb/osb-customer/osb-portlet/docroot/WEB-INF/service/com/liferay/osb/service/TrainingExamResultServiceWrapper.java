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
 * This class is a wrapper for {@link TrainingExamResultService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TrainingExamResultService
 * @generated
 */
public class TrainingExamResultServiceWrapper
	implements TrainingExamResultService,
		ServiceWrapper<TrainingExamResultService> {
	public TrainingExamResultServiceWrapper(
		TrainingExamResultService trainingExamResultService) {
		_trainingExamResultService = trainingExamResultService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _trainingExamResultService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_trainingExamResultService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _trainingExamResultService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TrainingExamResultService getWrappedTrainingExamResultService() {
		return _trainingExamResultService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTrainingExamResultService(
		TrainingExamResultService trainingExamResultService) {
		_trainingExamResultService = trainingExamResultService;
	}

	public TrainingExamResultService getWrappedService() {
		return _trainingExamResultService;
	}

	public void setWrappedService(
		TrainingExamResultService trainingExamResultService) {
		_trainingExamResultService = trainingExamResultService;
	}

	private TrainingExamResultService _trainingExamResultService;
}