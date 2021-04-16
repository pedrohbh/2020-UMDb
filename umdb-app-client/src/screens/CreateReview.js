import React from 'react';
import { useParams } from 'react-router-dom';
import { Segment, Grid, Header, Image, TextArea, Form, Rating, Button, Icon } from 'semantic-ui-react';

import rockyImage from '../assets/rocky.jpeg'
import MovieInfo from '../components/MovieInfo';

const CreateReview = () => {
    const { id } = useParams()
    return (
        <Segment>
            <Grid>
                <Grid.Row>
                    <Grid.Column width={5}>
                        <Image wrapped ui={false} src={rockyImage} />
                    </Grid.Column>
                    <Grid.Column width={11}>
                        <MovieInfo
                            title="Rocky Balboa"
                            rating={4}
                            year="2016"
                            director="Director 1"
                            actors="Ator 1, Ator 2, Ator 3, Ator 4"
                            genres="Luta, Comédia, Ação"
                            synopsys="Rocky Balboa, um pequeno boxeador da classe trabalhadora da Filadélfia, é arbitrariamente escolhido para lutar contra o campeão dos pesos pesados, Apollo Creed, quando o adversário do invicto lutador agendado para a luta é ferido. Durante o treinamento com o mal-humorado Mickey Goldmill, Rocky timidamente começa um relacionamento com Adrian, a invisível irmã de Paulie, seu amigo empacotador de carne."
                        />
                        <div>
                            <Header as="h3">Create review</Header>
                            <Form method="POST">
                                <Form.Field>
                                    <Rating maxRating={5} clearable />
                                </Form.Field>
                                <Form.Field>
                                    <TextArea placeholder='Texto da avaliação' name="text" />
                                </Form.Field>
                                <Button icon type="submit" labelPosition='left'>
                                    <Icon name='write' />
                                    Enviar
                                </Button>
                            </Form>
                        </div>
                    </Grid.Column>
                </Grid.Row>
            </Grid>
        </Segment>
    );
};

export default CreateReview;