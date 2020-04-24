jest.unmock('../../lib/selectors');

import Component from 'metal-jsx';
import {Map} from 'immutable';
import {Provider} from 'metal-redux';

import mockStore from '../../tests/mock-store';
import PostPreviewModal from '../PostPreviewModal';

const CREATOR_ID = 100;

const store = mockStore(
	Map().mergeIn(
		['people', CREATOR_ID, 'data'],
		{
			name: 'test'
		}
	)
);

class PostPreviewModalExample extends Component {
	render() {
		return (
			<Provider store={store}>
				<PostPreviewModal
					{...this.otherProps()}
					creatorClassNameId={LoopConstants.classNameIds.people}
					creatorId={CREATOR_ID}
					imageData={[]}
					sharedTo={[]}
				/>
			</Provider>
		);
	}
}

describe(
	'Preview',
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
				component = new PostPreviewModalExample();

				expect(component).toMatchSnapshot();
			}
		);
	}
);