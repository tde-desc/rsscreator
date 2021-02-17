const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = { feedConfigs: [] }
    }

    componentDidMount() {
        client({ method: 'GET', path: '/api/rest/feed/config' }).done(response => {
            alert(response.entity);
            this.setState({ feedConfigs: response.entity })
        })
    }
    render() {
        return (
            <FeedConfigList feedConfigs={this.state.feedConfigs} />
        )
    }
}

class FeedConfigList extends React.Component{
	render() {
		const feedConfigs = this.props.feedConfigs.map(feedConfig =>
			<FeedConfig key={feedConfig.id} feedConfig={feedConfig}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Description</th>
					</tr>
					{feedConfigs}
				</tbody>
			</table>
		)
	}
}

class FeedConfig extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.feedConfig.id}</td>
				<td>{this.props.feedConfig.name}</td>
				<td>{this.props.feedConfig.description}</td>
			</tr>
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)