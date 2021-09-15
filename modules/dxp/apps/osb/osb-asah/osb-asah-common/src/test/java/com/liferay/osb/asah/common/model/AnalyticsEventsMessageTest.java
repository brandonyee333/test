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

package com.liferay.osb.asah.common.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.RandomStringUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Marcos Martins
 */
public class AnalyticsEventsMessageTest {

	@Before
	public void setUp() {
		ValidatorFactory validatorFactory =
			Validation.buildDefaultValidatorFactory();

		_validator = validatorFactory.getValidator();
	}

	@Test
	public void testAnalyticsEventsMessageWithInvalidContext() {
		AnalyticsEventsMessage analyticsEventsMessage =
			new AnalyticsEventsMessage();

		analyticsEventsMessage.setContext(
			Collections.singletonMap(
				RandomStringUtils.random(8), RandomStringUtils.random(2049)));
		analyticsEventsMessage.setDataSourceId("1");

		analyticsEventsMessage.setEvents(
			Arrays.asList(
				new AnalyticsEventsMessage.Event() {
					{
						setApplicationId("1");
						setEventDate(new Date());
						setEventId("1");
						setProperties(
							Collections.singletonMap(
								RandomStringUtils.random(8),
								RandomStringUtils.random(8)));
					}
				}));

		analyticsEventsMessage.setUserId("1");

		Set<ConstraintViolation<AnalyticsEventsMessage>> violations =
			_validator.validate(analyticsEventsMessage);

		Assert.assertFalse(violations.isEmpty());
	}

	private Validator _validator;

}