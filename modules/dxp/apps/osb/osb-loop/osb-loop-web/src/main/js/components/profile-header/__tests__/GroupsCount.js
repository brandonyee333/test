jest.unmock('../../../lib/lang-util');

import GroupsCount from '../GroupsCount';
import LoopAssets from '../../../tests/loop-assets';
import mockStore from '../../../tests/mock-store';

describe(
	'GroupsCount',
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
				const {displayURL, entityClassNameId, id} = LoopAssets.getPerson();

				component = new GroupsCount(
					{
						displayURL,
						entityClassNameId,
						id,
						store: mockStore()
					}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);