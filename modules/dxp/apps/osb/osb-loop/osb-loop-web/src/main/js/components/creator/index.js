import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {bindAll, noop, uniqueId} from 'lodash';

import AnnouncementTitle from './AnnouncementTitle';
import AnnouncementToolbar from '../AnnouncementToolbar';
import DropTarget, {DRAG_TYPES} from '../DropTarget';
import Editor, {createEmptyEditorState, PLACEHOLDER_MAP} from '../Editor';
import ImageSorter from '../ImageSorter';
import InputList from '../InputList';
import LinkPreviewEditor from '../LinkPreviewEditor';
import LoopConstants from '../../lib/loop-constants';
import MarkdownSupportedIcon from '../MarkdownSupportedIcon';
import PrivatePostBar from '../PrivatePostBar';
import sendRequest from '../../lib/request';
import Toolbar from './Toolbar';
import {addDirtyState, removeDirtyState} from '../../actions/dirty-state';
import {getPayloadType, isValidURL} from '../../lib/util';
import {fetchLeadingDivisions} from '../../actions/people';
import {getUniqueEntityArr} from '../../lib/asset-entry-set-utils';

export function createEmptyPostData({announcement, fixedInputListItem, privateAssetEntrySet} = {}) {
	const sharedTo = [];

	if (fixedInputListItem) {
		sharedTo.push(
			{
				...fixedInputListItem,
				fixed: true
			}
		);
	}

	return {
		announcement: !!announcement,
		creatorClassNameId: LoopConstants.currentPerson.entityClassNameId,
		creatorClassPK: LoopConstants.currentPerson.entityClassPK,
		editorState: createEmptyEditorState(),
		imageData: [],
		linkData: null,
		linkInputValue: '',
		privateAssetEntrySet,
		sendEmailNotifications: true,
		sharedTo,
		stickyTime: 1,
		title: '',
		type: getPayloadType('text')
	};
}

export class Creator extends Component {
	created() {
		this._id = uniqueId();

		bindAll(
			this,
			'handleAnnouncementChange_',
			'handleEditorMentions_',
			'handleEditorPaste_',
			'handleEditorStateChange_',
			'handleFileDrop_',
			'handleImageDataChange_',
			'handleLeadingDivisionsRequest_',
			'handleLinkDataChange_',
			'handleLinkPreviewEditorValueChange_',
			'handleNewPostData_',
			'handlePrivatePostToggle_',
			'handleSharedToChange_',
			'handleTitleChange_',
			'handleTypeChange_',
			'search_'
		);
	}

	rendered() {
		const {
			addDirtyState,
			dirtyState,
			postData,
			removeDirtyState
		} = this.props;

		const {editorState, imageData, linkData} = postData;

		const id = this._id;

		const hasContent = editorState.getCurrentContent().hasText() || (imageData && imageData.length) || linkData;

		const exists = dirtyState.contains(id);

		if (!exists && hasContent || exists && !hasContent) {
			const fn = hasContent ? addDirtyState : removeDirtyState;

			fn(id);
		}
	}

	disposed() {
		const {dirtyState, removeDirtyState} = this.props;

		if (dirtyState.contains(this._id)) {
			removeDirtyState(this._id);
		}
	}

	focus() {
		this.components.editor.focus();
	}

	getPlaceholder_() {
		return PLACEHOLDER_MAP[this.props.postData.type];
	}

	handleAnnouncementChange_(announcement) {
		this.handleNewPostData_({announcement});
	}

	handleEditorMentions_(mentions) {
		let {sharedTo} = this.props.postData;

		const inputMentions = sharedTo.filter(item => item.removable || item.fixed);

		sharedTo = [...getUniqueEntityArr(mentions), ...inputMentions];

		this.handleNewPostData_({sharedTo});
	}

	handleEditorPaste_(pastedText) {
		const {linkData, type} = this.props.postData;

		if (!linkData && isValidURL(pastedText) && type !== getPayloadType('image')) {
			this.handleNewPostData_(
				{
					linkInputValue: pastedText,
					type: 'link'
				}
			);
		}
	}

