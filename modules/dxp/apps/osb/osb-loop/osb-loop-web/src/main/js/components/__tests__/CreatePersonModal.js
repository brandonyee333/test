jest.unmock('../../actions/crud');
jest.unmock('../../lib/form-validators');

import Component from 'metal-jsx';
import CreatePersonModal from '../CreatePersonModal';
import Promise from 'metal-promise';
import {Provider} from 'metal-redux';

import mockStore from '../../tests/mock-store';
import {addPerson} from '../../actions/people';

class CreatePersonModalExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<CreatePersonModal ref="modal" {...this.otherProps()} />
			</Provider>
		);
	}
}

describe(
	'CreatePersonModal',
	() => {
		let component;

		addPerson.mockImplementation(() => Promise.resolve({data: {name: 'test'}}));

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
				useFakeDate();

				component = new CreatePersonModalExample();

				expect(component).toMatchSnapshot();

				useRealDate();
			}
		);

		it(
			'should not submit if the form is invalid',
			() => {
				component = new CreatePersonModalExample();

				const modalComponent = component.refs.modal.components.child;

				modalComponent.handleSubmit_();

				expect(addPerson).not.toBeCalled();
			}
		);
	}
);