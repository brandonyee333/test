jest.unmock('../../actions/crud');
jest.unmock('../../lib/form-validators');

import Component from 'metal-jsx';
import EditJobTitleModal from '../EditJobTitleModal';
import Promise from 'metal-promise';
import {Provider} from 'metal-redux';

import mockStore from '../../tests/mock-store';
import {addJobTitle} from '../../actions/job-titles';

class EditJobTitleModalExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<EditJobTitleModal ref="modal" {...this.otherProps()} />
			</Provider>
		);
	}
}

describe(
	'EditJobTitleModal',
	() => {
		let component;

		addJobTitle.mockImplementation(() => Promise.resolve({data: {name: 'test'}}));

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
				component = new EditJobTitleModalExample();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should not submit if the form is invalid',
			() => {
				component = new EditJobTitleModalExample();

				const modalComponent = component.refs.modal.components.child;

				modalComponent.handleSubmit_();

				expect(addJobTitle).not.toBeCalled();
			}
		);
	}
);