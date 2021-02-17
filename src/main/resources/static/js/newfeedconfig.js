const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

export default class NewFeedConfig extends React.Component {
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(e) {
        e.preventDefault();
        const newFeedConfig = {};
        const node = ReactDOM.findDOMNode(e.target.parentElement)
        node.querySelectorAll(".field").forEach(field => {
            newFeedConfig[field.name] = field.value.trim();
        });
        this.onCreate(newFeedConfig);

        //clear dialogs input
        node.querySelectorAll(".field").forEach(field => {
            field.value = '';
        });
    }

    onCreate(newFeedConfig) {
    	client({
    			method: 'POST',
    			path: '/api/rest/feed/config',
    			entity: newFeedConfig,
    			headers: {'Content-Type': 'application/json'}
    	});
    }

    render() {
        return (
            <div id="createFeedConfig">
                <div>
                    <h3>Create new FeedConfig</h3>
                    <form>
                        <p><input type="text" placeholder="ID" name="id" className="field" /></p>
                        <p><input type="text" placeholder="Name" name="name" className="field" /></p>
                        <p><input type="text" placeholder="Beschreibung" name="description" className="field" /></p>
                        <p><input type="text" placeholder="URL" name="url" className="field" /></p>
                        <p><input type="text" placeholder="Eintrag" name="entryIdentifier" className="field" /></p>
                        <p><input type="text" placeholder="Titel" name="titleIdentifier" className="field" /></p>
                        <p><input type="text" placeholder="Bild" name="pictureIdentifier" className="field" /></p>
                        <p><input type="text" placeholder="Autor" name="authorIdentifier" className="field" /></p>
                        <p><input type="text" placeholder="Beschreibung" name="descriptionIdentifier" className="field" /></p>
                        <p><input type="text" placeholder="URL" name="urlIdentifier" className="field" /></p>
                        <button onClick={this.handleSubmit}>Create</button>
                    </form>
                </div>
            </div>
        )
    }
}