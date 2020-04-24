import Component, {Config} from 'metal-jsx';
import {fromJS} from 'immutable';

import Button from '../Button';
import ElementContainer from './ElementContainer';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import {Modal} from '../modal';
import {modalTypes} from '../../actions/modals';

const division = LoopAssets.getDivision();
const person = LoopAssets.getPerson();
const topic = LoopAssets.getTopic();

class EntityListModalKit extends Component {
	created() {
		this.toggleModal_ = this.toggleModal_.bind(this);
	}

	toggleModal_() {
		const {open_} = this.state;

		if (!open_) {
			this.state.loading_ = true;

			setTimeout(
				() => {
					this.state.loading_ = false;
				},
				1000
			);
		}

		this.state.open_ = !open_;
	}

	render() {
		const {loading_, open_} = this.state;

		return (
			<Kit header="EntityListModal">
				<Modal
					hideModal={this.toggleModal_}
					modalIMap={
						fromJS(
							{
								hideOnBlur: true,
								modalProps: {
									items: [
										division,
										person,
										topic
									],
									loading: loading_,
									title: 'EntityListModal'
								},
								modalType: modalTypes.ENTITY_LIST,
								open: open_
							}
						)
					}
				/>

				<ElementContainer>
					<Button onClick={this.toggleModal_} role="primary">{'Click to Open'}</Button>
				</ElementContainer>
			</Kit>
		);
	}
}

EntityListModalKit.STATE = {
	loading_: Config.value(true),
	open_: Config.value(false)
};

export default EntityListModalKit;