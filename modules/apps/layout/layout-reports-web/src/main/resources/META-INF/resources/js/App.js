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

import {useEventListener} from '@liferay/frontend-js-react-web';
import {setSessionValue} from 'frontend-js-web';
import React, {useEffect, useState} from 'react';

import LayoutReports from './components/LayoutReports';
import {StoreContextProvider} from './context/StoreContext';

import '../css/main.scss';
import SidebarHeader from './components/SidebarHeader';
import {ConstantsContextProvider} from './context/ConstantsContext';

export default function App(props) {
	const {portletNamespace} = props;

	const layoutReportsPanelToggle = document.getElementById(
		`${portletNamespace}layoutReportsPanelToggleId`
	);

	layoutReportsPanelToggle.setAttribute('aria-pressed', false);

	const layoutReportsPanelId = document.getElementById(
		`${portletNamespace}layoutReportsPanelId`
	);

	const sidenavInstance = Liferay.SideNavigation.instance(
		layoutReportsPanelToggle
	);

	const handleKeydownToggle = (event) => {
		if (event.key === 'Enter') {
			layoutReportsPanelToggle.setAttribute('aria-pressed', true);
		}
	};

	const handleKeydownPanel = (event) => {
		if (event.key === 'Escape') {
			layoutReportsPanelToggle.setAttribute('aria-pressed', false);
			sidenavInstance.toggle();
		}
	};

	useEffect(() => {
		sidenavInstance.on('open.lexicon.sidenav', () => {
			setSessionValue(
				'com.liferay.layout.reports.web_layoutReportsPanelState',
				'open'
			);

			if (
				layoutReportsPanelToggle.getAttribute('aria-pressed') === 'true'
			) {
				layoutReportsPanelId.setAttribute('tabindex', '0');
				layoutReportsPanelId.focus();
			}
		});

		sidenavInstance.on('closed.lexicon.sidenav', () => {
			setSessionValue(
				'com.liferay.layout.reports.web_layoutReportsPanelState',
				'closed'
			);

			if (
				layoutReportsPanelToggle.getAttribute('aria-pressed') ===
				'false'
			) {
				layoutReportsPanelToggle.setAttribute('tabindex', '0');
				layoutReportsPanelToggle.focus();
			}
		});

		Liferay.once('screenLoad', () => {
			Liferay.SideNavigation.destroy(layoutReportsPanelToggle);
		});
	}, [
		layoutReportsPanelToggle,
		portletNamespace,
		layoutReportsPanelId,
		sidenavInstance,
	]);

	const [eventTriggered, setEventTriggered] = useState(false);

	useEventListener(
		'keydown',
		handleKeydownToggle,
		{once: false},
		layoutReportsPanelToggle
	);

	useEventListener(
		'keydown',
		handleKeydownPanel,
		{once: false},
		layoutReportsPanelId
	);

	useEventListener(
		'mouseenter',
		() => setEventTriggered(true),
		{once: true},
		layoutReportsPanelToggle
	);

	useEventListener(
		'focus',
		() => setEventTriggered(true),
		{once: true},
		layoutReportsPanelToggle
	);

	return (
		<ConstantsContextProvider constants={props}>
			<StoreContextProvider>
				<SidebarHeader />

				<SidebarBody>
					<LayoutReports eventTriggered={eventTriggered} />
				</SidebarBody>
			</StoreContextProvider>
		</ConstantsContextProvider>
	);
}

const SidebarBody = ({children}) => (
	<div className="sidebar-body">{children}</div>
);
