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

package com.liferay.lcs.rest;

import com.liferay.lcs.oauth.OAuthUtil;
import com.liferay.lcs.rest.client.LCSProject;
import com.liferay.lcs.rest.client.LCSProjectClient;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletRequest;

/**
 * @author Igor Beslic
 */
public class LCSProjectClientUtil {

	public static List<LCSProject> getUserManageableLCSProjects(
		PortletRequest portletRequest) {

		try {
			return _lcsProjectClient.getUserManageableLCSProjects();
		}
		catch (Exception e) {
			_log.error("Unable to get user projects", e);

			if (OAuthUtil.hasOAuthException(e)) {
				OAuthUtil.processOAuthException(portletRequest, e);
			}
			else {
				SessionErrors.add(portletRequest, "generalPluginAccess");
			}
		}

		return Collections.emptyList();
	}

	public void setLCSProjectService(LCSProjectClient lcsProjectClient) {
		_lcsProjectClient = lcsProjectClient;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LCSProjectClientUtil.class);

	private static LCSProjectClient _lcsProjectClient;

}