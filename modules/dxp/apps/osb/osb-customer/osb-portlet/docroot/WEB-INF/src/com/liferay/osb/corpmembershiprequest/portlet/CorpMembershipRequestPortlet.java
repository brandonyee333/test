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

package com.liferay.osb.corpmembershiprequest.portlet;

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.compat.util.bridges.mvc.MVCPortlet;
import com.liferay.osb.model.CorpMembershipRequest;
import com.liferay.osb.service.CorpMembershipRequestLocalServiceUtil;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.portal.theme.ThemeDisplay;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Ryan Park
 */
public class CorpMembershipRequestPortlet extends MVCPortlet {

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String key = ParamUtil.getString(renderRequest, "key");

		try {
			CorpMembershipRequest corpMembershipRequest =
				CorpMembershipRequestLocalServiceUtil.
					fetchCorpMembershipRequest(key);

			if (corpMembershipRequest != null) {
				CorpMembershipRequestLocalServiceUtil.updateStatus(
					corpMembershipRequest.getCorpMembershipRequestId(),
					WorkflowConstants.STATUS_APPROVED);
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			return;
		}

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		portletDisplay.setURLBack(PortalUtil.getPortalURL(renderRequest));

		renderRequest.setAttribute(WebKeys.PORTLET_DECORATE, Boolean.TRUE);

		super.doDispatch(renderRequest, renderResponse);
	}

	private static Log _log = LogFactoryUtil.getLog(
		CorpMembershipRequestPortlet.class);

}