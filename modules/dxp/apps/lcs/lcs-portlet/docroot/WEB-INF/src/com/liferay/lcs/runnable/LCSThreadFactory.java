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

package com.liferay.lcs.runnable;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Igor Beslic
 */
public class LCSThreadFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable runnable) {
		Thread thread = _threadFactory.newThread(runnable);

		thread.setName(_getThreadName(runnable));
		thread.setUncaughtExceptionHandler(_lcsUncaughtExceptionHandler);

		return thread;
	}

	private String _getThreadName(Runnable runnable) {
		StringBundler sb = new StringBundler(4);

		sb.append("LCS ");

		Class<?> clazz = runnable.getClass();

		sb.append(clazz.getSimpleName());

		sb.append(StringPool.SPACE);
		sb.append(_counter.incrementAndGet());

		return sb.toString();
	}

	private final AtomicLong _counter = new AtomicLong(0);
	private final LCSUncaughtExceptionHandler _lcsUncaughtExceptionHandler =
		new LCSUncaughtExceptionHandler();
	private final ThreadFactory _threadFactory =
		Executors.defaultThreadFactory();

}