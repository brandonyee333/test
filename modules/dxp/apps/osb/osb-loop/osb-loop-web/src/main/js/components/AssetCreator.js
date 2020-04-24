import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {bindAll} from 'lodash';
import {connect} from 'metal-redux';
import {Map} from 'immutable';

import Card from './card';
import Creator, {createEmptyPostData} from './creator';
import EditorSpinner from './EditorSpinner';
import Icon from './Icon';
import {addAlert, alertTypes} from '../actions/alerts';
import {convertToText, processPostData} from '../lib/asset-entry-set-utils';
import {createPost} from '../actions/posts';
import {hotKeyManager} from '../lib/HotKeyManager';
import {modalTypes, showModal} from '../actions/modals';

import {
	classNameIdToSchema,
	streamIdToKeys,
	streamNameToId,
	toJS
} from '../lib/util';

class AssetCreator extends Component {
	created() {
		bindAll(
			this,
			'handleDataChange_',
			'handlePreview_',
			'handleSubmit_',
			'maximize_',
			'toggleMinimize_'
		);

		this.state.postData_ = this.getEmptyState_();
	}

	attached() {
		const {geolocation} = navigator;

		if (geolocation) {
			geolocation.getCurrentPosition(
				position => {
					const {latitude, longitude} = position.coords;

					this._geolocation = {
						latitude,
						longitude
					};
				}
			);
		}

		hotKeyManager.bind(
			{
				action: event => {
					event.preventDefault();

					this.maximize_();

					this.components.creator.components.child.focus();
				},
				definition: Liferay.Language.get('create-a-new-post'),
				keys: 'c'
			}
		);
	}

	detached() {
		hotKeyManager.unbind('c');
	}

	getEmptyState_() {
		const {fixedInputListItem, streamId} = this.props;

		return createEmptyPostData(
			{
				announcement: streamId === streamNameToId('announcements'),
				fixedInputListItem: toJS(fixedInputListItem),
				privateAssetEntrySet: streamId === streamNameToId('privatePosts')
			}
		);
	}

	handleAnnouncementTitleAlert_() {
		this.state.requireTitleHighlight_ = true;

		this.props.addAlert(
			{
				alertType: alertTypes.ERROR,
				message: Liferay.Language.get('title-is-required-for-announcements')
			}
		);
	}

	handleCreatePostAction_() {
		const {addAlert, createPost, streamId} = this.props;

		this.state.loading_ = true;

		const processedData = this.processData_();

		return createPost(
			{
				...processedData,
				streamId
			}
		).then(
			() => this.setState(
				{
					loading_: false,
					postData_: this.getEmptyState_()
				}
			)
		).catch(
			({message}) => {
				this.state.loading_ = false;

				let alertMessage = this.state.postData_.announcement ? Liferay.Language.get('oops-there-was-an-error-creating-this-announcement') : Liferay.Language.get('oops-there-was-an-error-creating-this-post');

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

	handleDataChange_(newState) {
		this.state.postData_ = newState;
	}

	handlePreview_() {
		const {
			announcement,
			creatorClassNameId,
			creatorClassPK,
			editorState,
			imageData,
			linkData,
			privateAssetEntrySet,
			sharedTo,
			stickyTime,
			title,
			type
		} = this.state.postData_;

		this.props.showModal(
			{
				modalProps: {
					announcement,
					creatorClassNameId,
					creatorId: creatorClassPK,
					imageData: imageData.map(item => JSON.parse(item.response).data),
					linkData,
					message: convertToText(editorState),
					onSubmit: this.handleSubmit_.bind(this),
					privateAssetEntrySet,
					sharedTo,
					stickyTime,
					title,
					type
				},
				modalType: modalTypes.POST_PREVIEW
			}
		);
	}

	handleSubmit_() {
		const {announcement, title} = this.state.postData_;

		let retVal = Promise.resolve();

		if (announcement && !title) {
			this.handleAnnouncementTitleAlert_();
		}
		else {
			retVal = this.handleCreatePostAction_();
		}

		return retVal;
	}

	maximize_() {
		this.state.minimized_ = false;
	}

	processData_() {
		const {
			editorState,
			...other
		} = this.state.postData_;

		return processPostData(
			{
				...other,
				textContent: convertToText(editorState)
			},
			this._geolocation
		);
	}

	syncStreamId(streamId) {
		this.handleDataChange_(
			{
				...this.state.postData_,
				announcement: streamId === streamNameToId('announcements'),
				privateAssetEntrySet: streamId === streamNameToId('privatePosts')
			}
		);
	}

	toggleMinimize_() {
		this.state.minimized_ = !this.state.minimized_;
	}

	render() {
		const {
			loading_,
			minimized_,
			postData_,
			requireTitleHighlight_
		} = this.state;

		const classNames = getCN(
			'asset-creator-container',
			{
				loading: loading_,
				minimized: minimized_,
				'private-post': postData_.privateAssetEntrySet
			}
		);

		return (
			<Card elementClasses={classNames}>
				{loading_ &&
					<EditorSpinner key="mask" />
				}

				<div key="creatorContainer" onClick={this.maximize_}>
					<Creator
						fixedInputListItem={toJS(this.props.fixedInputListItem)}
						forceAnnouncement={this.props.streamId === streamNameToId('announcements')}
						forcePrivate={this.props.streamId === streamNameToId('privatePosts')}
						onDrop={this.maximize_}
						onPostDataChange={this.handleDataChange_}
						onPreview={this.handlePreview_}
						onSubmit={this.handleSubmit_}
						postData={postData_}
						ref="creator"
						requireTitleHighlight={requireTitleHighlight_}
						submitButtonTitle={Liferay.Language.get('post')}
					/>
				</div>

				<div class="minimizer" key="minimizer" onClick={this.toggleMinimize_}>
					<Icon name="plus" size="small" />
				</div>
			</Card>
		);
	}
}

const STORE = {
	createPost: Config.func(),
	fixedInputListItem: Config.instanceOf(Map),
	showModal: Config.func()
};

AssetCreator.PROPS = {
	...STORE,
	streamId: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	)
};

AssetCreator.STATE = {
	loading_: Config.value(false),
	minimized_: Config.value(true),
	postData_: Config.value({}),
	requireTitleHighlight_: Config.value(false)
};

AssetCreator.SYNC_UPDATES = true;

export default connect(
	(state, ownProps) => {
		let fixedInputListItem = null;

		const {classNameId, classPK} = streamIdToKeys(ownProps.streamId);

		if (classNameId) {
			fixedInputListItem = state.getIn(
				[classNameIdToSchema(classNameId), classPK, 'data']
			);
		}

		return {fixedInputListItem};
	},
	{
		addAlert,
		createPost,
		showModal
	}
)(AssetCreator);