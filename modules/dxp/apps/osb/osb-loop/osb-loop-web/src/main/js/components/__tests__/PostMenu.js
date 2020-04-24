jest.unmock('../../actions/overlays');

import Component, {Config} from 'metal-jsx';
import Promise from 'metal-promise';
import {fromJS, Map} from 'immutable';
import {Provider} from 'metal-redux';

import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';
import PostMenu from '../PostMenu';
import {addAlert} from '../../actions/alerts';
import {bookmarkPost, followPost} from '../../actions/posts';
import {getPostType} from '../../lib/util';
import {showModal} from '../../actions/modals';

const announcement = LoopAssets.getPost(1);

announcement.type = 'announcement';

const store = mockStore(
	fromJS(
		{
			posts: Map().set(
				0,
				fromJS(
					{data: LoopAssets.getPost()}
				)
			).set(
				1,
				fromJS(
					{data: announcement}
				)
			),
			sharedTo: [LoopAssets.getPerson(2)]
		}
	)
);

class PostMenuExample extends Component {
	render() {
		return (
			<Provider store={store}>
				<PostMenu feedId={LoopConstants.loopStreamAliasIds.following} id={this.props.id} ref="postMenu" />
			</Provider>
		);
	}
}

PostMenuExample.PROPS = {
	id: Config.number().value(0)
};

describe(
	'PostMenu',
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
			'renders',
			() => {
				component = new PostMenuExample();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should return true if the post is an announcement',
			() => {
				getPostType.mockReturnValue('announcement');

				component = new PostMenuExample({id: 1});

				const connectFn = component.components.postMenu;

				expect(connectFn.components.child.announcement_()).toBe(true);

				getPostType.mockClear();
			}
		);

		it(
			'should call bookmarkPost action and addAlert action',
			() => {
				bookmarkPost.mockReturnValue(Promise.resolve());

				component = new PostMenuExample();

				const connectFn = component.components.postMenu;

				connectFn.components.child.handleBookmarkPost_();

				jest.runAllTimers();

				expect(addAlert).toBeCalled();
				expect(bookmarkPost).toBeCalled();

				bookmarkPost.mockClear();
			}
		);

		it(
			'should call showModal action',
			() => {
				showModal.mockReturnValue({type: 'test'});

				component = new PostMenuExample();

				const connectFn = component.components.postMenu;

				connectFn.components.child.handleDeletePost_();

				expect(showModal).toBeCalled();

				showModal.mockClear();
			}
		);

		it(
			'should call showModal action',
			() => {
				showModal.mockReturnValue({type: 'test'});

				component = new PostMenuExample();

				const connectFn = component.components.postMenu;

				connectFn.components.child.handleEditPost_();

				expect(showModal).toBeCalled();

				showModal.mockClear();
			}
		);

		it(
			'should call followPost action and addAlert action',
			() => {
				followPost.mockReturnValue(Promise.resolve());

				component = new PostMenuExample();

				const connectFn = component.components.postMenu;

				connectFn.components.child.handleFollowPost_();

				jest.runAllTimers();

				expect(addAlert).toBeCalled();
				expect(followPost).toBeCalled();

				followPost.mockClear();
			}
		);

		it(
			'should call showModal action',
			() => {
				showModal.mockReturnValue({type: 'test'});

				component = new PostMenuExample();

				const connectFn = component.components.postMenu;

				connectFn.components.child.handleSharePost_();

				expect(showModal).toBeCalled();

				showModal.mockClear();
			}
		);
	}
);