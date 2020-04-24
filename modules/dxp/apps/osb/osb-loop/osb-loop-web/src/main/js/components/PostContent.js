import Component, {Config} from 'metal-jsx';
import {Map} from 'immutable';
import {isUndefined} from 'lodash';

import ImageViewer from './ImageViewer';
import LinkPreview from './link-preview';
import LoopConstants from '../lib/loop-constants';
import MarkdownWithOverlay from './MarkdownWithOverlay';
import SeeMore from './SeeMore';

class PostContent extends Component {
	render() {
		const {
			id,
			imageData,
			linkData,
			message,
			messageInfoIMap,
			onSeeMore,
			truncated
		} = this.props;

		const secondaryTruncation = messageInfoIMap.get('truncated');

		return (
			<div class="post-content-container">
				{message &&
					<MarkdownWithOverlay id={id} message={isUndefined(secondaryTruncation) ? message : messageInfoIMap.get('message')} />
				}

				{secondaryTruncation !== false && truncated &&
					<SeeMore
						href={`${LoopConstants.urls.feed}/${id}`}
						messageInfoIMap={messageInfoIMap}
						onSeeMore={onSeeMore}
						truncated={truncated}
					/>
				}

				{imageData && !!imageData.length &&
					<ImageViewer items={imageData} />
				}

				{linkData &&
					<LinkPreview linkData={linkData} />
				}
			</div>
		);
	}
}

PostContent.PROPS = {
	feedId: Config.string(),
	id: Config.number(),
	imageData: Config.array(),
	linkData: Config.object(),
	message: Config.string(),
	messageInfoIMap: Config.instanceOf(Map).value(Map()),
	onSeeMore: Config.func(),
	truncated: Config.bool()
};

export default PostContent;