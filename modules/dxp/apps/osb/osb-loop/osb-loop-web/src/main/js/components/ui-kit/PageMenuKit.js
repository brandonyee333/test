import Component from 'metal-jsx';
import {noop} from 'lodash';
import {Provider} from 'metal-redux';

import ElementContainer from './ElementContainer';
import Kit from './Kit';
import mockStore from '../../tests/mock-store';
import PageMenu from '../pages/PageMenu';

class PageMenuKit extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<Kit header="PageMenu">
					<ElementContainer header="edit permissions ">
						<PageMenu
							id={0}
							onDelete={noop}
							onEdit={noop}
							permissionDelete={false}
							permissionEdit={true}
						/>
					</ElementContainer>

					<ElementContainer header="delete permissions">
						<PageMenu
							id={0}
							onDelete={noop}
							onEdit={noop}
							permissionDelete={true}
							permissionEdit={false}
						/>
					</ElementContainer>

					<ElementContainer header="edit and delete permissions">
						<PageMenu
							id={0}
							onDelete={noop}
							onEdit={noop}
							permissionDelete={true}
							permissionEdit={true}
						/>
					</ElementContainer>
				</Kit>
			</Provider>
		);
	}
}

export default PageMenuKit;