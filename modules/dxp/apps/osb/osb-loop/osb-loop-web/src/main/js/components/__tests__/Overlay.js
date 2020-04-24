jest.unmock('../../actions/overlays');

import Component from 'metal-jsx';
import dom from 'metal-dom';
import {Provider} from 'metal-redux';

import mockStore from '../../tests/mock-store';
import Overlay from '../Overlay';
import {overlayTypes} from '../../actions/overlays';

class OverlayExample extends Component {
	render() {
		return (
			<Provider store={mockStore()} >
				<div class="test-overlay">
					<Overlay ref="overlay">
						<div class="trigger" />
					</Overlay>
				</div>
			</Provider>
		);
	}
}

describe(
	'Overlay',
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
				component = new OverlayExample(
					{
						children: [<div key={0} />],
						overlayType: overlayTypes.ENTITY_SUMMARY
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should set this.state.active_ to true',
			() => {
				component = new OverlayExample(
					{
						children: [<div key={0}>{'tests'}</div>],
						overlayType: overlayTypes.ENTITY_SUMMARY
					}
				);

				const connectFn = component.refs.overlay;

				const overlayComponent = connectFn.components.child;

				expect(overlayComponent.state.active_).toBeFalsy();

				dom.triggerEvent(overlayComponent.element, 'mouseover');

				jest.runAllTimers();

				expect(overlayComponent.state.active_).toBeTruthy();
			}
		);

		it(
			'should set this.state.active_ to false',
			() => {
				component = new OverlayExample(
					{
						children: [<div key={0}>{'tests'}</div>],
						overlayType: overlayTypes.ENTITY_SUMMARY
					}
				);

				const connectFn = component.refs.overlay;

				const overlayComponent = connectFn.components.child;

				dom.triggerEvent(overlayComponent.element, 'mouseover');

				jest.runAllTimers();

				expect(overlayComponent.state.active_).toBeTruthy();

				dom.triggerEvent(overlayComponent.element, 'mouseout');

				jest.runAllTimers();

				expect(overlayComponent.state.active_).toBeFalsy();
			}
		);
	}
);