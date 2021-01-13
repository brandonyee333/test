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
	Align,
	align,
} from '../../../src/main/resources/META-INF/resources/liferay/position/align';
import {enterDocument, exitDocument} from './helpers';

let center;
let element;
let mutable;
let offsetParent;

window.scrollTo = jest.fn();

describe('Align', () => {
	afterEach(() => {
		exitDocument(center);
		exitDocument(element);
		exitDocument(mutable);
		exitDocument(offsetParent);
		window.scrollTo(0, 0);
	});

	beforeEach(() => {
		enterDocument(
			'<div id="center" style="position:absolute;top:100px;left:100px;width:50px;height:50px;"></div>'
		);
		enterDocument(
			'<div id="element" style="position:absolute;height:25px;width:25px;"></div>'
		);
		enterDocument(
			'<div id="mutable" style="position:absolute;width:50px;height:50px;"></div>'
		);
		enterDocument(
			'<div id="offsetParent" style="position:absolute;width:500px;height:500px;top:100px;left:100px;"></div>'
		);

		center = document.getElementById('center');
		element = document.getElementById('element');

		mutable = document.getElementById('mutable');
		mutable.style.top = '100px';
		mutable.style.left = '100px';
		mutable.style.bottom = '';
		mutable.style.right = '';

		offsetParent = document.getElementById('offsetParent');
	});

	it('defines constants and aliases', () => {
		expect(Align.BottomCenter).toBeDefined();
		expect(Align.BottomLeft).toBeDefined();
		expect(Align.BottomRight).toBeDefined();
		expect(Align.LeftCenter).toBeDefined();
		expect(Align.RightCenter).toBeDefined();
		expect(Align.TopCenter).toBeDefined();
		expect(Align.TopLeft).toBeDefined();
		expect(Align.TopRight).toBeDefined();

		expect(Align.Bottom).toBeDefined();
		expect(Align.Bottom).toBe(Align.BottomCenter);

		expect(Align.Left).toBeDefined();
		expect(Align.Left).toBe(Align.LeftCenter);

		expect(Align.Right).toBeDefined();
		expect(Align.Right).toBe(Align.RightCenter);

		expect(Align.Top).toBeDefined();
		expect(Align.Top).toBe(Align.TopCenter);
	});

	it('align to the bottom', () => {
		const position = align(element, center, Align.Bottom);
		expect(position).toBe(Align.Bottom);
	});

	it('does not try to find a better region to align', () => {
		mutable.style.left = '0px';

		const position = align(element, mutable, Align.Left, false);
		expect(position).toBe(Align.Left);
	});

	it('aligns to the right', () => {
		const position = align(element, center, Align.Right);
		expect(position).toBe(Align.Right);
	});

	it('aligns to the top', () => {
		const position = align(element, center, Align.Top);
		expect(position).toBe(Align.Top);
	});

	it('aligns to the left', () => {
		const position = align(element, center, Align.Left);
		expect(position).toBe(Align.Left);
	});
});
