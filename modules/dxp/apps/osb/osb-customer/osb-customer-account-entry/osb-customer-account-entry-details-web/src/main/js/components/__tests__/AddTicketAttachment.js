import React from 'react';
import {fireEvent, render} from '@testing-library/react';

import AddTicketAttachment from '../AddTicketAttachment';

describe('AddTicketAttachment', () => {
	it('renders correctly', () => {
		const {container} = render(
			<AddTicketAttachment
				addTicketAttachmentURL='/'
				generateTokenURL='/'
				uploadURL='/'
				zendeskTicketId='123'
				zendeskTicketURL='/'
			/>
		);

		expect(container).toMatchSnapshot();
	});
});
