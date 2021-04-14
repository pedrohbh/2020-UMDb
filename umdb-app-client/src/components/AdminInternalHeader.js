import React from 'react'
import { Link } from 'react-router-dom'

const AdminInternalHeader = ({ title, link }) => {
    return (
        <div style={{ marginBottom: "2em", position: "relative" }}>
            <h2 className="ui header centered">{title}</h2>
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