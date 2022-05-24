import {createContext} from 'react';

export const UserInfoContext = createContext({
    userId: null,
    firstName: '',
    lastName: '',
    email: '',
    role: '',
    token: '',
    isLoggedIn: false
});