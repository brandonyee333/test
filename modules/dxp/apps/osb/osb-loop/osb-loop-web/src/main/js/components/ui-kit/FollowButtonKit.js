import Component from 'metal-jsx';
import {fromJS, Map} from 'immutable';

import createStoreStateSwitcher from './StoreStateSwitcher';
import ElementContainer from './ElementContainer';
import ElementDisplay from './ElementDisplay';
import FollowButton from '../FollowButton';
import Kit from './Kit';
import LoopConstants from '../../lib/loop-constants';

const {classNameIds, followingType} = LoopConstants;

const states = {
	'Not Following, Notifications Disabled': Map(
		{
			people: Map().setIn(
				[1, 'data'],
				fromJS(
					{
						following: false,
						followingType: followingType.unfollowing,
						notifying: false,
						notifyingEmail: false
					}
				)
			)
		}
	),

	'Full Following, Notifications Disabled': Map(
		{
			people: Map().setIn(
				[1, 'data'],
				fromJS(
					{
						following: true,
						followingType: followingType.full,
						notifying: false,
						notifyingEmail: false
					}
				)
			)
		}
	),

	'Limited Following, Notifications Disabled': Map(
		{
			people: Map().setIn(
				[1, 'data'],
				fromJS(
					{
						following: true,
						followingType: followingType.limited,
						notifying: false,
						notifyingEmail: false
					}
				)
			)
		}
	),

	'In-App Notifications Enabled': Map(
		{
			people: Map().setIn(
				[1, 'data'],
				fromJS(
					{
						following: true,
						followingType: followingType.full,
						notifying: true,
						notifyingEmail: false
					}
				)
			)
		}
	),

	'Email Notifications Enabled': Map(
		{
			people: Map().setIn(
				[1, 'data'],
				fromJS(
					{
						following: true,
						followingType: followingType.full,
						notifying: true,
						notifyingEmail: true
					}
				)
			)
		}
	)
};

const {store, StoreStateSwitcher} = createStoreStateSwitcher(states);

class FollowButtonKit extends Component {
	render() {
		return (
			<Kit header="FollowButtonKit (visual only)">
				<StoreStateSwitcher store={store} />

				<ElementContainer header="Hover to Show Component">
					<ElementDisplay description="Regular">
						<FollowButton
							classNameId={classNameIds.people}
							followingName="Test Test 1"
							horizontal="rightEdge"
							id={1}
							offsetLeft={12}
							store={store}
							vertical="top"
						/>
					</ElementDisplay>

					<ElementDisplay description="Mini">
						<FollowButton
							classNameId={classNameIds.people}
							display="mini"
							followingName="Test Test 2"
							horizontal="rightEdge"
							id={1}
							offsetLeft={12}
							store={store}
							vertical="top"
						/>
					</ElementDisplay>
				</ElementContainer>
			</Kit>
		);
	}
}

export default FollowButtonKit;