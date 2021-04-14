import React from 'react';

import AdminContainer from '../../components/AdminContainer'
import AdminInternalHeader from '../../components/AdminInternalHeader'

const CreateDefault = ({ headerTitle, activeItem }) => {
    return (
        <AdminContainer activeItem={activeItem}>
            <AdminInternalHeader title={headerTitle} link="" />
            <form className="ui form">
                <div className="field">
                    <label>Nome</label>
                    <input type="text" name="nome" placeholder="Nome" />
                </div>
                <button className="ui labeled icon button" type="submit">
                    <i className="plus icon"></i>
                    Adicionar
                </button>
            </form>
        </AdminContainer>
    );
};

export default CreateDefault;