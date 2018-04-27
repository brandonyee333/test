import JSXComponent, {Config} from 'metal-jsx';

class EntityDisplay extends JSXComponent {
	render() {
		const {entity} = this.props;

		return (
			<div class="entity-display-container">
				<div class="entity-info">
					<div class="name">
						<span>{entity.name}</span>
					</div>
				</div>
			</div>
		);
	}
}

EntityDisplay.PROPS = {
	entity: Config.object()
};

export default EntityDisplay;