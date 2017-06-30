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

package com.liferay.osb.profileapps.portlet;

import com.liferay.compat.util.bridges.mvc.MVCPortlet;
import com.liferay.osb.model.AssetAttachmentConstants;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.service.DeveloperEntryLocalServiceUtil;
import com.liferay.osb.util.OSBRequestUtil;
import com.liferay.osb.util.OSBUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Amos Fong
 */
public class ProfileAppsPortlet extends MVCPortlet {

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("serveIcon")) {
				OSBRequestUtil.serveAssetAttachment(
					resourceRequest, resourceResponse,
					AssetAttachmentConstants.TYPE_ICON);
			}
			else {
				super.serveResource(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	@Override
	protected boolean isProcessPortletRequest(PortletRequest portletRequest) {
		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)portletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			DeveloperEntry developerEntry =
				DeveloperEntryLocalServiceUtil.getDeveloperEntryByGroupId(
					themeDisplay.getScopeGroupId());

			if (developerEntry.isApproved()) {
				return OSBUtil.isDisplayProfile(portletRequest);
			}
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}

		return false;
	}

	private static Log _log = LogFactoryUtil.getLog(ProfileAppsPortlet.class);

}