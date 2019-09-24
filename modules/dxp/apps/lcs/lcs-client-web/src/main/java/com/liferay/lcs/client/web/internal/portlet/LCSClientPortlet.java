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

package com.liferay.lcs.client.web.internal.portlet;

import com.liferay.lcs.client.advisor.ClusterNodeAdvisor;
import com.liferay.lcs.client.advisor.LCSClientAdvisor;
import com.liferay.lcs.client.advisor.LCSClusterEntryTokenAdvisor;
import com.liferay.lcs.client.advisor.LCSPortletStateAdvisor;
import com.liferay.lcs.client.alert.advisor.LCSAlertAdvisor;
import com.liferay.lcs.client.configuration.LCSConfiguration;
import com.liferay.lcs.client.configuration.LCSConfigurationProvider;
import com.liferay.lcs.client.constants.LCSClientPortletKeys;
import com.liferay.lcs.client.constants.LCSClientWebKeys;
import com.liferay.lcs.client.platform.exception.LCSException;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.client.task.TaskStatus;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.license.messaging.LCSPortletState;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 * @author Marko Cikos
 * @author Peter Shin
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.preferences-unique-per-layout=false",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.css-class-wrapper=lcs-portlet",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.footer-portlet-javascript=/js/main.js",
		"com.liferay.portlet.layout-cacheable=true",
		"com.liferay.portlet.scopeable=true",
		"com.liferay.portlet.single-page-application=false",
		"javax.portlet.display-name=Liferay Connected Services",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.info.keywords=LCS Liferay Connected Services",
		"javax.portlet.info.short-title=Liferay Connected Services",
		"javax.portlet.info.title=Liferay Connected Services",
		"javax.portlet.init-param.copy-request-parameters=true",
		"javax.portlet.init-param.add-process-action-success-action=false",
		"javax.portlet.init-param.view-jsp=/view.jsp",
		"javax.portlet.name=" + LCSClientPortletKeys.CLIENT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supported-public-render-parameter=layoutLCSClusterEntryId",
		"javax.portlet.supported-public-render-parameter=layoutLCSClusterNodeId",
		"javax.portlet.supported-public-render-parameter=layoutLCSProjectId",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class LCSClientPortlet extends MVCPortlet {

	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		renderRequest.setAttribute(
			LCSClientWebKeys.LCS_ALERTS, _lcsAlertAdvisor.getLCSAlerts());
		renderRequest.setAttribute(
			LCSConfiguration.class.getName(),
			_lcsConfigurationProvider.getLCSConfiguration());
		renderRequest.setAttribute(
			LCSClusterEntryTokenAdvisor.class.getName(),
			_lcsClusterEntryTokenAdvisor);
		renderRequest.setAttribute(
			LCSGatewayClient.class.getName(), _lcsGatewayClient);
		renderRequest.setAttribute(
			LCSClientWebKeys.LCS_ACTIVE_SERVICES, _getLCSActiveServices());
		renderRequest.setAttribute(
			LCSClientWebKeys.CLUSTER_NODE_INFOS,
			_clusterNodeAdvisor.getClusterNodeInfos());
		renderRequest.setAttribute(
			LCSClientWebKeys.LOCAL_CLUSTER_NODE_ADDRESS,
			_clusterNodeAdvisor.getLocalClusterNodeAddress());

		try {
			renderRequest.setAttribute(
				LCSClientWebKeys.LCS_CLUSTER_ENTRY_LAYOUT_URL,
				_lcsClientAdvisor.getLCSClusterEntryLayoutURL());
			renderRequest.setAttribute(
				LCSClientWebKeys.LCS_CLUSTER_ENTRY_NAME,
				_lcsClientAdvisor.getLCSClusterEntryName());
			renderRequest.setAttribute(
				LCSClientWebKeys.LCS_CLUSTER_NODE_LAYOUT_URL,
				_lcsClientAdvisor.getLCSClusterNodeLayoutURL());
			renderRequest.setAttribute(
				LCSClientWebKeys.LCS_CLUSTER_NODE_NAME,
				_lcsClientAdvisor.getLCSClusterNodeName());
			renderRequest.setAttribute(
				LCSClientWebKeys.LCS_PROJECT_LAYOUT_URL,
				_lcsClientAdvisor.getLCSProjectLayoutURL());
			renderRequest.setAttribute(
				LCSClientWebKeys.LCS_PROJECT_NAME,
				_lcsClientAdvisor.getLCSProjectName());
			renderRequest.setAttribute(
				LCSClientWebKeys.REGISTRATION_LAYOUT_URL,
				_lcsClientAdvisor.getRegistrationLayoutURL());
		}
		catch (LCSException lcse) {
			if (_log.isDebugEnabled()) {
				_log.debug(lcse.getMessage(), lcse);
			}
			else if (_log.isWarnEnabled()) {
				_log.warn(lcse.getMessage());
			}
		}

		super.doView(renderRequest, renderResponse);
	}

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

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("lcsGatewayAvailable", _lcsGatewayClient.isAvailable());

		LCSPortletState lcsPortletState =
			_lcsPortletStateAdvisor.getLCSPortletState(false);

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

	private Set<String> _getLCSActiveServices() {
		Set<String> activeServiceLabels = new HashSet<>();

		for (String simpleClassName :
				_taskStatus.getActiveTaskSimpleClassNames()) {

			if (!_taskSimpleClassNamesServiceLabels.containsKey(
					simpleClassName)) {

				continue;
			}

			activeServiceLabels.add(
				_taskSimpleClassNamesServiceLabels.get(simpleClassName));
		}

		return activeServiceLabels;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LCSClientPortlet.class);

	@Reference
	private ClusterNodeAdvisor _clusterNodeAdvisor;

	@Reference
	private LCSAlertAdvisor _lcsAlertAdvisor;

	@Reference
	private LCSClientAdvisor _lcsClientAdvisor;

	@Reference
	private LCSClusterEntryTokenAdvisor _lcsClusterEntryTokenAdvisor;

	@Reference
	private LCSConfigurationProvider _lcsConfigurationProvider;

	@Reference
	private LCSGatewayClient _lcsGatewayClient;

	@Reference
	private LCSPortletStateAdvisor _lcsPortletStateAdvisor;

	private final Map<String, String> _taskSimpleClassNamesServiceLabels =
		new HashMap<String, String>() {
			{
				put("CacheMetricsTask", "Portal Analytics");
				put("JVMMetricsTask", "Portal Analytics");
				put("LicenseManagerTask", "Subscription Management");
				put("PortalMetricsTask", "Portal Analytics");
				put("SendPatchesTask", "Fix Packs Management");
				put("SendPortalPropertiesTask", "Portal Properties Analysis");
				put("ServerMetricsTask", "Portal Analytics");
			}
		};

	@Reference
	private TaskStatus _taskStatus;

}