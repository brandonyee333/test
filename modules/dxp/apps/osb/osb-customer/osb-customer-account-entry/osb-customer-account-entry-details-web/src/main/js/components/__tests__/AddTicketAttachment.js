import {render} from 'react-testing-library';
import React from 'react';

import AddTicketAttachment from '../AddTicketAttachment';

describe('AddTicketAttachment', () => {
	it('renders correctly', () => {
		const {container} = render(
			<AddTicketAttachment
				addTicketAttachmentURL="/"
				fileRepositoryName="United States"
				generateTokenURL="/"
				knowledgeBaseArticle="/"
				uploadURL="/"
				zendeskTicketId="123"
				zendeskTicketURL="/"
			/>
		);

		expect(container).toMatchSnapshot();
	});
});
