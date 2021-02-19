import React from 'react';
import PropTypes from 'prop-types';

import Alert from './Alert';
import DynamicUploaderForm from './DynamicUploaderForm';

export default class AddTicketAttachment extends React.Component {
	static propTypes = {
		addTicketAttachmentURL: PropTypes.string.isRequired,
		fileRepositoryName: PropTypes.string.isRequired,
		generateTokenURL: PropTypes.string.isRequired,
		knowledgeBaseArticle: PropTypes.string.isRequired,
		uploadURL: PropTypes.string.isRequired,
		zendeskTicketId: PropTypes.string.isRequired,
		zendeskTicketURL: PropTypes.string.isRequired
	};

	render() {
		const {
			addTicketAttachmentURL,
			fileRepositoryName,
			generateTokenURL,
			knowledgeBaseArticle,
			uploadURL,
			zendeskTicketId,
			zendeskTicketURL
		} = this.props;

		return (
			<div className="add-ticket-attachment container-fluid-max-md">
				<h1>
					{Liferay.Language.get('attach-file-to-ticket')}{' '}
					<a href={zendeskTicketURL}>#{zendeskTicketId}</a>
				</h1>

				<Alert type="info">
					<span className="lead">
						{`${Liferay.Language.get('info')}:`}
					</span>{' '}
					{Liferay.Language.get(
						'only-one-file-can-be-attached-at-a-time'
					)}
				</Alert>

				<DynamicUploaderForm
					addTicketAttachmentURL={addTicketAttachmentURL}
					fileRepositoryName={fileRepositoryName}
					generateTokenURL={generateTokenURL}
					knowledgeBaseArticle={knowledgeBaseArticle}
					uploadURL={uploadURL}
				/>
			</div>
		);
	}
}
