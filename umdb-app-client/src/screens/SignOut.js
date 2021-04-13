import { useEffect } from 'react';

import { isAuthenticated, logout } from '../services/auth';

const SignUp = () => {
    useEffect(() => {
        if (isAuthenticated()) {
            logout()
        }
    }, [])

    return <div style={{ textAlign: 'center' }}>Logging Out...</div>
};

export default SignUp;