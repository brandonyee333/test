import Component, {Config} from 'metal-jsx';
import {bindAll, trim} from 'lodash';

import AutocompleteMenu, {getAcItem, itemDown, itemUp} from './AutocompleteMenu';
import EntityDisplay from './EntityDisplay';
import Input from './Input';
import Spinner from './Spinner';

const KEY_HANDLERS = {
	ArrowDown: 'handleArrowDown_',
	ArrowUp: 'handleArrowUp_',
	Enter: 'handleEnter_',
	Escape: 'handleEscape_'
};

class AutocompleteInput extends Component {
	created() {
		this._ignoreBlur = false;

		bindAll(
			this,
			'handleAutocompleteMenuClick_',
			'handleAutocompleteMenuClickOutside_',
			'handleAutocompleteMenuMouseDown_',
			'handleAutocompleteMenuMouseEnter_',
			'handleBlur_',
			'handleInput_',
			'handleKeyDown_',
			...Object.values(KEY_HANDLERS)
		);
	}

	cancelPendingRequests_() {
		if (this._request) {
			this._request.cancel();
		}
	}

	disposed() {
		this.cancelPendingRequests_();
	}

	closeMenu_() {
		this.setState(
			{
				highlightedIndex_: 0,
				results_: []
			}
		);
	}

	handleArrowDown_() {
		const {highlightedIndex_, results_} = this.state;

		this.state.highlightedIndex_ = itemDown(results_.length, highlightedIndex_);
	}

	handleArrowUp_() {
		const {highlightedIndex_, results_} = this.state;

		this.state.highlightedIndex_ = itemUp(results_.length, highlightedIndex_);
	}

	handleAutocompleteMenuClick_(item) {
		this.handleSelect(item.data);

		this.setIgnoreBlur_(false);
	}

	handleAutocompleteMenuClickOutside_() {
		this.closeMenu_();

		this.setIgnoreBlur_(false);
	}

	handleAutocompleteMenuMouseDown_() {
		this.setIgnoreBlur_(true);
	}

	handleAutocompleteMenuMouseEnter_(index) {
		this.state.highlightedIndex_ = index;
	}

	handleBlur_() {
		if (!this._ignoreBlur) {
			this.closeMenu_();
		}
	}

	handleEnter_() {
		const {highlightedIndex_, results_} = this.state;

		if (results_.length) {
			const {data} = getAcItem(results_, highlightedIndex_);

			this.handleSelect(data);
		}
	}

	handleEscape_() {
		this.closeMenu_();
	}

	handleInput_(event) {
		const {value} = event.target;

		if (value) {
			const searchQuery = trim(value);

			this.cancelPendingRequests_();

			this.state.searching_ = true;

			this._request = this.props.dataSource(searchQuery).then(
				results => {
					this.setState(
						{
							inputValue_: value,
							results_: results.map(
								item => ({data: item})
							),
							searching_: false
						}
					);
				}
			);
		}
		else {
			this.closeMenu_();
		}
	}

	handleKeyDown_(event) {
		const handler = KEY_HANDLERS[event.key];

		if (handler) {
			event.preventDefault();

			this[handler](event);
		}
		else {
			this.state.highlightedIndex_ = 0;
		}
	}

	handleSelect(item) {
		this.props.onSelect(item);

		this.setState(
			{
				highlightedIndex_: 0,
				inputValue_: '',
				results_: []
			}
		);
	}

	renderItem_(item) {
		return <EntityDisplay disableLinks={true} entity={item} summary={false} />;
	}

	setIgnoreBlur_(ignore) {
		this._ignoreBlur = ignore;
	}

	render() {
		const {
			props: {children},
			state: {
				highlightedIndex_,
				inputValue_,
				results_,
				searching_
			}
		} = this;

		return (
			<div class="autocomplete-input-container">
				{children}

				<Input
					{...this.otherProps()}
					onBlur={this.handleBlur_}
					onInput={this.handleInput_}
					onKeyDown={this.handleKeyDown_}
					row={true}
					value={inputValue_}
				/>

				{searching_ &&
					<Spinner key="spinner" size="small" />
				}

				{results_.length > 0 &&
					<AutocompleteMenu
						highlightedIndex={highlightedIndex_}
						itemRenderer={this.renderItem_}
						items={results_}
						onClick={this.handleAutocompleteMenuClick_}
						onClickOutside={this.handleAutocompleteMenuClickOutside_}
						onMouseDown={this.handleAutocompleteMenuMouseDown_}
						onMouseEnter={this.handleAutocompleteMenuMouseEnter_}
					/>
				}
			</div>
		);
	}
}

AutocompleteInput.PROPS = {
	dataSource: Config.func(),
	onSelect: Config.func()
};

AutocompleteInput.STATE = {
	highlightedIndex_: Config.value(0),
	inputValue_: Config.value(''),
	results_: Config.value([]),
	searching_: Config.value(false)
};

export default AutocompleteInput;