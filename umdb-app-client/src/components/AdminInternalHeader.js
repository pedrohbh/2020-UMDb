import React from 'react';
import { Link } from 'react-router-dom';
import { Header } from 'semantic-ui-react';

const AdminInternalHeader = ({ title, link }) => {
    return (
        <div style={{ marginBottom: "2em", position: "relative" }}>
            <Header as='h2' className="centered">{title}</Header>
            {
                link ? (
                    <Link
                        to={ link }
                        className="ui labeled icon button tiny"
                        style={{ position: 'absolute', top: '0', right: '0' }}
                    >
                        <i className="plus icon"></i>
                        Adicionar
                    </Link>
                ) : null
            }
        </div>
    );
};

export default AdminInternalHeader;