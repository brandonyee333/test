/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.asset.entry.set.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.image.ImageBag;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.repository.model.FileEntry;

import java.io.File;

import java.util.Map;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Timothy Bell
 */
public class AssetEntrySetImageUtil {

	public static JSONObject addImageFile(
			long userId, long classNameId, long classPK, String portletId,
			File file)
		throws PortalException {

		return getAssetEntrySetImage().addImageFile(
			userId, classNameId, classPK, portletId, file);
	}

	public static JSONObject addImageFile(
			long userId, long classNameId, long classPK, String portletId,
			File file, Map<String, String> imageMaxSizes)
		throws PortalException {

		return getAssetEntrySetImage().addImageFile(
			userId, classNameId, classPK, portletId, file, imageMaxSizes);
	}

	public static FileEntry addScaledImageFileEntry(
			long userId, long classNameId, long classPK, String portletId,
			ImageBag imageBag, String imageType, String originalFileName,
			String imageMaxSize)
		throws PortalException {

		return getAssetEntrySetImage().addScaledImageFileEntry(
			userId, classNameId, classPK, portletId, imageBag, imageType,
			originalFileName, imageMaxSize);
	}

	public static AssetEntrySetImage getAssetEntrySetImage() {
		return _instance._serviceTracker.getService();
	}

	public static JSONObject getImageJSONObject(
			JSONObject imageJSONObject, FileEntry fileEntry, String imageType)
		throws PortalException {

		return getAssetEntrySetImage().getImageJSONObject(
			imageJSONObject, fileEntry, imageType);
	}

	private AssetEntrySetImageUtil() {
		Bundle bundle = FrameworkUtil.getBundle(getClass());

		BundleContext bundleContext = bundle.getBundleContext();

		_serviceTracker = new ServiceTracker<>(
			bundleContext, AssetEntrySetImage.class, null);

		_serviceTracker.open();
	}

	private static final AssetEntrySetImageUtil _instance =
		new AssetEntrySetImageUtil();

	private final ServiceTracker<AssetEntrySetImage, AssetEntrySetImage>
		_serviceTracker;

}