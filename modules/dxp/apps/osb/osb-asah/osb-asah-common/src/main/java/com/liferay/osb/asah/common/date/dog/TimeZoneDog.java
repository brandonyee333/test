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

package com.liferay.osb.asah.common.date.dog;

import com.liferay.osb.asah.common.date.dog.util.TimeZoneDogUtil;
import com.liferay.osb.asah.common.dog.PreferenceDog;
import com.liferay.osb.asah.common.model.Preference;

import java.time.ZoneId;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Geyson Silva
 */
@Component
public class TimeZoneDog {

	public String getTimeZoneId() {
		Preference preference = _preferenceDog.getPreference("time-zone-id");

		return preference.getValue();
	}

	public ZoneId getZoneId() {
		return ZoneId.of(getTimeZoneId());
	}

	@PostConstruct
	private void _init() {
		TimeZoneDogUtil.setTimeZoneDog(this);
	}

	@Autowired
	private PreferenceDog _preferenceDog;

}