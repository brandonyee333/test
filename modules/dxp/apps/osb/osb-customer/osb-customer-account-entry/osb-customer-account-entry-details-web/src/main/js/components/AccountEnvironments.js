import React from 'react';
import PropTypes from 'prop-types';

import Accordion from './Accordion';
import AddAccountEnvironmentForm from './AddAccountEnvironmentForm';
import Button from './Button';
import Modal from './Modal';

export default class AccountEnvironments extends React.Component {
	state = {
		showModal: false
	};

	static propTypes = {
		addEnvironmentURL: PropTypes.string.isRequired,
		environments: PropTypes.array.isRequired,
		permitAdd: PropTypes.bool.isRequired,
		environmentConfiguration: PropTypes.object.isRequired
	};

	handleCloseModal = () =>
		this.setState(
			{
				showModal: false
			}
		);

	handleDeleteEnvironment = event => {
		if (!confirm(Liferay.Language.get('are-you-sure-you-want-to-delete-this'))) {
			event.preventDefault();
		}
	};

	handleDisplayModal = () =>
		this.setState(
			{
				showModal: true
			}
		);

	render() {
		const {
			addEnvironmentURL,
			environments,
			permitAdd,
			environmentConfiguration
		} = this.props;

		const {showModal} = this.state;

		const accordionItems = environments.map((environment, index) => ({
			body: (
				<React.Fragment key={index}>
					<div className="col-sm-6">
						<EnvironmentDetail
							label={Liferay.Language.get('operating-system')}
							value={environment.envOSLabel}
						/>

						<EnvironmentDetail
							label={Liferay.Language.get('application-server')}
							value={environment.envASLabel}
						/>

						<EnvironmentDetail
							href={environment.portalExtAccountEnvironmentAttachmentURL}
							label={Liferay.Language.get('portal-ext')}
							value={environment.portalExtAccountEnvironmentAttachmentFileName}
						/>
					</div>

					<div className="col-sm-6">
						<EnvironmentDetail
							label={Liferay.Language.get('java-version')}
							value={environment.envJVMLabel}
						/>

						<EnvironmentDetail
							label={Liferay.Language.get('database')}
							value={environment.envDBLabel}
						/>

						<EnvironmentDetail
							href={environment.patchLevelAccountEnvironmentAttachmentURL}
							label={Liferay.Language.get('patch-info')}
							value={environment.patchLevelAccountEnvironmentAttachmentFileName}
						/>
					</div>

					<div className="col-md-12">
						<Button
							href={environment.editAccountEnvironmentURL}
							size="sm"
							value="edit"
						>
							{Liferay.Language.get('edit')}
						</Button>

						<Button
							href={environment.deleteAccountEnvironmentURL}
							onClick={this.handleDeleteEnvironment}
							size="sm"
							value="delete"
						>
							{Liferay.Language.get('delete')}
						</Button>
					</div>
				</React.Fragment>
			),
			title: (
				<React.Fragment key={index}>
					<h4>{environment.name}</h4>

					<div className="panel-subtitle">
						<span>{Liferay.Language.get('product')}</span>:{' '}
						{environment.productEntryDisplayName}
					</div>

					<div className="panel-subtitle">
						<span>LR</span>: {environment.envLFRLabel}
					</div>
				</React.Fragment>
			)
		}));

		const addAccountEnvironmentForm = (
			<AddAccountEnvironmentForm
				addEnvironmentURL={addEnvironmentURL}
				handleCloseModal={this.handleCloseModal}
				namespace={window.AccountDetailsConstants.namespace}
				environmentConfiguration={environmentConfiguration}
			/>
		);

		return (
			<React.Fragment>
				<h3 className="card-header">
					{Liferay.Language.get('environment-configurations')}

					{permitAdd && (
						<Button
							icon={true}
							onClick={this.handleDisplayModal}
							value="add"
						>
							<svg className="lexicon-icon lexicon-icon-plus">
								<use xlinkHref="#plus" />
							</svg>
						</Button>
					)}

					<Modal
						body={addAccountEnvironmentForm}
						header={Liferay.Language.get('new-environment-configuration')}
						onClose={this.handleCloseModal}
						show={showModal}
					/>
				</h3>

				{!accordionItems.length ? (
					<div className="no-results">
						{Liferay.Language.get('no-environment-details')}
					</div>
				) : (
					<Accordion items={accordionItems} />
				)}
			</React.Fragment>
		);
	}
}

const EnvironmentDetail = props => (
	<div className="environment-detail">
		<div className="environment-label">{props.label}</div>

		{props.value && props.href ? (
			<a href={props.href} target="blank">
				{props.value}
			</a>
		) : (
			<div>{props.value}</div>
		)}
	</div>
);

EnvironmentDetail.propTypes = {
	href: PropTypes.string,
	label: PropTypes.string.isRequired,
	value: PropTypes.string
};