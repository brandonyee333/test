import Component, {Config} from 'metal-jsx';
import {Provider} from 'metal-redux';
import {bindAll} from 'lodash';
import {fromJS, Map, Range} from 'immutable';

import Button from '../Button';
import ElementContainer from './ElementContainer';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';
import {modalTypes} from '../../actions/modals';
import {Modal} from '../modal';

const IMAGE_POST_ID = 1;

const LINK_POST_ID = 2;

const POST_ID = 0;

const posts = Map(
	{
		[IMAGE_POST_ID]: Map({data: fromJS(LoopAssets.getPostImage(IMAGE_POST_ID))}),
		[LINK_POST_ID]: Map({data: fromJS(LoopAssets.getPostLink(LINK_POST_ID))}),
		[POST_ID]: Map({data: fromJS(LoopAssets.getPost(POST_ID))})
	}
);

const store = Map().set(
	'posts',
	posts
).setIn(
	['dirtyState', POST_ID],
	Map()
).set(
	'people',
	Range(0, 3).map(
		item => Map({data: fromJS(LoopAssets.getPerson(item))})
	).toMap()
);

class AssetEditModal extends Component {
	created() {
		bindAll(
			this,
			'closeModal_',
			'toggleModal_'
		);
	}

	closeModal_() {
		this.state.open_ = false;
	}

	toggleModal_(event) {
		this.setState(
			{
				id_: event.delegateTarget.getAttribute('data-id'),
				open_: !this.state.open_
			}
		);
	}

	render() {
		return (
			<Kit header="AssetEditModal">
				<Provider store={mockStore(store)}>
					<Modal
						hideModal={this.closeModal_}
						modalIMap={
							fromJS(
								{
									hideOnBlur: true,
									modalProps: {
										id: this.state.id_
									},
									modalType: modalTypes.EDIT_POST_MODAL,
									open: this.state.open_
								}
							)
						}
					/>
				</Provider>

				<ElementContainer>
					<Button data-id="0" onClick={this.toggleModal_} role="primary">{'Text Post'}</Button>
				</ElementContainer>

				<ElementContainer>
					<Button data-id="1" onClick={this.toggleModal_} role="primary">{'Image Post'}</Button>
				</ElementContainer>

				<ElementContainer>
					<Button data-id="2" onClick={this.toggleModal_} role="primary">{'Link Post'}</Button>
				</ElementContainer>
			</Kit>
		);
	}
}

AssetEditModal.STATE = {
	id_: Config.value('0'),
	open_: Config.value(false)
};

export default AssetEditModal;