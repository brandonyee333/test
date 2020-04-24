jest.unmock('../../actions/overlays');

import Component from 'metal-jsx';
import {Provider} from 'metal-redux';

import EntityIndexItem from '../EntityIndexItem';
import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';

class EntityIndexItemExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<EntityIndexItem {...this.otherProps()} entity={LoopAssets.getPerson()} />
			</Provider>
		);
	}
}

describe(
	'EntityIndexItem',
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
			'should render',
			() => {
				component = new EntityIndexItemExample();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render secondaryDetails',
			() => {
				component = new EntityIndexItemExample(
					{
						secondaryDetails: [<div class="test" key={0}>{'test'}</div>]
					}
				);

				expect(component.element.querySelector('.test')).toBeTruthy();
			}
		);
	}
);