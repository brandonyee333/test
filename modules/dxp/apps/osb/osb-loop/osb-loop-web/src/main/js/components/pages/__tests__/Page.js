jest.unmock('../../../lib/selectors');

import Component from 'metal-jsx';
import {fromJS, Map} from 'immutable';
import {Provider} from 'metal-redux';

import LoopAssets from '../../../tests/loop-assets';
import mockStore from '../../../tests/mock-store';
import Page from '../Page';
import {fetchPage} from '../../../actions/pages';

class PageExample extends Component {
	render() {
		const state = fromJS(
			{
				pages: Map().set(
					1,
					fromJS(
						{
							data: LoopAssets.getPage(1),
							loading: false
						}
					)
				),
				people: Map().set(
					1,
					fromJS(
						{
							data: LoopAssets.getPerson(1)
						}
					)
				)
			}
		);

		return (
			<Provider store={mockStore(state)}>
				<Page id={1} />
			</Provider>
		);
	}
}

describe(
	'Page',
	() => {
		let component;

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}

				fetchPage.mockClear();
			}
		);

		it(
			'renders',
			() => {
				fetchPage.mockReturnValue(Promise.resolve());

				component = new PageExample();

				expect(component).toMatchSnapshot();
			}
		);
	}
);