/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.workflow;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class WorkflowEngineManagerUtil {

	public static String getKey() {
		return getWorkflowEngineManager().getKey();
	}

	public static String getName() {
		return getWorkflowEngineManager().getName();
	}

	public static Map<String, Object> getOptionalAttributes() {
		return getWorkflowEngineManager().getOptionalAttributes();
	}

	public static String getVersion() {
		return getWorkflowEngineManager().getVersion();
	}

	public static WorkflowEngineManager getWorkflowEngineManager() {
		PortalRuntimePermission.checkGetBeanProperty(
			WorkflowEngineManagerUtil.class);

		return _workflowEngineManager;
	}

	public static boolean isDeployed() {
		return getWorkflowEngineManager().isDeployed();
	}

	public void setWorkflowEngineManager(
		WorkflowEngineManager workflowEngineManager) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_workflowEngineManager = workflowEngineManager;
	}

	private static WorkflowEngineManager _workflowEngineManager;

}