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

const isValid = field => {
	return field.value.match(ERROR_VALIDATION[field.name]);
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
	const [fieldErrors, setFieldErrors] = useState([]);
	const [fields, setFields] = useState({
		emailAddress: {
			error: false,
			errorMessage: Liferay.Language.get('invalid-format'),
			label: Liferay.Language.get('email-address'),
			name: 'emailAddress',
			value: ''
		},
		fullName: {
			error: false,
			errorMessage: Liferay.Language.get(
				'first-and-last-name-are-both-required'
			),
			label: Liferay.Language.get('name'),
			name: 'fullName',
			subtext: Liferay.Language.get('first-and-last-name'),
			value: ''
		},
		gitHubUserName: {
			error: false,
			errorMessage: Liferay.Language.get('incorrect-username'),
			label: Liferay.Language.get('github-username'),
			name: 'gitHubUserName',
			value: ''
		}
	});

	function handleClose() {
		setConfirmation('');
		setCustomError('');
		setDataLoading(false);
		setFieldErrors([]);
		setFields({
			emailAddress: {...fields.emailAddress, value: '', error: false},
			fullName: {...fields.fullName, value: '', error: false},
			gitHubUserName: {...fields.gitHubUserName, value: '', error: false}
		});

		onClose();
	}

	function validateAllFields() {
		return Object.values(fields).every(field => isValid(field));
	}

	function handleUpdate(event) {
		event.preventDefault();

		setDataLoading(true);

		if (!validateAllFields()) {
			const emailAddressError = !isValid(fields.emailAddress);
			const fullNameError = !isValid(fields.fullName);
			const gitHubUserNameError = !isValid(fields.gitHubUserName);

			setCustomError('');
			setDataLoading(false);

			setFields({
				emailAddress: {
					...fields.emailAddress,
					error: emailAddressError
				},
				fullName: {
					...fields.fullName,
					error: fullNameError
				},
				gitHubUserName: {
					...fields.gitHubUserName,
					error: gitHubUserNameError
				}
			});

			setFieldErrors([
				fullNameError ? fields.fullName.label : '',
				emailAddressError ? fields.emailAddress.label : '',
				gitHubUserNameError ? fields.gitHubUserName.label : ''
			]);
		} else {
			postData(
				addCollaboratorURL,
				window.AccountDetailsConstants.namespace,
				{
					fullName: fields.fullName.value,
					emailAddress: fields.emailAddress.value,
					gitHubUserName: fields.gitHubUserName.value
				},
				'formData'
			)
				.then(({data}) => {
					switch (data.message) {
						case 'success':
							addCollaboratorToMap(
								CollabRecord({
									createDate: data.createDate,
									deleteURL: data.deleteCollaboratorURL,
									emailAddress: fields.emailAddress.value,
									fullName: fields.fullName.value,
									gitHubUserName: fields.gitHubUserName.value,
									id: data.collaboratorId
								})
							);

							setConfirmation('success');
							break;
						case 'pending-invitation-limit':
							setConfirmation('delayed');
							break;
						case 'pending-project-status':
							setCustomError(
								Liferay.Language.get(
									'this-request-is-pending-project-status'
								)
							);
							break;
						case 'duplicate-collaborator':
							setCustomError(
								Liferay.Language.get(
									'please-provide-a-unique-github-username'
								)
							);
							break;
						default:
							setCustomError(
								Liferay.Language.get(
									'could-not-submit-please-try-again'
								)
							);
					}

					setDataLoading(false);

					setFields({
						emailAddress: {
							...fields.emailAddress,
							error: false
						},
						fullName: {
							...fields.fullName,
							error: false
						},
						gitHubUserName: {
							...fields.gitHubUserName,
							error: false
						}
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

					setFields({
						emailAddress: {
							...fields.emailAddress,
							error: false
						},
						fullName: {
							...fields.fullName,
							error: false
						},
						gitHubUserName: {
							...fields.gitHubUserName,
							error: false
						}
					});

					setFieldErrors([]);
				});
		}
	}

	const fieldsToUpdate = fieldErrors.filter(item => item);

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
						{['fullName', 'emailAddress', 'gitHubUserName'].map(
							item => (
								<SourceCodeAccessField
									field={fields[`${item}`]}
									key={item}
									updateInputValue={value =>
										setFields({
											...fields,
											[`${item}`]: {
												...fields[`${item}`],
												error: false,
												value: value
											}
										})
									}
								/>
							)
						)}

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
	field: PropTypes.shape({
		error: PropTypes.bool.isRequired,
		errorMessage: PropTypes.string.isRequired,
		label: PropTypes.string.isRequired,
		name: PropTypes.string.isRequired,
		notRequired: PropTypes.bool,
		subtext: PropTypes.string,
		value: PropTypes.string.isRequired
	}),
	updateInputValue: PropTypes.func.isRequired
};

function SourceCodeAccessField({field, updateInputValue}) {
	const NAMESPACE = window.AccountDetailsConstants.namespace;

	const handleChange = event => {
		updateInputValue(event.target.value);
	};

	return (
		<div
			className={`${field.error ? 'form-group has-error' : 'form-group'}`}
		>
			<label className="control-label" htmlFor={`${field.name}Input`}>
				{field.label}

				{!field.notRequired && (
					<svg className="lexicon-icon lexicon-icon-asterisk">
						<use xlinkHref="#asterisk" />
					</svg>
				)}
			</label>

			<input
				className="form-control"
				id={`${field.name}Input`}
				name={`${NAMESPACE}${field.name}`}
				onChange={handleChange}
				type="text"
				value={field.value}
			/>

			{field.subtext && (
				<div className="form-feedback-item">{field.subtext}</div>
			)}

			{field.error && (
				<div className="form-feedback-item-error">
					<span className="form-feedback-indicator inline-item-before">
						<svg className="lexicon-icon lexicon-icon-exclamation-full">
							<use xlinkHref="#exclamation-full" />
						</svg>
					</span>

					{field.value
						? field.errorMessage
						: Liferay.Language.get('this-field-is-required')}
				</div>
			)}
		</div>
	);
}
