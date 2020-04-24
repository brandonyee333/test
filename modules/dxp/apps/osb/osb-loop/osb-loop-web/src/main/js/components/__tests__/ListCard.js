import ListCard from '../ListCard';

describe(
	'ListCard Component',
	() => {
		const noIconNoURL = [
			{
				contant: 'test.test@liferay.com',
				label: 'mail',
				url: 'mailto:test.test@liferay.com'
			}
		];

		const withIconWithURL = [
			{
				contant: 'test.test@liferay.com',
				icon: 'mail',
				label: 'mail',
				url: 'mailto:test.test@liferay.com'
			}
		];

		const noCategory = [
			{
				contant: 'test.test@liferay.com',
				icon: 'mail',
				url: 'mailto:test.test@liferay.com'
			}
		];

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
				component = new ListCard();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should not render the category label',
			() => {
				component = new ListCard(
					{
						items: noCategory
					}
				);

				expect(component.element.querySelector('.category-label')).toBeFalsy();
			}
		);

		it(
			'should render the category label',
			() => {
				component = new ListCard(
					{
						items: withIconWithURL
					}
				);

				expect(component.element.querySelector('.category-label')).toBeTruthy();
			}
		);

		it(
			'should render the category-label',
			() => {
				component = new ListCard(
					{
						items: withIconWithURL
					}
				);

				expect(component.element.querySelector('.category-label')).toBeTruthy();
			}
		);

		it(
			'should not render IconListItem component',
			() => {
				component = new ListCard(
					{
						items: noIconNoURL
					}
				);

				expect(component.element.querySelector('.loop-icon-list-item-container')).toBeFalsy();
			}
		);

		it(
			'should render IconListItem component',
			() => {
				component = new ListCard(
					{
						items: withIconWithURL
					}
				);

				expect(component.element.querySelector('.loop-icon-list-item-container')).toBeTruthy();
			}
		);
	}
);