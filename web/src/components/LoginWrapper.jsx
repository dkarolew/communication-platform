import React from "react";
import { useNavigate } from "react-router-dom";


const LoginWrapper = ({isLoggedIn, children}) => {

    const navigate = useNavigate();

    return (
        <>
            {isLoggedIn ? (
                {children}
            ) : (
                navigate('/login')
            )}
        </>
    )
}

export default LoginWrapper