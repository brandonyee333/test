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

/**
 * @author Timothy Bell
 */
public interface AssetEntrySetImage {

	public JSONObject addImageFile(
			long userId, long classNameId, long classPK, String portletId,
			File file)
		throws PortalException;

	public JSONObject addImageFile(
			long userId, long classNameId, long classPK, String portletId,
			File file, Map<String, String> imageMaxSizes)
		throws PortalException;

	public FileEntry addScaledImageFileEntry(
			long userId, long classNameId, long classPK, String portletId,
			ImageBag imageBag, String imageType, String originalFileName,
			String imageMaxSize)
		throws PortalException;

	public JSONObject getImageJSONObject(
			JSONObject imageJSONObject, FileEntry fileEntry, String imageType)
		throws PortalException;

}