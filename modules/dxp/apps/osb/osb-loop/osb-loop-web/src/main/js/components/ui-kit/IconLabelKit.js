import Component from 'metal-jsx';
import {range} from 'lodash';

import IconLabel from '../IconLabel';
import ElementContainer from './ElementContainer';
import ElementDisplay from './ElementDisplay';
import Kit from './Kit';

const LABEL = 'LongLikelyToWrapLabel';

class IconLabelKit extends Component {
	render() {
		return (
			<Kit header="IconLabel">
				<ElementContainer header="Size small">
					{
						range(1, 5).map(
							size => (
								<ElementDisplay description={`.loop-icon-small-${size}x`} key={size}>
									<IconLabel
										label={LABEL}
										multiplier={size}
										name="alert"
										size="small"
									/>
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
									<IconLabel
										label={LABEL}
										multiplier={size}
										name="camera"
										size="large"
									/>
								</ElementDisplay>
							)
						)
					}
				</ElementContainer>

				<ElementContainer header="display">
					{
						['danger', 'dark', 'info', 'primary', 'secondary', 'success', 'warning'].map(
							display => (
								<ElementDisplay description={`.loop-icon-${display}`} key={display}>
									<IconLabel
										display={display}
										label={LABEL}
										multiplier={2}
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

export default IconLabelKit;