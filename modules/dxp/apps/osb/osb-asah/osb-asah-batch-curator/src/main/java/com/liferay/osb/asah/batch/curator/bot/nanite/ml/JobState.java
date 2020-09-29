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

package com.liferay.osb.asah.batch.curator.bot.nanite.ml;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Riccardo Ferrari
 */
public enum JobState {

	COMPLETE("complete"), ERROR("error"), RUNNING("running"),
	UNKNOWN("unknown");

	public static JobState of(String value) {
		return Optional.ofNullable(
			_states.get(value)
		).orElseThrow(
			IllegalArgumentException::new
		);
	}

	public String getValue() {
		return _value;
	}

	@Override
	public String toString() {
		return _value;
	}

	private JobState(String value) {
		_value = value;
	}

	private static Map<String, JobState> _states = new HashMap<>();

	static {
		for (JobState state : values()) {
			_states.put(state.getValue(), state);
		}
	}

	private final String _value;

}