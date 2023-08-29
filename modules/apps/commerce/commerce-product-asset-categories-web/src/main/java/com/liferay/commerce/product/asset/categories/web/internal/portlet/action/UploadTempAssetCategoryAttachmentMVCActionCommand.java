/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.asset.categories.web.internal.portlet.action;

import com.liferay.commerce.product.asset.categories.web.internal.constants.CommerceProductAssetCategoriesPortletKeys;
import com.liferay.commerce.product.asset.categories.web.internal.upload.AssetCategoryAttachmentsUploadResponseHandler;
import com.liferay.commerce.product.asset.categories.web.internal.upload.TempAssetCategoryAttachmentsUploadFileEntryHandler;
import com.liferay.commerce.product.configuration.AttachmentsConfiguration;
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
 * @author Alessio Antonio Rendina
 */
@Component(
	configurationPid = "com.liferay.commerce.product.configuration.AttachmentsConfiguration",
	property = {
		"javax.portlet.name=" + CommerceProductAssetCategoriesPortletKeys.ASSET_CATEGORIES_ADMIN,
		"mvc.command.name=/commerce_product_asset_categories/upload_temp_asset_category_attachment"
	},
	service = MVCActionCommand.class
)
public class UploadTempAssetCategoryAttachmentMVCActionCommand
	extends BaseMVCActionCommand {

	@Activate
	protected void activate(Map<String, Object> properties) {
		AttachmentsConfiguration attachmentsConfiguration =
			ConfigurableUtil.createConfigurable(
				AttachmentsConfiguration.class, properties);

		_assetCategoryAttachmentsUploadResponseHandler =
			new AssetCategoryAttachmentsUploadResponseHandler(
				attachmentsConfiguration, _itemSelectorUploadResponseHandler);

		_tempAssetCategoryAttachmentsUploadFileEntryHandler =
			new TempAssetCategoryAttachmentsUploadFileEntryHandler(
				attachmentsConfiguration, _uniqueFileNameProvider);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		_uploadHandler.upload(
			_tempAssetCategoryAttachmentsUploadFileEntryHandler,
			_assetCategoryAttachmentsUploadResponseHandler, actionRequest,
			actionResponse);
	}

	private AssetCategoryAttachmentsUploadResponseHandler
		_assetCategoryAttachmentsUploadResponseHandler;

	@Reference
	private ItemSelectorUploadResponseHandler
		_itemSelectorUploadResponseHandler;

	private TempAssetCategoryAttachmentsUploadFileEntryHandler
		_tempAssetCategoryAttachmentsUploadFileEntryHandler;

	@Reference
	private UniqueFileNameProvider _uniqueFileNameProvider;

	@Reference
	private UploadHandler _uploadHandler;

}