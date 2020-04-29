import React from 'react';
import PropTypes from 'prop-types';

import Alert from './Alert';
import Button from './Button';
import Modal from './Modal';

import {defaultFetch} from '../helpers/api';
import {sub} from '../helpers/language';

const ERROR_VALIDATION = {
	emailAddress: /^[\w-\.]+@([\w-]+\.)+[\w-]{2,6}$/i,
	fullName: /^[a-z,.'-]+\s[a-z\s,.'-]+$/i,
	gitHubUserName: /^[a-z\d](?:[a-z\d]|-(?=[a-z\d])){0,38}$/i
};

const LENGTH_OF_DAYS = '3';

export default class AddSourceCodeAccessModal extends React.Component {
	static propTypes = {
		addCollaboratorURL: PropTypes.string.isRequired,
		namespace: PropTypes.string.isRequired,
		onClose: PropTypes.func.isRequired,
		show: PropTypes.bool.isRequired
	};

	state = {
		emailAddress: {value: '', error: false},
		confirmation: '',
		fieldErrors: [],
		fullName: {value: '', error: false},
		gitHubUserName: {value: '', error: false}
	};

	handleClose = () => {
		this.setState({
			emailAddress: {value: '', error: false},
			confirmation: '',
			fieldErrors: [],
			fullName: {value: '', error: false},
			gitHubUserName: {value: '', error: false}
		});

		this.props.onClose();
	};

	handleEmailChange = value => {
		this.setState({
			emailAddress: {value: value, error: false}
		});
	};

	handleFullNameChange = value => {
		this.setState({
			fullName: {value: value, error: false}
		});
	};

	handleGitHubChange = value => {
		this.setState({
			gitHubUserName: {value: value, error: false}
		});
	};

	handleUpdate = event => {
		event.preventDefault();

		if (
			!(
				this.state.emailAddress.value.match(
					ERROR_VALIDATION.emailAddress
				) &&
				this.state.fullName.value.match(ERROR_VALIDATION.fullName) &&
				this.state.gitHubUserName.value.match(
					ERROR_VALIDATION.gitHubUserName
				)
			)
		) {
			this.setState(state => {
				const emailAddressError = !state.emailAddress.value.match(
					ERROR_VALIDATION.emailAddress
				);
				const fullNameError = !state.fullName.value.match(
					ERROR_VALIDATION.fullName
				);
				const gitHubUserNameError = !state.gitHubUserName.value.match(
					ERROR_VALIDATION.gitHubUserName
				);

				return {
					emailAddress: {
						value: state.emailAddress.value,
						error: emailAddressError
					},
					fullName: {
						value: state.fullName.value,
						error: fullNameError
					},
					gitHubUserName: {
						value: state.gitHubUserName.value,
						error: gitHubUserNameError
					},
					fieldErrors: [
						fullNameError,
						emailAddressError,
						gitHubUserNameError
					]
				};
			});
		} else {
			const {addCollaboratorURL, namespace} = this.props;
			const {emailAddress, fullName, gitHubUserName} = this.state;

			const formData = new FormData();

			formData.append([`${namespace}fullName`], fullName.value);
			formData.append([`${namespace}emailAddress`], emailAddress.value);
			formData.append(
				[`${namespace}gitHubUserName`],
				gitHubUserName.value
			);

			defaultFetch(addCollaboratorURL, {
				body: formData,
				method: 'POST'
			})
				.then(response => response.text())
				.then(text => {
					this.setState({
						confirmation: 'success'
					});
				})
				.catch(error => {
					this.setState({
						confirmation: 'wait'
					});
				});
		}
	};

