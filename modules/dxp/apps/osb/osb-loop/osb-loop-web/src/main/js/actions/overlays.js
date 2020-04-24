import {Align} from 'metal-position';

const {
	Bottom,
	BottomLeft,
	BottomRight,
	Left,
	Right,
	Top,
	TopLeft,
	TopRight
} = Align;

export const alignmentPositions = {
	BOTTOM: Bottom,
	BOTTOM_LEFT: BottomLeft,
	BOTTOM_RIGHT: BottomRight,
	LEFT: Left,
	RIGHT: Right,
	TOP: Top,
	TOP_LEFT: TopLeft,
	TOP_RIGHT: TopRight
};

export const overlayTypes = {
	COMMENT_MENU: 'COMMENT_MENU',
	DIVISION_PROFILE_MENU: 'DIVISION_PROFILE_MENU',
	ENTITY_SUMMARY: 'ENTITY_SUMMARY',
	FOLLOW_MENU: 'FOLLOW_MENU',
	FOLLOWING_LIST_MENU: 'FOLLOWING_LIST_MENU',
	JOB_TITLE_PROFILE_MENU: 'JOB_TITLE_PROFILE_MENU',
	OVERLAY_ERROR_MESSAGE: 'OVERLAY_ERROR_MESSAGE',
	PAGE_MENU: 'PAGE_MENU',
	PERSON_PROFILE_MENU: 'PERSON_PROFILE_MENU',
	POST_MENU: 'POST_MENU',
	TOPIC_PROFILE_MENU: 'TOPIC_PROFILE_MENU'
};

export const actionTypes = {
	HIDE_OVERLAY: 'HIDE_OVERLAY',
	SHOW_OVERLAY: 'SHOW_OVERLAY'
};

export function hideOverlay(id) {
	return {
		id,
		type: actionTypes.HIDE_OVERLAY
	};
}

export function showOverlay(config) {
	const {
		alignment,
		alignWithParent,
		containerClass,
		id,
		offset,
		overlayProps,
		overlayType
	} = config;

	return {
		alignment,
		alignWithParent,
		containerClass,
		id,
		offset,
		overlayProps,
		overlayType,
		type: actionTypes.SHOW_OVERLAY
	};
}