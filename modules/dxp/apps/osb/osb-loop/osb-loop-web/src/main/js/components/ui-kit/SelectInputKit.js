import Component, {Config} from 'metal-jsx';
import {bindAll, range} from 'lodash';

import ElementContainer from './ElementContainer';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import SelectInput from '../SelectInput';
import SelectInputEntityItem from '../SelectInputEntityItem';
import {getEntityId} from '../../lib/util';

class SelectInputKit extends Component {
	created() {
		this.state.selectedItem_ = this.state.items_[0];

		bindAll(
			this,
			'handleSelect_',
			'renderItem_'
		);
	}

	handleSelect_(entity) {
		this.state.selectedItem_ = entity;
	}

	renderItem_(item, selected) {
		return [<SelectInputEntityItem entity={item} key={getEntityId(item)} selected={selected} />];
	}

	render() {
		return (
			<Kit header="SelectInput">
				<ElementContainer>
					<SelectInput
						elementClasses="select-input-demo"
						getId={getEntityId}
						itemRenderer={this.renderItem_}
						items={this.state.items_}
						onSelect={this.handleSelect_}
						selectedItem={this.state.selectedItem_}
						selectId="UIKitSelectInput"
					/>
				</ElementContainer>
			</Kit>
		);
	}
}

SelectInputKit.STATE = {
	items_: Config.value(
		range(8).map(i => LoopAssets.getPerson(i))
	),
	selectedItem_: Config.value({})
};

export default SelectInputKit;