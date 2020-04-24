import Component from 'metal-jsx';
import {Provider} from 'metal-redux';

import mockStore from '../../tests/mock-store';
import ModalLink from '../ModalLink';

class ModalLinkExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<ModalLink {...this.otherProps()} ref="modalLink" />
			</Provider>
		);
	}
}

describe(
	'ModalLink',
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
				component = new ModalLinkExample();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call showModal on handle click',
			() => {
				component = new ModalLinkExample(
					{
						showModal: jest.fn()
					}
				);

				const eventObj = {
					preventDefault: jest.fn(),
					stopImmediatePropagation: jest.fn()
				};

				const modalLinkComponent = component.components.modalLink.components.child;

				modalLinkComponent.props.showModal = jest.fn();

				modalLinkComponent.handleClick_(eventObj);

				expect(modalLinkComponent.props.showModal).toBeCalled();
				expect(eventObj.preventDefault).toBeCalled();
				expect(eventObj.stopImmediatePropagation).toBeCalled();
			}
		);
	}
);