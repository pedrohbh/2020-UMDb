import React from 'react'

import AdminContainer from '../../components/AdminContainer'

const AdminHome = () => {
    return (
        <AdminContainer>
            <div style={{
                    textAlign: 'center',
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                    height: '100%'
                }}
            >
                Seja bem-vindo ao painel administrativo!
                <br /><br />
                Selecione um ação no menu ao lado.
            </div>
        </AdminContainer>
    );
};

export default AdminHome;