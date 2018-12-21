import React from 'react';
import TestRenderer from 'react-test-renderer';

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
		const tree = TestRenderer.create(
			<SubscriptionMessages messages={messages} />
		).toJSON();

		expect(tree).toMatchSnapshot();
	});
});