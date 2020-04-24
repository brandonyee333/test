import Component, {Config} from 'metal-jsx';
import dom from 'metal-dom';

import RadioGroup from '../index';

describe(
	'RadioGroup',
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
				component = new RadioGroup();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should check other option on click',
			() => {
				class SpanComponent extends Component {
					render() {
						return <span>{this.props.children}</span>;
					}
				}

				class TestGroup extends Component {
					created() {
						this.onChange_ = this.onChange_.bind(this);
					}

					onChange_(value) {
						this.state.checked = value;
					}

					render() {
						return (
							<RadioGroup checked={this.state.checked} component={SpanComponent} name="testradio" onChange={this.onChange_}>
								<RadioGroup.Option elementClasses="option-1" label="Option 1" value={0} />
								<RadioGroup.Option elementClasses="option-2" label="Option 2" value={1} />
								<RadioGroup.Option elementClasses="option-3" label="Option 3" value={2} />
							</RadioGroup>
						);
					}
				}

				TestGroup.STATE = {
					checked: Config.value(1)
				};

				component = new TestGroup();

				expect(component.element.querySelector('.option-2').classList.contains('radio-checked')).toBe(true);
				expect(component.element.querySelector('.option-3').classList.contains('radio-checked')).toBe(false);

				dom.triggerEvent(component.element.querySelector('.option-3'), 'click');

				jest.runAllTimers();

				expect(component.element.querySelector('.option-2').classList.contains('radio-checked')).toBe(false);
				expect(component.element.querySelector('.option-3').classList.contains('radio-checked')).toBe(true);
			}
		);
	}
);