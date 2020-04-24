jest.unmock('../overlays');

import {hideOverlay, showOverlay} from '../overlays';

describe(
	'Overlays',
	() => {
		it(
			'should call hideOverlay action',
			() => {
				const action = hideOverlay(1);

				expect(typeof action).toBe('object');
				expect(action.type).toBe('HIDE_OVERLAY');
			}
		);

		it(
			'should call showOverlay action',
			() => {
				const overlayProps = {id: 1};
				const overlayType = 'ENTITY_SUMMARY';

				const action = showOverlay({overlayProps, overlayType});

				expect(typeof action).toBe('object');
				expect(action.overlayProps).toBe(overlayProps);
				expect(action.overlayType).toBe(overlayType);
				expect(action.type).toBe('SHOW_OVERLAY');
			}
		);
	}
);