import React from 'react'

import AdminContainer from '../../components/AdminContainer'
import AdminInternalHeader from '../../components/AdminInternalHeader'


const ListDirectors = () => {
    return (
        <div>
            <AdminContainer activeItem="directors">
                <AdminInternalHeader title="Diretores" link="/admin/directors/add" />
                <table className="ui very basic collapsing celled table" style={{ width: '100%' }}>
                    <thead>
                        <tr>
                            <th className="fourteen wide">Nome</th>
                            <th className="two wide">Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                Diretor 1
                            </td>
                            <td>
                                <button class="ui icon button mini" data-tooltip="Editar" data-position="top center">
                                    <i class="edit icon"></i>
                                </button>
                                <button className="ui icon button mini" data-tooltip="Deletar" data-position="top center">
                                    <i class="trash alternate outline icon"></i>
                                </button>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Diretor 2
                            </td>
                            <td>
                                <button class="ui icon button mini" data-tooltip="Editar" data-position="top center">
                                    <i class="edit icon"></i>
                                </button>
                                <button className="ui icon button mini" data-tooltip="Deletar" data-position="top center">
                                    <i class="trash alternate outline icon"></i>
                                </button>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Diretor 3
                            </td>
                            <td>
                                <button class="ui icon button mini" data-tooltip="Editar" data-position="top center">
                                    <i class="edit icon"></i>
                                </button>
                                <button className="ui icon button mini" data-tooltip="Deletar" data-position="top center">
                                    <i class="trash alternate outline icon"></i>
                                </button>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Diretor 4
                            </td>
                            <td>
                                <button class="ui icon button mini" data-tooltip="Editar" data-position="top center">
                                    <i class="edit icon"></i>
                                </button>
                                <button className="ui icon button mini" data-tooltip="Deletar" data-position="top center">
                                    <i class="trash alternate outline icon"></i>
                                </button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </AdminContainer>
        </div>
    );
};

export default ListDirectors;