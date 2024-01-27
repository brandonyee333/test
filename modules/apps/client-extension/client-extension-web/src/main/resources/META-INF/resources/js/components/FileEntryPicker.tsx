/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayAlert, {DisplayType} from '@clayui/alert';
import ClayButton, {ClayButtonWithIcon} from '@clayui/button';
import {Text} from '@clayui/core';
import {ClayInput} from '@clayui/form';
import ClayLoadingIndicator from '@clayui/loading-indicator';
import PropTypes from 'prop-types';
import React, {useEffect, useRef, useState} from 'react';

const MALFORMED_JSON = '{]/';

type TFeedback = {displayType: DisplayType; message: string};

const EMPTY_FEEDBACK: TFeedback = {
	displayType: 'info',
	message: '',
};

const FileEntryPicker = ({
	companyId,
	externalReferenceCode,
	frontendTokenDefinition,
	frontendTokenDefinitionFileName,
	namespace,
}) => {
	const [feedback, setFeedback] = useState(EMPTY_FEEDBACK);
	const [fileName, setFileName] = useState(frontendTokenDefinitionFileName);
	const [alertRole, setAlertRole] = useState<'alert' | null>(null);
	const [isValidatingJSON, setIsValidatingJSON] = useState(false);

	const frontendTokenDefinitionRef = useRef<HTMLTextAreaElement>();
	const inputFileRef = useRef<HTMLInputElement>();
	const selectFileButtonRef = useRef<HTMLButtonElement>();

	const setFrontendTokenDefinitionRefValue = (value: string) => {
		if (frontendTokenDefinitionRef.current) {
			frontendTokenDefinitionRef.current.value = value;
		}
	};

	const clearSelection = () => {
		setFileName('');

		setFeedback(EMPTY_FEEDBACK);

		setFrontendTokenDefinitionRefValue('');

		selectFileButtonRef.current?.focus();
	};

	const getButtonSideLabel = (
		fileName: string,
		isValidatingJSON: boolean
	) => {
		if (isValidatingJSON) {
			return Liferay.Language.get('validating-json');
		}

		if (fileName) {
			return fileName;
		}

		return Liferay.Language.get('no-json-selected');
	};

	const onInputChange = async (event: any) => {
		const target = event.target as HTMLInputElement;

		const filePath = target.value;

		const fileName = filePath.replace(/^.*[\\]/, '');

		setFileName(fileName);

		if (!fileName.endsWith('.json')) {
			setFeedback({
				displayType: 'danger',
				message: Liferay.Language.get(
					'the-format-is-not-valid-please-upload-a-valid-frontend-token-definition-json-file'
				),
			});

			setFrontendTokenDefinitionRefValue(MALFORMED_JSON);

			return;
		}

		setIsValidatingJSON(true);

		if (!alertRole) {
			setAlertRole('alert');
		}

		setFeedback({
			displayType: 'info',
			message: Liferay.Language.get(
				'the-frontend-token-definition-json-file-is-being-uploaded-and-validated'
			),
		});

		readInputFile(fileName, target);
	};

	function readInputFile(fileName: string, input: HTMLInputElement) {
		if (input.files === null) {
			return;
		}

		let file = input.files[0];

		const fileReader = new FileReader();

		fileReader.onload = async function (event) {
			let frontendTokenDefinitionString = event.target?.result as string;

			if (frontendTokenDefinitionString === '') {
				file = new File(['{}'], fileName, {
					type: 'application/json',
				});
				frontendTokenDefinitionString = '{}';
			}

			if (frontendTokenDefinitionRef.current) {
				frontendTokenDefinitionRef.current.value = frontendTokenDefinitionString;
			}

			await handleUpload(file);

			setIsValidatingJSON(false);
		};

		fileReader.readAsText(file);
	}

	const handlePost = async (body: any, endpoint: string) => {
		try {
			const response = await Liferay.Util.fetch(
				'/o/com-liferay-frontend-token-definition-impl' + endpoint,
				{
					body,
					method: 'POST',
				}
			);

			const data = await response.json();

			let displayType: DisplayType = 'success';

			if (!response.ok) {
				displayType = 'danger';

				setFrontendTokenDefinitionRefValue(MALFORMED_JSON);
			}

			setFeedback({
				displayType,
				message: data.message,
			});
		}
		catch (error) {
			clearSelection();

			setFeedback({
				displayType: 'danger',
				message: Liferay.Language.get(
					'an-error-has-occurred-while-connecting-to-our-servers'
				),
			});
		}
	};

	async function handleUpload(
		file: File,
		focusSelectFileButton: boolean = true
	) {
		const formData = new FormData();

		formData.append('file', file);

		await handlePost(formData, '/validate-file');

		if (focusSelectFileButton) {
			selectFileButtonRef.current?.focus();
		}
	}

	const getClientExtensionFrontendTokenDefinitionInfo = () => {
		handlePost(
			Liferay.Util.objectToFormData({
				companyId,
				externalReferenceCode,
			}),
			'/tokens-info'
		);
	};

	const updateFeedbackAfterSubmissionError = (
		frontendTokenDefinition: string
	) => {
		handleUpload(
			new File([frontendTokenDefinition], fileName, {
				type: 'application/json',
			}),
			false
		);
	};

	useEffect(() => {
		if (!frontendTokenDefinitionRef.current) {
			return;
		}

		frontendTokenDefinitionRef.current.value = frontendTokenDefinition;

		if (frontendTokenDefinition && externalReferenceCode) {
			getClientExtensionFrontendTokenDefinitionInfo();
		}
		else if (frontendTokenDefinition) {
			updateFeedbackAfterSubmissionError(frontendTokenDefinition);
		}
		else {
			clearSelection();
		}

		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, []);

	const buttonTitle = !fileName
		? Liferay.Language.get('select-json')
		: Liferay.Language.get('replace-json');

	const inputId = `${namespace}file`;

	return (
		<>
			<label
				aria-describedby={
					namespace + 'frontendTokenDefinitiondescription'
				}
				className="d-block"
				htmlFor={inputId}
				tabIndex={0}
			>
				{Liferay.Language.get('frontend-token-definition-json-upload')}
			</label>

			<Text
				as="p"
				color="secondary"
				id={namespace + 'frontendTokenDefinitiondescription'}
				size={3}
			>
				{Liferay.Language.get('frontend-token-definition-is-a-json')}{' '}
				<a href="https://learn.liferay.com/w/dxp/site-building/site-appearance/style-books/developer-guide/style-book-token-definitions">
					{Liferay.Language.get(
						'learn-more-about-frontend-token-definition'
					)}
				</a>
			</Text>

			<ClayInput
				accept=".json"
				className="d-none"
				id={inputId}
				name={inputId}
				onChange={onInputChange}
				ref={inputFileRef}
				type="file"
			/>

			<ClayInput
				className="d-none"
				id={`${namespace}frontendTokenDefinition`}
				name={`${namespace}frontendTokenDefinition`}
				ref={frontendTokenDefinitionRef}
				type="textarea"
			/>

			<div className="my-2">
				<ClayButton
					disabled={isValidatingJSON}
					displayType="secondary"
					onClick={() => inputFileRef.current?.click()}
					ref={selectFileButtonRef}
					title={buttonTitle}
				>
					{buttonTitle}
				</ClayButton>

				<ClayInput
					className="d-none"
					id={`${namespace}frontendTokenDefinitionFileName`}
					name={`${namespace}frontendTokenDefinitionFileName`}
					type="text"
					value={fileName}
				/>

				<div className="inline-item">
					<small className="inline-item inline-item-after">
						<strong>
							{getButtonSideLabel(fileName, isValidatingJSON)}
						</strong>
					</small>

					{fileName && (
						<ClayButtonWithIcon
							borderless
							displayType="secondary"
							monospaced
							onClick={clearSelection}
							symbol="times-circle-full"
							title={Liferay.Language.get(
								'remove-file-from-selection'
							)}
						/>
					)}

					{isValidatingJSON && (
						<ClayLoadingIndicator
							className="ml-2"
							displayType="secondary"
							size="sm"
						/>
					)}
				</div>
			</div>

			<ClayAlert
				displayType={feedback.displayType}
				role={alertRole}
				style={{display: feedback.message ? 'block' : 'none'}}
				title={feedback.message}
				variant="feedback"
			/>
		</>
	);
};

FileEntryPicker.propTypes = {
	companyId: PropTypes.string.isRequired,
	externalReferenceCode: PropTypes.number.isRequired,
	frontendTokenDefinition: PropTypes.string.isRequired,
	frontendTokenDefinitionFileName: PropTypes.string.isRequired,
	namespace: PropTypes.string.isRequired,
};

export default FileEntryPicker;
