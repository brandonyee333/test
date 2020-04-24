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