import dom from 'metal-dom';
import {range} from 'lodash';

import AutocompleteMenu, {getAcItem, itemDown, itemUp} from '../AutocompleteMenu';

describe(
	'AutocompleteMenu',
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
				component = new AutocompleteMenu({items: []});

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call onClick with item data',
			() => {
				const ITEM = {data: {name: 'test'}};
				const spy = jest.fn();

				component = new AutocompleteMenu(
					{
						itemRenderer: jest.fn(),
						items: [ITEM],
						onClick: spy
					}
				);

				dom.triggerEvent(component.element.querySelector('.menu-item'), 'click');

				jest.runAllTimers();

				expect(spy).toBeCalledWith(ITEM);
			}
		);

		it(
			'should call onMouseEnter with current index',
			() => {
				const ITEM = {name: 'test'};
				const spy = jest.fn();

				component = new AutocompleteMenu(
					{
						itemRenderer: jest.fn(),
						items: [{data: ITEM}],
						onMouseEnter: spy
					}
				);

				dom.triggerEvent(component.element.querySelector('.menu-item'), 'mouseover');

				jest.runAllTimers();

				expect(spy).toBeCalled();
			}
		);

		it(
			'should increment item up and start at bottom when index is 0',
			() => {
				const TOTAL_ITEMS = 10;

				expect(itemUp(TOTAL_ITEMS, 9)).toBe(8);
				expect(itemUp(TOTAL_ITEMS, 1)).toBe(0);
				expect(itemUp(TOTAL_ITEMS, 0)).toBe(9);
			}
		);

		it(
			'should increment item down and start at top when index exceeds total items',
			() => {
				const TOTAL_ITEMS = 10;

				expect(itemDown(TOTAL_ITEMS, 0)).toBe(1);
				expect(itemDown(TOTAL_ITEMS, 5)).toBe(6);
				expect(itemDown(TOTAL_ITEMS, 9)).toBe(0);
			}
		);

		it(
			'should return an item from an array of objects',
			() => {
				const ITEMS = [
					{index: 0},
					{
						items: [
							{index: 1},
							{
								items: [{index: 2}]
							},
							{index: 3}
						]
					},
					{index: 4}
				];

				range(5).forEach(i => expect(getAcItem(ITEMS, i).index).toBe(i));

				expect(getAcItem(ITEMS, 5)).toBe(null);
			}
		);

		it(
			'should call onClickOutside prop if target node is not a child of menu',
			() => {
				const ITEM = {name: 'test'};
				const spy = jest.fn();

				document.body.innerHTML = '<div id="emptyNode"></div>';

				component = new AutocompleteMenu(
					{
						itemRenderer: jest.fn(),
						items: [{data: ITEM}],
						onClickOutside: spy
					}
				);

				dom.triggerEvent(component.element.querySelector('.menu-item'), 'click');

				jest.runAllTimers();

				expect(spy).not.toBeCalled();

				dom.triggerEvent(document.getElementById('emptyNode'), 'click');

				jest.runAllTimers();

				expect(spy).toBeCalled();
			}
		);
	}
);