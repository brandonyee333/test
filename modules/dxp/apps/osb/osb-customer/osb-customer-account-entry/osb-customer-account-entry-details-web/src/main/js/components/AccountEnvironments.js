import React from 'react';
import PropTypes from 'prop-types';

import Accordion from './Accordion';
import Button from './Button';

export default class AccountEnvironments extends React.Component {
	static propTypes = {
		environments: PropTypes.array.isRequired,
		permitAdd: PropTypes.bool.isRequired
	};

	handleDeleteEnvironment = () =>
		window.confirm(
			Liferay.Language.get('are-you-sure-you-want-to-delete-this')
		);

	render() {
		const {environments, permitAdd} = this.props;

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
									onClick={this.handleDeleteEnvironment}
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
								<span>LR</span>: {environment.envLFRLabel}
							</div>
						</React.Fragment>
					)
				}
			)
		);

		return (
			<React.Fragment>
				<h3>
					{Liferay.Language.get('environment-configurations')}

					{permitAdd && <Button value="add">+</Button>}
				</h3>

				{!accordionItems.length ? (
					<div>{Liferay.Language.get('no-environment-details')}</div>
				) : (
					<Accordion items={accordionItems} />
				)}
			</React.Fragment>
		);
	}
}

const EnvironmentDetail = props => (
	<React.Fragment>
		<div className="environment-label">{props.label}</div>

		{props.value && props.href ? (
			<a href={props.href} target="blank">
				{props.value}
			</a>
		) : (
			<div>{props.value}</div>
		)}
	</React.Fragment>
);

EnvironmentDetail.propTypes = {
	href: PropTypes.string,
	label: PropTypes.string.isRequired,
	value: PropTypes.string
};