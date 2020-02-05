import React from "react";

const UserInformation = ({userInfo}) => {

    console.log(userInfo);

    return (
        <div>
            {userInfo.map((user, i) => (
                <div className="card" key={user.id}>
                    <div className="card-body">
                        <h5 className="card-title">{user.name}</h5>
                        <h6 className="card-subtitle mb-2 text-muted">{user.job}</h6>
                        <p className="card-text">{user.description}</p>
                    </div>
                </div>
            ))}
        </div>
    )
}

export default UserInformation