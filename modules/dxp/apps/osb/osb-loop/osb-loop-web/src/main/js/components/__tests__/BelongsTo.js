import Component from 'metal-jsx';
import {Provider} from 'metal-redux';

import BelongsTo from '../BelongsTo';
import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';

class BelongsToExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<BelongsTo entity={LoopAssets.getDivision()} />
			</Provider>
		);
	}
}

describe(
	'BelongsTo',
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
				component = new BelongsToExample();

				expect(component).toMatchSnapshot();
			}
		);
	}
);