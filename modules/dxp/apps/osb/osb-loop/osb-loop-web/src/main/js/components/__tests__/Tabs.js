import Component, {Config} from 'metal-jsx';
import dom from 'metal-dom';

import Tabs from '../Tabs';

describe(
	'Tabs',
	() => {
		let component;

		class TabsTest extends Component {
			created() {
				this.state.tabIndex_ = this.props.tabIndex;

				this.handleTabIndexChange_ = this.handleTabIndexChange_.bind(this);
			}

			handleTabIndexChange_(index) {
				this.state.tabIndex_ = index;
			}

			render() {
				return (
					<Tabs
						index={this.state.tabIndex_}
						onIndexChange={this.handleTabIndexChange_}
					>
						<Tabs.Content key={1} name="foo">{'foo'}</Tabs.Content>
						<Tabs.Content key={2} name="bar">{'bar'}</Tabs.Content>
					</Tabs>
				);
			}
		}

		TabsTest.PROPS = {
			tabIndex: Config.value(0)
		};

		TabsTest.STATE = {
			tabIndex_: Config.value(0)
		};

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
				component = new TabsTest();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders the first tab if an index is not passed in',
			() => {
				component = new TabsTest();

				const tabContent = component.element.getElementsByClassName('content')[0].textContent;

				expect(tabContent).toBe('foo');
				expect(tabContent).not.toBe('bar');
			}
		);

		it(
			'renders the tab corresponding to the passed in index',
			() => {
				component = new TabsTest(
					{
						tabIndex: 1
					}
				);

				const tabContent = component.element.getElementsByClassName('content')[0].textContent;

				expect(tabContent).toBe('bar');
				expect(tabContent).not.toBe('foo');
			}
		);

		it(
			'renders the corresponding content when a tab is clicked',
			() => {
				component = new TabsTest();

				let tabContent = component.element.getElementsByClassName('content')[0].textContent;

				expect(tabContent).toBe('foo');
				expect(tabContent).not.toBe('bar');

				const tabs = component.element.querySelectorAll('.tab a');

				dom.triggerEvent(tabs[1], 'click');

				jest.runAllTimers();

				tabContent = component.element.getElementsByClassName('content')[0].textContent;

				expect(tabContent).toBe('bar');
				expect(tabContent).not.toBe('foo');
			}
		);
	}
);