import React from 'react'
import { Grid, Segment } from 'semantic-ui-react'

import { isAuthenticated } from '../services/auth';

import Filter from '../components/Filter'
import MovieSuggestion from '../components/MovieSuggestion'


const HomeContainer = props => {
    return (
        <Grid>
            <Grid.Column width={4}>
                <Segment>
                    <Filter onFilterMovie={props.onFilterMovie}/>
                </Segment>
                {
                    isAuthenticated() ? (
                        <Segment>
                            <MovieSuggestion />
                        </Segment>
                    ) : null
                }
            </Grid.Column>
            <Grid.Column width={12} stretched>
                { props.children }
            </Grid.Column>
        </Grid>
    );
};

export default HomeContainer;