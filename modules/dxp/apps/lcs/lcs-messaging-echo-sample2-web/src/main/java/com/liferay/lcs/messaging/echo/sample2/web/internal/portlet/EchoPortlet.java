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

package com.liferay.lcs.messaging.echo.sample2.web.internal.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Ferrari
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"javax.portlet.display-name=LCS Messaging Echo Sample2",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.info.keywords=LCS Messaging Echo Sample2",
		"javax.portlet.info.short-title=LCS Messaging Echo Sample2",
		"javax.portlet.info.title=LCS Messaging Echo Sample2",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.mime-type=text/html",
		"javax.portlet.name=com_liferay_lcs_messaging_echo_sample2_portlet_EchoPortlet",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user"
	},
	service = Portlet.class
)
public class EchoPortlet extends MVCPortlet {

	public void sendMessage(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		if (_log.isInfoEnabled()) {
			_log.info("Sending message to OSB LCS Echo service");
		}

		Message message = new Message();

		message.setPayload("LCS Echo Simple");
		message.setResponseId("lcsEchoResponseId");
		message.setResponseDestinationName("lcs_echo");

		MessageBusUtil.sendMessage("osb_lcs_echo", message);
	}

	private static final Log _log = LogFactoryUtil.getLog(EchoPortlet.class);

}