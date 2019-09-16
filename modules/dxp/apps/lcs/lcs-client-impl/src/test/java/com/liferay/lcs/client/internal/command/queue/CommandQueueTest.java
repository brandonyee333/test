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

package com.liferay.lcs.client.internal.command.queue;

import com.liferay.lcs.messaging.CheckHeartbeatCommandMessage;
import com.liferay.lcs.messaging.SendPortalPropertiesCommandMessage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Igor Beslic
 */
public class CommandQueueTest {

	@Test
	public void testAdd() {
		CommandQueue commandQueue = new CommandQueue();

		Assert.assertNull("Returned value if queue empty", commandQueue.next());

		Assert.assertEquals(
			"Consumed commands count", 0L, commandQueue.getConsumedCount());

		Assert.assertEquals(
			"Queued commands count", 0L, commandQueue.getQueuedCount());

		commandQueue.add(new SendPortalPropertiesCommandMessage());

		Assert.assertEquals(
			"Consumed commands count", 0L, commandQueue.getConsumedCount());

		Assert.assertEquals(
			"Queued commands count", 1L, commandQueue.getQueuedCount());
	}

	@Test
	public void testConcurrency() {
		CommandQueue commandQueue = new CommandQueue();

		_runInThreads(20, commandQueue);

		Assert.assertNull("Command queue is empty", commandQueue.next());

		Assert.assertEquals(
			"Consumed commands count", 20L, commandQueue.getConsumedCount());

		Assert.assertEquals(
			"Queued commands count", 20L, commandQueue.getQueuedCount());
	}

	@Test
	public void testNext() {
		CommandQueue commandQueue = new CommandQueue();

		commandQueue.add(new SendPortalPropertiesCommandMessage());

		Assert.assertEquals(
			"Consumed commands count", 0L, commandQueue.getConsumedCount());

		Assert.assertEquals(
			"Queued commands count", 1L, commandQueue.getQueuedCount());

		commandQueue.next();

		Assert.assertEquals(
			"Consumed commands count", 1L, commandQueue.getConsumedCount());

		Assert.assertNull("Returned value if queue empty", commandQueue.next());

		Assert.assertEquals(
			"Consumed commands count after `next` call on empty queue", 1L,
			commandQueue.getConsumedCount());
	}

	private void _runInThreads(int threadCount, CommandQueue commandQueue) {
		List<Thread> threads = new ArrayList<>();
		Random random = new Random();

		for (int i = 0; i < threadCount; i++) {
			threads.add(
				new Thread() {

					@Override
					public void run() {
						try {
							Thread.sleep(random.nextInt(35));
						}
						catch (InterruptedException ie) {
						}

						commandQueue.add(new CheckHeartbeatCommandMessage());
					}

				});

			threads.add(
				new Thread() {

					@Override
					public void run() {
						while (commandQueue.next() == null) {
							try {
								Thread.sleep(random.nextInt(30));
							}
							catch (InterruptedException ie) {
							}
						}
					}

				});
		}

		for (Thread thread : threads) {
			thread.start();
		}

		while (!threads.isEmpty()) {
			Iterator<Thread> iterator = threads.iterator();

			while (iterator.hasNext()) {
				Thread thread = iterator.next();

				if (thread.getState() != Thread.State.TERMINATED) {
					continue;
				}

				iterator.remove();
			}
		}
	}

}