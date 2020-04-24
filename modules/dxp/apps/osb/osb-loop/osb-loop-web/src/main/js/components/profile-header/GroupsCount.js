import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';

import IconLabel from '../IconLabel';
import {classNameIdToSchema} from '../../lib/util';
import {getPluralMessage} from '../../lib/lang-util';
import {GROUPS} from '../../lib/router-constants';

class GroupsCount extends Component {
	render() {
		const {displayURL, loopParticipantAssignmentsCount} = this.props;

		return (
			<a href={`${displayURL}/${GROUPS}`}>
				<IconLabel
					display="primary"
					label={getPluralMessage(Liferay.Language.get('x-group'), Liferay.Language.get('x-groups'), loopParticipantAssignmentsCount, true)}
					name="groups"
					size="small"
				/>
			</a>
		);
	}
}

const STORE = {
	loopParticipantAssignmentsCount: Config.number()
};

GroupsCount.PROPS = {
	...STORE,
	classNameId: Config.number(),
	displayURL: Config.string(),
	id: Config.number()
};

export default connect(
	(state, {classNameId, id}) => (
		{
			loopParticipantAssignmentsCount: state.getIn([classNameIdToSchema(classNameId), id, 'data', 'loopParticipantAssignmentsCount'])
		}
	)
)(GroupsCount);