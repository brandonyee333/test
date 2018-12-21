import React from 'react';
import TestRenderer from 'react-test-renderer';

import Accordion from '../Accordion';

describe('Accordion', () => {
	const items = [
		{
			body: 'Accordion Body',
			title: 'Accordion Title'
		}
	];

	it('renders correctly', () => {
		const tree = TestRenderer.create(<Accordion items={items} />).toJSON();

		expect(tree).toMatchSnapshot();
	});
});