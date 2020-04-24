import InfiniteScroll from '../InfiniteScroll';

describe(
	'InfiniteScroll',
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
				component = new InfiniteScroll(
					{
						children: <div>{'children'}</div>,
						onScrollEnd: jest.fn(() => Promise.resolve())
					}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);