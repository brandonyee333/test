import {
	fireEvent,
	getByLabelText,
	getByText,
	queryByRole,
	queryByText,
	render,
	within
} from 'react-testing-library';
import axios from 'axios';
import React from 'react';

import SourceCodeAccess from '../SourceCodeAccess';

const ADD_COLLABORATOR_URL = '/add';
const DELETE_COLLABORATOR_URL = '/delete';

const JSON_RESPONSES = {
	[`${ADD_COLLABORATOR_URL}`]: {
		message: 'success',
		collaboratorId: 104,
		createDate: '05/05/2020',
		deleteCollaboratorURL: '/url'
	},
	[`${DELETE_COLLABORATOR_URL}`]: {
		message: 'success'
	}
};

jest.mock('axios');

axios.post.mockImplementation(endpoint =>
	Promise.resolve({
		data: JSON_RESPONSES[endpoint]
	})
);

describe('SourceCodeAccess', () => {
	const collaboratorsJSON = [
		{
			createDate: '01/01/2020',
			deleteURL: DELETE_COLLABORATOR_URL,
			emailAddress: 'test1@liferay.com',
			fullName: 'Test One',
			gitHubUserName: 'testuser1',
			id: 101
		},
		{
			createDate: '02/02/2020',
			deleteURL: DELETE_COLLABORATOR_URL,
			emailAddress: 'test2@liferay.com',
			fullName: 'Test Two',
			gitHubUserName: 'testuser2',
			id: 102
		},
		{
			createDate: '03/03/2020',
			deleteURL: DELETE_COLLABORATOR_URL,
			emailAddress: 'test3@liferay.com',
			fullName: 'Test Three',
			gitHubUserName: 'testuser3',
			id: 103
		}
	];

	function renderSourceCodeAccess(props) {
		return render(
			<SourceCodeAccess
				addCollaboratorURL={ADD_COLLABORATOR_URL}
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
});
