/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.order.content.web.internal.upload;

import com.liferay.document.library.kernel.exception.FileExtensionException;
import com.liferay.item.selector.ItemSelectorUploadResponseHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.servlet.ServletResponseConstants;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.upload.UploadResponseHandler;

import javax.portlet.PortletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CSVUploadResponseHandler implements UploadResponseHandler {

	public CSVUploadResponseHandler(
		ItemSelectorUploadResponseHandler itemSelectorUploadResponseHandler) {

		_itemSelectorUploadResponseHandler = itemSelectorUploadResponseHandler;
	}

	@Override
	public JSONObject onFailure(
			PortletRequest portletRequest, PortalException portalException)
		throws PortalException {

		JSONObject jsonObject = _itemSelectorUploadResponseHandler.onFailure(
			portletRequest, portalException);

		if (portalException instanceof FileExtensionException) {
			jsonObject.put(
				"error",
				JSONUtil.put(
					"errorType",
					ServletResponseConstants.SC_FILE_EXTENSION_EXCEPTION
				).put(
					"message", ".csv"
				));
		}
		else {
			throw portalException;
		}

		return jsonObject;
	}

	@Override
	public JSONObject onSuccess(
			UploadPortletRequest uploadPortletRequest, FileEntry fileEntry)
		throws PortalException {

		return _itemSelectorUploadResponseHandler.onSuccess(
			uploadPortletRequest, fileEntry);
	}

	private final ItemSelectorUploadResponseHandler
		_itemSelectorUploadResponseHandler;

}