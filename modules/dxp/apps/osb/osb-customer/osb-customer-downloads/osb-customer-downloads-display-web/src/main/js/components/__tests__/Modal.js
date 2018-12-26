import React from 'react';
import TestRenderer from 'react-test-renderer';

import Modal from '../Modal';

describe('Modal', () => {
	const handleOnClose = () => console.log('close');

	it('renders correctly', () => {
		const tree = TestRenderer.create(
			<Modal onClose={handleOnClose} show>
				Modal
			</Modal>
		).toJSON();

		expect(tree).toMatchSnapshot();
	});

	it('renders with a footer correctly', () => {
		const tree = TestRenderer.create(
			<Modal footer="footer" onClose={handleOnClose} show>
				Modal with Footer
			</Modal>
		).toJSON();

		expect(tree).toMatchSnapshot();
	});

	it('renders with a header correctly', () => {
		const tree = TestRenderer.create(
			<Modal header="header" onClose={handleOnClose} show>
				Modal with Header
			</Modal>
		).toJSON();

		expect(tree).toMatchSnapshot();
	});

	it('renders with a certain size correctly', () => {
		const tree = TestRenderer.create(
			<Modal onClose={handleOnClose} show size="lg">
				Large Modal
			</Modal>
		).toJSON();

		expect(tree).toMatchSnapshot();
	});
});