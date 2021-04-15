import React from 'react'
import { Button, Icon, Table } from 'semantic-ui-react'

import AdminContainer from '../../components/AdminContainer'
import AdminInternalHeader from '../../components/AdminInternalHeader'


const ListDirectors = () => {
    return (
        <div>
            <AdminContainer activeItem="directors">
                <AdminInternalHeader title="Diretores" link="/admin/directors/add" />
                <Table basic='very' celled collapsing style={{ width: '100%' }}>
                    <Table.Header>
                        <Table.Row>
                            <Table.HeaderCell width={14}>Nome</Table.HeaderCell>
                            <Table.HeaderCell width={2}>Ações</Table.HeaderCell>
                        </Table.Row>
                    </Table.Header>
                    <Table.Body>
                        <Table.Row>
                            <Table.Cell>
                                Diretor 1
                            </Table.Cell>
                            <Table.Cell>
                                <Button icon size="mini" data-tooltip="Editar" data-position="top center">
                                    <Icon name='edit' />
                                </Button>
                                <Button icon size="mini" data-tooltip="Deletar" data-position="top center">
                                    <Icon name='trash alternate outline' />
                                </Button>
                            </Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>
                                Diretor 2
                            </Table.Cell>
                            <Table.Cell>
                                <Button icon size="mini" data-tooltip="Editar" data-position="top center">
                                    <Icon name='edit' />
                                </Button>
                                <Button icon size="mini" data-tooltip="Deletar" data-position="top center">
                                    <Icon name='trash alternate outline' />
                                </Button>
                            </Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>
                                Diretor 3
                            </Table.Cell>
                            <Table.Cell>
                                <Button icon size="mini" data-tooltip="Editar" data-position="top center">
                                    <Icon name='edit' />
                                </Button>
                                <Button icon size="mini" data-tooltip="Deletar" data-position="top center">
                                    <Icon name='trash alternate outline' />
                                </Button>
                            </Table.Cell>
                        </Table.Row>
                        <Table.Row>
                            <Table.Cell>
                                Diretor 4
                            </Table.Cell>
                            <Table.Cell>
                                <Button icon size="mini" data-tooltip="Editar" data-position="top center">
                                    <Icon name='edit' />
                                </Button>
                                <Button icon size="mini" data-tooltip="Deletar" data-position="top center">
                                    <Icon name='trash alternate outline' />
                                </Button>
                            </Table.Cell>
                        </Table.Row>
                    </Table.Body>
                </Table>
            </AdminContainer>
        </div>
    );
};

export default ListDirectors;