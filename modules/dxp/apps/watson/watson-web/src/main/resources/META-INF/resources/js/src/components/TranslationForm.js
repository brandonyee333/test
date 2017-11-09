import {bindAll, isEqual, noop} from 'lodash';
import JSXComponent, {Config} from 'metal-jsx';
import sub from 'string-sub';

import BottomBar from './BottomBar';
import {convertMapToObject} from '../lib/util';
import HTMLRenderer from './HTMLRenderer';
import Input from './Input';
import RichTextEditor from './RichTextEditor';
import TextAreaInput from './TextAreaInput';
import Tooltip from './Tooltip';

class TranslationForm extends JSXComponent {
	attached() {
		this.state.intervalId = setInterval(() => this.handleSoftSave(), 60000);
	}

	created() {
		bindAll(
			this,
			'handleCancel',
			'handleSoftSave',
			'handleSubmit',
			'handleUpdateValue'
		);

		this.state.changedFields = [];
		this.state.originalFormData = {};
		this.state.translatedFormData = {};
	}

	disposed() {
		const {intervalId} = this.state;

		clearInterval(intervalId);
	}

	handleCancel() {
		const {redirect: cancelMethod} = this.props;

		if (cancelMethod) {
			cancelMethod();
		}
	}

	handleSoftSave() {
		const {props, state} = this;

		if (props.autoSaveEnabled && props.autoSaveMethod) {
			let {formData = {}} = state;

			props.formConfig.forEach(
				inputId => {
					const {[inputId]: {translatable}} = props.fieldConfig;

					formData = this.handleUntranslatableFields(formData, inputId, translatable);
				}
			);

			formData.id = props.id;
			formData.return = true;
			formData.translatingTo = WatsonConstants.otherLanguageId;

			props.autoSaveMethod(formData);

			this.state.changedFields = [];
		}
	}

	handleSubmit() {
		const {props, state} = this;

		const {fieldConfig, id, submitMethod} = props;

		let {formData} = state;

		if (formData) {
			props.formConfig.forEach(
				inputId => {
					const {[inputId]: {translatable}} = fieldConfig;

					formData = this.handleUntranslatableFields(formData, inputId, translatable);
				}
			);

			formData.id = id;
			formData.translatingTo = WatsonConstants.otherLanguageId;

			submitMethod(formData);

			this.setState({changedFields: [], dataSent: true, submitted: true});
		}
	}

	handleUntranslatableFields(formData, inputId, translatable) {
		if (translatable !== true) {
			delete formData[inputId];
		}

		return formData;
	}

	handleUpdateValue(value, inputId) {
		const {formData} = this.state;

		this.setState(
			{
				formData: Object.assign(
					{},
					formData,
					{
						[inputId]: value
					}
				)
			}
		);

		Liferay.Watson.debouncedSessionExtend();
	}

	render() {
		const {disabled, redirect: cancelMethod} = this.props;

		const {
			changedFields: {
				length: changedFieldsLength
			},
			originalFormData
		} = this.state;

		const bottomBarButtons = [];

		if (cancelMethod) {
			bottomBarButtons.push(
				{
					action: cancelMethod,
					label: Liferay.Language.get('cancel')
				}
			);
		}

		bottomBarButtons.push(
			{
				label: Liferay.Language.get('save'),
				submitButton: true
			}
		);

		let {message = ''} = this.props;

		if (originalFormData && Object.keys(originalFormData).length && changedFieldsLength) {
			message = sub(Liferay.Language.get('x-unsaved-changes'), changedFieldsLength);
		}

		const currentLanguage = WatsonConstants.currentLanguageId || '';
		const translatingToLanguage = WatsonConstants.otherLanguageId || '';

		return (
			<div class="translation-form-container">
				<div class="top-bar">
					<span class="language-key">{currentLanguage.substr(0, 2).toUpperCase()}</span>
					<span class="language-key">{translatingToLanguage.substr(0, 2).toUpperCase()}</span>
				</div>

				{this.renderInputs()}

				<BottomBar
					buttons={bottomBarButtons}
					formIsValid={!disabled}
					handleSubmit={this.handleSubmit}
					message={message}
				/>
			</div>
		);
	}

	rendered() {
		const {loading, redirect, response} = this.props;

		const {dataSent} = this.state;

		if (!loading && dataSent && redirect) {
			if (response && (response.get('status') === 'success')) {
				redirect();
			}
			else {
				this.state.dataSent = false;
			}
		}
	}

