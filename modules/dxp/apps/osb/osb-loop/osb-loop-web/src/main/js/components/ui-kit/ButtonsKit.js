import Component from 'metal-jsx';

import Button from '../Button';
import ElementContainer from './ElementContainer';
import ElementDisplay from './ElementDisplay';
import Kit from './Kit';
import Icon from '../Icon';

const BUTTON_DISPLAY_TYPES = [
	'danger',
	'dark',
	'info',
	'success',
	'warning'
];

const TEXT = 'Button';

class ButtonsKit extends Component {
	render() {
		return (
			<Kit header={Liferay.Language.get('buttons')}>
				<ElementContainer header={Liferay.Language.get('button-class')}>
					<ElementDisplay description=".btn.btn-primary">
						<Button role="primary">{TEXT}</Button>
					</ElementDisplay>

					<ElementDisplay description=".btn.btn-secondary">
						<Button role="secondary">{TEXT}</Button>
					</ElementDisplay>

					<ElementDisplay description=".btn.btn-cancel">
						<Button role="cancel">{TEXT}</Button>
					</ElementDisplay>
				</ElementContainer>

				<ElementContainer header={Liferay.Language.get('color-container')}>
					<ElementDisplay description=".btn">
						<Button>{TEXT}</Button>
					</ElementDisplay>

					<ElementDisplay description=".btn.btn-primary">
						<Button role="primary">{TEXT}</Button>
					</ElementDisplay>

					{
						BUTTON_DISPLAY_TYPES.map(
							type => (
								<ElementDisplay description={`.btn.btn-${type}`} key={type}>
									<Button display={type}>{TEXT}</Button>
								</ElementDisplay>
							)
						)
					}
				</ElementContainer>

				<ElementContainer header={Liferay.Language.get('color-icons')}>
					<ElementDisplay description=".btn.btn-badge.btn-primary">
						<Button role="primary" shape="badge">
							<Icon name="star" />
						</Button>
					</ElementDisplay>

					<ElementDisplay description=".btn.btn-badge.btn-text-primary">
						<Button shape="badge" textDisplay="primary">
							<Icon name="star" />
						</Button>
					</ElementDisplay>

					{
						BUTTON_DISPLAY_TYPES.map(
							type => (
								<ElementDisplay description={`.btn.btn-badge.btn-text-${type}`} key={type}>
									<Button shape="badge" textDisplay={type}>
										<Icon name="star" />
									</Button>
								</ElementDisplay>
							)
						)
					}
				</ElementContainer>

				<ElementContainer header={Liferay.Language.get('color-text')}>
					<ElementDisplay description=".btn.btn-primary">
						<Button role="primary">{TEXT}</Button>
					</ElementDisplay>

					<ElementDisplay description=".btn.btn-text-primary">
						<Button textDisplay="primary">{TEXT}</Button>
					</ElementDisplay>

					{
						BUTTON_DISPLAY_TYPES.map(
							type => (
								<ElementDisplay description={`.btn.btn-text-${type}`} key={type}>
									<Button textDisplay={type}>{TEXT}</Button>
								</ElementDisplay>
							)
						)
					}
				</ElementContainer>

				<ElementContainer header={Liferay.Language.get('fill')}>
					<ElementDisplay description=".btn.btn-primary">
						<Button role="primary">{TEXT}</Button>
					</ElementDisplay>

					<ElementDisplay description=".btn.btn-inverted">
						<Button fill="inverted">{TEXT}</Button>
					</ElementDisplay>

					<ElementDisplay description=".btn.btn-empty">
						<Button fill="empty">{TEXT}</Button>
					</ElementDisplay>

					<ElementDisplay description=".btn.btn-badge.btn-primary">
						<Button role="primary" shape="badge">
							<Icon name="star" />
						</Button>
					</ElementDisplay>

					<ElementDisplay description=".btn.btn-badge.btn-inverted">
						<Button fill="inverted" shape="badge">
							<Icon name="star" />
						</Button>
					</ElementDisplay>

					<ElementDisplay description=".btn.btn-badge.btn-empty">
						<Button fill="empty" shape="badge">
							<Icon name="star" />
						</Button>
					</ElementDisplay>
				</ElementContainer>

				<ElementContainer header={Liferay.Language.get('sizes')}>
					<ElementDisplay description=".btn.btn-primary">
						<Button role="primary">{TEXT}</Button>
					</ElementDisplay>

					<ElementDisplay description=".btn.btn-large.btn-primary">
						<Button large={true} role="primary">{TEXT}</Button>
					</ElementDisplay>

					<ElementDisplay description=".btn.btn-badge.btn-primary">
						<Button role="primary" shape="badge">
							<Icon name="star" />
						</Button>
					</ElementDisplay>

					<ElementDisplay description=".btn.btn-badge-large.btn-primary">
						<Button large={true} role="primary" shape="badge">
							<Icon name="star" />
						</Button>
					</ElementDisplay>
				</ElementContainer>

				<ElementContainer header={Liferay.Language.get('states')}>
					<ElementDisplay description="standard">
						<Button role="primary">{TEXT}</Button>
					</ElementDisplay>

					<ElementDisplay description="hovered">
						<Button elementClasses="hovered" role="primary">{TEXT}</Button>
					</ElementDisplay>

					<ElementDisplay description="focused">
						<Button elementClasses="focused" role="primary">{TEXT}</Button>
					</ElementDisplay>

					<ElementDisplay description="disabled">
						<Button disabled={true} role="primary">{TEXT}</Button>
					</ElementDisplay>
				</ElementContainer>
			</Kit>
		);
	}
}

export default ButtonsKit;