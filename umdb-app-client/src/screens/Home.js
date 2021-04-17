import React, {Component} from 'react';
import { Grid, Header } from 'semantic-ui-react';

import HomeContainer from '../components/HomeContainer';
import MovieCard from '../components/MovieCard';
import { fetchMovies } from "../actions/movie";
import {connect} from "react-redux";


class Home extends Component{
    constructor(props) {
        super(props)

    }

    componentDidMount(){
        this.props.fetchMovies();
    }

    renderRow(){
        return this.props.movies.map((movie) =>{
            return(
                <Grid.Column width={4}>
                    <MovieCard
                        fluid
                        title={movie.name}
                        year={movie.year}
                        imageSource={`data:image/gif;Base64,${movie.image}`}
                        link={`/movie/${movie.id}`}
                        avgRate={movie.avgRate}
                        style={{ marginBottom: '15px' }}
                    />
                </Grid.Column>
            )
        })
    }

    render(){
        return (
        <>
            <Grid>
                <Grid.Row>
                    <Grid.Column width={16}>
                        <Header as='h2' textAlign="center">Filmes</Header>
                    </Grid.Column>
                </Grid.Row>
            </Grid>
            <HomeContainer>
                <Grid>
                    <Grid.Row>
                        {
                            this.props.movies.length > 0 ? (
                                this.renderRow()
                            ): (
                                <Grid.Column width={4}>
                                    <div>Não há filmes cadastrados</div>
                                </Grid.Column>
                            )
                        }
                    </Grid.Row>
                </Grid>
            </HomeContainer>
        </>
    );
    }

}

const mapStateToProps = state => {
    return { movies: Object.values(state.movies) };
};

export default connect(
    mapStateToProps,
    { fetchMovies }
)(Home);