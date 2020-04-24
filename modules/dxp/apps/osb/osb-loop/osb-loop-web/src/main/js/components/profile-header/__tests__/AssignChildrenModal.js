jest.unmock('../../../lib/lang-util');

import Component from 'metal-jsx';
import {Provider} from 'metal-redux';

import AssignChildrenModal from '../AssignChildrenModal';
import mockStore from '../../../tests/mock-store';

class AssignChildrenModalExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<AssignChildrenModal {...this.otherProps()} />
			</Provider>
		);
	}
}

describe(
	'AssignChildrenModal',
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
				component = new AssignChildrenModalExample(
					{
						division: {}
					}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);