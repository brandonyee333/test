import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {bindAll, isArray, partition} from 'lodash';
import {connect} from 'metal-redux';
import {Map} from 'immutable';

import GlobalMenu from './GlobalMenu';
import GlobalSearch from './GlobalSearch';
import IconLabel from '../IconLabel';
import LoopConstants from '../../lib/loop-constants';
import Notifications from '../notifications';
import Overlay from '../Overlay';
import sendRequest from '../../lib/request';
import {hideModal, modalTypes, showModal} from '../../actions/modals';
import {hotKeyManager} from '../../lib/HotKeyManager';
import {overlayTypes} from '../../actions/overlays';

const {
	classNameIds,
	currentPerson,
	namespace,
	urls
} = LoopConstants;

const TABS = [
	{
		icon: 'house',
		label: Liferay.Language.get('home'),
		url: urls.home
	},
	{
		icon: 'persons',
		label: Liferay.Language.get('people'),
		url: urls.people
	},
	{
		classNameId: classNameIds.divisions,
		icon: 'groups',
		label: Liferay.Language.get('groups'),
		url: urls.groups
	},
	{
		classNameId: classNameIds.topics,
		icon: 'hash',
		label: Liferay.Language.get('topics'),
		url: urls.topics
	}
];

class Toolbar extends Component {
	created() {
		bindAll(
			this,
			'handleSearchInput_',
			'search_',
			'showHotKeyModal_',
			'showMarkdownCheatsheet_',
			'sortSearchResults_'
		);

		this.state.inputValue_ = this.props.query;

		this._hotKeyModalConfig = {
			hideOnBlur: true,
			modalProps: {
				shortcuts: hotKeyManager.getIndex()
			},
			modalType: modalTypes.HOT_KEYS
		};

		this._markdownCheatsheetModalConfig = {
			hideOnBlur: true,
			modalProps: {},
			modalType: modalTypes.MARKDOWN_CHEATSHEET
		};
	}

	attached() {
		this.bindHotKeys_();

		this.getRecentSearches_();
	}

	detached() {
		this.unbindHotKeys_();
	}

	disposed() {
		this.cancelPendingRequests_();
	}

	bindHotKeys_() {
		const {SPA} = Liferay.Loop;

		hotKeyManager.bind(
			{
				action: this.showHotKeyModal_,
				definition: Liferay.Language.get('view-keyboard-shortcuts'),
				keys: '?'
			}
		);

		hotKeyManager.bind(
			{
				action: this.showMarkdownCheatsheet_,
				definition: Liferay.Language.get('view-markdown-cheatsheet'),
				keys: 'M M'
			}
		);

		hotKeyManager.bind(
			{
				action: () => {
					SPA.navigate(urls.groups);
				},
				definition: Liferay.Language.get('go-to-groups-index'),
				keys: 'g g'
			}
		);

		hotKeyManager.bind(
			{
				action: () => {
					SPA.navigate(urls.home);
				},
				definition: Liferay.Language.get('go-to-home-stream'),
				keys: 'g h'
			}
		);

		hotKeyManager.bind(
			{
				action: () => {
					SPA.navigate(this.props.currentPersonIMap.get('displayURL'));
				},
				definition: Liferay.Language.get('go-to-profile'),
				keys: 'g m'
			}
		);

		hotKeyManager.bind(
			{
				action: () => {
					SPA.navigate(urls.people);
				},
				definition: Liferay.Language.get('go-to-people-index'),
				keys: 'g p'
			}
		);

		hotKeyManager.bind(
			{
				action: () => {
					SPA.navigate(urls.topics);
				},
				definition: Liferay.Language.get('go-to-topics-index'),
				keys: 'g t'
			}
		);

		const {konami} = urls;

		if (konami) {
			hotKeyManager.bind(
				{
					action: () => {
						window.location.href = konami;
					},
					keys: 'up up down down left right left right b a'
				}
			);
		}
	}

	cancelPendingRequests_() {
		if (this._request) {
			this._request.cancel();
		}
	}

	clearResults_() {
		this.state.searchResults_ = {};
	}

	getRecentSearches_() {
		if (Liferay.Store) {
			Liferay.Store.get(
				`${namespace}recentSearches`,
				recentSearches => {
					if (isArray(recentSearches)) {
						this.state.recentSearches_ = recentSearches;
					}
				}
			);
		}
	}

	handleSearchInput_(query) {
		this.state.inputValue_ = query;

		this.search_(query);
	}

	search_(query) {
		if (query.length) {
			this.cancelPendingRequests_();

			this._request = sendRequest(
				{
					controller: 'home',
					controllerMethod: 'collatedSearch.json',
					data: {
						includeInactivePersons: true,
						keywords: query,
						searchLimit: 3
					}
				}
			).then(
				results => {
					if (this.state.inputValue_.length) {
						this.sortSearchResults_(results);
					}
					else {
						this.clearResults_();
					}
				}
			);
		}
		else {
			this.getRecentSearches_();
			this.clearResults_();
		}
	}

