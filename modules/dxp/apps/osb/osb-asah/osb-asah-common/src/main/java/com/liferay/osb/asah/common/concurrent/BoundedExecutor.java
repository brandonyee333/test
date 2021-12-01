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

package com.liferay.osb.asah.common.concurrent;

import com.liferay.osb.asah.common.util.Assert;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.lang.Nullable;

/**
 * @author Marcellus Tavares
 */
public class BoundedExecutor {

	public static BoundedExecutor newBoundedExecutor(int nThreads, int bound) {
		return new BoundedExecutor(
			Executors.newFixedThreadPool(nThreads), bound);
	}

	public BoundedExecutor(
		ExecutorService executorService, int concurrentTasksLimit) {

		_executorService = executorService;

		_bound = concurrentTasksLimit;

		_semaphore = new Semaphore(concurrentTasksLimit, true);
	}

	public void awaitPendingTasks() {
		try {
			_semaphore.acquireUninterruptibly(_bound);
		}
		finally {
			_semaphore.release(_bound);
		}
	}

	public void runAsync(
		Runnable task,
		@Nullable Supplier<ReentrantLock> reentrantLockSupplier) {

		Assert.notNull(task, "The task is null");

		_semaphore.acquireUninterruptibly();

		Optional<ReentrantLock> reentrantLockOptional = Optional.ofNullable(
			reentrantLockSupplier
		).map(
			Supplier::get
		);

		try {
			_executorService.execute(
				() -> {
					try {
						reentrantLockOptional.ifPresent(ReentrantLock::lock);

						task.run();
					}
					catch (Exception exception) {
						_log.error(
							"Uncaught exception on the asynchronous task",
							exception);
					}
					finally {
						reentrantLockOptional.ifPresent(ReentrantLock::unlock);

						_semaphore.release();
					}
				});
		}
		catch (RejectedExecutionException rejectedExecutionException) {
			_log.error(rejectedExecutionException, rejectedExecutionException);

			_semaphore.release();
		}
	}

	public void shutdown() {
		_executorService.shutdown();

		try {
			if (!_executorService.awaitTermination(1, TimeUnit.MINUTES)) {
				_executorService.shutdownNow();
			}
		}
		catch (InterruptedException interruptedException) {
			_log.error(
				"Interrupted while waiting for termination of executor",
				interruptedException);
		}
	}

	private static final Log _log = LogFactory.getLog(BoundedExecutor.class);

	private final int _bound;
	private final ExecutorService _executorService;
	private final Semaphore _semaphore;

}