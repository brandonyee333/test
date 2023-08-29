/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.catalog.web.internal.portlet.action;

import com.liferay.commerce.catalog.web.internal.upload.AttachmentsUploadResponseHandler;
import com.liferay.commerce.catalog.web.internal.upload.CommerceMediaDefaultImageUploadFileEntryHandler;
import com.liferay.commerce.product.configuration.AttachmentsConfiguration;
import com.liferay.commerce.product.constants.CPPortletKeys;
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
 * @author Alec Sloan
 * @author Alessio Antonio Rendina
 */
@Component(
	configurationPid = "com.liferay.commerce.product.configuration.AttachmentsConfiguration",
	property = {
		"javax.portlet.name=" + CPPortletKeys.COMMERCE_CATALOGS,
		"mvc.command.name=/commerce_catalogs/upload_commerce_media_default_image"
	},
	service = MVCActionCommand.class
)
public class UploadCommerceMediaDefaultImageMVCActionCommand
	extends BaseMVCActionCommand {

	@Activate
	protected void activate(Map<String, Object> properties) {
		AttachmentsConfiguration attachmentsConfiguration =
			ConfigurableUtil.createConfigurable(
				AttachmentsConfiguration.class, properties);

		_attachmentsUploadResponseHandler =
			new AttachmentsUploadResponseHandler(
				attachmentsConfiguration, _itemSelectorUploadResponseHandler);

		_commerceMediaDefaultImageUploadFileEntryHandler =
			new CommerceMediaDefaultImageUploadFileEntryHandler(
				attachmentsConfiguration, _uniqueFileNameProvider);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		_uploadHandler.upload(
			_commerceMediaDefaultImageUploadFileEntryHandler,
			_attachmentsUploadResponseHandler, actionRequest, actionResponse);
	}

	private AttachmentsUploadResponseHandler _attachmentsUploadResponseHandler;
	private CommerceMediaDefaultImageUploadFileEntryHandler
		_commerceMediaDefaultImageUploadFileEntryHandler;

	@Reference
	private ItemSelectorUploadResponseHandler
		_itemSelectorUploadResponseHandler;

	@Reference
	private UniqueFileNameProvider _uniqueFileNameProvider;

	@Reference
	private UploadHandler _uploadHandler;

}