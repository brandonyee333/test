/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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