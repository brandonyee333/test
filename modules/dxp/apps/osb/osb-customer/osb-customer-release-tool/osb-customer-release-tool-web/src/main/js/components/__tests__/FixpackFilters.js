import React from 'react';
import {cleanup, fireEvent, render} from 'react-testing-library';

import FixpackFilters from '../FixpackFilters';

const setup = () => {
	const JSONArray = [
		{
			name: 'DXP 7.0',
			product: 'dxp',
			version: '7.0',
			fixPacks: [
				{
					name: 'Fixpack 1',
					version: '1'
				},
				{
					name: 'Fixpack 2',
					version: '2'
				}
			]
		},
		{
			name: 'DXP 7.1',
			product: 'dxp',
			version: '7.1',
			fixPacks: [
				{
					name: 'Fixpack 1',
					version: '1'
				}
			]
		},
		{
			name: 'DXP 7.2',
			product: 'dxp',
			version: '7.2',
			fixPacks: [
				{
					name: 'Fixpack 1',
					version: '1'
				}
			]
		}
	];

	const utils = render(
		<FixpackFilters actionURL="/" filtersJSON={JSONArray} />
	);

	const fromFixPackDropdown = utils.container.querySelectorAll('select')[1];
	const fromProductDropdown = utils.container.querySelectorAll('select')[0];
	const toFixPackDropdown = utils.container.querySelectorAll('select')[3];
	const toProductDropdown = utils.container.querySelectorAll('select')[2];

	return {
		fromFixPackDropdown,
		fromProductDropdown,
		JSONArray,
		toFixPackDropdown,
		toProductDropdown,
		...utils
	};
};

afterEach(cleanup);

describe('FixpackFilters', () => {
	it('renders empty filters correctly', () => {
		const {container, getAllByText, queryByRole} = setup();

		expect(getAllByText('select-product').length).toBe(2);
		expect(getAllByText('select-release').length).toBe(2);
		expect(queryByRole('link')).toBeFalsy();
		expect(container).toMatchSnapshot();
	});

	it('renders preselected filters with a fix pack download link correctly', () => {
		const {JSONArray} = setup();

		const {container, getByRole, getByText} = render(
			<FixpackFilters
				actionURL="/"
				filtersJSON={JSONArray}
				fixpackURL="/"
				fromProductVersion="7.0"
				fromFixPackVersion="2"
				toProductVersion="7.1"
				toFixPackVersion="1"
			/>
		);

		expect(getByText('Fixpack 1')).toBeDefined();
		expect(getByText('Fixpack 2')).toBeDefined();
		expect(getByRole('link')).toBeDefined();
		expect(getByRole('link').innerHTML).toEqual(
			expect.stringContaining('get Fixpack 1')
		);
		expect(container).toMatchSnapshot();
	});

	it('enables one dropdown at a time as the previous dropdown gets filled out', () => {
		const {
			container,
			fromFixPackDropdown,
			fromProductDropdown,
			toFixPackDropdown,
			toProductDropdown
		} = setup();

		expect(container.querySelectorAll('[disabled]').length).toBe(3);

		/**
		 * Selecting a value in fromProductDropdown will
		 * enable fromFixPackDropdown and
		 * prepopulate toProductDropdown but keep it disabled
		 */

		fireEvent.change(fromProductDropdown, {
			target: {value: '7.0'}
		});

		expect(container.querySelectorAll('[disabled]').length).toBe(2);
		expect(fromFixPackDropdown.disabled).toBeFalsy();
		expect(toProductDropdown.disabled).toBeTruthy();
		expect(toFixPackDropdown.disabled).toBeTruthy();

		/**
		 * Selecting a value in fromFixPackDropdown will
		 * enable the prepopulated toProductDropdown and
		 * enable the toFixPackDropdown
		 */

		fireEvent.change(fromFixPackDropdown, {target: {value: '1'}});

		expect(container.querySelectorAll('[disabled]').length).toBe(0);
		expect(toProductDropdown.disabled).toBeFalsy();
		expect(toFixPackDropdown.disabled).toBeFalsy();

		/**
		 * Unselecting the value in fromFixPackDropdown will
		 * disable the prepopulated toProductDropdown and
		 * disable the toFixPackDropdown
		 */

		fireEvent.change(fromFixPackDropdown, {target: {value: ''}});

		expect(container.querySelectorAll('[disabled]').length).toBe(2);
		expect(toProductDropdown.disabled).toBeTruthy();
		expect(toFixPackDropdown.disabled).toBeTruthy();
	});

	it('prepopulates the toProductDropdown filter to be the product selected in the fromProductDropdown filter', () => {
		const {fromProductDropdown, toProductDropdown} = setup();

		fireEvent.change(fromProductDropdown, {
			target: {value: '7.0'}
		});

		expect(toProductDropdown.value).toBe('7.0');
	});

	it('shows toFixPackDropdown options of equal or greater value than the selected fromFixPackDropdown option if the same product is chosen for both fields', () => {
		const {
			fromFixPackDropdown,
			fromProductDropdown,
			toFixPackDropdown
		} = setup();

		fireEvent.change(fromProductDropdown, {
			target: {value: '7.0'}
		});

		/**
		 * To prevent user from being able to choose invalid range in UI,
		 * update fromFixPackDropdown selection from value 1 to value 2
		 * expect toFixPackDropdown to not show option whose value is 1 anymore
		 */

		fireEvent.change(fromFixPackDropdown, {target: {value: '1'}});

		expect(toFixPackDropdown.options.length).toBe(3);

		fireEvent.change(fromFixPackDropdown, {target: {value: '2'}});

		expect(toFixPackDropdown.options.length).toBe(2);
		expect(toFixPackDropdown.value).toBe('2');
	});

	it('auto populates the FixPack field if there is only one fix pack for the product selected', () => {
		const {
			fromFixPackDropdown,
			fromProductDropdown,
			toFixPackDropdown
		} = setup();

		fireEvent.change(fromProductDropdown, {
			target: {value: '7.1'}
		});

		expect(fromFixPackDropdown.value).toBe('1');
	});
});