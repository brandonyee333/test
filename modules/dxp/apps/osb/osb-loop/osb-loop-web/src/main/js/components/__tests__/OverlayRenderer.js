import Component from 'metal-jsx';
import {fromJS, OrderedMap} from 'immutable';
import {Provider} from 'metal-redux';

import mockStore from '../../tests/mock-store';
import OverlayRenderer from '../OverlayRenderer';
import {overlayTypes} from '../../actions/overlays';

const id = 'OVERLAY_ERROR_MESSAGE1';

const store = mockStore(
	fromJS(
		{
			overlays: OrderedMap(
				{
					[id]: fromJS(
						{
							align: 'Bottom',
							alignWithParent: false,
							id: 'OVERLAY_ERROR_MESSAGE1',
							offset: 0,
							overlayProps: {message: 'test test'},
							overlayType: overlayTypes.OVERLAY_ERROR_MESSAGE
						}
					)
				}
			)
		}
	)
);

class OverlayRendererExample extends Component {
	render() {
		return (
			<Provider store={store} >
				<OverlayRenderer ref="overlayRenderer" />
			</Provider>
		);
	}
}

describe(
	'OverlayRenderer',
	() => {
		document.body.innerHTML = `<div><span id="${id}" /><button id="button" /></div>`;

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
				component = new OverlayRendererExample();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render with a zIndex of 2200',
			() => {
				component = new OverlayRendererExample();

				const connectFn = component.refs.overlayRenderer;

				connectFn.components.child.props.modalActive = true;

				jest.runAllTimers();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call getOffset_',
			() => {
				const spy = jest.fn();

				component = new OverlayRendererExample();

				const connectFn = component.refs.overlayRenderer;

				connectFn.components.child.getOffset_ = spy;

				jest.runAllTimers();

				expect(spy).toBeCalled();
			}
		);

		it(
			'should call getPosition_',
			() => {
				const spy = jest.fn();

				component = new OverlayRendererExample();

				const connectFn = component.refs.overlayRenderer;

				connectFn.components.child.getPosition_ = spy;

				jest.runAllTimers();

				expect(spy).toBeCalled();
			}
		);
	}
);