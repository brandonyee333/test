import React from "react";
import {
	fireEvent,
	getByAltText,
	getByText,
	queryByRole,
	queryByText,
	render,
	within,
} from "react-testing-library";

import SourceCodeAccess from "../SourceCodeAccess";

describe("SourceCodeAccess", () => {
	const collaboratorsJSON = [
		{
			collaboratorId: 101,
			deleteCollaboratorURL: "/url",
			emailAddress: "test1@liferay.com",
			fullName: "Test1 Test1",
			gitHubUserName: "testuser1",
		},
		{
			collaboratorId: 102,
			deleteCollaboratorURL: "/url",
			emailAddress: "test2@liferay.com",
			fullName: "Test2 Test2",
			gitHubUserName: "testuser2",
		},
		{
			collaboratorId: 103,
			deleteCollaboratorURL: "/url",
			emailAddress: "test3@liferay.com",
			fullName: "Test3 Test3",
			gitHubUserName: "testuser3",
		},
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

	it("renders correctly", () => {
		const { container } = renderSourceCodeAccess();

		expect(container).toMatchSnapshot();
	});

	it("renders the header for collaborators list", () => {
		const { container } = renderSourceCodeAccess();

		getByText(
			container,
			"team-members-who-have-access-to-liferays-source-code"
		);
		expect(container).toMatchSnapshot();
	});

	it("renders no results message when there are no collaborators", () => {
		const { container } = renderSourceCodeAccess({
			collaborators: [],
		});

		getByText(container, "no-collaborator-details");
		expect(container).toMatchSnapshot();
	});

	it("renders details when a collaborator is clicked", () => {
		const { container } = renderSourceCodeAccess();

		fireEvent.click(queryByText(container, "Test1 Test1"));

		const { getByText } = within(queryByRole(container, "tabpanel"));

		getByText("test1@liferay.com");
		getByText("testuser1");
		getByText("Test1 Test1");

		expect(container).toMatchSnapshot();
	});

	xit("shows an add collaborator button", () => {
		const { container } = renderSourceCodeAccess();

		getByAltText(container, "add");
	});

	xit("renders the description for add collaborators button", () => {
		const { container } = renderSourceCodeAccess();

		getByText(
			container,
			"add-your-github-id-and-email-address-to-get-access-to-liferays-source-code"
		);
		expect(container).toMatchSnapshot();
	});

	xit("shows delete button when a collaborator is clicked", () => {
		const { container } = renderSourceCodeAccess();

		fireEvent.click(queryByText(container, "Test1 Test1"));

		const { getByText } = within(queryByRole(container, "tabpanel"));

		getByText("delete");
	});

	xit("opens a modal when add button is clicked", () => {
		const { container } = renderSourceCodeAccess();

		fireEvent.click(getByAltText(container, "add"));

		const modal = queryByRole(container, "dialog");

		expect(modal).toBeDefined();
	});
});
