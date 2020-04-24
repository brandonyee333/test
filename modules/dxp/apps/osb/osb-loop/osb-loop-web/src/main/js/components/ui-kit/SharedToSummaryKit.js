import Component from 'metal-jsx';
import {isEqual, sortBy, uniqWith} from 'lodash';
import {fromJS} from 'immutable';

import ElementContainer from './ElementContainer';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import LoopConstants from '../../lib/loop-constants';
import mockStore from '../../tests/mock-store';
import SharedToSummary from '../SharedToSummary';

const store = mockStore();

function getCombinations(items) {
	const results = [];

	const addItem = (prefixItem, items) => {
		items.forEach(
			(item, index) => {
				results.push(
					fromJS(
						sortBy(prefixItem.concat(item), ['name'])
					)
				);

				addItem(prefixItem.concat(item), items.slice(index + 1));
			}
		);
	};

	addItem([], items);

	return uniqWith(results, isEqual);
}

const COMBINATIONS = getCombinations(
	[
		LoopAssets.getDivision(),
		LoopAssets.getPerson(),
		LoopAssets.getTopic(),
		LoopAssets.getDivision(),
		LoopAssets.getPerson(),
		LoopAssets.getTopic()
	]
).concat(
	fromJS(
		[
			LoopAssets.getDivision('ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium'),
			LoopAssets.getPerson('ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium'),
			LoopAssets.getTopic('ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium')
		]
	)
);

class SharedToSummaryKit extends Component {
	render() {
		return (
			<Kit header="SharedToSummary">
				{
					COMBINATIONS.map(
						(entitiesIList, i) => (
							<ElementContainer key={i} style="width:500px;">
								<SharedToSummary
									creator={LoopConstants.currentPerson}
									entitiesIList={entitiesIList}
									store={store}
								/>
							</ElementContainer>
						)
					)
				}
			</Kit>
		);
	}
}

export default SharedToSummaryKit;