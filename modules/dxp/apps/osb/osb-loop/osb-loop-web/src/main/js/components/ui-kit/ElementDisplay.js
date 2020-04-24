import Component, {Config} from 'metal-jsx';

class ElementDisplay extends Component {
	render() {
		const {children, description} = this.props;

		return (
			<div class="element-wrapper">
				<div class="element">
					{children}
				</div>

				{description &&
					<div class="text-center">
						{description}
					</div>
				}
			</div>
		);
	};
}

ElementDisplay.PROPS = {
	description: Config.string()
};

export default ElementDisplay;