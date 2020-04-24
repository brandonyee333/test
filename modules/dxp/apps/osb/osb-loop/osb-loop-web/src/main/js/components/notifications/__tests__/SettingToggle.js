import SettingToggle from '../SettingToggle';

const {
	email: TYPE_EMAIL,
	inApp: TYPE_IN_APP
} = LoopConstants.notificationTypes;

describe(
	'SettingToggle',
	() => {
		let component;

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}
			}
		);

		it(
			'renders',
			() => {
				component = new SettingToggle();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders unchecked when type is TYPE_IN_APP and value is false',
			() => {
				component = new SettingToggle(
					{
						type: TYPE_IN_APP,
						value: false
					}
				);

				const checked = component.element.querySelector('.checkbox-input').hasAttribute('checked');

				expect(checked).toBeFalsy();
			}
		);

		it(
			'renders checked when type is TYPE_IN_APP and value is true',
			() => {
				component = new SettingToggle(
					{
						type: TYPE_IN_APP,
						value: true
					}
				);

				const checked = component.element.querySelector('.checkbox-input').hasAttribute('checked');

				expect(checked).toBeTruthy();
			}
		);

		it(
			'does not render disabled when disabled is passed in as false',
			() => {
				component = new SettingToggle(
					{
						disabled: false,
						type: TYPE_EMAIL,
						value: true
					}
				);

				const disabled = component.element.querySelector('.checkbox-input').hasAttribute('disabled');

				expect(disabled).toBeFalsy();
			}
		);

		it(
			'renders disabled when disabled is passed in as true',
			() => {
				component = new SettingToggle(
					{
						disabled: true,
						type: TYPE_EMAIL,
						value: true
					}
				);

				const disabled = component.element.querySelector('.checkbox-input').hasAttribute('disabled');

				expect(disabled).toBeTruthy();
			}
		);
	}
);