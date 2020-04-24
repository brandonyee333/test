jest.mock('../Editor');

jest.unmock('../../actions/overlays');
jest.unmock('../../lib/asset-entry-set-utils');
jest.unmock('../../lib/selectors');

import Component from 'metal-jsx';
import {fromJS, Map, Range} from 'immutable';
import {Provider} from 'metal-redux';

import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';
import Post from '../Post';
import {addDirtyState, removeDirtyState} from '../../actions/dirty-state';
import {bookmarkPost, likePost} from '../../actions/posts';
import {showModal} from '../../actions/modals';

const actionNoop = {
	type: 'NO_OP'
};

const creatorIMap = fromJS(LoopAssets.getPerson());

const post = LoopAssets.getPost(1);

const postIMap = fromJS(post);

const currentPersonClassPK = LoopConstants.currentPerson.entityClassPK;

const store = mockStore(
	fromJS(
		{
			dirtyState: Map(),
			feeds: Map().setIn(
				[0, 'posts'],
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
				fromJS(LoopAssets.getPerson(currentPersonClassPK))
			),
			posts: Range(1, 3).map(
				item => fromJS(
					{data: post}
				)
			).toMap(),
			sharedTo: [LoopAssets.getPerson(2)]
		}
	)
);

class PostExample extends Component {
	render() {
		return (
			<Provider store={store}>
				<Post
					{...this.otherProps()}
					childFeedId="0"
					creatorIMap={creatorIMap}
					postIMap={postIMap}
					ref="post"
				/>
			</Provider>
		);
	}
}

addDirtyState.mockReturnValue(actionNoop);
removeDirtyState.mockReturnValue(actionNoop);
showModal.mockReturnValue(actionNoop);

describe(
	'Post',
	() => {
		let component;

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}
			}
		);

		it(
			'should render',
			() => {
				component = new PostExample();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call handleCommentCreatorFocus_',
			() => {
				component = new PostExample();

				const spy = jest.fn();

				const connectFn = component.components.post;

				const postComponent = connectFn.components.child;

				const commentCreatorComponent = postComponent.components.commentCreator.components.child;

				commentCreatorComponent.focus = spy;

				postComponent.handleCommentCreatorFocus_();

				expect(spy).toBeCalled();
			}
		);

		it(
			'should call showModal on handleShowLikedModalClick_',
			() => {
				showModal.mockReturnValue(actionNoop);

				component = new PostExample();

				const connectFn = component.components.post;

				const postComponent = connectFn.components.child;

				postComponent.handleShowLikedModalClick_();

				expect(showModal).toBeCalled();

				showModal.mockClear();
			}
		);

		it(
			'should call bookmarkPost on handleBookmarkClick_',
			() => {
				bookmarkPost.mockReturnValue(actionNoop);

				component = new PostExample();

				const connectFn = component.components.post;

				const postComponent = connectFn.components.child;

				postComponent.handleBookmarkClick_();

				expect(bookmarkPost).toBeCalled();

				bookmarkPost.mockClear();
			}
		);

		it(
			'should call likePost on handleLikeClick_',
			() => {
				likePost.mockReturnValue(actionNoop);

				component = new PostExample();

				const connectFn = component.components.post;

				const postComponent = connectFn.components.child;

				postComponent.handleLikeClick_();

				expect(likePost).toBeCalled();

				likePost.mockClear();
			}
		);

		it(
			'should reference editor',
			() => {
				component = new PostExample();

				const connectFn = component.components.post;

				const postComponent = connectFn.components.child;

				const commentCreatorComponent = postComponent.components.commentCreator.components.child;

				postComponent.props.postIMap = postIMap.set('childAssetEntrySetsCount', 0);

				jest.runAllTimers();

				postComponent.props.postIMap = postIMap.set('childAssetEntrySetsCount', 1);

				jest.runAllTimers();

				expect(commentCreatorComponent.components.editor).toBeTruthy();
			}
		);
	}
);