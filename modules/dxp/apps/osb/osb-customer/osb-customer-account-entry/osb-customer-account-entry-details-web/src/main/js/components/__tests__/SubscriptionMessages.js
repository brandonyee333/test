import React from 'react';
import {render} from 'react-testing-library';

import SubscriptionMessages from '../SubscriptionMessages';

describe('SubscriptionMessages', () => {
	const messages = [
		{
			content: 'Message content',
			id: 'id',
			severity: 'urgent',
			title: 'Message Title'
		}
	];

	it('renders correctly', () => {
		const {container} = render(<SubscriptionMessages messages={messages} />);

		expect(container).toMatchSnapshot();
	});
});