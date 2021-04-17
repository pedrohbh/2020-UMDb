import React, { Component } from 'react'
import { Button, Icon, Table } from 'semantic-ui-react'
import { connect } from 'react-redux'
import history from '../../history'

import AdminContainer from '../../components/AdminContainer'
import AdminInternalHeader from '../../components/AdminInternalHeader'
import { fetchMovies, deleteMovie } from '../../actions/movie'


class ListMovies extends Component {
    componentDidMount() {
        this.props.fetchMovies()
    }

    handleEditButtonClick = (e, id) => {
        history.push(`/admin/movie/edit/${id}`)
    }

    handleDeleteButtonClick = (e, id) => {
        this.props.deleteMovie(id)
    }

    renderRow() {
        return this.props.movies.map(({id, name}) => {
            return (
                <Table.Row key={id}>
                    <Table.Cell>
                        {name}
                    </Table.Cell>
                    <Table.Cell>
                        <Button icon size="mini" data-tooltip="Editar" data-position="top center" onClick={(e) => this.handleEditButtonClick(e, id)}>
                            <Icon name='edit' />
                        </Button>
                        <Button icon size="mini" data-tooltip="Deletar" data-position="top center" onClick={(e) => this.handleDeleteButtonClick(e, id)}>
                            <Icon name='trash alternate outline' />
                        </Button>
                    </Table.Cell>
                </Table.Row>
            );
        });
    }
    
    render() {
        return (
            <div>
                <AdminContainer activeItem="movie">
                    <AdminInternalHeader title="Filmes" link="/admin/movies/add" />
                    <Table basic='very' celled collapsing style={{ width: '100%' }}>
                        <Table.Header>
                            <Table.Row>
                                <Table.HeaderCell width={14}>Nome</Table.HeaderCell>
                                <Table.HeaderCell width={2}>Ações</Table.HeaderCell>
                            </Table.Row>
                        </Table.Header>
                        <Table.Body>
                            {this.renderRow()}
                        </Table.Body>
                    </Table>
                </AdminContainer>
            </div>
        );
    }
};

const mapStateToProps = state => {
    return { movies: Object.values(state.movies) };
};

export default connect(
    mapStateToProps,
    { fetchMovies, deleteMovie }
)(ListMovies);