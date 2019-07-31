import React from 'react';
import {cleanup, fireEvent, render} from '@testing-library/react';

import Pagination from '../Pagination';

const setup = () => {
	const handleClick = jest.fn();

	const utils = render(
		<Pagination onClick={handleClick} perPage={10} total={100} />
	);

	const firstPage = utils.queryByText('«');
	const lastPage = utils.queryByText('»');
	const nextPage = utils.queryByText('›');
	const prevPage = utils.queryByText('‹');

	return {
		firstPage,
		handleClick,
		lastPage,
		nextPage,
		prevPage,
		...utils
	};
};

afterEach(cleanup);

describe('Pagination', () => {
	it('renders correctly', () => {
		const {container} = setup();

		expect(container).toMatchSnapshot();
	});

	it('renders page one as the first page on initial load', () => {
		const {getByText} = setup();

		const page1 = getByText('1');

		expect(page1).toBeTruthy();
	});

	it('renders only next and last page controls on initial load', () => {
		const {firstPage, lastPage, nextPage, prevPage} = setup();

		expect(firstPage).toBeNull();
		expect(lastPage).toBeTruthy();
		expect(nextPage).toBeTruthy();
		expect(prevPage).toBeNull();
	});

	it('renders last page correctly', () => {
		const {lastPage, getByText} = setup();

		lastPage.click();

		expect(getByText('100')).toBeTruthy();
	});

	it('renders only first and prev page controls on last page', () => {
		const {lastPage, queryByText} = setup();

		lastPage.click();

		const newFirstPage = queryByText('«');
		const newLastPage = queryByText('»');
		const newNextPage = queryByText('›');
		const newPrevPage = queryByText('‹');

		expect(newFirstPage).toBeTruthy();
		expect(newLastPage).toBeNull();
		expect(newNextPage).toBeNull();
		expect(newPrevPage).toBeTruthy();
	});

	it('renders all page controls when paginate on a page that is neither the first nor last page', () => {
		const {queryByText} = setup();

		fireEvent.click(queryByText('2'));

		const newFirstPage = queryByText('«');
		const newLastPage = queryByText('»');
		const newNextPage = queryByText('›');
		const newPrevPage = queryByText('‹');

		expect(newFirstPage).toBeTruthy();
		expect(newLastPage).toBeTruthy();
		expect(newNextPage).toBeTruthy();
		expect(newPrevPage).toBeTruthy();
	});

	it('renders a maximum of five pages previous to and after the current page', () => {
		const {container, getByText} = setup();

		fireEvent.click(getByText('6'));

		expect(getByText('1')).toBeTruthy();
		expect(getByText('5')).toBeTruthy();
		expect(getByText('7')).toBeTruthy();
		expect(getByText('11')).toBeTruthy();
		expect(container).toMatchSnapshot();
	});

	it('should not render page numbers exceeding the total page count on initial load', () => {
		const {container, handleClick, queryByText, rerender} = setup();

		rerender(<Pagination onClick={handleClick} perPage={10} total={5} />);

		expect(queryByText('5')).toBeTruthy();
		expect(queryByText('6')).toBeFalsy();
		expect(container).toMatchSnapshot();
	});

	it('should not render page numbers exceeding the total page count after interaction', () => {
		const {getByText, lastPage, queryByText} = setup();

		fireEvent.click(lastPage);
		fireEvent.click(getByText('98'));

		expect(getByText('100')).toBeTruthy();
		expect(queryByText('101')).toBeFalsy();
	});
});
