import React, { Component } from 'react';
import { Button, Dropdown, Icon, Form, Input, TextArea } from 'semantic-ui-react'
import { connect } from 'react-redux'
import api from '../../services/api'

import AdminContainer from '../../components/AdminContainer';
import AdminInternalHeader from '../../components/AdminInternalHeader';
import { editMovie, fetchMovie } from '../../actions/movie';


class EditMovie extends Component {
    constructor(props) {
        super(props)
        this.state = {
            name: '',
            image: '',
            synopsis: '',
            year: '',
            directorsList: [],
            actorsList: [],
            genresList: [],
            selectedDirectors: [],
            selectedActors: [],
            selectedGenres: [],
        }
    }

    setInicialValues = () => {
        this.setState({
            name: this.props.movie.name,
            year: this.props.movie.year,
            synopsis: this.props.movie.synopsis,
            selectedActors: this.props.movie.actors.map((actor) => actor.id),
            selectedDirectors: this.props.movie.directors.map((director) => director.id),
            selectedGenres: this.props.movie.genres.map((genre) => genre.id)
        })
    }

    async componentDidMount() {
        await this.props.fetchMovie(this.props.match.params.id)
        this.setInicialValues()

        api.get('/open/director')
        .then((response) => this.setState({directorsList: response.data.map(({id, name}) => { return {text: name, value: id} })}))
        .catch((error) => console.error(error))

        api.get('/open/actor')
        .then((response) => this.setState({actorsList: response.data.map(({id, name}) => { return {text: name, value: id} })}))
        .catch((error) => console.error(error))

        api.get('/open/genre')
        .then((response) => this.setState({genresList: response.data.map(({id, name}) => { return {text: name, value: id} })}))
        .catch((error) => console.error(error))
    }

    onSubmit = (e) => {
        e.preventDefault()
        const formData = new FormData();
        console.log(this.state)
        const movie = {
            name: this.state.name,
            year: this.state.year,
            synopsis: this.state.synopsis,
            actors: this.state.selectedActors.map((id) => { return { id } }),
            directors: this.state.selectedDirectors.map((id) => { return { id } }),
            genres: this.state.selectedGenres.map((id) => { return { id } })
        }
        // formData.append('image', this.state.image)
        formData.append('movie', JSON.stringify(movie))
        const config = {
            headers: {
                'content-type': 'multipart/form-data'
            }
        }
        this.props.editMovie(this.props.match.params.id, formData, config)
    };

    render() {
        return (
            <AdminContainer activeItem="movie">
                <AdminInternalHeader title="Editar filme" link="" />
                <Form method="POST" onSubmit={this.onSubmit}>
                    <Form.Field>
                        <label>Nome</label>
                        <Input placeholder='Nome' name="name" value={this.state.name} onChange={(e) => this.setState({ name: e.target.value })} />
                    </Form.Field>
                    <Form.Field>
                        <label>Imagem</label>
                        <Input type="file" name="image" accept=".jpg, .jpeg, .png" onChange={(e) => this.setState({image: e.target.files[0]})} />
                    </Form.Field>
                    <Form.Field>
                        <label>Sinopse</label>
                        <TextArea rows="6" placeholder='Sinopse' name="synopsis" value={this.state.synopsis} onChange={(e) => this.setState({ synopsis: e.target.value })} />
                    </Form.Field>
                    <Form.Field>
                        <label>Ano</label>
                        <Input placeholder='Ano' name="year" type="number" value={this.state.year} onChange={(e) => this.setState({ year: e.target.value })} />
                    </Form.Field>
                    <Form.Field>
                        <label>Diretores</label>
                        <Dropdown placeholder='Selecione o diretor' name="director" fluid selection multiple search options={this.state.directorsList} onChange={(e, data) => this.setState({ selectedDirectors: data.value })} value={this.state.selectedDirectors} />
                    </Form.Field>
                    <Form.Field>
                        <label>Atores</label>
                        <Dropdown placeholder='Selecione os atores' name="actors" fluid selection multiple search options={this.state.actorsList} onChange={(e, data) => this.setState({ selectedActors: data.value })} value={this.state.selectedActors} />
                    </Form.Field>
                    <Form.Field>
                        <label>Generos</label>
                        <Dropdown placeholder='Selecione os gêneros' name="genres" fluid selection multiple search options={this.state.genresList} onChange={(e, data) => this.setState({ selectedGenres: data.value })} value={this.state.selectedGenres} />
                    </Form.Field>
                    <Button icon type="submit" labelPosition='left'>
                        <Icon name='write' />
                        Editar
                    </Button>
                </Form>
            </AdminContainer>
        );
    }

};

const mapStateToProps = (state, ownProps) => {
    return { movie: state.movies[ownProps.match.params.id] };
};

export default connect(
    mapStateToProps,
    { editMovie, fetchMovie }
)(EditMovie);