import {bindAll, isArray, isEmpty} from 'lodash';
import JSXComponent, {Config} from 'metal-jsx';
import {List} from 'immutable';
import Router from 'metal-router';
import sub from 'string-sub';

import AddressGoogleMap from './AddressGoogleMap';
import BottomBar from './BottomBar';
import ButtonModal from './ButtonModal';
import Button from './Button';
import {compareObjectsData, convertMapToObject, deepCompareIsEqual} from '../lib/util';
import DependentSelectInput from './DependentSelectInput';
import DependentInput from './DependentInput';
import DynamicInputGenerator from './DynamicInputGenerator';
import FileUploader from './FileUploader';
import FileViewer from './FileViewer';
import Input from './Input';
import MultiSelectInput from './MultiSelectInput';
import RichTextEditor from './RichTextEditor';
import SelectInput from './SelectInput';
import TextAreaInput from './TextAreaInput';
import Tooltip from './Tooltip';

import validate from '../lib/validation';

class Form extends JSXComponent {
	checkConditionalValidation(inputId) {
		const {hiddenInputs = []} = this.state;

		return hiddenInputs.includes(inputId);
	}

	created() {
		bindAll(
			this,
			'handleBlur',
			'handleConditionalInput',
			'handleMultiInputChange',
			'handleNavigationReport',
			'handleNavigationTranslate',
			'handleResetFormChanges',
			'handleSubmit',
			'handleUpdateValue'
		);

		this.state.changedFields = [];
		this.state.hiddenInputs = [];
		this.state.originalFormData = {};
	}

	handleBlur(value, inputId) {
		if (inputId) {
			const currentInputConfig = this.props.fieldConfig[inputId];

			const {validations} = currentInputConfig;

			this.handleValidate(inputId, validations, value);
		}
	}

	handleConditionalInput(inputId, hiddenInput, controlledInputs = []) {
		let {hiddenInputs = []} = this.state;
		const {validationErrors} = this.state;

		if (hiddenInput) {
			hiddenInputs.push(inputId);

			controlledInputs.forEach(
				controlledInputId => {
					hiddenInputs = hiddenInputs.filter(item => item !== controlledInputId);

					delete validationErrors[inputId];
				}
			);
		}
		else {
			hiddenInputs = hiddenInputs.filter(item => item !== inputId);

			delete validationErrors[inputId];

			controlledInputs.forEach(
				controlledInputId => {
					hiddenInputs.push(controlledInputId);
				}
			);
		}

		hiddenInputs = [...(new Set(hiddenInputs))];

		this.setState({hiddenInputs, validationErrors});
	}

	handleMultiInputChange(inputs, inputId, key) {
		const formattedValue = [];

		inputs.forEach(
			entry => {
				const index = key ? 0 : entry.length - 1;

				formattedValue.push(entry[index]);
			}
		);

		this.handleUpdateValue(formattedValue, inputId);
	}

	handleNavigationReport() {
		Router.router().navigate(this.props.reportHref);
	}

	handleNavigationTranslate() {
		Router.router().navigate(this.props.translateHref);
	}

	handleResetFormChanges() {
		const {originalFormData} = this.state;

		const unfrozenFormData = JSON.parse(JSON.stringify(originalFormData));

		this.props.updateFormData(unfrozenFormData);

		this.setState({formDataReset: true, validationErrors: {}});
	}

	handleSubmit() {
		const {props, state} = this;

		const {disabled, fieldConfig, modelKey, redirect, submitMethod, watsonChildId = 0, watsonIncidentId = 0} = props;

		const {formData} = state;

		if (!disabled && formData) {
			props.formConfig.forEach(
				inputId => {
					const value = formData[inputId];

					const {[inputId]: {validations}} = fieldConfig;

					this.handleValidate(inputId, validations, value);
				}
			);

			if (Object.keys(state.validationErrors).length < 1) {
				const postData = formData;

				if (watsonIncidentId > 0) {
					postData.watsonIncidentId = watsonIncidentId;
				}
				if (watsonChildId > 0) {
					postData.watsonChildId = watsonChildId;
				}
				if (modelKey) {
					postData.key = modelKey;
				}

				submitMethod(postData);

				this.setState({changedFields: [], submitted: true});

				if (redirect) {
					redirect();
				}
			}
		}
	}

