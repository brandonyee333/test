import Component from 'metal-jsx';
import dom from 'metal-dom';

import Tooltip from '../Tooltip';

class Trigger extends Component {
	render() {
		return <div class="trigger" data-tooltip-response="test" title="This is the trigger" />;
	}
}

class TestTooltip extends Component {
	render() {
		return (
			<div>
				<Trigger ref="trigger" />

				<Tooltip ref="tooltip" />
			</div>
		);
	}
}

describe(
	'Tooltip',
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
				component = new Tooltip();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should display the tooltip on mouseenter',
			() => {
				component = new TestTooltip();

				expect(component.element.querySelector('.tooltip-content')).toBeNull();

				dom.triggerEvent(component.element.querySelector('.trigger'), 'mouseenter');

				setTimeout(
					() => expect(component.element.querySelector('.tooltip-content')).toBeTruthy(),
					700
				);
			}
		);

		it(
			'should display the tooltip on mouseleave',
			() => {
				component = new TestTooltip();

				expect(component.element.querySelector('.tooltip-content')).toBeNull();

				dom.triggerEvent(component.element.querySelector('.trigger'), 'mouseenter');

				setTimeout(
					() => {
						expect(component.element.querySelector('.tooltip-content')).toBeTruthy();

						dom.triggerEvent(component.element.querySelector('.trigger'), 'mouseleave');

						expect(component.element.querySelector('.tooltip-content')).toBeNull();
					},
					700
				);

			}
		);

		it(
			'should display the tooltip on mouseclick',
			() => {
				component = new TestTooltip();

				expect(component.element.querySelector('.tooltip-content')).toBeNull();

				dom.triggerEvent(component.element.querySelector('.trigger'), 'mouseenter');

				setTimeout(
					() => {
						expect(component.element.querySelector('.tooltip-content')).toBeTruthy();

						dom.triggerEvent(component.element.querySelector('.trigger'), 'click');

						expect(component.element.querySelector('.tooltip-content')).toBe('test');
					},
					700
				);

			}
		);
	}
);