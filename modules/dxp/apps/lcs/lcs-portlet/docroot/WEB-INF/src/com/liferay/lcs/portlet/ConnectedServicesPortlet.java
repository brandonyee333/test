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

package com.liferay.lcs.portlet;

import com.liferay.lcs.advisor.LCSPortletStateAdvisor;
import com.liferay.lcs.platform.gateway.LCSGatewayService;
import com.liferay.portal.kernel.bean.BeanLocator;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.license.messaging.LCSPortletState;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 * @author Marko Cikos
 * @author Peter Shin
 */
public class ConnectedServicesPortlet extends MVCPortlet {

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("serveConnectionStatus")) {
				serveConnectionStatus(resourceRequest, resourceResponse);
			}
			else {
				super.serveResource(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("result", "failure");

			_log.error(e, e);

			writeJSON(resourceRequest, resourceResponse, jsonObject);
		}
	}

	protected void serveConnectionStatus(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		BeanLocator beanLocator = PortletBeanLocatorUtil.getBeanLocator(
			"lcs-portlet");

		LCSGatewayService lcsGatewayService =
			(LCSGatewayService)beanLocator.locate(
				"com.liferay.lcs.platform.gateway.LCSGatewayService");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("lcsGatewayAvailable", lcsGatewayService.isAvailable());

		LCSPortletStateAdvisor lcsPortletStateAdvisor =
			(LCSPortletStateAdvisor)beanLocator.locate(
				"com.liferay.lcs.advisor.LCSPortletStateAdvisor");

		LCSPortletState lcsPortletState =
			lcsPortletStateAdvisor.getLCSPortletState(false);

		if ((lcsPortletState == LCSPortletState.NO_CONNECTION) ||
			(lcsPortletState == LCSPortletState.NOT_REGISTERED)) {

			jsonObject.put("ready", Boolean.FALSE);
		}
		else {
			jsonObject.put("ready", Boolean.TRUE);
		}

		jsonObject.put("result", "success");

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ConnectedServicesPortlet.class);

}