import {noop} from 'lodash';

import mockStore from '../../../tests/mock-store';
import PageMenu from '../PageMenu';
import {showModal} from '../../../actions/modals';

describe(
	'PageMenu',
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
				component = new PageMenu(
					{
						id: 0,
						permissionDelete: true,
						permissionEdit: true,
						store: mockStore()
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders with edit button only',
			() => {
				component = new PageMenu(
					{
						id: 0,
						permissionEdit: true,
						store: mockStore()
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders with delete button only',
			() => {
				component = new PageMenu(
					{
						id: 0,
						permissionDelete: true,
						store: mockStore()
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call showModal action',
			() => {
				showModal.mockReturnValue({type: 'test'});

				component = new PageMenu(
					{
						id: 0,
						onDelete: noop,
						permissionDelete: true,
						store: mockStore()
					}
				);

				component.components.child.handleDeletePage_();

				expect(showModal).toBeCalled();

				showModal.mockClear();
			}
		);
	}
);