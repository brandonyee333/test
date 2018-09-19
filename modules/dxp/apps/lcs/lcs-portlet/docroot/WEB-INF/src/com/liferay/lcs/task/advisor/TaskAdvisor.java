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

package com.liferay.lcs.task.advisor;

import com.liferay.lcs.command.Command;
import com.liferay.lcs.command.SendPatchesCommand;
import com.liferay.lcs.command.SendPortalPropertiesCommand;
import com.liferay.lcs.task.CacheMetricsTask;
import com.liferay.lcs.task.JVMMetricsTask;
import com.liferay.lcs.task.LicenseManagerTask;
import com.liferay.lcs.task.PortalMetricsTask;
import com.liferay.lcs.task.ScheduledTask;
import com.liferay.lcs.task.ServerMetricsTask;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Igor Beslic
 */
public class TaskAdvisor {

	public synchronized Set<String> getActiveServiceLabels() {
		Set<String> activeServiceLabels = new HashSet<>();

		for (String className : _activeClassNames) {
			if (!_taskClassNamesParentServiceLabels.containsKey(className)) {
				continue;
			}

			activeServiceLabels.add(
				_taskClassNamesParentServiceLabels.get(className));
		}

		return activeServiceLabels;
	}

	public synchronized void registerActivity(Object instance) {
		Class<?> clazz = instance.getClass();

		String className = clazz.getName();

		_activeClassNames.add(className);

		if (instance instanceof ScheduledTask) {
			_scheduledTaskClassNames.add(className);
		}
		else if (instance instanceof Command) {
			_executedCommandClassNames.add(className);
		}
	}

	public synchronized void reset() {
		_activeClassNames.clear();
		_executedCommandClassNames.clear();
		_scheduledTaskClassNames.clear();
	}

	private final Set<String> _activeClassNames = new HashSet<>();
	private final Set<String> _executedCommandClassNames = new HashSet<>();
	private final Set<String> _scheduledTaskClassNames = new HashSet<>();
	private final Map<String, String> _taskClassNamesParentServiceLabels =
		new HashMap<String, String>() {
			{
				put(CacheMetricsTask.class.getName(), "Portal Analytics");
				put(JVMMetricsTask.class.getName(), "Portal Analytics");
				put(
					LicenseManagerTask.class.getName(),
					"Subscription Management");
				put(PortalMetricsTask.class.getName(), "Portal Analytics");
				put(SendPatchesCommand.class.getName(), "Fix Packs Management");
				put(
					SendPortalPropertiesCommand.class.getName(),
					"Portal Properties Analysis");
				put(ServerMetricsTask.class.getName(), "Portal Analytics");
			}
		};

}