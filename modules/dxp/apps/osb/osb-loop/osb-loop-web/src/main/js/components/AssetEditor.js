import Component, {Config} from 'metal-jsx';
import moment from 'moment';
import Promise from 'metal-promise';
import {bindAll} from 'lodash';
import {connect} from 'metal-redux';

import Creator, {createEmptyPostData} from './creator';
import EditorSpinner from './EditorSpinner';
import PostPreview from './PostPreview';
import {addAlert, alertTypes} from '../actions/alerts';
import {getPostType} from '../lib/util';
import {hideModal} from '../actions/modals';
import {updatePost} from '../actions/posts';

import {
	convertToText,
	parseRemovableEntities,
	processPostData,
	processToEditorState
} from '../lib/asset-entry-set-utils';

class AssetEditor extends Component {
	created() {
		bindAll(
			this,
			'handlePostDataChange_',
			'handlePreview_',
			'handlePreviewCancel_',
			'handleSubmit_'
		);
	}

	attached() {
		this.setPostData_();
	}

	getPostData_() {
		let {linkInputValue} = this.state.postData_;

		const {
			imageData,
			linkData,
			...other
		} = this.state.postData_;

		if (!linkInputValue) {
			linkInputValue = linkData ? linkData.url : '';
		}

		return {
			...other,
			imageData: imageData || [],
			linkData,
			linkInputValue
		};
	}

	getTextContent_() {
		return convertToText(this.state.postData_.editorState);
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

	handlePostDataChange_(data) {
		this.state.postData_ = data;
	}

	handlePreview_() {
		this.state.showPreview_ = true;
	}

	handlePreviewCancel_() {
		this.state.showPreview_ = false;
	}

	handleSubmit_() {
		const {announcement, title} = this.state.postData_;

		let retVal = Promise.resolve();

		if (announcement && !title) {
			this.handleAnnouncementTitleAlert_();
		}
		else {
			retVal = this.handleUpdatePostAction_();
		}

		return retVal;
	}

	handleUpdatePostAction_() {
		const {
			addAlert,
			feedId,
			onSubmit,
			updatePost
		} = this.props;

		this.state.loading_ = true;

		const processedPostData = processPostData(
			{
				...this.state.postData_,
				textContent: this.getTextContent_()
			}
		);

		processedPostData.streamId = feedId;

		return updatePost(processedPostData).then(onSubmit).catch(
			({message}) => {
				this.state.loading_ = false;

				let alertMessage = this.state.postData_.announcement ? Liferay.Language.get('oops-there-was-an-error-editing-this-announcement') : Liferay.Language.get('oops-there-was-an-error-editing-this-post');

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

	setPostData_() {
		const {
			assetEntrySetId,
			payload,
			stickyTime,
			type,
			...other
		} = this.props.post;

		const {rawMessage, sharedTo, ...payloadOther} = payload;

		const editorState = processToEditorState(rawMessage, sharedTo);

		const stickyDays = moment(stickyTime).diff(moment(), 'days');

		this.setState(
			{
				loading_: false,
				postData_: {
					...other,
					...payloadOther,
					announcement: getPostType('announcement') === type,
					editorState,
					id: assetEntrySetId,
					sendEmailNotifications: false,
					sharedTo: parseRemovableEntities(sharedTo, editorState),
					stickyTime: stickyDays ? stickyDays + 1 : 0
				},
				requireTitleHighlight_: false,
				showPreview_: false
			}
		);
	}

	render() {
		const {
			loading_,
			postData_,
			requireTitleHighlight_,
			showPreview_
		} = this.state;

		const {creatorClassNameId, creatorClassPK} = postData_;

		return (
			<div>
				{loading_ &&
					<EditorSpinner key="mask" />
				}

				{showPreview_ &&
					<PostPreview
						{...postData_}
						creatorClassNameId={creatorClassNameId}
						creatorId={creatorClassPK}
						message={this.getTextContent_()}
						onCancel={this.handlePreviewCancel_}
						onSubmit={this.handleSubmit_}
					/>
				}

				<div class={showPreview_ ? 'hide' : 'asset-creator'} key="creator">
					<Creator
						editing={true}
						onPostDataChange={this.handlePostDataChange_}
						onPreview={this.handlePreview_}
						onSubmit={this.handleSubmit_}
						postData={this.getPostData_()}
						requireTitleHighlight={requireTitleHighlight_}
						showPrivateToggle={false}
						submitButtonTitle={Liferay.Language.get('confirm')}
					/>
				</div>
			</div>
		);
	}
}

const STORE = {
	addAlert: Config.func(),
	hideModal: Config.func(),
	updatePost: Config.func()
};

AssetEditor.PROPS = {
	...STORE,
	feedId: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	),
	onSubmit: Config.func(),
	post: Config.object()
};

AssetEditor.STATE = {
	loading_: Config.value(true),
	postData_: Config.value(createEmptyPostData()),
	requireTitleHighlight_: Config.value(false),
	showPreview_: Config.value(false)
};

AssetEditor.SYNC_UPDATES = true;

export default connect(
	null,
	{
		addAlert,
		hideModal,
		updatePost
	}
)(AssetEditor);