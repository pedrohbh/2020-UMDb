import React from 'react';
import { Card, Rating } from 'semantic-ui-react';
import history from '../history'

const MovieCard = ({ title, year, link, imageSource, avgRate }) => {
    const handleClick = (e) => {
        e.preventDefault()
        history.push(link)
    }
    return (
        <Card style={{ marginBottom: '15px' }} onClick={handleClick}>
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
                <Rating defaultRating={Math.floor(avgRate)} maxRating={5} disabled style={{ marginRight: '5px' }} />
                {avgRate.toFixed(1)}/5
            </Card.Content>
        </Card>
    );
}

export default MovieCard;