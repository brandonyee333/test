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
 * This class is a wrapper for {@link CorpGroupService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CorpGroupService
 * @generated
 */
public class CorpGroupServiceWrapper implements CorpGroupService,
	ServiceWrapper<CorpGroupService> {
	public CorpGroupServiceWrapper(CorpGroupService corpGroupService) {
		_corpGroupService = corpGroupService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _corpGroupService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_corpGroupService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _corpGroupService.invokeMethod(name, parameterTypes, arguments);
	}

	public com.liferay.osb.model.CorpGroup addCorpGroup(java.lang.String name,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.io.File logoFile, java.lang.String emailAddress,
		java.lang.String website,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupService.addCorpGroup(name, descriptionMap, logoFile,
			emailAddress, website, serviceContext);
	}

	public com.liferay.osb.model.CorpGroup deleteCorpGroup(long corpGroupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupService.deleteCorpGroup(corpGroupId);
	}

	public com.liferay.osb.model.CorpGroup updateCorpGroup(long corpGroupId,
		java.lang.String name,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.io.File logoFile, java.lang.String emailAddress,
		java.lang.String website,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupService.updateCorpGroup(corpGroupId, name,
			descriptionMap, logoFile, emailAddress, website, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public CorpGroupService getWrappedCorpGroupService() {
		return _corpGroupService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedCorpGroupService(CorpGroupService corpGroupService) {
		_corpGroupService = corpGroupService;
	}

	public CorpGroupService getWrappedService() {
		return _corpGroupService;
	}

	public void setWrappedService(CorpGroupService corpGroupService) {
		_corpGroupService = corpGroupService;
	}

	private CorpGroupService _corpGroupService;
}