import Component, {Config} from 'metal-jsx';

import IconLabel from './IconLabel';

class MenuIconLabel extends Component {
	render() {
		return (
			<IconLabel
				{...this.otherProps()}
				display={this.props.display}
				size="small"
				spacing="large"
			/>
		);
	}
}

MenuIconLabel.PROPS = {
	display: Config.string().value('default')
};

export default MenuIconLabel;