import React from 'react';
import TestRenderer from 'react-test-renderer';

import SupportInstructions from '../SupportInstructions';

describe('SupportInstructions', () => {
	it('renders correctly', () => {
		const tree = TestRenderer.create(
			<SupportInstructions
				accountEntryId="accountEntryId"
				editInstructionsURL="/"
				instructions="instructions"
			/>
		).toJSON();

		expect(tree).toMatchSnapshot();
	});
});