jest.unmock('../../lib/selectors');

import Component from 'metal-jsx';
import {fromJS, Map} from 'immutable';
import {Provider} from 'metal-redux';

import AnnouncementCarouselCard from '../AnnouncementCarouselCard';
import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';
import {ellipsizeText} from '../../lib/util';
import {lang} from '../../lib/lang-util';
import {likePost} from '../../actions/posts';

const ID = 1;

const textPost = LoopAssets.getPost(ID);

const store = mockStore(
	Map().mergeIn(
		['posts', ID, 'data'],
		fromJS(
			{
				...textPost,
				title: 'test title'
			}
		)
	).mergeIn(
		['people', ID, 'data'],
		fromJS(LoopAssets.getPerson(ID))
	)
);

class AnnouncementCarouselCardExample extends Component {
	render() {
		return (
			<Provider store={store}>
				<AnnouncementCarouselCard ref="carousel" {...this.props} />
			</Provider>
		);
	}
}

describe(
	'AnnouncementCarouselCard',
	() => {
		let component;

		lang.mockReturnValue([]);

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}
			}
		);

		beforeEach(
			() => {
				ellipsizeText.mockClear();
				likePost.mockClear();

				likePost.mockReturnValue({type: 'test'});
			}
		);

		it(
			'renders',
			() => {
				component = new AnnouncementCarouselCardExample(
					{
						id: ID
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call likePost',
			() => {
				component = new AnnouncementCarouselCardExample(
					{
						id: ID
					}
				);

				const rootComponent = component.refs.carousel.components.child;

				expect(likePost).not.toBeCalled();

				rootComponent.handleLikeToggle_();

				expect(likePost).toBeCalled();
			}
		);

		it(
			'should ellipsizeText on attached',
			() => {
				expect(ellipsizeText).not.toBeCalled();

				component = new AnnouncementCarouselCardExample(
					{
						id: ID
					}
				);

				expect(ellipsizeText).toBeCalled();
			}
		);
	}
);