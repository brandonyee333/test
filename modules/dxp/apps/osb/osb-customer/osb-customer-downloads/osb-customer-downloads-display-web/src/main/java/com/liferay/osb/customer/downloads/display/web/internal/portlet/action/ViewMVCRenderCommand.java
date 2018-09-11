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

package com.liferay.osb.customer.downloads.display.web.internal.portlet.action;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleService;
import com.liferay.osb.customer.downloads.display.web.internal.constants.DownloadsDisplayPortletKeys;
import com.liferay.osb.customer.downloads.display.web.internal.constants.DownloadsDisplayWebKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;

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
		"javax.portlet.name=" + DownloadsDisplayPortletKeys.DOWNLOADS_DISPLAY,
		"mvc.command.name=/", "mvc.command.name=/view"
	},
	service = MVCRenderCommand.class
)
public class ViewMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			return doRender(renderRequest, renderResponse);
		}
		catch (Exception e) {
			SessionErrors.add(renderRequest, e.getClass());

			return "/error.jsp";
		}
	}

	protected String doRender(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortalException {

		long journalArticleResourcePrimKey = ParamUtil.getLong(
			renderRequest, "journalArticleResourcePrimKey");

		if (journalArticleResourcePrimKey > 0) {
			JournalArticle journalArticle =
				_journalArticleService.getLatestArticle(
					journalArticleResourcePrimKey);

			renderRequest.setAttribute(
				DownloadsDisplayWebKeys.JOURNAL_ARTICLE, journalArticle);

			return "/view_download.jsp";
		}

		return "/view.jsp";
	}

	@Reference
	private JournalArticleService _journalArticleService;

}