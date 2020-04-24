import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';

import IconLabel from '../IconLabel';
import {classNameIdToSchema} from '../../lib/util';
import {FOLLOWERS} from '../../lib/router-constants';
import {getPluralMessage} from '../../lib/lang-util';

class FollowersCount extends Component {
	render() {
		const {displayURL, followersCount} = this.props;
		return (
			<a href={`${displayURL}/${FOLLOWERS}`}>
				<IconLabel
					display="warning"
					label={getPluralMessage(Liferay.Language.get('x-follower'), Liferay.Language.get('x-followers'), followersCount, true)}
					name="star"
					size="small"
				/>
			</a>
		);
	}
}

const STORE = {
	followersCount: Config.number()
};

FollowersCount.PROPS = {
	...STORE,
	classNameId: Config.number(),
	displayURL: Config.string(),
	id: Config.number()
};

export default connect(
	(state, {classNameId, id}) => (
		{
			followersCount: state.getIn([classNameIdToSchema(classNameId), id, 'data', 'followersCount'])
		}
	)
)(FollowersCount);