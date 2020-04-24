import Component from 'metal-jsx';
import {fromJS, Map, Range, Seq} from 'immutable';
import {noop} from 'lodash';
import {Provider} from 'metal-redux';

import ElementContainer from './ElementContainer';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import LoopConstants from '../../lib/loop-constants';
import mockStore from '../../tests/mock-store';
import SinglePostView from '../SinglePostView';

const currentPersonClassPK = LoopConstants.currentPerson.entityClassPK;

const post0 = {
	...LoopAssets.getPost(
		0,
		{
			bookmarked: true,
			likedParticipants: {
				liked: false,
				participants: []
			},
			sharedTo: [LoopAssets.getPerson(1)]
		}
	),
	childAssetEntrySetsCount: 3,
	type: 1
};

const posts = [
	post0,
	...Range(1, 3).map(item => LoopAssets.getPost(item))
];

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
			).toMap().setIn(
				[currentPersonClassPK, 'data'],
				fromJS(
					LoopAssets.getPerson(currentPersonClassPK)
				)
			),
			posts: Seq(posts).map(
				data => fromJS({data})
			).toMap()
		}
	)
);

const styles = {
	width: '616px'
};

class SinglePostViewKit extends Component {
	render() {
		return (
			<Kit card={false} header="SinglePostView">
				<ElementContainer style={styles}>
					<Provider store={store}>
						<SinglePostView id={0} itemsPerPage={7} onDelete={noop} />
					</Provider>
				</ElementContainer>
			</Kit>
		);
	}
}

export default SinglePostViewKit;