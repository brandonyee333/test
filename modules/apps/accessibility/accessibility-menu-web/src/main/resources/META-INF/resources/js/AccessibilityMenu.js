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

import ClayModal, {useModal} from '@clayui/modal';
import PropTypes from 'prop-types';
import React, {useEffect} from 'react';

import AccessibilitySetting from './AccessibilitySetting';
import {getSettingValue, toggleClassName} from './util';

const OPEN_ACCESSIBILITY_MENU_EVENT_NAME = 'openAccessibilityMenu';

const AccessibilityMenu = ({accessibilitySettings}) => {
	const {observer, onOpenChange, open} = useModal();

	useEffect(() => {
		const openAccessibilityMenu = () => onOpenChange(true);

		Liferay.on(OPEN_ACCESSIBILITY_MENU_EVENT_NAME, openAccessibilityMenu);

		accessibilitySettings.forEach((setting) => {
			toggleClassName(
				setting.className,
				getSettingValue(
					setting.defaultValue,
					setting.sessionClicksValue,
					setting.key
				)
			);
		});

		return () => {
			Liferay.detach(
				OPEN_ACCESSIBILITY_MENU_EVENT_NAME,
				openAccessibilityMenu
			);
		};
	}, [accessibilitySettings, onOpenChange]);

	return (
		<>
			{open && (
				<ClayModal observer={observer} size="md">
					<ClayModal.Header>
						{Liferay.Language.get('accessibility-help-menu')}
					</ClayModal.Header>

					<ClayModal.Body>
						{accessibilitySettings.map((setting) => (
							<AccessibilitySetting
								key={setting.key}
								setting={setting}
							/>
						))}
					</ClayModal.Body>
				</ClayModal>
			)}
		</>
	);
};

AccessibilityMenu.propTypes = {
	accessibilitySettings: PropTypes.arrayOf(
		PropTypes.shape({
			className: PropTypes.string,
			defaultValue: PropTypes.string,
			key: PropTypes.string,
			label: PropTypes.string,
			sessionClicksValue: PropTypes.string,
		})
	).isRequired,
};

export default AccessibilityMenu;
