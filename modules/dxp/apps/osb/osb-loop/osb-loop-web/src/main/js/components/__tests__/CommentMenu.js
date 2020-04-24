import CommentMenu from '../CommentMenu';
import mockStore from '../../tests/mock-store';
import {showModal} from '../../actions/modals';

describe(
	'CommentMenu',
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
				component = new CommentMenu(
					{
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

				component = new CommentMenu(
					{
						store: mockStore()
					}
				);

				component.components.child.handleDeletePost_();

				expect(showModal).toBeCalled();

				showModal.mockClear();
			}
		);
	}
);