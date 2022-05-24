import {UserInfoContext} from "./UserInfoContext";
import React, {useEffect, useState} from "react";
import {getLocalStorage, setLocalStorage} from "./storage";

export function UserInfoProvider({children}: { children: any }) {

    const initialState = {
        userId: null,
        firstName: '',
        lastName: '',
        email: '',
        role: '',
        token: '',
        isLoggedIn: false
    }

    const [userInfo, setUserInfo] = useState(() => getLocalStorage("user", initialState));

    useEffect(() => {
        setLocalStorage("user", userInfo);
    }, [userInfo]);

    return (
        // @ts-ignore
        <UserInfoContext.Provider value={{ userInfo, setUserInfo}}>
            {children}
        </UserInfoContext.Provider>
    );
}