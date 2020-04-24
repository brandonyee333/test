jest.unmock('../../../lib/selectors');

import {fromJS, Map} from 'immutable';

import JobTitleSelect from '../JobTitleSelect';
import mockStore from '../../../tests/mock-store';
import sendRequest from '../../../lib/request';

const ID = 0;

const store = mockStore(
	Map().mergeIn(
		['lists', 'jobTitles', 'data'],
		[ID]
	).mergeIn(
		['jobTitles', ID],
		fromJS(LoopAssets.getJobTitle(ID))
	)
);

describe(
	'JobTitleSelect',
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
				component = new JobTitleSelect(
					{
						name: 'foo',
						store
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render with jobTitles',
			() => {
				component = new JobTitleSelect(
					{
						name: 'foo',
						store
					}
				);

				component.state.jobTitles_ = [{entityClassPK: 1, name: 'foo'}];

				jest.runAllTimers();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call `sendRequest` and set state in promise callback',
			() => {
				component = new JobTitleSelect(
					{
						disabled: true,
						name: 'foo',
						store
					}
				);

				const items = {
					results: [1, 2, 3]
				};

				sendRequest.mockImplementation(() => Promise.resolve(items));

				return component.fetchJobTitles_().then(
					() => {
						expect(sendRequest).toHaveBeenCalled();
						expect(component.state.jobTitles_).toEqual(items.results);
					}
				);
			}
		);
	}
);