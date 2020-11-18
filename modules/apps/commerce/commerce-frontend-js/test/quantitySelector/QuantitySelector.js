/* eslint-disable @liferay/liferay/imports-first */
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

jest.mock(
	'../../src/main/resources/META-INF/resources/components/quantity_selector/utils/index'
);

import '@testing-library/jest-dom/extend-expect';
import {cleanup, render} from '@testing-library/react';
import React from 'react';

import QuantitySelector from '../../src/main/resources/META-INF/resources/components/quantity_selector/QuantitySelector';
import * as Utils from '../../src/main/resources/META-INF/resources/components/quantity_selector/utils/index';

describe('Quantity Selector', () => {
	describe('When QuantitySelector is loaded', () => {
		beforeEach(() => {
			jest.resetAllMocks();
		});

		afterEach(() => {
			cleanup();
		});

		it('if allowedQuantity is different from default [] the options should display the the correct option', () => {
			const ALLOWED_QUANTITIES = [
				2,
				4,
				65,
				33,
				913,
				267,
				323,
				122,
				90,
				113,
			];

			Utils.generateOptions.mockReturnValue(ALLOWED_QUANTITIES);

			// const settingsAllowedQuantities = {
			// 	allowedQuantity: [1, 2, 3, 4],
			// };

			const {getByText} = render(
				<QuantitySelector

					// settings={settingsAllowedQuantities}

					spritemap="./assets/icons.svg"
				/>
			);

			expect(Utils.generateOptions).toHaveBeenCalled();

			ALLOWED_QUANTITIES.forEach((o) => {
				const option = getByText(o.toString());
				expect(option.value).toEqual(o.toString());
				expect(option).toBeInTheDocument();
			});
		});

		it('if allowedQuantity is [] the component should display the options from min to max', () => {
			Utils.generateOptions.mockReturnValue([1, 2, 3, 4]);

			const {getByTestId, getByText} = render(
				<QuantitySelector

					// settings={settingss}

					spritemap="./assets/icons.svg"
				/>
			);

			const oo = getByText('1');

			expect(oo).toBeInTheDocument();
		});

		// it('check correctness of inputSize attribute', () => {
		//     Utils.generateOptions.mockReturnValue([1, 2, 3, 4]);
		// 	const { container, getByTestId } = render(
		// 		<QuantitySelector
		// 			inputSize={'2000'}
		// 			quantity={1}
		// 			spritemap="./assets/icons.svg"
		// 		/>
		// 	);
		// 	// const containerClass = container.type.styledComponentId
		// 	// const quantityRoot = document.getElementsByClassName(
		// 	// 	containerClass
		// 	// );
		// 	// console.log("quantityRoot", quantityRoot)
		// 	// const style = window.getComputedStyle(quantityRoot[0]);
		// 	// console.log("style", style)
		//     // expect(style.width).toBe(2000);
		// 	// const com = getByTestId('quantity-selector')
		// 	expect(container.firstChild).toHaveAttribute("width", "2000px");
		// });

		it('if SELECT is disabled the input should be disabled', () => {
			Utils.generateOptions.mockReturnValue([]);
			const {container, getByTestId} = render(
				<QuantitySelector
					disabled={true}
					inputSize={'2000'}
					spritemap="./assets/icons.svg"
					style="select"
				/>
			);
			expect(getByTestId('select')).toBeDisabled();

			// expect(getByTestId('quantity-selector')).toHaveStyle('width: 2000px')

		});

		it('if DATALIST is disabled the input should be disabled', () => {
			Utils.generateOptions.mockReturnValue([]);
			const {container, getByTestId} = render(
				<QuantitySelector
					disabled={true}
					spritemap="./assets/icons.svg"
					style="datalist"
				/>
			);
			expect(getByTestId('datalist')).toBeDisabled();
		});
	});
});
