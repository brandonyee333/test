jest.unmock('../../../lib/selectors');

import {dom} from 'metal-dom';
import {noop} from 'lodash';

import SortableListHeader from '../SortableListHeader';

const SORTABLE_COLUMNS = {
	creator: {
		name: 'Creator',
		value: 'creator'
	},
	secondaryInfo: {
		name: 'Date',
		value: 'secondaryInfo'
	},
	title: {
		name: 'File Name',
		value: 'title'
	}
};

describe(
	'SortaListHeader',
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
				component = new SortableListHeader(
					{
						onSortClick: noop,
						reverseSort: true,
						sortableColumns: SORTABLE_COLUMNS
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'calls onSortClick on click',
			() => {
				const spy = jest.fn();

				component = new SortableListHeader(
					{
						onSortClick: spy,
						reverseSort: true,
						sortableColumns: SORTABLE_COLUMNS
					}
				);

				dom.triggerEvent(component.element.querySelector('.creator-header'), 'click');
				dom.triggerEvent(component.element.querySelector('.secondary-info-header'), 'click');
				dom.triggerEvent(component.element.querySelector('.title-header'), 'click');

				expect(spy).toHaveBeenCalledTimes(3);
			}
		);

		it(
			'renders with title column selected ascending',
			() => {
				component = new SortableListHeader(
					{
						onSortClick: noop,
						reverseSort: true,
						selectedField: 'title',
						sortableColumns: SORTABLE_COLUMNS
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders with title column selected descending',
			() => {
				component = new SortableListHeader(
					{
						onSortClick: noop,
						reverseSort: false,
						selectedField: 'title',
						sortableColumns: SORTABLE_COLUMNS
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders with creator column selected ascending',
			() => {
				component = new SortableListHeader(
					{
						onSortClick: noop,
						reverseSort: true,
						selectedField: 'creator',
						sortableColumns: SORTABLE_COLUMNS
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders with creator column selected descending',
			() => {
				component = new SortableListHeader(
					{
						onSortClick: noop,
						reverseSort: false,
						selectedField: 'creator',
						sortableColumns: SORTABLE_COLUMNS
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders with secondaryInfo column selected ascending',
			() => {
				component = new SortableListHeader(
					{
						onSortClick: noop,
						reverseSort: true,
						selectedField: 'secondaryInfo',
						sortableColumns: SORTABLE_COLUMNS
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders with secondaryInfo column selected descending',
			() => {
				component = new SortableListHeader(
					{
						onSortClick: noop,
						reverseSort: false,
						selectedField: 'secondaryInfo',
						sortableColumns: SORTABLE_COLUMNS
					}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);