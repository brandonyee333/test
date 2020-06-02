import {
	fireEvent,
	getByLabelText,
	queryAllByText,
	render
} from 'react-testing-library';
import React from 'react';

import AddSourceCodeAccessModal from '../AddSourceCodeAccessModal';

describe('AddSourceCodeAccessModal', () => {
	function renderAddSourceCodeAccessModal(props) {
		return render(
			<AddSourceCodeAccessModal
				addCollaboratorToMap={jest.fn()}
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
		const {container, getByText} = renderAddSourceCodeAccessModal();

		getByText("give-a-team-member-access-to-liferay's-source-code");
		expect(container).toMatchSnapshot();
	});

	it('renders fields for name, email, and github', () => {
		const {container, getByText} = renderAddSourceCodeAccessModal();

		getByLabelText(container, 'name');
		getByLabelText(container, 'email-address');
		getByLabelText(container, 'github-username');
		getByText('first-and-last-name');
		expect(container).toMatchSnapshot();
	});

	it('renders error messages upon submission when fields are empty', () => {
		const {getByText, queryAllByText} = renderAddSourceCodeAccessModal();

		fireEvent.click(getByText('submit'));

		getByText(
			'please-correct-the-following-fields name, email-address, github-username'
		);

		expect(queryAllByText('this-field-is-required').length).toBe(3);
	});

	it('renders error message upon submission when last name is missing', () => {
		const {container, getByText} = renderAddSourceCodeAccessModal();

		fireEvent.change(getByLabelText(container, 'name'), {
			target: {value: 'Test'}
		});

		fireEvent.change(getByLabelText(container, 'email-address'), {
			target: {value: 'test@liferay.com'}
		});

		fireEvent.change(getByLabelText(container, 'github-username'), {
			target: {value: 'test'}
		});

		fireEvent.click(getByText('submit'));

		getByText('please-correct-the-following-fields name');
		getByText('first-and-last-name-are-both-required');
	});

	it('renders error message upon submission when email is invalid', () => {
		const {container, getByText} = renderAddSourceCodeAccessModal();

		fireEvent.change(getByLabelText(container, 'name'), {
			target: {value: 'Test Test'}
		});
		fireEvent.change(getByLabelText(container, 'email-address'), {
			target: {value: 'test@liferay'}
		});
		fireEvent.change(getByLabelText(container, 'github-username'), {
			target: {value: 'test'}
		});

		fireEvent.click(getByText('submit'));

		getByText('please-correct-the-following-fields email-address');
		getByText('invalid-format');
	});

	it('renders error message upon submission when github username is incorrect', () => {
		const {container, getByText} = renderAddSourceCodeAccessModal();

		fireEvent.change(getByLabelText(container, 'name'), {
			target: {value: 'Test Test'}
		});
		fireEvent.change(getByLabelText(container, 'email-address'), {
			target: {value: 'test@liferay.com'}
		});
		fireEvent.change(getByLabelText(container, 'github-username'), {
			target: {value: 'test$'}
		});

		fireEvent.click(getByText('submit'));

		getByText('please-correct-the-following-fields github-username');
		getByText('incorrect-username');
	});
});
