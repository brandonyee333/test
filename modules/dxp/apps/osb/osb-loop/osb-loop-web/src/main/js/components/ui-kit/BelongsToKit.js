import Component from 'metal-jsx';

import BelongsTo from '../BelongsTo';
import ElementContainer from './ElementContainer';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';

class BelongsToKit extends Component {
	render() {
		return (
			<Kit header="BelongsTo">
				<ElementContainer header="Default">
					<BelongsTo entity={LoopAssets.getDivision()} />
				</ElementContainer>

				<ElementContainer header="{invert: true}">
					<BelongsTo entity={LoopAssets.getDivision()} invert={true} />
				</ElementContainer>
			</Kit>
		);
	};
}

export default BelongsToKit;