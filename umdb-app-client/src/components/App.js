import React from 'react';
import { BrowserRouter, Route } from 'react-router-dom';

import Home from '../screens/Home';
import SignIn from '../screens/SignIn';
import SignUp from '../screens/SignUp';
import SignOut from '../screens/SignOut';
import ForgotPassword from '../screens/ForgotPassword';
import AdminHome from '../screens/admin/AdminHome';

import Header from './Header';
import Footer from './Footer';

const App = () => {
    return (
        <div className="ui container">
            <BrowserRouter>
                <div>
                    <Header />
                    <Route path="/" exact component={Home} />
                    <Route path="/signin" exact component={SignIn} />
                    <Route path="/signup" exact component={SignUp} />
                    <Route path="/signout" exact component={SignOut} />
                    <Route path="/signin/forgot" exact component={ForgotPassword} />
                    <Route path="/admin" exact component={AdminHome} />
                    <Footer />
                </div>
            </BrowserRouter>
        </div>
    );
}

export default App;