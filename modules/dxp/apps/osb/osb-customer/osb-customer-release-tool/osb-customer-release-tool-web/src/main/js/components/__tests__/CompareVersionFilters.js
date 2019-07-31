import React from 'react';
import {cleanup, fireEvent, render} from '@testing-library/react';

import CompareVersionFilters from '../CompareVersionFilters';

const setup = () => {
	const JSONArray = [
		{
			fixPacks: [
				{
					'release-id': '7010-base',
					name: 'GA',
					version: '0.0'
				},
				{
					'release-id': '7010-de-1',
					name: 'Fix Pack 1',
					version: '1.0'
				},
				{
					'release-id': '7010-de-2',
					name: 'Fix Pack 2',
					version: '2.0'
				}
			],
			name: 'DXP 7.0',
			product: 'dxp',
			version: '7.0'
		},
		{
			fixPacks: [
				{
					'release-id': '7110-base',
					name: 'GA',
					version: '0.0'
				},
				{
					'release-id': '7110-dxp-1',
					name: 'Fix Pack 1',
					version: '1.0'
				},
				{
					'release-id': '7110-dxp-2',
					name: 'Fix Pack 2',
					version: '2.0'
				},
				{
					'release-id': '7110-dxp-3',
					name: 'Fix Pack 3',
					version: '3.0'
				}
			],
			name: 'DXP 7.1',
			product: 'dxp',
			version: '7.1'
		}
	];

	const utils = render(
		<CompareVersionFilters actionURL='/' filtersJSON={JSONArray} />
	);

	const compareToVersionDropdown = utils.container.querySelectorAll(
		'select'
	)[1];
	const startingVersionDropdown = utils.container.querySelectorAll(
		'select'
	)[0];

	return {
		compareToVersionDropdown,
		JSONArray,
		startingVersionDropdown,
		...utils
	};
};

afterEach(cleanup);

describe('CompareVersionFilters', () => {
	it('renders filters correctly', () => {
		const {container} = setup();

		expect(container).toMatchSnapshot();
	});

	it('renders all option values for startingVersionDropdown on initial load', () => {
		const {startingVersionDropdown} = setup();

		// It should render all 7 dropdown options plus select placeholder

		expect(startingVersionDropdown.options.length).toBe(8);
	});

	it('renders compareToVersionDropdown as disabled on initial load', () => {
		const {compareToVersionDropdown} = setup();

		expect(compareToVersionDropdown.options.length).toBe(1);
		expect(compareToVersionDropdown.disabled).toBeTruthy();
	});

	it('renders preselected filters correctly', () => {
		const {JSONArray} = setup();

		const {container} = render(
			<CompareVersionFilters
				actionURL='/'
				filtersJSON={JSONArray}
				fromFixPackVersion='1.0'
				fromProductVersion='7.0'
				toFixPackVersion='2.0'
				toProductVersion='7.1'
			/>
		);

		const compareToVersionDropdown = container.querySelectorAll(
			'select'
		)[1];
		const startingVersionDropdown = container.querySelectorAll('select')[0];

		expect(compareToVersionDropdown.selectedOptions[0].innerHTML).toBe(
			'DXP 7.1 Fix Pack 2'
		);
		expect(startingVersionDropdown.selectedOptions[0].innerHTML).toBe(
			'DXP 7.0 Fix Pack 1'
		);

		expect(container).toMatchSnapshot();
	});

	it('shows compareToVersionDropdown options of equal or greater value than the selected startingVersionDropdown option', () => {
		const {
			compareToVersionDropdown,
			container,
			startingVersionDropdown
		} = setup();

		// Selecting DXP 7.0 Fix Pack 2

		fireEvent.change(startingVersionDropdown, {
			target: {value: '7.0-2.0'}
		});

		expect(compareToVersionDropdown.disabled).toBeFalsy();
		expect(compareToVersionDropdown.options.length).toBe(5);
		expect(compareToVersionDropdown.options[0].innerHTML).toBe(
			'select-version'
		);
		expect(compareToVersionDropdown.options[1].innerHTML).toBe(
			'DXP 7.1 GA'
		);

		expect(container).toMatchSnapshot();
	});

	it('clears out compareToVersionDropdown when a startingVersionDropdown is reselected', () => {
		const {compareToVersionDropdown, startingVersionDropdown} = setup();

		fireEvent.change(startingVersionDropdown, {
			target: {value: '7.0-2.0'}
		});

		fireEvent.change(compareToVersionDropdown, {
			target: {value: '7.1-1.0'}
		});

		expect(compareToVersionDropdown.selectedOptions[0].innerHTML).toBe(
			'DXP 7.1 Fix Pack 1'
		);

		fireEvent.change(startingVersionDropdown, {
			target: {value: '7.0-1.0'}
		});

		expect(compareToVersionDropdown.selectedOptions[0].innerHTML).toBe(
			'select-version'
		);
	});
});
