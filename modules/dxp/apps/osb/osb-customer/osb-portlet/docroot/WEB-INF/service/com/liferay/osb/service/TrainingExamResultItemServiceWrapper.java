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
 * This class is a wrapper for {@link TrainingExamResultItemService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TrainingExamResultItemService
 * @generated
 */
public class TrainingExamResultItemServiceWrapper
	implements TrainingExamResultItemService,
		ServiceWrapper<TrainingExamResultItemService> {
	public TrainingExamResultItemServiceWrapper(
		TrainingExamResultItemService trainingExamResultItemService) {
		_trainingExamResultItemService = trainingExamResultItemService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _trainingExamResultItemService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_trainingExamResultItemService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _trainingExamResultItemService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TrainingExamResultItemService getWrappedTrainingExamResultItemService() {
		return _trainingExamResultItemService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTrainingExamResultItemService(
		TrainingExamResultItemService trainingExamResultItemService) {
		_trainingExamResultItemService = trainingExamResultItemService;
	}

	public TrainingExamResultItemService getWrappedService() {
		return _trainingExamResultItemService;
	}

	public void setWrappedService(
		TrainingExamResultItemService trainingExamResultItemService) {
		_trainingExamResultItemService = trainingExamResultItemService;
	}

	private TrainingExamResultItemService _trainingExamResultItemService;
}