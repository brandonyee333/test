import {bindAll, isEmpty} from 'lodash';
import JSXComponent, {Config} from 'metal-jsx';

import BottomBar from './BottomBar';
import FileUploader from './FileUploader';
import FileViewer from './FileViewer';
import Input from './Input';
import SelectInput from './SelectInput';
import TextAreaInput from './TextAreaInput';

import validate from '../lib/validation';

class MicroForm extends JSXComponent {
	created() {
		bindAll(
			this,
			'handleCancel',
			'handleBlur',
			'handleSubmit',
			'handleUpdateValue',
			'handleValidate',
			'renderInputs'
		);

		this.state.microFormData = {};
	}

	handleBlur(value, inputId) {
		if (inputId) {
			const currentInputConfig = this.props.fieldConfig[inputId];

			const {validations} = currentInputConfig;

			this.handleValidate(inputId, validations, value);
		}
	}

	handleCancel() {
		const {cancelMethod} = this.props;

		if (cancelMethod) {
			cancelMethod();
		}
	}

	handleSubmit() {
		const {props, state} = this;

		const {fieldConfig, id, submitMethod, watsonIncidentId} = props;

		const {microFormData} = state;

		if (microFormData) {
			props.formConfig.forEach(
				inputId => {
					const value = microFormData[inputId];

					const {[inputId]: {validations}} = fieldConfig;

					this.handleValidate(inputId, validations, value);
				}
			);

			if (Object.keys(state.validationErrors).length < 1) {
				const postData = microFormData;

				postData.id = id;
				postData.watsonIncidentId = watsonIncidentId;

				submitMethod(postData);

				this.setState({dataSent: true, submitted: true});
			}
		}
	}

	handleUpdateValue(value, inputId) {
		const {microFormData} = this.state;

		this.setState(
			{
				microFormData: Object.assign(
					{},
					microFormData,
					{
						[inputId]: value
					}
				)
			}
		);

		Liferay.Watson.debouncedSessionExtend();
	}

	handleValidate(inputId, validations, value) {
		if (validations) {
			const {validationErrors} = this.state;

			const errors = validate(validations, value);

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

	render() {
		const formErrorsCount = Object.keys(this.state.validationErrors).length;

		let message = '';
		let status = '';

		const bottomBarButtons = [];

		bottomBarButtons.push(
			{
				label: Liferay.Language.get('submit'),
				submitButton: true
			}
		);

		bottomBarButtons.push(
			{
				action: this.handleCancel,
				label: Liferay.Language.get('cancel')
			}
		);

		if (formErrorsCount > 0) {
			message = Liferay.Language.get('error');
			status = 'failure';
		}

		return (
			<div class="micro-form">
				{this.renderInputs()}

				<BottomBar
					buttons={bottomBarButtons}
					formIsValid={formErrorsCount < 1}
					handleSubmit={this.handleSubmit}
					message={message}
					messageCssClass={status}
				/>
			</div>
		);
	}

	renderInputs() {
		const {props, state} = this;

		const inputs = [];

		const {fieldConfig, formConfig} = props;

		formConfig.forEach(
			inputId => {
				const currentInputConfig = fieldConfig[inputId];

				const {disabled, htmlType, type: currentType, validations} = currentInputConfig;

				let {label} = currentInputConfig;

				const {microFormData = {}} = state;

				const value = microFormData[inputId] || '';

				const {inputTypes: inputTypeConstants} = WatsonConstants.inputConfig;

				if (validations && validations.includes('required')) {
					label += ' *';
				}

				const config = {
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
				else if (currentType === inputTypeConstants.selectInput) {
					inputComponent = (
						<SelectInput
							{...config}
							options={currentInputConfig.options}
							sortOptions={currentInputConfig.sortOptions}
						/>
					);
				}
				else if (currentType === inputTypeConstants.file) {
					if (isEmpty(value)) {
						const {
							enableCropperTool,
							uploaderLabel
						} = currentInputConfig;

						let {acceptedTypes} = currentInputConfig;

						if (isEmpty(acceptedTypes)) {
							acceptedTypes = WatsonConstants.uploadSettings.acceptedTypes[9561].type;
						}

						inputComponent = (
							<FileUploader
								{...config}
								acceptedTypes={acceptedTypes}
								classPK={props.watsonIncidentId}
								enableCropperTool={enableCropperTool}
								multiple={false}
								uploaderLabel={uploaderLabel}
							/>
						);
					}
					else {
						inputComponent = (
							<FileViewer
								{...config}
								microForm={true}
							/>
						);
					}
				}

				let {cssClass = ''} = currentInputConfig;

				const validationMessages = [];

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

				inputs.push(
					<div class={`input ${cssClass}`}>
						{label &&
							<span class="input-label">
								{label}
							</span>
						}

						{inputComponent}

						{validationMessages}
					</div>
				);
			}
		);

		return inputs;
	}

	rendered() {
		if (this.props.triggerSubmit) {
			this.handleSubmit();
		}
	}
}

MicroForm.PROPS = {
	cancelMethod: Config.func(),
	disabled: Config.bool(),
	fieldConfig: Config.object(),
	formConfig: Config.array(),
	id: Config.string(),
	loading: Config.bool().value(false),
	response: Config.value(new Map()),
	storeData: Config.value(null),
	submitMethod: Config.func(),
	triggerSubmit: Config.bool().value(false),
	watsonIncidentId: Config.any()
};

MicroForm.STATE = {
	dataSent: Config.bool().value(false),
	microFormData: Config.object().value({}),
	submitted: Config.bool().value(false),
	validationErrors: Config.object().value({})
};

export default MicroForm;