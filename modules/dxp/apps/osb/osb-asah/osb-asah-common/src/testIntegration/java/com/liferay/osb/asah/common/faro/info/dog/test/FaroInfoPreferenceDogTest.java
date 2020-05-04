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

package com.liferay.osb.asah.common.faro.info.dog.test;

import com.liferay.osb.asah.common.faro.info.dog.FaroInfoPreferenceDog;
import com.liferay.osb.asah.common.model.Preference;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Matthew Kong
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class FaroInfoPreferenceDogTest {

	@Test
	public void testAddPreference() {
		Preference preference = _preferenceDog.getPreference(
			"data-retention-period");

		Assert.assertEquals(
			String.valueOf(TimeUnit.DAYS.toMillis(30 * 13)),
			preference.getValue());

		String dataRetentionPeriod = String.valueOf(
			TimeUnit.DAYS.toMillis(30 * 7));

		_preferenceDog.addPreference(
			"data-retention-period", dataRetentionPeriod);

		preference = _preferenceDog.getPreference("data-retention-period");

		Assert.assertEquals(dataRetentionPeriod, preference.getValue());
	}

	@Autowired
	private FaroInfoPreferenceDog _preferenceDog;

}