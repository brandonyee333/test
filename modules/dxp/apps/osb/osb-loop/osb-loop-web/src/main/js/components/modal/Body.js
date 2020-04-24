import getCN from 'classnames';
import Component, {Config} from 'metal-jsx';

class Body extends Component {
	render() {
		const {children, scrollable} = this.props;

		const classNames = getCN(
			'modal-body-container',
			{
				'modal-body-scrollable': scrollable
			}
		);

		return <div {...this.otherProps()} class={classNames}>{children}</div>;
	}
}

Body.PROPS = {
	scrollable: Config.bool().value(false)
};

export default Body;