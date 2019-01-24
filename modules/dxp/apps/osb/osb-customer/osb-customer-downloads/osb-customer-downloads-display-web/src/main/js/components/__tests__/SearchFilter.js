import React from 'react';
import TestRenderer from 'react-test-renderer';

import SearchFilter from '../SearchFilter';

describe('SearchFilter', () => {
	const productsJsonArray = [
		{
			name: 'Commerce',
			value: 'commerce',
			fileTypes: [{name: 'Product', value: 'product'}]
		},
		{
			name: 'DXP 7.0',
			value: 'dxp_70',
			fileTypes: [
				{name: 'Product', value: 'product'},
				{name: 'Fix Packs', value: 'fixPacks'},
				{name: 'Security', value: 'security'}
			]
		}
	];

	it('renders correctly with no selection', () => {
		const tree = TestRenderer.create(
			<SearchFilter
				actionURL="/"
				currentFileType=""
				currentProduct=""
				productsJSONArray={productsJsonArray}
			/>
		).toJSON();

		expect(tree).toMatchSnapshot();
	});

	it('renders correctly with preselection', () => {
		const tree = TestRenderer.create(
			<SearchFilter
				actionURL="/"
				currentFileType="security"
				currentProduct="dxp_70"
				productsJSONArray={productsJsonArray}
			/>
		).toJSON();

		expect(tree).toMatchSnapshot();
	});
});