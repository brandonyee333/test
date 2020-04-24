import Component from 'metal-jsx';
import {fromJS, Map} from 'immutable';
import {range, unary} from 'lodash';

import createStoreStateSwitcher from './StoreStateSwitcher';
import ElementContainer from './ElementContainer';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import Sidebar from '../Sidebar';

function getEntities(count, func) {
	return range(count).reduce(
		(result, next) => result.setIn(
			[next, 'data'],
			fromJS(func(next))
		),
		Map()
	);
}

function getPerson(followingCount) {
	return getEntities(1, LoopAssets.getPerson).setIn(
		[0, 'following'],
		fromJS(
			{
				divisions: {
					data: range(followingCount),
					total: followingCount
				},
				topics: {
					data: range(followingCount),
					total: followingCount
				}
			}
		)
	);
}

function getState(count) {
	return fromJS(
		{
			divisions: getEntities(count, unary(LoopAssets.getDivision)),
			people: getPerson(count, LoopAssets.getPerson),
			topics: getEntities(count, LoopAssets.getTopic)
		}
	);
}

const {store, StoreStateSwitcher} = createStoreStateSwitcher(
	{
		'Less than Five': getState(3),
		'Long Name': getState(3).setIn(['divisions', 0, 'data', 'name'], 'AReallyLongDivisionNameThatShouldOverflow'),
		'More than Five': getState(10),
		'No Following': getState(0)
	}
);

const style = {
	display: 'block',
	width: '230px'
};

class SidebarKit extends Component {
	render() {
		return (
			<Kit header="Sidebar">
				<StoreStateSwitcher store={store} />

				<ElementContainer style={style}>
					<Sidebar id={0} store={store} />
				</ElementContainer>
			</Kit>
		);
	}
}

export default SidebarKit;