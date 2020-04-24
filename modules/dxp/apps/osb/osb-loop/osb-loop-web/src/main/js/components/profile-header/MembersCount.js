import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';

import IconLabel from '../IconLabel';
import {classNameIdToSchema} from '../../lib/util';
import {getPluralMessage} from '../../lib/lang-util';
import {MEMBERS} from '../../lib/router-constants';

class MembersCount extends Component {
	render() {
		const {displayURL, loopParticipantAssignmentsCount} = this.props;

		return (
			<a href={`${displayURL}/${MEMBERS}`}>
				<IconLabel
					display="primary"
					label={getPluralMessage(Liferay.Language.get('x-member'), Liferay.Language.get('x-members'), loopParticipantAssignmentsCount, true)}
					name="persons"
					size="small"
				/>
			</a>
		);
	}
}

const STORE = {
	loopParticipantAssignmentsCount: Config.number()
};

MembersCount.PROPS = {
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
)(MembersCount);