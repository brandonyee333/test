import Component from 'metal-jsx';
import {fromJS, Map, Range} from 'immutable';
import {Provider} from 'metal-redux';

import CommentFeed from '../CommentFeed';
import ElementContainer from './ElementContainer';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';

const posts = [
	LoopAssets.getPost(0),
	LoopAssets.getPost(
		1,
		{
			message: 'onsectetur adipiscing elit. Aliquam bibendum massa sit amet ultri'.repeat(10),
			parentAssetEntrySetId: 0,
			truncated: true
		}
	),
	LoopAssets.getPost(
		2,
		{parentAssetEntrySetId: 0}
	),
	LoopAssets.getPost(
		3,
		{parentAssetEntrySetId: 0}
	)
];

const store = mockStore(
	Map(
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
					{data: LoopAssets.getPerson()}
				)
			).toMap(),
			posts: Range(0, 3).map(
				item => fromJS(
					{data: posts[item]}
				)
			).toMap()
		}
	)
);

class CommentFeedKit extends Component {
	render() {
		return (
			<Kit header="CommentFeed">
				<Provider store={store}>
					<ElementContainer header="with show previous comments">
						<CommentFeed commentFeedId="0" total={5} />
					</ElementContainer>

					<ElementContainer header="without show previous comments">
						<CommentFeed commentFeedId="0" total={3} />
					</ElementContainer>
				</Provider>
			</Kit>
		);
	}
}

export default CommentFeedKit;