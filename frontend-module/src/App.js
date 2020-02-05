import React, {Component} from 'react';
import UserInformation from "./components/user-information";
import './css/App.css';
import Pagination from "react-js-pagination";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            userInfo: [],
            name: null,
            activePage: 1
        };
        this.searchUserList = this.searchUserList.bind(this);
        this.clickSearchButton = this.clickSearchButton(this);
        this.handlePageChange = this.handlePageChange(this);
        console.log("App constructor.");
    }

    componentDidMount() {
        console.log("Finished rendering App onto DOM.");
    }

    searchUserList() {
        fetch('http://localhost:8080/api/v1/users')
            .then(res => res.json())
            .then((data) => {
                this.setState({userInfo: data})
            })
            .catch(console.log)
    }

    clickSearchButton() {
        this.setState({activePage: 1});
        this.searchUser(this.state.name);
    }

    searchUser(name) {
        fetch('http://localhost:8080/api/v1/users&name='+name)
            .then(res => res.json())
            .then((data) => {
                this.setState({userInfo: data})
            })
            .catch(console.log)
    }

    handlePageChange(pageNumber) {
        console.log(`active page is ${pageNumber}`);
        this.setState({activePage: pageNumber});
    }

    render() {
        return (
            <div>
                <h1 align="center">User List</h1>
                <form onSubmit={this.handleSubmit}>
                    <label>
                        Name : <input type="text" value={this.state.name} onChange={this.clickSearchButton} />
                    </label>
                    <button className='btn btn-light' onClick={this.searchUserList}>Search</button>
                </form>
                <UserInformation userInfo={this.state.userInfo}/>
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
