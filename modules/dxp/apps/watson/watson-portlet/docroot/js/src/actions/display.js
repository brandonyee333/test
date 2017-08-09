export const NAME = 'DISPLAY';

const actionTypes = {
	UPDATE_COLLAPSED_ENTRIES: 'UPDATE_COLLAPSED_ENTRIES',
	UPDATE_COLLAPSED_ENTRY: 'UPDATE_COLLAPSED_ENTRY',
	UPDATE_DISPLAY_BY: 'UPDATE_DISPLAY_BY',
	UPDATE_FILTER: 'UPDATE_FILTER',
	UPDATE_HIDE_LOADING_OVERLAY: 'UPDATE_HIDE_LOADING_OVERLAY',
	UPDATE_LAST_FOCUS: 'UPDATE_LAST_FOCUS',
	UPDATE_LAST_ITEMS_LOADED: 'UPDATE_LAST_ITEMS_LOADED',
	UPDATE_PAGE_TITLE: 'UPDATE_PAGE_TITLE',
	UPDATE_SORT_BY: 'UPDATE_SORT_BY'
};

const actions = {
	updateCollapsedEntries: data => {
		return {
			data,
			type: actionTypes.UPDATE_COLLAPSED_ENTRIES
		};
	},
	updateCollapsedEntry: data => {
		return {
			data,
			type: actionTypes.UPDATE_COLLAPSED_ENTRY
		};
	},
	updateDisplayBy: data => {
		return {
			data,
			type: actionTypes.UPDATE_DISPLAY_BY
		};
	},
	updateFilter: data => {
		return {
			data,
			type: actionTypes.UPDATE_FILTER
		};
	},
	updateHideLoadingOverlay: data => {
		return {
			data,
			type: actionTypes.UPDATE_HIDE_LOADING_OVERLAY
		};
	},
	updateLastFocus: data => {
		return {
			data,
			type: actionTypes.UPDATE_LAST_FOCUS
		};
	},
	updateLastItemsLoaded: data => {
		return {
			data,
			type: actionTypes.UPDATE_LAST_ITEMS_LOADED
		};
	},
	updatePageTitle: data => {
		return {
			data,
			type: actionTypes.UPDATE_PAGE_TITLE
		};
	},
	updateSortBy: data => {
		return {
			data,
			type: actionTypes.UPDATE_SORT_BY
		};
	}
};

const {
	updateCollapsedEntries,
	updateCollapsedEntry,
	updateDisplayBy,
	updateFilter,
	updateHideLoadingOverlay,
	updateLastFocus,
	updateLastItemsLoaded,
	updatePageTitle,
	updateSortBy
} = actions;

export {
	actionTypes,
	updateCollapsedEntries,
	updateCollapsedEntry,
	updateDisplayBy,
	updateFilter,
	updateHideLoadingOverlay,
	updateLastFocus,
	updateLastItemsLoaded,
	updatePageTitle,
	updateSortBy
};