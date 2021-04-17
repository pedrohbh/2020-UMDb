import React, { Component } from 'react'
import { Button, Icon , Table} from 'semantic-ui-react'
import { connect } from 'react-redux'

import AdminContainer from '../../components/AdminContainer'
import AdminInternalHeader from '../../components/AdminInternalHeader'
import { fetchActors, createActor } from '../../actions/actor'


class ListActors extends Component {

    componentDidMount() {
        this.props.fetchActors()
    }

    handleEditButtonClick = (e, id) => {
        this.props.history.push(`/admin/actor/edit/${id}`)
    }

    handleDeleteButtonClick = (e, id) => {
        this.props.history.push(`/admin/actor/delete/${id}`)
    }

    renderRow() {

        return this.props.actors.map(({id, name}) => {
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
                <AdminContainer activeItem="actor">
                    <AdminInternalHeader title="Atores" link="/admin/actor/add" />
                    <Table basic='very' celled collapsing style={{ width: '100%' }}>
                        <Table.Header>
                            <Table.Row>
                                <Table.HeaderCell width={14}>Nome</Table.HeaderCell>
                                <Table.HeaderCell width={2}>Ações</Table.HeaderCell>
                            </Table.Row>
                        </Table.Header>
                        <Table.Body>
                            { 
                                this.props.actors.length > 0 ? (
                                    this.renderRow()
                                ) : (
                                    <Table.Row>
                                        <Table.Cell>
                                            <div style={{margin: '10px 0'}}>Não há atores cadastrados.</div>
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
    return { actors: Object.values(state.actors) };
};

export default connect(
    mapStateToProps,
    { fetchActors, createActor }
)(ListActors);