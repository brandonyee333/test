import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {connect} from 'metal-redux';
import {EditorState, Entity, Modifier} from 'draft-js';
import {List, Map} from 'immutable';

import {
	bindAll,
	isNumber,
	isPlainObject,
	uniqueId
} from 'lodash';

import Avatar from './Avatar';
import DropTarget, {DRAG_TYPES} from './DropTarget';
import Editor, {createEmptyEditorState} from './Editor';
import EntityLink from './EntityLink';
import FileUploader, {IMAGE_FILE_TYPES} from '../lib/FileUploader';
import Icon from './Icon';
import ImageSorter from './ImageSorter';
import InlineButton from './InlineButton';
import LoopConstants from '../lib/loop-constants';
import Spinner from './Spinner';
import {addAlert, alertTypes} from '../actions/alerts';
import {addDirtyState, removeDirtyState} from '../actions/dirty-state';
import {convertToText, processPostData, processToEditorState} from '../lib/asset-entry-set-utils';
import {getCurrentPerson, getPost, getSharedTo} from '../lib/selectors';
import {lang} from '../lib/lang-util';
import {modalTypes, showModal} from '../actions/modals';

class CommentCreator extends Component {
	created() {
		this._id = uniqueId();

		bindAll(
			this,
			'handleBlur_',
			'handleEditorStateChange_',
			'handleError_',
			'handleExcludedMention_',
			'handleFileDrop_',
			'handleFocus_',
			'handleImageDataChange_',
			'handleImageUpload_',
			'handleMentionsChange_',
			'handleSubmit_'
		);

		this._uploader = new FileUploader(
			{
				fileTypes: IMAGE_FILE_TYPES,
				multiple: false,
				onChange: this.handleImageDataChange_,
				onError: this.handleError_,
				uploadURL: `${LoopConstants.urls.feed}/upload.json`
			}
		).render();
	}

	attached() {
		const {id, postIMap, sharedToIList} = this.props;

		if (id) {
			const {imageData, rawMessage} = postIMap.get('payload').toJS();

			const sharedTo = sharedToIList.toJS();

			const initialState = {
				editorState_: processToEditorState(rawMessage, sharedTo),
				sharedTo_: sharedTo
			};

			if (imageData && imageData.length) {
				initialState.imageData_ = imageData;
			}

			this.setState(initialState);
		}
	}

	disposed() {
		const {id, removeDirtyState} = this.props;

		if (id) {
			removeDirtyState(this._id);
		}

		if (this._uploader && this._uploader.destroy) {
			this._uploader.destroy();
		}
	}

	rendered() {
		const {addDirtyState, dirtyState, removeDirtyState} = this.props;

		const {imageData_} = this.state;

		const id = this._id;

		const hasContent = this.hasText() || (imageData_ && imageData_.length);

		const exists = dirtyState.contains(id);

		if (!exists && hasContent || exists && !hasContent) {
			const fn = hasContent ? addDirtyState : removeDirtyState;

			fn(id);
		}
	}

	detached() {
		const {dirtyState, removeDirtyState} = this.props;

		if (dirtyState.contains(this._id)) {
			removeDirtyState(this._id);
		}
	}

	focus() {
		this.refs.editor.focus();

		const {bottom} = this.element.getBoundingClientRect();

		if (bottom > window.innerHeight) {
			this.element.scrollIntoView(
				false,
				{behavior: 'smooth'}
			);
		}
	}

	getCurrentContent_() {
		return this.state.editorState_.getCurrentContent();
	}

