jest.unmock('../../../lib/util');

import Component from 'metal-jsx';
import {Provider} from 'metal-redux';

import MergeTopicsModal from '../MergeTopicsModal';
import mockStore from '../../../tests/mock-store';

class MergeTopicsModalExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<MergeTopicsModal {...this.otherProps()} />
			</Provider>
		);
	}
}

describe(
	'MergeTopicsModal',
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
				component = new MergeTopicsModalExample(
					{
						topic: {}
					}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);