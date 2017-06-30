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

package com.liferay.osb.badges.portlet;

import com.liferay.compat.util.bridges.mvc.MVCPortlet;
import com.liferay.osb.service.TrainingCustomerLocalServiceUtil;
import com.liferay.osb.util.OSBRequestUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.expando.model.ExpandoBridge;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Eddie Olson
 */
public class BadgesPortlet extends MVCPortlet {

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("serveBadge")) {
				long trainingCertificateTemplateId = ParamUtil.getLong(
					resourceRequest, "trainingCertificateTemplateId");

				OSBRequestUtil.serveTrainingCertificateTemplateBadge(
					resourceRequest, resourceResponse,
					trainingCertificateTemplateId);
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

			Group group = themeDisplay.getScopeGroup();

			if (group.isUser()) {
				if (!TrainingCustomerLocalServiceUtil.
						hasTrainingCustomerBadgeNames(group.getClassPK())) {

					return false;
				}

				User user = UserLocalServiceUtil.getUser(group.getClassPK());

				ExpandoBridge expandoBridge = user.getExpandoBridge();

				boolean displayBadges = GetterUtil.getBoolean(
					expandoBridge.getAttribute("osbDisplayBadges", false));
				boolean displayProfile = GetterUtil.getBoolean(
					expandoBridge.getAttribute("osbDisplayProfile", false));
				boolean displayCertificates = GetterUtil.getBoolean(
					expandoBridge.getAttribute(
						"osbDisplayCertificates", false));

				if (displayProfile && (displayBadges || displayCertificates)) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		return true;
	}

	private static Log _log = LogFactoryUtil.getLog(BadgesPortlet.class);

}