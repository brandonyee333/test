import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {async} from 'metal';

import {
	bindAll,
	isArray,
	isString,
	uniq
} from 'lodash';

import AutocompleteMenu, {getAcItem, itemDown, itemUp} from '../AutocompleteMenu';
import EntityDisplay from '../EntityDisplay';
import Icon from '../Icon';
import LoopConstants from '../../lib/loop-constants';
import {hotKeyManager} from '../../lib/HotKeyManager';
import {lang} from '../../lib/lang-util';
import {setParamValue} from '../../lib/router-util';

const {namespace, urls} = LoopConstants;

const KEY_HANDLERS = {
	ArrowDown: 'handleArrowDown_',
	ArrowUp: 'handleArrowUp_',
	Enter: 'handleItemSelect_',
	Escape: 'handleEscape_'
};

function renderSearchQuery(content) {
	return (
		<div class="search-query">
			<div class="search-icon-wrapper">
				<Icon display="primary" name="magnifier" />
			</div>

			<div>
				{content}
			</div>
		</div>
	);
}

class GlobalSearch extends Component {
	created() {
		this._ignoreBlur = false;
		this._totalResults = 0;

		bindAll(
			this,
			'focusSearch_',
			'handleArrowDown_',
			'handleArrowUp_',
			'handleClickOutside_',
			'handleItemRenderer_',
			'handleKeyDown_',
			'handleMouseDown_',
			'handleMouseEnter_',
			'handleValueChange_',
			'searchBlur_',
			...Object.values(KEY_HANDLERS)
		);
	}

	attached() {
		hotKeyManager.bind(
			{
				action: event => {
					event.preventDefault();

					this.focusSearch_();
				},
				definition: Liferay.Language.get('focus-toolbar-search'),
				keys: '/'
			}
		);
	}

	detached() {
		hotKeyManager.unbind('/');
	}

	focusSearch_() {
		if (!this.state.focused_) {
			this.state.focused_ = true;

			const {input} = this.refs;

			async.nextTick(input.select, input);
		}
	}

	getDisplayResults() {
		const {
			divisions,
			people,
			query,
			recentSearches,
			topics
		} = this.props;

		const results = [];

		let total = 0;

		if (query.length) {
			total++;

			results.push(
				{
					data: {query}
				}
			);
		}
		else {
			total += recentSearches.length;

			results.push(
				{
					items: recentSearches.map(
						item => ({data: item})
					),
					title: Liferay.Language.get('recent-searches')
				}
			);
		}

		if (people.length) {
			total += people.length;

			results.push(
				{
					items: people.map(
						item => ({data: item})
					),
					title: Liferay.Language.get('people')
				}
			);
		}

		if (divisions.length) {
			total += divisions.length;

			results.push(
				{
					items: divisions.map(
						item => ({data: item})
					),
					title: Liferay.Language.get('groups')
				}
			);
		}

		if (topics.length) {
			total += topics.length;

			results.push(
				{
					items: topics.map(
						item => ({data: item})
					),
					title: Liferay.Language.get('topics')
				}
			);
		}

		this._totalResults = total;

		return results;
	}

	handleArrowDown_() {
		this.state.highlightedIndex_ = itemDown(this._totalResults, this.state.highlightedIndex_);
	}

	handleArrowUp_() {
		this.state.highlightedIndex_ = itemUp(this._totalResults, this.state.highlightedIndex_);
	}

	handleClickOutside_(target) {
		if (!this.element.contains(target)) {
			this.state.focused_ = false;

			this.setIgnoreBlur_(false);
		}
	}

	handleEscape_() {
		this.refs.input.blur();
	}

	handleItemSelect_(item) {
		const {focused_, highlightedIndex_} = this.state;

		if (focused_) {
			const {data} = getAcItem(this.getDisplayResults(), highlightedIndex_);

			const {displayURL, name, query = data} = data;

			let href = displayURL;

			if (!displayURL) {
				if (!href) {
					this.setRecentSearches_(query);
				}

				href = setParamValue(urls.search, 'q', encodeURIComponent(query));
			}

			if (name) {
				this.props.onQueryChange(name);
			}
			else if (isString(data)) {
				this.props.onQueryChange(data);
			}

			Liferay.Loop.SPA.navigate(href);
		}

		this.setIgnoreBlur_(false);

		this.state.focused_ = false;

		this.refs.input.blur();
	}

	handleMouseDown_() {
		this.setIgnoreBlur_(true);
	}

	handleItemRenderer_(item) {
		let retVal = '';

		if (typeof item === 'string') {
			retVal = renderSearchQuery(item);
		}
		else if (item.query) {
			retVal = renderSearchQuery(
				lang(Liferay.Language.get('search-for-x'), [<span class="query" key="query">{item.query}</span>])
			);
		}
		else {
			retVal = [<EntityDisplay entity={item} key="entity" size="small" summary={false} />];
		}

		return retVal;
	}

	handleKeyDown_(event) {
		const handler = KEY_HANDLERS[event.key];

		if (!this.state.focused_) {
			this.focusSearch_();
		}

		if (handler) {
			event.preventDefault();

			this[handler](event);
		}
		else {
			this.state.highlightedIndex_ = 0;
		}
	}

	handleMouseEnter_(index) {
		this.state.highlightedIndex_ = index;
	}

	handleValueChange_(event) {
		this.props.onQueryChange(event.target.value);
	}

	searchBlur_(event) {
		if (!this._ignoreBlur) {
			this.state.focused_ = false;
		}
	}

	setIgnoreBlur_(ignore) {
		this._ignoreBlur = ignore;
	}

	setRecentSearches_(query) {
		let {recentSearches} = this.props;

		if (!isArray(recentSearches)) {
			recentSearches = [];
		}

		recentSearches = uniq([query, ...recentSearches]).slice(0, 5);

		Liferay.Store.set(`${namespace}recentSearches`, recentSearches);
	}

	render() {
		const {focused_, highlightedIndex_} = this.state;

		const classes = getCN(
			'global-search-container',
			{
				focused: focused_
			}
		);

		return (
			<div class={classes}>
				<div class="input-wrapper" onClick={this.focusSearch_}>
					<Icon name="magnifier" />

					<input
						onBlur={this.searchBlur_}
						onInput={this.handleValueChange_}
						onKeyDown={this.handleKeyDown_}
						placeholder={Liferay.Language.get('search')}
						ref="input"
						value={this.props.initialInputValue}
					/>
				</div>

				{focused_ &&
					<AutocompleteMenu
						highlightedIndex={highlightedIndex_}
						itemRenderer={this.handleItemRenderer_}
						items={this.getDisplayResults()}
						onClick={this.handleItemSelect_}
						onClickOutside={this.handleClickOutside_}
						onMouseDown={this.handleMouseDown_}
						onMouseEnter={this.handleMouseEnter_}
						ref="acMenu"
					/>
				}
			</div>
		);
	}
}

GlobalSearch.PROPS = {
	divisions: Config.array(),
	initialInputValue: Config.string(),
	onQueryChange: Config.func(),
	people: Config.array(),
	recentSearches: Config.array(),
	topics: Config.array()
};

GlobalSearch.STATE = {
	focused_: Config.value(false),
	highlightedIndex_: Config.value(0)
};

export default GlobalSearch;