/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.struts;

import com.liferay.portal.kernel.util.InstancePool;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ModuleConfig;

/**
 * @author Brian Wing Shun Chan
 */
public class DynamicPortletAction extends PortletAction {

	@Override
	public void processAction(
			ActionMapping actionMapping, ActionForm actionForm,
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		ModuleConfig moduleConfig = getModuleConfig(actionRequest);

		actionMapping = (ActionMapping)moduleConfig.findActionConfig(
			getPath(actionRequest));

		PortletAction action = (PortletAction)InstancePool.get(
			actionMapping.getType());

		action.processAction(
			actionMapping, actionForm, portletConfig, actionRequest,
			actionResponse);
	}

	@Override
	public ActionForward render(
			ActionMapping actionMapping, ActionForm actionForm,
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		ModuleConfig moduleConfig = getModuleConfig(renderRequest);

		actionMapping = (ActionMapping)moduleConfig.findActionConfig(
			getPath(renderRequest));

		PortletAction action = (PortletAction)InstancePool.get(
			actionMapping.getType());

		return action.render(
			actionMapping, actionForm, portletConfig, renderRequest,
			renderResponse);
	}

	protected String getPath(PortletRequest portletRequest) throws Exception {
		return null;
	}

}