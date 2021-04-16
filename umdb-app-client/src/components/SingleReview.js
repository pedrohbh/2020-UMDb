import React from 'react';
import { Segment, Header, Rating } from 'semantic-ui-react';

const SingleReview = ({ name, rating, text }) => {

    return (
        <Segment>
            <div style={{ position: 'relative' }}>
                <Header as="h4">{name}</Header>
                <div style={{ position: 'absolute', top: '0', right: '0' }}>
                    <Rating defaultRating={rating} maxRating={5} disabled style={{ marginRight: '5px' }} />
                    {rating}/5
                </div>
            </div>
            <p>{text}</p>
        </Segment>
    );
};

export default SingleReview;