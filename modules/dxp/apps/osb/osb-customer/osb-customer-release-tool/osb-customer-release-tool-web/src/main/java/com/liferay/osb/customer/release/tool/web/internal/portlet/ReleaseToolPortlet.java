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

package com.liferay.osb.customer.release.tool.web.internal.portlet;

import com.liferay.osb.customer.release.tool.web.internal.constants.ReleaseToolPortletKeys;
import com.liferay.osb.customer.release.tool.web.internal.search.FixPackSearcher;
import com.liferay.osb.customer.release.tool.web.internal.search.JiraIssueSearcher;
import com.liferay.osb.customer.release.tool.web.internal.util.FixPacksAssetCategoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=osb-release-tool-portlet",
		"com.liferay.portlet.display-category=category.osb",
		"com.liferay.portlet.footer-portlet-javascript=/dist/main.js",
		"com.liferay.portlet.header-portlet-css=/dist/main.css",
		"com.liferay.portlet.render-weight=50",
		"javax.portlet.display-name=OSB Release Tool",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + ReleaseToolPortletKeys.RELEASE_TOOL,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user"
	},
	service = Portlet.class
)
public class ReleaseToolPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		renderRequest.setAttribute(
			FixPacksAssetCategoryUtil.class.getName(),
			_fixPacksAssetCategoryUtil);
		renderRequest.setAttribute(
			FixPackSearcher.class.getName(), _fixPackSearcher);
		renderRequest.setAttribute(
			JiraIssueSearcher.class.getName(), _jiraIssueSearcher);

		super.render(renderRequest, renderResponse);
	}

	@Reference
	private FixPacksAssetCategoryUtil _fixPacksAssetCategoryUtil;

	@Reference
	private FixPackSearcher _fixPackSearcher;

	@Reference
	private JiraIssueSearcher _jiraIssueSearcher;

}