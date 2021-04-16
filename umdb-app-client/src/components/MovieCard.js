import React from 'react';
import { Card, Rating } from 'semantic-ui-react';

const MovieCard = ({ title, year, link, imageSource }) => {
    return (
        <Card style={{ marginBottom: '15px' }} href={link}>
            <img
                alt={title}
                style={{
                    height: '210px',
                    objectFit: 'cover',
                    objectPosition: 'top center'
                }}
                src={imageSource}
            />
            <Card.Content>
                <Card.Header>{title}</Card.Header>
                <Card.Meta>
                    <span className='date'>{year}</span>
                </Card.Meta>
            </Card.Content>
            <Card.Content extra>
                <Rating defaultRating={4} maxRating={5} disabled style={{ marginRight: '5px' }} />
                4.5/5
            </Card.Content>
        </Card>
    );
}

export default MovieCard;