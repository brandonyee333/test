import {bindAll, isEmpty} from 'lodash';
import JSXComponent, {Config} from 'metal-jsx';

import Modal from './Modal';
import MicroForm from './MicroForm';

class DynamicMicroFormModal extends JSXComponent {
	created() {
		bindAll(
			this,
			'_handleMicroFormClose',
			'_handleMicroFormSubmit'
		);
	}

	_handleMicroFormClose() {
		this.setState({showModal: false});
	}

	_handleMicroFormSubmit(data) {
		this._handleMicroFormClose();

		const {inputId, onChange} = this.props;

		onChange(data, inputId);
	}

	render() {
		const {props, state} = this;

		let generatedInput;

		const optionButtons = [];

		optionButtons.push(
			{
				label: Liferay.Language.get('add-document')
			}
		);

		const modal = {
			body: (
				<MicroForm
					cancelMethod={this._handleMicroFormClose}
					fieldConfig={WatsonConstants.inputConfig.resources.inputs}
					formConfig={['name', 'imagePayload', 'description']}
					submitMethod={this._handleMicroFormSubmit}
					watsonIncidentId={props.watsonIncidentId}
				/>
			),
			header: Liferay.Language.get('please-fill-out-all-required-fields')
		};

		if (!state.showModal) {
			const {value = {}} = props;

			const name = !isEmpty(value) ? value.name : Liferay.Language.get('resource');

			generatedInput = (
				<div class="micro-form-modal">
					<span class="input-value">{name}</span>
				</div>
			);
		}
		else {
			generatedInput = (
				<div class="micro-form-modal">
					<Modal
						body={modal.body}
						close={this._handleMicroFormClose}
						header={modal.header}
						visible={this.state.showModal}
					/>
				</div>
			);
		}
		return generatedInput;
	}
}

DynamicMicroFormModal.PROPS = {
	inputId: Config.object(),
	onChange: Config.func(),
	value: Config.object().value({}),
	watsonIncidentId: Config.any(),
	watsonPrimaryKey: Config.any()
};

DynamicMicroFormModal.STATE = {
	showModal: Config.bool().value(true)
};

export default DynamicMicroFormModal;