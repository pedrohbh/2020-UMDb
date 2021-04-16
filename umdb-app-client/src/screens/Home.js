import React from 'react';
import { Grid, Header } from 'semantic-ui-react';

import HomeContainer from '../components/HomeContainer';
import MovieCard from '../components/MovieCard';
import rockyImage from '../assets/rocky.jpeg'


const Home = () => {
    return (
        <>
            <Grid>
                <Grid.Row>
                    <Grid.Column width={16}>
                        <Header as='h2' textAlign="center">Filmes</Header>
                    </Grid.Column>
                </Grid.Row>
            </Grid>
            <HomeContainer>
                <Grid>
                    <Grid.Row>
                        <Grid.Column width={4}>
                            <MovieCard
                                fluid
                                title='Rocky Balboa'
                                year="2006"
                                imageSource={rockyImage}
                                link="/movie/1"
                                style={{ marginBottom: '15px' }}
                            />
                        </Grid.Column>
                        <Grid.Column width={4}>
                            <MovieCard
                                fluid
                                title='Rocky Balboa'
                                year="2006"
                                imageSource={rockyImage}
                                link="/movie/2"
                                style={{ marginBottom: '15px' }}
                            />
                        </Grid.Column>
                        <Grid.Column width={4}>
                            <MovieCard
                                fluid
                                title='Rocky Balboa'
                                year="2006"
                                imageSource={rockyImage}
                                link="/movie/3"
                                style={{ marginBottom: '15px' }}
                            />
                        </Grid.Column>
                        <Grid.Column width={4}>
                            <MovieCard
                                fluid
                                title='Rocky Balboa'
                                year="2006"
                                imageSource={rockyImage}
                                link="/movie/4"
                                style={{ marginBottom: '15px' }}
                            />
                        </Grid.Column>
                        <Grid.Column width={4}>
                            <MovieCard
                                fluid
                                title='Rocky Balboa'
                                year="2006"
                                imageSource={rockyImage}
                                link="/movie/5"
                                style={{ marginBottom: '15px' }}
                            />
                        </Grid.Column>
                        <Grid.Column width={4}>
                            <MovieCard
                                fluid
                                title='Rocky Balboa'
                                year="2006"
                                imageSource={rockyImage}
                                link="/movie/6"
                                style={{ marginBottom: '15px' }}
                            />
                        </Grid.Column>
                    </Grid.Row>
                </Grid>
            </HomeContainer>
        </>
    );
};

export default Home;