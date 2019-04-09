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
			endpoint="/"
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
				endpoint="/"
				filters={filters}
				fromFixPackVersion="1.0.6"
				jsonObject={noResults}
				toFixPackVersion="1.0.8"
			/>
		);

		expect(container).toMatchSnapshot();
	});

	it('shows a Clear All option when a filter is selected and removes the option when all filters are unselected', () => {
		const {container, queryByText} = setup();

		fireEvent.click(container.querySelector('input[type=checkbox]'));

		expect(queryByText('clear-all')).toBeTruthy();

		fireEvent.click(container.querySelector('input[type=checkbox]'));

		expect(queryByText('clear-all')).toBeFalsy();
	});

	it('clears all filter selections, text input, and toggle when Clear All is clicked', () => {
		const {container, getByText} = setup();

		const checkbox = container.querySelector('input[type=checkbox]');
		const textInput = container.querySelector('input[type=text]');
		const toggle = container.querySelector('.toggle-switch');

		fireEvent.click(checkbox);
		fireEvent.change(textInput, {
			target: {
				value: 'test'
			}
		});

		fireEvent.change(toggle, {
			target: {
				checked: true
			}
		});

		fireEvent.click(getByText('clear-all'));

		expect(checkbox.checked).toBeFalsy();
		expect(textInput.value).toBe('');
		expect(toggle.checked).toBeFalsy();
	});
});