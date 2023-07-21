/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.release.tool.web.internal.portlet;

import com.liferay.osb.customer.release.tool.web.internal.constants.ReleaseToolPortletKeys;
import com.liferay.osb.customer.release.tool.web.internal.search.ArtifactVersionSearcher;
import com.liferay.osb.customer.release.tool.web.internal.search.FixPackSearcher;
import com.liferay.osb.customer.release.tool.web.internal.search.JiraIssueSearcher;
import com.liferay.osb.customer.release.tool.web.internal.util.ReleasesAssetCategoryUtil;
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
			ArtifactVersionSearcher.class.getName(), _artifactVersionSearcher);
		renderRequest.setAttribute(
			FixPackSearcher.class.getName(), _fixPackSearcher);
		renderRequest.setAttribute(
			JiraIssueSearcher.class.getName(), _jiraIssueSearcher);
		renderRequest.setAttribute(
			ReleasesAssetCategoryUtil.class.getName(),
			_releasesAssetCategoryUtil);

		super.render(renderRequest, renderResponse);
	}

	@Reference
	private ArtifactVersionSearcher _artifactVersionSearcher;

	@Reference
	private FixPackSearcher _fixPackSearcher;

	@Reference
	private JiraIssueSearcher _jiraIssueSearcher;

	@Reference
	private ReleasesAssetCategoryUtil _releasesAssetCategoryUtil;

}