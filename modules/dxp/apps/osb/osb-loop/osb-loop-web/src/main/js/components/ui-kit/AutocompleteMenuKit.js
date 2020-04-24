import Component, {Config} from 'metal-jsx';
import {bindAll, noop} from 'lodash';

import AutocompleteMenu from '../AutocompleteMenu';
import ElementContainer from './ElementContainer';
import EntityDisplay from '../EntityDisplay';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';

const items = [
	{
		data: LoopAssets.getPerson()
	},
	{
		data: LoopAssets.getDivision()
	},
	{
		data: LoopAssets.getTopic()
	}
];

const items2 = [
	{
		data: LoopAssets.getPerson()
	},
	{
		items: [
			{
				data: LoopAssets.getDivision()
			},
			{
				data: LoopAssets.getDivision(1)
			}
		],
		title: 'Divisions'
	},
	{
		items: [
			{
				data: LoopAssets.getTopic()
			}
		],
		title: 'Topics'
	}
];

const styles = {
	height: '200px',
	position: 'relative'
};

class AutocompleteMenuKit extends Component {
	created() {
		bindAll(
			this,
			'handleCategoryMouseEnter_',
			'handleItemClick_',
			'handleItemRender_',
			'handleMouseEnter_'
		);
	}

	handleItemRender_(item) {
		return (
			<EntityDisplay disableLinks={true} entity={item} summary={false} />
		);
	}

	handleItemClick_(item) {
		alert('you clicked an item');
	}

	handleMouseEnter_(index) {
		this.state.index_ = index;
	}

	handleCategoryMouseEnter_(index) {
		this.state.categoryIndex_ = index;
	}

	render() {
		const {categoryIndex_, index_} = this.state;

		return (
			<Kit card={false} header="AutocompleteMenu">
				<ElementContainer header="No categories" style={styles}>
					<AutocompleteMenu
						highlightedIndex={index_}
						itemRenderer={this.handleItemRender_}
						items={items}
						onClick={this.handleItemClick_}
						onMouseDown={noop}
						onMouseEnter={this.handleMouseEnter_}
					/>
				</ElementContainer>

				<ElementContainer header="With categories" style={styles}>
					<AutocompleteMenu
						highlightedIndex={categoryIndex_}
						itemRenderer={this.handleItemRender_}
						items={items2}
						onClick={this.handleItemClick_}
						onMouseDown={noop}
						onMouseEnter={this.handleCategoryMouseEnter_}
					/>
				</ElementContainer>
			</Kit>
		);
	};
}

AutocompleteMenuKit.STATE = {
	categoryIndex_: Config.value(0),
	index_: Config.value(0)
};

export default AutocompleteMenuKit;