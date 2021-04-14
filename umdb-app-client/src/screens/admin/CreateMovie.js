import React from 'react';

import AdminContainer from '../../components/AdminContainer';
import AdminInternalHeader from '../../components/AdminInternalHeader';

const CreateMovie = () => {
    return (
        <AdminContainer activeItem="movies">
            <AdminInternalHeader title="Criar filme" link="" />
            <form className="ui form">
                <div className="field">
                    <label>Nome</label>
                    <input type="text" name="nome" placeholder="Nome" />
                </div>
                <div className="field">
                    <label>Ano</label>
                    <input type="text" name="year" placeholder="Ano" />
                </div>
                <div className="field">
                    <label>Diretor</label>
                    <div className="ui fluid search selection dropdown">
                        <input type="hidden" name="director" />
                        <i className="dropdown icon"></i>
                        <div className="default text">Selecione o diretor</div>
                        <div className="menu">
                            <div className="item">Diretor 1</div>
                            <div className="item">Diretor 2</div>
                            <div className="item">Diretor 3</div>
                            <div className="item">Diretor 4</div>
                        </div>
                    </div>
                </div>
                <div className="field">
                    <label>Atores</label>
                    <div className="ui fluid search selection dropdown">
                        <input type="hidden" name="director" />
                        <i className="dropdown icon"></i>
                        <div className="default text">Selecione os atores</div>
                        <div className="menu">
                            <div className="item">Ator 1</div>
                            <div className="item">Ator 2</div>
                            <div className="item">Ator 3</div>
                            <div className="item">Ator 4</div>
                        </div>
                    </div>
                </div>
                <div className="field">
                    <label>Gêneros</label>
                    <div className="ui fluid search selection dropdown">
                        <input type="hidden" name="director" />
                        <i className="dropdown icon"></i>
                        <div className="default text">Selecione os gêneros</div>
                        <div className="menu">
                            <div className="item">Gênero 1</div>
                            <div className="item">Gênero 2</div>
                            <div className="item">Gênero 3</div>
                            <div className="item">Gênero 4</div>
                        </div>
                    </div>
                </div>
                <button className="ui labeled icon button" type="submit">
                    <i className="plus icon"></i>
                    Adicionar
                </button>
            </form>
        </AdminContainer>
    );
};

export default CreateMovie;