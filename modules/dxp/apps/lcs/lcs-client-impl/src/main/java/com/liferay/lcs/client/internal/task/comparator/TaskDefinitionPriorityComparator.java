/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.task.comparator;

import com.liferay.lcs.client.internal.task.TaskDefinition;

import java.util.Comparator;

/**
 * @author Igor Beslic
 */
public class TaskDefinitionPriorityComparator
	implements Comparator<TaskDefinition> {

	@Override
	public int compare(
		TaskDefinition taskDefinition1, TaskDefinition taskDefinition2) {

		if (taskDefinition1.equals(taskDefinition2)) {
			return 0;
		}

		if (taskDefinition1.getPriority() <= taskDefinition2.getPriority()) {
			return -1;
		}

		return 1;
	}

}