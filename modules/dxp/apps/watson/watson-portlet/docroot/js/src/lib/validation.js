import {compact, flow, isArray, size, values} from 'lodash';

const getValueSize = flow(values, compact, size);

export default (validations, value) => {
	const errors = [];

	if (validations) {
		validations.forEach(
			validationType => {
				const validationFunction = validators[validationType];

				const result = validationFunction(value);

				if (result) {
					errors.push(result);
				}
			}
		);
	}

	return errors;
};

const validators = {
	arrayValues4(values = []) {
		let errorMessage = '';

		values.forEach(
			value => {
				if (getValueSize(value) < 4) {
					errorMessage = Liferay.Language.get('you-have-not-filled-out-all-the-required-fields');
				}
			}
		);

		return errorMessage;
	},

	arrayValues7(values = []) {
		let errorMessage = '';

		values.forEach(
			value => {
				if (getValueSize(value) < 7) {
					errorMessage = Liferay.Language.get('you-have-not-filled-out-all-the-required-fields');
				}
			}
		);

		return errorMessage;
	},

	date(value) {
		const dateRegex = /^(\d{4})-(\d{2})-(\d{2})$/;

		let errorMessage = '';

		if (value) {
			errorMessage = Liferay.Language.get('invalid-date');

			if (dateRegex.test(value)) {
				const matches = dateRegex.exec(value);

				if (matches[1].length == 4) {
					const month = parseInt(matches[2], 10);

					if (month >= 1 && month <= 12) {
						const day = parseInt(matches[3], 10);

						if (day >= 1 && day <= 31) {
							errorMessage = '';
						}
					}
				}
			}
		}

		if (!errorMessage) {
			const now = Date.now();

			const dateValue = Date.parse(value);

			if (dateValue > now) {
				errorMessage = Liferay.Language.get('the-date-cannot-be-in-the-future');
			}
		}

		return errorMessage;
	},

	emailAddress(value) {
		const emailRegex = /\S+@\S+\.\S+/;

		let errorMessage = Liferay.Language.get('please-enter-a-valid-email-address');

		if (value && emailRegex.test(value)) {
			errorMessage = '';
		}

		return errorMessage;
	},

	integer(value) {
		let errorMessage;

		if (value && !Number.isInteger(parseInt(value, 10))) {
			errorMessage = Liferay.Language.get('invalid-integer');
		}

		return errorMessage;
	},

	number(value) {
		let errorMessage;

		if (value && isNaN(value)) {
			errorMessage = Liferay.Language.get('invalid-number');
		}

		return errorMessage;
	},

	required(value) {
		let errorMessage;

		if (!value || value == '' || (isArray(value) && value.length < 1)) {
			errorMessage = Liferay.Language.get('field-is-required');
		}

		return errorMessage;
	}
};