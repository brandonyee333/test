import mockStore from '../../tests/mock-store';
import SharedToModal from '../SharedToModal';

describe(
	'SharedToModal',
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
				component = new SharedToModal(
					{
						entities: [[]],
						store: mockStore()
					}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);