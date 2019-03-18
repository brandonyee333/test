import React from 'react';
import {render} from 'react-testing-library';

import TableResults from '../TableResults';

describe('TableResults', () => {
	const error = {
		error: {
			name:
				'class com.liferay.osb.customer.release.tool.web.internal.exception.VersionRangeException',
			message: 'Please enter a valid version range.'
		}
	};

	const contentObj = {
		results: [
			{
				summary:
					'IE11 Web Image Resizing Does not Maintain Initial Aspect Ratio',
				components: ['Frontend Infrastructure > WYSIWYG'],
				release: 'GA',
				description: 'description',
				key: 'LPS-90100',
				url: '/'
			},
			{
				summary: 'Add menu - Heading order invalid',
				components: ['Accessibility'],
				release: 'GA',
				description: 'description 2',
				key: 'LPS-85155',
				url: '/'
			}
		],
		total: 2
	};

	it('renders error correctly', () => {
		const {container, getByRole} = render(<TableResults jsonObject={error} />);

		expect(getByRole('alert')).toBeTruthy();
		expect(container).toMatchSnapshot();
	});

	it('renders content correctly', () => {
		const {container, getByRole} = render(<TableResults jsonObject={contentObj} />);

		expect(getByRole('table')).toBeTruthy();
		expect(container).toMatchSnapshot();
	});
});