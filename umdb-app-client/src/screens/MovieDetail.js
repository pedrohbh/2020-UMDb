import React from 'react';
import { useParams, Link } from 'react-router-dom';
import { Segment, Grid, Header, Image } from 'semantic-ui-react';

import rockyImage from '../assets/rocky.jpeg'
import MovieInfo from '../components/MovieInfo';
import SingleReview from '../components/SingleReview';

const MovieDetail = () => {
    let { id } = useParams();

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
                            <div style={{ position: 'relative' }}>
                                <Header as="h3">Reviews</Header>
                                <Link
                                    to={`/movie/${id}/review`}
                                    className="ui labeled icon button mini"
                                    style={{ position: 'absolute', top: '0', right: '0' }}
                                >
                                    <i className="plus icon"></i>
                                    Adicionar
                                </Link>
                            </div>
                            <SingleReview
                                name="Breno Krohling"
                                rating="4"
                                text="Pellentesque habitant morbi tristique senectus."
                            />
                            <SingleReview
                                name="Lucas Tassis"
                                rating="4"
                                text="Pellentesque habitant morbi tristique senectus."
                            />
                        </div>
                    </Grid.Column>
                </Grid.Row>
            </Grid>
        </Segment>
    );
};

export default MovieDetail;