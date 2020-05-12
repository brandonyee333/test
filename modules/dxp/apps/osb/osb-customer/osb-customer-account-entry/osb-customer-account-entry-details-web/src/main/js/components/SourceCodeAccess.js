import axios from 'axios';
import {Map, Record} from 'immutable';
import React, {useState} from 'react';
import PropTypes from 'prop-types';

import Accordion from './Accordion';
import AddSourceCodeAccessModal from './AddSourceCodeAccessModal';
import Button from './Button';

const sortDateByRecency = (a, b) =>
	new Date(a.createDate) > new Date(b.createDate) ? -1 : 1;

export const CollabRecord = Record({
	createDate: null,
	deleteURL: '',
	emailAddress: '',
	fullName: '',
	gitHubUserName: '',
	id: null
});

SourceCodeAccess.propTypes = {
	addCollaboratorURL: PropTypes.string.isRequired,
	collaborators: PropTypes.arrayOf(
		PropTypes.shape({
			createDate: PropTypes.string.isRequired,
			deleteURL: PropTypes.string.isRequired,
			emailAddress: PropTypes.string.isRequired,
			fullName: PropTypes.string.isRequired,
			gitHubUserName: PropTypes.string.isRequired,
			id: PropTypes.number.isRequired
		})
	).isRequired
};

export default function SourceCodeAccess({addCollaboratorURL, collaborators}) {
	const processedCollaborators = collaborators.map(collaborator => [
		collaborator.id,
		CollabRecord({
			createDate: collaborator.createDate,
			deleteURL: collaborator.deleteURL,
			emailAddress: collaborator.emailAddress,
			fullName: collaborator.fullName,
			gitHubUserName: collaborator.gitHubUserName,
			id: collaborator.id
		})
	]);

	const [showModal, setShowModal] = useState(false);
	const [collaboratorsMap, setCollaboratorsMap] = useState(
		Map(processedCollaborators)
	);

	function addCollaboratorToMap(collaborator) {
		setCollaboratorsMap(
			collaboratorsMap.set(collaborator.id, collaborator)
		);
	}

	function deleteCollaboratorFromMap(collaborator) {
		axios
			.post(collaborator.deleteURL)
			.then(({data}) => {
				if (data.message === 'success') {
					setCollaboratorsMap(
						collaboratorsMap.delete(collaborator.id)
					);
				}
			})
			.catch(error => {
				console.log(error);
			});
	}

	function handleCloseModal() {
		setShowModal(false);
	}

	function handleDeleteCollaborator(collaborator) {
		if (
			confirm(
				Liferay.Language.get('are-you-sure-you-want-to-delete-this')
			)
		) {
			deleteCollaboratorFromMap(collaborator);
		}
	}

	function handleDisplayModal() {
		setShowModal(true);
	}

	const sortedCollaborators = [...collaboratorsMap.values()].sort(
		sortDateByRecency
	);

	const accordionItems = sortedCollaborators.map(collaborator => ({
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
						display="secondary"
						onClick={event =>
							handleDeleteCollaborator(collaborator)
						}
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

				<Button icon onClick={handleDisplayModal} value="add">
					<svg className="lexicon-icon lexicon-icon-plus">
						<use xlinkHref="#plus" />
					</svg>
				</Button>

				<AddSourceCodeAccessModal
					addCollaboratorToMap={addCollaboratorToMap}
					addCollaboratorURL={addCollaboratorURL}
					onClose={handleCloseModal}
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
