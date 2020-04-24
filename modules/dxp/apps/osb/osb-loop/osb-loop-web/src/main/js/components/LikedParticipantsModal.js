import Component, {Config} from 'metal-jsx';

import EntityListModal from './EntityListModal';
import sendRequest from '../lib/request';

export class LikedParticipantsModal extends Component {
	created() {
		this._request = sendRequest(
			{
				controller: 'feed',
				controllerMethod: 'viewLikedParticipants.json',
				data: {
					end: -1,
					id: this.props.id,
					start: -1
				},
				method: 'GET'
			}
		).then(
			items => this.setState(
				{
					items_: items,
					loading_: false
				}
			)
		);
	}

	disposed() {
		this._request.cancel();
	}

	render() {
		const {items_, loading_} = this.state;

		return (
			<EntityListModal
				hideModal={this.props.hideModal}
				items={items_}
				loading={loading_}
				title={Liferay.Language.get('people-who-liked-this-post')}
			/>
		);
	}
}

LikedParticipantsModal.PROPS = {
	hideModal: Config.func(),
	id: Config.number()
};

LikedParticipantsModal.STATE = {
	items_: Config.value([]),
	loading_: Config.value(true)
};

export default LikedParticipantsModal;