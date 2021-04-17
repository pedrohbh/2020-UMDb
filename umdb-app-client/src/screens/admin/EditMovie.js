import React, { Component } from 'react';
import { Button, Dropdown, Icon, Form, Input, TextArea } from 'semantic-ui-react'
import { connect } from 'react-redux'

import AdminContainer from '../../components/AdminContainer';
import AdminInternalHeader from '../../components/AdminInternalHeader';
import { fetchDirectors } from '../../actions/director';
import { fetchGenres } from '../../actions/genre';
import { fetchActors } from '../../actions/actor';
import { editMovie, fetchMovie } from '../../actions/movie';


class CreateMovie extends Component {
    constructor(props) {
        super(props)
        this.state = {
            name: '',
            image: '',
            synopsis: '',
            year: '',
            director: '',
            actors: [],
            genres: []
        }
    }

    componentDidMount() {
        this.props.fetchMovie(this.props.match.params.id)
        this.props.fetchDirectors()
        this.props.fetchGenres()
        this.props.fetchActors()
    }

    onSubmit = (e) => {
        e.preventDefault()
        const formData = new FormData();
        const movie = {
            name: this.state.name,
            year: this.state.year,
            synopsis: this.state.synopsis,
        }
        formData.append('image', this.state.image)
        formData.append('movie', movie)
        const config = {
            headers: {
                'content-type': 'multipart/form-data'
            }
        }
        this.props.createMovie(formData, config)
    };

    render() {
        return (
            <AdminContainer activeItem="movie">
                <AdminInternalHeader title="Criar filme" link="" />
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
                    {/* <Form.Field>
                        <label>Diretor</label>
                        <Dropdown placeholder='Selecione o diretor' name="director" fluid selection search options={this.props.directos.map(({id, name}) => { return {key: id, text: name, value: id} })} value={this.state.director} onChange={(e) => this.setState({ director: e.target.value })} />
                    </Form.Field>
                    <Form.Field>
                        <label>Atores</label>
                        <Dropdown placeholder='Selecione os atores' name="actors" fluid selection multiple search options={this.props.actors.map(({id, name}) => { return {key: id, text: name, value: id} })} value={this.state.actors} onChange={(e) => this.setState({ actors: e.target.value })} />
                    </Form.Field>
                    <Form.Field>
                        <label>Generos</label>
                        <Dropdown placeholder='Selecione os gêneros' name="genres" fluid selection multiple search options={this.props.actors.map(({id, name}) => { return {key: id, text: name, value: id} })} value={this.state.genres} onChange={(e) => this.setState({ genres: e.target.value })} />
                    </Form.Field> */}
                    <Button icon type="submit" labelPosition='left'>
                        <Icon name='plus' />
                        Adicionar
                    </Button>
                </Form>
            </AdminContainer>
        );
    }

};

const mapStateToProps = (state, ownProps) => {
    return {
        movie: state.movies[ownProps.match.params.id],
        directors: Object.values(state.directors),
        actors: Object.values(state.actors),
        genres: Object.values(state.genres),

    };
};

export default connect(
    mapStateToProps,
    { fetchDirectors, fetchActors, fetchGenres, editMovie, fetchMovie }
)(CreateMovie);