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

package com.liferay.osb.model.impl;

import com.liferay.osb.model.PortalRelease;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing PortalRelease in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PortalRelease
 * @generated
 */
public class PortalReleaseCacheModel implements CacheModel<PortalRelease>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{portalReleaseId=");
		sb.append(portalReleaseId);
		sb.append(", versionName=");
		sb.append(versionName);
		sb.append(", buildNumber=");
		sb.append(buildNumber);
		sb.append(", fixPackName=");
		sb.append(fixPackName);
		sb.append(", ee=");
		sb.append(ee);
		sb.append(", marketplaceSupport=");
		sb.append(marketplaceSupport);
		sb.append(", osgiSupport=");
		sb.append(osgiSupport);
		sb.append(", paclSupport=");
		sb.append(paclSupport);
		sb.append(", hidden=");
		sb.append(hidden);
		sb.append("}");

		return sb.toString();
	}

	public PortalRelease toEntityModel() {
		PortalReleaseImpl portalReleaseImpl = new PortalReleaseImpl();

		portalReleaseImpl.setPortalReleaseId(portalReleaseId);

		if (versionName == null) {
			portalReleaseImpl.setVersionName(StringPool.BLANK);
		}
		else {
			portalReleaseImpl.setVersionName(versionName);
		}

		portalReleaseImpl.setBuildNumber(buildNumber);

		if (fixPackName == null) {
			portalReleaseImpl.setFixPackName(StringPool.BLANK);
		}
		else {
			portalReleaseImpl.setFixPackName(fixPackName);
		}

		portalReleaseImpl.setEe(ee);
		portalReleaseImpl.setMarketplaceSupport(marketplaceSupport);
		portalReleaseImpl.setOsgiSupport(osgiSupport);
		portalReleaseImpl.setPaclSupport(paclSupport);
		portalReleaseImpl.setHidden(hidden);

		portalReleaseImpl.resetOriginalValues();

		return portalReleaseImpl;
	}

	public long portalReleaseId;
	public String versionName;
	public int buildNumber;
	public String fixPackName;
	public boolean ee;
	public boolean marketplaceSupport;
	public boolean osgiSupport;
	public boolean paclSupport;
	public boolean hidden;
}