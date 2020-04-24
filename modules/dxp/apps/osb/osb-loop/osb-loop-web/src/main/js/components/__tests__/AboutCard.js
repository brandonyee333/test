import AboutCard from '../AboutCard';

describe(
	'AboutCard',
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
				component = new AboutCard({message: ''});

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render details when passed',
			() => {
				component = new AboutCard(
					{
						details: {
							name: 'test',
							title: 'developer'
						},
						message: ''
					}
				);

				const innerHTML = component.element.innerHTML;

				expect(innerHTML).toContain('name');
				expect(innerHTML).toContain('test');
				expect(innerHTML).toContain('title');
				expect(innerHTML).toContain('developer');
			}
		);
	}
);