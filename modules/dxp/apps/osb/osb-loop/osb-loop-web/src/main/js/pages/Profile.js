import Component, {Config} from 'metal-jsx';
import {Map} from 'immutable';

import ProfileHeader from '../components/profile-header';

class Profile extends Component {
	render() {
		const {
			children,
			classNameId,
			id,
			parentEntityIMap
		} = this.props;

		return (
			<div class="profile-container">
				<ProfileHeader
					{...this.otherProps()}
					classNameId={classNameId}
					id={id}
					parentEntityIMap={parentEntityIMap}
				/>

				{children}
			</div>
		);
	}
}

Profile.PROPS = {
	classNameId: Config.number(),
	id: Config.number(),
	parentEntityIMap: Config.instanceOf(Map)
};

export default Profile;