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

package com.liferay.osb.customer.release.notes.web.internal.portlet.action;

import com.liferay.osb.customer.release.notes.jira.exception.NoSuchJIRAProjectException;
import com.liferay.osb.customer.release.notes.jira.service.JIRAProjectLocalService;
import com.liferay.osb.customer.release.notes.web.internal.constants.ReleaseNotesPortletKeys;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Samuel Kong
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + ReleaseNotesPortletKeys.RELEASE_NOTES,
	service = ConfigurationAction.class
)
public class ReleaseNotesConfigurationAction
	extends DefaultConfigurationAction {

	@Override
	public String getJspPath(HttpServletRequest request) {
		return "/configuration.jsp";
	}

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String jiraProjectKey = getParameter(actionRequest, "jiraProjectKey");

		try {
			_jiraProjectLocalService.getJIRAProject(jiraProjectKey);
		}
		catch (NoSuchJIRAProjectException nsjirape) {
			SessionErrors.add(actionRequest, "jiraProjectKey");
		}

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	@Reference(unbind = "-")
	protected void setJIRAProjectLocalService(
		JIRAProjectLocalService jiraProjectLocalService) {

		_jiraProjectLocalService = jiraProjectLocalService;
	}

	private JIRAProjectLocalService _jiraProjectLocalService;

}