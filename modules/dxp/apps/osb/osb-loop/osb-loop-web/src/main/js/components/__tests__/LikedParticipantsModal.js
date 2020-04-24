import {noop} from 'lodash';

import sendRequest from '../../lib/request';
import {LikedParticipantsModal} from '../LikedParticipantsModal';

describe(
	'LikedParticipantsModal',
	() => {
		let component;

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}

				sendRequest.mockClear();
			}
		);

		it(
			'renders',
			() => {
				component = new LikedParticipantsModal(
					{
						hideModal: noop,
						id: 1
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'calls sendRequest on created',
			() => {
				expect(sendRequest).not.toBeCalled();

				component = new LikedParticipantsModal(
					{
						hideModal: noop,
						id: 1
					}
				);

				expect(sendRequest).toBeCalled();
			}
		);
	}
);