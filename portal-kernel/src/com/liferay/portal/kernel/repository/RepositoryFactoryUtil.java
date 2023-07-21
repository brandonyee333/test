/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.repository;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Adolfo Pérez
 */
public class RepositoryFactoryUtil {

	public static LocalRepository createLocalRepository(long repositoryId)
		throws PortalException {

		return getRepositoryFactory().createLocalRepository(repositoryId);
	}

	public static Repository createRepository(long repositoryId)
		throws PortalException {

		return getRepositoryFactory().createRepository(repositoryId);
	}

	public static RepositoryFactory getRepositoryFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			RepositoryFactoryUtil.class);

		return _repositoryFactory;
	}

	public void setRepositoryFactory(RepositoryFactory repositoryFactory) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_repositoryFactory = repositoryFactory;
	}

	private static RepositoryFactory _repositoryFactory;

}