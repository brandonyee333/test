import React from 'react';
import {render} from 'react-testing-library';

import Alert from '../Alert';

describe('Alert', () => {
	it('renders correctly', () => {
		const {container} = render(<Alert type="danger">Alert Danger</Alert>);

		expect(container).toMatchSnapshot();
	});

	it('renders with close icon correctly', () => {
		const handleOnClose = () => console.log('close');

		const {container} = render(
			<Alert onClose={handleOnClose} type="info">
				Alert Info
			</Alert>
		);

		expect(container).toMatchSnapshot();
	});
});