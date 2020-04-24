import Component from 'metal-jsx';
import {range} from 'lodash';

import Icon from '../Icon';
import ElementContainer from './ElementContainer';
import ElementDisplay from './ElementDisplay';
import Kit from './Kit';

class IconKit extends Component {
	render() {
		return (
			<Kit header="Icons">
				<ElementContainer header="Size small">
					{
						range(1, 5).map(
							size => (
								<ElementDisplay description={`.loop-icon-small-${size}x`} key={size}>
									<Icon multiplier={size} name="alert" size="small" />
								</ElementDisplay>
							)
						)
					}
				</ElementContainer>

				<ElementContainer header="Size large">
					{
						range(1, 5).map(
							size => (
								<ElementDisplay description={`.loop-icon-large-${size}x`} key={size}>
									<Icon multiplier={size} name="camera" size="large" />
								</ElementDisplay>
							)
						)
					}
				</ElementContainer>

				<ElementContainer header="Size Tiny">
					<ElementDisplay description=".loop-icon-tiny-1x">
						<Icon name="camera" size="tiny" />
					</ElementDisplay>
				</ElementContainer>

				<ElementContainer header="display">
					{
						['danger', 'dark', 'default', 'inactive', 'info', 'primary', 'secondary', 'success', 'warning'].map(
							display => (
								<ElementDisplay description={`.loop-icon-${display}`} key={display}>
									<Icon
										display={display}
										multiplier={2}
										name="alert"
										size="small"
									/>
								</ElementDisplay>
							)
						)
					}
				</ElementContainer>

				<ElementContainer header="colors-inverted">
					{
						['danger', 'dark', 'default', 'inactive', 'info', 'primary', 'secondary', 'success', 'warning'].map(
							display => (
								<ElementDisplay description={`.loop-icon-${display}`} key={display}>
									<Icon
										display={display}
										invertedColor={true}
										name="alert"
										size="small"
									/>
								</ElementDisplay>
							)
						)
					}
				</ElementContainer>
			</Kit>
		);
	}
}

export default IconKit;