jest.unmock('../../../lib/lang-util');

import Component from 'metal-jsx';
import {Provider} from 'metal-redux';

import AssignParentModal from '../AssignParentModal';
import mockStore from '../../../tests/mock-store';

class AssignParentModalExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<AssignParentModal {...this.otherProps()} />
			</Provider>
		);
	}
}

describe(
	'AssignParentModal',
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
				component = new AssignParentModalExample(
					{
						division: {}
					}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);