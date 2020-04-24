import {Map} from 'immutable';

import SeeMore from '../SeeMore';

describe(
	'SeeMore',
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
				component = new SeeMore();

				expect(component.element).toBeNull();
			}
		);

		it(
			'renders seeMoreLink if expanded is false and truncated is true',
			() => {
				component = new SeeMore(
					{
						expanded: false,
						messageInfoIMap: Map(),
						truncated: true
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders see-all in seeMoreLink if an id is passed in, expanded is true, and messageInfo truncated is true',
			() => {
				component = new SeeMore(
					{
						expanded: true,
						id: 1,
						messageInfoIMap: Map(
							{
								truncated: true
							}
						),
						truncated: true
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call onSeemore on onSeemoreclick_',
			() => {
				component = new SeeMore(
					{
						expanded: false,
						messageInfoIMap: Map(),
						onSeeMore: jest.fn(),
						truncated: true
					}
				);

				const eventObj = {
					preventDefault: jest.fn(),
					stopImmediatePropagation: jest.fn()
				};

				component.onSeeMoreClick_(eventObj);

				expect(component.props.onSeeMore).toBeCalled();
				expect(eventObj.preventDefault).toBeCalled();
				expect(eventObj.stopImmediatePropagation).toBeCalled();
			}
		);
	}
);