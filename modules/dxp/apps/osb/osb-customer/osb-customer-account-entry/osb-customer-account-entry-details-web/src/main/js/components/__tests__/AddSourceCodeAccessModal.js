import {fireEvent, getByLabelText, render} from 'react-testing-library';
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

		getByText("give-a-team-member-access-to-liferay-dxp's-source-code");
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

	it('renders error message upon submission when Name field contains only white space characters', () => {
		const {container, getByText} = renderAddSourceCodeAccessModal();

		fireEvent.change(getByLabelText(container, 'name'), {
			target: {value: '	 '}
		});

		fireEvent.change(getByLabelText(container, 'email-address'), {
			target: {value: 'test@liferay.com'}
		});

		fireEvent.change(getByLabelText(container, 'github-username'), {
			target: {value: 'test'}
		});

		fireEvent.click(getByText('submit'));

		getByText('please-correct-the-following-fields name');
		getByText('name-is-required');
	});

	it('allows the user to enter a name with an apostrophe', () => {
		const {
			container,
			getByText,
			queryByText
		} = renderAddSourceCodeAccessModal();

		fireEvent.change(getByLabelText(container, 'name'), {
			target: {value: "Conan O'Brien"}
		});

		fireEvent.change(getByLabelText(container, 'email-address'), {
			target: {value: 'test@liferay.com'}
		});

		fireEvent.change(getByLabelText(container, 'github-username'), {
			target: {value: 'test'}
		});

		fireEvent.click(getByText('submit'));

		expect(
			queryByText('please-correct-the-following-fields name')
		).toBeFalsy();
		expect(
			queryByText('name-is-required')
		).toBeFalsy();
	});

	it('allows the user to enter a double-barrelled surname', () => {
		const {
			container,
			getByText,
			queryByText
		} = renderAddSourceCodeAccessModal();

		fireEvent.change(getByLabelText(container, 'name'), {
			target: {value: 'Andrew Lloyd Webber'}
		});

		fireEvent.change(getByLabelText(container, 'email-address'), {
			target: {value: 'test@liferay.com'}
		});

		fireEvent.change(getByLabelText(container, 'github-username'), {
			target: {value: 'test'}
		});

		fireEvent.click(getByText('submit'));

		expect(
			queryByText('please-correct-the-following-fields name')
		).toBeFalsy();
		expect(
			queryByText('name-is-required')
		).toBeFalsy();
	});

	it('allows the user to enter a name with a dash in it', () => {
		const {
			container,
			getByText,
			queryByText
		} = renderAddSourceCodeAccessModal();

		fireEvent.change(getByLabelText(container, 'name'), {
			target: {value: 'Julia Louis-Dreyfus'}
		});

		fireEvent.change(getByLabelText(container, 'email-address'), {
			target: {value: 'test@liferay.com'}
		});

		fireEvent.change(getByLabelText(container, 'github-username'), {
			target: {value: 'test'}
		});

		fireEvent.click(getByText('submit'));

		expect(
			queryByText('please-correct-the-following-fields name')
		).toBeFalsy();
		expect(
			queryByText('name-is-required')
		).toBeFalsy();
	});

	it('allows the user to enter a full name with a period in it', () => {
		const {
			container,
			getByText,
			queryByText
		} = renderAddSourceCodeAccessModal();

		fireEvent.change(getByLabelText(container, 'name'), {
			target: {value: 'John F. Kennedy'}
		});

		fireEvent.change(getByLabelText(container, 'email-address'), {
			target: {value: 'test@liferay.com'}
		});

		fireEvent.change(getByLabelText(container, 'github-username'), {
			target: {value: 'test'}
		});

		fireEvent.click(getByText('submit'));

		expect(
			queryByText('please-correct-the-following-fields name')
		).toBeFalsy();
		expect(
			queryByText('name-is-required')
		).toBeFalsy();
	});

	it('allows the user to enter a name with diacritics', () => {
		const {
			container,
			getByText,
			queryByText
		} = renderAddSourceCodeAccessModal();

		fireEvent.change(getByLabelText(container, 'name'), {
			target: {value: 'Léopold Louis-Dreyfus'}
		});

		fireEvent.change(getByLabelText(container, 'email-address'), {
			target: {value: 'test@liferay.com'}
		});

		fireEvent.change(getByLabelText(container, 'github-username'), {
			target: {value: 'test'}
		});

		fireEvent.click(getByText('submit'));

		expect(
			queryByText('please-correct-the-following-fields name')
		).toBeFalsy();
		expect(
			queryByText('name-is-required')
		).toBeFalsy();
	});

	it('allows the user to enter a name in a foreign language', () => {
		const {
			container,
			getByText,
			queryByText
		} = renderAddSourceCodeAccessModal();

		fireEvent.change(getByLabelText(container, 'name'), {
			target: {value: '田中太郎'}
		});

		fireEvent.change(getByLabelText(container, 'email-address'), {
			target: {value: 'test@liferay.com'}
		});

		fireEvent.change(getByLabelText(container, 'github-username'), {
			target: {value: 'test'}
		});

		fireEvent.click(getByText('submit'));

		expect(
			queryByText('please-correct-the-following-fields name')
		).toBeFalsy();
		expect(
			queryByText('name-is-required')
		).toBeFalsy();
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

	it('expects email domain name to be at least two characters long', () => {
		const {container, queryByText} = renderAddSourceCodeAccessModal();

		fireEvent.change(getByLabelText(container, 'name'), {
			target: {value: 'Test Test'}
		});
		fireEvent.change(getByLabelText(container, 'email-address'), {
			target: {value: 'test@liferay.d'}
		});
		fireEvent.change(getByLabelText(container, 'github-username'), {
			target: {value: 'test'}
		});

		fireEvent.click(queryByText('submit'));

		expect(
			queryByText('please-correct-the-following-fields email-address')
		).toBeTruthy();
		expect(queryByText('invalid-format')).toBeTruthy();

		fireEvent.change(getByLabelText(container, 'email-address'), {
			target: {value: 'test@liferay.digital'}
		});

		expect(queryByText('invalid-format')).toBeFalsy();
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
