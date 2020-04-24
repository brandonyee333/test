import Component from 'metal-jsx';
import {fromJS} from 'immutable';
import {Provider} from 'metal-redux';

import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';
import PageListCard from '../PageListCard';

class PageListCardExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<PageListCard
					loading={false}
					pagesIList={fromJS([LoopAssets.getPerson()])}
					title="test test"
				/>
			</Provider>
		);
	}
}

describe(
	'PageListCard',
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
				component = new PageListCardExample();

				expect(component).toMatchSnapshot();
			}
		);
	}
);