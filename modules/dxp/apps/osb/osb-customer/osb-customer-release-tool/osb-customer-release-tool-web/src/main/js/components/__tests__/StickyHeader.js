import React from 'react';
import {render} from 'react-testing-library';

import StickyHeader from '../StickyHeader';

describe('StickyHeader', () => {
	it('renders correctly', () => {
		const {container} = render(<StickyHeader />);

		expect(container).toBeTruthy();
	});
});
