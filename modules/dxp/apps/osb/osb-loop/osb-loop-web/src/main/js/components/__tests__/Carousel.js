import Carousel from '../Carousel';

describe(
	'Carousel',
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
				component = new Carousel();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should decrement page',
			() => {
				component = new Carousel(
					{
						children: [1, 2, 3],
						itemsPerPage: 1
					}
				);

				component.state.currentPage_ = 1;

				const eventObj = {
					preventDefault: jest.fn(),
					stopImmediatePropagation: jest.fn()
				};

				component.handleDecrementPage_(eventObj);

				expect(component.state.currentPage_).toEqual(0);
				expect(component.state.previousPage_).toEqual(1);
				expect(eventObj.preventDefault).toBeCalled();
				expect(eventObj.stopImmediatePropagation).toBeCalled();
			}
		);

		it(
			'should increment page',
			() => {
				component = new Carousel(
					{
						children: [1, 2, 3],
						itemsPerPage: 1
					}
				);

				component.state.currentPage_ = 0;

				const eventObj = {
					preventDefault: jest.fn(),
					stopImmediatePropagation: jest.fn()
				};

				component.handleIncrementPage_(eventObj);

				expect(component.state.currentPage_).toEqual(1);
				expect(component.state.previousPage_).toEqual(0);
				expect(eventObj.preventDefault).toBeCalled();
				expect(eventObj.stopImmediatePropagation).toBeCalled();
			}
		);

		it(
			'should set page index',
			() => {
				component = new Carousel(
					{
						children: []
					}
				);

				component.state.currentPage_ = 0;

				component.handleSetPage_(1);

				expect(component.state.currentPage_).toEqual(1);
				expect(component.state.previousPage_).toEqual(0);
			}
		);
	}
);