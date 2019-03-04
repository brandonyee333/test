import React from 'react';
import {cleanup, fireEvent, render} from 'react-testing-library';

import SubscriptionMessages from '../SubscriptionMessages';

const setup = () => {
	const messages = [
		{
			content: 'Message content 1',
			id: '001',
			severity: 'urgent',
			title: 'Message Title 1'
		},
		{
			content: 'Message content 2',
			id: '002',
			severity: 'info',
			title: 'Message Title 2'
		},
		{
			content: 'Message content 3',
			id: '003',
			severity: 'warning',
			title: 'Message Title 3'
		}
	];

	const utils = render(<SubscriptionMessages messages={messages} />);

	return {...utils};
};

afterEach(cleanup);

describe('SubscriptionMessages', () => {
	it('renders correctly', () => {
		const {container} = setup();

		expect(container).toMatchSnapshot();
	});

	it('dismisses the message after the x icon was clicked on', () => {
		const {getByLabelText, queryAllByRole} = setup();

		let alerts = queryAllByRole('alert');

		expect(alerts.length).toBe(3);

		fireEvent.click(getByLabelText('Close'));

		alerts = queryAllByRole('alert');

		expect(alerts.length).toBe(2);
	});

	it('opens a modal when "View Message" link was clicked', () => {
		const {queryByRole, queryByText} = setup();

		fireEvent.click(queryByText('view-message'));

		expect(queryByRole('dialog')).toBeDefined();
	});
});