import React from 'react';
import {
	fireEvent,
	getByLabelText,
	queryAllByRole,
	queryByRole,
	queryByText,
	render
} from 'react-testing-library';

import SubscriptionMessages from '../SubscriptionMessages';

describe('SubscriptionMessages', () => {
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

	it('renders correctly', () => {
		const {container} = render(<SubscriptionMessages messages={messages} />);

		expect(container).toMatchSnapshot();
	});

	it('dismisses the message after the x icon was clicked on', () => {
		const {container} = render(<SubscriptionMessages messages={messages} />);

		let alerts = queryAllByRole(container, 'alert');

		expect(alerts.length).toBe(3);

		const closeButton = getByLabelText(container, 'Close');

		fireEvent.click(closeButton);

		alerts = queryAllByRole(container, 'alert');

		expect(alerts.length).toBe(2);
	});

	it('opens a modal when "View Message" link was clicked', () => {
		const {container} = render(<SubscriptionMessages messages={messages} />);

		const viewMessage = queryByText(container, 'view-message');

		fireEvent.click(viewMessage);

		const modal = queryByRole(container, 'dialog');

		expect(modal).toBeDefined();
	});
});