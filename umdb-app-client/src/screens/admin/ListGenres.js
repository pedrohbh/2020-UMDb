import React, { Component } from 'react'
import { Button, Icon, Table } from 'semantic-ui-react'
import { connect } from 'react-redux'
import history from '../../history'

import AdminContainer from '../../components/AdminContainer'
import AdminInternalHeader from '../../components/AdminInternalHeader'
import { fetchGenres, deleteGenre } from '../../actions/genre'


class ListGenres extends Component {

    componentDidMount() {
        this.props.fetchGenres()
    }

    handleEditButtonClick = (e, id) => {
        history.push(`/admin/actor/edit/${id}`)
    }

    handleDeleteButtonClick = (e, id) => {
        this.props.deleteGenre(id)
    }

    renderRow() {
        return this.props.genres.map(({id, name}) => {
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

    render () {
        return (
            <div>
                <AdminContainer activeItem="genre">
                    <AdminInternalHeader title="Generos" link="/admin/genres/add" />
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
    return { genres: Object.values(state.genres) };
};

export default connect(
    mapStateToProps,
    { fetchGenres, deleteGenre }
)(ListGenres);