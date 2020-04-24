import Component, {Config} from 'metal-jsx';
import Promise from 'metal-promise';

import Checkbox from './Checkbox';
import DatePicker from './DatePicker';
import EntityInputList from './EntityInputList';
import Input from './Input';
import InputList from './InputList';
import JobTitleSelect from './JobTitleSelect';
import MarkdownEditor from './MarkdownEditor';
import MultiInput from './MultiInput';
import Select from './Select';
import FormSelectInput from './FormSelectInput';
import Textarea from './Textarea';

class Form extends Component {
	created() {
		this._formElements = [];

		this.handleSubmit_ = this.handleSubmit_.bind(this);
	}

	addFormElement(component) {
		this._formElements.push(component);
	}

	removeFormElement(component) {
		this._formElements = this._formElements.filter(element => element !== component);
	}

	getChildContext() {
		return {ancestorForm: this};
	}

	getValues() {
		return this._formElements.reduce(
			(res, next) => (
				{
					...res,
					[next.getFormElementName()]: next.getFormElementValue()
				}
			),
			{}
		);
	}

	handleSubmit_(event) {
		if (this.props.validateOnSubmit && !this.validate()) {
			event.preventDefault();
		}
	}

	validate() {
		return Promise.all(
			this._formElements.map(
				element => {
					let retVal = element.validateFormElement();

					if (!(retVal instanceof Promise)) {
						retVal = Promise.resolve(retVal);
					}

					return retVal;
				}
			)
		).then(
			values => {
				let retVal;

				if (values.every(val => val)) {
					retVal = Promise.resolve(this.getValues());
				}
				else {
					retVal = Promise.reject('');
				}

				return retVal;
			}
		);
	}

	render() {
		return (
			<form
				class="form-container"
				onSubmit={this.handleSubmit_}
				{...this.otherProps()}
			>
				{this.props.children}
			</form>
		);
	}
}

Form.PROPS = {
	validateOnSubmit: Config.bool().value(true)
};

Form.Checkbox = Checkbox;
Form.DatePicker = DatePicker;
Form.EntityInputList = EntityInputList;
Form.Input = Input;
Form.InputList = InputList;
Form.JobTitleSelect = JobTitleSelect;
Form.MarkdownEditor = MarkdownEditor;
Form.MultiInput = MultiInput;
Form.Select = Select;
Form.SelectInput = FormSelectInput;
Form.Textarea = Textarea;

export default Form;