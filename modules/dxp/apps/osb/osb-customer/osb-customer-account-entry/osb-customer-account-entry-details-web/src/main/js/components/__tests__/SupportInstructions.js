import React from 'react';
import {
	fireEvent,
	queryByLabelText,
	queryByRole,
	queryByText,
	queryByValue,
	render
} from 'react-testing-library';

import SupportInstructions from '../SupportInstructions';

describe('SupportInstructions', () => {
	it('renders correctly', () => {
		const {container} = render(
			<SupportInstructions
				accountEntryId="accountEntryId"
				editInstructionsURL="/"
				instructions="instructions"
			/>
		);

		expect(container).toMatchSnapshot();
	});

	it('renders no results message when there is no existing instruction', () => {
		const {container} = render(
			<SupportInstructions
				accountEntryId="accountEntryId"
				editInstructionsURL="/"
				instructions=""
			/>
		);

		const noResultsMsgs = queryByText(container, 'no-support-instructions');

		expect(noResultsMsgs).toBeTruthy();
		expect(container).toMatchSnapshot();
	});

	it('renders add button when there is no existing instruction', () => {
		const {container} = render(
			<SupportInstructions
				accountEntryId="accountEntryId"
				editInstructionsURL="/"
				instructions=""
			/>
		);

		const addButton = queryByValue(container, 'add');

		expect(addButton).toBeTruthy();
	});

	it('renders edit button when there is an existing instruction', () => {
		const {container} = render(
			<SupportInstructions
				accountEntryId="accountEntryId"
				editInstructionsURL="/"
				instructions="instructions"
			/>
		);

		const editButton = queryByValue(container, 'edit');

		expect(editButton).toBeTruthy();
	});

	it('renders modal when edit button is clicked', () => {
		const {container} = render(
			<SupportInstructions
				accountEntryId="accountEntryId"
				editInstructionsURL="/"
				instructions="instructions"
			/>
		);

		const editButton = queryByValue(container, 'edit');

		fireEvent.click(editButton);

		const modal = queryByRole(container, 'dialog');

		expect(modal).toBeDefined();
	});

	it('renders cancel button in modal when editor is activated', () => {
		const {container} = render(
			<SupportInstructions
				accountEntryId="accountEntryId"
				editInstructionsURL="/"
				instructions="instructions"
			/>
		);

		const editButton = queryByValue(container, 'edit');

		fireEvent.click(editButton);

		const cancelButton = queryByValue(container, 'cancel');

		expect(cancelButton).toBeDefined();
	});

	it('renders save button in modal when editor is activated', () => {
		const {container} = render(
			<SupportInstructions
				accountEntryId="accountEntryId"
				editInstructionsURL="/"
				instructions="instructions"
			/>
		);

		const editButton = queryByValue(container, 'edit');

		fireEvent.click(editButton);

		const saveButton = queryByValue(container, 'save');

		expect(saveButton).toBeDefined();
	});

	it('closes modal when cancel button is clicked', () => {
		const {container} = render(
			<SupportInstructions
				accountEntryId="accountEntryId"
				editInstructionsURL="/"
				instructions="instructions"
			/>
		);

		const editButton = queryByValue(container, 'edit');

		fireEvent.click(editButton);

		const cancelButton = queryByValue(container, 'cancel');
		let modal = queryByRole(container, 'dialog');

		expect(modal).toBeDefined();

		fireEvent.click(cancelButton);

		modal = queryByRole(container, 'dialog');

		expect(modal).toBeNull();
	});

	it('closes modal when x on modal header is clicked', () => {
		const {container} = render(
			<SupportInstructions
				accountEntryId="accountEntryId"
				editInstructionsURL="/"
				instructions="instructions"
			/>
		);

		const editButton = queryByValue(container, 'edit');

		fireEvent.click(editButton);

		const closeButton = queryByLabelText(container, 'Close');
		let modal = queryByRole(container, 'dialog');

		expect(modal).toBeDefined();

		fireEvent.click(closeButton);

		modal = queryByRole(container, 'dialog');

		expect(modal).toBeNull();
	});
});