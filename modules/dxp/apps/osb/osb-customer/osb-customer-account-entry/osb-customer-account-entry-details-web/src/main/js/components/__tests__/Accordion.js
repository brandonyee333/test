import React from 'react';
import {fireEvent, render} from 'react-testing-library';

import Accordion from '../Accordion';

describe('Accordion', () => {
	const items = [
		{
			body: 'Accordion Body 1',
			title: 'Accordion Title 1'
		},
		{
			body: 'Accordion Body 2',
			title: 'Accordion Title 2'
		}
	];

	it('renders correctly', () => {
		const {container} = render(<Accordion items={items} />);

		expect(container).toMatchSnapshot();
	});

	it('does not show panel body on initial load', () => {
		const {queryByText} = render(<Accordion items={items} />);

		const accordionBody = queryByText('Accordion Body 1');

		expect(accordionBody).toBeNull();
	});

	it('expands panel body when clicking on panel title ', () => {
		const {getByText, queryByText} = render(<Accordion items={items} />);

		fireEvent.click(getByText('Accordion Title 1'));

		const accordionBody = queryByText('Accordion Body 1');

		expect(accordionBody).toBeDefined();
	});
});