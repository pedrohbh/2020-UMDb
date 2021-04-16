import React from 'react';
import { useParams } from 'react-router-dom';
import { Segment, Grid, Header, Image } from 'semantic-ui-react';

import rockyImage from '../assets/rocky.jpeg'

const MovieDetail = () => {
    let { id } = useParams();

    return (
        <Segment>
            <Grid>
                <Grid.Row>
                    <Grid.Column width={6}>
                        <Image wrapped ui={false} src={rockyImage} />
                    </Grid.Column>
                    <Grid.Column width={10}>
                        <Header as="h3">Rocky Balboa</Header>
                        <div style={{ display: 'flex', marginBottom: '5px'}}>
                            <label style={{ fontWeight: 'bold', marginRight: '2px' }}>Ano:</label>
                            <p>2016</p>
                        </div>
                        <div style={{ display: 'flex', marginBottom: '5px'}}>
                            <label style={{ fontWeight: 'bold', marginRight: '2px' }}>Diretor:</label>
                            <p>Diretor 1</p>
                        </div>
                        <div style={{ display: 'flex', marginBottom: '5px'}}>
                            <label style={{ fontWeight: 'bold', marginRight: '2px' }}>Atores:</label>
                            <p>Ator 1, Ator 2, Ator 3, Ator 4</p>
                        </div>
                        <div style={{ display: 'flex', marginBottom: '5px'}}>
                            <label style={{ fontWeight: 'bold', marginRight: '2px' }}>Gêneros:</label>
                            <p>Luta, Comédia, Ação</p>
                        </div>
                        <div>
                            <p><label style={{ fontWeight: 'bold', marginRight: '2px' }}>Sinopse:</label>Rocky Balboa, um pequeno boxeador da classe trabalhadora da Filadélfia, é arbitrariamente escolhido para lutar contra o campeão dos pesos pesados, Apollo Creed, quando o adversário do invicto lutador agendado para a luta é ferido. Durante o treinamento com o mal-humorado Mickey Goldmill, Rocky timidamente começa um relacionamento com Adrian, a invisível irmã de Paulie, seu amigo empacotador de carne.</p>
                        </div>
                    </Grid.Column>
                </Grid.Row>
            </Grid>
        </Segment>
    );
};

export default MovieDetail;