	handleUpdateValue(value = '', inputId) {
		this.handleBlur(value, inputId);

		const {formData = {}} = this.state;

		formData[inputId] = value;

		this.props.updateFormData(formData);

		this.state.submitted = false;

		Liferay.Watson.debouncedSessionExtend();
	}

	handleValidate(inputId, validations, value) {
		if (validations) {
			const {validationErrors} = this.state;

			const skipValidation = this.checkConditionalValidation(inputId);

			let errors = [];

			if (!skipValidation) {
				errors = validate(validations, value);
			}

			if (errors.length > 0) {
				this.setState(
					{
						validationErrors: Object.assign(
							{},
							validationErrors,
							{
								[inputId]: errors
							}
						)
					}
				);
			}
			else {
				delete validationErrors[inputId];

				this.setState(
					{
						validationErrors
					}
				);
			}
		}
	}

	refreshData() {
		const {
			props: {
				formData,
				loading,
				watsonPrimaryKey
			},
			state: {
				originalFormData
			}
		} = this;

		if (watsonPrimaryKey && watsonPrimaryKey > 0) {
			const reset = compareObjectsData(originalFormData, formData);

			if (!loading && (Object.is(formData, {}) && !deepCompareIsEqual(formData, originalFormData)) || (!loading && reset)) {
				const unfrozenFormData = JSON.parse(JSON.stringify(originalFormData));

				this.props.updateFormData(unfrozenFormData);
			}
		}
	}

	render() {
		const {props, state} = this;

		const {
			action,
			additionalTopBarButtons,
			autoSaved,
			button,
			buttonLabel = Liferay.Language.get('save'),
			cancelMethod,
			deleteMethod,
			disabled = false,
			reportHref,
			requestTranslationMethod,
			response,
			translateHref
		} = props;

		let {message = ''} = props;

		const {
			changedFields: {
				length: changedFieldsLength
			},
			formDataReset = false,
			originalFormData = {},
			submitted,
			validationErrors = {}
		} = state;

		const bottomBarButtons = [];
		const optionalButtons = [];

		if (button) {
			bottomBarButtons.push(button);
		}

		if (cancelMethod) {
			bottomBarButtons.push(
				{
					action: cancelMethod,
					label: Liferay.Language.get('cancel')
				}
			);
		}
		else if (changedFieldsLength > 0 && !submitted) {
			const resetFormDataButtons = [];

			resetFormDataButtons.push(
				{
					label: Liferay.Language.get('revert')
				}
			);

			const modal = {
				body: Liferay.Language.get('you-have-changed-data-on-this-form-are-you-sure')
			};

			optionalButtons.push(
				<ButtonModal action={this.handleResetFormChanges} buttons={resetFormDataButtons} modalData={modal} />
			);
		}

		bottomBarButtons.push(
			{
				label: buttonLabel,
				submitButton: true
			}
		);

		let status = '';

		if (response) {
			const responseData = response.get('data') || new Map();
			const responseMessage = response.get('message');
			const responseStatus = response.get('status');

			if (responseData || responseMessage) {
				const entryId = responseData.get('id');

				if ((entryId && entryId === originalFormData.id) || (action === 'create' || action === 'index')) {
					if (responseStatus === 'failure' || (responseStatus === 'success' && responseMessage && (!changedFieldsLength || submitted))) {
						message = responseMessage;
						status = responseStatus;
					}
				}
			}
		}

		const formErrorsCount = Object.keys(validationErrors).length;

		if (formErrorsCount > 0) {
			message = sub(Liferay.Language.get('x-errors-on-form'), formErrorsCount);
			status = 'failure';
		}
		else if ((!message || autoSaved) && originalFormData && Object.keys(originalFormData).length && changedFieldsLength) {
			message = sub(Liferay.Language.get('x-unsaved-changes'), changedFieldsLength);
		}
		else if (!message && originalFormData && formDataReset && !changedFieldsLength) {
			message = Liferay.Language.get('your-changes-have-been-reverted');
		}

		const topBarOptionButtons = [];

		if (additionalTopBarButtons) {
			topBarOptionButtons.push(additionalTopBarButtons);
		}

		if (requestTranslationMethod) {
			const optionButtons = [];

			optionButtons.push(
				{
					label: Liferay.Language.get('request-translation')
				}
			);

			const modal = {
				body: Liferay.Language.get('are-you-sure-that-you-would-like-to-request-a-translation')
			};

			topBarOptionButtons.push(
				<ButtonModal action={requestTranslationMethod} buttons={optionButtons} modalData={modal} />
			);
		}

		if (deleteMethod) {
			const optionButtons = [];

			optionButtons.push(
				{
					label: Liferay.Language.get('delete')
				}
			);

			const modal = {
				body: Liferay.Language.get('are-you-sure-you-want-to-delete-this')
			};

			topBarOptionButtons.push(
				<ButtonModal action={deleteMethod} buttons={optionButtons} modalData={modal} />
			);
		}

		return (
			<div class="form-container">
				<div class="top-bar">
					<div class="buttons">
						{translateHref &&
							<Button
								label={Liferay.Language.get('translate')}
								onClick={this.handleNavigationTranslate}
							/>
						}

						{reportHref &&
							<Button
								label={Liferay.Language.get('print')}
								onClick={this.handleNavigationReport}
							/>
						}
					</div>

					{topBarOptionButtons}
				</div>

				{this.renderInputs()}

				<BottomBar
					buttons={bottomBarButtons}
					formIsValid={formErrorsCount < 1 || !disabled}
					handleSubmit={this.handleSubmit}
					message={message}
					messageCssClass={status}
					optionalButtons={optionalButtons}
				/>
			</div>
		);
	}

