import React from 'react';
import {fireEvent, render} from '@testing-library/react';

import SearchFilter from '../SearchFilter';

describe('SearchFilter', () => {
	const productsJsonArray = [
		{
			fileTypes: [
				{
					name: 'One',
					value: 'one'
				}
			],
			name: 'Product One',
			value: 'product_one'
		},
		{
			fileTypes: [
				{
					name: 'One',
					value: 'one'
				},
				{
					name: 'Two',
					value: 'two'
				},
				{
					name: 'Three',
					value: 'three'
				}
			],
			name: 'Product Two',
			value: 'product_two'
		}
	];

	it('renders correctly with no selection', () => {
		const {container} = render(
			<SearchFilter
				actionURL="/"
				currentFileType=""
				currentProduct=""
				productsJSONArray={productsJsonArray}
			/>
		);

		expect(container).toMatchSnapshot();
	});

	it('renders correctly with both filters preselected', () => {
		const {container} = render(
			<SearchFilter
				actionURL="/"
				currentFileType="two"
				currentProduct="product_two"
				productsJSONArray={productsJsonArray}
			/>
		);

		expect(container).toMatchSnapshot();
	});

	it('populates file type select field with option values when a product is selected', () => {
		const {getByLabelText} = render(
			<SearchFilter
				actionURL="/"
				currentFileType=""
				currentProduct=""
				productsJSONArray={productsJsonArray}
			/>
		);

		const productInput = getByLabelText('product:');

		fireEvent.change(
			productInput,
			{
				target: {
					value: 'product_two'
				}
			}
		);

		const fileTypeInput = getByLabelText('file-type:');

		fireEvent.change(
			fileTypeInput,
			{
				target: {
					value: 'three'
				}
			}
		);

		expect(fileTypeInput.value).toBe('three');
	});

	it('auto populates file type when product with only one file type is selected', () => {
		const {getByLabelText} = render(
			<SearchFilter
				actionURL="/"
				currentFileType=""
				currentProduct=""
				productsJSONArray={productsJsonArray}
			/>
		);

		const productInput = getByLabelText('product:');

		fireEvent.change(
			productInput,
			{
				target: {
					value: 'product_one'
				}
			}
		);

		const fileTypeInput = getByLabelText('file-type:');

		expect(fileTypeInput.value).toBe('one');
	});
});