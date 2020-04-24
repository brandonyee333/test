jest.unmock('../../actions/crud');
jest.unmock('../../lib/form-validators');

import Component from 'metal-jsx';
import CreateJobTitleModal from '../CreateJobTitleModal';
import Promise from 'metal-promise';
import {Provider} from 'metal-redux';

import mockStore from '../../tests/mock-store';
import {addJobTitle} from '../../actions/job-titles';

class CreateJobTitleModalExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<CreateJobTitleModal ref="modal" {...this.otherProps()} />
			</Provider>
		);
	}
}

describe(
	'CreateJobTitleModal',
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
				component = new CreateJobTitleModalExample();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should not submit if the form is invalid',
			() => {
				component = new CreateJobTitleModalExample();

				const modalComponent = component.refs.modal.components.child;

				modalComponent.handleSubmit_();

				expect(addJobTitle).not.toBeCalled();
			}
		);
	}
);