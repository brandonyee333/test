import Component from 'metal-jsx';
import {fromJS, Map} from 'immutable';
import {Provider} from 'metal-redux';

import Comment from '../Comment';
import ElementContainer from './ElementContainer';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import LoopConstants from '../../lib/loop-constants';
import mockStore from '../../tests/mock-store';

const currentPersonClassPK = LoopConstants.currentPerson.entityClassPK;

const creatorIMap = fromJS(LoopAssets.getPerson());

const post0 = LoopAssets.getPost(0);

const post1 = LoopAssets.getPost(
	1,
	{
		message: 'onsectetur adipiscing elit. Aliquam bibendum massa sit amet ultri'.repeat(10),
		truncated: true
	}
);

const post2 = LoopAssets.getPost(
	2,
	{
		permissions: {
			deleteAssetEntrySet: false,
			updateAssetEntrySet: false
		}
	}
);

const post3 = LoopAssets.getPost(
	3,
	{
		parentAssetEntrySetId: 1
	}
);

const state = fromJS(
	{
		dirtyState: Map(),
		people: Map().setIn(
			[3, 'data'],
			creatorIMap
		).setIn(
			[currentPersonClassPK, 'data'],
			fromJS(LoopAssets.getPerson(currentPersonClassPK))
		),
		posts: Map().setIn([3, 'data'], fromJS(post3))
	}
);

class CommentKit extends Component {
	render() {
		return (
			<Kit header="Comment">
				<Provider store={mockStore(state)}>
					<ElementContainer header="Normal">
						<Comment
							commentFeedId={0}
							creatorIMap={creatorIMap}
							id={0}
							postIMap={fromJS(post0)}
						/>
					</ElementContainer>

					<ElementContainer header="Long Content">
						<Comment
							commentFeedId={0}
							creatorIMap={creatorIMap}
							id={1}
							postIMap={fromJS(post1)}
						/>
					</ElementContainer>

					<ElementContainer header="No permissions">
						<Comment
							commentFeedId={0}
							creatorIMap={creatorIMap}
							id={2}
							postIMap={fromJS(post2)}
						/>
					</ElementContainer>

					<ElementContainer header="Editing">
						<Comment
							commentFeedId={0}
							creatorIMap={creatorIMap}
							editing_={true}
							id={3}
							postIMap={fromJS(post3)}
						/>
					</ElementContainer>

					<ElementContainer header="Switching To Edit">
						<Comment
							commentFeedId={0}
							creatorIMap={creatorIMap}
							id={3}
							postIMap={fromJS(post3)}
						/>
					</ElementContainer>
				</Provider>
			</Kit>
		);
	}
}

export default CommentKit;