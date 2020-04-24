import OrgChartModal from '../index';
import mockStore from '../../../tests/mock-store';
import {fetchHierarchy} from '../../../actions/divisions';

describe(
	'OrgChartModal',
	() => {
		let component;

		fetchHierarchy.mockImplementation(() => Promise.resolve());

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
				component = new OrgChartModal({store: mockStore()});

				expect(component).toMatchSnapshot();
			}
		);
	}
);