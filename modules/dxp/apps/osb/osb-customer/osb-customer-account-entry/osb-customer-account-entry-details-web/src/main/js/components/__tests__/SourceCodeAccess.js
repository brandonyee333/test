import React from 'react';
import {
	fireEvent,
	queryAllByRole,
	queryByRole,
	queryByText,
	queryByValue,
	render
} from 'react-testing-library';

import SourceCodeAccess from '../SourceCodeAccess';

describe('SourceCodeAccess', () => {
	const collaboratorsJSON = [
		{
			emailAddress: 'test1@liferay.com',
			fullName: 'Test1 Test1',
			gitHubUserName: 'testuser1'
		},
		{
			emailAddress: 'test2@liferay.com',
			fullName: 'Test2 Test2',
			gitHubUserName: 'testuser2'
		},
		{
			emailAddress: 'test3@liferay.com',
			fullName: 'Test3 Test3',
			gitHubUserName: 'testuser3'
		}
	];

	it('renders correctly', () => {
		const {container} = render(
			<SourceCodeAccess
				addCollaboratorURL="/url"
				collaborators={collaboratorsJSON}
			/>
		);

		expect(container).toMatchSnapshot();
	});

	it('renders the header for collaborators list', () => {
		const {container} = render(
			<SourceCodeAccess
				addCollaboratorURL="/url"
				collaborators={collaboratorsJSON}
			/>
		);

		const sourceCodeAccessHeader = queryByText(container, 'team-members-who-have-access-to-liferays-source-code');

		expect(sourceCodeAccessHeader).toBeTruthy();
		expect(container).toMatchSnapshot();
	});

	it('renders no results message when there are no collaborators', () => {
		const {container} = render(
			<SourceCodeAccess
				addCollaboratorURL="/url"
				collaborators={[]}
			/>
		);

		const noResultsMsgs = queryByText(container, 'no-collaborator-details');

		expect(noResultsMsgs).toBeTruthy();
		expect(container).toMatchSnapshot();
	});

	it('renders details when a collaborator is clicked', () => {
		const {container, getByText} = render(
			<SourceCodeAccess
				addCollaboratorURL="/url"
				collaborators={collaboratorsJSON}
			/>
		);

		fireEvent.click(queryByRole(container, 'tab'));

		const email = queryByText(container, 'email');
		const gitHubUserName = queryByText(container, 'github-user-name');
		const name = queryByText(container, 'name');

		expect(email).toBeTruthy();
		expect(gitHubUserName).toBeTruthy();
		expect(name).toBeTruthy();
		expect(container).toMatchSnapshot();
	});

	xit('shows an add collaborator button', () => {
		const {container} = render(
			<SourceCodeAccess
				addCollaboratorURL="/url"
				collaborators={collaboratorsJSON}
			/>
		);

		const addButton = queryByValue(container, 'add');

		expect(addButton).toBeTruthy();
	});

	xit('renders the description for add collaborators button', () => {
		const {container} = render(
			<SourceCodeAccess
				addCollaboratorURL="/url"
				collaborators={collaboratorsJSON}
			/>
		);

		const addCollaboratorDescription = queryByText(container, 'add-your-github-id-and-email-address-to-get-access-to-liferays-source-code');

		expect(addCollaboratorDescription).toBeTruthy();
		expect(container).toMatchSnapshot();
	});

	xit('shows delete button when a collaborator is clicked', () => {
		const {container} = render(
			<AccountEnvironments
				addEnvironmentURL="/url"
				environmentConfiguration={environmentConfigJSON}
				environments={accountEnvironmentsJSON}
			/>
		);

		fireEvent.click(queryByRole(container, 'tab'));

		const deleteButton = queryByValue(container, 'delete');

		expect(deleteButton).toBeTruthy();
	});

	xit('opens a modal when add button is clicked', () => {
		const {container} = render(
			<AccountEnvironments
				addEnvironmentURL="/url"
				environmentConfiguration={environmentConfigJSON}
				environments={accountEnvironmentsJSON}
				permitAdd
			/>
		);

		const addButton = queryByValue(container, 'add');

		fireEvent.click(addButton);

		const modal = queryByRole(container, 'dialog');

		expect(modal).toBeDefined();
	});
});