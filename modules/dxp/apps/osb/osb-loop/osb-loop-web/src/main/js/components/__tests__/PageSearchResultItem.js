import Component from 'metal-jsx';
import {fromJS} from 'immutable';
import {Provider} from 'metal-redux';

import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';
import PageSearchResultItem from '../PageSearchResultItem.js';

class PageSearchResultItemExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<PageSearchResultItem
					creatorIMap={fromJS(LoopAssets.getPerson())}
					ownerIMap={fromJS(LoopAssets.getDivision())}
					pageIMap={fromJS(LoopAssets.getPage())}
				/>
			</Provider>
		);
	}
}

describe(
	'PageSearchResultItem',
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
				component = new PageSearchResultItemExample();

				expect(component).toMatchSnapshot();
			}
		);
	}
);