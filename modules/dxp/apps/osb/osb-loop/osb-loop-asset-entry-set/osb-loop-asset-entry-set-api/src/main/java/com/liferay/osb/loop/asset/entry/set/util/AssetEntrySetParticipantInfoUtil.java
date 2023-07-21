/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.asset.entry.set.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.util.ObjectValuePair;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Matthew Kong
 * @author Timothy Bell
 */
public class AssetEntrySetParticipantInfoUtil {

	public static AssetEntrySetParticipantInfo
		getAssetEntrySetParticipantInfo() {

		return _instance._serviceTracker.getService();
	}

	public static JSONArray getAssetTagsJSONArray(
			long userId, String[] assetTagNames)
		throws PortalException {

		return getAssetEntrySetParticipantInfo().getAssetTagsJSONArray(
			userId, assetTagNames);
	}

	public static ObjectValuePair<Long, Long> getClassNameIdAndClassPKOVP(
		long userId) {

		return getAssetEntrySetParticipantInfo().getClassNameIdAndClassPKOVP(
			userId);
	}

	public static String[] getMembershipSearchTerms(long userId) {
		return getAssetEntrySetParticipantInfo().getMembershipSearchTerms(
			userId);
	}

	public static String getParticipantName(long classNameId, long classPK)
		throws PortalException {

		return getAssetEntrySetParticipantInfo().getParticipantName(
			classNameId, classPK);
	}

	public static String getSearchTerm(long classNameId, long classPK) {
		return getAssetEntrySetParticipantInfo().getSearchTerm(
			classNameId, classPK);
	}

	public static boolean isMember(
		long classNameId, long classPK, long sharedToClassNameId,
		long sharedToClassPK) {

		return getAssetEntrySetParticipantInfo().isMember(
			classNameId, classPK, sharedToClassNameId, sharedToClassPK);
	}

	private AssetEntrySetParticipantInfoUtil() {
		Bundle bundle = FrameworkUtil.getBundle(getClass());

		BundleContext bundleContext = bundle.getBundleContext();

		_serviceTracker = new ServiceTracker<>(
			bundleContext, AssetEntrySetParticipantInfo.class, null);

		_serviceTracker.open();
	}

	private static final AssetEntrySetParticipantInfoUtil _instance =
		new AssetEntrySetParticipantInfoUtil();

	private final ServiceTracker
		<AssetEntrySetParticipantInfo, AssetEntrySetParticipantInfo>
			_serviceTracker;

}