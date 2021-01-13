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

function append(parent, child) {
	if (typeof child === 'string') {
		child = buildFragment(child);
	}
	if (isNodeListLike(child)) {
		const childArr = Array.prototype.slice.call(child);
		for (let i = 0; i < childArr.length; i++) {
			parent.appendChild(childArr[i]);
		}
	}
	else {
		parent.appendChild(child);
	}

	return child;
}

/**
 * Helper for converting a HTML string into a document fragment.
 * @param {string} htmlString The HTML string to convert.
 * @return {!Element} The resulting document fragment.
 */
export function buildFragment(htmlString) {
	const tempDiv = document.createElement('div');
	tempDiv.innerHTML = `<br>${htmlString}`;
	tempDiv.removeChild(tempDiv.firstChild);

	const fragment = document.createDocumentFragment();
	while (tempDiv.firstChild) {
		fragment.appendChild(tempDiv.firstChild);
	}

	return fragment;
}

/**
 * Inserts node in document as last element.
 * @param {Element} node Element to remove children from.
 */
export function enterDocument(node) {
	return node && append(document.body, node);
}

/**
 * Removes node from document.
 * @param {Element} node Element to remove children from.
 */
export function exitDocument(node) {
	if (node && node.parentNode) {
		node.parentNode.removeChild(node);
	}
}

/**
 * Returns true if the specified value is a NodeList or like one.
 * @param {?} val Variable to test.
 * @return {boolean} Whether variable is like a NodeList.
 */
function isNodeListLike(val) {
	return (
		val && typeof val.length === 'number' && typeof val.item === 'function'
	);
}
