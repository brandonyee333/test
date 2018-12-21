import React from 'react';
import TestRenderer from 'react-test-renderer';

import Alert from '../Alert';

describe('Alert', () => {
	it('renders correctly', () => {
		const tree = TestRenderer.create(
			<Alert type="danger">Alert Danger</Alert>
		).toJSON();

		expect(tree).toMatchSnapshot();
	});

	it('renders with close icon correctly', () => {
		const handleOnClose = () => console.log('close');

		const tree = TestRenderer.create(
			<Alert onClose={handleOnClose} type="info">
				Alert Info
			</Alert>
		).toJSON();

		expect(tree).toMatchSnapshot();
	});
});