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

import {localStorage} from 'frontend-js-web';

export const SETTINGS_STRING_VALUES_MAP = {
	false: false,
	true: true,
	undefined,
};

export function getSettingValue(defaultValue, sessionClicksValue, key) {
	if (
		!themeDisplay.isSignedIn() ||
		SETTINGS_STRING_VALUES_MAP[sessionClicksValue] === undefined
	) {
		const localStorageValue = localStorage.getItem(
			key,
			localStorage.TYPES.PERSONALIZATION
		);

		if (localStorageValue in SETTINGS_STRING_VALUES_MAP) {
			return SETTINGS_STRING_VALUES_MAP[localStorageValue];
		}
		else {
			return SETTINGS_STRING_VALUES_MAP[defaultValue];
		}
	}

	return SETTINGS_STRING_VALUES_MAP[sessionClicksValue];
}
