import {render} from 'react-testing-library';
import React from 'react';

import DynamicUploaderForm from '../DynamicUploaderForm';

describe('DynamicUploaderForm', () => {
	it('renders correctly', () => {
		const {container} = render(
			<DynamicUploaderForm
				addTicketAttachmentURL="/"
				fileRepositoryName="United States"
				generateTokenURL="/"
				knowledgeBaseArticle="/"
				uploadURL="/"
			/>
		);

		expect(container).toMatchSnapshot();
	});
});