	handleEditorStateChange_(editorState) {
		this.handleNewPostData_({editorState});
	}

	handleFileDrop_(event) {
		const {files} = event.dataTransfer;

		this.refs.toolbar.components.child.addFiles(files);

		this.props.onDrop();
	}

	handleImageDataChange_(imageData) {
		const {type} = this.props.postData;

		const retStateObj = {imageData};

		if (retStateObj.imageData.length === 0 && type === getPayloadType('image')) {
			retStateObj.type = getPayloadType('text');
		}
		else {
			retStateObj.type = getPayloadType('image');
		}

		this.handleNewPostData_(retStateObj);
	}

	handleLeadingDivisionsRequest_() {
		return this.props.fetchLeadingDivisions(LoopConstants.currentPerson.entityClassPK);
	}

	handleLinkDataChange_(linkData) {
		const data = {linkData};

		if (!linkData) {
			data.linkInputValue = '';
		}

		this.handleNewPostData_(data);
	}

	handleLinkPreviewEditorValueChange_(linkInputValue) {
		if (this.props.postData.linkInputValue !== linkInputValue) {
			this.handleNewPostData_({linkInputValue});
		}
	}

	handleNewPostData_(values) {
		const {onPostDataChange, postData} = this.props;

		onPostDataChange({...postData, ...values});
	}

	handlePrivatePostToggle_(privateAssetEntrySet) {
		const {onPostDataChange, postData} = this.props;

		onPostDataChange({...postData, ...{privateAssetEntrySet}});
	}

	handleSharedToChange_(sharedToMentions = []) {
		let {sharedTo} = this.props.postData;

		const editorMentions = sharedTo.filter(item => !(item.removable || item.fixed));

		sharedTo = [...editorMentions, ...getUniqueEntityArr(sharedToMentions)];

		this.handleNewPostData_({sharedTo});
	}

	handleTitleChange_(title) {
		this.handleNewPostData_({title});
	}

	handleTypeChange_(type) {
		type = getPayloadType(type);

		if (this.props.postData.type !== type) {
			let newData = {type};

			if (type === getPayloadType('text')) {
				newData = {
					...newData,
					imageData: [],
					linkData: null,
					linkInputValue: ''
				};
			}

			this.handleNewPostData_(newData);
		}
	}

	hasContent_() {
		const {editorState, imageData, linkData} = this.props.postData;

		return (imageData && imageData.length) || linkData || editorState.getCurrentContent().hasText();
	}

	search_(input) {
		return sendRequest(
			{
				controller: 'home',
				controllerMethod: 'search.json',
				data: {
					keywords: input,
					searchLimit: 5
				}
			}
		);
	}

