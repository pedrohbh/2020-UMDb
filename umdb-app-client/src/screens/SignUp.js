import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { Button, Icon, Form, Input, Card, Grid } from 'semantic-ui-react';

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

    const isButtonDisabled = () => {
        return !passwordsMatch || !name || !email || !password1 || !password2
    }

    return (
        <div className="page-login">
            <Grid centered container>
                <Grid.Column width={8}>
                    {/* <div className="ui icon warning message">
                        <i className="lock icon"></i>
                        <div className="content">
                            <div className="header">
                                Oops, algo deu errado!
                            </div>
                            <p>Verifique seu usuário, senha e tente novamente!</p>
                        </div>
                    </div> */}
                    <Card fluid>
                        <Card.Content>
                            <Form method="POST" onSubmit={handleSignUp}>
                                <Form.Field>
                                    <label>Nome</label>
                                    <Input
                                        type="text"
                                        name="name"
                                        placeholder="Nome"
                                        onChange={e => setName(e.target.value)}
                                    />
                                        
                                </Form.Field>
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
                                        name="password1"
                                        placeholder="Senha"
                                        onChange={e => setPassword1(e.target.value)}
                                    />
                                        
                                </Form.Field>
                                <Form.Field>
                                    <label>Confirmar senha</label>
                                    <Input
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
                                            <div className="icon" style={{ marginTop: '5px' }}>
                                                <i className="ban icon"></i>
                                                Senhas não são iguais.
                                            </div>
                                        )
                                    }
                                </Form.Field>
                                <div style={{ display: 'flex', alignItems: 'center'}}>
                                    <Button
                                        disabled={isButtonDisabled()}
                                        className="cadastro"
                                        icon
                                        labelPosition='left'
                                        type="submit"
                                        style={{ marginRight: '10px' }}
                                    >
                                        <Icon name="unlock alternate"></Icon>
                                        Cadastrar
                                    </Button>
                                    <div className="link-cadastro">
                                        Já tem uma conta? <Link to='/signin'>Faça login</Link>
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

export default SignUp;