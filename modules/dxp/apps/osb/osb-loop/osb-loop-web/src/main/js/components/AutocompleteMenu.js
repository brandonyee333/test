import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {bindAll} from 'lodash';

export function itemUp(totalItems, currentIndex) {
	return currentIndex === 0 ? totalItems - 1 : currentIndex - 1;
}

export function itemDown(totalItems, currentIndex) {
	return currentIndex === totalItems - 1 ? 0 : currentIndex + 1;
}

export function getAcItem(array, index, refObj = {count: 0}) {
	let retVal = null;

	for (let i = 0; i < array.length; i++) {
		const item = array[i];

		const {items} = item;

		if (items) {
			const nestedItem = getAcItem(items, index, refObj);

			if (refObj.count === index) {
				retVal = nestedItem;
			}
		}
		else if (refObj.count === index) {
			retVal = item;
		}
		else {
			refObj.count++;
		}

		if (retVal) {
			break;
		}
	}

	return retVal;
}

class AutocompleteMenu extends Component {
	created() {
		this.handleClickOutside_ = this.handleClickOutside_.bind(this);
	}

	attached() {
		const {onClickOutside} = this.props;

		if (onClickOutside) {
			document.addEventListener('click', this.handleClickOutside_);
		}
	}

	detached() {
		const {onClickOutside} = this.props;

		if (onClickOutside) {
			document.removeEventListener('click', this.handleClickOutside_);
		}
	}

	handleClickOutside_({target}) {
		if (!this.element.contains(target)) {
			this.props.onClickOutside(target);
		}
	}

	render() {
		const {items, style} = this.props;

		return (
			<div class="autocomplete-menu-container" style={style}>
				<AutocompleteList {...this.otherProps()} items={items} refObj={{count: 0}} />
			</div>
		);
	}
}

AutocompleteMenu.PROPS = {
	items: Config.array(),
	style: Config.object()
};

export default AutocompleteMenu;

class AutocompleteList extends Component {
	render() {
		const {items} = this.props;

		return (
			<ul class="autocomplete-list">
				{
					items.map(
						(item, i) => (
							<AutocompleteListItem
								{...this.otherProps()}
								item={item}
								key={item.items ? `list${i}` : `item${i}`}
							/>
						)
					)
				}
			</ul>
		);
	}
}

AutocompleteList.PROPS = {
	items: Config.array().value([])
};

class AutocompleteListItem extends Component {
	created() {
		bindAll(
			this,
			'handleItemClick_',
			'handleMouseEnter_'
		);
	}

	handleItemClick_() {
		const {item, onClick} = this.props;

		if (item.data) {
			onClick(item);
		}
	}

	handleMouseEnter_(event) {
		const {item, onMouseEnter} = this.props;

		if (item.data) {
			onMouseEnter(this._index);
		}
	}

	render() {
		const {
			highlightedIndex,
			item,
			itemRenderer,
			onClick,
			onMouseDown,
			onMouseEnter,
			refObj
		} = this.props;

		const {
			data,
			items,
			title
		} = item;

		if (!items) {
			this._index = refObj.count;

			refObj.count++;
		}

		const classes = getCN(
			'menu-item',
			{
				active: !items && highlightedIndex === this._index,
				selectable: data
			}
		);

		return (
			<li
				class={classes}
				onClick={this.handleItemClick_}
				onMouseDown={onMouseDown}
				onMouseEnter={this.handleMouseEnter_}
			>

				{title &&
					<h2 class="category-header">{title}</h2>
				}

				{data &&
					itemRenderer(data)
				}

				{items &&
					<AutocompleteList
						highlightedIndex={highlightedIndex}
						itemRenderer={itemRenderer}
						items={items}
						onClick={onClick}
						onMouseDown={onMouseDown}
						onMouseEnter={onMouseEnter}
						refObj={refObj}
					/>
				}
			</li>
		);
	}
}

AutocompleteListItem.PROPS = {
	highlightedIndex: Config.number(),
	item: Config.object(),
	itemRenderer: Config.func(),
	onClick: Config.func(),
	onMouseDown: Config.func(),
	onMouseEnter: Config.func(),
	refObj: Config.object()
};