jest.mock('../Editor');

jest.unmock('../../lib/asset-entry-set-utils');
jest.unmock('../../lib/selectors');
jest.unmock('../../pages/ErrorPage');

import Component, {Config} from 'metal-jsx';
import Promise from 'metal-promise';
import {fromJS, Map, Range} from 'immutable';

import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';
import SinglePostView from '../SinglePostView';
import {addDirtyState, removeDirtyState} from '../../actions/dirty-state';
import {fetchPost} from '../../actions/posts';
import {Provider} from 'metal-redux';

const actionNoop = {
	type: 'NO_OP'
};

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
			).toMap().setIn(
				[currentPersonClassPK, 'data'],
				fromJS(
					LoopAssets.getPerson(currentPersonClassPK)
				)
			),
			posts: Range(0, 3).map(
				item => fromJS(
					{
						data: LoopAssets.getPost(item),
						loading: false
					}
				)
			).toMap()
		}
	)
);

class SinglePostViewExample extends Component {
	render() {
		const router = {
			params: {
				focusedId: this.props.focusedId
			}
		};

		return (
			<Provider store={store}>
				<SinglePostView router={router} />
			</Provider>
		);
	}
}

SinglePostViewExample.PROPS = {
	focusedId: Config.number().value(0)
};

addDirtyState.mockReturnValue(actionNoop);
removeDirtyState.mockReturnValue(actionNoop);

describe(
	'SinglePostView',
	() => {
		let component;

		afterEach(
			() => {
				if (component) {
					component.dispose();

					fetchPost.mockClear();
				}
			}
		);

		it(
			'should render with loading set to false',
			() => {
				fetchPost.mockReturnValue(Promise.resolve(actionNoop));

				component = new SinglePostViewExample();

				jest.runAllTimers();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render loading',
			() => {
				fetchPost.mockReturnValue(Promise.resolve(actionNoop));

				component = new SinglePostViewExample({focusedId: 9});

				expect(component).toMatchSnapshot();
			}
		);
	}
);