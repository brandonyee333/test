import Component from 'metal-jsx';
import {fromJS, Map, OrderedMap, Range} from 'immutable';
import {Provider} from 'metal-redux';

import AnnouncementCarousel from '../AnnouncementCarousel';
import ElementContainer from './ElementContainer';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';

const FEED_ID = 0;

const postConfigs = [
	{
		getPost: LoopAssets.getPostImage,
		title: 'Image Post Title!'
	},
	{
		getPost: LoopAssets.getPostLink,
		title: 'Link Post Title!'
	},
	{
		getPost: LoopAssets.getPost,
		title: 'Text Post Title!'
	},
	{
		getPost: LoopAssets.getPost,
		title: 'Text Post2 Title!'
	}
];

const rangeEnd = postConfigs.length;

const STATE_1 = Map(
	{
		feeds: Map().setIn(
			[FEED_ID, 'posts'],
			Range(0, rangeEnd).reduce(
				(result, next) => result.set(next, Map.of('id', next)),
				OrderedMap()
			)
		),
		people: Range(0, rangeEnd).reduce(
			(result, next) => result.setIn(
				[next, 'data'],
				fromJS(LoopAssets.getPerson(next))
			),
			Map()
		),
		posts: postConfigs.reduce(
			(result, next, id) => result.setIn(
				[id, 'data'],
				fromJS(next.getPost(id)).setIn(['payload', 'title'], next.title)
			),
			Map()
		)
	}
);

const STATE_2 = STATE_1.updateIn(
	['feeds', FEED_ID, 'posts'],
	posts => posts.take(2)
);

const STATE_3 = STATE_1.updateIn(
	['feeds', FEED_ID, 'posts'],
	posts => posts.take(1)
);

const store1 = mockStore(STATE_1);
const store2 = mockStore(STATE_2);
const store3 = mockStore(STATE_3);

const style = {
	margin: '0 auto',
	maxWidth: '616px',
	overflow: 'hidden',
	padding: '0 12px',
	width: '100%'
};

class AnnouncementCarouselKit extends Component {
	render() {
		return (
			<Kit card={false} header="AnnouncementCarousel">
				<ElementContainer header="more than 2 posts">
					<div style={style}>
						<Provider store={store1}>
							<AnnouncementCarousel id={FEED_ID} />
						</Provider>
					</div>
				</ElementContainer>

				<ElementContainer header="2 posts">
					<div style={style}>
						<Provider store={store2}>
							<AnnouncementCarousel id={FEED_ID} />
						</Provider>
					</div>
				</ElementContainer>

				<ElementContainer header="1 posts">
					<div style={style}>
						<Provider store={store3}>
							<AnnouncementCarousel id={FEED_ID} />
						</Provider>
					</div>
				</ElementContainer>
			</Kit>
		);
	};
}

export default AnnouncementCarouselKit;