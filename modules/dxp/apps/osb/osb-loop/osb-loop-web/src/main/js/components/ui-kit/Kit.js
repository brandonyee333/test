import Component, {Config} from 'metal-jsx';

import Card from '../card';

class Kit extends Component {
	render() {
		const {card, children, header} = this.props;

		let kitContainer = (
			<div class="kit-container">
				<h1 class="element-header-1">{header}</h1>

				{children}
			</div>
		);

		if (card) {
			kitContainer = (
				<Card>
					{kitContainer}
				</Card>
			);
		}

		return kitContainer;
	}
}

Kit.PROPS = {
	card: Config.bool().value(true),
	header: Config.string()
};

export default Kit;