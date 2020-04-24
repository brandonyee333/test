import Component from 'metal-jsx';

import CommentMenu from '../CommentMenu';
import ElementContainer from './ElementContainer';
import ElementDisplay from './ElementDisplay';
import Kit from './Kit';
import mockStore from '../../tests/mock-store';

const store = mockStore();

class CommentMenuKit extends Component {
	render() {
		return (
			<Kit header="CommentMenu">
				<ElementContainer>
					<ElementDisplay description="Full permissions">
						<CommentMenu
							permissionDelete={true}
							permissionEdit={true}
							store={store}
						/>
					</ElementDisplay>

					<ElementDisplay description="Edit permissions">
						<CommentMenu
							permissionDelete={false}
							permissionEdit={true}
							store={store}
						/>
					</ElementDisplay>

					<ElementDisplay description="Delete permissions">
						<CommentMenu
							permissionDelete={true}
							permissionEdit={false}
							store={store}
						/>
					</ElementDisplay>
				</ElementContainer>
			</Kit>
		);
	}
}

export default CommentMenuKit;