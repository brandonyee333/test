import EntityLink from '../EntityLink';
import LoopAssets from '../../tests/loop-assets';

const TestPerson = LoopAssets.getPerson();
const TestTopic = LoopAssets.getTopic();

describe(
	'EntityLink',
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
				component = new EntityLink(
					{
						entity: TestPerson
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render with class "inactive"',
			() => {
				component = new EntityLink(
					{
						entity: {
							...TestPerson,
							inactive: true
						}
					}
				);

				expect(component.element.classList.contains('inactive')).toBeTruthy();
			}
		);

		it(
			'should disable link href',
			() => {
				component = new EntityLink(
					{
						entity: TestPerson
					}
				);

				expect(component.element.href).toBe(TestPerson.displayURL);

				component.props.disabled = true;

				jest.runAllTimers();

				expect(component.element.href).toBe('javascript:;');
			}
		);

		it(
			'should render hashtag before a topic',
			() => {
				component = new EntityLink(
					{
						entity: TestTopic
					}
				);

				expect(component.element.innerHTML).toBe(`#${TestTopic.name}`);

				component.props.showTrigger = false;

				jest.runAllTimers();

				expect(component.element.innerHTML).toBe(TestTopic.name);
			}
		);
	}
);