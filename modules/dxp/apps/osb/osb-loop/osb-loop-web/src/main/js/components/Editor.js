import bridge from 'metal-react';
import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';

import {
	CompositeDecorator,
	convertToRaw,
	Editor as DraftEditor,
	EditorState,
	Entity,
	getVisibleSelectionRect,
	Modifier
} from 'draft-js';

import {
	bindAll,
	isNumber,
	noop,
	trim,
	values
} from 'lodash';

const Draft = bridge(DraftEditor);

import AutocompleteMenu, {getAcItem, itemDown, itemUp} from './AutocompleteMenu';
import EntityDisplay from './EntityDisplay';
import LoopConstants from '../lib/loop-constants';
import sendRequest from '../lib/request';
import {ARROW_DOWN, ARROW_UP, ESCAPE} from '../lib/key-constants';
import {Mention, MentionToken} from '../lib/draft-dependencies';

const KEY_HANDLERS = {
	[ARROW_DOWN]: 'handleArrowDown_',
	[ARROW_UP]: 'handleArrowUp_',
	[ESCAPE]: 'handleEscape_'
};

const MENTION_REGEX = /(?:\s|^)([@#](?:(?:[\w\-.,()]+\s){0,3})?[\w\-.,()\u00C0-\u017F]+)/g;

export const PLACEHOLDER_MAP = {
	image: Liferay.Language.get('say-something-about-this-photo'),
	link: Liferay.Language.get('say-something-about-this-link'),
	text: Liferay.Language.get('share-what-is-new')
};

export function createEmptyEditorState() {
	return EditorState.createEmpty();
}

class Editor extends Component {
	created() {
		bindAll(
			this,
			'focus',
			'handleAutocomplete_',
			'handleAutocompleteMenuClick_',
			'handleAutocompleteMenuClickOutside_',
			'handleAutocompleteMenuMouseDown_',
			'handleAutocompleteMenuMouseEnter_',
			'handleBlur_',
			'handleKeys_',
			'handleReturn_',
			'renderItem_'
		);

		this._ignoreBlur = false;
		this._topAlignment = null;
		this._topOffset = '0px';
		this._totalMentions = 0;
	}

	attached() {
		this.addBaseDecorators_();

		const {editorState} = this.props;

		if (editorState) {
			const rawEditorContent = convertToRaw(editorState.getCurrentContent());

			this._totalMentions = rawEditorContent.entityMap.size;
		}
	}

	addBaseDecorators_() {
		const {editorState, onEditorStateChange} = this.props;

		onEditorStateChange(
			EditorState.set(
				editorState,
				{
					decorator: this.createDecorators_()
				}
			)
		);
	}

	closeMenu_() {
		if (!this.state.modalOpen_) {
			this.setState(
				{
					highlightedIndex_: 0,
					open_: false
				}
			);
		}
	}

	createDecorators_() {
		return new CompositeDecorator(
			[
				{
					component: ({children, entityKey}) => {
						const entityData = Entity.get(entityKey).getData();

						return Mention(entityData, children);
					},
					strategy: (contentBlock, callback) => {
						contentBlock.findEntityRanges(
							character => {
								const entityKey = character.getEntity();

								return (entityKey !== null && Entity.get(entityKey).getType() === 'MENTION');
							},
							callback
						);
					}
				},
				{
					component: props => MentionToken(
						{
							onChange: this.handleAutocomplete_,
							...props
						}
					),
					strategy: (contentBlock, callback) => {
						const selection = this.getSelection_();

						const text = contentBlock.getText().slice(0, selection.getStartOffset() + 1);

						let matchArr;

						while ((matchArr = MENTION_REGEX.exec(text)) !== null) {
							const start = matchArr.index;

							callback(start, start + matchArr[0].length);
						}
					}
				}
			]
		);
	}

	focus() {
		this.components.editor.getInstance().focus();
	}

	getDeepSharedToCount_(results) {
		let retVal = 0;

		results.forEach(
			result => {
				const items = result.items;

				if (items) {
					retVal += items.length;
				}
				else {
					retVal += 1;
				}
			}
		);

		return retVal;
	};

	getMenuStyles_() {
		const containerRects = this.components.editor.element.getBoundingClientRect();

		const rects = getVisibleSelectionRect(window);

		if (!this._ignoreBlur && containerRects && rects) {
			const offset = rects.top - containerRects.top + this.props.autocompleteMenuTopOffset;

			this._topOffset = `${offset}px`;
		}

		return {
			top: this._topOffset
		};
	}

	getNewSelection_(anchorOffset, focusOffset) {
		let selection = this.getSelection_();

		if (isNumber(anchorOffset) && isNumber(focusOffset)) {
			selection = selection.merge(
				{
					anchorOffset,
					focusOffset
				}
			);
		}

		return selection;
	}

	getSelection_() {
		return this.props.editorState.getSelection();
	}

	handleArrowDown_() {
		const {autocompleteResults_, highlightedIndex_} = this.state;

		this.state.highlightedIndex_ = itemDown(this.getDeepSharedToCount_(autocompleteResults_), highlightedIndex_);
	}

	handleArrowUp_() {
		const {autocompleteResults_, highlightedIndex_} = this.state;

		this.state.highlightedIndex_ = itemUp(this.getDeepSharedToCount_(autocompleteResults_), highlightedIndex_);
	}

	handleAutocomplete_(query, queryStart) {
		if (query && isNumber(queryStart)) {
			const queryTrigger = query.substring(0, 1);

			const searchQuery = trim(
				query.substring(1, query.length)
			);

			this.requestAutocompleteResults_(searchQuery, queryTrigger).then(
				response => {
					let autocompleteResults_ = [];
					let open_ = false;

					if (this.props.parentId) {
						const {currentSharedToJSONArray, newSharedToJSONArray} = response;

						if (currentSharedToJSONArray.length) {
							autocompleteResults_ = currentSharedToJSONArray.map(
								participant => (
									{
										data: participant,
										excluded: false
									}
								)
							);

							open_ = true;
						}

						if (newSharedToJSONArray.length) {
							autocompleteResults_.push(
								{
									items: newSharedToJSONArray.map(
										nonparticipant => (
											{
												data: nonparticipant,
												excluded: true
											}
										)
									),
									title: Liferay.Language.get('not-mentioned-in-this-private-post')
								}
							);

							open_ = true;
						}
					}
					else if (response.length) {
						autocompleteResults_ = response.map(
							item => ({data: item})
						);

						open_ = true;
					}

					this.setState(
						{
							autocompleteResults_,
							open_,
							query_: query,
							queryStart_: queryStart
						}
					);
				}
			);
		}
		else {
			this.state.open_ = false;
		}
	}

	handleAutocompleteMenuClick_(item) {
		const {data, excluded} = item;

		if (excluded) {
			this.state.modalOpen_ = true;

			this.props.onExcludedMention(data, this.selectItem_.bind(this, data), this.focus);
		}
		else {
			this.selectItem_(data);
		}
	}

	handleAutocompleteMenuClickOutside_() {
		this.closeMenu_();

		this._ignoreBlur = false;
	}

	handleAutocompleteMenuMouseDown_() {
		this._ignoreBlur = true;
	}

	handleAutocompleteMenuMouseEnter_(index) {
		this.state.highlightedIndex_ = index;
	}

	handleBlur_() {
		this.props.onBlur();

		if (!this._ignoreBlur) {
			this.closeMenu_();
		}
	}

	handleEscape_() {
		this.closeMenu_();
	}

	handleKeys_(event) {
		const handler = KEY_HANDLERS[event.keyCode];

		if (handler && this.state.open_) {
			event.preventDefault();

			this[handler](event);
		}
		else {
			this.state.highlightedIndex_ = 0;
		}
	}

	handleReturn_(event) {
		const {autocompleteResults_, highlightedIndex_, open_} = this.state;

		let retVal;

		if (open_) {
			const item = getAcItem(autocompleteResults_, highlightedIndex_);

			if (item) {
				const {data, excluded} = item;

				if (excluded) {
					this.state.modalOpen_ = true;

					this.props.onExcludedMention(data, this.selectItem_.bind(this, data), this.focus);
				}
				else {
					this.selectItem_(data);
				}

				retVal = true;
			}
		}
		else if (this.props.onEnter) {
			retVal = this.props.onEnter(event);

			if (retVal) {
				this.components.editor.getInstance().blur();
			}
		}

		return retVal;
	}

	renderItem_(item) {
		return <EntityDisplay disableLinks={true} entity={item} summary={false} />;
	}

	requestAutocompleteResults_(string, trigger) {
		const {parentId} = this.props;

		const request = {
			controller: 'home',
			controllerMethod: 'search.json',
			data: {
				keywords: string,
				queryType: LoopConstants.queryTypes[trigger],
				searchLimit: 5
			}
		};

		if (parentId) {
			request.controllerMethod = 'privateSearch.json';

			request.data.parentAssetEntrySetId = parentId;
		}

		if (this._request) {
			this._request.cancel();
		}

		this._request = sendRequest(request);

		return this._request;
	}

	selectItem_(item) {
		const {editorState} = this.props;

		const {query_, queryStart_} = this.state;

		const selection = this.getNewSelection_(queryStart_, queryStart_ + query_.length);

		const endOfMention = queryStart_ + item.name.length + 1;

		const contentState = Modifier.applyEntity(
			Modifier.replaceText(editorState.getCurrentContent(), selection, query_.substring(0, 1) + item.name),
			this.getNewSelection_(queryStart_, endOfMention),
			Entity.create('MENTION', 'IMMUTABLE', item)
		);

		let newEditorState = EditorState.push(editorState, contentState, 'apply-entity');

		newEditorState = EditorState.forceSelection(
			newEditorState,
			this.getNewSelection_(endOfMention, endOfMention)
		);

		this.setState(
			{
				highlightedIndex_: 0,
				modalOpen_: false,
				open_: false
			},
			() => this.props.onEditorStateChange(newEditorState)
		);
	}

	syncEditorState(newVal, prevVal) {
		const {onMentionsChange} = this.props;

		if (prevVal && !prevVal.getDecorator()) {
			this.addBaseDecorators_();
		}

		const rawEditorContent = convertToRaw(newVal.getCurrentContent());

		let mentions = values(rawEditorContent.entityMap);

		if (mentions.length !== this._totalMentions) {
			mentions = mentions.map(item => item.data);

			this._totalMentions = mentions.length;

			onMentionsChange(mentions);
		}
	}

	render() {
		const {
			editorState,
			onEditorStateChange,
			onFocus,
			onPaste,
			placeholder,
			size
		} = this.props;

		const {autocompleteResults_, highlightedIndex_, open_} = this.state;

		const classNames = getCN(
			'editor-container',
			{
				[`editor-${size}`]: size
			}
		);

		return (
			<div class={classNames}>
				<div class="editor" onClick={this.focus}>
					<Draft
						editorState={editorState}
						handleReturn={this.handleReturn_}
						onBlur={this.handleBlur_}
						onChange={onEditorStateChange}
						onDownArrow={this.handleKeys_}
						onEscape={this.handleKeys_}
						onFocus={onFocus}
						onPasteRawText={onPaste}
						onUpArrow={this.handleKeys_}
						placeholder={placeholder}
						ref="editor"
						spellCheck={true}
						stripPastedStyles={true}
					/>
				</div>

				{open_ &&
					<AutocompleteMenu
						highlightedIndex={highlightedIndex_}
						itemRenderer={this.renderItem_}
						items={autocompleteResults_}
						onClick={this.handleAutocompleteMenuClick_}
						onClickOutside={this.handleAutocompleteMenuClickOutside_}
						onMouseDown={this.handleAutocompleteMenuMouseDown_}
						onMouseEnter={this.handleAutocompleteMenuMouseEnter_}
						ref="acMenu"
						style={this.getMenuStyles_()}
					/>
				}
			</div>
		);
	}
}

Editor.PROPS = {
	autocompleteMenuTopOffset: Config.number().value(22),
	editorState: Config.object().value(EditorState.createEmpty()),
	onBlur: Config.func().value(noop),
	onEditorStateChange: Config.func().value(noop),
	onEnter: Config.func(),
	onExcludedMention: Config.func().value(noop),
	onFocus: Config.func(),
	onMentionsChange: Config.func().value(noop),
	onPaste: Config.func(),
	parentId: Config.number(),
	placeholder: Config.oneOfType(
		[
			Config.array(),
			Config.string()
		]
	),
	size: Config.oneOf(['small', 'large']).value('small')
};

Editor.STATE = {
	autocompleteResults_: Config.value([]),
	highlightedIndex_: Config.value(0),
	modalOpen_: Config.value(false),
	open_: Config.value(false),
	query_: Config.value(''),
	queryStart_: Config.value(null)
};

/**
 * Any component that passes state to <Editor /> needs this as well
 *
 * Metal.js updates the ui asyncronously after state changes by default. This
 * should be fine for most React components, but Draft.js relies on the
 * synchronous updates, probably due to its undo/redo logic. Setting this flag
 * to true makes everything work as expected, making sure that Draft.js doesn't
 * miss any editor state change due to batching.
 */

Editor.SYNC_UPDATES = true;

export default Editor;