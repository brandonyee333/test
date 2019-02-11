import React from 'react';
import {render} from 'react-testing-library';

import Modal from '../Modal';

describe('Modal', () => {
	const handleOnClose = () => console.log('close');

	it('renders correctly', () => {
		const {container} = render(
			<Modal onClose={handleOnClose} show>
				Modal
			</Modal>
		);

		expect(container).toMatchSnapshot();
	});

	it('renders with a footer correctly', () => {
		const {container} = render(
			<Modal footer="footer" onClose={handleOnClose} show>
				Modal with Footer
			</Modal>
		);

		expect(container).toMatchSnapshot();
	});

	it('renders with a header correctly', () => {
		const {container} = render(
			<Modal header="header" onClose={handleOnClose} show>
				Modal with Header
			</Modal>
		);

		expect(container).toMatchSnapshot();
	});

	it('renders with a certain size correctly', () => {
		const {container} = render(
			<Modal onClose={handleOnClose} show size="lg">
				Large Modal
			</Modal>
		);

		expect(container).toMatchSnapshot();
	});
});