import React from 'react';
import PropTypes from 'prop-types';

import Accordion from './Accordion';

export default class SourceCodeAccess extends React.Component {
	static propTypes = {
		addCollaboratorURL: PropTypes.string.isRequired,
		collaborators: PropTypes.arrayOf(
			PropTypes.shape({
				collaboratorId: PropTypes.number.isRequired,
				deleteCollaboratorURL: PropTypes.string.isRequired,
				emailAddress: PropTypes.string.isRequired,
				fullName: PropTypes.string.isRequired,
				gitHubUserName: PropTypes.string.isRequired
			})
		).isRequired
	};

	render() {
		const {collaborators} = this.props;

		const accordionItems = collaborators.map(collaborator => ({
			body: (
				<React.Fragment>
					<div className="col-sm-6">
						<CollaboratorDetail
							label={Liferay.Language.get('name')}
							value={collaborator.fullName}
						/>

						<CollaboratorDetail
							label={Liferay.Language.get('email')}
							value={collaborator.emailAddress}
						/>
					</div>

					<div className="col-sm-6">
						<CollaboratorDetail
							label={Liferay.Language.get('github-username')}
							value={collaborator.gitHubUserName}
						/>
					</div>
				</React.Fragment>
			),
			title: (
				<React.Fragment>
					<h4>{collaborator.fullName}</h4>

					<div className="panel-subtitle">
						{collaborator.gitHubUserName}
					</div>

					<div className="panel-subtitle">
						{collaborator.emailAddress}
					</div>
				</React.Fragment>
			)
		}));

		return (
			<React.Fragment>
				<div className="card-header small-title">
					{Liferay.Language.get(
						"team-members-who-have-access-to-liferay's-source-code"
					)}
				</div>

				{!accordionItems.length ? (
					<div className="no-results">
						{Liferay.Language.get('no-collaborator-details')}
					</div>
				) : (
					<Accordion items={accordionItems} />
				)}
			</React.Fragment>
		);
	}
}

CollaboratorDetail.propTypes = {
	href: PropTypes.string,
	label: PropTypes.string.isRequired,
	value: PropTypes.string
};

function CollaboratorDetail(props) {
	return (
		<div className="panel-detail">
			<div className="panel-label widespace">{props.label}</div>

			{props.value && props.href ? (
				<a href={props.href} rel="noopener noreferrer" target="_blank">
					{props.value}
				</a>
			) : (
				<div>{props.value}</div>
			)}
		</div>
	);
}
