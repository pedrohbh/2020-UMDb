import React, { Component } from 'react'
import { Button, Icon, Table } from 'semantic-ui-react'
import { connect } from 'react-redux'
import history from '../../history'

import AdminContainer from '../../components/AdminContainer'
import AdminInternalHeader from '../../components/AdminInternalHeader'
import { fetchDirectors, deleteDirector } from '../../actions/director'


class ListDirectors extends Component {
    componentDidMount() {
        this.props.fetchDirectors()
    }

    handleEditButtonClick = (e, id) => {
        history.push(`/admin/director/edit/${id}`)
    }

    handleDeleteButtonClick = (e, id) => {
        this.props.deleteDirector(id)
    }

    renderRow() {
        return this.props.directors.map(({id, name}) => {
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
                <AdminContainer activeItem="director">
                    <AdminInternalHeader title="Diretores" link="/admin/directors/add" />
                    <Table basic='very' celled collapsing style={{ width: '100%' }}>
                        <Table.Header>
                            <Table.Row>
                                <Table.HeaderCell width={14}>Nome</Table.HeaderCell>
                                <Table.HeaderCell width={2}>Ações</Table.HeaderCell>
                            </Table.Row>
                        </Table.Header>
                        <Table.Body>
                        { 
                            this.props.directors.length > 0 ? (
                                this.renderRow()
                            ) : (
                                <Table.Row>
                                    <Table.Cell>
                                        <div style={{margin: '10px 0'}}>Não há diretores cadastrados.</div>
                                    </Table.Cell>
                                </Table.Row>
                            )
                        }
                        </Table.Body>
                    </Table>
                </AdminContainer>
            </div>
        );
    }
};

const mapStateToProps = state => {
    return { directors: Object.values(state.directors) };
};

export default connect(
    mapStateToProps,
    { fetchDirectors, deleteDirector }
)(ListDirectors);