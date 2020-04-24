jest.unmock('../../../lib/lang-util');

import Component from 'metal-jsx';
import {Provider} from 'metal-redux';

import ConvertDivisionTypeModal from '../ConvertDivisionTypeModal';
import mockStore from '../../../tests/mock-store';
import sendRequest from '../../../lib/request';

class ConvertDivisionTypeModalExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<ConvertDivisionTypeModal {...this.otherProps()} ref="ConvertDivisionTypeModal" />
			</Provider>
		);
	}
}

describe(
	'ConvertDivisionTypeModal',
	() => {
		let component;

		sendRequest.mockReturnValue(Promise.resolve());

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}

				sendRequest.mockClear();
			}
		);

		it(
			'renders',
			() => {
				component = new ConvertDivisionTypeModalExample(
					{
						division: {}
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call sendRequest on handleInputChange_',
			() => {
				component = new ConvertDivisionTypeModalExample(
					{
						division: {}
					}
				);

				const ConvertDivisionTypeModal = component.components.ConvertDivisionTypeModal;

				ConvertDivisionTypeModal.components.child.handleInputChange_();

				expect(sendRequest).toBeCalled();
			}
		);
	}
);