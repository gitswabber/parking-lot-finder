import React from "react";
import {Table} from 'react-bootstrap';

const ParkingLotList = ({data: parkingLotList}) => {
    return (
        <div style={{marginLeft: "5%", marginRight: "5%"}}>
            <Table responsive>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Address</th>
                    <th>Tel</th>
                    <th>Available Now</th>
                    <th>Business hour</th>
                    <th>Fee</th>
                    <th>Use time</th>
                </tr>
                </thead>
                <tbody>
                {parkingLotList.map((parkingLot, i) => (
                        <tr key={parkingLot.code}>
                            <td>{parkingLot.name}</td>
                            <td>{parkingLot.address}</td>
                            <td>{parkingLot.tel}</td>
                            <td>{parkingLot.available ? "Yes" : "No"}</td>
                            <td>{parkingLot.openingTime} - {parkingLot.closingTime}</td>
                            <td>{parkingLot.basicParkingFee}</td>
                            <td>{parkingLot.basicParkingMin}</td>
                        </tr>
                ))}
                </tbody>
            </Table>
        </div>
    )
}

export default ParkingLotList