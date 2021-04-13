import React, { useState } from 'react';
import { Link } from 'react-router-dom';

const SignUp = () => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password1, setPassword1] = useState('');
    const [password2, setPassword2] = useState('');
    const [passwordsMatch, setPasswordsMatch] = useState(true);

    const handleSignUp = (e) => {
        e.preventDefault();
        console.log(`Submitting Form ${JSON.stringify({ name, email, password1, password2 })}`)
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
                            <form className="ui form" method="POST" onSubmit={handleSignUp}>
                                <div className="field">
                                    <label>Nome</label>
                                    <input
                                        type="text"
                                        name="name"
                                        placeholder="Nome"
                                        onChange={e => setName(e.target.value)}
                                    />
                                        
                                </div>
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
                                        name="password1"
                                        placeholder="Senha"
                                        onChange={e => setPassword1(e.target.value)}
                                    />
                                        
                                </div>
                                <div className="field">
                                    <label>Confirmar senha</label>
                                    <input
                                        type="password"
                                        name="password2"
                                        placeholder="Confirmar senha"
                                        onChange={e => {
                                            setPassword2(e.target.value)
                                            setPasswordsMatch(password1 === e.target.value)
                                        }}
                                    />
                                    {
                                        passwordsMatch ? (
                                            null
                                        ) : (
                                            <div className="icon">
                                                <i className="ban icon"></i>
                                                Senhas não são iguais.
                                            </div>
                                        )
                                    }
                                </div>
                                <div style={{ display: 'flex', alignItems: 'center'}}>
                                    <button disabled={password1 !== password2} className="ui labeled icon button" type="submit" style={{ marginRight: '10px' }}>
                                        <i className="unlock alternate icon"></i>
                                        Cadastrar
                                    </button>
                                    <div className="link-cadastro">
                                        Já tem uma conta? <Link to='/signin'>Faça login</Link>
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

export default SignUp;