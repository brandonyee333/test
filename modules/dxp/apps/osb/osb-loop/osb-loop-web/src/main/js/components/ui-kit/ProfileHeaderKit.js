import Component from 'metal-jsx';
import {Provider} from 'metal-redux';
import {fromJS, Map} from 'immutable';

import ElementContainer from './ElementContainer';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import LoopConstants from '../../lib/loop-constants';
import mockStore from '../../tests/mock-store';
import ProfileHeader from '../profile-header';

const {classNameIds} = LoopConstants;

const state = Map().setIn(
	['people', 0, 'data'],
	fromJS(LoopAssets.getPerson())
).setIn(
	['topics', 0, 'data'],
	fromJS(LoopAssets.getTopic())
).setIn(
	['divisions', 0, 'data'],
	fromJS(LoopAssets.getDivision())
);

class ProfileHeaderKit extends Component {
	render() {
		return (
			<Kit card={false} header="ProfileHeader">
				<ElementContainer header="Person">
					<Provider store={mockStore(state)}>
						<ProfileHeader
							classNameId={classNameIds.people}
							id={0}
						/>
					</Provider>
				</ElementContainer>

				<ElementContainer header="Topic">
					<Provider store={mockStore(state)}>
						<ProfileHeader
							classNameId={classNameIds.topics}
							id={0}
						/>
					</Provider>
				</ElementContainer>

				<ElementContainer header="Division">
					<Provider store={mockStore(state)}>
						<ProfileHeader
							classNameId={classNameIds.divisions}
							id={0}
						/>
					</Provider>
				</ElementContainer>
			</Kit>
		);
	}
}

export default ProfileHeaderKit;