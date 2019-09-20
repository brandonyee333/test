import React from 'react';
import {fireEvent, getByLabelText, render} from 'react-testing-library';

import Alert from '../Alert';

describe('Alert', () => {
	const handleClose = jest.fn();

	it('renders correctly', () => {
		const {container} = render(<Alert type="danger">Alert Danger</Alert>);

		expect(container).toMatchSnapshot();
	});

	it('renders info alert correctly', () => {
		const {container} = render(<Alert type="info">Alert Info</Alert>);

		expect(container).toMatchSnapshot();
	});

	it('renders with close button correctly', () => {
		const {container} = render(
			<Alert onClose={handleClose} type="warning">
				Alert Warning
			</Alert>
		);

		const closeButton = getByLabelText(container, 'Close');

		expect(closeButton).toBeDefined();
		expect(container).toMatchSnapshot();
	});

	it('does something when close button is clicked', () => {
		const {container} = render(
			<Alert onClose={handleClose} type="danger">
				Alert Danger
			</Alert>
		);

		const closeButton = getByLabelText(container, 'Close');

		fireEvent.click(closeButton);

		expect(handleClose).toHaveBeenCalledTimes(1);
	});
});