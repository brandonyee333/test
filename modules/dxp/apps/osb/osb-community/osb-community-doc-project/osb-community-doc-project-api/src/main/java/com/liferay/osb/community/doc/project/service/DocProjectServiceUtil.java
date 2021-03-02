/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.community.doc.project.service;

import com.liferay.osb.community.doc.project.model.DocProject;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * Provides the remote service utility for DocProject. This utility wraps
 * <code>com.liferay.osb.community.doc.project.service.impl.DocProjectServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Ryan Park
 * @see DocProjectService
 * @generated
 */
public class DocProjectServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.community.doc.project.service.impl.DocProjectServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static DocProject addDocProject(
			String name, String description, String iconFileName,
			java.io.File iconFile, boolean unlisted, String type,
			String typeSettings, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addDocProject(
			name, description, iconFileName, iconFile, unlisted, type,
			typeSettings, status, serviceContext);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static DocProject updateDocProject(
			long docProjectId, String name, String description,
			String iconFileName, java.io.File iconFile, boolean unlisted,
			String type, String typeSettings, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateDocProject(
			docProjectId, name, description, iconFileName, iconFile, unlisted,
			type, typeSettings, status, serviceContext);
	}

	public static DocProjectService getService() {
		return _service;
	}

	private static volatile DocProjectService _service;

}