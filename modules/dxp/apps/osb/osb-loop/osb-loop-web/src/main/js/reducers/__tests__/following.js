jest.unmock('../../actions/following');
jest.unmock('../../lib/util');
jest.unmock('../following');

import {fromJS, Map, OrderedSet} from 'immutable';

import reducer from '../following';
import {actionTypes} from '../../actions/following';

const {classNameIds} = LoopConstants;

describe(
	'Following Reducer',
	() => {
		it(
			'should be a function',
			() => {
				expect(typeof reducer).toBe('function');
			}
		);

		it(
			'should update data on follow success',
			() => {
				const id = 1;

				const action = {
					data: {
						followersCount: 2,
						following: true,
						followingType: 2,
						notifying: true,
						notifyingEmail: true
					},
					entityClassNameId: classNameIds.people,
					id,
					type: actionTypes.FOLLOW_ENTITY_SUCCESS
				};

				const state = reducer(
					Map(
						{
							people: Map().mergeIn(
								[id],
								{
									data: {
										followersCount: 1,
										following: false,
										followingType: 1,
										name: 'test',
										notifying: false,
										notifyingEmail: false
									}
								}
							)
						}
					),
					action
				);

				const data = state.getIn(['people', id, 'data']);

				expect(data.get('followersCount')).toBe(2);
				expect(data.get('following')).toBe(true);
				expect(data.get('followingType')).toBe(2);
				expect(data.get('name')).toBe('test');
				expect(data.get('notifying')).toBe(true);
				expect(data.get('notifyingEmail')).toBe(true);
			}
		);

		it(
			'should remove from data if folowing is false',
			() => {
				const id = 1;

				const action = {
					data: {
						followersCount: 1,
						following: false,
						followingType: 1,
						notifying: false,
						notifyingEmail: false
					},
					entityClassNameId: classNameIds.people,
					id,
					type: actionTypes.FOLLOW_ENTITY_SUCCESS
				};

				const state = reducer(
					Map(
						{
							people: Map().set(
								id,
								fromJS(
									{
										data: {
											followersCount: 2,
											following: true,
											followingType: 2,
											name: 'test',
											notifying: true,
											notifyingEmail: true
										}
									}
								)
							)
						}
					),
					action
				);

				const data = state.getIn(['people', id, 'data']);

				expect(data.get('followersCount')).toBe(1);
				expect(data.get('following')).toBe(false);
				expect(data.get('followingType')).toBe(1);
				expect(data.get('name')).toBe('test');
				expect(data.get('notifying')).toBe(false);
				expect(data.get('notifyingEmail')).toBe(false);

				const followingIOSet = state.getIn(['people', LoopConstants.currentPerson.entityClassPK, 'following', 'people', 'data']);

				expect(followingIOSet.size).toBe(0);
				expect(followingIOSet).toBe(OrderedSet());
			}
		);
	}
);