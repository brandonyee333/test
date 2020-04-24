export const actionTypes = {
	REMOVE_TOOLBAR_DISPLAY: 'REMOVE_TOOLBAR_DISPLAY',
	SET_ACTIVE_TAB_INDEX: 'SET_ACTIVE_TAB_INDEX',
	SET_PAGE_TITLE: 'SET_PAGE_TITLE',
	SET_TOOLBAR_DISPLAY: 'SET_TOOLBAR_DISPLAY'
};

export function setActiveTabIndex(index) {
	return {
		index,
		type: actionTypes.SET_ACTIVE_TAB_INDEX
	};
}

export function setDisplay(display) {
	return {
		display,
		type: actionTypes.SET_TOOLBAR_DISPLAY
	};
}

export function setPageTitle(pageTitle) {
	return {
		pageTitle,
		type: actionTypes.SET_PAGE_TITLE
	};
}

export function removeDisplay() {
	return {
		type: actionTypes.REMOVE_TOOLBAR_DISPLAY
	};
}