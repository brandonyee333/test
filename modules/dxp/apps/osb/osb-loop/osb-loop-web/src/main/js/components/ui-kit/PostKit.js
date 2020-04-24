import Component from 'metal-jsx';
import {fromJS, List, Map, Range} from 'immutable';
import {Provider} from 'metal-redux';
import {range} from 'lodash';

import ElementContainer from './ElementContainer';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import LoopConstants from '../../lib/loop-constants';
import mockStore from '../../tests/mock-store';
import Post from '../Post';

const currentPersonClassPK = LoopConstants.currentPerson.entityClassPK;

const post1 = {
	...LoopAssets.getPost(
		1,
		{
			likedParticipants: {
				liked: false,
				participants: List()
			},
			sharedTo: [LoopAssets.getPerson(1)]
		}
	),
	assetEntrySetLikesCount: 0,
	childAssetEntrySetsCount: 0
};

const post2 = {
	...LoopAssets.getPost(
		2,
		{
			bookmarked: true,
			title: 'Announcement Title'
		}
	),
	childAssetEntrySetsCount: 3,
	type: 1
};

const post3 = {
	...LoopAssets.getPost(
		3,
		{title: 'Announcement Title'}
	),
	childAssetEntrySetsCount: 0,
	type: 1
};

const post4 = {
	...LoopAssets.getPost(
		4,
		{
			likedParticipants: {
				liked: false,
				participants: List()
			}
		}
	),
	assetEntrySetLikesCount: 0,
	childAssetEntrySetsCount: 6,
	privateAssetEntrySet: true
};

const post5 = {
	...LoopAssets.getPost(5),
	childAssetEntrySetsCount: 0
};

const comments = range(4).map(item => LoopAssets.getPost(item));

const store = mockStore(
	fromJS(
		{
			dirtyState: Map(),
			feeds: Map().setIn(
				['0', 'posts'],
				Range(0, 3).map(
					item => Map.of('id', item)
				).toOrderedMap()
			),
			people: Range(0, 3).map(
				item => fromJS(
					{data: LoopAssets.getPerson(item)}
				)
			).toMap().mergeIn(
				[currentPersonClassPK, 'data'],
				fromJS(
					LoopAssets.getPerson(currentPersonClassPK)
				)
			),
			posts: Range(0, 3).map(
				item => fromJS(
					{data: comments[item]}
				)
			).toMap()
		}
	)
);

const styles = {
	width: '616px'
};

class PostExample extends Component {
	render() {
		return (
			<Provider store={store}>
				<Post
					creatorIMap={fromJS(LoopAssets.getPerson())}
					feedId={0}
					id={1}
					itemsPerPage={7}
					messageInfoIMap={{}}
					{...this.otherProps()}
				/>
			</Provider>
		);
	}
}

class PostKit extends Component {
	render() {
		return (
			<Kit card={false} header="Post">
				<ElementContainer header="Post, no bookmark, no likes, & no comments" style={styles}>
					<PostExample childFeedId="1" postIMap={fromJS(post1)} />
				</ElementContainer>

				<ElementContainer header="Announcement, bookmarked, liked, & comments" style={styles}>
					<PostExample childFeedId="0" postIMap={fromJS(post2)} />
				</ElementContainer>

				<ElementContainer header="Announcement, no bookmark, liked, & no comments" style={styles}>
					<PostExample childFeedId="1" postIMap={fromJS(post3)} />
				</ElementContainer>

				<ElementContainer header="Post, no bookmark, no likes, & comments" style={styles}>
					<PostExample childFeedId="0" postIMap={fromJS(post4)} />
				</ElementContainer>

				<ElementContainer header="Private Post, liked,& no comments" style={styles}>
					<PostExample childFeedId="0" postIMap={fromJS(post5)} />
				</ElementContainer>
			</Kit>
		);
	}
}

export default PostKit;