import React from 'react';
import { Button, Dropdown, Icon, Form, Input} from 'semantic-ui-react'

import AdminContainer from '../../components/AdminContainer';
import AdminInternalHeader from '../../components/AdminInternalHeader';


const CreateMovie = () => {
    const directors = [
        { key: 'director1', text: 'Diretor 1', value: 'director1' },
        { key: 'director2', text: 'Diretor 2', value: 'director2' },
        { key: 'director3', text: 'Diretor 3', value: 'director3' },
        { key: 'director4', text: 'Diretor 4', value: 'director4' }
    ]
    const actors = [
        { key: 'actor1', text: 'Ator 1', value: 'actor1' },
        { key: 'actor2', text: 'Ator 2', value: 'actor2' },
        { key: 'actor3', text: 'Ator 3', value: 'actor3' },
        { key: 'actor4', text: 'Ator 4', value: 'actor4' }
    ]
    const genres = [
        { key: 'genre1', text: 'Genero 1', value: 'genre1' },
        { key: 'genre2', text: 'Genero 2', value: 'genre2' },
        { key: 'genre3', text: 'Genero 3', value: 'genre3' },
        { key: 'genre4', text: 'Genero 4', value: 'genre4' }
    ]
    return (
        <AdminContainer activeItem="movies">
            <AdminInternalHeader title="Criar filme" link="" />
            <Form>
                <Form.Field>
                    <label>Nome</label>
                    <Input placeholder='Nome' name="name" />
                </Form.Field>
                <Form.Field>
                    <label>Ano</label>
                    <Input placeholder='Ano' name="year" type="number" />
                </Form.Field>
                <Form.Field>
                    <label>Diretor</label>
                    <Dropdown placeholder='Selecione o diretor' name="director" fluid selection search options={directors} />
                </Form.Field>
                <Form.Field>
                    <label>Atores</label>
                    <Dropdown placeholder='Selecione os atores' name="actors" fluid selection multiple search options={actors} />
                </Form.Field>
                <Form.Field>
                    <label>Generos</label>
                    <Dropdown placeholder='Selecione os gêneros' name="genres" fluid selection multiple search options={genres} />
                </Form.Field>
                <Button icon type="submit" labelPosition='left'>
                    <Icon name='plus' />
                    Adicionar
                </Button>
            </Form>
        </AdminContainer>
    );
};

export default CreateMovie;