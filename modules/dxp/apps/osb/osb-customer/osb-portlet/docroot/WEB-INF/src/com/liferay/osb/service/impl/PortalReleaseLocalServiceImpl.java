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

package com.liferay.osb.service.impl;

import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.PortalReleaseBuildNumberException;
import com.liferay.osb.PortalReleaseNameException;
import com.liferay.osb.model.PortalRelease;
import com.liferay.osb.service.base.PortalReleaseLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Yury Butrymovich
 */
public class PortalReleaseLocalServiceImpl
	extends PortalReleaseLocalServiceBaseImpl {

	public PortalRelease addPortalRelease(
			String versionName, int buildNumber, boolean ee,
			boolean marketplaceSupport, boolean osgiSupport,
			boolean paclSupport, boolean hidden)
		throws PortalException, SystemException {

		validate(versionName, buildNumber);

		long portalReleaseId = counterLocalService.increment();

		PortalRelease portalRelease = portalReleasePersistence.create(
			portalReleaseId);

		portalRelease.setVersionName(versionName);
		portalRelease.setBuildNumber(buildNumber);
		portalRelease.setEe(ee);
		portalRelease.setMarketplaceSupport(marketplaceSupport);
		portalRelease.setOsgiSupport(osgiSupport);
		portalRelease.setPaclSupport(paclSupport);
		portalRelease.setHidden(hidden);

		portalReleasePersistence.update(portalRelease, false);

		return portalRelease;
	}

	public PortalRelease fetchBuildNumberPortalRelease(int buildNumber)
		throws SystemException {

		return portalReleasePersistence.fetchByBuildNumber(buildNumber);
	}

	public PortalRelease getPortalRelease(int buildNumber)
		throws PortalException, SystemException {

		return portalReleasePersistence.findByBuildNumber(buildNumber);
	}

	public List<PortalRelease> getPortalReleases(boolean marketplaceSupport)
		throws SystemException {

		return portalReleasePersistence.findByMarketplaceSupport(
				marketplaceSupport);
	}

	public List<PortalRelease> getPortalReleases(
			boolean marketplaceSupport, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		return portalReleasePersistence.findByMarketplaceSupport(
			marketplaceSupport, start, end, obc);
	}

	public PortalRelease updatePortalRelease(
			long portalReleaseId, String versionName, int buildNumber,
			String fixPackName, boolean ee, boolean marketplaceSupport,
			boolean osgiSupport, boolean paclSupport, boolean hidden)
		throws PortalException, SystemException {

		PortalRelease portalRelease = portalReleasePersistence.findByPrimaryKey(
			portalReleaseId);

		validate(versionName, buildNumber);

		portalRelease.setVersionName(versionName);
		portalRelease.setBuildNumber(buildNumber);
		portalRelease.setFixPackName(fixPackName);
		portalRelease.setEe(ee);
		portalRelease.setMarketplaceSupport(marketplaceSupport);
		portalRelease.setOsgiSupport(osgiSupport);
		portalRelease.setPaclSupport(paclSupport);
		portalRelease.setHidden(hidden);

		portalReleasePersistence.update(portalRelease, false);

		return portalRelease;
	}

	protected void validate(String versionName, int buildNumber)
		throws PortalException {

		if (Validator.isNull(versionName) || versionName.isEmpty()) {
			throw new PortalReleaseNameException();
		}

		if (buildNumber <= 0) {
			throw new PortalReleaseBuildNumberException();
		}
	}

}