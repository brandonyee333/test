import Component from 'metal-jsx';
import {fromJS, Map, Range} from 'immutable';
import {Provider} from 'metal-redux';

import createStateSwitcher from './StoreStateSwitcher';
import Feed from '../Feed';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import LoopConstants from '../../lib/loop-constants';
import {getPostType} from '../../lib/util';

const feedId = 111;

const state = fromJS(
	{
		dirtyState: Map(),
		feeds: Map().set(
			feedId,
			fromJS(
				{
					loading: false,
					posts: Range(0, 5).map(
						id => fromJS(
							{
								childFeedId: `${feedId}-${id}`,
								id
							}
						)
					).toOrderedMap()
				}
			)
		),
		people: Range(0, 5).map(
			id => fromJS({data: LoopAssets.getPerson(id)})
		).toMap().setIn(
			[LoopConstants.currentPerson.entityClassPK, 'data'],
			fromJS(LoopConstants.currentPerson)
		),
		posts:	Range(0, 5).map(
			id => fromJS(
				{data: LoopAssets.getPost(id)}
			)
		).toMap()
	}
);

const {store, StoreStateSwitcher} = createStateSwitcher(
	{
		'Displaying Posts': state,
		'Loading': state.updateIn(
			['feeds', feedId],
			feed => feed.set('loading', true).update('posts', posts => posts.take(1))
		)
	}
);

class FeedKit extends Component {
	render() {
		return (
			<Kit card={false} header="Feed">
				<Provider store={store}>
					<StoreStateSwitcher />

					<Feed
						id={feedId}
						itemsPerPage={7}
						type={getPostType('post')}
					/>
				</Provider>
			</Kit>
		);
	}
}

export default FeedKit;