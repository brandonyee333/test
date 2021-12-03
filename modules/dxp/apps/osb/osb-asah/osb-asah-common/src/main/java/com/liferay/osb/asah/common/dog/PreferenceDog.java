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

package com.liferay.osb.asah.common.dog;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.Preference;
import com.liferay.osb.asah.common.repository.PreferenceRepository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class PreferenceDog {

	public Preference getPreference(String key) {
		Preference preference = _preferenceRepository.findByKey(key);

		if (preference == null) {
			preference = _preferenceRepository.save(
				new Preference(key, _defaultPreferences.get(key)));
		}

		return preference;
	}

	public Preference savePreference(String key, String value) {
		Preference preference = _preferenceRepository.findByKey(key);

		if (preference == null) {
			return _preferenceRepository.save(new Preference(key, value));
		}

		preference.setValue(value);

		return _preferenceRepository.save(preference);
	}

	private static final Map<String, String> _defaultPreferences =
		new HashMap<String, String>() {
			{
				put(
					"data-retention-period",
					String.valueOf(13 * DateUtil.MONTH));
				put("time-zone-id", "UTC");
			}
		};

	@Autowired
	private PreferenceRepository _preferenceRepository;

}