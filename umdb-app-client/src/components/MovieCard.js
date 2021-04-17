import React from 'react';
import { Card, Rating } from 'semantic-ui-react';
import history from '../history'

const MovieCard = ({ title, year, link, imageSource }) => {
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
                <Rating defaultRating={4} maxRating={5} disabled style={{ marginRight: '5px' }} />
                4.5/5
            </Card.Content>
        </Card>
    );
}

export default MovieCard;