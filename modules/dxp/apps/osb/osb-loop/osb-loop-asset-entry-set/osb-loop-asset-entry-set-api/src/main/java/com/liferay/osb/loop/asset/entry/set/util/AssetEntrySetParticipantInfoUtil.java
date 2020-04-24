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