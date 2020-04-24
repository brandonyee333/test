import Component from 'metal-jsx';
import {Provider} from 'metal-redux';

import CreateEntityCard from '../CreateEntityCard';
import mockStore from '../../tests/mock-store';

class CreateEntityCardExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<CreateEntityCard />
			</Provider>
		);
	}
}

describe(
	'CreateEntityCard',
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
				component = new CreateEntityCardExample();

				expect(component).toMatchSnapshot();
			}
		);
	}
);