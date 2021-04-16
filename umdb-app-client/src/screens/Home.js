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
                                link="/"
                            />
                        </Grid.Column>
                        <Grid.Column width={4}>
                            <MovieCard
                                fluid
                                title='Rocky Balboa'
                                year="2006"
                                imageSource={rockyImage}
                                link="/"
                                style={{ marginBottom: '15px' }}
                            />
                        </Grid.Column>
                        <Grid.Column width={4}>
                            <MovieCard
                                fluid
                                title='Rocky Balboa'
                                year="2006"
                                imageSource={rockyImage}
                                link="/"
                                style={{ marginBottom: '15px' }}
                            />
                        </Grid.Column>
                        <Grid.Column width={4}>
                            <MovieCard
                                fluid
                                title='Rocky Balboa'
                                year="2006"
                                imageSource={rockyImage}
                                link="/"
                                style={{ marginBottom: '15px' }}
                            />
                        </Grid.Column>
                        <Grid.Column width={4}>
                            <MovieCard
                                fluid
                                title='Rocky Balboa'
                                year="2006"
                                imageSource={rockyImage}
                                link="/"
                                style={{ marginBottom: '15px' }}
                            />
                        </Grid.Column>
                        <Grid.Column width={4}>
                            <MovieCard
                                fluid
                                title='Rocky Balboa'
                                year="2006"
                                imageSource={rockyImage}
                                link="/"
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