import FollowersCount from '../FollowersCount';

import LoopAssets from '../../../tests/loop-assets';
import mockStore from '../../../tests/mock-store';
import {getPluralMessage} from '../../../lib/lang-util';

describe(
	'FollowersCount',
	() => {
		let component;

		getPluralMessage.mockReturnValue([]);

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
				const {displayURL, entityClassNameId, id} = LoopAssets.getPerson();

				component = new FollowersCount(
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