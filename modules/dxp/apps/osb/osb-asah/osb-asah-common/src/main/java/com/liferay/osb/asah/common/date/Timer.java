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

package com.liferay.osb.asah.common.date;

import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;

/**
 * @author André Miranda
 */
public class Timer {

	public Timer(TemporalUnit temporalUnit, int value) {
		LocalDateTime nowLocalDateTime = LocalDateTime.now();

		_expirationLocalDateTime = nowLocalDateTime.plus(value, temporalUnit);
	}

	public boolean isValid() {
		return _expirationLocalDateTime.isAfter(LocalDateTime.now());
	}

	private final LocalDateTime _expirationLocalDateTime;

}