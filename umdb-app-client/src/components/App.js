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

import CreateActor from '../screens/admin/CreateActor';
import CreateDirector from '../screens/admin/CreateDirector';
import CreateGenre from '../screens/admin/CreateGenre';
import CreateMovie from '../screens/admin/CreateMovie';
import ListDirectors from '../screens/admin/ListDirectors';
import ListGenres from '../screens/admin/ListGenres';
import ListActors from '../screens/admin/ListActors';
import ListMovies from '../screens/admin/ListMovies';

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
                    <Route path="/admin/actors" exact component={ListActors} />
                    <Route path="/admin/actors/add" exact component={CreateActor} />
                    <Route path="/admin/directors" exact component={ListDirectors} />
                    <Route path="/admin/directors/add" exact component={CreateDirector} />
                    <Route path="/admin/genres" exact component={ListGenres} />
                    <Route path="/admin/genres/add" exact component={CreateGenre} />
                    <Route path="/admin/movies" exact component={ListMovies} />
                    <Route path="/admin/movies/add" exact component={CreateMovie} />
                    <Footer />
                </div>
            </BrowserRouter>
        </div>
    );
}

export default App;