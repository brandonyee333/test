import Component from 'metal-jsx';
import {fromJS, Map} from 'immutable';
import {Provider} from 'metal-redux';
import {range} from 'lodash';

import ElementContainer from './ElementContainer';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import LoopConstants from '../../lib/loop-constants';
import mockStore from '../../tests/mock-store';
import Toolbar from '../toolbar';

function getEntities(count, func) {
	return range(count).reduce(
		(result, next) => result.setIn(
			[next, 'data'],
			fromJS(func(next))
		),
		Map()
	);
}

const divisionsCount = 15;
const topicsCount = 5;

const state = Map().setIn(
	['divisions'],
	getEntities(divisionsCount, LoopAssets.getDivision)
).setIn(
	['topics'],
	getEntities(topicsCount, LoopAssets.getTopic)
).setIn(
	['people', LoopConstants.currentPerson.entityClassPK],
	fromJS(
		{
			...LoopConstants.currentPerson,
			following: {
				divisions: {
					data: range(divisionsCount),
					total: divisionsCount
				},
				topics: {
					data: range(topicsCount),
					total: topicsCount
				}
			}
		}
	)
);

class ToolbarExample extends Component {
	render() {
		return (
			<Provider store={mockStore(state)}>
				<Toolbar />
			</Provider>
		);
	}
}

class ToolbarKit extends Component {
	render() {
		return (
			<Kit header="Toolbar">
				<ElementContainer>
					<ToolbarExample />
				</ElementContainer>
			</Kit>
		);
	};
}

export default ToolbarKit;