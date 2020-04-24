import EntityInputList from '../EntityInputList';

import sendRequest from '../../../lib/request';

describe(
	'EntityInputList',
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
				component = new EntityInputList({name: 'foo'});

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call `sendRequest`',
			() => {
				component = new EntityInputList({name: 'foo'});

				component.fetchEntity_();

				expect(sendRequest).toHaveBeenCalled();
			}
		);
	}
);