	render() {
		const {
			editing,
			forceAnnouncement,
			forcePrivate,
			onPreview,
			onSubmit,
			postData,
			requireTitleHighlight,
			showPrivateToggle,
			submitButtonTitle,
			titleCharacterLimit
		} = this.props;

		const {
			announcement,
			creatorClassNameId,
			creatorClassPK,
			editorState,
			imageData,
			linkData,
			linkInputValue,
			privateAssetEntrySet,
			sendEmailNotifications,
			sharedTo,
			stickyTime,
			title,
			type
		} = postData;

		const typeIsImage = type === getPayloadType('image');
		const typeIsLink = type === getPayloadType('link');

		const titleTooLong = !!(announcement && title && title.length > titleCharacterLimit);

		const dropTargetEnter = editing ? document : document.querySelector(`#portlet_${LoopConstants.portletId}`);

		const imageUploading = imageData.some(({completed, progress}) => progress && !completed);

		return (
			<div class="creator-container">
				{privateAssetEntrySet &&
					<PrivatePostBar announcement={announcement} key="privatePostBar" />
				}

				{announcement &&
					<AnnouncementTitle
						key="announcementTitle"
						onTitleChange={this.handleTitleChange_}
						requireTitleHighlight={requireTitleHighlight}
						title={title}
						titleCharacterLimit={titleCharacterLimit}
						titleTooLong={titleTooLong}
					/>
				}

				<DropTarget
					container={dropTargetEnter || document}
					dragMessage={Liferay.Language.get('drag-a-photo-here')}
					hoverMessage={Liferay.Language.get('drop-photo')}
					key="dropTarget"
					onDrop={this.handleFileDrop_}
					targetType={DRAG_TYPES.FILE}
				>
					<div class="creator-content">
						<Editor
							autocompleteMenuTopOffset={44}
							editorState={editorState}
							key="editor"
							onEditorStateChange={this.handleEditorStateChange_}
							onMentionsChange={this.handleEditorMentions_}
							onPaste={this.handleEditorPaste_}
							placeholder={this.getPlaceholder_()}
							ref="editor"
						/>

						{typeIsImage && imageData && !!imageData.length &&
							<ImageSorter images={imageData} key="imageSorter" onChange={this.handleImageDataChange_} />
						}

						{typeIsLink &&
							<LinkPreviewEditor
								inputValue={linkInputValue}
								key="linkPreview"
								linkData={linkData}
								onDataChange={this.handleLinkDataChange_}
								onInputChange={this.handleLinkPreviewEditorValueChange_}
							/>
						}

						<InputList
							dataSource={this.search_}
							elementClasses="creator"
							inlineLabel={true}
							items={getUniqueEntityArr(sharedTo)}
							label={Liferay.Language.get('to')}
							onChange={this.handleSharedToChange_}
							placeholder={Liferay.Language.get('share-with-people-groups-or-topics')}
							ref="inputList"
						/>

						<MarkdownSupportedIcon openNewWindow={editing} />
					</div>

					{announcement &&
						<AnnouncementToolbar
							dataRequest={this.handleLeadingDivisionsRequest_}
							editing={editing}
							key="announcementToolbar"
							onChange={this.handleNewPostData_}
							postAsClassNameId={creatorClassNameId}
							postAsId={creatorClassPK}
							sendEmailNotifications={sendEmailNotifications}
							stickyTime={stickyTime}
						/>
					}

					<Toolbar
						announcement={announcement}
						disableSubmit={!this.hasContent_() || titleTooLong || imageUploading}
						forceAnnouncement={forceAnnouncement}
						forcePrivate={forcePrivate}
						imageData={imageData}
						key="toolbar"
						onAnnouncementChange={this.handleAnnouncementChange_}
						onDataChange={this.handleNewPostData_}
						onImageDataChange={this.handleImageDataChange_}
						onPreview={onPreview}
						onPrivatePostToggle={this.handlePrivatePostToggle_}
						onSubmit={onSubmit}
						onTypeChange={this.handleTypeChange_}
						privateAssetEntrySet={privateAssetEntrySet}
						ref="toolbar"
						showPrivateToggle={showPrivateToggle}
						submitButtonTitle={submitButtonTitle}
						type={type}
					/>
				</DropTarget>
			</div>
		);
	}
}

const STORE = {
	addDirtyState: Config.func(),
	dirtyState: Config.object(),
	fetchLeadingDivisions: Config.func(),
	removeDirtyState: Config.func()
};

Creator.PROPS = {
	...STORE,
	editing: Config.bool(),
	forceAnnouncement: Config.bool(),
	forcePrivate: Config.bool(),
	onDrop: Config.func().value(noop),
	onPostDataChange: Config.func(),
	onPreview: Config.func(),
	onSubmit: Config.func(),
	postData: Config.object(),
	requireTitleHighlight: Config.bool(),
	showPrivateToggle: Config.bool().value(true),
	submitButtonTitle: Config.string(),
	titleCharacterLimit: Config.number().value(82)
};

Creator.SYNC_UPDATES = true;

export default connect(
	state => (
		{
			dirtyState: state.get('dirtyState')
		}
	),
	{
		addDirtyState,
		fetchLeadingDivisions,
		removeDirtyState
	}
)(Creator);