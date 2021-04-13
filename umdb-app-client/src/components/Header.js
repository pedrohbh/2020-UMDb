import React from 'react';
import { Link } from 'react-router-dom';

import { isAuthenticated, isAdmin } from '../services/auth';

const Header = () => {
    return (
        <header className="ui secondary pointing menu">
            <Link to="/" className="item">UMDb</Link>
            <div className="right menu">
                {
                    !isAuthenticated() ? (
                        <Link to="/signin" className="item">Login</Link>
                    ) : (
                        <Link to="/signout" className="item">Logout</Link>
                    )
                }
                { isAdmin() ? (<Link to="/admin" className="item">Página Administrativa</Link>) : null }
            </div>
        </header>
    );
}

export default Header;