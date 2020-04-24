import Component from 'metal-jsx';
import {fromJS, Map, OrderedMap, Range} from 'immutable';
import {Provider} from 'metal-redux';

import AnnouncementCarouselCard from '../AnnouncementCarouselCard';
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
		getPost: LoopAssets.getPostImage,
		title: 'Image Post2 with an Extra Especially Long Title!'
	},
	{
		getPost: LoopAssets.getPostLink,
		title: 'Link Post Title!'
	},
	{
		getPost: LoopAssets.getPostLink,
		title: 'Link Post2 with an Extra Especially Long Title!'
	},
	{
		getPost: LoopAssets.getPost,
		title: 'Text Post Title!'
	},
	{
		getPost: LoopAssets.getPost,
		title: 'Text Post2 with an Extra Especially Long Title!'
	}
];

const rangeEnd = postConfigs.length;

const STATE = Map(
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

const store = mockStore(STATE);

const style = {
	margin: '0 auto',
	maxWidth: '308px',
	width: '100%'
};

class AnnouncementCarouselCardKit extends Component {
	render() {
		return (
			<Kit card={false} header="AnnouncementCarouselCard">
				{postConfigs.map(
					(config, id) => (
						<ElementContainer header="Image Post" key={id}>
							<div style={style}>
								<Provider store={store}>
									<AnnouncementCarouselCard id={id} />
								</Provider>
							</div>
						</ElementContainer>
					)
				)}
			</Kit>
		);
	};
}

export default AnnouncementCarouselCardKit;