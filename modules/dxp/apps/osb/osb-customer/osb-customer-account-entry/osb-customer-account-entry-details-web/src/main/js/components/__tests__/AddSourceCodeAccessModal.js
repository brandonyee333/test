import React from 'react';
import {
	fireEvent,
	getByLabelText,
	getByText,
	queryAllByText,
	render,
	waitForElement
} from 'react-testing-library';

import AddSourceCodeAccessModal from '../AddSourceCodeAccessModal';

describe('AddSourceCodeAccessModal', () => {
	function renderAddSourceCodeAccessModal(props) {
		return render(
			<AddSourceCodeAccessModal
				addCollaboratorURL="/url"
				onClose={jest.fn()}
				show={true}
				{...props}
			/>
		);
	}

	it('renders correctly', () => {
		const {container} = renderAddSourceCodeAccessModal();

		expect(container).toMatchSnapshot();
	});

	it('renders the header of the modal', () => {
		const {container} = renderAddSourceCodeAccessModal();

		getByText(
			container,
			"give-a-team-member-access-to-liferay's-source-code"
		);
		expect(container).toMatchSnapshot();
	});

	it('renders fields for name, email, and github', () => {
		const {container} = renderAddSourceCodeAccessModal();

		getByLabelText(container, 'name');
		getByLabelText(container, 'email-address');
		getByLabelText(container, 'github-username');
		getByText(container, 'first-and-last-name');
		expect(container).toMatchSnapshot();
	});

	it('renders error messages upon submission when fields are empty', () => {
		const {container} = renderAddSourceCodeAccessModal();

		fireEvent.click(getByText(container, 'submit'));

		getByText(
			container,
			'please-correct-the-following-fields name, email-address, github-username'
		);

		expect(queryAllByText(container, 'this-field-is-required').length).toBe(
			3
		);
	});

	it('renders error message upon submission when last name is missing', async () => {
		const {container} = renderAddSourceCodeAccessModal();

		fireEvent.change(getByLabelText(container, 'name'), {
			target: {value: 'Test'}
		});
		fireEvent.change(getByLabelText(container, 'email-address'), {
			target: {value: 'test@liferay.com'}
		});
		fireEvent.change(getByLabelText(container, 'github-username'), {
			target: {value: 'test'}
		});

		fireEvent.click(getByText(container, 'submit'));

		waitForElement(() =>
			getByText(container, 'please-correct-the-following-fields name')
		);

		waitForElement(() =>
			getByText(container, 'first-and-last-name-are-both-required')
		);
	});

	it('renders error message upon submission when email is invalid', async () => {
		const {container} = renderAddSourceCodeAccessModal();

		fireEvent.change(getByLabelText(container, 'name'), {
			target: {value: 'Test Test'}
		});
		fireEvent.change(getByLabelText(container, 'email-address'), {
			target: {value: 'test@liferay'}
		});
		fireEvent.change(getByLabelText(container, 'github-username'), {
			target: {value: 'test'}
		});

		fireEvent.click(getByText(container, 'submit'));

		waitForElement(() =>
			getByText(
				container,
				'please-correct-the-following-fields email-address'
			)
		);

		waitForElement(() => getByText(container, 'invalid-format'));
	});

	it('renders error message upon submission when github username is incorrect', async () => {
		const {container} = renderAddSourceCodeAccessModal();

		fireEvent.change(getByLabelText(container, 'name'), {
			target: {value: 'Test Test'}
		});
		fireEvent.change(getByLabelText(container, 'email-address'), {
			target: {value: 'test@liferay.com'}
		});
		fireEvent.change(getByLabelText(container, 'github-username'), {
			target: {value: 'test$'}
		});

		fireEvent.click(getByText(container, 'submit'));

		waitForElement(() =>
			getByText(
				container,
				'please-correct-the-following-fields github-username'
			)
		);

		waitForElement(() => getByText(container, 'incorrect-username'));
	});
});
