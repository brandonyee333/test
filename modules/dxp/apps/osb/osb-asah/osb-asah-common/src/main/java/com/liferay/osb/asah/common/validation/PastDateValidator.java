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

package com.liferay.osb.asah.common.validation;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Marcellus Tavares
 */
public class PastDateValidator implements ConstraintValidator<PastDate, Date> {

	@Override
	public void initialize(PastDate pastDate) {
		_allowedLateness = pastDate.allowedLateness();
		_clock = Clock.systemUTC();
	}

	@Override
	public boolean isValid(
		Date date, ConstraintValidatorContext constraintValidatorContext) {

		if (date == null) {
			return false;
		}

		Instant instant = date.toInstant();

		Instant clockInstant = _clock.instant();

		int result = instant.compareTo(clockInstant);

		if (result >= 0) {
			return false;
		}

		result = instant.compareTo(
			clockInstant.minus(_allowedLateness, ChronoUnit.MILLIS));

		if (result >= 0) {
			return true;
		}

		return false;
	}

	private long _allowedLateness;
	private Clock _clock;

}