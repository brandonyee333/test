import Component from 'metal-jsx';
import {fromJS, Map} from 'immutable';

import ElementContainer from './ElementContainer';
import ElementDisplay from './ElementDisplay';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';
import PersonProfileMenu from '../profile-header/PersonProfileMenu';

const ID = 1;

const state = Map().setIn(
	['people', ID, 'data'],
	fromJS(LoopAssets.getPerson(ID))
);

const store = mockStore(state);

class PersonProfileMenuKit extends Component {
	render() {
		return (
			<Kit header="PersonProfileMenuKit (visual only)">
				<ElementContainer header="Hover Over Items">
					<ElementDisplay description="Full Permissions">
						<PersonProfileMenu
							editURL="#PersonProfileMenu"
							id={ID}
							permissionDelete={true}
							permissionEdit={true}
							store={store}
						/>
					</ElementDisplay>

					<ElementDisplay description="Delete Only">
						<PersonProfileMenu
							editURL="#PersonProfileMenu"
							id={ID}
							permissionDelete={true}
							permissionEdit={false}
							store={store}
						/>
					</ElementDisplay>

					<ElementDisplay description="Edit Only">
						<PersonProfileMenu
							editURL="#PersonProfileMenu"
							id={ID}
							permissionDelete={false}
							permissionEdit={true}
							store={store}
						/>
					</ElementDisplay>
				</ElementContainer>
			</Kit>
		);
	}
}

export default PersonProfileMenuKit;