import React from 'react';
import {cleanup, fireEvent, render} from 'react-testing-library';

import SortableTable from '../SortableTable';

const setup = () => {
	const fixPackJSONObject = {
		results: [
			{
				content: 'Content Text',
				releaseDate: 'Mar 6, 2019',
				resourcePrimKey: '118267960',
				title: 'Fix Pack 2'
			},
			{
				content: 'Content 2 Text',
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

	const utils = render(<SortableTable fixPackJSONObject={fixPackJSONObject} />);

	return {
		noResults,
		...utils
	};
};

afterEach(cleanup);

describe('SortableTable', () => {
	it('renders correctly', () => {
		const {container} = setup();

		expect(container).toMatchSnapshot();
	});

	it('renders no results message', () => {
		const {container, getByText, noResults, rerender} = setup();

		rerender(<SortableTable fixPackJSONObject={noResults} />);

		expect(
			getByText('no-highlights-found-to-match-your-selection')
		).toBeTruthy();
		expect(container).toMatchSnapshot();
	});

	it('renders in reverse order when sorting indicator is clicked', () => {
		const {container, getByRole} = setup();

		fireEvent.click(getByRole('button'));

		expect(container).toMatchSnapshot();
	});
});