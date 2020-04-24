import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List} from 'immutable';

import Avatar from './Avatar';
import Button from './Button';
import {getRel} from '../lib/selectors';

class LikeCounter extends Component {
	render() {
		const {count, onOtherClick, peopleIList} = this.props;

		const otherCount = count - peopleIList.size;

		return (
			<div class="like-counter-container">
				<span class="secondary-info">
					{Liferay.Language.get('people-who-liked-this-post')}
				</span>

				<div class="participants">
					{otherCount > 0 &&
						<Button
							elementClasses="btn-plus-more-pill"
							onClick={onOtherClick}
							shape="pill"
						>
							{'+'}{otherCount}
						</Button>
					}

					{
						peopleIList.toJS().map(
							person => (
								<Avatar
									entity={person}
									key={person.entityClassPK}
									size="smallest"
									summary={true}
								/>
							)
						)
					}
				</div>
			</div>
		);
	}
}

const STORE = {
	peopleIList: Config.instanceOf(List)
};

LikeCounter.PROPS = {
	...STORE,
	count: Config.number(),
	onOtherClick: Config.func(),
	participantsIList: Config.instanceOf(List)
};

export default connect(
	(state, ownProps) => (
		{
			peopleIList: getRel('people', state, ownProps.participantsIList)
		}
	),
)(LikeCounter);