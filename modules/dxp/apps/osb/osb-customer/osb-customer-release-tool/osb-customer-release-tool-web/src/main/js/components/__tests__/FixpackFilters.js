import React from 'react';
import {cleanup, fireEvent, render} from 'react-testing-library';

import FixpackFilters from '../FixpackFilters';

const setup = () => {
	const JSONArray = [
		{
			fixPacks: [
				{
					name: 'Fixpack 1',
					version: '1.0'
				},
				{
					name: 'Fixpack 2',
					version: '2.0'
				},
				{
					name: 'Fixpack 3',
					version: '3.0'
				}
			],
			name: 'DXP 7.0',
			product: 'dxp',
			version: '7.0'
		},
		{
			fixPacks: [
				{
					name: 'Fixpack 1',
					version: '1.0'
				}
			],
			name: 'DXP 7.1',
			product: 'dxp',
			version: '7.1'
		},
		{
			fixPacks: [
				{
					name: 'Fixpack 1',
					version: '1.0'
				}
			],
			name: 'DXP 7.2',
			product: 'dxp',
			version: '7.2'
		}
	];

	const utils = render(
		<FixpackFilters actionURL="/" filtersJSON={JSONArray} tabName="tab" />
	);

	const fromFixPackDropdown = utils.container.querySelectorAll('select')[1];
	const productDropdown = utils.container.querySelectorAll('select')[0];
	const toFixPackDropdown = utils.container.querySelectorAll('select')[2];

	return {
		fromFixPackDropdown,
		JSONArray,
		productDropdown,
		toFixPackDropdown,
		...utils
	};
};

afterEach(cleanup);

describe('FixpackFilters', () => {
	it('renders empty filters correctly', () => {
		const {container, getAllByText, queryByRole} = setup();

		expect(getAllByText('select-product').length).toBe(1);
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
				fromFixPackVersion="1.0"
				productVersion="7.0"
				tabName="tab"
				toFixPackVersion="2.0"
			/>
		);

		expect(getByText('Fixpack 1')).toBeDefined();
		expect(getByText('Fixpack 2')).toBeDefined();
		expect(getByRole('link')).toBeDefined();
		expect(getByRole('link').innerHTML).toEqual(
			expect.stringContaining('get Fixpack 2')
		);
		expect(container).toMatchSnapshot();
	});

	it('enables one dropdown at a time as the previous dropdown gets filled out', () => {
		const {
			container,
			fromFixPackDropdown,
			productDropdown,
			toFixPackDropdown
		} = setup();

		expect(container.querySelectorAll('[disabled]').length).toBe(2);

		/**
		 * Selecting a value in productDropdown will
		 * enable fromFixPackDropdown field
		 * but keeping toProductDropdown field disabled
		 */

		fireEvent.change(productDropdown, {
			target: {value: '7.0'}
		});

		expect(container.querySelectorAll('[disabled]').length).toBe(1);
		expect(fromFixPackDropdown.disabled).toBeFalsy();
		expect(toFixPackDropdown.disabled).toBeTruthy();

		/**
		 * Selecting a value in fromFixPackDropdown will
		 * enable toFixPackDropdown field
		 */

		fireEvent.change(fromFixPackDropdown, {target: {value: '1.0'}});

		expect(container.querySelectorAll('[disabled]').length).toBe(0);
		expect(toFixPackDropdown.disabled).toBeFalsy();

		/**
		 * Unselecting the value in fromFixPackDropdown will
		 * disable the toFixPackDropdown field
		 */

		fireEvent.change(fromFixPackDropdown, {target: {value: ''}});

		expect(container.querySelectorAll('[disabled]').length).toBe(1);
		expect(toFixPackDropdown.disabled).toBeTruthy();
	});

	it('shows toFixPackDropdown options of equal or greater value than the selected fromFixPackDropdown option', () => {
		const {fromFixPackDropdown, productDropdown, toFixPackDropdown} = setup();

		fireEvent.change(productDropdown, {
			target: {value: '7.0'}
		});

		/**
		 * To prevent user from being able to choose invalid range in UI,
		 * update fromFixPackDropdown selection from value 1 to value 3
		 * expect toFixPackDropdown to not show option 1 and 2 anymore
		 * expect toFixPackDropdown to only contain option 3
		 */

		fireEvent.change(fromFixPackDropdown, {target: {value: '1.0'}});

		expect(toFixPackDropdown.options.length).toBe(4);

		fireEvent.change(fromFixPackDropdown, {target: {value: '3.0'}});

		expect(toFixPackDropdown.options.length).toBe(2);
		expect(toFixPackDropdown.value).toBe('');
	});

	it('auto populates the FixPack field if there is only one fix pack for the product selected', () => {
		const {fromFixPackDropdown, productDropdown} = setup();

		fireEvent.change(productDropdown, {
			target: {value: '7.1'}
		});

		expect(fromFixPackDropdown.value).toBe('1.0');
	});

	it('clears out fromFixPackDropdown when a product is updated', () => {
		const {JSONArray} = setup();

		const {container} = render(
			<FixpackFilters
				actionURL="/"
				filtersJSON={JSONArray}
				fixpackURL="/"
				fromFixPackVersion="2.0"
				productVersion="7.0"
				tabName="tab"
				toFixPackVersion="3.0"
			/>
		);

		const fromFixPackDropdown = container.querySelectorAll('select')[1];
		const productDropdown = container.querySelectorAll('select')[0];
		const toFixPackDropdown = container.querySelectorAll('select')[2];

		fireEvent.change(productDropdown, {target: {value: '7.1'}});

		// Fix Pack value auto populates since sample data for DXP 7.1 contains one fix pack

		expect(fromFixPackDropdown.value).toBe('1.0');
		expect(toFixPackDropdown.disabled).toBeTruthy();
	});

	it('clears out toFixPackDropdown when a fromFixPackDropdown is updated', () => {
		const {JSONArray} = setup();

		const {container} = render(
			<FixpackFilters
				actionURL="/"
				filtersJSON={JSONArray}
				fixpackURL="/"
				fromFixPackVersion="2.0"
				productVersion="7.0"
				tabName="tab"
				toFixPackVersion="3.0"
			/>
		);

		const fromFixPackDropdown = container.querySelectorAll('select')[1];
		const toFixPackDropdown = container.querySelectorAll('select')[2];

		fireEvent.change(fromFixPackDropdown, {target: {value: '1.0'}});

		expect(toFixPackDropdown.value).toBe('');
	});

	it('removes the fix pack download link when a fromFixPackDropdown is updated', () => {
		const {JSONArray} = setup();

		const {container, queryByRole} = render(
			<FixpackFilters
				actionURL="/"
				filtersJSON={JSONArray}
				fixpackURL="/"
				fromFixPackVersion="2.0"
				productVersion="7.0"
				tabName="tab"
				toFixPackVersion="3.0"
			/>
		);

		const fromFixPackDropdown = container.querySelectorAll('select')[1];

		expect(queryByRole('link')).toBeTruthy();

		fireEvent.change(fromFixPackDropdown, {target: {value: '1.0'}});

		expect(queryByRole('link')).toBeFalsy();
	});
});