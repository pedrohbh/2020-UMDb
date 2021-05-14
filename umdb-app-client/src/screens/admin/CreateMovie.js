import React, { Component } from 'react';
import { Button, Dropdown, Icon, Form, Input, TextArea } from 'semantic-ui-react'
import { connect } from 'react-redux'

import AdminContainer from '../../components/AdminContainer';
import AdminInternalHeader from '../../components/AdminInternalHeader';
import { createMovie } from '../../actions/movie';
import api from '../../services/api';


class CreateMovie extends Component {
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
            isFetching: false
        }
    }

    componentDidMount() {

        api.get('/open/director')
        .then((response) => this.setState({directorsList: response.data.map(({id, name}) => { return {key: id, text: name, value: id} })}))
        .catch((error) => console.error(error))

        api.get('/open/actor')
        .then((response) => this.setState({actorsList: response.data.map(({id, name}) => { return {key: id, text: name, value: id} })}))
        .catch((error) => console.error(error))

        api.get('/open/genre')
        .then((response) => this.setState({genresList: response.data.map(({id, name}) => { return {key: id, text: name, value: id} })}))
        .catch((error) => console.error(error))
    }

    onSubmit = (e) => {
        e.preventDefault()
        const formData = new FormData();
        const movie = {
            name: this.state.name,
            year: this.state.year,
            synopsis: this.state.synopsis,
            actors: this.state.selectedActors.map((id) => { return { id } }),
            directors: this.state.selectedDirectors.map((id) => { return { id } }),
            genres: this.state.selectedGenres.map((id) => { return { id } })
        }
        formData.append('image', this.state.image)
        formData.append('movie', JSON.stringify(movie))
        const config = {
            headers: {
                'content-type': 'multipart/form-data'
            }
        }
        this.props.createMovie(formData, config)
    };

    tryToFetchMovie = () => {
        this.setState({isFetching: true})
        api.get(`/open/movie/suggestion?name=${this.state.name}`)
        .then(({ data }) => {
            if (data.directors) {
                api.get('/open/director')
                .then((response) => this.setState({directorsList: response.data.map(({id, name}) => { return {key: id, text: name, value: id} })}))
                .catch((error) => console.error(error))
                this.setState({ selectedDirectors: data.directors.map((director) => director.id) })
            }
            this.setState({
                synopsis: data.synopsis,
                year: data.year
            })
        })
        .catch((error) => console.error(error))
        this.setState({isFetching: false})
    }

    render() {
        return (
            <AdminContainer activeItem="movie">
                <AdminInternalHeader title="Criar filme" link="" />
                <Form method="POST" onSubmit={this.onSubmit}>
                    <fieldset disabled={this.state.isFetching} style={{border: 0, padding: 0}}>
                        <Form.Field>
                            <label>Nome</label>
                            <Input onBlur={this.tryToFetchMovie} placeholder='Nome' name="name" value={this.state.name} onChange={(e) => this.setState({ name: e.target.value })} />
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
                            <Dropdown placeholder='Selecione os atores' name="actors" fluid selection multiple search options={this.state.actorsList} onChange={(e, data) => this.setState({ selectedActors: data.value })} />
                        </Form.Field>
                        <Form.Field>
                            <label>Generos</label>
                            <Dropdown placeholder='Selecione os gêneros' name="genres" fluid selection multiple search options={this.state.genresList} onChange={(e, data) => this.setState({ selectedGenres: data.value })} />
                        </Form.Field>
                        <Button icon type="submit" labelPosition='left'>
                            <Icon name='plus' />
                            Adicionar
                        </Button>
                    </fieldset>
                </Form>
            </AdminContainer>
        );
    }
};

export default connect(
    null,
    { createMovie }
)(CreateMovie);