import React from 'react';
import {cleanup, render} from 'react-testing-library';

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
				summary:
					'IE11 Web Image Resizing Does not Maintain Initial Aspect Ratio',
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
		<Changelog jiraIssueEndpoint="/" jiraIssueJSONObject={changelogJSONObj} />
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
		const {changelogJSONObj, container, queryByRole} = setup();

		// Pagination will only show if there are more than 50 results

		changelogJSONObj.total < 50
			? expect(queryByRole('navigation')).toBeFalsy()
			: expect(queryByRole('navigation')).toBeTruthy();
		expect(container).toMatchSnapshot();
	});

	it('renders changelog with pagination when results exceed 50 items', () => {
		const {changelogJSONObjSize51} = setup();

		const {container, queryByRole} = render(
			<Changelog
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
			<Changelog jiraIssueEndpoint="/" jiraIssueJSONObject={noResults} />
		);

		expect(queryByRole('navigation')).toBeFalsy();
		expect(container).toMatchSnapshot();
	});
});