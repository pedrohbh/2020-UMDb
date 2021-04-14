import React from 'react'

import AdminSidebar from '../components/AdminSidebar'


const AdminContainer = props => {
    return (
        <div className="ui grid">
            <div className="four wide column">
                <AdminSidebar activeItem={props.activeItem} />
            </div>
            <div className="twelve wide stretched column">
                <div className="ui segment">
                    { props.children }
                </div>
            </div>
        </div>
    );
};

export default AdminContainer;