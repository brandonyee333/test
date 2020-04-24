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