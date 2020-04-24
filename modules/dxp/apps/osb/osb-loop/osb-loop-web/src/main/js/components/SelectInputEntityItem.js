import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';

import Avatar from './Avatar';
import Icon from './Icon';

class SelectInputEntityItem extends Component {
	render() {
		const {entity, selected} = this.props;

		const elementClasses = getCN(
			'select-input-entity-item-container',
			{selected}
		);

		return (
			<div class={elementClasses}>
				<Avatar disableLink={true} entity={entity} size="tiny" />

				<div class="name">
					{entity.name}
				</div>

				{selected &&
					<Icon display="primary" name="check" size="small" />
				}
			</div>
		);
	}
}

SelectInputEntityItem.PROPS = {
	entity: Config.object(),
	selected: Config.bool()
};

export default SelectInputEntityItem;