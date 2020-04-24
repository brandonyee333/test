import IconListItem from '../IconListItem';

describe(
	'IconListItem',
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
				component = new IconListItem(
					{
						icon: 'skype',
						label: 'testTest',
						url: 'skype:testTest?chat'
					}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);