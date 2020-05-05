import React from 'react';
import PropTypes from 'prop-types';

import Accordion from './Accordion';
import AddSourceCodeAccessModal from './AddSourceCodeAccessModal';
import Button from './Button';

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

	state = {
		showModal: false
	};

	handleCloseModal = () =>
		this.setState({
			showModal: false
		});

	handleDeleteCollaborator = event => {
		if (
			!confirm(
				Liferay.Language.get('are-you-sure-you-want-to-delete-this')
			)
		) {
			event.preventDefault();
		}
	};

	handleDisplayModal = () =>
		this.setState({
			showModal: true
		});

	render() {
		const {addCollaboratorURL, collaborators, namespace} = this.props;

		const {showModal} = this.state;

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

					<div className="col-md-12">
						<Button
							href={collaborator.deleteCollaboratorURL}
							onClick={this.handleDeleteCollaborator}
							size="sm"
							value="delete"
						>
							{Liferay.Language.get('delete')}
						</Button>
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
				<div className="card-header">
					<div>
						<div className="small-title">
							{Liferay.Language.get(
								"team-members-who-have-access-to-liferay's-source-code"
							)}
						</div>

						<div className="panel-subtitle">
							{Liferay.Language.get(
								"add-your-email-address-and-github-username-to-get-access-to-liferay's-source-code"
							)}
						</div>
					</div>

					<Button icon onClick={this.handleDisplayModal} value="add">
						<svg className="lexicon-icon lexicon-icon-plus">
							<use xlinkHref="#plus" />
						</svg>
					</Button>

					<AddSourceCodeAccessModal
						addCollaboratorURL={addCollaboratorURL}
						onClose={this.handleCloseModal}
						show={showModal}
					/>
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
