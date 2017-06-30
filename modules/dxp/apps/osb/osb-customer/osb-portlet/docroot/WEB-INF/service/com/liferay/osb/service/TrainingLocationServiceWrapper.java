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
 * This class is a wrapper for {@link TrainingLocationService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TrainingLocationService
 * @generated
 */
public class TrainingLocationServiceWrapper implements TrainingLocationService,
	ServiceWrapper<TrainingLocationService> {
	public TrainingLocationServiceWrapper(
		TrainingLocationService trainingLocationService) {
		_trainingLocationService = trainingLocationService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _trainingLocationService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_trainingLocationService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _trainingLocationService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TrainingLocationService getWrappedTrainingLocationService() {
		return _trainingLocationService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTrainingLocationService(
		TrainingLocationService trainingLocationService) {
		_trainingLocationService = trainingLocationService;
	}

	public TrainingLocationService getWrappedService() {
		return _trainingLocationService;
	}

	public void setWrappedService(
		TrainingLocationService trainingLocationService) {
		_trainingLocationService = trainingLocationService;
	}

	private TrainingLocationService _trainingLocationService;
}