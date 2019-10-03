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