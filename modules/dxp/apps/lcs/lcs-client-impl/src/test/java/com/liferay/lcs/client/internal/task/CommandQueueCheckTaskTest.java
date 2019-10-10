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