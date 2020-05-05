import React from 'react';
import PropTypes from 'prop-types';

import Accordion from './Accordion';
import EditAccountEnvironmentForm from './EditAccountEnvironmentForm';
import Button from './Button';
import Modal from './Modal';

export default class AccountEnvironments extends React.Component {
	static defaultProps = {
		environments: [],
		permitAdd: false,
		permitDelete: false,
		permitEdit: false
	};

	static propTypes = {
		addEnvironmentURL: PropTypes.string.isRequired,
		environmentConfiguration: PropTypes.shape({
			envCommerce: PropTypes.shape({
				envCommerceVersions: PropTypes.arrayOf(PropTypes.object),
				envLFRVersions: PropTypes.arrayOf(PropTypes.object)
			}),
			envLFRVersions: PropTypes.arrayOf(PropTypes.object),
			products: PropTypes.arrayOf(PropTypes.object)
		}).isRequired,
		environments: PropTypes.arrayOf(PropTypes.object).isRequired,
		permitAdd: PropTypes.bool,
		permitDelete: PropTypes.bool,
		permitEdit: PropTypes.bool
	};

	state = {
		environment: null,
		showModal: false
	};

	handleCloseModal = () =>
		this.setState({
			showModal: false
		});

	handleDeleteEnvironment = event => {
		if (
			!confirm(
				Liferay.Language.get('are-you-sure-you-want-to-delete-this')
			)
		) {
			event.preventDefault();
		}
	};

	handleDisplayEditModal = environment =>
		this.setState({
			environment: environment,
			showModal: true
		});

	handleDisplayNewModal = () =>
		this.setState({
			environment: null,
			showModal: true
		});

	render() {
		const {
			addEnvironmentURL,
			environmentConfiguration,
			environments,
			permitAdd,
			permitDelete,
			permitEdit
		} = this.props;

		const {environment, showModal} = this.state;

		const accordionItems = environments.map(environment => ({
			body: (
				<React.Fragment>
					<div className="col-sm-6">
						{environment.envCommerceLabel !== 'N/A' && (
							<EnvironmentDetail
								label={Liferay.Language.get('commerce-version')}
								value={environment.envCommerceLabel}
							/>
						)}

						<EnvironmentDetail
							label={Liferay.Language.get('operating-system')}
							value={environment.envOSLabel}
						/>

						<EnvironmentDetail
							label={Liferay.Language.get('application-server')}
							value={environment.envASLabel}
						/>

						{!!environment.envSearchLabels.length && (
							<EnvironmentDetail
								label={Liferay.Language.get('search')}
								value={environment.envSearchLabels
									.toString()
									.replace(/,/g, ', ')}
							/>
						)}

						{environment.envCSLabel !== 'N/A' && (
							<EnvironmentDetail
								label={Liferay.Language.get('cloud-services')}
								value={environment.envCSLabel}
							/>
						)}

						<EnvironmentDetail
							href={
								environment.portalExtAccountEnvironmentAttachmentURL
							}
							label={Liferay.Language.get('portal-ext')}
							value={
								environment.portalExtAccountEnvironmentAttachmentFileName
							}
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
							label={Liferay.Language.get('browser')}
							value={environment.envBrowserLabel}
						/>

						<EnvironmentDetail
							href={
								environment.patchLevelAccountEnvironmentAttachmentURL
							}
							label={Liferay.Language.get('patch-info')}
							value={
								environment.patchLevelAccountEnvironmentAttachmentFileName
							}
						/>
					</div>

					{(permitDelete || permitEdit) && (
						<div className="col-md-12">
							{permitEdit && (
								<Button
									display="link"
									onClick={() =>
										this.handleDisplayEditModal(environment)
									}
									size="sm"
									type="button"
									value="edit"
								>
									{Liferay.Language.get('edit')}
								</Button>
							)}

							{permitDelete && (
								<Button
									href={
										environment.deleteAccountEnvironmentURL
									}
									onClick={this.handleDeleteEnvironment}
									size="sm"
									value="delete"
								>
									{Liferay.Language.get('delete')}
								</Button>
							)}
						</div>
					)}
				</React.Fragment>
			),
			title: (
				<React.Fragment>
					<h4>{environment.name}</h4>

					<div className="panel-subtitle">
						{environment.productEntryDisplayName}
					</div>

					<div className="panel-subtitle">
						{environment.envLFRLabel}
					</div>
				</React.Fragment>
			)
		}));

		const modalHeader = environment
			? Liferay.Language.get('edit-environment-configuration')
			: Liferay.Language.get('new-environment-configuration');

		return (
			<React.Fragment>
				<div className="card-header small-title">
					{Liferay.Language.get('environment-configurations')}

					{permitAdd && (
						<Button
							icon
							onClick={this.handleDisplayNewModal}
							value="add"
						>
							<svg className="lexicon-icon lexicon-icon-plus">
								<use xlinkHref="#plus" />
							</svg>
						</Button>
					)}

					<Modal
						header={modalHeader}
						onClose={this.handleCloseModal}
						show={showModal}
					>
						<EditAccountEnvironmentForm
							addEnvironmentURL={addEnvironmentURL}
							environment={environment}
							environmentConfiguration={environmentConfiguration}
							handleCloseModal={this.handleCloseModal}
							namespace={window.AccountDetailsConstants.namespace}
						/>
					</Modal>
				</div>

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

EnvironmentDetail.propTypes = {
	href: PropTypes.string,
	label: PropTypes.string.isRequired,
	value: PropTypes.string
};

function EnvironmentDetail(props) {
	return (
		<div className="panel-detail">
			<div className="panel-label">{props.label}</div>

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
