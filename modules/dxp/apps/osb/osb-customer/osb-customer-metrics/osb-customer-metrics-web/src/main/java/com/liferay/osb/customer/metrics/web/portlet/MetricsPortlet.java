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

package com.liferay.osb.customer.metrics.web.portlet;

import com.liferay.osb.customer.metrics.api.model.MetricsModel;
import com.liferay.osb.customer.metrics.api.model.MetricsModelRegistry;
import com.liferay.osb.customer.metrics.web.internal.constants.MetricsPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=OSB Customer Metrics",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + MetricsPortletKeys.METRICS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class MetricsPortlet extends MVCPortlet {

	public void cleanAndSyncModel(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String modelClassName = ParamUtil.getString(
			actionRequest, "modelClassName");

		MetricsModel<?> model = _metricsModelRegistry.getMetricsModel(
			modelClassName);

		model.deleteAll(modelClassName);
	}

	public void syncModel(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String modelClassName = ParamUtil.getString(
			actionRequest, "modelClassName");

		MetricsModel<?> model = _metricsModelRegistry.getMetricsModel(
			modelClassName);

		model.resyncAll(modelClassName);
	}

	@Reference
	private MetricsModelRegistry _metricsModelRegistry;

}