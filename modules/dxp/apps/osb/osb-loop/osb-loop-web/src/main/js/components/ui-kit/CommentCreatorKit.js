import Component from 'metal-jsx';
import Promise from 'metal-promise';
import {fromJS, Map} from 'immutable';
import {Provider} from 'metal-redux';

import CommentCreator from '../CommentCreator';
import ElementContainer from './ElementContainer';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import LoopConstants from '../../lib/loop-constants';
import mockStore from '../../tests/mock-store';

const currentPersonClassPK = LoopConstants.currentPerson.entityClassPK;

const storeState = fromJS(
	{
		dirtyState: Map(),
		people: Map().mergeIn(
			[currentPersonClassPK, 'data'],
			fromJS(LoopAssets.getPerson(currentPersonClassPK))
		)
	}
);

const store = mockStore(storeState);

function handleSubmit() {
	return new Promise(
		resolve => setTimeout(
			() => resolve(true),
			1000
		)
	);
}

const style = {
	display: 'block'
};

class CommentCreatorKit extends Component {
	render() {
		return (
			<Kit header="CommentCreator">
				<ElementContainer style={style}>
					<Provider store={store}>
						<CommentCreator parentId={0} submitMethod={handleSubmit} />
					</Provider>
				</ElementContainer>
			</Kit>
		);
	}
}

export default CommentCreatorKit;