/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.asset.entry.set.service;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * Provides the remote service utility for AssetEntrySet. This utility wraps
 * <code>com.liferay.osb.loop.asset.entry.set.service.impl.AssetEntrySetServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySetService
 * @generated
 */
public class AssetEntrySetServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.loop.asset.entry.set.service.impl.AssetEntrySetServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.json.JSONObject addFileAttachment(
			java.io.File file)
		throws PortalException {

		return getService().addFileAttachment(file);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static AssetEntrySetService getService() {
		return _service;
	}

	public static void setService(AssetEntrySetService service) {
		_service = service;
	}

	private static volatile AssetEntrySetService _service;

}