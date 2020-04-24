import Component from 'metal-jsx';
import {fromJS} from 'immutable';

import mockStore from '../../tests/mock-store';
import ElementContainer from './ElementContainer';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import PostUndoCard from '../PostUndoCard';

const store = mockStore();

const postIMap = fromJS(LoopAssets.getPost());

class PostUndoCardKit extends Component {
	render() {
		return (
			<Kit card={false} header="PostUndoCard">
				<ElementContainer header="Removed By Unfollowing">
					<PostUndoCard
						feedId={0}
						postIMap={postIMap}
						removedBy="follow"
						store={store}
					/>
				</ElementContainer>

				<ElementContainer header="Removed By Unbookmarking">
					<PostUndoCard
						feedId={0}
						postIMap={postIMap}
						removedBy="bookmark"
						store={store}
					/>
				</ElementContainer>
			</Kit>
		);
	}
}

export default PostUndoCardKit;