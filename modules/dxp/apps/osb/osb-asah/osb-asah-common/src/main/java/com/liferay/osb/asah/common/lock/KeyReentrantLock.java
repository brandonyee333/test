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

package com.liferay.osb.asah.common.lock;

import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Inácio Nery
 */
public class KeyReentrantLock {

	public static synchronized ReentrantLock getReentrantLock(
		Class<?> clazz, String key) {

		return _reentrantLocks.computeIfAbsent(
			ProjectIdThreadLocal.getProjectId() + "#" + clazz.getSimpleName() +
				"#" + key,
			computedKey -> new ReentrantLock());
	}

	private static final Map<String, ReentrantLock> _reentrantLocks =
		new ConcurrentHashMap<>();

}