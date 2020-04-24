import dom from 'metal-dom';

import sendRequest from '../../../lib/request';
import SettingsContainer, {Settings} from '../Settings';

const settings = {
	testSetting: {
		email: true,
		inApp: true
	},
	testSetting2: {
		email: true,
		inApp: true
	}
};

const categories = [
	{
		label: 'Test Category',
		settings: [
			{
				key: 'testSetting',
				label: 'Test Setting'
			},
			{
				key: 'testSetting2',
				label: 'Test Setting2'
			}
		]
	}
];

describe(
	'Settings',
	() => {
		let component;

		sendRequest.mockResponseValue(
			{
				categories,
				showWarning: true
			}
		);

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
				component = new Settings(
					{
						categories,
						confirmActive: false,
						onConfirm: jest.fn(),
						onConfirmCancel: jest.fn(),
						settings,
						showWarning: false,
						toggleWarning: jest.fn()
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'calls sendRequest on created',
			() => {
				component = new SettingsContainer(
					{
						categories,
						confirmActive: false,
						onConfirm: jest.fn(),
						onConfirmCancel: jest.fn(),
						settings,
						showWarning: false,
						toggleWarning: jest.fn()
					}
				);

				expect(sendRequest).toBeCalled();
			}
		);

		it(
			'calls toggleWarning',
			() => {
				const spy = jest.fn();

				component = new Settings(
					{
						categories,
						confirmActive: false,
						onConfirm: jest.fn(),
						onConfirmCancel: jest.fn(),
						settings,
						showWarning: false,
						toggleWarning: spy
					}
				);

				const inputs = component.element.querySelectorAll('table .checkbox-input');

				dom.triggerEvent(inputs[2], 'click');

				jest.runAllTimers();

				expect(spy).toBeCalled();
			}
		);
	}
);