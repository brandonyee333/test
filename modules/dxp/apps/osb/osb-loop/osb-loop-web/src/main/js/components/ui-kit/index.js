import Component, {Config} from 'metal-jsx';
import {find, get} from 'lodash';

import Icon from '../Icon';
import SelectInput from '../SelectInput';

const req = require.context('.', false, /\w+Kit.js$/);

const kits = req.keys().map(
	(kit, id) => (
		{
			component: req(kit, id).default,
			id,
			name: kit.replace('./', '').replace('Kit.js', '')
		}
	)
);

class UIKit extends Component {
	created() {
		this.handleSelect_ = this.handleSelect_.bind(this);

		this._items = kits;

		const hash = window.location.hash;

		if (hash) {
			const item = find(this._items, ['name', hash.substring(1)]);

			this.state.activeKitId_ = get(item, 'id', 0);
		}
	}

	handleSelect_(item) {
		this.state.activeKitId_ = item.id;

		window.location.hash = item.name;
	}

	renderItem_(item, active) {
		return [
			<div class="kit-select-item" key={item.id}>
				{item.name}

				{active &&
					<Icon
						display="primary"
						name="check"
						ref={item.id}
						size="small"
					/>
				}
			</div>
		];
	}

	render() {
		const {activeKitId_} = this.state;

		const ComponentKit = kits[activeKitId_].component;

		return (
			<div class="loop-ui-kit">
				<SelectInput
					itemRenderer={this.renderItem_}
					items={this._items}
					onSelect={this.handleSelect_}
					selectedItem={this._items[activeKitId_]}
					selectId="uiKit"
				/>

				<ComponentKit />
			</div>
		);
	};
}

UIKit.STATE = {
	activeKitId_: Config.value(0)
};

export default UIKit;