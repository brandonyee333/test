import React from 'react';
import {
	findByText,
	fireEvent,
	getByLabelText,
	getByText,
	queryByRole,
	queryByText,
	render,
	waitFor,
	within
} from '@testing-library/react';

import SourceCodeAccess from '../SourceCodeAccess';

describe('SourceCodeAccess', () => {
	const collaboratorsJSON = [
		{
			collaboratorId: 101,
			createDate: '01/01/2020',
			deleteCollaboratorURL:
				'http://www.mocky.io/v2/5eb5a4f83100005e0069977b',
			emailAddress: 'test1@liferay.com',
			fullName: 'Test One',
			gitHubUserName: 'testuser1'
		},
		{
			collaboratorId: 102,
			createDate: '02/02/2020',
			deleteCollaboratorURL:
				'http://www.mocky.io/v2/5eb5a4f83100005e0069977b',
			emailAddress: 'test2@liferay.com',
			fullName: 'Test Two',
			gitHubUserName: 'testuser2'
		},
		{
			collaboratorId: 103,
			createDate: '03/03/2020',
			deleteCollaboratorURL:
				'http://www.mocky.io/v2/5eb5a4f83100005e0069977b',
			emailAddress: 'test3@liferay.com',
			fullName: 'Test Three',
			gitHubUserName: 'testuser3'
		}
	];

	function renderSourceCodeAccess(props) {
		return render(
			<SourceCodeAccess
				addCollaboratorURL="/url"
				collaborators={collaboratorsJSON}
				{...props}
			/>
		);
	}

	it('renders correctly', () => {
		const {container} = renderSourceCodeAccess();

		expect(container).toMatchSnapshot();
	});

	it('renders the header for collaborators list', () => {
		const {container} = renderSourceCodeAccess();

		getByText(
			container,
			"team-members-who-have-access-to-liferay's-source-code"
		);
		expect(container).toMatchSnapshot();
	});

	it('renders no results message when there are no collaborators', () => {
		const {container} = renderSourceCodeAccess({
			collaborators: []
		});

		getByText(container, 'no-collaborator-details');
		expect(container).toMatchSnapshot();
	});

	it('renders details when a collaborator is clicked', () => {
		const {container} = renderSourceCodeAccess();

		fireEvent.click(queryByText(container, 'Test One'));

		const {getByText} = within(queryByRole(container, 'tabpanel'));

		getByText('test1@liferay.com');
		getByText('testuser1');
		getByText('Test One');

		expect(container).toMatchSnapshot();
	});

	it('shows an add collaborator button', () => {
		const {container} = renderSourceCodeAccess();

		getByLabelText(container, 'add');
	});

	it('renders the description for add collaborators button', () => {
		const {container} = renderSourceCodeAccess();

		getByText(
			container,
			"add-your-email-address-and-github-username-to-get-access-to-liferay's-source-code"
		);
		expect(container).toMatchSnapshot();
	});

	it('shows delete button when a collaborator is clicked', () => {
		const {container} = renderSourceCodeAccess();

		fireEvent.click(queryByText(container, 'Test One'));

		const {getByText} = within(queryByRole(container, 'tabpanel'));

		getByText('delete');
	});

	it('opens a modal when add button is clicked', () => {
		const {container} = renderSourceCodeAccess();

		fireEvent.click(getByLabelText(container, 'add'));

		const modal = queryByRole(container, 'dialog');

		expect(modal).toBeDefined();
	});

	it('closes the modal when cancel button is clicked', () => {
		const {container} = renderSourceCodeAccess();

		fireEvent.click(getByLabelText(container, 'add'));

		const {getByText} = within(queryByRole(container, 'dialog'));

		fireEvent.click(getByText('cancel'));

		const modal = queryByRole(container, 'dialog');

		expect(modal).toBeNull();
	});

	it('adds the collaborator after using the modal', async () => {
		const {container} = renderSourceCodeAccess({
			addCollaboratorURL:
				'http://www.mocky.io/v2/5eb497460e0000f359081cd9'
		});

		fireEvent.click(getByLabelText(container, 'add'));

		const modal = queryByRole(container, 'dialog');

		fireEvent.change(within(modal).getByLabelText('name'), {
			target: {value: 'Test Four'}
		});
		fireEvent.change(within(modal).getByLabelText('email-address'), {
			target: {value: 'test4@liferay.com'}
		});
		fireEvent.change(within(modal).getByLabelText('github-username'), {
			target: {value: 'testuser4'}
		});

		fireEvent.click(within(modal).getByText('submit'));

		await findByText(
			container,
			'success-your-request-submitted-successfully'
		);

		await findByText(container, 'Test Four');
		await findByText(container, 'test4@liferay.com');
		await findByText(container, 'testuser4');
	});

	it('deletes the collaborator after delete button is clicked', async () => {
		window.confirm = jest.fn(() => true);

		const {container} = renderSourceCodeAccess();

		fireEvent.click(queryByText(container, 'Test One'));

		const {getByText} = within(queryByRole(container, 'tabpanel'));

		fireEvent.click(getByText('delete'));

		await waitFor(() => expect(window.confirm).toBeCalled());

		await waitFor(() =>
			expect(queryByText(container, 'Test One')).toBeNull()
		);
		await waitFor(() =>
			expect(queryByText(container, 'test1@liferay.com')).toBeNull()
		);
		await waitFor(() =>
			expect(queryByText(container, 'testuser1')).toBeNull()
		);
	});
});
