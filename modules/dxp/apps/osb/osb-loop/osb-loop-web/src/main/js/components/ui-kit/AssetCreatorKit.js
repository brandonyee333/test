import Component from 'metal-jsx';
import {Provider} from 'metal-redux';
import {Map} from 'immutable';

import AssetCreator from '../AssetCreator';
import Kit from './Kit';
import mockStore from '../../tests/mock-store';

const state = Map().setIn(
	['dirtyState', 0],
	Map()
);

class AssetCreatorKit extends Component {
	render() {
		return (
			<Kit card={false} header="AssetCreator">
				<Provider store={mockStore(state)}>
					<AssetCreator streamId={0} />
				</Provider>
			</Kit>
		);
	};
}

export default AssetCreatorKit;