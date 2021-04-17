import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { Button, Icon, Form, Input, Card, Grid } from 'semantic-ui-react';

import { login } from '../services/auth'

const SignIn = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(false)

    const handleLogin = (e) => {
        e.preventDefault();

        const logged = login(email, password)
        if (!logged) {
            setError(true)
        }
    }

    return (
        <div className="page-login">
            <Grid centered container>
                <Grid.Column width={8}>
                    { error ? (
                        <div className="ui icon warning message">
                            <i className="lock icon"></i>
                            <div className="content">
                                <div className="header">
                                    Oops, algo deu errado!
                                </div>
                                <p>Verifique seu usuário, senha e tente novamente!</p>
                            </div>
                        </div>
                    ) : null}
                    <Card fluid>
                        <Card.Content>
                            <Form method="POST" onSubmit={handleLogin}>
                                <Form.Field>
                                    <label>E-mail</label>
                                    <Input
                                        type="email"
                                        name="email"
                                        placeholder="E-mail"
                                        onChange={e => setEmail(e.target.value)}
                                    />
                                </Form.Field>
                                <Form.Field>
                                    <label>Senha</label>
                                    <Input
                                        type="password"
                                        name="password"
                                        placeholder="Senha"
                                        onChange={e => setPassword(e.target.value)}
                                    />
                                </Form.Field>
                                <div style={{ display: 'flex', alignItems: 'center'}}>
                                    <Button icon labelPosition='left' type="submit" style={{ marginRight: '10px' }}>
                                        <Icon name="unlock alternate" />
                                        Login
                                    </Button>
                                    <div className="link-cadastro">
                                        Não tem uma conta? <Link to='/signup'>Cadastre-se</Link>
                                    </div>
                                </div>
                            </Form>
                        </Card.Content>
                    </Card>
                </Grid.Column>
            </Grid>
        </div>
    );
};

export default SignIn;