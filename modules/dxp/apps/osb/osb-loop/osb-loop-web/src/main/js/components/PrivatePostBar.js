import Component, {Config} from 'metal-jsx';

import IconLabel from './IconLabel';

class PrivatePostBar extends Component {
	render() {
		return (
			<div class="private-post-bar-container">
				<IconLabel
					display="secondary"
					label={this.props.announcement ? Liferay.Language.get('private-announcement') : Liferay.Language.get('private-post')}
					name="lock"
					size="small"
					spacing="medium"
					title={Liferay.Language.get('shared-privately-with-the-people-groups-and-topics-that-are-mentioned')}
				/>
			</div>
		);
	}
}

PrivatePostBar.PROPS = {
	announcement: Config.bool()
};

export default PrivatePostBar;