import GlobalMenu from '../GlobalMenu';

describe(
	'GlobalMenu',
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
				component = new GlobalMenu(
					{
						entity: LoopConstants.currentPerson
					}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);