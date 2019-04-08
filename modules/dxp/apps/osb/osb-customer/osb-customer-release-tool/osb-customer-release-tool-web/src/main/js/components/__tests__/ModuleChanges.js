import React from 'react';
import {cleanup, fireEvent, render} from 'react-testing-library';

import ModuleChanges from '../ModuleChanges';

const setup = () => {
	const filters = [
		{
			label: 'Liferay Artifacts',
			value: 0
		},
		{
			label: 'Third Party Libraries',
			value: 1
		}
	];

	const jsonObject = {
		total: 279,
		results: [
			{
				fromRepositoryURL: '/',
				fromVersion: '1.0.1',
				group: 'com.liferay',
				name: 'com.liferay.portal.workflow.kaleo.designer.api',
				toRepositoryURL: '/',
				toVersion: '1.0.1'
			},
			{
				fromRepositoryURL: '/',
				fromVersion: '1.0.6',
				group: 'com.liferay',
				name: 'com.liferay.announcements.web',
				toRepositoryURL: '/',
				toVersion: '1.0.8'
			}
		]
	};

	const noResults = {
		results: [],
		total: 0
	};

	const utils = render(
		<ModuleChanges
			description="Description for Module Changes tab."
			endpoint='/'
			filters={filters}
			fromFixPackVersion="1.0.1"
			jsonObject={jsonObject}
			toFixPackVersion="1.0.8"
		/>
	);

	return {filters, noResults, ...utils};
};

afterEach(cleanup);

describe('ModuleChanges', () => {
	it('renders correctly', () => {
		const {container} = setup();

		expect(container).toMatchSnapshot();
	});

	it('renders no results correctly', () => {
		const {filters, noResults} = setup();

		const {container} = render(
			<ModuleChanges
				description="Description for Module Changes tab."
				endpoint='/'
				filters={filters}
				fromFixPackVersion="1.0.6"
				jsonObject={noResults}
				toFixPackVersion="1.0.8"
			/>
		);

		expect(container).toMatchSnapshot();
	});
});