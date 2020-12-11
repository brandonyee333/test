import {queryByText, render} from 'react-testing-library';
import React from 'react';

import SupportInstructions from '../SupportInstructions';

describe('SupportInstructions', () => {
	it('renders correctly', () => {
		const {container} = render(
			<SupportInstructions instructions="instructions" />
		);

		expect(container).toMatchSnapshot();
	});

	it('renders no results message when there is no existing instruction', () => {
		const {container} = render(
			<SupportInstructions
				accountEntryId="accountEntryId"
				editInstructionsURL="/"
				instructions=""
			/>
		);

		const noResultsMsgs = queryByText(container, 'no-support-instructions');

		expect(noResultsMsgs).toBeTruthy();
		expect(container).toMatchSnapshot();
	});
});
