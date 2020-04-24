import Component from 'metal-jsx';
import {createStore} from 'redux';
import {fromJS, Map} from 'immutable';
import {Provider} from 'metal-redux';

import ElementContainer from './ElementContainer';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import PostMenu from '../PostMenu';

const creatorIMap = fromJS(LoopAssets.getPerson());

const followingPostIMap = fromJS(LoopAssets.getPost(0)).set(
	'following',
	true
);

const notFollowingPostIMap = fromJS(LoopAssets.getPost(1));

const bookmarkedPostIMap = fromJS(LoopAssets.getPost(2)).setIn(
	['payload', 'bookmarked'],
	true
);

const store = createStore(
	s => s,
	fromJS(
		{
			posts: Map().setIn(
				[0, 'data'],
				followingPostIMap
			).setIn(
				[1, 'data'],
				notFollowingPostIMap
			).setIn(
				[2, 'data'],
				bookmarkedPostIMap
			)
		}
	)
);

class PostMenuKit extends Component {
	render() {
		return (
			<Provider store={store}>
				<Kit header="PostMenu">
					<ElementContainer header="Following Post">
						<PostMenu
							creatorIMap={creatorIMap}
							feedId={1}
							postIMap={followingPostIMap}
						/>
					</ElementContainer>

					<ElementContainer header="Not Following Post">
						<PostMenu
							creatorIMap={creatorIMap}
							feedId={1}
							postIMap={notFollowingPostIMap}
						/>
					</ElementContainer>

					<ElementContainer header="Bookmarked Post">
						<PostMenu
							creatorIMap={creatorIMap}
							feedId={1}
							postIMap={bookmarkedPostIMap}
						/>
					</ElementContainer>
				</Kit>
			</Provider>
		);
	}
}

export default PostMenuKit;