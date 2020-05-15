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

package com.liferay.osb.asah.common.elasticsearch.impl;

import java.security.SecureRandom;

/**
 * Provides a simple way to generate unique ordered IDs across distributed
 * shards (or even multiple systems). It's based on Simpleflake's ID generation
 * and requires no coordination with server IPs, Mac addresses, or database
 * content.
 *
 * <p>
 * This implementation follows a pattern that a 64-bits long is prefixed with a
 * millisecond timestamp, but the remaining bits are completely random. For
 * example,
 * </p>
 *
 * <p>
 * <pre>
 * id = 0000000000000000000000000000000000000000000   0   00000000000000000000
 *     |-------------------------------------------| |-| |--------------------|
 *                    timestamp                    sequence      random
 *                      bits                      safety bit      bits
 * </pre>
 * </p>
 *
 * @author Eduardo Lundgren
 */
public class TimeOrderedUuidGenerator {

	/**
	 * Gets start count timestamp value.
	 */
	public TimeOrderedUuidGenerator() {
		this(_TIMESTAMP_MIN);
	}

	/**
	 * Instantiates a new time ordered unique generator.
	 *
	 * @param startTimestamp the start timestamp
	 */
	public TimeOrderedUuidGenerator(long startTimestamp) {
		if (startTimestamp < 0) {
			throw new IllegalArgumentException(
				"Start timestamp is less than 0");
		}

		_startTimestamp = startTimestamp;
	}

	/**
	 * Generates a unique ordered ID across distributed shards (or even multiple
	 * systems).
	 *
	 * @return the unique ordered ID
	 */
	public synchronized String generateId() {
		long timestamp = System.currentTimeMillis();

		// Prevent the clock from moving backwards

		while (timestamp < _lastTimestamp) {
			timestamp = System.currentTimeMillis();
		}

		if ((timestamp < _startTimestamp) || (timestamp > _TIMESTAMP_MAX)) {
			throw new RuntimeException(
				"Refusing to generate ID because system clock is invalid");
		}

		if (timestamp != _lastTimestamp) {
			_lastRandom = _secureRandom.nextInt(_RANDOM_MAX);
			_lastTimestamp = timestamp;
		}
		else {
			_lastRandom++;
		}

		long uuid = timestamp - _startTimestamp;

		uuid <<= _SEQUENCE_SAFETY_BITS;
		uuid <<= _RANDOM_BITS;
		uuid |= _lastRandom;

		return String.valueOf(uuid);
	}

	private static final int _RANDOM_BITS = 20;

	private static final int _RANDOM_MAX = -1 ^ (-1 << _RANDOM_BITS);

	private static final int _SEQUENCE_SAFETY_BITS = 1;

	private static final int _TIMESTAMP_BITS = 43;

	private static final long _TIMESTAMP_MAX = -1L ^ (-1L << _TIMESTAMP_BITS);

	private static final long _TIMESTAMP_MIN = 1388502000000L;

	private static int _lastRandom;
	private static long _lastTimestamp = -1L;
	private static final SecureRandom _secureRandom = new SecureRandom();

	private final long _startTimestamp;

}