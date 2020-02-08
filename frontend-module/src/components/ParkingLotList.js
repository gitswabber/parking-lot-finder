import React from "react";

const ParkingLotList = ({data: parkingLotList}) => {

    return (
        <div>
            {parkingLotList.map((parkingLot, i) => (
                <div className="card" key={parkingLot.code}>
                    <div className="card-body">
                        <h5 className="card-title">{parkingLot.name}</h5>
                        <h6 className="card-subtitle mb-2 text-muted">{parkingLot.address}</h6>
                        <p className="card-text">{parkingLot.tel}</p>
                        <p className="card-text">Open : {parkingLot.openingTime}</p>
                        <p className="card-text">Close : {parkingLot.closingTime}</p>
                        <p className="card-text">{parkingLot.available ? "Parking available" : "Parking not available"}</p>
                    </div>
                </div>
            ))}
        </div>
    )
}

export default ParkingLotList