	getNewSelection_(anchorOffset, focusOffset) {
		let selection = this.state.editorState_.getSelection();

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

	handleBlur_() {
		this.state.focus_ = false;
	}

	handleEditorStateChange_(editorState) {
		this.state.editorState_ = editorState;
	}

	handleError_(message) {
		this.props.addAlert(
			{
				alertType: alertTypes.ERROR,
				message
			}
		);
	}

	handleExcludedMention_(entity, confirmCallback, cancelCallback) {
		const name = <EntityLink disabled={true} entity={entity} />;

		this.props.showModal(
			{
				modalProps: {
					message: lang(Liferay.Language.get('are-you-sure-you-want-to-include-x-in-this-private-post-this-post-is-not-already-shared-to-x'), [name]),
					onCancel: cancelCallback,
					onConfirm: confirmCallback
				},
				modalType: modalTypes.CONFIRM_DIALOG
			}
		);
	}

	handleFileDrop_(event) {
		const {files} = event.dataTransfer;

		this._uploader.addFiles(files);
	}

	handleFocus_() {
		this.state.focus_ = true;
	}

	handleMentionsChange_(sharedTo) {
		this.state.sharedTo_ = sharedTo;
	}

	handleImageDataChange_(imageData) {
		let retVal = imageData;

		if (isPlainObject(imageData)) {
			retVal = [
				{
					...this.state.imageData_[0],
					...imageData
				}
			];
		}

		this.state.imageData_ = retVal;
	}

	handleImageUpload_() {
		this._uploader.openDialog();
	}

	handleSubmit_(event) {
		const {altKey, shiftKey} = event;

		const {
			addAlert,
			commentFeedId,
			id,
			parentId,
			submitMethod
		} = this.props;

		const {editorState_, imageData_, sharedTo_} = this.state;

		let retVal = true;

		const finishedUploading = !imageData_.some(
			image => image.progress && !image.completed
		);

		if (!(altKey || shiftKey) && (this.hasText() || imageData_.length) && finishedUploading) {
			const processedPostData = processPostData(
				{
					id: id || null,
					imageData: imageData_,
					parentAssetEntrySetId: parentId,
					sharedTo: sharedTo_,
					textContent: convertToText(editorState_),
					type: imageData_.length ? 'image' : 'text'
				}
			);

			this.state.loading_ = true;

			submitMethod(
				{
					...processedPostData,
					streamId: commentFeedId
				}
			).then(
				response => {
					if (response) {
						this.setState(
							{
								editorState_: createEmptyEditorState(),
								imageData_: [],
								loading_: false,
								sharedTo_: []
							}
						);
					}
				}
			).catch(
				({message}) => {
					this.state.loading_ = false;

					let alertMessage = Liferay.Language.get('oops-there-was-an-error-creating-this-comment');

					if (message) {
						alertMessage = `${alertMessage} ${message}`;
					}

					addAlert(
						{
							alertType: alertTypes.ERROR,
							message: alertMessage
						}
					);
				}
			);
		}

		if (altKey || shiftKey) {
			retVal = false;
		}

		return retVal;
	}

	hasText() {
		return this.getCurrentContent_().hasText();
	}

	insertMention(entityIMap) {
		if (!this.hasText()) {
			const entity = entityIMap.toJS();

			const mentionText = `@${entity.name}`;
			const mentionTextWithSpace = `${mentionText} `;

			const contentState = Modifier.applyEntity(
				Modifier.insertText(this.getCurrentContent_(), this.getNewSelection_(0, 0), mentionTextWithSpace),
				this.getNewSelection_(0, mentionText.length),
				Entity.create('MENTION', 'IMMUTABLE', entity)
			);

			let newEditorState = EditorState.push(this.state.editorState_, contentState, 'apply-entity');

			const mentionTextWithSpaceLength = mentionTextWithSpace.length;

			newEditorState = EditorState.forceSelection(
				newEditorState,
				this.getNewSelection_(mentionTextWithSpaceLength, mentionTextWithSpaceLength)
			);

			this.handleEditorStateChange_(newEditorState);
		}
	}

	render() {
		const {
			creatorIMap,
			currentPersonIMap,
			onCancelClick,
			parentId,
			placeholder,
			privateAssetEntrySet
		} = this.props;

		const {
			editorState_,
			focus_,
			imageData_,
			loading_
		} = this.state;

		const creator = creatorIMap || currentPersonIMap;

		const commentInputClassnames = getCN(
			'comment-input',
			{
				focus: focus_
			}
		);

		return (
			<div class="comment-creator-container">
				<div class="comment-control">
					<Avatar entity={creator.toJS()} size="smallest" />
				</div>

				<DropTarget
					container={document.querySelector(`#portlet_${LoopConstants.portletId}`) || document}
					dragMessage={Liferay.Language.get('drag-a-photo-here')}
					elementClasses={commentInputClassnames}
					hoverMessage={Liferay.Language.get('drop-photo')}
					onDrop={this.handleFileDrop_}
					targetType={DRAG_TYPES.FILE}
				>
					<Editor
						autocompleteMenuTopOffset={26}
						editorState={editorState_}
						onBlur={this.handleBlur_}
						onEditorStateChange={this.handleEditorStateChange_}
						onEnter={this.handleSubmit_}
						onExcludedMention={this.handleExcludedMention_}
						onFocus={this.handleFocus_}
						onMentionsChange={this.handleMentionsChange_}
						parentId={privateAssetEntrySet ? parentId : null}
						placeholder={placeholder}
						ref="editor"
						size="small"
					/>

					{imageData_ && !!imageData_.length &&
						<ImageSorter
							display="mini"
							images={imageData_}
							onChange={this.handleImageDataChange_}
						/>
					}

					{loading_ &&
						<div class="comment-loading-mask" key="mask">
							<Spinner />
						</div>
					}
				</DropTarget>

				<div class="comment-control">
					<Icon elementClasses="upload-image" name="camera" onClick={this.handleImageUpload_} />

					{onCancelClick &&
						<InlineButton elementClasses="cancel-edit" onClick={onCancelClick}>
							{Liferay.Language.get('cancel')}
						</InlineButton>
					}
				</div>
			</div>
		);
	}
}

const STORE = {
	addAlert: Config.func(),
	addDirtyState: Config.func(),
	creatorIMap: Config.instanceOf(Map),
	currentPersonIMap: Config.instanceOf(Map),
	dirtyState: Config.object(),
	postIMap: Config.instanceOf(Map),
	removeDirtyState: Config.func(),
	sharedToIList: Config.instanceOf(List),
	showModal: Config.func()
};

CommentCreator.PROPS = {
	...STORE,
	commentFeedId: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	),
	id: Config.number(),
	parentId: Config.number(),
	placeholder: Config.string().value(Liferay.Language.get('write-a-comment')),
	privateAssetEntrySet: Config.bool(),
	submitMethod: Config.func()
};

CommentCreator.STATE = {
	editorState_: Config.value(createEmptyEditorState()),
	focus_: Config.value(false),
	imageData_: Config.value([]),
	loading_: Config.value(false),
	sharedTo_: Config.value([])
};

CommentCreator.SYNC_UPDATES = true;

export default connect(
	(state, ownProps) => (
		{
			...getPost(state, ownProps.id),
			currentPersonIMap: getCurrentPerson(state),
			dirtyState: state.get('dirtyState'),
			sharedToIList: getSharedTo(state, ownProps.id)
		}
	),
	{
		addAlert,
		addDirtyState,
		removeDirtyState,
		showModal
	}
)(CommentCreator);