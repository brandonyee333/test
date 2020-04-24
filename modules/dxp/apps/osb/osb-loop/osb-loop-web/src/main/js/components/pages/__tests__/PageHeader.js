jest.unmock('../../../lib/lang-util');

import Component from 'metal-jsx';
import {fromJS} from 'immutable';
import {Provider} from 'metal-redux';

import LoopAssets from '../../../tests/loop-assets';
import mockStore from '../../../tests/mock-store';
import PageHeader from '../PageHeader';

class PageHeaderExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<PageHeader
					{...this.otherProps()}
					contentModifiedTime={1483982880145}
					creatorIMap={fromJS(LoopAssets.getPerson())}
				/>
			</Provider>
		);
	}
}

describe(
	'PageHeader',
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
				component = new PageHeaderExample();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render with custom verbiage',
			() => {
				component = new PageHeaderExample(
					{
						ownerIMap: fromJS(LoopAssets.getDivision()),
						verbiage: Liferay.Language.get('x-on-x')
					}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);