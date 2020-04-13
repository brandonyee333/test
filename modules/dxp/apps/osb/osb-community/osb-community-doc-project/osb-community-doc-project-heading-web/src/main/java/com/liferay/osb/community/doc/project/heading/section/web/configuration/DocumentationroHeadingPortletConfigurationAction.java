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

package com.liferay.osb.community.doc.project.heading.section.web.configuration;

import com.liferay.osb.community.doc.project.heading.section.web.constants.DocProjectHeadingPortletKeys;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

/**
 * @author Yury Butrymovich
 */
@Component(
	configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
	property = "javax.portlet.name=" + DocProjectHeadingPortletKeys.DOC_PROJECT_HEADING,
	service = ConfigurationAction.class
)
public class DocumentationroHeadingPortletConfigurationAction
	extends DefaultConfigurationAction {

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String docProjectId = ParamUtil.getString(
			actionRequest, "docProjectId");

		setPreference(actionRequest, "docProjectId", docProjectId);

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

}