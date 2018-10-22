import React from 'react';
import PropTypes from 'prop-types';

import DynamicUploaderForm from './DynamicUploaderForm';

export default class AddTicketAttachment extends React.Component {
	static propTypes = {
		addTicketAttachmentURL: PropTypes.string.isRequired,
		generateTokenURL: PropTypes.string.isRequired,
		uploadURL: PropTypes.string.isRequired,
		zendeskTicketId: PropTypes.string.isRequired
	};

	render() {
		const {
			addTicketAttachmentURL,
			generateTokenURL,
			uploadURL,
			zendeskTicketId,
			zendeskTicketURL
		} = this.props;

		return (
			<div className='add-ticket-attachment container-fluid-max-md'>
				<h1>
					{Liferay.Language.get('attach-files-to-ticket')}
					{' '}
					<a href={zendeskTicketURL}>#{zendeskTicketId}</a>
				</h1>

				<DynamicUploaderForm
					addTicketAttachmentURL={addTicketAttachmentURL}
					generateTokenURL={generateTokenURL}
					uploadURL={uploadURL}
				/>
			</div>
		);
	}
}