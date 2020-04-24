import ParentEntity from '../ParentEntity';

describe(
	'ParentEntity',
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
				component = new ParentEntity();

				expect(component).toBeTruthy();
			}
		);
	}
);