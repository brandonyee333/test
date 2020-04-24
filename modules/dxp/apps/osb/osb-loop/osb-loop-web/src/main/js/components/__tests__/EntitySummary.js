jest.unmock('../../actions/overlays');
jest.unmock('../../lib/lang-util');
jest.unmock('../../lib/util');

import Component from 'metal-jsx';
import {fromJS} from 'immutable';
import {Provider} from 'metal-redux';

import EntitySummary from '../EntitySummary';
import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';

class EntitySummaryExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<EntitySummary entityIMap={fromJS(LoopAssets.getPerson())} />
			</Provider>
		);
	}
}

describe(
	'EntitySummary',
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
				component = new EntitySummaryExample();

				expect(component).toMatchSnapshot();
			}
		);
	}
);