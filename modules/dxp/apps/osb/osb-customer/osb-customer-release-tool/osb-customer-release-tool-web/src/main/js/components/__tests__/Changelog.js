import React from 'react';
import {cleanup, fireEvent, render} from 'react-testing-library';

import times from 'lodash.times';

import Changelog from '../Changelog';

const setup = () => {
	const changelogJSONObj = {
		results: [
			{
				components: ['Frontend Infrastructure > WYSIWYG'],
				description: 'description',
				key: 'LPS-90100',
				release: 'GA',
				summary: 'summary 1',
				url: '/'
			},
			{
				components: ['Accessibility'],
				description: 'description 2',
				key: 'LPS-85155',
				release: 'GA',
				summary: 'summary 2',
				url: '/'
			}
		],
		total: 2
	};

	const changelogJSONObjSize51 = {
		results: times(51, i => {
			return {
				components: [`component ${i}`],
				description: `description ${i}`,
				key: `${i}`,
				release: 'GA',
				summary: `summary ${i}`,
				url: '/'
			};
		}),
		total: 51
	};

	const noResults = {
		results: [],
		total: 0
	};

	const utils = render(
		<Changelog
			description="description"
			jiraIssueEndpoint="/"
			jiraIssueJSONObject={changelogJSONObj}
		/>
	);

	return {
		changelogJSONObj,
		changelogJSONObjSize51,
		noResults,
		...utils
	};
};

afterEach(cleanup);

describe('Changelog', () => {
	it('renders changelog results correctly', () => {
		const {container, queryByRole} = setup();

		expect(queryByRole('navigation')).toBeFalsy();
		expect(container).toMatchSnapshot();
	});

	it('renders changelog with pagination when results exceed 50 items', () => {
		const {changelogJSONObjSize51} = setup();

		const {container, queryByRole} = render(
			<Changelog
				description="description"
				jiraIssueEndpoint="/"
				jiraIssueJSONObject={changelogJSONObjSize51}
			/>
		);

		expect(queryByRole('navigation')).toBeTruthy();
		expect(container).toMatchSnapshot();
	});

	it('renders no results correctly', () => {
		const {noResults} = setup();

		const {container, queryByRole} = render(
			<Changelog
				description="description"
				jiraIssueEndpoint="/"
				jiraIssueJSONObject={noResults}
			/>
		);

		expect(queryByRole('navigation')).toBeFalsy();
		expect(container).toMatchSnapshot();
	});

	it('renders details modal when a ticket summary is clicked', () => {
		const {container, queryByRole, queryByText} = setup();

		fireEvent.click(queryByText('summary 1'));

		expect(queryByRole('dialog')).toBeTruthy();
		expect(queryByText('details')).toBeTruthy();
		expect(container).toMatchSnapshot();
	});

	it('updates details modal when a different ticket summary is clicked', () => {
		const {container, queryByRole, queryByText} = setup();

		fireEvent.click(queryByText('summary 1'));

		expect(queryByRole('dialog')).toBeTruthy();
		expect(queryByText('details')).toBeTruthy();

		fireEvent.click(queryByText('summary 2'));
		expect(container).toMatchSnapshot();
	});
});