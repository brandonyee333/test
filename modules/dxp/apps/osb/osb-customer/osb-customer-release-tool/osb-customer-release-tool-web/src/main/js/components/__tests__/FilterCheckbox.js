import React from 'react';
import {cleanup, fireEvent, render} from '@testing-library/react';

import FilterCheckbox from '../FilterCheckbox';

const setup = () => {
	const handleOnChange = jest.fn();

	const utils = render(
		<FilterCheckbox
			handleOnChange={handleOnChange}
			label="test label"
			value="checkbox value"
		/>
	);

	return {handleOnChange, ...utils};
};

afterEach(cleanup);

describe('FilterCheckbox', () => {
	it('renders correctly', () => {
		const {container} = setup();

		expect(container).toMatchSnapshot();
	});

	it('triggers onChange handler when checkbox is checked', () => {
		const {getByLabelText, handleOnChange} = setup();

		fireEvent.click(getByLabelText('test label'));

		expect(handleOnChange).toBeCalledTimes(1);
	});
});