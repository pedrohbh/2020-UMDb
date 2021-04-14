import React from 'react'

import AdminContainer from '../../components/AdminContainer'
import AdminInternalHeader from '../../components/AdminInternalHeader'


const ListGenres = () => {
    return (
        <div>
            <AdminContainer activeItem="genres">
                <AdminInternalHeader title="Generos" link="/admin/genres/add" />
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
                                Gênero 1
                            </td>
                            <td>
                                <button className="ui icon button mini" data-tooltip="Editar" data-position="top center">
                                    <i className="edit icon"></i>
                                </button>
                                <button className="ui icon button mini" data-tooltip="Deletar" data-position="top center">
                                    <i className="trash alternate outline icon"></i>
                                </button>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Gênero 2
                            </td>
                            <td>
                                <button className="ui icon button mini" data-tooltip="Editar" data-position="top center">
                                    <i className="edit icon"></i>
                                </button>
                                <button className="ui icon button mini" data-tooltip="Deletar" data-position="top center">
                                    <i className="trash alternate outline icon"></i>
                                </button>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Gênero 3
                            </td>
                            <td>
                                <button className="ui icon button mini" data-tooltip="Editar" data-position="top center">
                                    <i className="edit icon"></i>
                                </button>
                                <button className="ui icon button mini" data-tooltip="Deletar" data-position="top center">
                                    <i className="trash alternate outline icon"></i>
                                </button>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Gênero 4
                            </td>
                            <td>
                                <button className="ui icon button mini" data-tooltip="Editar" data-position="top center">
                                    <i className="edit icon"></i>
                                </button>
                                <button className="ui icon button mini" data-tooltip="Deletar" data-position="top center">
                                    <i className="trash alternate outline icon"></i>
                                </button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </AdminContainer>
        </div>
    );
};

export default ListGenres;