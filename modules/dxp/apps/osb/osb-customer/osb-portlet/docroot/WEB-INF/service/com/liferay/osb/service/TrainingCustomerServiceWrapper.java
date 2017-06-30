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
 * This class is a wrapper for {@link TrainingCustomerService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TrainingCustomerService
 * @generated
 */
public class TrainingCustomerServiceWrapper implements TrainingCustomerService,
	ServiceWrapper<TrainingCustomerService> {
	public TrainingCustomerServiceWrapper(
		TrainingCustomerService trainingCustomerService) {
		_trainingCustomerService = trainingCustomerService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _trainingCustomerService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_trainingCustomerService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _trainingCustomerService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TrainingCustomerService getWrappedTrainingCustomerService() {
		return _trainingCustomerService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTrainingCustomerService(
		TrainingCustomerService trainingCustomerService) {
		_trainingCustomerService = trainingCustomerService;
	}

	public TrainingCustomerService getWrappedService() {
		return _trainingCustomerService;
	}

	public void setWrappedService(
		TrainingCustomerService trainingCustomerService) {
		_trainingCustomerService = trainingCustomerService;
	}

	private TrainingCustomerService _trainingCustomerService;
}