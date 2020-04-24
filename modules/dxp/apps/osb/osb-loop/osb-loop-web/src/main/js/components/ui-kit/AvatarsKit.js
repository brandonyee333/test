import Component from 'metal-jsx';
import {range} from 'lodash';

import Avatar from '../Avatar';
import ElementContainer from './ElementContainer';
import ElementDisplay from './ElementDisplay';
import Kit from './Kit';
import LoopConstants from '../../lib/loop-constants';

const DEFAULT_INDEX = 8;

const TEAM_OBJ = {
	name: 'Test Team',
	type: 'team'
};

class AvatarsKit extends Component {
	render() {
		return (
			<Kit header={Liferay.Language.get('avatars')}>
				<ElementContainer header={Liferay.Language.get('avatar-class')}>
					<ElementDisplay description={`(${Liferay.Language.get('image')})`}>
						<Avatar entity={LoopConstants.currentPerson} />
					</ElementDisplay>

					<ElementDisplay description={`(${Liferay.Language.get('icon')})`}>
						<Avatar
							entity={{
								...LoopConstants.currentPerson,
								...TEAM_OBJ
							}}
						/>
					</ElementDisplay>

					<ElementDisplay description={`(${Liferay.Language.get('initials')})`}>
						<Avatar
							entity={{
								...LoopConstants.currentPerson
							}}
						/>
					</ElementDisplay>
				</ElementContainer>

				<ElementContainer header={Liferay.Language.get('colors')}>
					{
						range(10).map(
							value => (
								<ElementDisplay description={`.avatar-color-${value}`} key={value}>
									<Avatar
										entity={{
											...TEAM_OBJ,
											entityClassPK: value
										}}
									/>
								</ElementDisplay>
							)
						)
					}
				</ElementContainer>

				<ElementContainer header={Liferay.Language.get('colors-inverted')}>
					{
						range(10).map(
							value => (
								<ElementDisplay description={`.avatar-color-${value}.inverted`} key={value}>
									<Avatar
										entity={{
											...TEAM_OBJ,
											entityClassPK: value
										}}
										invertedColor={true}
									/>
								</ElementDisplay>
							)
						)
					}
				</ElementContainer>

				<ElementContainer header={Liferay.Language.get('sizes')}>
					{
						['tiny', 'smallest', 'small', 'large', 'largest'].map(
							value => (
								<ElementDisplay description={value ? `.avatar-size-${value}` : ''} key={value}>
									<Avatar
										entity={TEAM_OBJ}
										index={DEFAULT_INDEX}
										size={value}
									/>
								</ElementDisplay>
							)
						)
					}
				</ElementContainer>

				<ElementContainer header={Liferay.Language.get('states')}>
					<ElementDisplay description=".hover">
						<Avatar
							avatarClasses="hover"
							entity={{
								...TEAM_OBJ,
								displayURL: 'javascript:;'
							}}
							index={DEFAULT_INDEX}
						/>
					</ElementDisplay>

					<ElementDisplay description=".focus">
						<Avatar
							avatarClasses="focus"
							entity={{
								...TEAM_OBJ,
								displayURL: 'javascript:;'
							}}
							index={DEFAULT_INDEX}
						/>
					</ElementDisplay>

					<ElementDisplay description=".disabled">
						<Avatar
							entity={{
								...TEAM_OBJ,
								inactive: true
							}}
							index={DEFAULT_INDEX}
						/>
					</ElementDisplay>

					<ElementDisplay description=".disabled">
						<Avatar
							entity={{
								...LoopConstants.currentPerson,
								inactive: true
							}}
							index={DEFAULT_INDEX}
						/>
					</ElementDisplay>
				</ElementContainer>
			</Kit>
		);
	};
}

export default AvatarsKit;