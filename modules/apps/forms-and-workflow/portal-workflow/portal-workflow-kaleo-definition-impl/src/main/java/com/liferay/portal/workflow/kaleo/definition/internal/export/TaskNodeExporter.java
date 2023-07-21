/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.definition.internal.export;

import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.workflow.kaleo.definition.Node;
import com.liferay.portal.workflow.kaleo.definition.Task;
import com.liferay.portal.workflow.kaleo.definition.export.NodeExporter;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true, property = "node.type=TASK", service = NodeExporter.class
)
public class TaskNodeExporter extends BaseNodeExporter implements NodeExporter {

	@Override
	protected Element createNodeElement(Element element, String namespace) {
		return element.addElement("task", namespace);
	}

	@Override
	protected void exportAdditionalNodeElements(
		Node node, Element nodeElement) {

		Task task = (Task)node;

		exportAssignmentsElement(
			task.getAssignments(), nodeElement, "assignments");

		exportTimersElement(task, nodeElement, "task-timers", "task-timer");
	}

}