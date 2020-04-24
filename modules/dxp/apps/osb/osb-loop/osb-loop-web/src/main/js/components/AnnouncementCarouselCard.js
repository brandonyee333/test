import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {Map} from 'immutable';

import Avatar from './Avatar';
import LoopConstants from '../lib/loop-constants';
import MarkdownContent from './MarkdownContent';
import PostHeaderText from './PostHeaderText';
import TinyLinkPreview from './TinyLinkPreview';
import {ellipsizeText, toJS} from '../lib/util';
import {likePost} from '../actions/posts';
import {postSelector} from '../lib/selectors';

class AnnouncementCarouselCard extends Component {
	created() {
		this.handleLikeToggle_ = this.handleLikeToggle_.bind(this);
	}

	attached() {
		const {title} = this.refs;

		if (title) {
			ellipsizeText(title);
		}
	}

	handleLikeToggle_() {
		const {id, likePost, postIMap} = this.props;

		const liked = postIMap.getIn(['payload', 'likedParticipants', 'liked'], false);

		likePost(id, !liked);
	}

	render() {
		const {creatorIMap, id, postIMap} = this.props;

		const creator = creatorIMap.toJS();

		const createTime = postIMap.get('createTime');
		const location = postIMap.get('location');
		const payloadIMap = postIMap.get('payload');
		const title = postIMap.get('title');

		const imageData = toJS(payloadIMap, 'imageData');
		const linkData = toJS(payloadIMap, 'linkData');

		const contentModifiedTime = payloadIMap.get('contentModifiedTime');
		const message = payloadIMap.get('message');

		const postURL = `${LoopConstants.urls.feed}/${id}`;

		const image = imageData && !!imageData.length;

		return (
			<div class="announcement-carousel-card-container">
				<div class="content">
					<div class="text">
						<a class="title" href={postURL} ref="title" title={title}>{title}</a>

						<MarkdownContent message={message} />

						<a class="read-more" href={postURL}>{Liferay.Language.get('see-more')}</a>
					</div>

					{image &&
						<a class="photo" href={postURL} style={{backgroundImage: `url(${imageData[0].imageURL_web})`}} />
					}

					{linkData &&
						<TinyLinkPreview linkData={linkData} />
					}
				</div>

				<div class="post-info">
					<Avatar entity={creator} size="smallest" summary={true} />

					<PostHeaderText
						contentModifiedTime={contentModifiedTime}
						createTime={createTime}
						creator={creator}
						id={id}
						location={location}
						nowrap={true}
						privateAnnouncement={postIMap.get('privateAssetEntrySet')}
						url={postURL}
						verbiage={Liferay.Language.get('x-shared-an-announcement-with-x')}
					/>
				</div>
			</div>
		);
	}
}

const STORE = {
	creatorIMap: Config.instanceOf(Map),
	likePost: Config.func(),
	postIMap: Config.instanceOf(Map)
};

AnnouncementCarouselCard.PROPS = {
	...STORE,
	id: Config.number(),
	likePost: Config.func()
};

export default connect(
	postSelector,
	{
		likePost
	}
)(AnnouncementCarouselCard);