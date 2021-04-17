import React from 'react';
import { Link } from 'react-router-dom';
import { Menu } from 'semantic-ui-react';


const AdminSidebar = props => {
    const getClassName = (item) => {
        return props.activeItem === item ? 'item active' : 'item'
    }

    return (
        <Menu vertical fluid tabular>
            <Link to="/admin/actors" className={getClassName('actor')}>
                Atores
            </Link>
            <Link to="/admin/directors" className={getClassName('director')}>
                Diretores
            </Link>
            <Link to="/admin/movies" className={getClassName('movie')}>
                Filmes
            </Link>
            <Link to="/admin/genres" className={getClassName('genre')}>
                Gêneros
            </Link>
        </Menu>
    );
}

export default AdminSidebar;