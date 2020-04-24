import BookmarkFlag from '../BookmarkFlag';

describe(
	'BookmarkFlag',
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
				component = new BookmarkFlag();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders visibility based on bookmarked boolean',
			() => {
				component = new BookmarkFlag();

				expect(component.element.classList.contains('visible')).toBeFalsy();

				component.props.bookmarked = true;

				jest.runAllTimers();

				expect(component.element.classList.contains('visible')).toBeTruthy();

				expect(component).toMatchSnapshot();
			}
		);
	}
);