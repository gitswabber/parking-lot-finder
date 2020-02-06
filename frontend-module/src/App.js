import React, {Component} from 'react';
import ParkingLotList from "./components/ParkingLotList";
import Pagination from "react-js-pagination";
import {Button, ButtonGroup, ButtonToolbar} from 'react-bootstrap';

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            parkingLotList: [],
            address: '',
            name: '',
            tel: '',
            activePage: 1
        };
        this.handleAddressChange = this.handleAddressChange.bind(this);
        this.handleNameChange = this.handleNameChange.bind(this);
        this.handleTelChange = this.handleTelChange.bind(this);
        this.clickSearchButton = this.clickSearchButton.bind(this);
        this.handlePageChange = this.handlePageChange.bind(this);
        console.log("App constructor.");
    }

    componentDidMount() {
        console.log("Finished rendering App onto DOM.");
    }

    handleAddressChange(e) {
        this.setState({address: e.target.value});
    }

    handleNameChange(e) {
        this.setState({name: e.target.value});
    }

    handleTelChange(e) {
        this.setState({tel: e.target.value});
    }

    clickSearchButton() {
        this.setState({activePage: 1});
        fetch('http://localhost:8080/api/v1/parking-lots')
            .then(res => res.json())
            .then((data) => {
                this.setState({parkingLotList: data})
            })
            .catch(console.log)
        console.log("Data: " + this.state.parkingLotList);
    }

    handlePageChange(pageNumber) {
        console.log(`active page is ${pageNumber}`);
        this.setState({activePage: pageNumber});
    }

    render() {
        return (
            <div>
                <h2 align="center">Finding parking lot in Seoul!</h2>
                <form>
                    <label>
                        {/* todo https://react-bootstrap.github.io/components/input-group/ */}
                        Address : <input type="text" value={this.state.address} onChange={this.handleAddressChange}/>
                        Name : <input type="text" value={this.state.name} onChange={this.handleNameChange}/>
                        Tel : <input type="text" value={this.state.tel} onChange={this.handleTelChange}/>
                    </label>
                    <ButtonGroup>
                        <ButtonToolbar>
                            <Button variant="secondary" onClick={() => this.clickSearchButton()}>Search</Button>
                        </ButtonToolbar>
                    </ButtonGroup>
                </form>
                <ParkingLotList data={this.state.parkingLotList}/>
                <div className="d-flex justify-content-center">
                    <Pagination
                        activePage={this.state.activePage}
                        itemsCountPerPage={10}
                        totalItemsCount={450}
                        pageRangeDisplayed={5}
                        itemClass='page-item'
                        linkClass='page-link'
                        onChange={this.handlePageChange}
                    />
                </div>
            </div>
        )
    }
}

export default App;
