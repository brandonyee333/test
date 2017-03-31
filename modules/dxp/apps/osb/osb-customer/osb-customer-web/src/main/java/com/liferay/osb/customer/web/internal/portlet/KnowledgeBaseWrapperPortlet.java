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
import com.liferay.osb.customer.web.internal.util.OSBConstants;
import com.liferay.osb.customer.web.internal.util.OSBWebKeys;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
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

		try {
			LiferayPortletRequest liferayPortletRequest =
				(LiferayPortletRequest)renderRequest;

			HttpServletRequest request =
				liferayPortletRequest.getOriginalHttpServletRequest();

			ThemeDisplay themeDisplay =
				(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			String mvcPath = ParamUtil.getString(
				request,
				"_" + KBPortletKeys.KNOWLEDGE_BASE_DISPLAY + "_mvcPath");
			String redirect = ParamUtil.getString(
				request,
				"_" + KBPortletKeys.KNOWLEDGE_BASE_DISPLAY + "_redirect");
			String urlTitle = ParamUtil.getString(
				request,
				"_" + KBPortletKeys.KNOWLEDGE_BASE_DISPLAY + "_urlTitle");

			if (mvcPath.equals("/display/edit_article.jsp")) {
				renderRequest.setAttribute(Constants.CMD, Constants.EDIT);
			}
			else {
				PortletPreferences portletPreferences =
					PortletPreferencesFactoryUtil.getExistingPortletSetup(
						themeDisplay.getLayout(),
						KBPortletKeys.KNOWLEDGE_BASE_DISPLAY);

				long parentResourcePrimKey = GetterUtil.getLong(
					portletPreferences.getValue("resourcePrimKey", null));

				KBArticle kbArticle =
					_kbArticleLocalService.fetchKBArticleByUrlTitle(
						OSBConstants.GROUP_KNOWLEDGE_ID, parentResourcePrimKey,
						urlTitle);

				if (kbArticle == null) {
					kbArticle = _kbArticleLocalService.fetchFirstChildKBArticle(
						OSBConstants.GROUP_KNOWLEDGE_ID, parentResourcePrimKey);
				}

				renderRequest.setAttribute(
					OSBWebKeys.KNOWLEDGE_BASE_KB_ARTICLE, kbArticle);

				renderRequest.setAttribute(Constants.CMD, Constants.VIEW);
			}

			renderRequest.setAttribute(OSBWebKeys.REDIRECT, redirect);
		}
		catch (Exception e) {
			if (isSessionErrorException(e)) {
				SessionErrors.add(renderRequest, e.getClass(), e);

				SessionMessages.add(
					renderRequest,
					PortalUtil.getPortletId(renderRequest) +
						SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
			}
			else {
				throw new PortletException(e);
			}
		}

		super.render(renderRequest, renderResponse);
	}

	@Reference(unbind = "-")
	protected void setKBArticleLocalService(
		KBArticleLocalService kbArticleLocalService) {

		_kbArticleLocalService = kbArticleLocalService;
	}

	private KBArticleLocalService _kbArticleLocalService;

}