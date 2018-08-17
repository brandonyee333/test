import React from 'react';
import PropTypes from 'prop-types';

import Accordion from './Accordion';
import Button from './Button';
import Modal from './Modal';

export default class AccountEnvironments extends React.Component {
	state = {
		modalTriggered: false
	};

	static propTypes = {
		environments: PropTypes.array.isRequired,
		permitAdd: PropTypes.bool.isRequired
	};

	closeAddEnvironmentModal = () => this.setState(
		{
			modalTriggered: false
		}
	);

	handleDeleteEnvironment = event => {
		if (
			!confirm(
				Liferay.Language.get('are-you-sure-you-want-to-delete-this')
			)
		) {
			event.preventDefault();
		}
	};

	triggerAddEnvironmentModal = () => this.setState(
		{
			modalTriggered: true
		}
	);

	render() {
		const {environments, permitAdd} = this.props;
		const {modalTriggered} = this.state;

		const accordionItems = environments.map(
			(environment, index) => (
				{
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
								<Button href={environment.editAccountEnvironmentURL} value="edit">
									{Liferay.Language.get('edit')}
								</Button>

								<Button
									href={environment.deleteAccountEnvironmentURL}
									onClick={event => this.handleDeleteEnvironment(event)}
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
								<span>{Liferay.Language.get('product')}</span>: 
								{environment.productEntryDisplayName}
							</div>

							<div className="panel-subtitle">
								<span>LR</span>: {environment.envLFRLabel}
							</div>
						</React.Fragment>
					)
				}
			)
		);

		return (
			<React.Fragment>
				<h3 className="card-header">
					{Liferay.Language.get('environment-configurations')}

					{permitAdd && (
						<Button onClick={this.triggerAddEnvironmentModal} value="add">
							+
						</Button>
					)}

					{modalTriggered && (
						<Modal
							body=""
							closeModal={this.closeAddEnvironmentModal}
							header={Liferay.Language.get('new-environment-configuration')}
							showModal={modalTriggered}
						/>
					)}
				</h3>

				{!accordionItems.length ? (
					<div className="no-results">{Liferay.Language.get('no-environment-details')}</div>
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