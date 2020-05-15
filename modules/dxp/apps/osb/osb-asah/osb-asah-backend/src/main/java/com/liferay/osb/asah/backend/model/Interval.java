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

package com.liferay.osb.asah.backend.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Rachael Koestartyo
 */
public enum Interval {

	DAY("D"), MONTH("M"), WEEK("W");

	public static Interval of(String interval) {
		return Optional.ofNullable(
			_intervals.get(interval)
		).orElseThrow(
			IllegalArgumentException::new
		);
	}

	public String getKey() {
		return _key;
	}

	private Interval(String key) {
		_key = key;
	}

	private static final Map<String, Interval> _intervals =
		new HashMap<String, Interval>() {
			{
				put("D", DAY);
				put("M", MONTH);
				put("W", WEEK);
			}
		};

	private final String _key;

}