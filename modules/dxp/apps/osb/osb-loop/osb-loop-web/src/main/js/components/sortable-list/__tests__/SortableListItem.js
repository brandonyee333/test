import Component from 'metal-jsx';
import {Map} from 'immutable';
import {Provider} from 'metal-redux';

import LoopAssets from '../../../tests/loop-assets';
import mockStore from '../../../tests/mock-store';
import SortableListItem from '../SortableListItem';

const creatorIMap = Map(LoopAssets.getPerson());
const displayURL = '/pages/0';
const id = 0;
const secondaryInfo = 'December 1, 2017';
const title = 'Hello World';

class SortableListItemExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<SortableListItem {...this.otherProps()} ref="sortableListItem" />
			</Provider>
		);
	}
}

describe(
	'SortableListItem',
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
				component = new SortableListItemExample(
					{
						creatorIMap,
						displayURL,
						id,
						secondaryInfo,
						selected: false,
						title
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders as selected',
			() => {
				component = new SortableListItemExample(
					{
						creatorIMap,
						displayURL,
						id,
						secondaryInfo,
						selected: true,
						title
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'calls navigate on handleClick',
			() => {
				const spy = jest.fn();

				Liferay.Loop.SPA.navigate = spy;

				component = new SortableListItemExample(
					{
						creatorIMap,
						displayURL,
						id,
						secondaryInfo,
						selected: false,
						title
					}
				);

				const connectFn = component.components.sortableListItem;

				connectFn.handleClick_();

				expect(spy).toBeCalled();
			}
		);
	}
);