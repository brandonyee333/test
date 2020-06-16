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

package com.liferay.osb.community.doc.project.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for DocProject. This utility wraps
 * {@link com.liferay.osb.community.doc.project.service.impl.DocProjectServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Ryan Park
 * @see DocProjectService
 * @see com.liferay.osb.community.doc.project.service.base.DocProjectServiceBaseImpl
 * @see com.liferay.osb.community.doc.project.service.impl.DocProjectServiceImpl
 * @generated
 */
@ProviderType
public class DocProjectServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.community.doc.project.service.impl.DocProjectServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.community.doc.project.model.DocProject addDocProject(
		java.lang.String name, java.lang.String description,
		java.lang.String iconFileName, java.io.File iconFile, boolean unlisted,
		java.lang.String type, java.lang.String typeSettings, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addDocProject(name, description, iconFileName, iconFile,
			unlisted, type, typeSettings, status, serviceContext);
	}

	public static com.liferay.osb.community.doc.project.model.DocProject updateDocProject(
		long docProjectId, java.lang.String name, java.lang.String description,
		java.lang.String iconFileName, java.io.File iconFile, boolean unlisted,
		java.lang.String type, java.lang.String typeSettings, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateDocProject(docProjectId, name, description,
			iconFileName, iconFile, unlisted, type, typeSettings, status,
			serviceContext);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static DocProjectService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DocProjectService, DocProjectService> _serviceTracker =
		ServiceTrackerFactory.open(DocProjectService.class);
}