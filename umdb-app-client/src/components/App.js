import React from 'react';
import { Router, Route } from 'react-router-dom';
import { Container } from 'semantic-ui-react';

import Home from '../screens/Home';
import SignIn from '../screens/SignIn';
import SignUp from '../screens/SignUp';
import SignOut from '../screens/SignOut';
import ForgotPassword from '../screens/ForgotPassword';
import AdminHome from '../screens/admin/AdminHome';

import Header from './Header';
import Footer from './Footer';
import history from '../history';

import CreateDefault from '../screens/admin/CreateDefault';
import CreateMovie from '../screens/admin/CreateMovie';
import ListDirectors from '../screens/admin/ListDirectors';
import ListGenres from '../screens/admin/ListGenres';
import ListActors from '../screens/admin/ListActors';
import ListMovies from '../screens/admin/ListMovies';
import MovieDetail from '../screens/MovieDetail';
import CreateReview from '../screens/CreateReview';
import EditDefault from '../screens/admin/EditDefault';
import EditMovie from '../screens/admin/EditMovie';

const App = () => {
    return (
        <Container>
            <Router history={history}>
                <div>
                    <Header />
                    <Route path="/" exact component={Home} />
                    <Route path="/signin" exact component={SignIn} />
                    <Route path="/signup" exact component={SignUp} />
                    <Route path="/signout" exact component={SignOut} />
                    <Route path="/signin/forgot" exact component={ForgotPassword} />
                    <Route path="/admin" exact component={AdminHome} />
                    <Route path="/admin/actors" exact component={ListActors} />
                    <Route
                        path="/admin/actor/add" exact
                        render={() => <CreateDefault headerTitle="Criar Ator" activeItem="actor" />}
                    />
                    <Route
                        path="/admin/actor/edit/:id" exact
                        render={props => <EditDefault {...props} headerTitle="Editar Ator" activeItem="actor" />}
                    />
                    <Route path="/admin/directors" exact component={ListDirectors} />
                    <Route
                        path="/admin/director/edit/:id" exact
                        render={props => <EditDefault {...props} headerTitle="Editar Diretor" activeItem="director" />}
                    />
                    <Route
                        path="/admin/directors/add" exact
                        render={() => <CreateDefault headerTitle="Criar Diretor" activeItem="director" />}
                    />
                    <Route path="/admin/genres" exact component={ListGenres} />
                    <Route
                        path="/admin/genres/add" exact
                        render={() => <CreateDefault headerTitle="Criar Gênero" activeItem="genre" />}
                    />
                    <Route
                        path="/admin/genre/edit/:id" exact
                        render={props => <EditDefault {...props} headerTitle="Editar Gênero" activeItem="genre" />}
                    />
                    <Route path="/admin/movies" exact component={ListMovies} />
                    <Route path="/admin/movies/add" exact component={CreateMovie} />
                    <Route path="/admin/movie/edit/:id" exact component={EditMovie} />
                    <Route path="/movie/:id" exact component={MovieDetail} />
                    <Route path="/movie/:id/review" exact component={CreateReview} />
                    <Footer />
                </div>
            </Router>
        </Container>
    );
}

export default App;