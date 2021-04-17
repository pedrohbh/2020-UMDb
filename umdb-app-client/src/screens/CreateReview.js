import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Segment, Grid, Header, Image, TextArea, Form, Rating, Button, Icon } from 'semantic-ui-react';
import { connect } from 'react-redux'

import MovieInfo from '../components/MovieInfo';
import { fetchMovie } from '../actions/movie';
import { createReview } from '../actions/review';

class CreateReview extends Component {
    constructor (props) {
        super(props);
        this.state = {
            image: '',
            directors: '',
            actors: '',
            genres: '',
            name: '',
            year: '',
            rating: '',
            synopsis: ''
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
            rating: this.props.movie.avgRate,
            synopsis: this.props.movie.synopsis,
            commentary: '',
            reviewRating: 0
        })
    }

    handleSubmit = () => {
        const data = {
            rating: this.state.reviewRating,
            commentary: this.state.commentary
        }
        this.props.createReview(data, this.props.match.params.id)
    }

    render () {
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
                                    <Header as="h3">Escrever avaliação</Header>
                                    <Link
                                        to={`/movie/${this.props.match.params.id}/`}
                                        className="ui labeled icon button mini"
                                        style={{ position: 'absolute', top: '0', right: '0' }}
                                    >
                                        <i className="cancel icon"></i>
                                        Cancelar
                                    </Link>
                                </div>
                                
                                <Form method="POST" onSubmit={this.handleSubmit}>
                                    <Form.Field>
                                        <Rating maxRating={5} clearable value={this.state.rating} onRate={(e, data) => this.setState({ reviewRating: data.rating })} />
                                    </Form.Field>
                                    <Form.Field>
                                        <TextArea placeholder='Texto da avaliação' name="text" value={this.state.commentary} onChange={(e) => this.setState({commentary: e.target.value})}/>
                                    </Form.Field>
                                    <Button icon type="submit" labelPosition='left'>
                                        <Icon name='write' />
                                        Enviar
                                    </Button>
                                </Form>
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
    { fetchMovie, createReview }
)(CreateReview);