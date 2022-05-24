import {Link} from "react-router-dom";
import React, {useContext} from "react";
import {UserInfoContext} from "../utils/UserInfoContext";
import {removeFromLocalStorage} from "../utils/storage";


const NavBar = () => {

    // @ts-ignore
    const {userInfo} = useContext(UserInfoContext);

    const isAdmin = userInfo.role === "ADMIN";

    const logout = () => {
        removeFromLocalStorage("user");
    }

    return (
        <nav className="navbar navbar-expand navbar-dark bg-dark">
            <Link to={"/"} className="navbar-brand" style={{marginLeft: '20px'}}>
                Communication platform
            </Link>
            <div className="navbar-nav mr-auto">
                {userInfo.isLoggedIn && (
                    <div className="navbar-nav ml-auto">
                        <li className="nav-item">
                            <Link to={"/"} className="nav-link">
                                Home
                            </Link>
                        </li>
                        <li className="nav-item">
                            <Link to={"/dashboard"} className="nav-link">
                                Dashboard
                            </Link>
                        </li>
                    </div>
                )}
                {isAdmin && (
                    <li className="nav-item">
                        <Link to={"/admin-panel"} className="nav-link">
                            Admin panel
                        </Link>
                    </li>
                )}
            </div>
            {userInfo.isLoggedIn ? (
                <div className="navbar-nav ml-auto">
                    <li className="nav-item">
                        <Link to={"/profile"} className="nav-link">
                            Profile
                        </Link>
                    </li>
                    <li className="nav-item">
                        <a href="/login" className="nav-link" onClick={logout}>
                            Sign Out
                        </a>
                    </li>
                </div>
            ) : (
                <div className="navbar-nav ml-auto">
                    <li className="nav-item">
                        <Link to={"/login"} className="nav-link">
                            Sign In
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to={"/register"} className="nav-link">
                            Sign Up
                        </Link>
                    </li>
                </div>
            )}
        </nav>
    )
}

export default NavBar
