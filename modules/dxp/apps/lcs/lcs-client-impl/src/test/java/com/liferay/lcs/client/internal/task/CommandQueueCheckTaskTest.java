/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.task;

import com.liferay.lcs.client.internal.command.queue.CommandQueue;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.lcs.client.internal.runnable.LCSThreadFactory;
import com.liferay.lcs.client.internal.task.advisor.TaskAdvisor;
import com.liferay.lcs.client.internal.task.executor.LCSTaskExecutor;
import com.liferay.lcs.messaging.ExecuteScriptCommandMessage;
import com.liferay.lcs.messaging.SendPortalPropertiesCommandMessage;
import com.liferay.lcs.messaging.SignOffCommandMessage;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Igor Beslic
 */
public class CommandQueueCheckTaskTest {

	@Test
	public void testSignOffCommandMessageDetection() {
		CommandQueue commandQueue = new CommandQueue();

		commandQueue.add(new ExecuteScriptCommandMessage());
		commandQueue.add(new ExecuteScriptCommandMessage());
		commandQueue.add(new SendPortalPropertiesCommandMessage());
		commandQueue.add(new SignOffCommandMessage());

		LCSTaskExecutor lcsTaskExecutor = new LCSTaskExecutor(
			new LCSEventManager(), new LCSThreadFactory());

		CommandQueueCheckTask commandQueueCheckTask = new CommandQueueCheckTask(
			commandQueue, lcsTaskExecutor, new TaskAdvisor());

		commandQueueCheckTask.run();

		Set<String> activeTaskSimpleClassNames =
			lcsTaskExecutor.getActiveTaskSimpleClassNames();

		Assert.assertTrue(
			"SignOff task is executed",
			activeTaskSimpleClassNames.contains(
				SignOffTask.class.getSimpleName()));
		Assert.assertTrue(
			"SignOff task is only one executed",
			activeTaskSimpleClassNames.size() == 1);

		System.out.println(activeTaskSimpleClassNames);
	}

}