import Component from 'metal-jsx';
import {fromJS} from 'immutable';
import {Provider} from 'metal-redux';

import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';
import PageListItem from '../PageListItem';

class PageListItemExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<PageListItem
					createDataIMap={fromJS(LoopAssets.getPerson())}
					entity={LoopAssets.getPage()}
					ownerIMap={fromJS(LoopAssets.getDivision())}
				/>
			</Provider>
		);
	}
}

describe(
	'PageListItem',
	() => {
		let component;

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}
			}
		);

		it(
			'renders',
			() => {
				component = new PageListItemExample();

				expect(component).toMatchSnapshot();
			}
		);
	}
);