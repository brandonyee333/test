/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.definitions.web.internal.portlet.action;

import com.liferay.commerce.product.configuration.AttachmentsConfiguration;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.definitions.web.internal.upload.AttachmentsUploadResponseHandler;
import com.liferay.commerce.product.definitions.web.internal.upload.TempAttachmentsUploadFileEntryHandler;
import com.liferay.item.selector.ItemSelectorUploadResponseHandler;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.upload.UniqueFileNameProvider;
import com.liferay.upload.UploadHandler;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(
	configurationPid = "com.liferay.commerce.product.configuration.AttachmentsConfiguration",
	property = {
		"javax.portlet.name=" + CPPortletKeys.CP_DEFINITIONS,
		"mvc.command.name=/cp_definitions/upload_temp_attachment"
	},
	service = MVCActionCommand.class
)
public class UploadTempAttachmentMVCActionCommand extends BaseMVCActionCommand {

	@Activate
	protected void activate(Map<String, Object> properties) {
		AttachmentsConfiguration attachmentsConfiguration =
			ConfigurableUtil.createConfigurable(
				AttachmentsConfiguration.class, properties);

		_attachmentsUploadResponseHandler =
			new AttachmentsUploadResponseHandler(
				attachmentsConfiguration, _itemSelectorUploadResponseHandler);
		_tempAttachmentsUploadFileEntryHandler =
			new TempAttachmentsUploadFileEntryHandler(
				attachmentsConfiguration, _uniqueFileNameProvider);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		_uploadHandler.upload(
			_tempAttachmentsUploadFileEntryHandler,
			_attachmentsUploadResponseHandler, actionRequest, actionResponse);
	}

	private AttachmentsUploadResponseHandler _attachmentsUploadResponseHandler;

	@Reference
	private ItemSelectorUploadResponseHandler
		_itemSelectorUploadResponseHandler;

	private TempAttachmentsUploadFileEntryHandler
		_tempAttachmentsUploadFileEntryHandler;

	@Reference
	private UniqueFileNameProvider _uniqueFileNameProvider;

	@Reference
	private UploadHandler _uploadHandler;

}