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

package com.liferay.portal.workflow.kaleo.designer.internal.upgrade.v1_0_0;

import com.liferay.portal.kernel.security.permission.ResourceActions;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.util.List;

/**
 * @author Sam Ziemer
 */
public class UpgradeResourceActions extends UpgradeProcess {

	public UpgradeResourceActions(
		ResourceActionLocalService resourceActionLocalService,
		ResourceActions resourceActions) {

		_resourceActionLocalService = resourceActionLocalService;
		_resourceActions = resourceActions;
	}

	@Override
	protected void doUpgrade() throws Exception {
		_resourceActions.read(
			null, UpgradeResourceActions.class.getClassLoader(),
			"/META-INF/resource-actions/default.xml");

		List<String> modelNames = _resourceActions.getPortletModelResources(
			_KALEO_DESIGNER_PORTLET_KEY);

		for (String modelName : modelNames) {
			List<String> modelActions =
				_resourceActions.getModelResourceActions(modelName);

			_resourceActionLocalService.checkResourceActions(
				modelName, modelActions);
		}
	}

	private static final String _KALEO_DESIGNER_PORTLET_KEY =
		"com_liferay_portal_workflow_kaleo_designer_web_portlet_" +
			"KaleoDesignerPortlet";

	private final ResourceActionLocalService _resourceActionLocalService;
	private final ResourceActions _resourceActions;

}