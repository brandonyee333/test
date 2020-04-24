jest.unmock('metal-position');

import dom from 'metal-dom';
import {range} from 'lodash';

import DropDown from '../DropDown';
import LoopAssets from '../../../tests/loop-assets';

describe(
	'DropDown',
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
				component = new DropDown(
					{
						clearCount: jest.fn(),
						count: 0,
						items,
						newFeedRequest: jest.fn(),
						onItemClick: jest.fn(),
						onLoadMore: jest.fn(),
						onLoadNew: jest.fn()
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call getPageTitle_',
			() => {
				component = new DropDown(
					{
						clearCount: jest.fn(),
						count: 0,
						items,
						newFeedRequest: jest.fn(),
						onItemClick: jest.fn(),
						onLoadMore: jest.fn(),
						onLoadNew: jest.fn()
					}
				);

				component.getPageTitle_ = jest.fn();

				component.props.count = 6;

				jest.runAllTimers();

				expect(component.getPageTitle_).toBeCalled();
			}
		);

		it(
			'should set `showSettings_` to false',
			() => {
				component = new DropDown(
					{
						clearCount: jest.fn(),
						count: 0,
						items,
						newFeedRequest: jest.fn(),
						onItemClick: jest.fn(),
						onLoadMore: jest.fn(),
						onLoadNew: jest.fn()
					}
				);

				component.state.showSettings_ = true;

				component.onBackClick_();

				expect(component.state.showSettings_).toBe(false);
			}
		);

		it(
			'should call onClickOutside_',
			() => {
				component = new DropDown(
					{
						clearCount: jest.fn(),
						count: 0,
						items,
						newFeedRequest: jest.fn(),
						onItemClick: jest.fn(),
						onLoadMore: jest.fn(),
						onLoadNew: jest.fn()
					}
				);

				component.onClickOutside_ = jest.fn();

				dom.triggerEvent(component.element.querySelector('.toggle-icon'), 'click');

				jest.runAllTimers();

				dom.triggerEvent(document.body, 'mousedown');

				jest.runAllTimers();

				expect(component.onClickOutside_).toBeCalled();
			}
		);

		it(
			'should call onDropDownToggle_',
			() => {
				const spy = jest.fn();

				component = new DropDown(
					{
						clearCount: jest.fn(),
						count: 0,
						items,
						newFeedRequest: jest.fn(),
						onItemClick: jest.fn(),
						onLoadMore: jest.fn(),
						onLoadNew: spy
					}
				);

				component.state.open_ = false;

				dom.triggerEvent(component.element.querySelector('.toggle-icon'), 'click');

				expect(spy).not.toBeCalled();

				component.state.open_ = true;

				dom.triggerEvent(component.element.querySelector('.toggle-icon'), 'click');

				jest.runAllTimers();

				expect(spy).toBeCalled();
			}
		);

		it(
			'should call onLoadNew',
			() => {
				const spy = jest.fn();

				component = new DropDown(
					{
						clearCount: jest.fn(),
						count: 0,
						items,
						newFeedRequest: jest.fn(),
						onItemClick: jest.fn(),
						onLoadMore: jest.fn(),
						onLoadNew: spy
					}
				);

				component.state.open_ = false;

				dom.triggerEvent(component.element.querySelector('.toggle-icon'), 'click');

				jest.runAllTimers();

				expect(spy).toBeCalled();
			}
		);

		it(
			'should call onShowSettingsClick_',
			() => {
				component = new DropDown(
					{
						clearCount: jest.fn(),
						count: 0,
						items,
						newFeedRequest: jest.fn(),
						onItemClick: jest.fn(),
						onLoadMore: jest.fn(),
						onLoadNew: jest.fn()
					}
				);

				component.onShowSettingsClick_ = jest.fn();

				dom.triggerEvent(component.element.querySelector('.toggle-icon'), 'click');

				jest.runAllTimers();

				dom.triggerEvent(component.element.querySelector('.settings-toggle'), 'click');

				jest.runAllTimers();

				expect(component.onShowSettingsClick_).toBeCalled();
			}
		);

		it(
			'should set scrollPosition_ to 0',
			() => {
				component = new DropDown(
					{
						clearCount: jest.fn(),
						count: 1,
						items,
						newFeedRequest: jest.fn(),
						onItemClick: jest.fn(),
						onLoadMore: jest.fn(),
						onLoadNew: jest.fn()
					}
				);

				component.state.open_ = true;

				jest.runAllTimers();

				component._scrollPosition = 200;

				expect(component._scrollPosition).toBe(200);

				component.onDropDownToggle_();

				expect(component._scrollPosition).toBe(0);
			}
		);

		it(
			'should set scrollPosition_ to 200',
			() => {
				component = new DropDown(
					{
						clearCount: jest.fn(),
						count: 0,
						items,
						newFeedRequest: jest.fn(),
						onItemClick: jest.fn(),
						onLoadMore: jest.fn(),
						onLoadNew: jest.fn()
					}
				);

				component.state.open_ = true;

				jest.runAllTimers();

				component.refs.feed.element.scrollTop = 200;

				expect(component._scrollPosition).toBe(0);

				component.onDropDownToggle_();

				expect(component._scrollPosition).toBe(200);
			}
		);

		it(
			'should not set scrollPosition if feed does not exist',
			() => {
				component = new DropDown(
					{
						clearCount: jest.fn(),
						count: 1,
						items,
						newFeedRequest: jest.fn(),
						onItemClick: jest.fn(),
						onLoadMore: jest.fn(),
						onLoadNew: jest.fn()
					}
				);

				component.setState(
					{
						open_: false,
						showSettings_: true
					}
				);

				jest.runAllTimers();

				component._scrollPosition = 200;

				expect(component._scrollPosition).toBe(200);

				component.onDropDownToggle_();

				expect(component.refs.feed).toBeFalsy();
				expect(component._scrollPosition).toBe(200);
			}
		);
	}
);