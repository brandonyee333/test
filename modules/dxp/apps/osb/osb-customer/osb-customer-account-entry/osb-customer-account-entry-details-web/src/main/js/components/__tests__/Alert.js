import React from 'react';
import Alert from '../Alert';
import TestRenderer from 'react-test-renderer';

it('renders correctly', () => {
	const tree = TestRenderer.create(
		<Alert type="danger">Alert Danger</Alert>
	).toJSON();

	expect(tree).toMatchSnapshot();
});

it('renders close icon correctly', () => {
	const handleOnClose = () => console.log('close');

	const tree = TestRenderer.create(
		<Alert onClose={handleOnClose} type="info">
			Alert Info
		</Alert>
	).toJSON();

	expect(tree).toMatchSnapshot();
});