import {render} from 'react-testing-library';
import React from 'react';

import DynamicUploaderForm from '../DynamicUploaderForm';

describe('DynamicUploaderForm', () => {
	it('renders correctly', () => {
		const {container} = render(
			<DynamicUploaderForm
				addTicketAttachmentURL="/"
				fileRepositoryMessage="Your file will be uploaded to a file server in the United States."
				generateTokenURL="/"
				knowledgeBaseArticle="/"
				uploadURL="/"
			/>
		);

		expect(container).toMatchSnapshot();
	});
});
