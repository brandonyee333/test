import Component, {Config} from 'metal-jsx';

import InputList from './InputList';
import sendRequest from '../../lib/request';

class EntityInputList extends Component {
	created() {
		this.fetchEntity_ = this.fetchEntity_.bind(this);
	}

	fetchEntity_(input) {
		return sendRequest(
			{
				controller: 'home',
				controllerMethod: 'search.json',
				data: {
					keywords: input,
					searchLimit: 5,
					type: this.props.type
				}
			}
		);
	}

	render() {
		const {label, name} = this.props;

		return (
			<span>
				<label class="label-container">{label}</label>

				<InputList
					{...this.otherProps()}
					dataSource={this.fetchEntity_}
					name={name}
				/>
			</span>
		);
	}
}

EntityInputList.PROPS = {
	label: Config.string(),
	name: Config.string(),
	type: Config.number()
};

export default EntityInputList;