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
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class PreferenceDog {

	public synchronized Preference getPreference(String id) {
		Optional<Preference> preferenceOptional =
			_preferenceRepository.findById(id);

		if (preferenceOptional.isPresent()) {
			return preferenceOptional.get();
		}

		Preference preference = new Preference(id, _defaultPreferences.get(id));

		preference.setIsNew(Boolean.TRUE);

		return _preferenceRepository.save(preference);
	}

	public synchronized Preference savePreference(String id, String value) {
		Optional<Preference> preferenceOptional =
			_preferenceRepository.findById(id);

		if (preferenceOptional.isPresent()) {
			Preference preference = preferenceOptional.get();

			preference.setValue(value);

			return _preferenceRepository.save(preference);
		}

		Preference preference = new Preference(id, value);

		preference.setIsNew(Boolean.TRUE);

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