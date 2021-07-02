import {render} from 'react-testing-library';
import React from 'react';

import AddTicketAttachment from '../AddTicketAttachment';

describe('AddTicketAttachment', () => {
	it('renders correctly', () => {
		const {container} = render(
			<AddTicketAttachment
				addTicketAttachmentURL="/"
				dataAccessArticleURL="/"
				fileRepositoryMessage="Your file will be uploaded to a file server in the United States."
				generateTokenURL="/"
				uploadURL="/"
				zendeskTicketId="123"
				zendeskTicketURL="/"
			/>
		);

		expect(container).toMatchSnapshot();
	});
});
