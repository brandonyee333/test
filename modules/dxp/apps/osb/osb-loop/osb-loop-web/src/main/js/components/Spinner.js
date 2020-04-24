import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';

class Spinner extends Component {
	render() {
		const {size} = this.props;

		return (
			<div class="spinner-container">
				<div class={getCN('spinner', size)} />
			</div>
		);
	}
}

Spinner.PROPS = {
	size: Config.oneOf(['small'])
};

export default Spinner;