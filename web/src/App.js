import * as React from 'react';
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import DashboardPage from "./pages/DashboardPage";
import LoginPage from "./pages/LoginPage";
import {UserInfoProvider} from "./utils/UserInfoProvider";
import NavBar from "./components/NavBar";
import RegisterPage from "./pages/RegisterPage";
import ProfilePage from "./pages/ProfilePage";
import AdminPanelPage from "./pages/AdminPanelPage";
import HomePage from "./pages/HomePage";

function App() {

    return (
        <UserInfoProvider>
            <Router>
                <NavBar />
                    <Routes >
                          <Route exact path="/" element={<HomePage />} />
                          <Route exact path="/dashboard" element={<DashboardPage />} />
                          <Route exact path="/profile" element={<ProfilePage />} />
                          <Route exact path="/admin-panel" element={<AdminPanelPage />} />
                          <Route exact path="/login" element={<LoginPage />} />
                          <Route exact path="/register" element={<RegisterPage />} />
                    </Routes >
          </Router>
      </UserInfoProvider>
    );
}

export default App;
