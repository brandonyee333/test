/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.watson.service.internal.upgrade;

import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Steven Smith
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class WatsonServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"com.liferay.watson.service", "1.0.0", "1.0.1",
			new com.liferay.watson.service.internal.upgrade.v1_0_1.
				UpgradeWatsonListType());

		registry.register(
			"com.liferay.watson.service", "1.0.1", "1.0.2",
			new com.liferay.watson.service.internal.upgrade.v1_0_2.
				UpgradeWatsonListType());
		registry.register(
			"com.liferay.watson.service", "1.0.2", "1.0.3",
			new com.liferay.watson.service.internal.upgrade.v1_0_3.
				UpgradeWatsonIncidents());

		registry.register(
			"com.liferay.watson.service", "1.0.3", "1.0.4",
			new com.liferay.watson.service.internal.upgrade.v1_0_4.
				UpgradeWatsonListType());
	}

}