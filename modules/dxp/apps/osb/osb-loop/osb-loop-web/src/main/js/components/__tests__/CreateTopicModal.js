jest.unmock('../../actions/crud');
jest.unmock('../../lib/form-validators');

import Component from 'metal-jsx';
import CreateTopicModal from '../CreateTopicModal';
import {Provider} from 'metal-redux';

import mockStore from '../../tests/mock-store';
import {addTopic} from '../../actions/topics';

class CreateTopicModalExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<CreateTopicModal ref="modal" {...this.otherProps()} />
			</Provider>
		);
	}
}

describe(
	'CreateTopicModal',
	() => {
		let component;

		addTopic.mockImplementation(() => Promise.resolve({data: {name: 'test'}}));

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
				component = new CreateTopicModalExample();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should not submit if the form is invalid',
			() => {
				component = new CreateTopicModalExample();

				const modalComponent = component.refs.modal.components.child;

				modalComponent.handleSubmit_();

				expect(addTopic).not.toBeCalled();
			}
		);
	}
);