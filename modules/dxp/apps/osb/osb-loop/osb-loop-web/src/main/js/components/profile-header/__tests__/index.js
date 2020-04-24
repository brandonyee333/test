jest.unmock('../../../actions/overlays');
jest.unmock('../../../lib/lang-util');
jest.unmock('../../../lib/selectors');
jest.unmock('../../../lib/util');

import Component, {Config} from 'metal-jsx';
import dom from 'metal-dom';
import {fromJS, Map} from 'immutable';
import {Provider} from 'metal-redux';

import LoopAssets from '../../../tests/loop-assets';
import ProfileHeader from '../index';
import mockStore from '../../../tests/mock-store';

const {classNameIds} = LoopConstants;

class ProfileHeaderExample extends Component {
	render() {
		const {classNameId, id} = this.props;

		const store = mockStore(
			fromJS(
				{
					people: Map().setIn(
						[0, 'data'],
						fromJS(
							{
								...LoopAssets.getPerson(0),
								permissionEdit: true
							}
						)
					).setIn(
						[1, 'data'],
						fromJS(
							{
								...LoopAssets.getPerson(1),
								inactive: true,
								permissionEdit: false
							}
						)
					),
					topics: Map().setIn(
						[0, 'data'],
						fromJS(LoopAssets.getTopic(0))
					)
				}
			)
		);

		return (
			<Provider store={store}>
				<ProfileHeader classNameId={classNameId} id={id} />
			</Provider>
		);
	}
}

ProfileHeaderExample.PROPS = {
	classNameId: Config.number().value(classNameIds.people),
	id: Config.number().value(0)
};

describe(
	'ProfileHeader',
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
				component = new ProfileHeaderExample();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should display as an active user',
			() => {
				component = new ProfileHeaderExample();

				const element = component.element;

				expect(element.querySelector('.disabled')).toBeFalsy();
				expect(element.querySelector('.follow-button-container')).toBeTruthy();
			}
		);

		it(
			'should display as a disabled user',
			() => {
				component = new ProfileHeaderExample({id: 1});

				const element = component.element;

				expect(element.querySelector('.disabled')).toBeTruthy();
				expect(element.querySelector('.follow-button-container')).toBeFalsy();
			}
		);

		it(
			'should display cover image edit controls for a person entity',
			() => {
				component = new ProfileHeaderExample();

				const element = component.element;

				dom.triggerEvent(element, 'mouseover');

				jest.runAllTimers();

				expect(element.querySelector('.loop-icon-camera')).toBeTruthy();
			}
		);

		it(
			'should not display cover image edit controls for a person entity',
			() => {
				component = new ProfileHeaderExample({id: 1});

				const element = component.element;

				dom.triggerEvent(element, 'mouseover');

				jest.runAllTimers();

				expect(element.querySelector('.loop-icon-camera')).toBeFalsy();
			}
		);

		it(
			'should display cover image edit controls for a topic entity',
			() => {
				component = new ProfileHeaderExample({classNameId: classNameIds.topics});

				const element = component.element;

				dom.triggerEvent(element, 'mouseover');

				jest.runAllTimers();

				expect(element.querySelector('.loop-icon-camera')).toBeTruthy();
			}
		);
	}
);