import React from 'react';
import {cleanup, fireEvent, render} from '@testing-library/react';

import Accordion from '../Accordion';

const setup = () => {
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

	const utils = render(<Accordion items={items} />);

	return {...utils};
};

afterEach(cleanup);

describe('Accordion', () => {
	it('renders correctly', () => {
		const {container} = setup();

		expect(container).toMatchSnapshot();
	});

	it('does not show panel body on initial load', () => {
		const {queryByText} = setup();

		expect(queryByText('Accordion Body 1')).toBeNull();
	});

	it('expands panel body when clicking on panel title ', () => {
		const {getByText, queryByText} = setup();

		fireEvent.click(getByText('Accordion Title 1'));

		expect(queryByText('Accordion Body 1')).toBeDefined();
	});
});
