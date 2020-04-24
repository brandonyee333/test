import dom from 'metal-dom';
import {range} from 'lodash';

import Feed from '../Feed';
import LoopAssets from '../../../tests/loop-assets';

describe(
	'Feed',
	() => {
		const items = range(6).map(LoopAssets.getNotification);

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
				component = new Feed(
					{
						items,
						onItemClick: jest.fn(),
						onLoadMore: jest.fn()
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should not render view-more',
			() => {
				component = new Feed(
					{
						hasMoreItems: false,
						items,
						onItemClick: jest.fn(),
						onLoadMore: jest.fn()
					}
				);

				expect(component.element.querySelector('.view-more')).toBeFalsy();
			}
		);

		it(
			'should render view-more',
			() => {
				component = new Feed(
					{
						hasMoreItems: true,
						items,
						onItemClick: jest.fn(),
						onLoadMore: jest.fn()
					}
				);

				expect(component.element.querySelector('.view-more')).toBeTruthy();
			}
		);

		it(
			'should not render loading spinner',
			() => {
				component = new Feed(
					{
						hasMoreItems: true,
						items,
						loading: false,
						onItemClick: jest.fn(),
						onLoadMore: jest.fn()
					}
				);

				expect(component.element.querySelector('.spinner')).toBeFalsy();
			}
		);

		it(
			'should render loading spinner',
			() => {
				component = new Feed(
					{
						hasMoreItems: true,
						items,
						loading: true,
						onItemClick: jest.fn(),
						onLoadMore: jest.fn()
					}
				);

				expect(component.element.querySelector('.spinner')).toBeTruthy();
			}
		);

		it(
			'should call onLoadMore',
			() => {
				const spy = jest.fn();

				component = new Feed(
					{
						hasMoreItems: true,
						items,
						onItemClick: jest.fn(),
						onLoadMore: spy
					}
				);
				dom.triggerEvent(component.element.querySelector('.view-more'), 'click');

				jest.runAllTimers();

				expect(spy).toBeCalled();
			}
		);

		it(
			'should call onItemClick',
			() => {
				const spy = jest.fn();

				component = new Feed(
					{
						items,
						onItemClick: spy,
						onLoadMore: jest.fn()
					}
				);

				dom.triggerEvent(component.element.querySelector('.item'), 'click');

				jest.runAllTimers();

				expect(spy).toBeCalled();
			}
		);
	}
);