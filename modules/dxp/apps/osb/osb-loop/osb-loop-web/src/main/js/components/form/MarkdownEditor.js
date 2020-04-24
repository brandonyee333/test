import FormComponent, {Config} from './FormComponent';
import {uniqueId} from 'lodash';

import Label from './Label';
import MarkdownEditor from '../MarkdownEditor';

class FormMarkdownEditor extends FormComponent {
	created() {
		this._id = uniqueId('formSelectInput');

		this.handleChange_ = this.handleChange_.bind(this);
	}

	handleChange_(value) {
		this.state.value_ = value;
	}

	syncInitialValue(newValue) {
		if (newValue && newValue !== this.state.value_) {
			this.state.value_ = newValue;
		}
	}

	render() {
		const {label} = this.props;

		return (
			<div>
				{label &&
					<Label for={this._id} text={label} />
				}

				<MarkdownEditor
					{...this.otherProps()}
					onChange={this.handleChange_}
					value={this.state.value_}
				/>
			</div>
		);
	}
}

FormMarkdownEditor.PROPS = {
	initialValue: Config.string().value(''),
	label: Config.string()
};

FormMarkdownEditor.STATE = {
	value_: Config.value('')
};

export default FormMarkdownEditor;