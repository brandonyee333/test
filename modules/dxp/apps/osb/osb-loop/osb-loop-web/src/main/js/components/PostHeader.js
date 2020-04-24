import Component, {Config} from 'metal-jsx';
import {fromJS} from 'immutable';

import Avatar from './Avatar';
import PostHeaderText from './PostHeaderText';

class PostHeader extends Component {
	render() {
		const {
			contentModifiedTime,
			createTime,
			creator,
			displayURL,
			id,
			location,
			sharedTo
		} = this.props;

		return (
			<div class="post-header-container">
				<Avatar entity={creator} size="small" summary={true} />

				<PostHeaderText
					contentModifiedTime={contentModifiedTime}
					createTime={createTime}
					creator={creator}
					entitiesIList={sharedTo && fromJS(sharedTo)}
					id={id}
					location={location}
					url={displayURL}
				/>
			</div>
		);
	}
}

PostHeader.PROPS = {
	contentModifiedTime: Config.number(),
	createTime: Config.number(),
	creator: Config.object().required(),
	displayURL: Config.string(),
	id: Config.number(),
	location: Config.string(),
	sharedTo: Config.array()
};

export default PostHeader;