jest.unmock('../../actions/crud');
jest.unmock('../../lib/form-validators');

import Component from 'metal-jsx';
import CreateDivisionModal from '../CreateDivisionModal';
import {Provider} from 'metal-redux';

import mockStore from '../../tests/mock-store';
import {addDivision} from '../../actions/divisions';

class CreateDivisionModalExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<CreateDivisionModal ref="modal" {...this.otherProps()} />
			</Provider>
		);
	}
}

describe(
	'CreateDivisionModal',
	() => {
		let component;

		addDivision.mockImplementation(() => Promise.resolve({data: {name: 'test'}}));

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
				component = new CreateDivisionModalExample();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should not submit if the form is invalid',
			() => {
				component = new CreateDivisionModalExample();

				const modalComponent = component.refs.modal.components.child;

				modalComponent.handleSubmit_();

				expect(addDivision).not.toBeCalled();
			}
		);
	}
);