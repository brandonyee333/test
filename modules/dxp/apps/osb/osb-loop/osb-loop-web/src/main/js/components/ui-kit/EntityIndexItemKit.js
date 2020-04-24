import Component from 'metal-jsx';
import {fromJS, Map} from 'immutable';
import {Provider} from 'metal-redux';
import {range} from 'lodash';

import createStoreStateSwitcher from './StoreStateSwitcher';
import EntityIndexItem from '../EntityIndexItem';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import LoopConstants from '../../lib/loop-constants';

const followingType = LoopConstants.followingType;

const states = {
	'Not Following, Notifications Disabled': Map(
		{
			divisions: Map().setIn(
				[0, 'data'],
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
			divisions: Map().setIn(
				[0, 'data'],
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
			divisions: Map().setIn(
				[0, 'data'],
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
			divisions: Map().setIn(
				[0, 'data'],
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
			divisions: Map().setIn(
				[0, 'data'],
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

class EntityIndexItemKit extends Component {
	render() {
		return (
			<Kit header="EntityIndexItem">
				<StoreStateSwitcher store={store} />

				<Provider store={store}>
					<EntityIndexItem.Wrapper>
						{
							range(13).map(
								value => (
									<EntityIndexItem entity={LoopAssets.getDivision(value)} key={value}>
										{'30 followers'}
									</EntityIndexItem>
								)
							)
						}
					</EntityIndexItem.Wrapper>
				</Provider>
			</Kit>
		);
	};
}

export default EntityIndexItemKit;