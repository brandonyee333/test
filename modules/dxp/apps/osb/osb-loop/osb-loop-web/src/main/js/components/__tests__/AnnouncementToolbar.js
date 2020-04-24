jest.unmock('../../lib/selectors');
jest.unmock('../../lib/util');
jest.unmock('../../lib/lang-util');

import {fromJS, Map} from 'immutable';

import AnnouncementToolbar from '../AnnouncementToolbar';
import dom from 'metal-dom';
import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';
import sendRequest from '../../lib/request';

const {classNameIds, currentPerson} = LoopConstants;

describe(
	'AnnouncementToolbar',
	() => {
		let component;

		const id = currentPerson.entityClassPK;

		const store = mockStore(
			Map().mergeIn(
				['people', id, 'data'],
				fromJS(LoopAssets.getPerson(id))
			)
		);

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
				component = new AnnouncementToolbar(
					{
						dataRequest: sendRequest,
						postAsClassNameId: classNameIds.people,
						postAsId: id,
						store
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'calls onChange for handleEmailCheckboxChange',
			() => {
				const spy = jest.fn();

				component = new AnnouncementToolbar(
					{
						dataRequest: sendRequest,
						onChange: spy,
						postAsClassNameId: classNameIds.people,
						postAsId: id,
						store
					}
				);

				dom.triggerEvent(component.element.querySelector('.checkbox-input'), 'change');

				jest.runAllTimers();

				expect(spy).toBeCalled();
			}
		);
	}
);