jest.unmock('../../actions/overlays');

import Component, {Config} from 'metal-jsx';
import {Provider} from 'metal-redux';

import AffixHeader from '../AffixHeader';
import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';

class AffixHeaderExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<AffixHeader active={this.props.active} entity={LoopAssets.getPerson()} />
			</Provider>
		);
	}
}

AffixHeaderExample.PROPS = {
	active: Config.bool()
};

describe(
	'AffixHeader Component',
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
				component = new AffixHeaderExample();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should not render the content',
			() => {
				component = new AffixHeaderExample({active: false});

				expect(component.element.querySelector('.content')).toBeFalsy();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render the content',
			() => {
				component = new AffixHeaderExample({active: true});

				expect(component.element.querySelector('.content')).toBeTruthy();

				expect(component).toMatchSnapshot();
			}
		);
	}
);