/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.definition.internal.export.builder;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.workflow.kaleo.definition.Assignment;
import com.liferay.portal.workflow.kaleo.definition.Task;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.model.KaleoTask;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskLocalService;

import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true, property = "node.type=TASK", service = NodeBuilder.class
)
public class TaskNodeBuilder
	extends BaseNodeBuilder<Task> implements NodeBuilder {

	@Override
	protected Task createNode(KaleoNode kaleoNode) throws PortalException {
		KaleoTask kaleoTask = _kaleoTaskLocalService.getKaleoNodeKaleoTask(
			kaleoNode.getKaleoNodeId());

		Task task = new Task(kaleoNode.getName(), kaleoNode.getDescription());

		Set<Assignment> assignments = buildAssigments(
			KaleoTask.class.getName(), kaleoTask.getKaleoTaskId());

		task.setAssignments(assignments);

		return task;
	}

	@Reference
	private KaleoTaskLocalService _kaleoTaskLocalService;

}