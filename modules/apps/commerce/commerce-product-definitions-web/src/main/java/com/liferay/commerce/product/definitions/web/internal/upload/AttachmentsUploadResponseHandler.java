/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.definitions.web.internal.upload;

import com.liferay.commerce.product.configuration.AttachmentsConfiguration;
import com.liferay.commerce.product.exception.CPAttachmentFileEntryNameException;
import com.liferay.commerce.product.exception.CPAttachmentFileEntrySizeException;
import com.liferay.item.selector.ItemSelectorUploadResponseHandler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.servlet.ServletResponseConstants;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.upload.UploadResponseHandler;

import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marco Leo
 */
@Component(
	configurationPid = "com.liferay.commerce.product.configuration.AttachmentsConfiguration",
	service = AttachmentsUploadResponseHandler.class
)
public class AttachmentsUploadResponseHandler implements UploadResponseHandler {

	public AttachmentsUploadResponseHandler(
		AttachmentsConfiguration attachmentsConfiguration,
		ItemSelectorUploadResponseHandler itemSelectorUploadResponseHandler) {

		_attachmentsConfiguration = attachmentsConfiguration;
		_itemSelectorUploadResponseHandler = itemSelectorUploadResponseHandler;
	}

	@Override
	public JSONObject onFailure(
			PortletRequest portletRequest, PortalException portalException)
		throws PortalException {

		JSONObject jsonObject = _itemSelectorUploadResponseHandler.onFailure(
			portletRequest, portalException);

		if (portalException instanceof CPAttachmentFileEntryNameException ||
			portalException instanceof CPAttachmentFileEntrySizeException) {

			String errorMessage = StringPool.BLANK;
			int errorType = 0;

			if (portalException instanceof CPAttachmentFileEntryNameException) {
				errorType =
					ServletResponseConstants.SC_FILE_EXTENSION_EXCEPTION;

				errorMessage = StringUtil.merge(
					_attachmentsConfiguration.imageExtensions());
			}
			else if (portalException instanceof
						CPAttachmentFileEntrySizeException) {

				errorType = ServletResponseConstants.SC_FILE_SIZE_EXCEPTION;
			}

			jsonObject.put(
				"error",
				JSONUtil.put(
					"errorType", errorType
				).put(
					"message", errorMessage
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

	private final AttachmentsConfiguration _attachmentsConfiguration;
	private final ItemSelectorUploadResponseHandler
		_itemSelectorUploadResponseHandler;

}