	renderInputs() {
		const {props, state} = this;

		const inputs = [];

		const {action, disabled: incidentDisabled, fieldConfig, formConfig} = props;

		formConfig.forEach(
			(inputId, index) => {
				const autoFocus = index < 1 && action === 'create';

				const currentInputConfig = fieldConfig[inputId];

				const {defaultValue, htmlType, tooltipLabel, type: currentType, validations} = currentInputConfig;

				let {disabled, label} = currentInputConfig;

				disabled = incidentDisabled ? incidentDisabled : disabled;

				const {formData = {}} = state;

				const value = formData[inputId] || '';

				if (!value && defaultValue && action === 'create') {
					if (htmlType === 'date') {
						this.handleUpdateValue(Date.now(), inputId);
					}
					else {
						this.handleUpdateValue(defaultValue, inputId);
					}
				}

				const {inputTypes: inputTypeConstants} = WatsonConstants.inputConfig;

				if (validations && validations.includes('required')) {
					label += ' *';
				}

				let hiddenInput = state.hiddenInputs.includes(inputId);

				const config = {
					autoFocus,
					disabled,
					inputId,
					onBlur: this.handleBlur,
					onChange: this.handleUpdateValue,
					value
				};

				let inputComponent;

				if (currentType === inputTypeConstants.input) {
					inputComponent = <Input {...config} htmlType={htmlType} />;
				}
				else if (currentType === inputTypeConstants.textareaInput) {
					inputComponent = <TextAreaInput {...config} />;
				}
				else if (currentType === inputTypeConstants.richTextEditor) {
					inputComponent = (
						<RichTextEditor
							{...config}
							loading={props.loading}
						/>
					);
				}
				else if (currentType === inputTypeConstants.selectInput) {
					inputComponent = (
						<SelectInput
							{...config}
							options={currentInputConfig.options}
							sortOptions={currentInputConfig.sortOptions}
						/>
					);
				}
				else if (currentType === inputTypeConstants.dependentKeyedInput) {
					const {controlledInputs, dependentKey, invertHidden = false, parentInputId} = currentInputConfig;

					const parentInputValue = formData[parentInputId];

					hiddenInput = true;

					if (parentInputValue) {
						if ((!invertHidden && !dependentKey.includes(parentInputValue)) || (invertHidden && dependentKey.includes(parentInputValue))) {
							hiddenInput = false;

							if (state.hiddenInputs.includes(inputId)) {
								this.handleConditionalInput(inputId, hiddenInput, controlledInputs);
							}
						}
					}

					if (hiddenInput && !state.hiddenInputs.includes(inputId)) {
						this.handleConditionalInput(inputId, hiddenInput, controlledInputs);
					}

					if (currentInputConfig.inputType === inputTypeConstants.microForm) {
						config.onChange = this.handleMultiInputChange;

						inputComponent = (
							<DynamicInputGenerator
								{...config}
								inputConfig={currentInputConfig}
								label={Liferay.Language.get('add-document')}
								type="microForm"
								watsonIncidentId={props.watsonIncidentId}
								watsonPrimaryKey={props.watsonPrimaryKey}
							/>
						);
					}
					else if (currentInputConfig.inputType === inputTypeConstants.selectInput) {
						inputComponent = (
							<SelectInput
								{...config}
								options={currentInputConfig.options}
								sortOptions={currentInputConfig.sortOptions}
							/>
						);
					}
					else if (currentInputConfig.inputType === inputTypeConstants.dependentSelectInput) {
						const {
							listTypeValue,
							options: defaultOptions,
							parentInputId,
							showDefaultOptions
						} = currentInputConfig;

						const parentInputValue = formData[parentInputId];

						inputComponent = (
							<DependentSelectInput
								{...config}
								defaultOptions={defaultOptions}
								listTypeValue={listTypeValue}
								parentInputValue={parentInputValue}
								showDefaultOptions={showDefaultOptions}
							/>
						);
					}
					else {
						inputComponent = (
							<Input
								{...config}
								htmlType={htmlType}
							/>
						);
					}
				}
				else if (currentType === inputTypeConstants.dependentSelectInput) {
					const {
						listTypeValue,
						options: defaultOptions,
						parentInputId,
						showDefaultOptions
					} = currentInputConfig;

					const parentInputValue = formData[parentInputId];

					inputComponent = (
						<DependentSelectInput
							{...config}
							defaultOptions={defaultOptions}
							listTypeValue={listTypeValue}
							parentInputValue={parentInputValue}
							showDefaultOptions={showDefaultOptions}
						/>
					);
				}
				else if (currentType === inputTypeConstants.dependentInput) {
					const {listTypeValue, parentInputId} = currentInputConfig;

					const parentInputValue = formData[parentInputId];

					inputComponent = (
						<DependentInput
							{...config}
							listTypeValue={listTypeValue}
							parentInputValue={parentInputValue}
						/>
					);
				}
				else if (currentType === inputTypeConstants.multiSelectInput) {
					inputComponent = (
						<MultiSelectInput
							{...config}
							options={currentInputConfig.options}
						/>
					);
				}
				else if (currentType === inputTypeConstants.dynamicRelationshipInputGenerator) {
					if (action !== 'create') {
						config.onChange = this.handleMultiInputChange;

						const {buttonLabel, fancy, tripleOnly} = currentInputConfig;

						const type = fancy ? 'fancy' : 'simple';

						inputComponent = (
							<DynamicInputGenerator
								{...config}
								formData={formData}
								inputConfig={fieldConfig}
								label={buttonLabel}
								model={props.model}
								onClick={props.relateOnClick}
								tripleOnly={tripleOnly}
								type={type}
								watsonIncidentId={props.watsonIncidentId}
								watsonPrimaryKey={props.watsonPrimaryKey}
							/>
						);
					}
				}
				else if (currentType === inputTypeConstants.doubleDependentInput) {
					config.onChange = this.handleMultiInputChange;

					const {buttonLabel} = currentInputConfig;

					inputComponent = (
						<DynamicInputGenerator
							{...config}
							inputConfig={currentInputConfig}
							label={buttonLabel}
							type="generator"
							watsonPrimaryKey={props.watsonPrimaryKey}
						/>
					);
				}
				else if (currentType === inputTypeConstants.dynamicInputGenerator) {
					config.onChange = this.handleMultiInputChange;

					const {buttonLabel} = currentInputConfig;

					inputComponent = (
						<DynamicInputGenerator
							{...config}
							inputConfig={currentInputConfig}
							label={buttonLabel}
							type="generator"
							watsonPrimaryKey={props.watsonPrimaryKey}
						/>
					);
				}
				else if (currentType === inputTypeConstants.googleMap) {
					inputComponent = (
						<AddressGoogleMap
							{...config}
							fieldConfig={fieldConfig}
							formData={formData}
							tooltipLabel={tooltipLabel}
						/>
					);
				}
				else if (currentType === inputTypeConstants.file) {
					if (isEmpty(value)) {
						const {
							enableCropperTool,
							multiple = false,
							parentInputId,
							uploaderLabel
						} = currentInputConfig;

						let {acceptedTypes} = currentInputConfig;

						if (isEmpty(acceptedTypes)) {
							acceptedTypes = '.none';

							const parentInputValue = formData[parentInputId];

							if (parentInputValue) {
								const uploadTypes = WatsonConstants.uploadSettings.acceptedTypes;

								const parentSelected = uploadTypes[parentInputValue];

								if (parentSelected) {
									acceptedTypes = parentSelected.type;
								}
							}
						}

						inputComponent = (
							<FileUploader
								{...config}
								acceptedTypes={acceptedTypes}
								classPK={props.watsonPrimaryKey}
								enableCropperTool={enableCropperTool}
								multiple={multiple}
								uploaderLabel={uploaderLabel}
							/>
						);
					}
					else {
						inputComponent = (
							<FileViewer
								{...config}
							/>
						);
					}
				}
				else if (currentType === inputTypeConstants.inputView) {
					if (action !== 'create') {
						inputComponent = (
							<div class="input-value small">{value}</div>
						);
					}
				}

				let {cssClass = ''} = currentInputConfig;

				const fieldIndex = state.changedFields.indexOf(inputId);

				if (!deepCompareIsEqual(formData[inputId], state.originalFormData[inputId])) {
					cssClass += ' changed';

					if (fieldIndex === -1) {
						state.changedFields.push(inputId);
					}
				}
				else if (fieldIndex > -1) {
					state.changedFields.splice(fieldIndex, 1);
				}

				if (!hiddenInput) {
					const validationMessages = [];

					if (props.errors) {
						let backendError = props.errors.get(inputId);

						if (List.isList(backendError)) {
							backendError = backendError.toArray();
						}

						if (backendError && isArray(backendError)) {
							cssClass += ' error';

							backendError.forEach(
								validationError => {
									validationMessages.push(
										<span class="validation-message">
											{validationError}
										</span>
									);
								}
							);
						}
						else if (backendError && backendError.length > 0) {
							cssClass += ' error';

							validationMessages.push(
								<span class="validation-message">
									{backendError}
								</span>
							);
						}
					}

					const validationErrors = state.validationErrors[inputId];

					if (validationErrors && validationErrors.length > 0) {
						cssClass += ' error';

						validationErrors.forEach(
							validationError => {
								validationMessages.push(
									<span class="validation-message">
										{validationError}
									</span>
								);
							}
						);
					}

					if (inputComponent) {
						if (label || !currentInputConfig.noLabel) {
							let tooltipIcon;

							if (tooltipLabel) {
								tooltipIcon = <span class="tooltip-icon" data-for={inputId} data-tip={tooltipLabel} />;
							}

							inputs.push(
								<div class={`input ${cssClass}`}>
									{label &&
										<span class="input-label">
											{label}

											{tooltipIcon}

											{tooltipIcon &&
												<Tooltip
													id={inputId}
													key={index}
													tooltipLabel={tooltipLabel}
												/>
											}
										</span>
									}

									{inputComponent}

									{validationMessages}
								</div>
							);
						}
						else {
							inputs.push(inputComponent);
						}
					}
				}
			}
		);

		return inputs;
	}

