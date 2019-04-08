import React from 'react';
import {cleanup, fireEvent, render} from 'react-testing-library';

import SortableTable from '../SortableTable';

const setup = () => {
	const changelogJSONObj = {
		results: [
			{
				components: ['Frontend Infrastructure > WYSIWYG'],
				description: 'description',
				key: 'LPS-90100',
				release: 'GA',
				summary: 'IE11 Web Image Resizing Does not Maintain Initial Aspect Ratio',
				url: '/'
			},
			{
				components: ['Accessibility'],
				description: 'description 2',
				key: 'LPS-85155',
				release: 'GA',
				summary: 'Add menu - Heading order invalid',
				url: '/'
			}
		],
		total: 2
	};

	const highlightsJSONObj = {
		results: [
			{
				content: 'Content Text',
				fieldsUsed: {
					importantChanges: true,
					keyHighlights: true,
					knownIssues: true,
					security: true
				},
				releaseDate: 'Mar 6, 2019',
				resourcePrimKey: '118267960',
				title: 'Fix Pack 2'
			},
			{
				content: 'Content 2 Text',
				fieldsUsed: {
					importantChanges: false,
					keyHighlights: false,
					knownIssues: false,
					security: false
				},
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
		<SortableTable
			jsonObject={highlightsJSONObj}
			orderBy="desc"
			tabName="highlights"
		/>
	);

	return {
		changelogJSONObj,
		noResults,
		...utils
	};
};

afterEach(cleanup);

describe('SortableTable', () => {
	it('renders highlights table correctly', () => {
		const {container} = setup();

		expect(container).toMatchSnapshot();
	});

	it('renders changelog table correctly', () => {
		const {changelogJSONObj} = setup();

		const {container} = render(
			<SortableTable
				jsonObject={changelogJSONObj}
				orderBy="desc"
				tabName="changelog"
			/>
		);

		expect(container).toMatchSnapshot();
	});

	it('renders no results message', () => {
		const {container, getByText, noResults, rerender} = setup();

		rerender(
			<SortableTable
				jsonObject={noResults}
				orderBy="desc"
				tabName="highlights"
			/>
		);

		expect(
			getByText('no-results-found-to-match-your-selection')
		).toBeTruthy();
		expect(container).toMatchSnapshot();
	});

	it('renders in reverse order when sorting indicator is clicked', () => {
		const {container, getByRole} = setup();

		fireEvent.click(getByRole('button'));

		expect(container).toMatchSnapshot();
	});

	it('renders the singular x-result language key when there is only one result', () => {
		const singleResult = {
			results: [
				{
					components: ['Accessibility'],
					description: 'description 2',
					key: 'LPS-85155',
					release: 'GA',
					summary: 'Add menu - Heading order invalid',
					url: '/'
				}
			],
			total: 1
		};

		const {container, getByText} = render(
			<SortableTable
				jsonObject={singleResult}
				orderBy="desc"
				tabName="changelog"
			/>
		);

		expect(getByText('x-result')).toBeTruthy();
		expect(container).toMatchSnapshot();
	});
});