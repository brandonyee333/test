jest.unmock('../../lib/selectors');

import {fromJS, Map} from 'immutable';

import DivisionMembersCard from '../DivisionMembersCard';
import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';
import {fetchLeads, fetchMembers} from '../../actions/divisions';

describe(
	'DivisionMembersCard',
	() => {
		let component;

		const mockRet = {type: 'test'};

		const store = mockStore(
			Map().setIn(
				['divisions', 0, 'data'],
				fromJS(LoopAssets.getDivision())
			)
		);

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}
			}
		);

		beforeEach(
			() => {
				fetchLeads.mockReturnValue(mockRet);
				fetchMembers.mockReturnValue(mockRet);
			}
		);

		it(
			'renders',
			() => {
				component = new DivisionMembersCard(
					{
						id: 0,
						store
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call fetchLeads when created',
			() => {
				component = new DivisionMembersCard(
					{
						id: 0,
						store
					}
				);

				expect(fetchLeads).toBeCalled();

				fetchMembers.mockClear();
			}
		);

		it(
			'should call fetchMembers when created',
			() => {
				component = new DivisionMembersCard(
					{
						id: 0,
						store
					}
				);

				expect(fetchMembers).toBeCalled();

				fetchMembers.mockClear();
			}
		);
	}
);