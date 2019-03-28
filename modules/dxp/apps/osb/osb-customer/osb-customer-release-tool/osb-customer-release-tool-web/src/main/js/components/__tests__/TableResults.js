import React from 'react';
import {render} from 'react-testing-library';

import TableResults from '../TableResults';

describe('TableResults', () => {
	const error = {
		error: {
			message: 'Please enter a valid version range.',
			name: 'class com.liferay.osb.customer.release.tool.web.internal.exception.VersionRangeException'
		}
	};

	const contentObj = {
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

	const tab = {
		tabDescription: 'description for the tab',
		tabName: 'test-tab'
	}

	it('renders error correctly', () => {
		const {container, getByRole} = render(
			<TableResults jsonObject={error} tab={tab} />
		);

		expect(getByRole('alert')).toBeTruthy();
		expect(container).toMatchSnapshot();
	});

	it('renders content correctly', () => {
		const {container, getByRole} = render(
			<TableResults jsonObject={contentObj} tab={tab} />
		);

		expect(getByRole('table')).toBeTruthy();
		expect(container).toMatchSnapshot();
	});
});