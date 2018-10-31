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

package com.liferay.osb.customer.web.internal.portlet;

import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.journal.exception.NoSuchArticleException;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleService;
import com.liferay.osb.customer.constants.OSBCustomerActionKeys;
import com.liferay.osb.customer.constants.OSBCustomerPortletKeys;
import com.liferay.osb.customer.web.internal.permission.OSBCustomerArticlePermission;
import com.liferay.osb.customer.web.internal.search.ArticleSearchUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.SubscriptionLocalService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alan Zhang
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=osb-documentation-display-portlet",
		"com.liferay.portlet.display-category=category.osb",
		"com.liferay.portlet.header-portlet-css=/display/css/main.css",
		"com.liferay.portlet.icon=/icon.png",
		"javax.portlet.display-name=OSB Documentation Display",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/display/",
		"javax.portlet.init-param.view-template=/display/view.jsp",
		"javax.portlet.name=" + OSBCustomerPortletKeys.DISPLAY,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supported-public-render-parameter=articleId",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class DisplayPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			String articleId = ParamUtil.getString(renderRequest, "articleId");

			JournalArticle journalArticle = _journalArticleService.getArticle(
				themeDisplay.getScopeGroupId(), articleId);

			if (!journalArticle.isApproved()) {
				throw new NoSuchArticleException();
			}

			String[] searchStructureIds =
				ArticleSearchUtil.getSearchStructureIds(
					themeDisplay.getScopeGroupId());

			if (!ArrayUtil.contains(
					searchStructureIds, journalArticle.getDDMStructureKey())) {

				throw new NoSuchArticleException();
			}

			renderRequest.setAttribute(
				"actionURL", renderResponse.createActionURL());

			String editJournalArticleURL = createEditArticleURL(
				renderRequest, journalArticle);

			renderRequest.setAttribute(
				"editJournalArticleURL", editJournalArticleURL);

			StringBundler sb = new StringBundler(4);

			sb.append("articleId=");
			sb.append(articleId);
			sb.append("&groupId=");
			sb.append(themeDisplay.getScopeGroupId());

			renderRequest.setAttribute("queryString", sb.toString());

			long plid = _portal.getPlidFromPortletId(
				themeDisplay.getScopeGroupId(), OSBCustomerPortletKeys.SEARCH);

			PortletURL renderURL = PortletURLFactoryUtil.create(
				renderRequest, OSBCustomerPortletKeys.SEARCH, plid,
				PortletRequest.RENDER_PHASE);

			renderRequest.setAttribute("renderURL", renderURL.toString());
		}
		catch (Exception e) {
			if (isSessionErrorException(e)) {
				SessionErrors.add(renderRequest, e.getClass(), e);

				SessionMessages.add(
					renderRequest,
					_portal.getPortletId(renderRequest) +
						SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
			}
			else {
				throw new PortletException(e);
			}
		}

		super.render(renderRequest, renderResponse);
	}

	public void subscribe(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long resourcePrimKey = ParamUtil.getLong(
			actionRequest, "resourcePrimKey");
		boolean subscribe = ParamUtil.getBoolean(actionRequest, "subscribe");

		if (subscribe) {
			_subscriptionLocalService.addSubscription(
				themeDisplay.getUserId(), 0, JournalArticle.class.getName(),
				resourcePrimKey);
		}
		else {
			_subscriptionLocalService.deleteSubscription(
				themeDisplay.getUserId(), JournalArticle.class.getName(),
				resourcePrimKey);
		}
	}

	protected String createEditArticleURL(
			RenderRequest renderRequest, JournalArticle journalArticle)
		throws PortalException {

		long controlPanelPlid = _portal.getControlPanelPlid(
			journalArticle.getCompanyId());

		LiferayPortletURL renderURL = PortletURLFactoryUtil.create(
			renderRequest, JournalPortletKeys.JOURNAL, controlPanelPlid,
			PortletRequest.RENDER_PHASE);

		String currentURL = _portal.getCurrentURL(renderRequest);

		renderURL.setDoAsGroupId(journalArticle.getGroupId());

		renderURL.setParameter("mvcPath", "/edit_article.jsp");
		renderURL.setParameter("redirect", currentURL);
		renderURL.setParameter(
			"articleId", String.valueOf(journalArticle.getArticleId()));
		renderURL.setParameter(
			"version", String.valueOf(journalArticle.getVersion()));

		return renderURL.toString();
	}

	@Override
	protected boolean isProcessPortletRequest(PortletRequest portletRequest) {
		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)portletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			if (OSBCustomerArticlePermission.contains(
					themeDisplay.getPermissionChecker(),
					OSBCustomerActionKeys.VIEW_JOURNAL_ARTICLE)) {

				return true;
			}
		}
		catch (Exception e) {
		}

		return false;
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof NoSuchArticleException ||
			cause instanceof PrincipalException) {

			return true;
		}
		else {
			return false;
		}
	}

	@Reference
	private JournalArticleService _journalArticleService;

	@Reference
	private Portal _portal;

	@Reference
	private SubscriptionLocalService _subscriptionLocalService;

}