	showHotKeyModal_() {
		this.props.showModal(this._hotKeyModalConfig);
	}

	showMarkdownCheatsheet_() {
		this.props.showModal(this._markdownCheatsheetModalConfig);
	}

	sortSearchResults_(entities) {
		const filterPeople = partition(
			entities,
			({entityClassNameId}) => entityClassNameId === classNameIds.people
		);

		const filterTopics = partition(
			filterPeople[1],
			({entityClassNameId}) => entityClassNameId === classNameIds.topics
		);

		const divisions = filterTopics[1];
		const people = filterPeople[0];
		const topics = filterTopics[0];

		this.state.searchResults_ = {
			divisions,
			people,
			topics
		};
	}

	syncPageTitle(newVal) {
		if (newVal) {
			document.title = newVal;
		}
	}

	unbindHotKeys_() {
		hotKeyManager.unbind('?');
		hotKeyManager.unbind('g g');
		hotKeyManager.unbind('g h');
		hotKeyManager.unbind('g m');
		hotKeyManager.unbind('g p');
		hotKeyManager.unbind('g t');
		hotKeyManager.unbind('up up down down left right left right b a enter');
	}

	render() {
		const {
			props: {
				activeTabIndex,
				currentPersonIMap,
				display,
				pageTitle
			},
			state: {
				inputValue_,
				recentSearches_,
				searchResults_: {
					divisions = [],
					people = [],
					topics = []
				}
			}
		} = this;

		const classes = getCN(
			'toolbar-container',
			{
				[`display-${display}`]: display
			}
		);

		return (
			<div class={classes}>
				<div class="content">
					<button class="accessibility-button" onClick={this.showHotKeyModal_} tabindex="1">{Liferay.Language.get('keyboard-shortcuts')}</button>

					<div class="page-title toolbar-item">
						{pageTitle}
					</div>

					<nav class="nav toolbar-item">
						<ul>
							{
								TABS.map(
									({classNameId, icon, label, url}, i) => {
										const active = i === activeTabIndex;

										return (
											<li class="nav-item" key={i}>
												{classNameId &&
													<Overlay
														containerClass="following-list-menu-container"
														hideDelay={50}
														hideOnClick={true}
														offset={12}
														overlayProps={{
															classNameId,
															id: currentPersonIMap.get('entityClassPK')
														}}
														overlayType={overlayTypes.FOLLOWING_LIST_MENU}
													>
														<a
															class={getCN(
																'following-list-menu',
																'menu-trigger',
																'nav-link',
																{'nav-active': active}
															)}
															href={url}
														>
															<IconLabel label={label} name={icon} responsiveWrap={true} />
														</a>
													</Overlay>
												}

												{!classNameId &&
													<a class={getCN('nav-link', {active})} href={url}>
														<IconLabel label={label} name={icon} responsiveWrap={true} />
													</a>
												}
											</li>
										);
									}
								)
							}
						</ul>
					</nav>

					<div class="toolbar-item search">
						<div>
							<GlobalSearch
								divisions={divisions}
								initialInputValue={inputValue_}
								onQueryChange={this.handleSearchInput_}
								people={people}
								query={inputValue_}
								recentSearches={recentSearches_}
								topics={topics}
							/>
						</div>
					</div>

					<div class="toolbar-item">
						<Notifications itemsPerPage={6} />
					</div>

					<div class="toolbar-item">
						<GlobalMenu entity={currentPersonIMap.toJS()} hotKeysModalConfig={this._hotKeyModalConfig} markdownCheatsheetModalConfig={this._markdownCheatsheetModalConfig} />
					</div>
				</div>
			</div>
		);
	}
}

const STORE = {
	activeTabIndex: Config.number(),
	currentPersonIMap: Config.instanceOf(Map),
	display: Config.oneOf(['default', 'primary']),
	hideModal: Config.func(),
	pageTitle: Config.string(),
	showModal: Config.func()
};

Toolbar.PROPS = {
	...STORE
};

Toolbar.STATE = {
	inputValue_: Config.value(''),
	recentSearches_: Config.value([]),
	searchResults_: Config.value({})
};

export default connect(
	state => {
		const toolbarIMap = state.get('toolbar', Map());

		return {
			activeTabIndex: toolbarIMap.get('activeTabIndex'),
			currentPersonIMap: state.getIn(['people', currentPerson.entityClassPK, 'data'], Map()),
			display: toolbarIMap.get('display'),
			pageTitle: toolbarIMap.get('pageTitle')
		};
	},
	{
		hideModal,
		showModal
	}
)(Toolbar);