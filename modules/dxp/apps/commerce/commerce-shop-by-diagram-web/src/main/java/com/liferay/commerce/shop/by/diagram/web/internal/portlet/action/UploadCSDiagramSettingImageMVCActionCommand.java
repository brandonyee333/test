/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.shop.by.diagram.web.internal.portlet.action;

import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.shop.by.diagram.configuration.CSDiagramSettingImageConfiguration;
import com.liferay.commerce.shop.by.diagram.web.internal.upload.CSDiagramSettingImageUploadFileEntryHandler;
import com.liferay.commerce.shop.by.diagram.web.internal.upload.CSDiagramSettingImageUploadResponseHandler;
import com.liferay.item.selector.ItemSelectorUploadResponseHandler;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.json.JSONFactory;
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
	configurationPid = "com.liferay.commerce.shop.by.diagram.configuration.CSDiagramSettingImageConfiguration",
	property = {
		"javax.portlet.name=" + CPPortletKeys.CP_DEFINITIONS,
		"mvc.command.name=/cp_definitions/upload_cs_diagram_setting_image"
	},
	service = MVCActionCommand.class
)
public class UploadCSDiagramSettingImageMVCActionCommand
	extends BaseMVCActionCommand {

	@Activate
	protected void activate(Map<String, Object> properties) {
		CSDiagramSettingImageConfiguration csDiagramSettingImageConfiguration =
			ConfigurableUtil.createConfigurable(
				CSDiagramSettingImageConfiguration.class, properties);

		_csDiagramSettingImageUploadFileEntryHandler =
			new CSDiagramSettingImageUploadFileEntryHandler(
				csDiagramSettingImageConfiguration, _uniqueFileNameProvider);

		_csDiagramSettingImageUploadResponseHandler =
			new CSDiagramSettingImageUploadResponseHandler(
				csDiagramSettingImageConfiguration,
				_itemSelectorUploadResponseHandler);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		_uploadHandler.upload(
			_csDiagramSettingImageUploadFileEntryHandler,
			_csDiagramSettingImageUploadResponseHandler, actionRequest,
			actionResponse);
	}

	private CSDiagramSettingImageUploadFileEntryHandler
		_csDiagramSettingImageUploadFileEntryHandler;

	@Reference
	private CSDiagramSettingImageUploadResponseHandler
		_csDiagramSettingImageUploadResponseHandler;

	@Reference
	private ItemSelectorUploadResponseHandler
		_itemSelectorUploadResponseHandler;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private UniqueFileNameProvider _uniqueFileNameProvider;

	@Reference
	private UploadHandler _uploadHandler;

}