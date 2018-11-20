

class JorgenBucket extends HTMLElement {
    constructor() {
        super();
        this.root = this.attachShadow({mode: "open"});
    }

    connectedCallback() {
        console.log('jorgen-bucket connect');
        this.render();
    }

    render() {
        this.root.innerHTML = `
            <div>
                <label>Förnamn<input type="text" value="${this.firstName}"/></label>
                <label>Efternamn<input type="text" value="${this.lastName}"/></label>
                <label>Ålder<input type="text" value="${this.age}"/></label>
            </div>
        `;
    }

    setData(cls) {
        console.log("jorgen-bucket setting data");
        this.data = cls;
        this.render();
    }

    getData() {
        let inputs = this.shadowRoot.querySelectorAll("input");
        console.log("There are " + inputs.length + " inputs in element");
        return new example(inputs[0].value, inputs[1].value, parseInt(inputs[2].value));
    }

    get firstName() {
        return this.data ? this.data.firstName : "";
    }

    get lastName() {
        return this.data ? this.data.lastName : "";
    }

    get age() {
        return this.data ? this.data.age : "";

    }

}


if (!window.customElements.get('jorgen-bucket')) {
    customElements.whenDefined('jorgen-bucket').then(() => { console.log('jorgen-bucket defined'); });
    customElements.define("jorgen-bucket", JorgenBucket);
}
