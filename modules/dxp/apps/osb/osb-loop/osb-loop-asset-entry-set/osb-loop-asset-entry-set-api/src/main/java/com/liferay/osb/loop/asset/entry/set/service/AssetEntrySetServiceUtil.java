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

package com.liferay.osb.loop.asset.entry.set.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for AssetEntrySet. This utility wraps
 * {@link com.liferay.osb.loop.asset.entry.set.service.impl.AssetEntrySetServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySetService
 * @see com.liferay.osb.loop.asset.entry.set.service.base.AssetEntrySetServiceBaseImpl
 * @see com.liferay.osb.loop.asset.entry.set.service.impl.AssetEntrySetServiceImpl
 * @generated
 */
@ProviderType
public class AssetEntrySetServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.loop.asset.entry.set.service.impl.AssetEntrySetServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.json.JSONObject addFileAttachment(
		java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().addFileAttachment(file);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static AssetEntrySetService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AssetEntrySetService, AssetEntrySetService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(AssetEntrySetService.class);

		ServiceTracker<AssetEntrySetService, AssetEntrySetService> serviceTracker =
			new ServiceTracker<AssetEntrySetService, AssetEntrySetService>(bundle.getBundleContext(),
				AssetEntrySetService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}