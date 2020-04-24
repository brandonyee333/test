import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {Map} from 'immutable';

import AnnouncementHeader from './AnnouncementHeader';
import Button from './Button';
import Modal from './modal';
import PostContent from './PostContent';
import PostHeader from './PostHeader';
import PrivatePostBar from './PrivatePostBar';
import sendRequest from '../lib/request';
import {classNameIdToSchema, getPayloadType} from '../lib/util';
import {getRel} from '../lib/selectors';
import {getStickyTime, getUniqueEntityArr} from '../lib/asset-entry-set-utils';

class PostPreview extends Component {
	created() {
		this.handleSubmit_ = this.handleSubmit_.bind(this);
	}

	handleSubmit_() {
		const {hideModal, onSubmit, privateAssetEntrySet} = this.props;

		onSubmit(!privateAssetEntrySet).then(hideModal);
	}

	render() {
		const {
			announcement,
			creatorIMap,
			hideModal,
			id,
			imageData,
			linkData,
			markdown,
			onCancel,
			privateAssetEntrySet,
			sharedTo,
			stickyTime,
			title,
			type
		} = this.props;

		const time = Date.now();

		const creator = creatorIMap.toJS();

		return (
			<div class="post-preview-container">
				<Modal.Body>
					{privateAssetEntrySet &&
						<PrivatePostBar announcement={announcement} />
					}

					{announcement &&
						<AnnouncementHeader
							createTime={time}
							creator={creator}
							id={id}
							sharedTo={sharedTo}
							stickyTime={getStickyTime(stickyTime)}
							title={title}
						/>
					}

					{!announcement &&
						<PostHeader
							createTime={time}
							creator={creator}
							id={id}
							sharedTo={getUniqueEntityArr(sharedTo)}
						/>
					}

					<PostContent
						id={id}
						imageData={type === getPayloadType('image') && imageData ? imageData : []}
						linkData={type === getPayloadType('link') && linkData ? linkData : null}
						message={markdown}
					/>
				</Modal.Body>

				<Modal.Footer>
					<Button onClick={onCancel ? onCancel : hideModal} role="secondary">{Liferay.Language.get('back')}</Button>

					<Button disabled={!markdown} onClick={this.handleSubmit_} role="primary">{id ? Liferay.Language.get('confirm') : Liferay.Language.get('post')}</Button>
				</Modal.Footer>
			</div>
		);
	}
}

const STORE = {
	creatorIMap: Config.instanceOf(Map)
};

PostPreview.PROPS = {
	...STORE,
	announcement: Config.bool(),
	creatorClassNameId: Config.number(),
	creatorId: Config.number(),
	hideModal: Config.func(),
	id: Config.number(),
	imageData: Config.array(),
	linkData: Config.oneOfType(
		[
			Config.object(),
			Config.string()
		]
	),
	markdown: Config.string(),
	message: Config.string(),
	onCancel: Config.func(),
	onSubmit: Config.func(),
	sharedTo: Config.array(),
	stickyTime: Config.number(),
	title: Config.string(),
	type: Config.string()
};

export const ConnectedPreview = connect(
	(state, {creatorId, creatorClassNameId}) => (
		{
			creatorIMap: getRel(classNameIdToSchema(creatorClassNameId), state, creatorId, Map())
		}
	)
)(PostPreview);

export default class PostPreviewContainer extends Component {
	created() {
		this._request = sendRequest(
			{
				controller: 'home',
				controllerMethod: 'viewMarkdownHTML.json',
				data: {
					markdownSource: this.props.message
				}
			}
		).then(
			markdown => {
				const {message} = markdown;

				if (message || message === '') {
					markdown = message;
				}

				this.state.markdown_ = markdown;
			}
		);
	}

	disposed() {
		this._request.cancel();
	}

	render() {
		return <ConnectedPreview {...this.otherProps()} markdown={this.state.markdown_} />;
	}
}

PostPreviewContainer.PROPS = {
	message: Config.string()
};

PostPreviewContainer.STATE = {
	markdown_: Config.value('')
};