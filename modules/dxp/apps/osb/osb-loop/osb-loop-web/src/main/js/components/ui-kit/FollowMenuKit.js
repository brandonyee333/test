import Component from 'metal-jsx';
import {fromJS, Map} from 'immutable';

import Button from '../Button';
import createStoreStateSwitcher from './StoreStateSwitcher';
import ElementContainer from './ElementContainer';
import FollowMenu from '../FollowMenu';
import Kit from './Kit';
import LoopConstants from '../../lib/loop-constants';

const {classNameIds, followingType} = LoopConstants;

const states = {
	'Full Following, Notifications Disabled': Map(
		{
			people: Map().setIn(
				[1, 'data'],
				fromJS(
					{
						following: true,
						followingType: followingType.full,
						name: 'Test Test',
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
						name: 'Test Test',
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
						name: 'Test Test',
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
						name: 'Test Test',
						notifying: true,
						notifyingEmail: true
					}
				)
			)
		}
	),

	'No Unfollow Button': Map(
		{
			people: Map().setIn(
				[1, 'data'],
				fromJS(
					{
						following: true,
						followingType: followingType.full,
						name: '',
						notifying: true,
						notifyingEmail: false
					}
				)
			)
		}
	)
};

const {store, StoreStateSwitcher} = createStoreStateSwitcher(states);

class FollowMenuKit extends Component {
	render() {
		const trigger = <Button role="primary">{'Hover'}</Button>;

		return (
			<Kit header="FollowMenuKit (visual only)">
				<StoreStateSwitcher store={store} />

				<ElementContainer header="Hover to Show Component">
					<FollowMenu
						classNameId={classNameIds.people}
						horizontal="rightEdge"
						id={1}
						offsetLeft={12}
						store={store}
						trigger={trigger}
						vertical="top"
					/>
				</ElementContainer>
			</Kit>
		);
	}
}

export default FollowMenuKit;