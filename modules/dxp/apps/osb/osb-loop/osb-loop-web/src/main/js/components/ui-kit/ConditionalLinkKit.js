import Component from 'metal-jsx';

import ConditionalLink from '../ConditionalLink';
import ElementContainer from './ElementContainer';
import ElementDisplay from './ElementDisplay';
import Kit from './Kit';

class ConditionalLinkKit extends Component {
	render() {
		return (
			<Kit header="ConditionalLinks">
				<ElementContainer>
					<ElementDisplay description={'{useSpan: true}'}>
						<ConditionalLink href="#" useSpan={true}>
							{'I am not a link!'}
						</ConditionalLink>
					</ElementDisplay>

					<ElementDisplay description={'{useSpan: false}'}>
						<ConditionalLink href="#" useSpan={false}>
							{'I am a link!'}
						</ConditionalLink>
					</ElementDisplay>
				</ElementContainer>
			</Kit>
		);
	};
}

export default ConditionalLinkKit;