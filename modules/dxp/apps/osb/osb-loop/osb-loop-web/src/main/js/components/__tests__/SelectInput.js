import Component, {Config} from 'metal-jsx';
import {bindAll, range} from 'lodash';
import dom from 'metal-dom';

import LoopAssets from '../../tests/loop-assets';
import SelectInput from '../SelectInput';

describe(
	'SelectInput',
	() => {
		const DROPDOWN_SELECTOR = '.drop-down';

		const ITEM_LIST_SELECTOR = '.item-list';

		const ITEMS = range(8).map(LoopAssets.getPerson);

		const SELECTED_ITEM_SELECTOR = '.selected-item';

		class TestSelectInput extends Component {
			created() {
				this.state.selectedItem = ITEMS[0];

				bindAll(
					this,
					'handleSelect',
					'renderItem'
				);
			}

			getId(entity) {
				return `${entity.entityClassNameId}_${entity.entityClassPK}`;
			}

			handleSelect(entity) {
				this.state.selectedItem = entity;
			}

			renderItem(item, selected) {
				return [<div class="display-item" key={this.getId(item)}>{item.name}</div>];
			}

			render() {
				return (
					<SelectInput
						getId={this.getId}
						itemRenderer={this.renderItem}
						items={this.props.items}
						onSelect={this.handleSelect}
						ref="selectInput"
						selectedItem={this.state.selectedItem}
						selectId="UIKitSelectInput"
					/>
				);
			}
		}

		TestSelectInput.PROPS = {
			items: Config.array().value(ITEMS)
		};

		TestSelectInput.STATE = {
			selectedItem: Config.object()
		};

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
				component = new TestSelectInput();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders the list on click',
			() => {
				component = new TestSelectInput();

				const {element} = component;

				const {name} = ITEMS[0];

				expect(element.querySelector(DROPDOWN_SELECTOR)).toBeNull();

				dom.triggerEvent(element.querySelector(SELECTED_ITEM_SELECTOR), 'click');

				jest.runAllTimers();

				expect(element.querySelector(DROPDOWN_SELECTOR).innerHTML).toContain(name);
			}
		);

		it(
			'correctly renders newly selected item',
			() => {
				component = new TestSelectInput();

				const {element} = component;

				dom.triggerEvent(component.element.querySelector(SELECTED_ITEM_SELECTOR), 'click');

				jest.runAllTimers();

				const node = component.element.querySelector(ITEM_LIST_SELECTOR).children[2];

				const name = node.textContent;

				expect(element.querySelector(SELECTED_ITEM_SELECTOR).innerHTML).not.toContain(name);
				expect(component.state.selectedItem.name).not.toBe(name);

				dom.triggerEvent(node, 'click');

				jest.runAllTimers();

				expect(element.querySelector(SELECTED_ITEM_SELECTOR).innerHTML).toContain(name);
				expect(component.state.selectedItem.name).toBe(name);
			}
		);

		it(
			'should filter list on input',
			() => {
				component = new TestSelectInput();

				const {element} = component;

				dom.triggerEvent(element.querySelector(SELECTED_ITEM_SELECTOR), 'click');

				jest.runAllTimers();

				const count = element.querySelector(ITEM_LIST_SELECTOR).children.length;

				const inputNode = element.querySelector('input');

				inputNode.value = ITEMS[2].name;

				dom.triggerEvent(inputNode, 'input');

				jest.runAllTimers();

				expect(element.querySelector(ITEM_LIST_SELECTOR).children.length).toBeLessThan(count);
			}
		);

		it(
			'should update _filteredItems when items is updated',
			() => {
				const items = range(2).map(LoopAssets.getPerson);

				component = new TestSelectInput(
					{
						items
					}
				);

				const {selectInput} = component.components;

				expect(selectInput._filteredItems.length).toBe(2);

				component.props.items = [...items, LoopAssets.getPerson(2)];

				jest.runAllTimers();

				expect(selectInput._filteredItems.length).toBe(3);
			}
		);
	}
);