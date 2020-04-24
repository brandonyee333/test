import Promise from 'metal-promise';

import FollowMenu from '../FollowMenu';
import mockStore from '../../tests/mock-store';
import {addAlert} from '../../actions/alerts';
import {follow} from '../../actions/following';
import {notify, notifyEmail} from '../../actions/notifying';

describe(
	'FollowMenu',
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
				component = new FollowMenu(
					{
						store: mockStore()
					}
				);

				expect(component).toBeTruthy();
			}
		);

		it(
			'should call the notify action',
			() => {
				component = new FollowMenu(
					{
						store: mockStore()
					}
				);

				notify.mockReturnValue({type: 'test'});

				const childComponent = component.components.child;

				expect(notify).not.toBeCalled();

				childComponent.handleNotify_();

				expect(notify).toBeCalled();

				notify.mockClear();
			}
		);

		it(
			'should call the notifyEmail action',
			() => {
				component = new FollowMenu(
					{
						store: mockStore()
					}
				);

				notifyEmail.mockReturnValue({type: 'test'});

				const childComponent = component.components.child;

				expect(notifyEmail).not.toBeCalled();

				childComponent.handleNotifyEmail_();

				expect(notifyEmail).toBeCalled();

				notifyEmail.mockClear();
			}
		);

		it(
			'should call the notify and notifyEmail action when both notifying and notifyingEmail are true',
			() => {
				component = new FollowMenu(
					{
						store: mockStore()
					}
				);

				notify.mockReturnValue({type: 'test'});
				notifyEmail.mockReturnValue({type: 'test'});

				const childComponent = component.components.child;

				expect(notify).not.toBeCalled();
				expect(notifyEmail).not.toBeCalled();

				childComponent.props.notifying = true;
				childComponent.props.notifyingEmail = true;

				childComponent.handleNotify_();

				expect(notify).toBeCalled();
				expect(notifyEmail).toBeCalled();

				notify.mockClear();
				notifyEmail.mockClear();
			}
		);

		it(
			'should call the follow action on radioChange',
			() => {
				component = new FollowMenu(
					{
						store: mockStore()
					}
				);

				follow.mockReturnValue({type: 'test'});

				const childComponent = component.components.child;

				expect(follow).not.toBeCalled();

				childComponent.handleRadioChange_();

				expect(follow).toBeCalled();

				follow.mockClear();
			}
		);

		it(
			'should call the follow action and addAlert action on unfollowClick',
			() => {

				follow.mockReturnValue(Promise.resolve());

				component = new FollowMenu(
					{
						store: mockStore()
					}
				);

				const childComponent = component.components.child;

				expect(follow).not.toBeCalled();

				childComponent.handleUnfollowClick_();

				jest.runAllTimers();

				expect(addAlert).toBeCalled();
				expect(follow).toBeCalled();

				addAlert.mockClear();
			}
		);
	}
);