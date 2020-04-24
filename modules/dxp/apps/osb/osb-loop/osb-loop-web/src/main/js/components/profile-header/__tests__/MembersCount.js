jest.unmock('../../../lib/lang-util');

import LoopAssets from '../../../tests/loop-assets';
import MembersCount from '../MembersCount';
import mockStore from '../../../tests/mock-store';

describe(
	'MembersCount',
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

				component = new MembersCount(
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