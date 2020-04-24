import Component, {Config} from 'metal-jsx';

import EntityDisplay from './EntityDisplay';
import IconLabel from './IconLabel';
import LoadingWrapper from './LoadingWrapper';
import Modal from './modal';

class EntityListModal extends Component {
	render() {
		const {
			hideModal,
			iconName,
			items,
			loading,
			title
		} = this.props;

		return (
			<div class="entity-list-modal-container">
				<Modal.Header onClose={hideModal}>
					{iconName ? <IconLabel label={title} name={iconName} /> : title}
				</Modal.Header>

				<Modal.Body scrollable={true}>
					<LoadingWrapper loading={loading}>
						<ul>
							{
								items.map(
									(item, i) => (
										<li key={i}>
											<EntityDisplay entity={item} inlineFollow={true} summary={false} />
										</li>
									)
								)
							}
						</ul>
					</LoadingWrapper>
				</Modal.Body>
			</div>
		);
	}
}

EntityListModal.PROPS = {
	hideModal: Config.func(),
	iconName: Config.string(),
	items: Config.array(),
	loading: Config.bool().value(false),
	title: Config.string()
};

export default EntityListModal;