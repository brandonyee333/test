/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.workflow;

import com.liferay.portal.kernel.messaging.proxy.MessagingProxy;
import com.liferay.portal.kernel.messaging.proxy.ProxyMode;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Micha Kiener
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 * @author Eduardo Lundgren
 */
@MessagingProxy(mode = ProxyMode.SYNC)
public interface WorkflowDefinitionManager {

	public WorkflowDefinition deployWorkflowDefinition(
			long companyId, long userId, String title, byte[] bytes)
		throws WorkflowException;

	public int getActiveWorkflowDefinitionCount(long companyId)
		throws WorkflowException;

	public int getActiveWorkflowDefinitionCount(long companyId, String name)
		throws WorkflowException;

	public List<WorkflowDefinition> getActiveWorkflowDefinitions(
			long companyId, int start, int end,
			OrderByComparator<WorkflowDefinition> orderByComparator)
		throws WorkflowException;

	public List<WorkflowDefinition> getActiveWorkflowDefinitions(
			long companyId, String name, int start, int end,
			OrderByComparator<WorkflowDefinition> orderByComparator)
		throws WorkflowException;

	public WorkflowDefinition getLatestKaleoDefinition(
			long companyId, String name)
		throws WorkflowException;

	public WorkflowDefinition getWorkflowDefinition(
			long companyId, String name, int version)
		throws WorkflowException;

	public int getWorkflowDefinitionCount(long companyId)
		throws WorkflowException;

	public int getWorkflowDefinitionCount(long companyId, String name)
		throws WorkflowException;

	public List<WorkflowDefinition> getWorkflowDefinitions(
			long companyId, int start, int end,
			OrderByComparator<WorkflowDefinition> orderByComparator)
		throws WorkflowException;

	public List<WorkflowDefinition> getWorkflowDefinitions(
			long companyId, String name, int start, int end,
			OrderByComparator<WorkflowDefinition> orderByComparator)
		throws WorkflowException;

	public void undeployWorkflowDefinition(
			long companyId, long userId, String name, int version)
		throws WorkflowException;

	public WorkflowDefinition updateActive(
			long companyId, long userId, String name, int version,
			boolean active)
		throws WorkflowException;

	public WorkflowDefinition updateTitle(
			long companyId, long userId, String name, int version, String title)
		throws WorkflowException;

	public void validateWorkflowDefinition(byte[] bytes)
		throws WorkflowException;

}