import Component from 'metal-jsx';
import {Map} from 'immutable';

import FollowersCount from '../profile-header/FollowersCount';
import Kit from './Kit';
import LoopConstants from '../../lib/loop-constants';
import mockStore from '../../tests/mock-store';

const state = Map().setIn(['people', 0, 'data', 'followersCount'], 23);

class FollowersCountKit extends Component {
	render() {
		return (
			<Kit>
				<FollowersCount
					classNameId={LoopConstants.classNameIds.people}
					id={0}
					store={mockStore(state)}
				/>
			</Kit>
		);
	}
}

export default FollowersCountKit;