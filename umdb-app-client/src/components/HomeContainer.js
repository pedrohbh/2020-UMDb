import React from 'react'
import { Grid, Segment, Button } from 'semantic-ui-react'

import api from '../services/api';
import { isAuthenticated } from '../services/auth';

import Filter from '../components/Filter'
import MovieSuggestion from '../components/MovieSuggestion'


const HomeContainer = props => {

    const openRdfXml = () => {
        api.get("/open/movie/data/", {
            "Content-Type": "application/xml; charset=utf-8"
        }).then((response) => {
            const blob = new Blob([response.data], {type: 'text/xml'});
            const url = URL.createObjectURL(blob);
            window.open(url);
            URL.revokeObjectURL(url);
        }).catch((error) => {
            console.error(error);
        });
    }

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
                <Button onClick={openRdfXml} style={{ width: '100%' }}>RFD/XML</Button>
            </Grid.Column>
            <Grid.Column width={12} stretched>
                { props.children }
            </Grid.Column>
        </Grid>
    );
};

export default HomeContainer;