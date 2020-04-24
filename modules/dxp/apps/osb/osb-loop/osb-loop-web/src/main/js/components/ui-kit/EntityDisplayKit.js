import Component from 'metal-jsx';

import ElementContainer from './ElementContainer';
import EntityDisplay from '../EntityDisplay';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';

class EntityDisplayKit extends Component {
	render() {
		return (
			<Kit header="EntityDisplay">
				<ElementContainer header="person">
					<EntityDisplay entity={LoopAssets.getPerson()} />
				</ElementContainer>

				<ElementContainer header="division">
					<EntityDisplay entity={LoopAssets.getDivision()} />
				</ElementContainer>

				<ElementContainer header="topic">
					<EntityDisplay entity={LoopAssets.getTopic()} />
				</ElementContainer>

				<ElementContainer header="overflow" style="max-width: 200px; overflow: hidden">
					<EntityDisplay
						entity={{
							...LoopAssets.getPerson(),
							jobTitle: 'AReallyLongJobTitleThatWillAlsoOverflow',
							name: 'ThisIsAReallyLongName ForAPersonToHaveAndWillOverflow'
						}}
					/>
				</ElementContainer>
			</Kit>
		);
	}
}

export default EntityDisplayKit;