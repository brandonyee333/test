import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import Transition from 'metal-css-transitions';
import {bindAll, isBoolean} from 'lodash';

import Card from './card';
import Icon from './Icon';
import Input from './Input';

import {
	ARROW_DOWN,
	ARROW_UP,
	ENTER,
	ESCAPE,
	TAB
} from '../lib/key-constants';

const KEY_HANDLERS = {
	[ARROW_DOWN]: 'handleInputArrowDown_',
	[ARROW_UP]: 'handleInputArrowUp_',
	[ENTER]: 'handleInputEnter_',
	[ESCAPE]: 'handleDropdownActive_',
	[TAB]: 'handleDropdownActive_'
};

class SelectItem extends Component {
	created() {
		this.handleClick_ = this.handleClick_.bind(this);
	}

	handleClick_() {
		const {item, onClick} = this.props;

		onClick(item);
	}

	render() {
		const {focused} = this.props;

		const classNames = getCN(
			'select-item-container',
			{focused}
		);

		return (
			<li class={classNames} onClick={this.handleClick_}>
				{this.props.children}
			</li>
		);
	}
}

SelectItem.PROPS = {
	focused: Config.bool(),
	item: Config.object(),
	onClick: Config.func()
};

class SelectInput extends Component {
	created() {
		bindAll(
			this,
			'handleDropdownActive_',
			'handleInputArrowDown_',
			'handleInputArrowUp_',
			'handleInputChange_',
			'handleInputEnter_',
			'handleSelectedItemKeyDown_',
			'isSelectedItem_',
			'onClickOutside_',
			'onInputKeyDown_',
			'setSelected_'
		);
	}

	detached() {
		document.removeEventListener('mousedown', this.onClickOutside_);
	}

	handleDropdownActive_(active) {
		if (!this.props.disabled) {
			this.state.active_ = isBoolean(active) ? active : !this.state.active_;

			if (this.state.active_) {
				document.addEventListener('mousedown', this.onClickOutside_);
			}
			else {
				document.removeEventListener('mousedown', this.onClickOutside_);
			}
		}
	}

	handleSelectChange_(event) {
		this.setSelected_(this.props.items.find(event.target.value));
	}

	handleSelectedItemKeyDown_({keyCode}) {
		if (keyCode === ENTER) {
			this.handleDropdownActive_();
		}
	}

	handleInputArrowDown_() {
		this.state.focusIndex_++;
	}

	handleInputArrowUp_() {
		this.state.focusIndex_--;
	}

	handleInputChange_(event) {
		this.state.inputValue_ = event.target.value;

		this.syncItems();
	}

	handleInputEnter_() {
		const {
			_filteredItems,
			state: {focusIndex_}
		} = this;

		if (focusIndex_ >= 0 && focusIndex_ < _filteredItems.length) {
			this.setSelected_(_filteredItems[focusIndex_]);
		}
	}

	handleListScroll_(focusIndex) {
		const focusedItem = this.refs[`selectInput${focusIndex}`];

		if (focusedItem && focusedItem.element) {
			const {itemsList} = this.refs;

			this.scrollIntoViewIfNeeded_(itemsList, focusedItem.element);
		}
	}

	isSelectedItem_(item) {
		const {getId, selectedItem} = this.props;

		return getId(selectedItem) === getId(item);
	}

	onClickOutside_({target}) {
		if (this.state.active_ && !this.element.contains(target)) {
			this.handleDropdownActive_(false);
		}
	}

	onInputKeyDown_(event) {
		const handler = KEY_HANDLERS[event.keyCode];

		if (handler && this.state.active_) {
			event.preventDefault();

			this[handler](event);
		}
	}

	scrollIntoViewIfNeeded_(scrollContainer, element) {
		const containerRect = scrollContainer.getBoundingClientRect();
		const elementRect = element.getBoundingClientRect();

		const offsetBottom = containerRect.bottom - elementRect.bottom;
		const offsetTop = elementRect.top - containerRect.top;

		if (offsetTop < 0) {
			scrollContainer.scrollTop += offsetTop;
		}
		else if (offsetBottom < 0) {
			scrollContainer.scrollTop -= offsetBottom;
		}
	}

	setFocusIndex_(newVal) {
		const filteredItemsLength = this._filteredItems.length;

		if (newVal < 0) {
			newVal = filteredItemsLength - 1;
		}
		else if (newVal > filteredItemsLength - 1) {
			newVal = 0;
		}

		this.handleListScroll_(newVal);

		return newVal;
	}

	setSelected_(entity) {
		this.props.onSelect(entity);

		this.state.active_ = false;
		this.state.focusIndex_ = -1;
	}

	syncItems() {
		const {getName, items} = this.props;

		this._filteredItems = items.filter(
			item => {
				const normalizedName = getName(item).toLowerCase();

				return normalizedName.includes(this.state.inputValue_.toLowerCase()) || this.isSelectedItem_(item);
			}
		);
	}

	render() {
		const {active_, inputValue_} = this.state;

		const {
			disabled,
			disabledTitle,
			getId,
			getName,
			itemRenderer,
			items,
			selectedItem,
			selectId
		} = this.props;

		const elementClasses = getCN(
			'select-input-container',
			{
				active: active_,
				disabled
			}
		);

		return (
			<div class={elementClasses}>
				{disabled &&
					<div class="disabled-mask" data-tooltip title={disabledTitle} />
				}

				<select
					id={selectId}
					onChange={this.handleSelectChange_}
					style={{display: 'none'}}
					value={getId(selectedItem)}
				>
					{
						items.map(
							item => {
								const optionId = getId(item);

								return <option key={optionId} value={optionId}>{getName(item)}</option>;
							}
						)
					}
				</select>

				<div class="selected-item" onClick={this.handleDropdownActive_} onKeyDown={this.handleSelectedItemKeyDown_} tabIndex="0">
					<div class="item-container">
						{itemRenderer(selectedItem, false)}
					</div>

					{!disabled &&
						<Icon elementClasses="select-arrow" name="arrow-down-short" size="small" />
					}
				</div>

				<Transition name="transition-slide-up">
					{active_ &&
						<Card elementClasses="drop-down">
							{items.length > 5 &&
								<div class="magnifier">
									<Input
										focusOnAttached={true}
										icon="magnifier"
										onInput={this.handleInputChange_}
										onKeyDown={this.onInputKeyDown_}
										placeholder={Liferay.Language.get('search')}
										ref="input"
										row={true}
										value={inputValue_}
									/>
								</div>
							}

							<ul class="item-list" ref="itemsList">
								{
									this._filteredItems.map(
										(item, i) => (
											<SelectItem
												focused={this.state.focusIndex_ === i}
												item={item}
												key={getId(item)}
												onClick={this.setSelected_}
												ref={`selectInput${i}`}
											>
												{itemRenderer(item, this.isSelectedItem_(item))}
											</SelectItem>
										)
									)
								}
							</ul>
						</Card>
					}
				</Transition>
			</div>
		);
	}
}

SelectInput.PROPS = {
	disabled: Config.bool().value(false),
	disabledTitle: Config.string(),
	getId: Config.func().value(item => item.id),
	getName: Config.func().value(item => item.name),
	itemRenderer: Config.func(),
	items: Config.array(),
	onSelect: Config.func(),
	selectedItem: Config.object(),
	selectId: Config.string()
};

SelectInput.STATE = {
	active_: Config.value(false),
	focusIndex_: Config.setter('setFocusIndex_').value(0),
	inputValue_: Config.value('')
};

export default SelectInput;