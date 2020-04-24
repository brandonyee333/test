jest.unmock('../../lib/asset-entry-set-utils');
jest.unmock('../../lib/selectors');
jest.unmock('../../lib/util');

import Component from 'metal-jsx';
import {fromJS, Map} from 'immutable';
import {Provider} from 'metal-redux';

import AssetEditModal from '../AssetEditModal';
import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';
import {addDirtyState, removeDirtyState} from '../../actions/dirty-state';

const actionNoop = {
	type: 'NO_OP'
};

const ID = 1;

const state = Map().setIn(
	['dirtyState', ID],
	Map()
).setIn(
	['people', ID, 'data'],
	fromJS(LoopAssets.getPerson(ID))
).setIn(
	['posts', ID, 'data'],
	fromJS(LoopAssets.getPost(ID))
);

class AssetEditModalExample extends Component {
	render() {
		return (
			<Provider store={mockStore(state)}>
				<AssetEditModal
					id={ID}
					ref="assetEditor"
				/>
			</Provider>
		);
	}
}

describe(
	'AssetEditModal',
	() => {
		let component;

		addDirtyState.mockReturnValue(actionNoop);
		removeDirtyState.mockReturnValue(actionNoop);

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}

				addDirtyState.mockClear();
				removeDirtyState.mockClear();
			}
		);

		it(
			'renders',
			() => {
				component = new AssetEditModalExample();

				expect(component.element).toBeTruthy();
			}
		);
	}
);