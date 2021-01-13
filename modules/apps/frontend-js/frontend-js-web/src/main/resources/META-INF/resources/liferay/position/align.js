/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import {
	getOffsetLeft,
	getOffsetTop,
	getRegion,
	insideViewport,
	intersectRegion,
	intersection,
} from './position';

/**
 * Align utility. Computes region or best region to align an element with
 * another. Regions are relative to viewport, make sure to use element with
 * position fixed, or position absolute when the element first positioned
 * parent is the body element.
 */

/**
 * Constants that represent the supported positions for `Align`.
 */
const Align = {
	BottomCenter: 4,
	BottomLeft: 5,
	BottomRight: 3,
	LeftCenter: 6,
	RightCenter: 2,
	TopCenter: 0,
	TopLeft: 7,
	TopRight: 1,
};

/**
 * Aliases for position constants.
 */
Align.Bottom = Align.BottomCenter;
Align.Left = Align.LeftCenter;
Align.Right = Align.RightCenter;
Align.Top = Align.TopCenter;

/**
 * Aligns the element with the best region around alignElement. The best
 * region is defined by clockwise rotation starting from the specified
 * `position`. The element is always aligned in the middle of alignElement
 * axis.
 * @param {!Element} element Element to be aligned.
 * @param {!Element} alignElement Element to align with.
 * @param {Align.Top|Align.Right|Align.Bottom|Align.Left} position
 *     The initial position to try. Options `Align.Top`, `Align.Right`,
 *     `Align.Bottom`, `Align.Left`.
 * @param {boolean} autoBestAlign Option to suggest or not the best region
 *      to align.
 * @return {string} The final chosen position for the aligned element.
 */
function align(element, alignElement, position, autoBestAlign = true) {
	let bestRegion;

	if (autoBestAlign) {
		const suggestion = suggestAlignBestRegion(
			element,
			alignElement,
			position
		);
		position = suggestion.position;
		bestRegion = suggestion.region;
	}
	else {
		bestRegion = getAlignRegion(element, alignElement, position);
	}

	const computedStyle = window.getComputedStyle(element);
	if (computedStyle.getPropertyValue('position') !== 'fixed') {
		bestRegion.top += window.pageYOffset;
		bestRegion.left += window.pageXOffset;

		let offsetParent = element;
		while ((offsetParent = offsetParent.offsetParent)) {
			bestRegion.top -= getOffsetTop(offsetParent);
			bestRegion.left -= getOffsetLeft(offsetParent);
		}
	}

	element.style.top = bestRegion.top + 'px';
	element.style.left = bestRegion.left + 'px';

	return position;
}

/**
 * Returns the best region to align element with alignElement. This is similar
 * to `Align.suggestAlignBestRegion`, but it only returns the region information,
 * while `Align.suggestAlignBestRegion` also returns the chosen position.
 * @param {!Element} element Element to be aligned.
 * @param {!Element} alignElement Element to align with.
 * @param {Align.Top|Align.Right|Align.Bottom|Align.Left} position
 *     The initial position to try. Options `Align.Top`, `Align.Right`,
 *     `Align.Bottom`, `Align.Left`.
 * @return {DOMRect} Best region to align element.
 */
function getAlignBestRegion(element, alignElement, position) {
	return suggestAlignBestRegion(element, alignElement, position).region;
}

/**
 * Returns the region to align element with alignElement. The element is
 * always aligned in the middle of alignElement axis.
 * @param {!Element} element Element to be aligned.
 * @param {!Element} alignElement Element to align with.
 * @param {Align.Top|Align.Right|Align.Bottom|Align.Left} position
 *     The position to align. Options `Align.Top`, `Align.Right`,
 *     `Align.Bottom`, `Align.Left`.
 * @return {DOMRect} Region to align element.
 */
function getAlignRegion(element, alignElement, position) {
	const targetRectangle = getRegion(alignElement);
	const sourceRectangle = getRegion(element);
	let top = 0;
	let left = 0;

	switch (position) {
		case Align.TopCenter:
			top = targetRectangle.top - sourceRectangle.height;
			left =
				targetRectangle.left +
				targetRectangle.width / 2 -
				sourceRectangle.width / 2;
			break;
		case Align.RightCenter:
			top =
				targetRectangle.top +
				targetRectangle.height / 2 -
				sourceRectangle.height / 2;
			left = targetRectangle.left + targetRectangle.width;
			break;
		case Align.BottomCenter:
			top = targetRectangle.bottom;
			left =
				targetRectangle.left +
				targetRectangle.width / 2 -
				sourceRectangle.width / 2;
			break;
		case Align.LeftCenter:
			top =
				targetRectangle.top +
				targetRectangle.height / 2 -
				sourceRectangle.height / 2;
			left = targetRectangle.left - sourceRectangle.width;
			break;
		case Align.TopRight:
			top = targetRectangle.top - sourceRectangle.height;
			left = targetRectangle.right - sourceRectangle.width;
			break;
		case Align.BottomRight:
			top = targetRectangle.bottom;
			left = targetRectangle.right - sourceRectangle.width;
			break;
		case Align.BottomLeft:
			top = targetRectangle.bottom;
			left = targetRectangle.left;
			break;
		case Align.TopLeft:
			top = targetRectangle.top - sourceRectangle.height;
			left = targetRectangle.left;
			break;
		default:
			break;
	}

	return {
		bottom: top + sourceRectangle.height,
		height: sourceRectangle.height,
		left,
		right: left + sourceRectangle.width,
		top,
		width: sourceRectangle.width,
	};
}

/**
 * Looks for the best region for aligning the given element. The best
 * region is defined by clockwise rotation starting from the specified
 * `position`. The element is always aligned in the middle of alignElement
 * axis.
 * @param {!Element} element Element to be aligned.
 * @param {!Element} alignElement Element to align with.
 * @param {Align.Top|Align.Right|Align.Bottom|Align.Left} position
 *     The initial position to try. Options `Align.Top`, `Align.Right`,
 *     `Align.Bottom`, `Align.Left`.
 * @return {{position: string, region: DOMRect}} Best region to align element.
 */
function suggestAlignBestRegion(element, alignElement, position) {
	let bestArea = 0;
	let bestPosition = position;
	let bestRegion = getAlignRegion(element, alignElement, bestPosition);
	let tryPosition = bestPosition;
	let tryRegion = bestRegion;
	const viewportRegion = getRegion(window);

	for (let i = 0; i < 8; ) {
		if (intersectRegion(viewportRegion, tryRegion)) {
			const visibleRegion = intersection(viewportRegion, tryRegion);
			const area = visibleRegion.width * visibleRegion.height;
			if (area > bestArea) {
				bestArea = area;
				bestRegion = tryRegion;
				bestPosition = tryPosition;
			}
			if (insideViewport(tryRegion)) {
				break;
			}
		}
		tryPosition = (position + ++i) % 8;
		tryRegion = getAlignRegion(element, alignElement, tryPosition);
	}

	return {
		position: bestPosition,
		region: bestRegion,
	};
}

export {
	Align,
	align,
	getAlignBestRegion,
	getAlignRegion,
	suggestAlignBestRegion,
};
