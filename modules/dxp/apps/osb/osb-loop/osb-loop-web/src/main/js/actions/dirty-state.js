export const actionTypes = {
	ADD_DIRTY_STATE: 'ADD_DIRTY_STATE',
	REMOVE_DIRTY_STATE: 'REMOVE_DIRTY_STATE'
};

export function addDirtyState(id) {
	return {
		id,
		type: actionTypes.ADD_DIRTY_STATE
	};
}

export function removeDirtyState(id) {
	return {
		id,
		type: actionTypes.REMOVE_DIRTY_STATE
	};
}