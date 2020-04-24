import Component from 'metal-jsx';
import {Map} from 'immutable';
import {Provider} from 'metal-redux';

import ElementContainer from './ElementContainer';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';
import SortableListItem from '../sortable-list/SortableListItem';

const {getPerson} = LoopAssets;

const creatorIMap = Map(getPerson());
const	secondaryInfo = 'December 31, 1922';
const	title = 'Test';

const SortableListItemExample = ({creatorIMap, secondaryInfo, selected, title}) => (
	<Provider store={mockStore()}>
		<SortableListItem
			creatorIMap={creatorIMap}
			id={0}
			secondaryInfo={secondaryInfo}
			selected={selected}
			title={title}
		/>
	</Provider>
);

class SortableListItemKit extends Component {
	render() {
		return (
			<Kit header="SortableListItem">
				<ElementContainer header="Basic">
					<SortableListItemExample
						creatorIMap={creatorIMap}
						secondaryInfo={secondaryInfo}
						selected={false}
						title={title}
					/>
				</ElementContainer>

				<ElementContainer header="Long Title">
					<SortableListItemExample
						creatorIMap={Map(getPerson(123))}
						secondaryInfo="a few moments ago"
						selected={false}
						title={title}
					/>
				</ElementContainer>

				<ElementContainer header="Selected Item">
					<SortableListItemExample
						creatorIMap={creatorIMap}
						secondaryInfo={secondaryInfo}
						selected={true}
						title={title}
					/>
				</ElementContainer>
			</Kit>
		);
	}
}

export default SortableListItemKit;