jest.unmock('../../lib/selectors');

import Component from 'metal-jsx';
import {Map} from 'immutable';
import {Provider} from 'metal-redux';

import mockStore from '../../tests/mock-store';
import PostPreview from '../PostPreview';
import sendRequest from '../../lib/request';

const CREATOR_ID = 100;

const store = mockStore(
	Map().mergeIn(
		['people', CREATOR_ID, 'data'],
		{
			name: 'test'
		}
	)
);

class PostPreviewContainerExample extends Component {
	render() {
		return (
			<Provider store={store}>
				<PostPreview
					{...this.otherProps()}
					creatorClassNameId={LoopConstants.classNameIds.people}
					creatorId={CREATOR_ID}
					imageData={[]}
					linkData={{}}
					markdown="Hi"
					message="Hi"
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

				sendRequest.mockClear();
			}
		);

		it(
			'renders',
			() => {
				component = new PostPreviewContainerExample();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call sendRequest on created',
			() => {
				expect(sendRequest).not.toBeCalled();

				component = new PostPreviewContainerExample();

				expect(sendRequest).toBeCalled();
			}
		);
	}
);