jest.unmock('../../../lib/router-util');

import GlobalSearch from '../GlobalSearch';

describe(
	'GlobalSearch',
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
				component = new GlobalSearch();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should set focus state to true',
			() => {
				component = new GlobalSearch();

				expect(component.state.focused_).toBeFalsy();

				component.focusSearch_();

				expect(component.state.focused_).toBeTruthy();
			}
		);

		it(
			'should return an array of object results',
			() => {
				component = new GlobalSearch(
					{
						divisions: [1],
						people: [2],
						query: 'test',
						topics: [4]
					}
				);

				const expectedResult = [
					{
						data: {query: 'test'}
					},
					{
						items: [{data: 2}],
						title: 'people'
					},
					{
						items: [{data: 1}],
						title: 'groups'
					},
					{
						items: [{data: 4}],
						title: 'topics'
					}
				];

				const retVal = component.getDisplayResults();

				expect(retVal).toEqual(expectedResult);
			}
		);

		it(
			'should set ignoreBlur',
			() => {
				component = new GlobalSearch();

				component.setIgnoreBlur_(true);

				expect(component._ignoreBlur).toBe(true);

				component.setIgnoreBlur_(false);

				expect(component._ignoreBlur).toBe(false);
			}
		);

		it(
			'should set focused_ to false and call setIgnoreBlur_',
			() => {
				const spy = jest.fn();

				component = new GlobalSearch(
					{
						divisions: [],
						people: [],
						query: 'test',
						topics: []
					}
				);

				component.state.focused_ = true;
				component.setIgnoreBlur_ = spy;

				expect(component.state.focused_).toBe(true);
				expect(spy).not.toBeCalled();

				component.handleClickOutside_();

				expect(component.state.focused_).toBe(false);
				expect(spy).toBeCalled();
			}
		);
	}
);