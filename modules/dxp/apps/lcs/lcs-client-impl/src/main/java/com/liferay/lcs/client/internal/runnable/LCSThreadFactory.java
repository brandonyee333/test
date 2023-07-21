/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.runnable;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

import org.osgi.service.component.annotations.Component;

/**
 * @author Igor Beslic
 */
@Component(service = ThreadFactory.class)
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