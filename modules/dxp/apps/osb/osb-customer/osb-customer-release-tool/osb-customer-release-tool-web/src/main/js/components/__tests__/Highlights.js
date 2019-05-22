import React from 'react';
import {cleanup, fireEvent, render} from 'react-testing-library';

import Highlights from '../Highlights';

const setup = () => {
	const filters = [
		{
			label: 'Key Highlights',
			value: 'keyHighlights'
		},
		{
			label: 'Important Changes',
			value: 'importantChanges'
		},
		{
			label: 'Known Issues',
			value: 'knownIssues'
		},
		{
			label: 'Security',
			value: 'security'
		}
	];

	const highlightsJSONObj = {
		results: [
			{
				content: 'Content Text',
				fieldsUsed: [
					'importantChanges',
					'keyHighlights',
					'knownIssues',
					'security'
				],
				releaseDate: 'Mar 6, 2019',
				resourcePrimKey: '118267960',
				title: 'Fix Pack 2'
			},
			{
				content: 'Content 2 Text',
				fieldsUsed: ['keyHighlights'],
				releaseDate: 'Mar 1, 2019',
				resourcePrimKey: '118267952',
				title: 'Fix Pack1'
			}
		],
		total: 2
	};

	const noResults = {
		results: [],
		total: 0
	};

	const utils = render(
		<Highlights
			description="Description for Highlights tab."
			filters={filters}
			jsonObject={highlightsJSONObj}
		/>
	);

	return {filters, noResults, ...utils};
};

afterEach(cleanup);

describe('Highlights', () => {
	it('renders correctly', () => {
		const {container} = setup();

		expect(container).toMatchSnapshot();
	});

	it('renders no results correctly', () => {
		const {filters, noResults} = setup();

		const {container} = render(
			<Highlights
				description="Description for No Results Highlights tab."
				filters={filters}
				jsonObject={noResults}
			/>
		);

		expect(container).toMatchSnapshot();
	});

	it('displays filtered results when a filter checkbox is checked', () => {
		const {container, getByLabelText} = setup();
		const tbody = container.querySelector('tbody');

		fireEvent.click(getByLabelText('Key Highlights'));

		expect(tbody.querySelectorAll('tr').length).toBe(2);

		// There is only one result with both Key Highlights and Security

		fireEvent.click(getByLabelText('Security'));

		expect(tbody.querySelectorAll('tr').length).toBe(1);

		// Unselecting Key Highlights should show one result for Security

		fireEvent.click(getByLabelText('Key Highlights'));

		expect(tbody.querySelectorAll('tr').length).toBe(1);

		// Restore global state

		fireEvent.click(getByLabelText('Security'));
	});

	it('shows a Clear All option when a filter is selected and removes the option when all filters are unselected', () => {
		const {getByLabelText, queryByText} = setup();

		fireEvent.click(getByLabelText('Key Highlights'));

		expect(queryByText('clear-all')).toBeTruthy();

		fireEvent.click(getByLabelText('Key Highlights'));

		expect(queryByText('clear-all')).toBeFalsy();
	});

	it('clears all filter selections when Clear All is clicked', () => {
		const {getByLabelText, getByText} = setup();

		const checkbox = getByLabelText('Key Highlights');

		fireEvent.click(checkbox);
		fireEvent.click(getByText('clear-all'));

		expect(checkbox.checked).toBeFalsy();
	});

	it('updates the querystring with the selected filter name', () => {
		const {getByLabelText} = setup();

		fireEvent.click(getByLabelText('Key Highlights'));

		expect(window.location.search).toBe('?refineBy=keyHighlights');
	});
});