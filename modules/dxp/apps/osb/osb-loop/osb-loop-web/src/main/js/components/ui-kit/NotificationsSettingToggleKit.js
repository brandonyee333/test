import Component from 'metal-jsx';

import ElementContainer from './ElementContainer';
import ElementDisplay from './ElementDisplay';
import Kit from './Kit';
import SettingToggle from '../notifications/SettingToggle';

import LoopConstants from '../../lib/loop-constants';

const {
	email: TYPE_EMAIL,
	inApp: TYPE_IN_APP,
	none: TYPE_NONE
} = LoopConstants.notificationTypes;

class SettingToggleKit extends Component {
	render() {
		return (
			<Kit header="SettingToggle">
				<ElementContainer header="TYPE_IN_APP (visual only)">
					<ElementDisplay description="{value: TYPE_NONE}">
						<SettingToggle
							type={TYPE_IN_APP}
							value={TYPE_NONE}
						/>
					</ElementDisplay>
					<ElementDisplay description="{value: TYPE_IN_APP}">
						<SettingToggle
							type={TYPE_IN_APP}
							value={TYPE_IN_APP}
						/>
					</ElementDisplay>
					<ElementDisplay description="{value: TYPE_EMAIL}">
						<SettingToggle
							type={TYPE_IN_APP}
							value={TYPE_EMAIL}
						/>
					</ElementDisplay>
					<ElementDisplay description="{disabled: true, value: TYPE_NONE}">
						<SettingToggle
							disabled={true}
							type={TYPE_IN_APP}
							value={TYPE_NONE}
						/>
					</ElementDisplay>
					<ElementDisplay description="{disabled: true, value: TYPE_IN_APP}">
						<SettingToggle
							disabled={true}
							type={TYPE_IN_APP}
							value={TYPE_IN_APP}
						/>
					</ElementDisplay>
					<ElementDisplay description="{disabled: true, value: TYPE_EMAIL}">
						<SettingToggle
							disabled={true}
							type={TYPE_IN_APP}
							value={TYPE_EMAIL}
						/>
					</ElementDisplay>
				</ElementContainer>

				<ElementContainer header="TYPE_EMAIL (visual only)">
					<ElementDisplay description="{value: TYPE_NONE}">
						<SettingToggle
							type={TYPE_EMAIL}
							value={TYPE_NONE}
						/>
					</ElementDisplay>
					<ElementDisplay description="{value: TYPE_IN_APP}">
						<SettingToggle
							type={TYPE_EMAIL}
							value={TYPE_IN_APP}
						/>
					</ElementDisplay>
					<ElementDisplay description="{value: TYPE_EMAIL}">
						<SettingToggle
							type={TYPE_EMAIL}
							value={TYPE_EMAIL}
						/>
					</ElementDisplay>
					<ElementDisplay description="{disabled: true, value: TYPE_NONE}">
						<SettingToggle
							disabled={true}
							type={TYPE_EMAIL}
							value={TYPE_NONE}
						/>
					</ElementDisplay>
					<ElementDisplay description="{disabled: true, value: TYPE_IN_APP}">
						<SettingToggle
							disabled={true}
							type={TYPE_EMAIL}
							value={TYPE_IN_APP}
						/>
					</ElementDisplay>
					<ElementDisplay description="{disabled: true, value: TYPE_EMAIL}">
						<SettingToggle
							disabled={true}
							type={TYPE_EMAIL}
							value={TYPE_EMAIL}
						/>
					</ElementDisplay>
				</ElementContainer>
			</Kit>
		);
	}
}

export default SettingToggleKit;