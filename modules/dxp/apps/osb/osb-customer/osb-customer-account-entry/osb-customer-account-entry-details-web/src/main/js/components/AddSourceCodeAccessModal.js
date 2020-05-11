import React, {useState} from 'react';
import PropTypes from 'prop-types';

import Alert from './Alert';
import Button from './Button';
import Modal from './Modal';

import {postData} from '../helpers/api';
import {langSub} from '../helpers/language';
import {CollabRecord} from './SourceCodeAccess';

const ERROR_VALIDATION = {
	emailAddress: /^[\w-\.]+@([\w-]+\.)+[\w-]{2,6}$/i,
	fullName: /^[a-z,.'-]+\s[a-z\s,.'-]+$/i,
	gitHubUserName: /^[a-z\d](?:[a-z\d]|-(?=[a-z\d])){0,38}$/i
};

const LENGTH_OF_DAYS = '3';

const isValid = (value, field) => {
	return value.match(ERROR_VALIDATION[field]);
};

AddSourceCodeAccessModal.propTypes = {
	addCollaboratorToMap: PropTypes.func.isRequired,
	addCollaboratorURL: PropTypes.string.isRequired,
	onClose: PropTypes.func.isRequired,
	show: PropTypes.bool.isRequired
};

export default function AddSourceCodeAccessModal({
	addCollaboratorToMap,
	addCollaboratorURL,
	onClose,
	show
}) {
	const [confirmation, setConfirmation] = useState('');
	const [customError, setCustomError] = useState('');
	const [dataLoading, setDataLoading] = useState(false);
	const [emailAddress, setEmailAddress] = useState({value: '', error: false});
	const [fieldErrors, setFieldErrors] = useState([]);
	const [fullName, setFullName] = useState({value: '', error: false});
	const [gitHubUserName, setGitHubUserName] = useState({
		value: '',
		error: false
	});

	function handleClose() {
		setConfirmation('');
		setCustomError('');
		setDataLoading(false);
		setEmailAddress({value: '', error: false});
		setFieldErrors([]);
		setFullName({value: '', error: false});
		setGitHubUserName({value: '', error: false});

		onClose();
	}

	function validateAllFields() {
		return (
			isValid(emailAddress.value, 'emailAddress') &&
			isValid(fullName.value, 'fullName') &&
			isValid(gitHubUserName.value, 'gitHubUserName')
		);
	}

	function handleUpdate(event) {
		event.preventDefault();

		setDataLoading(true);

		if (!validateAllFields()) {
			const emailAddressError = !isValid(
				emailAddress.value,
				'emailAddress'
			);
			const fullNameError = !isValid(fullName.value, 'fullName');
			const gitHubUserNameError = !isValid(
				gitHubUserName.value,
				'gitHubUserName'
			);

			setCustomError('');
			setDataLoading(false);
			setEmailAddress({
				value: emailAddress.value,
				error: emailAddressError
			});
			setFullName({value: fullName.value, error: fullNameError});
			setGitHubUserName({
				value: gitHubUserName.value,
				error: gitHubUserNameError
			});
			setFieldErrors([
				fullNameError,
				emailAddressError,
				gitHubUserNameError
			]);
		} else {
			postData(
				addCollaboratorURL,
				window.AccountDetailsConstants.namespace,
				{
					fullName: fullName.value,
					emailAddress: emailAddress.value,
					gitHubUserName: gitHubUserName.value
				},
				'formData'
			)
				.then(response => response.data)
				.then(data => {
					if (data.message === 'success') {
						addCollaboratorToMap(
							CollabRecord({
								collaboratorId: data.collaboratorId,
								createDate: data.createDate,
								deleteCollaboratorURL:
									data.deleteCollaboratorURL,
								emailAddress: emailAddress.value,
								fullName: fullName.value,
								gitHubUserName: gitHubUserName.value
							})
						);

						setConfirmation('success');
					} else if (data.message === 'pending-invitation-limit') {
						setConfirmation('delayed');
					} else if (data.message === 'pending-project-status') {
						setCustomError(
							Liferay.Language.get(
								'this-request-is-pending-project-status'
							)
						);
					} else if (data.message === 'duplicate-collaborator') {
						setCustomError(
							Liferay.Language.get(
								'please-provide-a-unique-github-username'
							)
						);
					} else {
						setCustomError(
							Liferay.Language.get(
								'could-not-submit-please-try-again'
							)
						);
					}

					setDataLoading(false);
					setEmailAddress({
						value: emailAddress.value,
						error: false
					});
					setFullName({value: fullName.value, error: false});
					setGitHubUserName({
						value: gitHubUserName.value,
						error: false
					});
					setFieldErrors([]);
				})
				.catch(error => {
					setCustomError(
						Liferay.Language.get(
							'could-not-submit-please-try-again'
						)
					);
					setDataLoading(false);
					setEmailAddress({
						value: emailAddress.value,
						error: false
					});
					setFullName({value: fullName.value, error: false});
					setGitHubUserName({
						value: gitHubUserName.value,
						error: false
					});
					setFieldErrors([]);
				});
		}
	}

	const fieldNames = [
		Liferay.Language.get('name'),
		Liferay.Language.get('email-address'),
		Liferay.Language.get('github-username')
	];

	const errorMessages = [
		Liferay.Language.get('first-and-last-name-are-both-required'),
		Liferay.Language.get('invalid-format'),
		Liferay.Language.get('incorrect-username')
	];

	const fieldsToUpdate = fieldErrors
		.map((item, index) => (item ? fieldNames[index] : ''))
		.filter(item => item);

	const successConfirmation = (
		<div className="modal-body-announcement">
			<div className="modal-body-icon check-circle-full">
				<svg className="lexicon-icon lexicon-icon-check-circle-full">
					<use xlinkHref="#check-circle-full" />
				</svg>
			</div>

			<div className="modal-body-title">
				{Liferay.Language.get(
					'success-your-request-submitted-successfully'
				)}
			</div>

			<div className="last-text modal-body-text">
				{langSub(
					Liferay.Language.get(
						'you-will-receive-an-email-invitation-from-x-to-collaborate-on-the-liferaydxp-repository-accept-the-invitation-to-gain-access'
					),
					[
						<a
							aria-label={Liferay.Language.get('github-link')}
							className="btn-link component-title"
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

	const delayedConfirmation = (
		<div className="modal-body-announcement">
			<div className="modal-body-icon warning-full">
				<svg className="lexicon-icon lexicon-icon-warning-full">
					<use xlinkHref="#warning-full" />
				</svg>
			</div>

			<div className="modal-body-title">
				{Liferay.Language.get("oh-no-you-can't-be-granted-access-yet")}
			</div>

			<div className="modal-body-text">
				{langSub(
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
				{langSub(
					Liferay.Language.get(
						'once-you-can-be-granted-access-you-will-receive-email-invitation-from-x-to-collaborate-on-the-liferaydxp-repository'
					),
					[
						<a
							aria-label={Liferay.Language.get('github-link')}
							className="btn-link component-title"
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

	const modalConfirmation = (
		<div className="btn-row">
			<Button
				display="default"
				onClick={handleClose}
				type="reset"
				value="close"
			>
				{Liferay.Language.get('close')}
			</Button>
		</div>
	);

	return (
		<Modal
			header={Liferay.Language.get(
				"give-a-team-member-access-to-liferay's-source-code"
			)}
			onClose={handleClose}
			show={show}
		>
			{dataLoading ? (
				<span aria-hidden="true" className="loading-animation"></span>
			) : confirmation ? (
				confirmation === 'success' ? (
					<>
						{successConfirmation} {modalConfirmation}
					</>
				) : (
					<>
						{delayedConfirmation} {modalConfirmation}
					</>
				)
			) : (
				<>
					{customError && <Alert type="danger">{customError}</Alert>}

					{fieldsToUpdate.length > 0 && (
						<Alert type="danger">
							<span className="semi-bold">
								{Liferay.Language.get('error-colon')}
							</span>{' '}
							{Liferay.Language.get(
								'please-correct-the-following-fields'
							)}{' '}
							{fieldsToUpdate.join(', ')}
						</Alert>
					)}

					<form
						action={addCollaboratorURL}
						onSubmit={handleUpdate}
						method="post"
					>
						<SourceCodeAccessField
							errorMessage={errorMessages[0]}
							field={fullName}
							label={fieldNames[0]}
							name="fullName"
							required={true}
							subtext={Liferay.Language.get(
								'first-and-last-name'
							)}
							updateInputValue={value =>
								setFullName({value: value, error: false})
							}
						/>

						<SourceCodeAccessField
							errorMessage={errorMessages[1]}
							field={emailAddress}
							label={fieldNames[1]}
							name="emailAddress"
							required={true}
							updateInputValue={value =>
								setEmailAddress({value: value, error: false})
							}
						/>

						<SourceCodeAccessField
							errorMessage={errorMessages[2]}
							field={gitHubUserName}
							label={fieldNames[2]}
							name="gitHubUserName"
							required={true}
							updateInputValue={value =>
								setGitHubUserName({value: value, error: false})
							}
						/>

						<div className="btn-row">
							<Button
								display="default"
								onClick={handleClose}
								type="reset"
								value="cancel"
							>
								{Liferay.Language.get('cancel')}
							</Button>

							<Button type="submit" value="submit">
								{Liferay.Language.get('submit')}
							</Button>
						</div>
					</form>
				</>
			)}
		</Modal>
	);
}

SourceCodeAccessField.propTypes = {
	errorMessage: PropTypes.string.isRequired,
	field: PropTypes.shape({
		value: PropTypes.string.isRequired,
		error: PropTypes.bool.isRequired
	}),
	label: PropTypes.string.isRequired,
	name: PropTypes.string.isRequired,
	required: PropTypes.bool,
	updateInputValue: PropTypes.func.isRequired,
	subtext: PropTypes.string
};

function SourceCodeAccessField({
	errorMessage,
	field,
	label,
	name,
	required,
	updateInputValue,
	subtext
}) {
	const NAMESPACE = window.AccountDetailsConstants.namespace;

	const handleChange = event => {
		updateInputValue(event.target.value);
	};

	return (
		<div
			className={`${field.error ? 'form-group has-error' : 'form-group'}`}
		>
			<label className="control-label" htmlFor={`${name}Input`}>
				{label}

				{required && (
					<svg className="lexicon-icon lexicon-icon-asterisk">
						<use xlinkHref="#asterisk" />
					</svg>
				)}
			</label>

			<input
				className="form-control"
				id={`${name}Input`}
				name={`${NAMESPACE}${name}`}
				onChange={handleChange}
				type="text"
				value={field.value}
			/>

			{subtext && <div className="form-feedback-item">{subtext}</div>}

			{field.error && (
				<div className="form-feedback-item-error">
					<span className="form-feedback-indicator inline-item-before">
						<svg className="lexicon-icon lexicon-icon-exclamation-full">
							<use xlinkHref="#exclamation-full" />
						</svg>
					</span>

					{field.value
						? errorMessage
						: Liferay.Language.get('this-field-is-required')}
				</div>
			)}
		</div>
	);
}
