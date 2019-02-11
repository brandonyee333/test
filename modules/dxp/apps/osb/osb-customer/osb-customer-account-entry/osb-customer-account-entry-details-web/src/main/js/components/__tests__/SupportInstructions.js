import React from 'react';
import {render} from 'react-testing-library';

import SupportInstructions from '../SupportInstructions';

describe('SupportInstructions', () => {
	it('renders correctly', () => {
		const {container} = render(
			<SupportInstructions
				accountEntryId="accountEntryId"
				editInstructionsURL="/"
				instructions="instructions"
			/>
		);

		expect(container).toMatchSnapshot();
	});
});