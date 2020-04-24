import Component from 'metal-jsx';
import {range} from 'lodash';

import ElementContainer from './ElementContainer';
import EntityPillList from '../EntityPillList';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';

const topics = range(5).map(item => LoopAssets.getTopic(item));

topics.push(LoopAssets.getTopic('AReallyLongNameThatShouldWrap'));

const styles = {
	display: 'block',
	width: '250px'
};

class ProfileDetailCardKit extends Component {
	render() {
		return (
			<Kit header="EntityPillList">
				<ElementContainer style={styles}>
					<EntityPillList entities={topics} />
				</ElementContainer>
			</Kit>
		);
	}
}

export default ProfileDetailCardKit;