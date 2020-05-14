import {fireEvent, getByLabelText, render} from '@testing-library/react';
import React from 'react';

import Modal from '../Modal';

describe('Modal', () => {
	const handleClose = jest.fn();

	it('renders correctly', () => {
		const {container} = render(
			<Modal onClose={handleClose} show>
				Modal
			</Modal>
		);

		expect(container).toMatchSnapshot();
	});

	it('renders with a footer correctly', () => {
		const {container} = render(
			<Modal footer="footer" onClose={handleClose} show>
				Modal with Footer
			</Modal>
		);

		expect(container).toMatchSnapshot();
	});

	it('renders with a header correctly', () => {
		const {container} = render(
			<Modal header="header" onClose={handleClose} show>
				Modal with Header
			</Modal>
		);

		expect(container).toMatchSnapshot();
	});

	it('renders with a certain size correctly', () => {
		const {container} = render(
			<Modal onClose={handleClose} show size="lg">
				Large Modal
			</Modal>
		);

		expect(container).toMatchSnapshot();
	});

	it('does something when close button in header is clicked', () => {
		const {container} = render(
			<Modal header="header" onClose={handleClose} show size="lg">
				Large Modal
			</Modal>
		);

		const closeButton = getByLabelText(container, 'Close');

		fireEvent.click(closeButton);

		expect(handleClose).toHaveBeenCalledTimes(1);
	});
});
