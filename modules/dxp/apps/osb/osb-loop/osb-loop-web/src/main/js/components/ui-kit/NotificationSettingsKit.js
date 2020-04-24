import Component from 'metal-jsx';
import {noop} from 'lodash';

import {Settings} from '../notifications/Settings';
import ElementContainer from './ElementContainer';
import Kit from './Kit';

const styles = {
	display: 'block'
};

const categories = [
	{
		label: 'Test Category',
		settings: [
			{
				key: 'testSetting',
				label: 'Test Setting'
			}
		]
	}
];

const settings = {
	testSetting: {
		email: true,
		inApp: true
	}
};

class SettingsKit extends Component {
	render() {
		return (
			<Kit header="Settings">
				<ElementContainer header="All settings set" style={styles}>
					<Settings
						categories={categories}
						confirmActive={false}
						onConfirm={noop}
						onConfirmCancel={noop}
						settings={settings}
						showWarning={false}
						toggleWarning={noop}
					/>
				</ElementContainer>

				<ElementContainer header="Confirm Mask" style={styles}>
					<Settings
						categories={categories}
						confirmActive={true}
						onConfirm={noop}
						onConfirmCancel={noop}
						settings={settings}
						showWarning={false}
						toggleWarning={noop}
					/>
				</ElementContainer>
			</Kit>
		);
	}
}

export default SettingsKit;