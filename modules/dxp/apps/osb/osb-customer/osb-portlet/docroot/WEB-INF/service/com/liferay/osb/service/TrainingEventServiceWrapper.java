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
 * This class is a wrapper for {@link TrainingEventService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TrainingEventService
 * @generated
 */
public class TrainingEventServiceWrapper implements TrainingEventService,
	ServiceWrapper<TrainingEventService> {
	public TrainingEventServiceWrapper(
		TrainingEventService trainingEventService) {
		_trainingEventService = trainingEventService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _trainingEventService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_trainingEventService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _trainingEventService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TrainingEventService getWrappedTrainingEventService() {
		return _trainingEventService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTrainingEventService(
		TrainingEventService trainingEventService) {
		_trainingEventService = trainingEventService;
	}

	public TrainingEventService getWrappedService() {
		return _trainingEventService;
	}

	public void setWrappedService(TrainingEventService trainingEventService) {
		_trainingEventService = trainingEventService;
	}

	private TrainingEventService _trainingEventService;
}