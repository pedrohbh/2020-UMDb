import { useEffect } from 'react';
import history from '../history'

import { isAuthenticated, logout } from '../services/auth';

const SignUp = () => {
    useEffect(() => {
        if (isAuthenticated()) {
            logout()
            history.push('/')
        }
    }, [])

    return <div style={{ textAlign: 'center' }}>Logging Out...</div>
};

export default SignUp;