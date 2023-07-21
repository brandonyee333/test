/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.definition;

import java.util.Set;

/**
 * @author Michael C. Han
 */
public class Task extends Node {

	public Task(String name, String description) {
		super(NodeType.TASK, name, description);
	}

	public Set<Assignment> getAssignments() {
		return _assignments;
	}

	public void setAssignments(Set<Assignment> assignments) {
		_assignments = assignments;
	}

	private Set<Assignment> _assignments;

}