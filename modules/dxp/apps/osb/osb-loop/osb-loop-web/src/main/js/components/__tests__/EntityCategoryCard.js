import Component from 'metal-jsx';
import {Provider} from 'metal-redux';
import {range} from 'lodash';

import EntityCategoryCard from '../EntityCategoryCard';
import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';

const people_10 = range(0, 10).map(
	item => LoopAssets.getPerson(item)
);

class EntityCategoryCardExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<EntityCategoryCard {...this.otherProps()} />
			</Provider>
		);
	}
}

describe(
	'EntityCategoryCard',
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
				component = new EntityCategoryCardExample(
					{
						categories: [],
						loading: false,
						seeMoreLinkData: {
							link: '#',
							message: '#'
						}
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should not render items',
			() => {
				component = new EntityCategoryCardExample(
					{
						categories: [],
						loading: false,
						seeMoreLinkData: {
							link: '#',
							message: ''
						}
					}
				);

				expect(component.element.querySelector('.category')).toBeFalsy();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render items',
			() => {
				component = new EntityCategoryCardExample(
					{
						categories: [
							{
								items: people_10,
								label: 'Group 2'
							}
						],
						loading: false,
						seeMoreLinkData: {
							link: '#',
							message: ''
						}
					}
				);

				expect(component.element.querySelector('.category')).toBeTruthy();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render as column',
			() => {
				component = new EntityCategoryCardExample(
					{
						categories: [
							{
								items: people_10,
								label: 'Group 2'
							}
						],
						displayDirection: 'column',
						loading: false,
						seeMoreLinkData: {
							link: '#',
							message: ''
						}
					}
				);

				expect(component.element.querySelector('.category-column')).toBeTruthy();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render as row',
			() => {
				component = new EntityCategoryCardExample(
					{
						categories: [
							{
								items: people_10,
								label: 'Group 2'
							}
						],
						loading: false,
						seeMoreLinkData: {
							link: '#',
							message: ''
						}
					}
				);

				expect(component.element.querySelector('.category-row')).toBeTruthy();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render as medium',
			() => {
				component = new EntityCategoryCardExample(
					{
						categories: [
							{
								items: people_10,
								label: 'Group 2'
							}
						],
						displaySize: 'medium',
						loading: false,
						seeMoreLinkData: {
							link: '#',
							message: ''
						}
					}
				);

				expect(document.querySelector('.medium')).toBeTruthy();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render as small',
			() => {
				component = new EntityCategoryCardExample(
					{
						categories: [
							{
								items: people_10,
								label: 'Group 2'
							}
						],
						displaySize: 'small',
						loading: false,
						seeMoreLinkData: {
							link: '#',
							message: ''
						}
					}
				);

				expect(document.querySelector('.small')).toBeTruthy();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render See More',
			() => {
				component = new EntityCategoryCardExample(
					{
						categories: [
							{
								items: people_10,
								label: 'Group 2'
							}
						],
						loading: false,
						seeMoreLinkData: {
							link: '#',
							message: '#'
						}
					}
				);

				expect(component.element.querySelector('.card-footer-container')).toBeTruthy();

				expect(component).toMatchSnapshot();
			}
		);
	}
);