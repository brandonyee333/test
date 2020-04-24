import Component, {Config} from 'metal-jsx';
import {bindAll, range} from 'lodash';

import Button from '../Button';
import Form from '../form';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import SelectInputEntityItem from '../SelectInputEntityItem';
import {getEntityId} from '../../lib/util';

class FormKit extends Component {
	created() {
		bindAll(
			this,
			'dataSource_',
			'handleClick_'
		);

		this._dataSourceItems = range(20).map(LoopAssets.getPerson);
	}

	dataSource_(query) {
		return Promise.resolve(
			this._dataSourceItems.filter(
				item => item.name.toLowerCase().includes(query.toLowerCase())
			).slice(0, 5)
		);
	}

	handleClick_() {
		const {form} = this.refs;

		form.validate();

		this.setState(
			{
				formData_: form.getValues(),
				showFormData_: true
			}
		);
	}

	renderItem_(item, selected) {
		return [<SelectInputEntityItem entity={item} key={getEntityId(item)} selected={selected} />];
	}

	render() {
		const {formData_, showFormData_} = this.state;

		return (
			<Kit card={false} header="Form">
				<Form ref="form">
					<Form.Input
						label="Topic Name"
						name="topic"
						validator={{
							maxLength: 50,
							required: true,
							topicName: true
						}}
					/>

					<Form.Input
						initialValue="test test"
						label="Person's Name"
						name="personName"
						validator="required"
					/>

					<Form.Input label="Website" name="website" validator="url" />

					<Form.Input label="Name" name="name" />

					<Form.Textarea label="Repository List" name="repoList" validator="required" />

					<Form.Checkbox label="Remember Me" name="remember" />

					<Form.InputList
						dataSource={this.dataSource_}
						label="Members"
						name="members"
						placeholder="Add members..."
					/>

					<Form.SelectInput
						getId={getEntityId}
						initialValue={this._dataSourceItems[0]}
						itemRenderer={this.renderItem_}
						items={this._dataSourceItems}
						label="Leader"
						name="leader"
					/>

					<Form.Select label="Job Title" name="jobTitle">
						<option value="Accountant">{'Accountant'}</option>
						<option value="Engineer">{'Engineer'}</option>
						<option value="Quality Assurance">{'Quality Assurance'}</option>
					</Form.Select>
				</Form>

				<Button onClick={this.handleClick_} role="primary">{'Validate'}</Button>

				{showFormData_ &&
					<div>
						<h3>{'Form Data:'}</h3>

						<pre>{JSON.stringify(formData_)}</pre>
					</div>
				}
			</Kit>
		);
	}
}

FormKit.STATE = {
	formData_: Config.value({}),
	showFormData_: Config.value(false)
};

export default FormKit;