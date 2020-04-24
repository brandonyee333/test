import Component, {Config} from 'metal-jsx';

import Card from './card';
import Icon from './Icon';
import ModalLink from './ModalLink';

class CreateEntityCard extends Component {
	render() {
		const {label, modalConfig} = this.props;

		return (
			<Card elementClasses="create-entity-card-container">
				<ModalLink config={modalConfig}>
					{label}

					<Icon display="secondary" name="plus" size="small" />
				</ModalLink>
			</Card>
		);
	}
}

CreateEntityCard.Props = {
	label: Config.string(),
	modalConfig: Config.object()
};

export default CreateEntityCard;