	rendered(firstRender) {
		if (!firstRender) {
			this.refreshData();
		}
	}

	syncAutoSaved(newState) {
		if (newState) {
			this.setState({changedFields: []});
		}
	}

	syncFormData(newState, oldState) {
		if (newState && newState !== oldState) {
			this.setState({formData: newState});
		}
	}

	syncStoreData(newState, oldState) {
		if (newState && newState !== oldState) {
			let originalFormData = convertMapToObject(newState);

			originalFormData = Object.freeze(originalFormData);

			this.setState({changedFields: [], originalFormData});
		}
	}
}

Form.PROPS = {
	action: Config.string().value(''),
	additionalTopBarButtons: Config.any(),
	autoSaved: Config.number(),
	disabled: Config.bool().value(false),
	errors: Config.value(new Map()),
	fieldConfig: Config.object(),
	formConfig: Config.array(),
	formData: Config.object().value({}),
	loading: Config.bool(),
	message: Config.string().value(''),
	model: Config.string(),
	modelKey: Config.any(),
	redirect: Config.func(),
	reportHref: Config.any(),
	requestTranslationMethod: Config.func(),
	response: Config.value(null),
	storeData: Config.object().value({}),
	submitMethod: Config.func(),
	translateHref: Config.any(),
	watsonChildId: Config.any(),
	watsonIncidentId: Config.any(),
	watsonPrimaryKey: Config.any()
};

Form.STATE = {
	changedFields: Config.array().value([]),
	formData: Config.object().value({}),
	formDataReset: Config.bool().value(false),
	hiddenInputs: Config.array().value([]),
	originalFormData: Config.object().value({}),
	submitted: Config.bool().value(false),
	validationErrors: Config.object().value({})
};

export default Form;