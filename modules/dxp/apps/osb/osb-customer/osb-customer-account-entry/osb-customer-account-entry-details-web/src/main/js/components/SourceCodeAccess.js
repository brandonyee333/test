import axios from 'axios';
import {Map} from 'immutable';
import React, {useState} from 'react';
import PropTypes from 'prop-types';

import Accordion from './Accordion';
import AddSourceCodeAccessModal from './AddSourceCodeAccessModal';
import Button from './Button';

import {langSub} from '../helpers/language';
import {CollaboratorsRecord} from '../store/sourceCodeAccessCollaborator';

const ACCESS_DXP_SOURCE_CODE_ARTICLE =
	'https://help.liferay.com/hc/articles/360045389291';

SourceCodeAccess.propTypes = {
	addCollaboratorURL: PropTypes.string.isRequired,
	collaborators: PropTypes.arrayOf(
		PropTypes.shape({
			collaboratorId: PropTypes.number.isRequired,
			createDate: PropTypes.string.isRequired,
			deleteCollaboratorURL: PropTypes.string.isRequired,
			emailAddress: PropTypes.string.isRequired,
			fullName: PropTypes.string.isRequired,
			gitHubUserName: PropTypes.string.isRequired
		})
	).isRequired
};

export default function SourceCodeAccess({addCollaboratorURL, collaborators}) {
	const processedCollaborators = collaborators.map(collaborator => [
		collaborator.collaboratorId,
		CollaboratorsRecord({
			collaboratorId: collaborator.collaboratorId,
			createDate: collaborator.createDate,
			deleteCollaboratorURL: collaborator.deleteCollaboratorURL,
			emailAddress: collaborator.emailAddress,
			fullName: collaborator.fullName,
			gitHubUserName: collaborator.gitHubUserName
		})
	]);

	const [showModal, setShowModal] = useState(false);
	const [collaboratorsMap, setCollaboratorsMap] = useState(
		Map(processedCollaborators)
	);

	function addCollaboratorToMap(collaborator) {
		setCollaboratorsMap(
			collaboratorsMap.set(collaborator.collaboratorId, collaborator)
		);
	}

	function deleteCollaboratorFromMap(collaborator) {
		axios
			.post(collaborator.deleteCollaboratorURL)
			.then(({data}) => {
				if (data.message === 'success') {
					setCollaboratorsMap(
						collaboratorsMap.delete(collaborator.collaboratorId)
					);
				}
			})
			.catch(error => {
				console.error(error);
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

	function sortDateByRecency(a, b) {
		return new Date(a.createDate) > new Date(b.createDate) ? -1 : 1;
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
							"team-members-who-have-access-to-liferay-dxp's-source-code"
						)}
					</div>

					<PanelSubHeading />
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

function PanelSubHeading() {
	const subtitle = langSub(
		Liferay.Language.get(
			"add-your-email-address-and-github-username-to-get-access-to-liferay-dxp's-source-code-refer-to-this-article-for-details"
		),
		['btn-link component-title', ACCESS_DXP_SOURCE_CODE_ARTICLE],
		false
	);

	return <div className="panel-subtitle">
		<div dangerouslySetInnerHTML={{__html: subtitle}} />
	</div>;
}
