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

package com.liferay.osb.asah.batch.curator.bot.nanite;

import com.liferay.osb.asah.common.faro.info.dog.FaroInfoOSBAsahTaskDog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

/**
 * @author Michael Bowerman
 */
@TestConfiguration
public class NaniteTestConfiguration {

	@Autowired
	public NaniteTestConfiguration(@Lazy List<Nanite> nanites) {
		_nanites = nanites;
	}

	@Bean
	@Lazy
	@Primary
	public FaroInfoOSBAsahTaskDog faroInfoOSBAsahTaskDog() {
		FaroInfoOSBAsahTaskDog faroInfoOSBAsahTaskDog = Mockito.mock(
			FaroInfoOSBAsahTaskDog.class);

		Mockito.doAnswer(
			invocation -> {
				String className = invocation.getArgumentAt(0, String.class);
				JSONObject contextJSONObject = invocation.getArgumentAt(
					1, JSONObject.class);

				if (_nanitesMap.isEmpty()) {
					for (Nanite nanite : _nanites) {
						Class<?> clazz = nanite.getClass();

						_nanitesMap.put(clazz.getSimpleName(), nanite);
					}
				}

				Nanite nanite = _nanitesMap.get(className);

				if (nanite == null) {
					throw new IllegalArgumentException(
						"Unable to find nanite with class name " + className);
				}

				if (nanite instanceof BaseActivitiesNanite) {
					BaseActivitiesNanite baseActivitiesNanite =
						(BaseActivitiesNanite)nanite;

					baseActivitiesNanite.run();
				}
				else {
					nanite.run(contextJSONObject);
				}

				return null;
			}
		).when(
			faroInfoOSBAsahTaskDog
		).addOSBAsahTask(
			Mockito.anyString(), Mockito.any(JSONObject.class)
		);

		return faroInfoOSBAsahTaskDog;
	}

	private final List<Nanite> _nanites;
	private final Map<String, Nanite> _nanitesMap = new HashMap<>();

}