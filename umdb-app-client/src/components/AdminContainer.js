import React from 'react'
import { Grid, Segment } from 'semantic-ui-react'

import AdminSidebar from '../components/AdminSidebar'


const AdminContainer = props => {
    return (
        <Grid>
            <Grid.Column width={4}>
                <AdminSidebar activeItem={props.activeItem} />
            </Grid.Column>
            <Grid.Column width={12} stretched>
                <Segment>
                    { props.children }
                </Segment>
            </Grid.Column>
        </Grid>
    );
};

export default AdminContainer;