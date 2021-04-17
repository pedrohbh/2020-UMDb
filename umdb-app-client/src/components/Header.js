import React from 'react';
import { Link } from 'react-router-dom';
import { Menu } from 'semantic-ui-react'

import { isAuthenticated, isAdmin } from '../services/auth';

const Header = () => {
    return (
        <Menu secondary pointing>
            <Link to="/" className="item">UMDb</Link>
            <Menu.Menu position='right'>
                {
                    !isAuthenticated() ? (
                        <Link to="/signin" className="item">Login</Link>
                    ) : (
                        <Link to="/signout" className="item">Logout</Link>
                    )
                }
                { isAdmin() ? (<Link to="/admin" className="item">Página Administrativa</Link>) : null }
            </Menu.Menu>
        </Menu>
    );
}

export default Header;