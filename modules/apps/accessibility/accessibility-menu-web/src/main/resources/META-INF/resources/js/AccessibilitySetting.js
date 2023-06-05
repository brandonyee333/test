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

import {ClayToggle} from '@clayui/form';
import {localStorage, setSessionValue} from 'frontend-js-web';
import PropTypes from 'prop-types';
import React, {useEffect, useState} from 'react';

import {SETTINGS_STRING_VALUES_MAP, getSettingValue} from './util';

const KEY_EVENT = 'Enter';

const AccessibilitySetting = ({setting}) => {
	const {className, defaultValue, key, label, sessionClicksValue} = setting;

	const [value, setValue] = useState(
		SETTINGS_STRING_VALUES_MAP[sessionClicksValue]
	);
	const [disabled, setDisabled] = useState(true);

	useEffect(() => {
		setValue(getSettingValue(defaultValue, sessionClicksValue, key));

		setDisabled(false);
	}, [defaultValue, key, sessionClicksValue]);

	const afterValueSet = (value) => {
		document.querySelector('body').classList.toggle(className, value);

		setValue(value);

		setDisabled(false);
	};

	const handleToggle = (value) => {
		setDisabled(true);

		if (themeDisplay.isSignedIn()) {
			setSessionValue(key, value).then((response) => {
				console.log('response', response);
				afterValueSet(value);
			});
		}
		else {
			localStorage.setItem(
				key,
				value,
				localStorage.TYPES.PERSONALIZATION
			);

			afterValueSet(value);
		}
	};

	return (
		<div className="c-mb-3">
			<ClayToggle
				disabled={disabled}
				label={label}
				onKeyDown={(event) => {
					if (event.key === KEY_EVENT) {
						handleToggle(!value);
					}
				}}
				onToggle={handleToggle}
				toggled={value}
			/>
		</div>
	);
};

AccessibilitySetting.propTypes = {
	setting: PropTypes.shape({
		className: PropTypes.string,
		defaultValue: PropTypes.string,
		key: PropTypes.string,
		label: PropTypes.string,
		sessionClicksValue: PropTypes.string,
	}).isRequired,
};

export default AccessibilitySetting;
