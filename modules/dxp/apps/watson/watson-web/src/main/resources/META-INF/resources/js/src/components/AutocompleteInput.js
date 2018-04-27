import JSXComponent, {Config} from 'metal-jsx';
import {bindAll, trim} from 'lodash';
import sub from 'string-sub';

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

class AutocompleteInput extends JSXComponent {
	created() {
		this._ignoreBlur = false;
		this._totalResults = 0;

		bindAll(
			this,
			'getDisplayResults',
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

	attached() {
		if (!this.state.inputValue_) {
			this.state.inputValue_ = this.props.query;
		}
	}

	cancelPendingRequests_() {
		if (this._request && this._request.cancel) {
			this._request.cancel();
		}
	}

	disposed() {
		this.cancelPendingRequests_();
	}

	closeMenu_(persistPropsValue) {
		let newInputValue = '';

		if (persistPropsValue && this.props.query) {
			newInputValue = this.props.query;
		}

		this.setState(
			{
				highlightedIndex_: 0,
				inputValue_: newInputValue,
				responseResults: []
			}
		);
	}

	getDisplayResults() {
		const {inputValue_, responseResults} = this.state;

		const results = [];

		let total = 0;

		if (inputValue_.length) {
			total++;

			results.push(
				{
					data: {query: inputValue_}
				}
			);
		}

		if (responseResults.length > 0) {
			results.push(...responseResults);

			total += results.length;
		}

		this._totalResults = total;

		return results;
	}

	handleArrowDown_() {
		const {highlightedIndex_} = this.state;

		this.state.highlightedIndex_ = itemDown(this._totalResults, highlightedIndex_);
	}

	handleArrowUp_() {
		const {highlightedIndex_} = this.state;

		this.state.highlightedIndex_ = itemUp(this._totalResults, highlightedIndex_);
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
		const {highlightedIndex_} = this.state;

		if (this._totalResults > 0) {
			const {data} = getAcItem(this.getDisplayResults(), highlightedIndex_);

			this.handleSelect(data);
		}
	}

	handleEscape_() {
		this.closeMenu_();
	}

	handleInput_(value) {
		if (value) {
			const searchQuery = trim(value);

			this.cancelPendingRequests_();

			this.setState(
				{
					inputValue_: value,
					searching_: true
				}
			);

			this._request = this.props.dataSource(searchQuery).then(
				results => {
					const itemList = [];

					results.forEach(
						item => {
							itemList.push({data: item});
						}
					);

					this.setState(
						{
							responseResults: itemList,
							searching_: false
						}
					);
				}
			);
		}
		else {
			this.closeMenu_(false);
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
				responseResults: []
			}
		);
	}

	renderItem_(item) {
		if (item.query) {
			item.name = sub(Liferay.Language.get('search-for-x'), item.query);
		}

		return <EntityDisplay entity={item} />;
	}

	setIgnoreBlur_(ignore) {
		this._ignoreBlur = ignore;
	}

	render() {
		const {
			state: {
				highlightedIndex_,
				inputValue_,
				searching_
			}
		} = this;

		return (
			<div class="autocomplete-input-container">
				<Input
					{...this.otherProps()}
					onBlur={this.handleBlur_}
					onChange={this.handleInput_}
					onKeyDown={this.handleKeyDown_}
					value={inputValue_}
				/>

				{searching_ &&
					<Spinner key="spinner" size="small" />
				}

				{inputValue_.length > 0 &&
					<AutocompleteMenu
						highlightedIndex={highlightedIndex_}
						itemRenderer={this.renderItem_}
						items={this.getDisplayResults()}
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
	responseResults: Config.value([]),
	searching_: Config.value(false)
};

export default AutocompleteInput;