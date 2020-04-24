import Component, {Config} from 'metal-jsx';
import Promise from 'metal-promise';
import {bindAll, range} from 'lodash';

import ElementContainer from './ElementContainer';
import InputList from '../InputList';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';

class InputListKit extends Component {
	created() {
		this._dataSourceItems = range(20).map(
			i => {
				let func = LoopAssets.getTopic;

				if (i <= 5) {
					func = LoopAssets.getPerson;
				}
				else if (i <= 10) {
					func = LoopAssets.getDivision;
				}

				return {
					...func(i),
					removable: true
				};
			}
		);

		this.state.items_ = this._dataSourceItems.slice(0, 5);

		bindAll(
			this,
			'dataSource_',
			'handleChange_'
		);
	}

	dataSource_(query) {
		return Promise.resolve(
			this._dataSourceItems.filter(
				item => item.name.toLowerCase().includes(query.toLowerCase())
			).slice(0, 5)
		);
	}

	handleChange_(items) {
		this.state.items_ = items;
	}

	render() {
		return (
			<Kit header="InputList">
				<ElementContainer header="Normal">
					<InputList
						dataSource={this.dataSource_}
						items={this.state.items_}
						label="Add People"
						onChange={this.handleChange_}
						placeholder="Search for an entity"
					/>
				</ElementContainer>

				<ElementContainer header="Disabled">
					<InputList disabled items={this._dataSourceItems} label="Add Entities" />
				</ElementContainer>
			</Kit>
		);
	}
}

InputListKit.STATE = {
	items_: Config.value([])
};

export default InputListKit;