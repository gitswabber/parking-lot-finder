import React, {Component} from 'react';
import ParkingLotList from "./components/ParkingLotList";
import Pagination from "react-js-pagination";
import {Form, InputGroup, FormControl, Button, ButtonGroup, ButtonToolbar} from 'react-bootstrap';

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            parkingLotList: [],
            address: '',
            name: '',
            tel: '',
            activePage: 1,
            itemsCountPerPage: 10,
            pageRangeDisplayed: 5,
            totalItemsCount: 0
        };
        this.handleAddressChange = this.handleAddressChange.bind(this);
        this.handleNameChange = this.handleNameChange.bind(this);
        this.handleTelChange = this.handleTelChange.bind(this);
        this.clickSearchButton = this.clickSearchButton.bind(this);
        this.handlePageChange = this.handlePageChange.bind(this);
        this.searchParkingLotList = this.searchParkingLotList.bind(this);
    }

    // todo handling for initialization
    componentDidMount() {
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
        this.searchParkingLotList(1);
    }

    handlePageChange(pageNumber) {
        this.setState({activePage: pageNumber});
        this.searchParkingLotList(pageNumber);
    }

    searchParkingLotList(pageNumber) {
        const activePage = pageNumber - 1;
        const parameters = "?address=" + this.state.address + "&name=" + this.state.name + "&tel=" + this.state.tel
            + "&page=" + activePage + "&size=" + this.state.itemsCountPerPage
            + "&sort=basicParkingFee,ASC";

        console.log(parameters);

        fetch('http://localhost:8080/api/v1/parking-lots' + parameters)
            .then(res => res.json())
            .then(data => {
                this.setState({
                    parkingLotList: data.itemResponseList,
                    totalItemsCount: data.totalItemsCount
                })
            })
            .catch(error => {
                alert("A system error has occurred : " + error);
            });
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
                    {/*                    <Form>
                        <InputGroup className="mb-3">
                            <InputGroup.Prepend>
                                <InputGroup.Text id="basic-addon1">Address</InputGroup.Text>
                            </InputGroup.Prepend>
                            <FormControl
                                placeholder="address"
                                aria-label="Username"
                                aria-describedby="basic-addon1"
                            />
                        </InputGroup>
                    </Form>
                    <ButtonGroup>
                        <ButtonToolbar>
                            <Button variant="secondary" onClick={() => this.clickSearchButton()}>Search</Button>
                        </ButtonToolbar>
                    </ButtonGroup>*/}
                </form>
                <ParkingLotList data={this.state.parkingLotList}/>
                <div className="d-flex justify-content-center">
                    <Pagination
                        activePage={this.state.activePage}
                        itemsCountPerPage={this.state.itemsCountPerPage}
                        totalItemsCount={this.state.totalItemsCount}
                        pageRangeDisplayed={this.state.pageRangeDisplayed}
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
