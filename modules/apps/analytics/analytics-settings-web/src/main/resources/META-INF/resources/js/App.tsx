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

import Loading from '@clayui/loading-indicator';
import React, {useEffect, useState} from 'react';

import DefaultPage from './pages/default/DefaultPage';
import WizardPage from './pages/wizard/WizardPage';
import {fetchConnection} from './utils/api';

export enum EPageView {
	Wizard = 'VIEW_WIZARD_MODE',
	Default = 'VIEW_DEFAULT_MODE',
}

type TView = {
	[key in EPageView]: React.FC;
};

const View: TView = {
	[EPageView.Wizard]: WizardPage,
	[EPageView.Default]: DefaultPage,
};

function App() {
	const [pageView, setPageView] = useState<EPageView>(EPageView.Wizard);
	const [loading, setLoading] = useState(true);

	useEffect(() => {
		const request = async () => {
			const result = await fetchConnection();

			// @ts-ignore

			setPageView(result?.pageView);

			setLoading(false);
		};

		request();
	}, []);

	if (loading) {
		return <Loading className="mt-9" size="md" />;
	}

	const PageView: React.FC = View[pageView];

	return (
		<div className="analytics-settings-web mt-5">
			<PageView />
		</div>
	);
}

export default App;
