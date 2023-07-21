/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class ConnectionRetryUtil {

	public static int getRetryCount(long syncAccountId) {
		if (_counts.containsKey(syncAccountId)) {
			return _counts.get(syncAccountId);
		}

		return 0;
	}

	public static long incrementRetryDelay(long syncAccountId) {
		int count = getRetryCount(syncAccountId);

		count++;

		_counts.put(syncAccountId, count);

		Long delay = _delays.get(syncAccountId);

		if (delay == null) {
			_delays.put(syncAccountId, _INITIAL_INTERVAL);

			return _INITIAL_INTERVAL;
		}
		else if (delay == _MAX_INTERVAL) {
			return delay;
		}

		delay = (long)(delay * _MULTIPLIER);

		if (delay > _MAX_INTERVAL) {
			delay = _MAX_INTERVAL;
		}

		_delays.put(syncAccountId, delay);

		return delay;
	}

	public static void resetRetry(long syncAccountId) {
		_counts.remove(syncAccountId);
		_delays.remove(syncAccountId);
	}

	public static boolean retryInProgress(long syncAccountId) {
		if (getRetryCount(syncAccountId) > 0) {
			return true;
		}

		return false;
	}

	private static final long _INITIAL_INTERVAL = 1000;

	private static final long _MAX_INTERVAL = 30000;

	private static final double _MULTIPLIER = 1.2;

	private static final Map<Long, Integer> _counts = new HashMap<>();
	private static final Map<Long, Long> _delays = new HashMap<>();

}