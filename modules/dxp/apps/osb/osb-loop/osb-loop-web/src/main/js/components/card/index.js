import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';

import Body from './Body';
import Footer from './Footer';
import Header from './Header';

class Card extends Component {
	render() {
		const {children, floating} = this.props;

		return (
			<div {...this.otherProps()} class={getCN('card-container', {floating})}>
				{children}
			</div>
		);
	}
}

Card.PROPS = {
	floating: Config.bool().value(false)
};

Card.Body = Body;
Card.Header = Header;
Card.Footer = Footer;

export default Card;