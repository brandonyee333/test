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

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Inácio Nery
 */
public class KeyReentrantLock {

	public static ReentrantLock getReentrantLock(
		Class<?> clazz, Object... keys) {

		return _reentrantLocks.get(
			clazz.getSimpleName() + "#" + Arrays.toString(keys),
			computedKey -> new ReentrantLock());
	}

	private static final Cache<String, ReentrantLock> _reentrantLocks =
		Caffeine.newBuilder(
		).expireAfterAccess(
			10, TimeUnit.MINUTES
		).maximumSize(
			100000
		).build();

}