import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Segment, Grid, Header, Image } from 'semantic-ui-react';
import { connect } from 'react-redux'

import MovieInfo from '../components/MovieInfo';
import SingleReview from '../components/SingleReview';
import { fetchMovie } from '../actions/movie';

class MovieDetail extends Component {
    constructor (props) {
        super(props);
        this.state = {
            image: '',
            directors: '',
            actors: '',
            genres: '',
            name: '',
            year: '',
            avgRate: 0,
            synopsis: '',
            reviews: []
        }
    }

    async componentDidMount() {
        await this.props.fetchMovie(this.props.match.params.id)
        this.setState({
            directors: this.props.movie.directors.map((director) => director.name).join(', '),
            actors: this.props.movie.actors.map((actor) => actor.name).join(', '),
            genres: this.props.movie.genres.map((genre) => genre.name).join(', '),
            image: this.props.movie.image,
            name: this.props.movie.name,
            year: this.props.movie.year,
            avgRate: this.props.movie.avgRate,
            synopsis: this.props.movie.synopsis,
            reviews: this.props.movie.reviews
        })
    }

    renderReviews = () => {
        return this.state.reviews.map((review) => {
            return (<SingleReview
                name={review.user.name}
                rating={review.rate}
                text={review.commentary}
                key={review.id}
            />)
        })
    }

    render() {
        return (
            <Segment>
                <Grid>
                    <Grid.Row>
                        <Grid.Column width={5}>
                            <Image wrapped ui={false} src={`data:image/gif;Base64,${this.state.image}`} />
                        </Grid.Column>
                        <Grid.Column width={11}>
                            <MovieInfo
                                title={this.state.name}
                                rating={this.state.avgRate}
                                year={this.state.year}
                                director={this.state.directors}
                                actors={this.state.actors}
                                genres={this.state.genres}
                                synopsis={this.state.synopsis}
                            />
                            <div>
                                <div style={{ position: 'relative' }}>
                                    <Header as="h3">Avaliações</Header>
                                    <Link
                                        to={`/movie/${this.props.match.params.id}/review`}
                                        className="ui labeled icon button mini"
                                        style={{ position: 'absolute', top: '0', right: '0' }}
                                    >
                                        <i className="plus icon"></i>
                                        Adicionar
                                    </Link>
                                </div>
                                {
                                    this.props.movie?.reviews?.length > 0 ? (
                                        this.renderReviews()
                                    ) : (
                                        <div>Nenhuma avaliação até o momento.</div>
                                    )
                                }
                            </div>
                        </Grid.Column>
                    </Grid.Row>
                </Grid>
            </Segment>
        );
    }
};

const mapStateToProps = (state, ownProps) => {
    return { movie: state.movies[ownProps.match.params.id] };
};

export default connect(
    mapStateToProps,
    { fetchMovie }
)(MovieDetail);