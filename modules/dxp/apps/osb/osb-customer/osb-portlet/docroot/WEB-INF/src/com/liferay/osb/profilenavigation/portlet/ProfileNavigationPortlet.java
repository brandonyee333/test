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

package com.liferay.osb.profilenavigation.portlet;

import com.liferay.compat.util.bridges.mvc.MVCPortlet;
import com.liferay.osb.corpmembers.util.CorpMembersUtil;
import com.liferay.osb.model.AssetAttachmentConstants;
import com.liferay.osb.service.CorpEntryServiceUtil;
import com.liferay.osb.util.OSBRequestUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Douglas Wong
 * @author Ryan Park
 * @author Joan Kim
 */
public class ProfileNavigationPortlet extends MVCPortlet {

	public void sendCorpMembershipRequest(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		JSONObject jsonObject = CorpMembersUtil.sendCorpMembershipRequest(
			actionRequest, actionResponse);

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("serveMedia")) {
				OSBRequestUtil.serveAssetAttachment(
					resourceRequest, resourceResponse,
					AssetAttachmentConstants.TYPE_MEDIA);
			}
			else {
				super.serveResource(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void unsetCorpEntryUser(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long corpEntryId = ParamUtil.getLong(actionRequest, "corpEntryId");

		CorpEntryServiceUtil.unsetCorpEntryUsers(
			corpEntryId, new long[] {themeDisplay.getUserId()});

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("message", "success");

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		renderRequest.setAttribute(WebKeys.PORTLET_DECORATE, Boolean.FALSE);

		super.doDispatch(renderRequest, renderResponse);
	}

}