	render() {
		const {show} = this.props;

		const {
			emailAddress,
			confirmation,
			fieldErrors,
			fullName,
			gitHubUserName
		} = this.state;

		const fieldNames = [
			Liferay.Language.get('name'),
			Liferay.Language.get('email-address'),
			Liferay.Language.get('github-username')
		];

		const fieldsToUpdate = fieldErrors
			.map((item, index) => (item ? fieldNames[index] : ''))
			.filter(item => item);

		const modalFooter = (
			<form onSubmit={this.handleUpdate}>
				<div className="btn-row">
					<Button
						display="default"
						onClick={this.handleClose}
						type="reset"
						value="cancel"
					>
						{Liferay.Language.get('cancel')}
					</Button>

					<Button type="submit" value="save">
						{Liferay.Language.get('submit')}
					</Button>
				</div>
			</form>
		);

		const modalFooterConfirmation = (
			<div className="btn-row">
				<Button
					display="default"
					onClick={this.handleClose}
					type="reset"
					value="cancel"
				>
					{Liferay.Language.get('close')}
				</Button>
			</div>
		);

		const successConfirmation = (
			<div className="modal-body-announcement">
				<div className="modal-body-icon check-circle-full">
					<svg className="lexicon-icon lexicon-icon-check-circle-full">
						<use xlinkHref="#check-circle-full" />
					</svg>
				</div>

				<div className="modal-body-title">
					{Liferay.Language.get(
						'success-your-request-submitted-succesfully'
					)}
				</div>

				<div className="last-text modal-body-text">
					{sub(
						Liferay.Language.get(
							'you-will-receive-an-email-invitation-from-x-to-collaborate-on-the-liferaydxp-repository-accept-the-invitation-to-gain-access'
						),
						[
							<a
								aria-label={Liferay.Language.get('github')}
								className="component-title link-primary"
								href="https://github.com/"
							>
								{Liferay.Language.get('github')}
							</a>
						],
						false
					)}
				</div>
			</div>
		);

		const waitConfirmation = (
			<div className="modal-body-announcement">
				<div className="modal-body-icon warning-full">
					<svg className="lexicon-icon lexicon-icon-warning-full">
						<use xlinkHref="#warning-full" />
					</svg>
				</div>

				<div className="modal-body-title">
					{Liferay.Language.get(
						"oh-no-you-can't-be-granted-access-yet"
					)}
				</div>

				<div className="modal-body-text">
					{sub(
						Liferay.Language.get('you-will-gain-access-within-x'),
						[
							<span className="semi-bold">
								{LENGTH_OF_DAYS} {Liferay.Language.get('days')}
							</span>
						],
						false
					)}
				</div>

				<div className="modal-body-text">
					{Liferay.Language.get(
						'we-are-sorry-for-this-inconvenience-github-limits-the-amount-of-repository-invitations-that-can-be-sent-daily'
					)}
				</div>

				<div className="last-text modal-body-text">
					{sub(
						Liferay.Language.get(
							'once-you-can-be-granted-access-you-will-receive-email-invitation-from-x-to-collaborate-on-the-liferaydxp-repository'
						),
						[
							<a
								aria-label={Liferay.Language.get('github')}
								className="component-title link-primary"
								href="https://github.com/"
							>
								{Liferay.Language.get('github')}
							</a>
						],
						false
					)}
				</div>
			</div>
		);

		return (
			<Modal
				footer={confirmation ? modalFooterConfirmation : modalFooter}
				header={Liferay.Language.get(
					"give-a-team-member-access-to-liferay's-source-code"
				)}
				onClose={this.handleClose}
				show={show}
			>
				{confirmation ? (
					confirmation === 'success' ? (
						successConfirmation
					) : (
						waitConfirmation
					)
				) : (
					<>
						{fieldsToUpdate.length > 0 && (
							<Alert type="danger">
								<span className="urgent">
									{Liferay.Language.get('error-colon')}
								</span>{' '}
								{Liferay.Language.get(
									'please-correct-the-following-fields'
								)}{' '}
								{fieldsToUpdate.join(', ')}
							</Alert>
						)}
						<form>
							<SourceCodeAccessField
								errorMessage={Liferay.Language.get(
									'first-and-last-name-are-both-required'
								)}
								hasError={fullName.error}
								id="fullNameInput"
								inputValue={fullName.value}
								label={fieldNames[0]}
								required={true}
								subtext={Liferay.Language.get(
									'first-and-last-name'
								)}
								updateInputValue={this.handleFullNameChange}
							/>

							<SourceCodeAccessField
								errorMessage={Liferay.Language.get(
									'invalid-format'
								)}
								hasError={emailAddress.error}
								id="emailAddressInput"
								inputValue={emailAddress.value}
								label={fieldNames[1]}
								required={true}
								updateInputValue={this.handleEmailChange}
							/>

							<SourceCodeAccessField
								errorMessage={Liferay.Language.get(
									'incorrect-username'
								)}
								hasError={gitHubUserName.error}
								id="gitHubUserNameInput"
								inputValue={gitHubUserName.value}
								label={fieldNames[2]}
								required={true}
								updateInputValue={this.handleGitHubChange}
							/>
						</form>
					</>
				)}
			</Modal>
		);
	}
}

SourceCodeAccessField.propTypes = {
	hasError: PropTypes.bool.isRequired,
	errorMessage: PropTypes.string.isRequired,
	id: PropTypes.string.isRequired,
	label: PropTypes.string.isRequired,
	required: PropTypes.bool,
	inputValue: PropTypes.string.isRequired,
	updateInputValue: PropTypes.func.isRequired,
	subtext: PropTypes.string
};

function SourceCodeAccessField({
	hasError,
	errorMessage,
	id,
	label,
	required,
	inputValue,
	updateInputValue,
	subtext
}) {
	const handleChange = event => {
		updateInputValue(event.target.value);
	};

	return (
		<div className={`${hasError ? 'form-group has-error' : 'form-group'}`}>
			<label className="control-label" htmlFor={id}>
				{label}

				{required && (
					<svg className="lexicon-icon lexicon-icon-asterisk">
						<use xlinkHref="#asterisk" />
					</svg>
				)}
			</label>

			<input
				className="form-control"
				id={id}
				onChange={handleChange}
				type="text"
				value={inputValue}
			/>

			{subtext && <div className="form-feedback-item">{subtext}</div>}

			{hasError && (
				<div className="form-feedback-item-error">
					<span className="form-feedback-indicator inline-item-before">
						<svg className="lexicon-icon lexicon-icon-exclamation-full">
							<use xlinkHref="#exclamation-full" />
						</svg>
					</span>

					{inputValue
						? errorMessage
						: Liferay.Language.get('this-field-is-required')}
				</div>
			)}
		</div>
	);
}
