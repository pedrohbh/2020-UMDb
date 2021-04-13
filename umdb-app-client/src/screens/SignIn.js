import React, { useState } from 'react';
import { Link } from 'react-router-dom';

const SignIn = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = (e) => {
        e.preventDefault();
        console.log(`Submitting Form ${JSON.stringify({ email, password })}`)
    }

    return (
        <div className="page-login">
            <div className="ui centered grid container">
                <div className="eight wide column">
                    {/* <div className="ui icon warning message">
                        <i className="lock icon"></i>
                        <div className="content">
                            <div className="header">
                                Oops, algo deu errado!
                            </div>
                            <p>Verifique seu usuário, senha e tente novamente!</p>
                        </div>
                    </div> */}
                    <div className="ui fluid card">
                        <div className="content">
                            <form className="ui form" method="POST" onSubmit={handleLogin}>
                                <div className="field">
                                    <label>E-mail</label>
                                    <input
                                        type="email"
                                        name="email"
                                        placeholder="E-mail"
                                        onChange={e => setEmail(e.target.value)}
                                    />
                                </div>
                                <div className="field">
                                    <label>Senha</label>
                                    <input
                                        type="password"
                                        name="password"
                                        placeholder="Senha"
                                        onChange={e => setPassword(e.target.value)}
                                    />
                                </div>
                                <div style={{ display: 'flex', alignItems: 'center'}}>
                                    <button className="ui labeled icon button" type="submit" style={{ marginRight: '10px' }}>
                                        <i className="unlock alternate icon"></i>
                                        Login
                                    </button>
                                    <div className="link-cadastro">
                                        Não tem uma conta? <Link to='/signup'>Cadastre-se</Link>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default SignIn;