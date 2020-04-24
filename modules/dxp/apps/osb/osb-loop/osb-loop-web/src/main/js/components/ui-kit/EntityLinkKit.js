import Component from 'metal-jsx';

import EntityLink from '../EntityLink';
import ElementContainer from './ElementContainer';
import ElementDisplay from './ElementDisplay';
import Kit from './Kit';
import LoopConstants from '../../lib/loop-constants';

const DUMMY_TOPIC = {
	displayURL: '#',
	entityClassNameId: LoopConstants.classNameIds.topics,
	name: 'TestTopic'
};

class EntityLinkKit extends Component {
	render() {
		return (
			<Kit header="EntityLinks">
				<ElementContainer>
					<ElementDisplay description={`(${Liferay.Language.get('default')})`}>
						<EntityLink entity={LoopConstants.currentPerson} />
					</ElementDisplay>

					<ElementDisplay description={`(${Liferay.Language.get('inactive')})`}>
						<EntityLink
							entity={{
								...LoopConstants.currentPerson,
								inactive: true
							}}
						/>
					</ElementDisplay>

					<ElementDisplay description={`(${Liferay.Language.get('disabled')})`}>
						<EntityLink disabled={true} entity={LoopConstants.currentPerson} />
					</ElementDisplay>

					<ElementDisplay description={`(${Liferay.Language.get('topic')})`}>
						<EntityLink entity={DUMMY_TOPIC} />
					</ElementDisplay>

					<ElementDisplay description={'{showTrigger: false}'}>
						<EntityLink entity={DUMMY_TOPIC} showTrigger={false} />
					</ElementDisplay>
				</ElementContainer>
			</Kit>
		);
	};
}

export default EntityLinkKit;