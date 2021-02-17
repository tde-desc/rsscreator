const React = require('react');
const client = require('./client');

export default class NewFeedConfig extends React.Component {
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(e) {
        e.preventDefault();
        const newFeedConfig = {};
        this.props.attributes.forEach(attribute => {
            newFeedConfig[attribute] = ReactDOM.findDOMNode(this.refs[attribute])
                .value.trim();
        });
        this.props.onCreate(newFeedConfig);

        //clear dialogs input
        this.props.attributes.forEach(attribute => {
            ReactDOM.findDOMNode(this.refs[attribute]).value = '';
        });
    }

    onCreate(newFeedConfig) {
    	follow(client, root, ['feedConfigs']).then(feedConfigCollection => {
    		return client({
    			method: 'POST',
    			path: '/api/rest/feed/config',
    			entity: newFeedConfig,
    			headers: {'Content-Type': 'application/json'}
    		})
    	}).then(response => {
    		return follow(client, root, [
    			{rel: 'feedConfigs', params: {'size': this.state.pageSize}}]);
    	});
    }

    render() {
        return (
            <div id="createFeedConfig">
                <div>
                    <h2>Create new FeedConfig</h2>
                    <form>
                        <p>
                            <input type="text" placeholder="ID" ref="ID" className="field" />
                        </p>
                        <button onClick={this.handleSubmit}>Create</button>
                    </form>
                </div>
            </div>
        )
    }
}