	renderInputs() {
		const {props, state} = this;

		const inputs = [];

		const {
			autoSaveResponse,
			disabled: incidentDisabled = false,
			fieldConfig,
			formConfig
		} = props;

		formConfig.forEach(
			(inputId, index) => {
				const autoFocus = index < 1;

				const currentInputConfig = fieldConfig[inputId];

				const {cssClass: inputCssClass = '', htmlType, tooltipLabel, translatable, type: currentType, validations} = currentInputConfig;
				let {disabled, label} = currentInputConfig;

				disabled = incidentDisabled ? incidentDisabled : !translatable;

				const {formData = {}, originalFormData} = state;

				let value = formData[inputId];

				const inputTypeConstants = WatsonConstants.inputConfig.inputTypes;

				if (validations && validations.includes('required')) {
					label += ' *';
				}

				const config = {
					autoFocus,
					disabled,
					inputId,
					onBlur: null,
					onChange: this.handleUpdateValue,
					value
				};

				value = originalFormData[inputId];

				let inputComponent = null;

				if (!disabled) {
					if (currentType === inputTypeConstants.input || currentType === inputTypeConstants.dependentInput || currentType === inputTypeConstants.dependentKeyedInput) {
						inputComponent = <Input {...config} htmlType={htmlType} />;
					}
					else if (currentType === inputTypeConstants.textareaInput) {
						inputComponent = <TextAreaInput {...config} />;

						if (value) {
							const formattedValue = value.replace(/(?:\r\n|\r|\n)/g, '<br />');

							value = <HTMLRenderer classNames="input-value" content={formattedValue} />;
						}
					}
					else if (currentType === inputTypeConstants.richTextEditor) {
						inputComponent = <RichTextEditor {...config} />;

						value = <HTMLRenderer classNames="input-value" content={value} />;
					}
				}

				let cssClass = '';

				const fieldIndex = state.changedFields.indexOf(inputId);

				if ((!isEqual(formData[inputId], state.translatedFormData[inputId]) && !autoSaveResponse) || (autoSaveResponse && !isEqual(formData[inputId], autoSaveResponse[inputId]))) {
					cssClass += 'changed';

					if (fieldIndex === -1) {
						state.changedFields.push(inputId);
					}
				}
				else if (fieldIndex > -1) {
					state.changedFields.splice(fieldIndex, 1);
				}

				if (label) {
					let tooltipIcon;

					if (tooltipLabel) {
						tooltipIcon = <span class="tooltip-icon" data-for={inputId} data-tip={tooltipLabel} />;
					}

					inputs.push(
						<div class={`translation-fields ${inputCssClass}`}>
							<div class="input-label">
								{label}

								{tooltipIcon}

								{tooltipIcon &&
									<Tooltip
										id={inputId}
										key={index}
										tooltipLabel={tooltipLabel}
									/>
								}
							</div>

							<div class={`input-data ${inputCssClass}`}>
								<div class="input-value">{value}</div>

								<div class={`input ${cssClass}`}>
									{inputComponent}
								</div>
							</div>
						</div>
					);
				}
			}
		);

		return inputs;
	}

	syncStoreData(newState, oldState) {
		if (newState && newState !== oldState) {
			const originalFormData = convertMapToObject(newState);

			this.setState({changedFields: [], originalFormData});
		}
	}

	syncTranslatedData(newState, oldState) {
		if (newState && newState !== oldState) {
			this.setState({formData: newState, translatedFormData: newState});
		}
	}
}

TranslationForm.PROPS = {
	autoSaveEnabled: Config.bool(),
	autoSaveMethod: Config.func().value(noop),
	autoSaveResponse: Config.object(),
	disabled: Config.bool(),
	fieldConfig: Config.object(),
	formConfig: Config.array(),
	id: Config.string(),
	loading: Config.bool().value(false),
	redirect: Config.func(),
	response: Config.value(new Map()),
	storeData: Config.value(null),
	submitMethod: Config.func(),
	translatedData: Config.value(null)
};

TranslationForm.STATE = {
	changedFields: Config.array().value([]),
	dataSent: Config.bool().value(false),
	formData: Config.object().value({}),
	intervalId: Config.number(),
	originalFormData: Config.object().value({}),
	submitted: Config.bool().value(false),
	translatedFormData: Config.object().value({})
};

export default TranslationForm;