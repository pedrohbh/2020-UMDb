import React from 'react'
import { Grid, Segment } from 'semantic-ui-react'

import Filter from '../components/Filter'


const HomeContainer = props => {
    return (
        <Grid>
            <Grid.Column width={4}>
                <Segment>
                    <Filter onFilterMovie={props.onFilterMovie}/>
                </Segment>
            </Grid.Column>
            <Grid.Column width={12} stretched>
                { props.children }
            </Grid.Column>
        </Grid>
    );
};

export default HomeContainer;