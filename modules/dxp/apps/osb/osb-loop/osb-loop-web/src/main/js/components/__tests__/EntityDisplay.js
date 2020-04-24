jest.unmock('../../lib/lang-util');

import Component, {Config} from 'metal-jsx';
import {Provider} from 'metal-redux';

import EntityDisplay from '../EntityDisplay';
import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';

class EntityDisplayExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<EntityDisplay entity={this.props.entity} />
			</Provider>
		);
	}
}

EntityDisplayExample.PROPS = {
	entity: Config.object()
};

describe(
	'EntityDisplay',
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
				component = new EntityDisplayExample({entity: LoopAssets.getPerson()});

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders for a division',
			() => {
				component = new EntityDisplayExample({entity: LoopAssets.getDivision()});

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders for a division',
			() => {
				component = new EntityDisplayExample({entity: LoopAssets.getTopic()});

				expect(component).toMatchSnapshot();
			}
		);
	}
);