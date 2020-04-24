import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {bindAll, noop, uniqueId} from 'lodash';

import AutocompleteInput from './AutocompleteInput';
import EntityDisplay from './EntityDisplay';
import Icon from './Icon';
import IconLabel from './IconLabel';
import LoopConstants from '../lib/loop-constants';
import {getIconName, getLocationSubtypeName} from '../lib/util';

class InputListItem extends Component {
	created() {
		this.handleClick_ = this.handleClick_.bind(this);
	}

	handleClick_() {
		const {item, onRemove} = this.props;

		onRemove(item);
	}

	render() {
		const {
			disabled,
			item: {
				name,
				removable,
				subtype,
				type
			}
		} = this.props;

		let iconType = type;

		if (subtype && type == LoopConstants.types.location) {
			iconType = getLocationSubtypeName(subtype);
		}

		return (
			<span class="item-wrapper">
				<span class="item-content">
					<IconLabel label={name} name={getIconName(iconType)} size="small" />
				</span>

				{removable && !disabled &&
					<Icon elementClasses="remove" name="close-short" onClick={this.handleClick_} size="small" />
				}
			</span>
		);
	}
}

InputListItem.PROPS = {
	disabled: Config.bool().value(false),
	item: Config.object(),
	onRemove: Config.func()
};

class InputList extends Component {
	created() {
		this._id = `inputList${uniqueId()}`;

		bindAll(
			this,
			'handleAdd_',
			'handleRemoveItem_'
		);
	}

	handleAdd_(item) {
		const {items, onChange} = this.props;

		item.removable = true;

		onChange([...items, item]);
	}

	handleRemoveItem_(removedItem) {
		const {items, onChange} = this.props;

		onChange(items.filter(item => item !== removedItem));
	}

	renderItem_(item) {
		return <EntityDisplay disableLinks={true} entity={item} summary={false} />;
	}

	render() {
		const {
			allRemovable,
			dataSource,
			disabled,
			inlineLabel,
			items,
			label,
			placeholder
		} = this.props;

		const classNames = getCN(
			'input-list-container',
			{
				disabled,
				'inline-label': inlineLabel
			}
		);

		return (
			<div class={classNames} onClick={this.handleFocus_}>
				{label &&
					<label class="control-label" for={this._id}>{label}</label>
				}

				<AutocompleteInput
					dataSource={dataSource}
					disabled={disabled}
					id={this._id}
					onSelect={this.handleAdd_}
					placeholder={placeholder}
				>
					{
						items.map(
							(item, i) => {
								if (allRemovable) {
									item.removable = true;
								}

								return (
									<InputListItem
										disabled={disabled}
										item={item}
										key={item.entityClassPK}
										onRemove={this.handleRemoveItem_}
										ref={`listItem${i}`}
									/>
								);
							}
						)
					}
				</AutocompleteInput>
			</div>
		);
	}
}

InputList.PROPS = {
	allRemovable: Config.bool().value(false),
	dataSource: Config.func().value(noop),
	disabled: Config.bool().value(false),
	inlineLabel: Config.bool(),
	items: Config.array().value([]),
	label: Config.string(),
	multiple: Config.bool().value(true),
	onChange: Config.func(),
	placeholder: Config.string()
};

export default InputList;