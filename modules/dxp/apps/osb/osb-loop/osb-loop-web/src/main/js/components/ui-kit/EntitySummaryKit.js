import Component from 'metal-jsx';
import {fromJS, Map} from 'immutable';
import {Provider} from 'metal-redux';

import Button from '../Button';
import createStoreStateSwitcher from './StoreStateSwitcher';
import ElementContainer from './ElementContainer';
import EntitySummary from '../EntitySummary';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import LoopConstants from '../../lib/loop-constants';

const division = LoopAssets.getDivision();
const person = LoopAssets.getPerson();
const topic = LoopAssets.getTopic();

const {full} = LoopConstants.followingType;

const states = {
	'Following': Map(
		{
			division: Map().setIn(
				[0, 'data'],
				fromJS(
					{
						...division,
						following: true,
						followingType: full,
						notifyingEmail: false
					}
				)
			),
			people: Map().setIn(
				[0, 'data'],
				fromJS(
					{
						...person,
						following: true,
						followingType: full,
						notifyingEmail: false
					}
				)
			),
			topic: Map().setIn(
				[0, 'data'],
				fromJS(
					{
						...topic,
						following: true,
						followingType: full,
						notifyingEmail: false
					}
				)
			)
		}
	),
	'Not following': Map(
		{
			division: Map().setIn(
				[0, 'data'],
				fromJS(
					{
						...division,
						following: false,
						followingType: full,
						notifyingEmail: false
					}
				)
			),
			people: Map().setIn(
				[0, 'data'],
				fromJS(
					{
						...person,
						following: false,
						followingType: full,
						notifyingEmail: false
					}
				)
			),
			topic: Map().setIn(
				[0, 'data'],
				fromJS(
					{
						...topic,
						following: false,
						followingType: full,
						notifyingEmail: false
					}
				)
			)
		}
	)
};

const {store, StoreStateSwitcher} = createStoreStateSwitcher(states);

const EntitySummaryExample = ({entity, children}) => (
	<Provider store={store}>
		<EntitySummary
			entity={entity}
		>
			{children}
		</EntitySummary>
	</Provider>
);

class EntitySummaryKit extends Component {
	render() {
		return (
			<Kit header="EntitySummary">
				<StoreStateSwitcher store={store} />

				<ElementContainer header="hover to show">
					<EntitySummaryExample entity={person}>
						<Button role="primary">{'Hover Over!'}</Button>
					</EntitySummaryExample>
				</ElementContainer>

				<ElementContainer header="person">
					<EntitySummaryExample entity={person} />
				</ElementContainer>

				<ElementContainer header="topic">
					<EntitySummaryExample entity={topic} />
				</ElementContainer>

				<ElementContainer header="division">
					<EntitySummaryExample entity={division} />
				</ElementContainer>

				<ElementContainer header="division without cover image">
					<EntitySummaryExample
						entity={{
							...division,
							coverImageData: {
								imageURL_raw: null,
								imageURL_thumbnail: null,
								imageURL_web: null
							}
						}}
					/>
				</ElementContainer>

				<ElementContainer header="Inactive Person">
					<EntitySummaryExample
						entity={{
							...person,
							...LoopConstants.currentPerson,
							inactive: true
						}}
					/>
				</ElementContainer>
			</Kit>
		);
	}
}

export default EntitySummaryKit;