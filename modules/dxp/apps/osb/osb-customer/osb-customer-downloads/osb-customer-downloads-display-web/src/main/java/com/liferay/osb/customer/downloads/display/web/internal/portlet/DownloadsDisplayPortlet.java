/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.downloads.display.web.internal.portlet;

import com.liferay.dynamic.data.mapping.util.DDMIndexer;
import com.liferay.journal.util.JournalConverter;
import com.liferay.osb.customer.downloads.display.constants.DownloadsDisplayPortletKeys;
import com.liferay.osb.customer.downloads.display.web.internal.util.DownloadsAssetCategoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"com.liferay.portlet.css-class-wrapper=osb-downloads-display-portlet",
		"com.liferay.portlet.display-category=category.osb",
		"com.liferay.portlet.footer-portlet-javascript=/dist/main.js",
		"com.liferay.portlet.header-portlet-css=/dist/main.css",
		"javax.portlet.display-name=OSB Downloads Display",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.mvc-command-names-default-views=/view",
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.name=" + DownloadsDisplayPortletKeys.DOWNLOADS_DISPLAY,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user"
	},
	service = Portlet.class
)
public class DownloadsDisplayPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		renderRequest.setAttribute(DDMIndexer.class.getName(), _ddmIndexer);
		renderRequest.setAttribute(
			DownloadsAssetCategoryUtil.class.getName(),
			_downloadsAssetCategoryUtil);
		renderRequest.setAttribute(
			JournalConverter.class.getName(), _journalConverter);

		super.render(renderRequest, renderResponse);
	}

	@Reference
	private DDMIndexer _ddmIndexer;

	@Reference
	private DownloadsAssetCategoryUtil _downloadsAssetCategoryUtil;

	@Reference
	private JournalConverter _journalConverter;

}