jest.unmock('../../actions/overlays');
jest.unmock('../../lib/util');

import Component from 'metal-jsx';
import Promise from 'metal-promise';
import {fromJS, Map} from 'immutable';
import {Provider} from 'metal-redux';

import FollowButton from '../FollowButton';
import mockStore from '../../tests/mock-store';
import {addAlert} from '../../actions/alerts';
import {follow} from '../../actions/following';

const {classNameIds} = LoopConstants;

const store = mockStore(
	fromJS(
		{
			people: Map().set(
				0,
				Map().setIn(['data', 'following'], true)
			).set(
				1,
				Map().setIn(['data', 'following'], false)
			)
		}
	)
);

class FollowButtonExample extends Component {
	render() {
		return (
			<Provider store={store}>
				<FollowButton {...this.otherProps()} ref="followButton" />
			</Provider>
		);
	}
}

describe(
	'FollowButton',
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
				component = new FollowButtonExample();

				expect(component.element.classList.contains('btn')).toBe(true);
				expect(component.element.classList.contains('active')).toBe(false);
			}
		);

		it(
			'should render appropriately when not following',
			() => {
				component = new FollowButtonExample({classNameId: classNameIds.people, id: 1});

				expect(component.element.classList.contains('following')).toBe(false);

				expect(component.element.querySelector('.loop-icon-label').textContent).toBe('follow');

				expect(component.element.querySelector('.dropdown-arrow')).toBeFalsy();
			}
		);

		it(
			'should render appropriately when following',
			() => {
				component = new FollowButtonExample({classNameId: classNameIds.people, id: 0});

				expect(component.element.classList.contains('following')).toBe(true);

				expect(component.element.querySelector('.loop-icon-label').textContent).toBe('following');

				expect(component.element.querySelector('.dropdown-arrow')).toBeTruthy();
			}
		);

		it(
			'should call  follow & addAlert',
			() => {
				follow.mockReturnValue(Promise.resolve());

				component = new FollowButtonExample({classNameId: classNameIds.people, id: 1});

				const connectFn = component.components.followButton;

				connectFn.components.child.handleClick();

				jest.runAllTimers();

				expect(addAlert).toBeCalled();
				expect(follow).toBeCalled();

				follow.mockClear();
			}
		);
	}
);