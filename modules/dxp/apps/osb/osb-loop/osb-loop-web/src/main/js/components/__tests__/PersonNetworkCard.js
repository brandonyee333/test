jest.unmock('../../lib/selectors');

import Component from 'metal-jsx';
import Promise from 'metal-promise';
import {fromJS, Map} from 'immutable';
import {Provider} from 'metal-redux';

import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';
import PersonNetworkCard from '../PersonNetworkCard';
import {fetchManagers, fetchSubordinates} from '../../actions/people';

const store = mockStore(
	Map().setIn(
		['people', 0, 'data'],
		fromJS(LoopAssets.getPerson())
	)
);

class PersonNetworkCardExample extends Component {
	render() {
		return (
			<Provider store={store}>
				<PersonNetworkCard id={0} />
			</Provider>
		);
	}
}

describe(
	'PersonNetworkCard',
	() => {
		let component;

		const mockRet = Promise.resolve({type: 'test'});

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}
			}
		);

		beforeEach(
			() => {
				fetchManagers.mockReturnValue(mockRet);
				fetchSubordinates.mockReturnValue(mockRet);
			}
		);

		it(
			'renders',
			() => {
				component = new PersonNetworkCardExample();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call fetchManagers when created',
			() => {
				component = new PersonNetworkCardExample();

				expect(fetchManagers).toBeCalled();

				fetchManagers.mockClear();
			}
		);

		it(
			'should call fetchSubordinates when created',
			() => {
				component = new PersonNetworkCardExample();

				expect(fetchSubordinates).toBeCalled();

				fetchSubordinates.mockClear();
			}
		);
	}
);