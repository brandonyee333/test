import './AppCard.scss';

interface AppCardProps {
	category: string;
	logo: string;
	title: string;
}

export function AppCard({category, logo, title}: AppCardProps) {
	return (
		<div className="app-card-container">
			<div className="app-card-logo">
				<img alt="logo" className="app-card-logo-image" src={logo} />
			</div>

			<div className="app-card-info">
				<span className="app-card-info-description">{category}</span>

				<span className="app-card-info-text">{title}</span>
			</div>
		</div>
	);
}
