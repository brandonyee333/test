/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.customer.web.internal.portlet;

import com.liferay.knowledge.base.constants.KBPortletKeys;
import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.knowledge.base.service.KBArticleLocalService;
import com.liferay.osb.customer.web.internal.constants.OSBCustomerConstants;
import com.liferay.osb.customer.web.internal.constants.OSBCustomerWebKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alan Zhang
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=osb-knowledge-base-knowledge-base-wrapper-portlet",
		"com.liferay.portlet.display-category=category.osb",
		"com.liferay.portlet.header-portlet-css=/knowledge_base_wrapper/css/main.css",
		"com.liferay.portlet.icon=/icon.png",
		"javax.portlet.display-name=OSB Knowledge Base Knowledge Base Wrapper",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/knowledge_base_wrapper/",
		"javax.portlet.init-param.view-template=/knowledge_base_wrapper/view.jsp",
		"javax.portlet.name=5_WAR_osbknowledgebaseportlet",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class KnowledgeBaseWrapperPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		LiferayPortletRequest liferayPortletRequest =
			(LiferayPortletRequest)renderRequest;

		HttpServletRequest request =
			liferayPortletRequest.getOriginalHttpServletRequest();

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String redirect = ParamUtil.getString(
			request, "_" + KBPortletKeys.KNOWLEDGE_BASE_DISPLAY + "_redirect");
		String urlTitle = ParamUtil.getString(
			request, "_" + KBPortletKeys.KNOWLEDGE_BASE_DISPLAY + "_urlTitle");

		try {
			KBArticle kbArticle = getKBArticle(themeDisplay, urlTitle);

			renderRequest.setAttribute(
				OSBCustomerWebKeys.KNOWLEDGE_BASE_KB_ARTICLE, kbArticle);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		renderRequest.setAttribute(OSBCustomerWebKeys.REDIRECT, redirect);

		super.render(renderRequest, renderResponse);
	}

	protected KBArticle getKBArticle(ThemeDisplay themeDisplay, String urlTitle)
		throws PortalException {

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.getExistingPortletSetup(
				themeDisplay.getLayout(), KBPortletKeys.KNOWLEDGE_BASE_DISPLAY);

		long parentResourcePrimKey = GetterUtil.getLong(
			portletPreferences.getValue("resourcePrimKey", null));

		KBArticle kbArticle = _kbArticleLocalService.fetchKBArticleByUrlTitle(
			OSBCustomerConstants.GROUP_KNOWLEDGE_ID, parentResourcePrimKey,
			urlTitle);

		if (kbArticle == null) {
			kbArticle = _kbArticleLocalService.fetchFirstChildKBArticle(
				OSBCustomerConstants.GROUP_KNOWLEDGE_ID, parentResourcePrimKey);
		}

		return kbArticle;
	}

	@Reference(unbind = "-")
	protected void setKBArticleLocalService(
		KBArticleLocalService kbArticleLocalService) {

		_kbArticleLocalService = kbArticleLocalService;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		KnowledgeBaseWrapperPortlet.class);

	private KBArticleLocalService _kbArticleLocalService;

}