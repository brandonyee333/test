import Component from 'metal-jsx';

import MenuList from '../index';

describe(
	'MenuList',
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
				component = new MenuList();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders dividers, items, and labels',
			() => {
				const itemText = 'Item Text';
				const labelText = 'Label Text';

				class TestGroup extends Component {
					render() {
						return (
							<MenuList>
								<MenuList.Divider />
								<MenuList.Label>{labelText}</MenuList.Label>
								<MenuList.Item>{itemText}</MenuList.Item>
							</MenuList>
						);
					}
				}

				component = new TestGroup();

				expect(component.element.querySelector('.menu-divider-container')).toBeTruthy();
				expect(component.element.querySelector('.menu-item-container').innerHTML).toBe(itemText);
				expect(component.element.querySelector('.menu-label-container').innerHTML).toBe(labelText);
			}
		);

		it(
			'renders justify-between classname on item when passed justifyBetween={true}',
			() => {
				const itemText = 'Item Text';

				class TestGroup extends Component {
					render() {
						return (
							<MenuList>
								<MenuList.Item justifyBetween={true}>{itemText}</MenuList.Item>
							</MenuList>
						);
					}
				}

				component = new TestGroup();

				expect(component.element.querySelector('.menu-item-container').className).toContain('justify-between');
			}
		);

		it(
			'renders menu-link classname on item when href is passed in',
			() => {
				const itemText = 'Item Text';

				class TestGroup extends Component {
					render() {
						return (
							<MenuList>
								<MenuList.Item href="javascript:;">{itemText}</MenuList.Item>
							</MenuList>
						);
					}
				}

				component = new TestGroup();

				expect(component.element.querySelector('.menu-item-container').className).toContain('menu-link');
			}
		);

		it(
			'renders active classname on item when passed active={true}',
			() => {
				const itemText = 'Item Text';

				class TestGroup extends Component {
					render() {
						return (
							<MenuList>
								<MenuList.Item active={true}>{itemText}</MenuList.Item>
							</MenuList>
						);
					}
				}

				component = new TestGroup();

				expect(component.element.querySelector('.menu-item-container').className).toContain('active');
			}
		);
	}
);